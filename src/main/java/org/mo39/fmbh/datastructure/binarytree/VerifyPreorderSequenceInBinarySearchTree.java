package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;


/**
 * <pre>
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a
 * binary search tree.
 * 
 * You may assume each number in the sequence is unique.
 * 
 * Follow up: Could you do it using only constant space complexity?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/">
 *      Verify Preorder Sequence In Binary Search Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum VerifyPreorderSequenceInBinarySearchTree {

  RECURSIVE_SOLUTION {

    @Override
    public boolean solve(int[] preorder) {
      return recur(preorder, 0, preorder.length - 1);
    }

    private boolean recur(int[] preorder, int start, int end) {
      if (start >= end) return true;
      int root = preorder[start], posi = -1;
      for (int i = start + 1; i <= end; i++) {
        if (preorder[i] > root && posi == -1) posi = i;
        if (posi == -1 && preorder[i] > root) return false;
        if (posi != -1 && preorder[i] < root) return false;
      }
      posi = posi == -1 ? end : posi;
      return recur(preorder, start + 1, posi) & recur(preorder, posi, end);
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public boolean solve(int[] preorder) {
      int low = Integer.MIN_VALUE;
      Stack<Integer> path = new Stack<>();
      for (int value : preorder) {
        if (value < low) return false;
        while (!path.empty() && value > path.peek()) {
          low = path.pop();
        }
        path.push(value);
      }
      return true;
    }

  },

  SOLUTION {

    @Override
    public boolean solve(int[] preorder) {
      int low = Integer.MIN_VALUE, i = -1;
      for (int p : preorder) {
        if (p < low) return false;
        while (i >= 0 && p > preorder[i]) {
          low = preorder[i--];
        }
        preorder[++i] = p;
      }
      return true;
    }

  };

  public abstract boolean solve(int[] preorder);

  public static class TestVerifyPreorderSequenceInBinarySearchTree {

    private int[] notTrue = {4, 2, 3, 1};
    private int[] isTrue = {10, 5, 3, 7, 15, 13, 16};

    @Test
    public void testSolutions() {
      Assert.assertTrue(RECURSIVE_SOLUTION.solve(isTrue));
      Assert.assertTrue(ITERATIVE_SOLUTION.solve(isTrue));
      Assert.assertFalse(RECURSIVE_SOLUTION.solve(notTrue));
      Assert.assertFalse(ITERATIVE_SOLUTION.solve(notTrue));
    }

  }

}
