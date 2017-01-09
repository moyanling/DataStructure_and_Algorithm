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
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum profit.
 * 
 * Example 1:
 * 
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * 
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger
 * than buying price)
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * 
 * In this case, no transaction is done, i.e. max profit = 0.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">Best Time To Buy
 *      And Sell Stock</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BestTimeToBuyAndSellStock {

  SOLUTION {

    @Override
    public int solve(int[] prices) {
      int minPrice = Integer.MAX_VALUE, maxProfit = 0;
      for (int i = 0; i < prices.length; i++) {
        minPrice = Math.min(minPrice, prices[i]);
        maxProfit = Math.max(maxProfit, prices[i] - minPrice);
      }
      return maxProfit;
    }

  };

  public abstract int solve(int[] prices);

  public static class TestBestTimeToBuyAndSellStock {

    private int[] prices = {7, 1, 5, 3, 6, 4};
    private int expected = 5;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(prices));
    }


  }

}
