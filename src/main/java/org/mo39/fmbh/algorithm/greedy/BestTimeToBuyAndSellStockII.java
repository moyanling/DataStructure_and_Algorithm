package org.mo39.fmbh.algorithm.greedy;

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
 * as you like (ie, buy one and sell one share of the stock multiple times). However,
 * you may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">Best Time To Buy
 *      And Sell Stock II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BestTimeToBuyAndSellStockII {

  SOLUTION_0 {

    @Override
    public int solve(int[] prices) {
      if (prices.length == 0) return 0;
      int min = prices[0], max = prices[0], profit = 0;
      for (int i = 1; i < prices.length; i++) {
        if (prices[i] < max) {
          profit += max - min;
          min = prices[i];
          max = prices[i];
        } else max = Math.max(max, prices[i]);
      }
      return profit + max - min;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(int[] prices) {
      int profit = 0;
      for (int i = 0; i < prices.length - 1; i++) {
        if (prices[i + 1] > prices[i]) profit += prices[i + 1] - prices[i];
      }

      return profit;
    }

  };

  public abstract int solve(int[] prices);

  public static class TestBestTimeToBuyAndSellStockII {

    private int[] prices = {7, 3, 2, 8, 1, 9};
    private int expected = 14;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(prices));
      Assert.assertEquals(expected, SOLUTION_1.solve(prices));
    }

  }

}
