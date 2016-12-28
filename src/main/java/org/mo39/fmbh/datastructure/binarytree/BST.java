package org.mo39.fmbh.datastructure.binarytree;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.ImmutablePair;
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
public class BST<T> extends Tree<T> {

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
   * Convenient method to insert a new node to this BST.
   * <p>
   * Note: {@link InsertSol#ITERATIVE_SOLUTION} is used to insert data.
   *
   * @param data
   */
  @Override
  public void insert(T data) {
    insert(data, InsertSol.ITERATIVE_SOLUTION);
  }

  /**
   * Insert a new node to this BST.
   *
   * @param data
   * @param sol
   */
  public void insert(T data, InsertSol sol) {
    size++;
    sol.solve(this, data);
  }

  /**
   * Find a TreeNode given the search key.
   * <p>
   * Note: {@link SearchSol#ITERATIVE_SOLUTION} is used to search for the TreeNode.<br>
   *
   * @param key
   * @param sol
   * @return
   */
  @Override
  public TreeNode<T> search(T key) {
    return SearchSol.ITERATIVE_SOLUTION.solve(this, key).left;
  }

  @Override
  public TreeNode<T> trace(T key, Consumer<TreeNode<T>> consumer) {
    ImmutablePair<TreeNode<T>, TreeNode<T>> tuple =
        TraceSol.ITERATIVE_SOLUTION.solve(this, key, consumer);
    return tuple.left == null ? tuple.right : tuple.left;
  }

  /**
   * Find a TreeNode given the search key.
   *
   * @param key
   * @param sol
   * @return
   */
  public TreeNode<T> search(T key, SearchSol sol) {
    ImmutablePair<TreeNode<T>, TreeNode<T>> tuple = sol.solve(this, key);
    return tuple == null ? null : tuple.left;
  }

  /**
   * Search and delete a TreeNode given the search key.
   * <p>
   * Note: {@link SearchSol#ITERATIVE_SOLUTION} is used to search for the TreeNode.<br>
   *
   * @param key
   * @return true if the key is found and deleted. false if the key is not found.
   */
  @Override
  public boolean delete(T key) {
    if (delete(key, SearchSol.ITERATIVE_SOLUTION)) {
      size--;
      return true;
    } else return false;
  }

  /**
   * Search and delete a TreeNode given the search key.
   *
   * @param key
   * @param sol
   * @return true if the key is found and deleted. false if the key is not found.
   */
  public boolean delete(T key, SearchSol sol) {
    ImmutablePair<TreeNode<T>, TreeNode<T>> tuple = sol.solve(this, key);
    if (tuple == null) return false;
    // If node is found but parent is null, then it's the root. Create a dummy node.
    if (tuple.right == null) {
      TreeNode<T> dummyRoot = new TreeNode<>(null);
      dummyRoot.right = root;
      tuple = ImmutablePair.of(tuple.left, dummyRoot);
    }
    // The node to be deleted has no child.
    if (tuple.left.left == null && tuple.left.right == null)
      transplant(tuple.right, tuple.left, null);
    // The node to be deleted has one child.
    else if (tuple.left.left == null || tuple.left.right == null) transplant(tuple.right,
        tuple.left, tuple.left.left == null ? tuple.left.right : tuple.left.left);
    // The node to be deleted has two children.
    else {
      if (tuple.left.right.left == null) {
        transplant(tuple.right, tuple.left, tuple.left.right);
        tuple.left.right.left = tuple.left.left;
      } else {
        ImmutablePair<TreeNode<T>, TreeNode<T>> p = getPeakNode(tuple.left.right, o -> o.left);
        transplant(p.right, p.left, null);
        p.left.left = tuple.left.left;
        p.left.right = tuple.left.right;
        transplant(tuple.right, tuple.left, p.left);
      }
    }
    // If using dummyRoot, replace the new root.
    if (tuple.right.val == null) root = tuple.left.right;
    return true;
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

  /**
   * Get the maximum.
   *
   * @return
   */
  @Override
  public TreeNode<T> getMax() {
    return getPeakNode(root, o -> o.right).left;
  }

  /**
   * Get the minimum.
   *
   * @return
   */
  @Override
  public TreeNode<T> getMin() {
    return getPeakNode(root, o -> o.left).left;
  }


  /**
   * Use a translator to walk along the tree path to find the TreeNode with peak value. Return a
   * node with its parent.
   *
   * @param translator
   * @return
   */
  private ImmutablePair<TreeNode<T>, TreeNode<T>> getPeakNode(TreeNode<T> root,
      Function<TreeNode<T>, TreeNode<T>> f) {
    checkArgument(root != null);
    TreeNode<T> pre = null;
    while (f.apply(root) != null) {
      pre = root;
      root = f.apply(root);
    }
    return ImmutablePair.of(root, pre);
  }

  public enum InsertSol {

    /**
     * Iteratively walk along the tree path and find the place to insert the wrapped data.
     *
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> void handle(Tree<T> bst, T data) {
        TreeNode<T> pre = null;
        TreeNode<T> curr = bst.root;
        while (curr != null) {
          pre = curr;
          int compareResult = bst.comparator.compare(data, curr.val);
          checkArgument(compareResult != 0, "Duplicate node are not allowed: " + data);
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
    RECURSIVE_SOLUTION {

      @Override
      public <T> void handle(Tree<T> bst, T data) {
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

    public <T> void solve(Tree<T> bst, T data) {
      checkArgument(data != null);
      if (bst.root == null) {
        bst.root = new TreeNode<>(data);
        return;
      }
      handle(bst, data);
    };

    public abstract <T> void handle(Tree<T> bst, T data);
  }

  public enum TraceSol {

    ITERATIVE_SOLUTION {

      @Override
      public <T> ImmutablePair<TreeNode<T>, TreeNode<T>> handle(Tree<T> bst, T key,
          Consumer<TreeNode<T>> consumer) {
        TreeNode<T> curr = bst.root;
        TreeNode<T> pre = null;
        while (curr != null) {
          consumer.accept(curr);
          int compareResult = bst.comparator.compare(key, curr.val);
          if (compareResult == 0) return ImmutablePair.of(curr, pre);
          pre = curr;
          if (compareResult < 0) curr = curr.left;
          else curr = curr.right;
        }
        return ImmutablePair.of(null, pre);
      }

    },

    RECURSIVE_SOLUTION {

      @Override
      public <T> ImmutablePair<TreeNode<T>, TreeNode<T>> handle(Tree<T> bst, T key,
          Consumer<TreeNode<T>> consumer) {
        return recur(bst.root, null, bst.comparator, key, consumer);
      }

      private <T> ImmutablePair<TreeNode<T>, TreeNode<T>> recur(TreeNode<T> curr, TreeNode<T> pre,
          Comparator<T> comparator, T key, Consumer<TreeNode<T>> consumer) {
        if (curr == null) return ImmutablePair.of(null, pre);
        consumer.accept(curr);
        int compareResult = comparator.compare(key, curr.val);
        if (compareResult == 0) return ImmutablePair.of(curr, pre);
        pre = curr;
        if (compareResult < 0) return recur(curr.left, pre, comparator, key, consumer);
        return recur(curr.right, pre, comparator, key, consumer);
      }

    };

    protected abstract <T> ImmutablePair<TreeNode<T>, TreeNode<T>> handle(Tree<T> bst, T key,
        Consumer<TreeNode<T>> consumer);

    public <T> ImmutablePair<TreeNode<T>, TreeNode<T>> solve(Tree<T> bst, T key,
        Consumer<TreeNode<T>> consumer) {
      checkArgument(key != null);
      return handle(bst, key, consumer);
    }
  }

  public enum SearchSol {

    ITERATIVE_SOLUTION() {

      @Override
      public <T> ImmutablePair<TreeNode<T>, TreeNode<T>> handle(Tree<T> bst, T key) {
        return TraceSol.ITERATIVE_SOLUTION.solve(bst, key, t -> {
          return;
        });
      }

    },

    RECURSIVE_SOLUTION() {

      @Override
      public <T> ImmutablePair<TreeNode<T>, TreeNode<T>> handle(Tree<T> bst, T key) {
        return TraceSol.RECURSIVE_SOLUTION.solve(bst, key, t -> {
          return;
        });
      }

    };

    protected abstract <T> ImmutablePair<TreeNode<T>, TreeNode<T>> handle(Tree<T> bst, T key);

    public <T> ImmutablePair<TreeNode<T>, TreeNode<T>> solve(Tree<T> bst, T key) {
      checkArgument(key != null);
      return handle(bst, key);
    }

  }

  public static class TestBST {

    private Integer[] datas = new TestData().noDupulicateIntegerArr0;
    private Tree<Integer> bst = BST.init();

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
      Tree<Integer> recursive = BST.init();
      for (Integer data : datas) {
        InsertSol.RECURSIVE_SOLUTION.solve(recursive, data);
      }
      Z.verify(bst.root, recursive.root);
      // ---------
      Tree<Integer> iterative = BST.init();
      for (Integer data : datas) {
        InsertSol.ITERATIVE_SOLUTION.solve(iterative, data);
      }
      Z.verify(bst.root, iterative.root);
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
      Assert.assertNull(SearchSol.ITERATIVE_SOLUTION.solve(bst, 0).left);
      Assert.assertNull(SearchSol.RECURSIVE_SOLUTION.solve(bst, 0).left);
      Assert.assertEquals(new Integer(5), SearchSol.ITERATIVE_SOLUTION.solve(bst, 5).left.val);
      Assert.assertEquals(new Integer(5), SearchSol.RECURSIVE_SOLUTION.solve(bst, 5).left.val);
      // ---------
      Assert.assertEquals(new Integer(7), SearchSol.ITERATIVE_SOLUTION.solve(bst, 9).right.val);
      Assert.assertEquals(new Integer(7), SearchSol.ITERATIVE_SOLUTION.solve(bst, 3).right.val);
      Assert.assertEquals(new Integer(7), SearchSol.RECURSIVE_SOLUTION.solve(bst, 9).right.val);
      Assert.assertEquals(new Integer(7), SearchSol.RECURSIVE_SOLUTION.solve(bst, 3).right.val);
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
      Z.verify(root, bst.root);
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
      Z.verify(root, bst.root);
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
      Z.verify(root, bst.root);
    }

  }


}
