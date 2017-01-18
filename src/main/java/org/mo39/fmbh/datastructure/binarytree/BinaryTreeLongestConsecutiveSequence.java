package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * 
 * 
 * The path refers to any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The longest consecutive path need to be from parent to child
 * (cannot be the reverse).
 * 
 * For example,
 * 
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * 
 * Longest consecutive sequence path is 3-4-5, so return 3. 
 * 
 *    2
 *     \
 *      3
 *     / 
 *    2    
 *   / 
 *  1
 * 
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/">Binary
 *      Tree Longest Consecutive Sequence</a>
 * 
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryTreeLongestConsecutiveSequence {

  SOLUTION_0 {

    int max;

    @Override
    public int solve(TreeNode<Integer> root) {
      if (root == null) return 0;
      max = 1;
      recur(root);
      return max;
    }

    private int recur(TreeNode<Integer> root) {
      if (root.left == null && root.right == null) return 1;
      int left = 1, right = 1;
      if (root.left != null) {
        int leftResult = recur(root.left);
        if (root.left.val == root.val + 1) left = 1 + leftResult;
      }
      if (root.right != null) {
        int rightResult = recur(root.right);
        if (root.right.val == root.val + 1) right = 1 + rightResult;
      }
      int result = Math.max(left, right);
      max = Math.max(max, result);
      return result;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(TreeNode<Integer> root) {
      if (root == null) { return 0; }
      return recur(root, root.val + 1, 1, 1);
    }

    private int recur(TreeNode<Integer> node, int target, int curr, int max) {
      if (node == null) return max;
      if (node.val == target) {
        curr++;
        max = Math.max(max, curr);
      } else curr = 1;
      return Math.max(recur(node.left, node.val + 1, curr, max),
          recur(node.right, node.val + 1, curr, max));
    }

  };

  public abstract int solve(TreeNode<Integer> root);

  public static class TestBinaryTreeLongestConsecutiveSequence {

    private TreeNode<Integer> root = new TreeNode<>(1);
    private int expected = 3;

    {
      root.right = new TreeNode<>(3);
      root.right.right = new TreeNode<>(4);
      root.right.right.right = new TreeNode<>(5);
      root.right.left = new TreeNode<>(2);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(root));
      Assert.assertEquals(expected, SOLUTION_1.solve(root));
    }

  }

}
