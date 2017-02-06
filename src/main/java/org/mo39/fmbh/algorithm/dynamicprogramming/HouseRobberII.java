package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/house-robber/">House Robber</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum HouseRobberII {

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int[] nums) {
      // TODO
      int prevNo = 0, prevYes = 0;
      for (int i = 0; i < nums.length - 1; i++) {
        int temp = prevNo;
        prevNo = Math.max(prevNo, prevYes);
        prevYes = nums[i] + temp;
      }
      return Math.max(prevNo, prevYes);
    }

  };

  public abstract int solve(int[] nums);

}
