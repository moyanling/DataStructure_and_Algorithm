package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * 
 * 
 * For example:
 * Given binary tree,
 * 
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * 
 * 
 * 
 * return 4.
 *  *
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/count-univalue-subtrees/">Count Univalue Subtrees</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CountUnivalueSubtrees {

  SOLUTION {

    int count;

    @Override
    public int solve(TreeNode root) {
      count = 0;
      if (root == null) return 0;
      recur(root);
      return count;
    }


    private boolean recur(TreeNode root) {
      boolean result = true;
      if (root.left != null) {
        result &= root.left.val == root.val;
        result &= recur(root.left);
      }
      if (root.right != null) {
        result &= root.right.val == root.val;
        result &= recur(root.right);
      }
      if (result) count++;
      return result;
    }

  };

  public abstract int solve(TreeNode root);

  public static class TestCountUnivalueSubtrees {

    private TreeNode root = new TreeNode(5);
    private int expected = 4;

    {
      root.left = new TreeNode(1);
      root.right = new TreeNode(5);
      root.left.left = new TreeNode(5);
      root.left.right = new TreeNode(5);
      root.right.right = new TreeNode(5);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(root));
    }


  }

}
