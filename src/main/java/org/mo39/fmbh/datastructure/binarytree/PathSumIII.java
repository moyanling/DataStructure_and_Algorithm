package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards
 * (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000
 * to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/path-sum-iii/">Path Sum III</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PathSumIII {

  SOLUTION {

    private int count = 0;

    @Override
    public int solve(TreeNode<Integer> root, int sum) {
      recur(root, sum, new ArrayList<>());
      return count;
    }

    private void recur(TreeNode<Integer> root, int target, List<Integer> path) {
      List<Integer> right = new ArrayList<>();
      List<Integer> left = new ArrayList<>();
      left.addAll(path);
      if (left.size() != 0) left.add(root.val);
      else left.add(root.val);
      right.addAll(left);
      if (root.left != null) recur(root.left, target, left);
      if (root.right != null) recur(root.right, target, right);
      if (root.left == null && root.right == null) {
      }
    }

  };

  public abstract int solve(TreeNode<Integer> root, int sum);

  public static class TestPathSumIII {

    TreeNode<Integer> root = new TreeNode<>(10);
    TreeNode<Integer> node1 = new TreeNode<>(5);
    TreeNode<Integer> node2 = new TreeNode<>(-3);
    TreeNode<Integer> node3 = new TreeNode<>(3);
    TreeNode<Integer> node4 = new TreeNode<>(2);
    TreeNode<Integer> node5 = new TreeNode<>(11);
    TreeNode<Integer> node6 = new TreeNode<>(3);
    TreeNode<Integer> node7 = new TreeNode<>(-2);
    TreeNode<Integer> node8 = new TreeNode<>(1);

    private int expected = 3;

    @Before
    public void before() {
      root.left = node1;
      root.right = node2;
      node1.left = node3;
      node1.right = node4;
      node2.right = node5;
      node3.left = node6;
      node3.right = node7;
      node4.right = node8;
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(root, 8));
    }

  }

}
