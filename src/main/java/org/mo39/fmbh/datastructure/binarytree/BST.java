package org.mo39.fmbh.datastructure.binarytree;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Binary Search Tree.
 * <p>
 * After research, there's no way to get generic type of T at runtime because of type-erasure, so
 * it's not possible to make sure the generic type T implements Comparable when initiating the
 * instance using constructor if T itself does not extends Comparable. Instead of cast to Comparable
 * at runtime, the constructor is changed to static factory method to check generic type at compile
 * time. So, the conclusion is, always consider static factory method over constructor.
 * <p>
 * Cast is not used for these two reasons:<br>
 * 1. Cast would throw an Exception only when the second element is inserted into this BST when
 * doing comparation.<br>
 * 2. Down cast has a performance over head.<br>
 * <p>
 * There's another walk around to infer the generic type, for example, by providing a root as an
 * argument for BST constructor then get the class of this root and see if it's Comparable, but
 * doing this way harms the usage of this class because one has to provide a root when initate the
 * class, which may add extra code.
 *
 * @author Jihan Chen
 *
 */
public class BST<T> {

  private TreeNode<T> root;
  private Comparator<T> comparator;

  /**
   * Create a new BST with a {@link Comparable} generic type.
   *
   * @return a new BST.
   */
  public static <T extends Comparable<T>> BST<T> init() {
    return new BST<T>((o1, o2) -> o1.compareTo(o2));
  }

  /**
   * Create a new BST with given a {@link Comparator}
   *
   * @return a new BST.
   */
  public static <T> BST<T> init(Comparator<T> comparator) {
    return new BST<T>(comparator);
  }

