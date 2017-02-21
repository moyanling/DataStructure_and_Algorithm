package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called the "root." Besides the root, each house
 * has one and only one parent house. After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree". It will automatically contact
 * the police if two directly-linked houses were broken into on the same night.
 * 
 * 
 * 
 * Determine the maximum amount of money the thief can rob tonight without alerting
 * the police.
 * 
 * 
 * Example 1:
 * 
 *      3
 *     / \
 *    2   3
 *     \   \ 
 *      3   1
 * 
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * 
 * 
 * Example 2:
 * 
 *      3
 *     / \
 *    4   5
 *   / \   \ 
 *  1   3   1
 * 
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/house-robber-iii/">House Robber III</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum HouseRobberIII {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(TreeNode root) {
      if (root == null) return 0;
      int parent = root.val//
          + (root.left == null ? 0 : solve(root.left.left) + solve(root.left.right))
          + (root.right == null ? 0 : solve(root.right.left) + solve(root.right.right));
      int child = solve(root.left) + solve(root.right);
      return Math.max(parent, child);
    }

  },

  TOP_DOWN_WITH_MEMO {

    Map<TreeNode, Integer> memo;

    @Override
    public int solve(TreeNode root) {
      memo = new HashMap<>();
      return recur(root);
    }

    private int recur(TreeNode root) {
      if (root == null) return 0;
      int parent = root.val//
          + (root.left == null ? 0 : tryMemo(root.left.left) + tryMemo(root.left.right))
          + (root.right == null ? 0 : tryMemo(root.right.left) + tryMemo(root.right.right));
      int child = tryMemo(root.left) + tryMemo(root.right);
      return Math.max(parent, child);
    }

    private int tryMemo(TreeNode node) {
      if (memo.containsKey(node)) return memo.get(node);
      int toRet = recur(node);
      memo.put(node, toRet);
      return toRet;
    }

  },

  SOLUTION {

    @Override
    public int solve(TreeNode root) {
      int[] res = recur(root);
      return Math.max(res[0], res[1]);
    }

    private int[] recur(TreeNode root) {
      if (root == null) return new int[2];

      int[] left = recur(root.left);
      int[] right = recur(root.right);
      int[] res = new int[2];

      res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
      res[1] = root.val + left[0] + right[0];

      return res;
    }

  };



  public abstract int solve(TreeNode root);

  public static class TestHouseRobberIII {

    private TreeNode root = new TreeNode(0);
    private int expected = 200;

    @Before
    public void before() {
      root.left = new TreeNode(1);
      root.right = new TreeNode(100);
      root.left.left = new TreeNode(100);
      root.right.left = new TreeNode(2);
      root.right.right = new TreeNode(3);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(root));
      Assert.assertEquals(expected, TOP_DOWN_WITH_MEMO.solve(root));
      Assert.assertEquals(expected, SOLUTION.solve(root));
    }

  }

}
