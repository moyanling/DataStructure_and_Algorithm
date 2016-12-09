package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer array with all positive numbers and no duplicates, find the number of possible
 * combinations that add up to a positive integer target.
 * 
 * Example:
 * 
 * nums = [1, 2, 3] target = 4
 * 
 * The possible combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3,
 * 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * 
 * 
 * Follow up: What if negative numbers are allowed in the given array? How does it change the
 * problem? What limitation we need to add to the question to allow negative numbers?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum-iv/">Combination Sum IV</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CombinationSumIV {

  /**
   * <pre>
   * Thinking in recursive solution:
   * - If I find a combination of n length, then the number for this cannot be determined
   *   because I don't know how many times one number is used.
   * - Suppose I had a solve function to solve the nums[0, n], then there are two cases:
   *    1. The last one is not contributing to the total count.
   *       e.g. it is larger than target. Then simply solve nums[0, n-1]. But how to tell if it's contributing.
   *    2.
   * </pre>
   */
  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[] nums, int target) {

      return 0;
    }

  };

  public abstract int solve(int[] nums, int target);

}