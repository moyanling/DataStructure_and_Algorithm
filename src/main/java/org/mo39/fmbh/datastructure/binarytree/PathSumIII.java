package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

  /**
   * This one is so bad actually. Almost TLE.
   */
  SOLUTION {

    private int count = 0;

    @Override
    public int solve(TreeNode root, int sum) {
      count = 0;
      recur(root, sum, new LinkedList<>());
      return count;
    }

    void recur(TreeNode root, int target, LinkedList<Integer> path) {
      if (root == null) return;
      path.add(root.val);
      count(path, target);
      recur(root.left, target, path);
      recur(root.right, target, path);
      path.removeLast();
    }

    void count(LinkedList<Integer> path, int target) {
      if (path.getLast() == target) count++;
      if (path.size() < 2) return;
      path.set(path.size() - 1, path.get(path.size() - 2) + path.getLast());
      if (path.getLast() == target) count++;
      for (int i = 0; i < path.size() - 1; i++) {
        if (i < path.size() - 2 && path.getLast() - path.get(i) == target) count++;
      }
    }

  },

  SOLUTION_1 {

    int count = 0;

    @Override
    public int solve(TreeNode root, int target) {
      count = 0;
      Map<Integer, Integer> path = new HashMap<>();
      recur(root, 0, target, path);
      return count;
    }

    void recur(TreeNode root, int pre, int target, Map<Integer, Integer> path) {
      if (root == null) return;
      int newSum = pre + root.val;
      if (path.containsKey(newSum - target)) count++;
      if (newSum == target) count++;
      if (root.val == target) count++;
      path.put(newSum, path.getOrDefault(newSum, 0) + 1);
      recur(root.left, newSum, target, path);
      recur(root.right, newSum, target, path);
      if (path.get(newSum) == 1) path.remove(newSum);
      else path.put(newSum, path.get(newSum) - 1);
    }



  };

  public abstract int solve(TreeNode root, int sum);

  public static class TestPathSumIII {

    TreeNode root = new TreeNode(5);
    TreeNode node1 = new TreeNode(4);
    TreeNode node2 = new TreeNode(8);
    TreeNode node3 = new TreeNode(11);
    TreeNode node4 = new TreeNode(7);
    TreeNode node5 = new TreeNode(2);
    TreeNode node6 = new TreeNode(13);
    TreeNode node7 = new TreeNode(5);
    TreeNode node8 = new TreeNode(1);
    TreeNode node9 = new TreeNode(4);

    private int expected = 3;

    @Before
    public void before() {
      root.left = node1;
      root.right = node2;
      node1.left = node3;
      node3.left = node4;
      node3.right = node5;
      node2.left = node6;
      node2.right = node9;
      node6.left = node7;
      node6.right = node8;
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(root, 22));
      Assert.assertEquals(expected, SOLUTION_1.solve(root, 22));
      Assert.assertEquals(0, SOLUTION_1.solve(new TreeNode(1), 0));
    }

  }

}
