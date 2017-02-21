package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the
 * nearest leaf node.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">Minimum Depth Of
 *      Binary Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MinimumDepthOfBinaryTree {

  SOLUTION_0 {

    @Override
    public <T> int solve(TreeNode root) {
      if (root == null) return 0;
      int leftDepth = Integer.MAX_VALUE, rightDepth = Integer.MAX_VALUE;
      if (root.left == null && root.right == null) return 1;
      if (root.left != null) leftDepth = solve(root.left);
      if (root.right != null) rightDepth = solve(root.right);
      return Math.min(leftDepth, rightDepth) + 1;
    }

  },

  /**
   * The key idea is to make sure it trace to the leave instead of a null.
   */
  SOLUTION_1 {

    @Override
    public <T> int solve(TreeNode root) {
      if (root == null) return 0;
      int leftDepth = solve(root.left), rightDepth = solve(root.right);
      return 1 + (Math.min(leftDepth, rightDepth) > 0 ? Math.min(leftDepth, rightDepth)
          : Math.max(leftDepth, rightDepth));
    }

  };

  public abstract <T> int solve(TreeNode root);

  public static class TestMinimumDepthOfBinaryTree {

    private TreeNode root = new TreeNode(0);
    private int expected = 2;

    @Before
    public void before() {
      root.left = new TreeNode(1);
      root.left.left = new TreeNode(2);
      root.right = new TreeNode(3);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(root));
      Assert.assertEquals(expected, SOLUTION_1.solve(root));
    }

  }

}
