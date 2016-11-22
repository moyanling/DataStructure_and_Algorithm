package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

@ProblemSource(LEETCODE)
public enum SumOfLeftLeaves {

  RECURSIVE_SOLUTION {

    private int sum;

    @Override
    public int solve(TreeNode<Integer> root) {
      traverse(root);
      return sum;
    }

    private void traverse(TreeNode<Integer> root) {
      if (root == null) return;
      if (root.left != null && root.left.left == null && root.left.right == null) {
        sum += root.left.val;
        traverse(root.right);
        return;
      }
      traverse(root.left);
      traverse(root.right);
    }

  };

  public abstract int solve(TreeNode<Integer> root);

  public static class TestSumOfLeftLeaves {

    private int expected = 5;
    private TreeNode<Integer> root = new TestData().root;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(root));
    }

  }

}
