package org.mo39.fmbh.datastructure.binarytree;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.datastructure.binarytree.TreeNode.LevelOrderSol;

public class BinarySearchTree<T extends Comparable<T>> {

  private TreeNode<T> root;
  private Comparator<T> comparator;

  public BinarySearchTree() {}

  public BinarySearchTree(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  private int compare(T data, T other) {
    return comparator == null ? data.compareTo(other) : comparator.compare(data, other);
  }

  public TreeNode<T> getRoot() {
    return root;
  }

  public void insert(T data) {
    insert(data, InsertSol.ITERATIVE_SOLUTION);
  }

  public void insert(T data, InsertSol sol) {
    switch (sol) {
      case ITERATIVE_SOLUTION:
        iterativeInsert(data);
        break;
      case RECUSIVE_SOLUTION:
        recursiveInsert(data, root);
        break;
      default:
        throw new Error();
    }
  }

  private void iterativeInsert(T data) {
    if (root == null) {
      root = new TreeNode<>(data);
      return;
    }
    TreeNode<T> pre = null;
    TreeNode<T> curr = root;
    while (curr != null) {
      pre = curr;
      int compareResult = compare(data, curr.val);
      checkArgument(compareResult != 0, "Duplicate node are not allowed.");
      if (compareResult < 0) curr = curr.left;
      else curr = curr.right;
    }
    if (compare(data, pre.val) < 0) pre.left = new TreeNode<>(data);
    else pre.right = new TreeNode<>(data);
  }

  private void recursiveInsert(T data, TreeNode<T> curr) {
    if (root == null) {
      root = new TreeNode<>(data);
      return;
    }
    int compareResult = compare(data, curr.val);
    checkArgument(compareResult != 0, "Duplicate node are not allowed.");
    if (compareResult < 0) {
      if (curr.left == null) {
        curr.left = new TreeNode<>(data);
        return;
      }
      recursiveInsert(data, curr.left);
    } else {
      if (curr.right == null) {
        curr.right = new TreeNode<>(data);
        return;
      }
      recursiveInsert(data, curr.right);
    }

  }

  public enum InsertSol {
    ITERATIVE_SOLUTION, RECUSIVE_SOLUTION;
  }

  public static class TestBinarySearchTree {

    private Integer[] datas = new TestData().noDupulicateIntegerArr0;
    private BinarySearchTree<Integer> bst;

    @Before
    public void before() throws IOException {
      bst = new BinarySearchTree<>();
      for (Integer data : datas) {
        bst.insert(data);
      }
    }

    @Test
    public void testInsertSol() {
      BinarySearchTree<Integer> otherBst = new BinarySearchTree<>();
      for (Integer data : datas) {
        otherBst.insert(data, InsertSol.RECUSIVE_SOLUTION);
      }
      Assert.assertEquals(//
          bst.root.bfs(LevelOrderSol.ITERATIVE_SOLUTION_WITH_NULL),
          otherBst.root.bfs(LevelOrderSol.ITERATIVE_SOLUTION_WITH_NULL));
    }

    @Test
    public void testPreOrder() {

    }

  }


}
