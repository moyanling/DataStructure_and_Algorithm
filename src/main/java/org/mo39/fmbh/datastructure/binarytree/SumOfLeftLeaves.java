package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-left-leaves/">Sum Of Left Leaves</a>
 * @author Jihan Chen
 */
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
