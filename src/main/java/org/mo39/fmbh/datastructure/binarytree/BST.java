package org.mo39.fmbh.datastructure.binarytree;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.interfaces.Translator;

/**
 * Binary Search Tree.
 * <p>
 * After research, there's no way to get generic type of T at runtime because of type-erasure, so
 * it's not possible to make sure the generic type T implements Comparable when initiating the
 * instance using construct if T itself does not extends Comparable. Instead of cast to Comparableat
 * runtime, the constructor is changed to static factory method to check generic type at compile
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
    return sol.solve(this, key);
  }

  /**
   * Get the maximum.
   *
   * @return
   */
  public TreeNode<T> getMax() {
    return getPeakNode(o -> o.right);
  }

  /**
   * Get the minimum.
   *
   * @return
   */
  public TreeNode<T> getMin() {
    return getPeakNode(o -> o.left);
  }

  /**
   * Use a translator to walk along the tree path to find the TreeNode with peak value.
   *
   * @param translator
   * @return
   */
  private TreeNode<T> getPeakNode(Translator<TreeNode<T>, TreeNode<T>> translator) {
    TreeNode<T> curr = root;
    while (translator.translate(curr) != null) {
      curr = translator.translate(curr);
    }
    return curr;
  }

  public enum InsertSol {

    ITERATIVE_SOLUTION() {

      @Override
      public <T> void solve(BST<T> bst, T data) {
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

    RECUSIVE_SOLUTION {

      @Override
      public <T> void solve(BST<T> bst, T data) {
        if (bst.root == null) {
          bst.root = new TreeNode<>(data);
          return;
        }
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

    public abstract <T> void solve(BST<T> bst, T data);
  }

  public enum SearchSol {

    ITERATIVE_SOLUTION() {

      @Override
      public <T> TreeNode<T> solve(BST<T> bst, T key) {
        TreeNode<T> curr = bst.root;
        while (curr != null) {
          int compareResult = bst.comparator.compare(key, curr.val);
          if (compareResult == 0) return curr;
          else if (compareResult < 0) curr = curr.left;
          else curr = curr.right;
        }
        return null;
      }

    },

    RECURSIVE_SOLUTION() {

      @Override
      public <T> TreeNode<T> solve(BST<T> bst, T key) {
        return recur(bst.root, bst.comparator, key);
      }

      private <T> TreeNode<T> recur(TreeNode<T> curr, Comparator<T> comparator, T key) {
        if (curr == null) return null;
        int compareResult = comparator.compare(key, curr.val);
        if (compareResult == 0) return curr;
        else if (compareResult < 0) return recur(curr.left, comparator, key);
        else return recur(curr.right, comparator, key);
      }

    };

    public abstract <T> TreeNode<T> solve(BST<T> bst, T key);
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
      Assert.assertNull(bst.search(0, SearchSol.ITERATIVE_SOLUTION));
      Assert.assertNull(bst.search(0, SearchSol.RECURSIVE_SOLUTION));
      Assert.assertEquals(new Integer(5), bst.search(5, SearchSol.ITERATIVE_SOLUTION).val);
      Assert.assertEquals(new Integer(5), bst.search(5, SearchSol.RECURSIVE_SOLUTION).val);
      Assert.assertEquals(new Integer(3), bst.search(3, SearchSol.ITERATIVE_SOLUTION).val);
      Assert.assertEquals(new Integer(3), bst.search(3, SearchSol.RECURSIVE_SOLUTION).val);
    }

  }


}
