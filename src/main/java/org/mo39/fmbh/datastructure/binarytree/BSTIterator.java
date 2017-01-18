package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the
 * root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the
 * height of the tree.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/bst-iterator/">Bst Iterator</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BSTIterator {

  SOLUTION {

    TreeNode<Integer> cur;
    Deque<TreeNode<Integer>> stack = new ArrayDeque<>();

    @Override
    public void init(TreeNode<Integer> root) {
      this.cur = root;
    }

    /**
     * This should go to the left most and push each as a in-order traversal.</br>
     * This always requires the cur to be the next one to handle.
     */
    @Override
    public boolean hasNext() {
      for (; cur != null; stack.push(cur), cur = cur.left);
      return !stack.isEmpty();
    }

    /**
     * this always pop the one to return and make cur the next one to handle. </br>
     * Don't do push here. make cur.right to be the next one handled by hasNext().
     */
    @Override
    public int next() {
      int result = (cur = stack.pop()).val;
      cur = cur.right;
      return result;
    }

  };

  public abstract void init(TreeNode<Integer> root);

  public abstract boolean hasNext();

  public abstract int next();

  public static class TestBSTIterator {

    private TreeNode<Integer> root = new TreeNode<>(1);
    private List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2));

    {
      root.right = new TreeNode<>(2);
    }

    private void verify(BSTIterator sol) {
      List<Integer> list = new ArrayList<>();
      int i = 1;
      sol.init(root);
      while (sol.hasNext()) {
        list.add(sol.next());
        if (i++ > 10) break;
      }
      Assert.assertEquals(expected, list);
    }

    @Test
    public void testSolutions() {
      verify(SOLUTION);
    }

  }

}
