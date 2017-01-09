package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or
 * green. The cost of painting each house with a certain color is different. You have to paint all
 * the houses such that no two adjacent houses have the same color.
 * 
 * 
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For
 * example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of
 * painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 * 
 * 
 * Note: All costs are positive integers.
 * 
 * </pre>
 * 
 * This is a really typical dynamic programming problem.
 * 
 * @see <a href="https://leetcode.com/problems/paint-house/">Paint House</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PaintHouse {

  RECURSIVE_SOLUTION {


    @Override
    public int solve(int[][] costs) {
      return recur(costs, 0, -1);
    }

    private int recur(int[][] costs, int i, int ban) {
      if (i > costs.length - 1) return 0;
      int min = Integer.MAX_VALUE;
      if (ban != 0) min = Math.min(min, costs[i][0] + recur(costs, i + 1, 0));
      if (ban != 1) min = Math.min(min, costs[i][1] + recur(costs, i + 1, 1));
      if (ban != 2) min = Math.min(min, costs[i][2] + recur(costs, i + 1, 2));
      return min;
    }

  },

  TOP_DOWN_WITH_MEMO {

    int[][] memo;

    @Override
    public int solve(int[][] costs) {
      memo = new int[costs.length][3];
      return recur(costs, 0, -1);
    }

    private int recur(int[][] costs, int i, int ban) {
      if (i > costs.length - 1) return 0;
      int min = Integer.MAX_VALUE;
      if (ban != 0) min = Math.min(min, minOf(costs, i, 0));
      if (ban != 1) min = Math.min(min, minOf(costs, i, 1));
      if (ban != 2) min = Math.min(min, minOf(costs, i, 2));
      return min;
    }

    private int minOf(int[][] costs, int i, int ban) {
      if (memo[i][ban] != 0) return memo[i][ban];
      return memo[i][ban] = costs[i][ban] + recur(costs, i + 1, ban);
    }

  },

  BOTTOM_UP_SOLUTION {

    @Override
    public int solve(int[][] costs) {
      int[] pre = costs[0];
      for (int i = 1; i < costs.length; i++) {
        int[] cur = new int[3];
        cur[0] = Math.min(pre[1], pre[2]) + costs[i][0];
        cur[1] = Math.min(pre[0], pre[2]) + costs[i][1];
        cur[2] = Math.min(pre[0], pre[1]) + costs[i][2];
        pre = cur;
      }
      return Math.min(pre[0], Math.min(pre[1], pre[2]));
    }

  };

  public abstract int solve(int[][] costs);


}