  /**
   * Create a new BST with given {@link Comparator}.
   *
   * @param comparator
   */
  protected BST(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  /**
   * Return the root of this BST.
   *
   * @return
   */
  public TreeNode<T> getRoot() {
    return root;
  }

  /**
   * Convenient method to insert a new node to this BST.
   * <p>
   * Note: {@link InsertSol#ITERATIVE_SOLUTION} is used to insert data.
   *
   * @param data
   */
  public void isnert(T data) {
    InsertSol.ITERATIVE_SOLUTION.solve(this, data);
  }

  /**
   * Insert a new node to this BST.
   *
   * @param data
   * @param sol
   */
  public void insert(T data, InsertSol sol) {
    sol.solve(this, data);
  }

  /**
   * Find a TreeNode given the search key.
   *
   * @param key
   * @param sol
   * @return
   */
  public TreeNode<T> search(T key, SearchSol sol) {
    NodeP<T> nodeP = sol.solve(this, key);
    return nodeP == null ? null : nodeP.node;
  }

  /**
   * Search and delete a TreeNode given the search key.
   * <p>
   * Note: {@link SearchSol#ITERATIVE_SOLUTION} is used to search for the TreeNode.<br>
   *
   * @param key
   * @return true if the key is found and deleted. false if the key is not found.
   */
  public boolean delete(T key) {
    return delete(key, SearchSol.ITERATIVE_SOLUTION);
  }

  /**
   * Search and delete a TreeNode given the search key.
   *
   * @param key
   * @param sol
   * @return true if the key is found and deleted. false if the key is not found.
   */
  public boolean delete(T key, SearchSol sol) {
    NodeP<T> nodeP = sol.solve(this, key);
    if (nodeP == null) return false;
    // If node is found but parent is null, then it's the root. Create a dummy node.
    if (nodeP.parent == null) {
      TreeNode<T> dummyRoot = new TreeNode<>(null);
      dummyRoot.right = root;
      nodeP = new NodeP<T>(nodeP.node, dummyRoot);
    }
    // The node to be deleted has no child.
    if (nodeP.node.left == null && nodeP.node.right == null)
      transplant(nodeP.parent, nodeP.node, null);
    // The node to be deleted has one child.
    else if (nodeP.node.left == null || nodeP.node.right == null) transplant(nodeP.parent,
        nodeP.node, nodeP.node.left == null ? nodeP.node.right : nodeP.node.left);
    // The node to be deleted has two children.
    else {
      if (nodeP.node.right.left == null) {
        transplant(nodeP.parent, nodeP.node, nodeP.node.right);
        nodeP.node.right.left = nodeP.node.left;
      } else {
        NodeP<T> p = getPeakNode(nodeP.node.right, o -> o.left);
        transplant(p.parent, p.node, null);
        p.node.left = nodeP.node.left;
        p.node.right = nodeP.node.right;
        transplant(nodeP.parent, nodeP.node, p.node);
      }
    }
    // If using dummyRoot, replace the new root.
    if (nodeP.parent.val == null) root = nodeP.node.right;
    return true;
  }

  /**
   * Get the maximum.
   *
   * @return
   */
  public TreeNode<T> getMax() {
    return getPeakNode(root, o -> o.right).node;
  }

  /**
   * Get the minimum.
   *
   * @return
   */
  public TreeNode<T> getMin() {
    return getPeakNode(root, o -> o.left).node;
  }


  /**
   * Use a translator to walk along the tree path to find the TreeNode with peak value. Return a
   * node with its parent.
   *
   * @param translator
   * @return
   */
  private NodeP<T> getPeakNode(TreeNode<T> root, Function<TreeNode<T>, TreeNode<T>> f) {
    checkArgument(root != null);
    TreeNode<T> pre = null;
    while (f.apply(root) != null) {
      pre = root;
      root = f.apply(root);
    }
    return new NodeP<T>(root, pre);
  }

  /**
   * Given a parent node, its child node, replace the child node with a new node.
   *
   * @param parent
   * @param searchResult
   * @param newNode
   */
  private void transplant(TreeNode<T> parent, TreeNode<T> child, TreeNode<T> newNode) {
    if (parent.left == child) {
      parent.left = newNode;
    } else if (parent.right == child) parent.right = newNode;
    else throw new AssertionError();
  }

  public enum InsertSol {

    /**
     * Iteratively walk along the tree path and find the place to insert the wrapped data.
     *
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> void handle(BST<T> bst, T data) {
        if (bst.root == null) {
          bst.root = new TreeNode<>(data);
          return;
        }
        TreeNode<T> pre = null;
        TreeNode<T> curr = bst.root;
        while (curr != null) {
          pre = curr;
          int compareResult = bst.comparator.compare(data, curr.val);
          checkArgument(compareResult != 0, "Duplicate node are not allowed.");
          if (compareResult < 0) curr = curr.left;
          else curr = curr.right;
        }
        if (bst.comparator.compare(data, pre.val) < 0) pre.left = new TreeNode<>(data);
        else pre.right = new TreeNode<>(data);
      }

    },

    /**
     * Recursively walk along the tree path and find the place to insert the wrapped data.
     *
     */
    RECUSIVE_SOLUTION {

      @Override
      public <T> void handle(BST<T> bst, T data) {
        recur(data, bst.comparator, bst.root);
      }

      private <T> void recur(T data, Comparator<T> c, TreeNode<T> curr) {
        int compareResult = c.compare(data, curr.val);
        checkArgument(compareResult != 0, "Duplicate node are not allowed.");
        if (compareResult < 0) {
          if (curr.left == null) {
            curr.left = new TreeNode<>(data);
            return;
          }
          recur(data, c, curr.left);
        } else {
          if (curr.right == null) {
            curr.right = new TreeNode<>(data);
            return;
          }
          recur(data, c, curr.right);
        }
      }
    };

    public <T> void solve(BST<T> bst, T data) {
      checkArgument(data != null);
      if (bst.root == null) {
        bst.root = new TreeNode<>(data);
        return;
      }
      handle(bst, data);
    };

    public abstract <T> void handle(BST<T> bst, T data);
  }

  public enum SearchSol {

    ITERATIVE_SOLUTION() {

      @Override
      public <T> NodeP<T> handle(BST<T> bst, T key) {
        TreeNode<T> curr = bst.root;
        TreeNode<T> pre = null;
        while (curr != null) {
          int compareResult = bst.comparator.compare(key, curr.val);
          if (compareResult == 0) return new NodeP<T>(curr, pre);
          pre = curr;
          if (compareResult < 0) curr = curr.left;
          else curr = curr.right;
        }
        return null;
      }

    },

    RECURSIVE_SOLUTION() {

      @Override
      public <T> NodeP<T> handle(BST<T> bst, T key) {
        return recur(bst.root, null, bst.comparator, key);
      }

      private <T> NodeP<T> recur(TreeNode<T> curr, TreeNode<T> pre, Comparator<T> comparator,
          T key) {
        if (curr == null) return null;
        int compareResult = comparator.compare(key, curr.val);
        if (compareResult == 0) return new NodeP<T>(curr, pre);
        pre = curr;
        if (compareResult < 0) return recur(curr.left, pre, comparator, key);
        else return recur(curr.right, pre, comparator, key);
      }

    };

    protected abstract <T> NodeP<T> handle(BST<T> bst, T key);

    public <T> NodeP<T> solve(BST<T> bst, T key) {
      checkArgument(key != null);
      return handle(bst, key);
    }

  }

  /**
   * Helper class used to store a TreeNode and its parent.
   *
   * @author Jihan Chen
   *
   * @param <T>
   */
  public static class NodeP<T> {

    public final TreeNode<T> node;
    public final TreeNode<T> parent;

    public NodeP(TreeNode<T> node, TreeNode<T> parent) {
      this.node = node;
      this.parent = parent;
    }
  }

  public static class TestBST {

    private Integer[] datas = new TestData().noDupulicateIntegerArr0;
    private BST<Integer> bst = BST.init();

    @Before
    public void before() throws IOException {
      TreeNode<Integer> root = new TreeNode<>(7);
      TreeNode<Integer> treeNode1 = new TreeNode<>(3);
      TreeNode<Integer> treeNode2 = new TreeNode<>(2);
      TreeNode<Integer> treeNode3 = new TreeNode<>(4);
      TreeNode<Integer> treeNode4 = new TreeNode<>(5);
      TreeNode<Integer> treeNode5 = new TreeNode<>(9);
      // Construct a bst structure manually.
      root.left = treeNode1;
      root.right = treeNode5;
      treeNode1.left = treeNode2;
      treeNode1.right = treeNode3;
      treeNode3.right = treeNode4;
      // This is not a valid way to create a bst outside this class.
      bst.root = root;
    }

    @Test
    public void testInsertSol() {
      // ---------
      BST<Integer> recursive = BST.init();
      for (Integer data : datas) {
        recursive.insert(data, InsertSol.RECUSIVE_SOLUTION);
      }
      Z.verifyTreeNodes(bst.root, recursive.root);
      // ---------
      BST<Integer> iterative = BST.init();
      for (Integer data : datas) {
        iterative.insert(data, InsertSol.ITERATIVE_SOLUTION);
      }
      Z.verifyTreeNodes(bst.root, iterative.root);
    }

    @Test
    public void testGetMax() {
      Assert.assertEquals(new Integer(9), bst.getMax().val);
    }

    @Test
    public void testGetMin() {
      Assert.assertEquals(new Integer(2), bst.getMin().val);
    }

    @Test
    public void testSearch() {
      // ---------
      Assert.assertNull(bst.search(0, SearchSol.ITERATIVE_SOLUTION));
      Assert.assertNull(bst.search(0, SearchSol.RECURSIVE_SOLUTION));
      Assert.assertEquals(new Integer(5), bst.search(5, SearchSol.ITERATIVE_SOLUTION).val);
      Assert.assertEquals(new Integer(5), bst.search(5, SearchSol.RECURSIVE_SOLUTION).val);
      // ---------
      Assert.assertEquals(new Integer(7), SearchSol.ITERATIVE_SOLUTION.solve(bst, 9).parent.val);
      Assert.assertEquals(new Integer(7), SearchSol.ITERATIVE_SOLUTION.solve(bst, 3).parent.val);
      Assert.assertEquals(new Integer(7), SearchSol.RECURSIVE_SOLUTION.solve(bst, 9).parent.val);
      Assert.assertEquals(new Integer(7), SearchSol.RECURSIVE_SOLUTION.solve(bst, 3).parent.val);
    }

    @Test
    public void testDeleteRoot() {
      // ---------
      TreeNode<Integer> root = new TreeNode<>(9);
      TreeNode<Integer> treeNode1 = new TreeNode<>(3);
      TreeNode<Integer> treeNode2 = new TreeNode<>(2);
      TreeNode<Integer> treeNode3 = new TreeNode<>(4);
      TreeNode<Integer> treeNode4 = new TreeNode<>(5);
      // Construct a bst structure manually.
      root.left = treeNode1;
      treeNode1.left = treeNode2;
      treeNode1.right = treeNode3;
      treeNode3.right = treeNode4;
      // ---------
      bst.delete(7);
      Z.verifyTreeNodes(root, bst.root);
    }

    @Test
    public void testDeleteTreeNode3() {
      // Construct a bst structure manually.
      TreeNode<Integer> root = new TreeNode<>(7);
      TreeNode<Integer> treeNode2 = new TreeNode<>(2);
      TreeNode<Integer> treeNode3 = new TreeNode<>(4);
      TreeNode<Integer> treeNode4 = new TreeNode<>(5);
      TreeNode<Integer> treeNode5 = new TreeNode<>(9);
      root.left = treeNode3;
      root.right = treeNode5;
      treeNode3.left = treeNode2;
      treeNode3.right = treeNode4;
      // ---------
      bst.delete(3);
      Z.verifyTreeNodes(root, bst.root);
    }

    @Test
    public void testDeleteTreeNode4() {
      // Construct a bst structure manually.
      TreeNode<Integer> root = new TreeNode<>(7);
      TreeNode<Integer> treeNode1 = new TreeNode<>(3);
      TreeNode<Integer> treeNode2 = new TreeNode<>(2);
      TreeNode<Integer> treeNode4 = new TreeNode<>(5);
      TreeNode<Integer> treeNode5 = new TreeNode<>(9);
      root.left = treeNode1;
      root.right = treeNode5;
      treeNode1.left = treeNode2;
      treeNode1.right = treeNode4;
      // ---------
      bst.delete(4);
      Z.verifyTreeNodes(root, bst.root);
    }

  }


}
