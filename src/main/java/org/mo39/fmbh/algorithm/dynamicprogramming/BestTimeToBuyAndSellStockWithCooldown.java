package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many transactions
 * as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following
 * restrictions:
 * 
 * 
 *     You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 *     After you sell your stock, you cannot buy stock on next day. (ie, cooldown
 * 1 day)
 * 
 * 
 * Example:
 * 
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">Best
 *      Time To Buy And Sell Stock With Cooldown</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BestTimeToBuyAndSellStockWithCooldown {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[] prices) {
      if (prices.length < 2) return 0;
      int oneTransaction = prices[prices.length - 1] - prices[0];
      int multipleTransaction = recur(prices, 0, prices.length - 1);
      return Math.max(oneTransaction, multipleTransaction);
    }

    private int recur(int[] prices, int start, int end) {
      if (start >= end) return 0;// Transaction cannot happen;
      int maxProfit = Integer.MIN_VALUE;
      for (int i = start; i <= end; i++) {
        int leftProfit = recur(prices, start, i - 1);
        if (i - 1 > start) leftProfit = Math.max(leftProfit, prices[i - 1] - prices[start]);
        int rightProfit = recur(prices, i + 1, end);
        if (end > i + 1) rightProfit = Math.max(rightProfit, prices[end] - prices[i + 1]);
        maxProfit = Math.max(maxProfit, leftProfit + rightProfit);
      }
      return maxProfit;
    }

  },

  TOP_DOWN_WITH_MEMO {

    private int[][] memo;

    @Override
    public int solve(int[] prices) {
      if (prices.length < 2) return 0;
      memo = new int[prices.length][prices.length];
      int oneTransaction = prices[prices.length - 1] - prices[0];
      int multipleTransaction = recur(prices, 0, prices.length - 1);
      return Math.max(oneTransaction, multipleTransaction);
    }

    private int recur(int[] prices, int start, int end) {
      if (start >= end) return 0;// Transaction cannot happen;
      if (memo[start][end] != 0) return memo[start][end];
      int maxProfit = Integer.MIN_VALUE;
      for (int i = start; i <= end; i++) {
        int leftProfit = recur(prices, start, i - 1);
        if (i - 1 > start) leftProfit = Math.max(leftProfit, prices[i - 1] - prices[start]);
        int rightProfit = recur(prices, i + 1, end);
        if (end > i + 1) rightProfit = Math.max(rightProfit, prices[end] - prices[i + 1]);
        maxProfit = Math.max(maxProfit, leftProfit + rightProfit);
      }
      memo[start][end] = maxProfit;
      return maxProfit;
    }

  },

  /**
   * //TODO
   */
  BOTTOM_UP_METHOD {


    @Override
    public int solve(int[] prices) {
      int sell = 0, doNothing = 0;
      for (int i = 1; i < prices.length; i++) {
        int copy = sell;
        sell = Math.max(sell + prices[i] - prices[i - 1], doNothing);
        doNothing = Math.max(copy, doNothing);
      }
      return Math.max(sell, doNothing);
    }

  };

  public abstract int solve(int[] prices);

  public static class TestBestTimeToBuyAndSellStockWithCooldown {

    private int[] prices = {1, 2, 3, 0, 2};
    private int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(prices));
      Assert.assertEquals(expected, TOP_DOWN_WITH_MEMO.solve(prices));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(prices));
    }

  }

}
