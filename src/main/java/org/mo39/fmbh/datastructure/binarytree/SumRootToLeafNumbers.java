package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * 
 * Find the total sum of all root-to-leaf numbers.
 * 
 * For example,
 * 
 *     1
 *    / \
 *   2   3
 * 
 * 
 * 
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * 
 * 
 * Return the sum = 12 + 13 = 25.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/sum-root-to-leaf-numbers/">Sum Root To Leaf
 *      Numbers</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SumRootToLeafNumbers {

  SOLUTION_0 {

    @Override
    public int solve(TreeNode root) {
      recur(root, 0);
      return sum;
    }

    int sum = 0;

    void recur(TreeNode root, int pre) {
      if (root == null) return;
      int cur = pre * 10 + root.val;
      if (root.left == null && root.right == null) sum += cur;
      recur(root.left, cur);
      recur(root.right, cur);
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(TreeNode root) {
      return recur(root, 0);
    }


    int recur(TreeNode root, int pre) {
      if (root == null) return 0;
      int cur = pre * 10 + root.val;
      if (root.left == null && root.right == null) return cur;
      return recur(root.left, cur) + recur(root.right, cur);
    }

  };

  public abstract int solve(TreeNode root);

  public static class TestSumRootToLeafNumbers {

    TreeNode root = new TreeNode(1);
    int expected = 25;

    {
      root.left = new TreeNode(2);
      root.right = new TreeNode(3);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(root));
      Assert.assertEquals(expected, SOLUTION_1.solve(root));
    }


  }

}
