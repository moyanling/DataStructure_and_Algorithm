package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root.
 * 
 * 
 * 
 * Example:
 * Given a binary tree 
 * 
 *           1
 *          / \
 *         2   3
 *        / \     
 *       4   5    
 * 
 * 
 * 
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * 
 * Note:
 * The length of path between two nodes is represented by the number of edges
 * between them.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/diameter-of-binary-tree/">Diameter Of Binary Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DiameterOfBinaryTree {

  SOLUTION {

    int max = 0;

    @Override
    public int solve(TreeNode root) {
      recur(root);
      return max;
    }

    int recur(TreeNode root) {
      if (root == null)
        return 0;
      int left = recur(root.left);
      int right = recur(root.right);
      max = Math.max(max, left + right);
      return 1 + Math.max(left, right);
    }

  };

  public abstract int solve(TreeNode root);

  public static class TestDiameterOfBinaryTree {

    TreeNode root = new TestData().root;

    @Test
    public void testSolutions() {
      Assert.assertEquals(6, SOLUTION.solve(root));
    }
  }

}
