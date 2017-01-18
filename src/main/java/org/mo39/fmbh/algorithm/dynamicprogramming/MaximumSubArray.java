package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.INTRODUCTION_TO_ALGORITHM;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Find the contiguous subnumsay within a one-dimensional numsay of numbers which has the largest
 * sum. Return a 3 length nums with first two as indexes and the third element as the maximum sum.
 *
 * @author Jihan Chen
 *
 */
@ProblemSource({INTRODUCTION_TO_ALGORITHM, LEETCODE})
public enum MaximumSubArray {

  /**
   * This is a way to solve maximum sub numsay using brute force. It's converted to problem
   * "Best time to buy and sell stock". This problem is cited as below
   * <p>
   * Given an int nums presenting the price of a stock. Find a day to buy the stock and a day to
   * sell the stock so that one can earn most money (the difference is the largest).
   * <p>
   * This problem can be converted to MaximumSubArray:<br>
   * Consider the difference between each day, then find the MaximumSubArray of these differences.
   * <p>
   * Take the combination of two elements in nums and take the largest diff. <br>
   * The time complexity is <b>O(n^2)</b>
   *
   * @author Jihan Chen
   *
   */
  BRUTE_FORCE() {

    private Integer largestDiff;
    private int result;

    @Override
    public int solve(int[] nums) {
      result = Integer.MIN_VALUE;
      int[] newArr = new int[nums.length];
      for (int i = 0; i < nums.length; i++) {
        newArr[i] = (i == 0 ? 0 : newArr[i - 1]) + nums[i];
      }
      for (int i = 0; i < newArr.length - 1; i++) {
        for (int j = i + 1; j < newArr.length; j++) {
          if (largestDiff == null || newArr[j] - newArr[i] > largestDiff) {
            largestDiff = newArr[j] - newArr[i];
            result = largestDiff;
          }
        }
      }
      return result;
    }

  },

  /**
   * Divide and conquer.<br>
   * There are three sub cases for this problem after division. The maximum sum is at the left part
   * of division. The maximum sum is at the right part of division. Or the maximum sum goes across
   * the mid. The first two cases can be solved using recursion. And the third case needs a helper
   * function.
   * <p>
   * It takes log(n) to split and finish each recursion. And in each recursion, it takes O(n) to
   * find the maximum sum across mid. So the overall time complexity is <b>nlog(n)</b>.
   *
   * @param stockDiff
   * @param low
   * @param high
   * @return
   */
  DIVIDE_AND_CONQUER() {

    @Override
    public int solve(int[] nums) {
      return findMaximumSubArr(nums, 0, nums.length - 1);
    }

    private int findMaximumSubArr(int[] nums, int low, int high) {
      if (low == high) return nums[low];
      int mid = (low + high) / 2;
      int cross = findMaximumSubArrCrossMid(nums, low, mid, high);
      int left = findMaximumSubArr(nums, low, mid);
      int right = findMaximumSubArr(nums, mid + 1, high);
      if (cross >= left && cross >= right) return cross;
      else if (left >= cross && left >= right) return left;
      return right;
    }

    /**
     * Helper function that calculates the maximum sum that goes across the mid. The time complexity
     * for this process is <b>O(n)</b>.
     *
     * @param stockDiff
     * @param low
     * @param mid
     * @param high
     * @return
     */
    private int findMaximumSubArrCrossMid(int[] nums, int low, int mid, int high) {
      int currLeftSum = 0;
      Integer leftSum = null;
      for (int i = mid; i >= low; i--) {
        currLeftSum += nums[i];
        if (leftSum == null || leftSum < currLeftSum) {
          leftSum = currLeftSum;
        }
      }
      int currRightSum = 0;
      Integer rightSum = null;
      for (int i = mid + 1; i <= high; i++) {
        currRightSum += nums[i];
        if (rightSum == null || rightSum < currRightSum) {
          rightSum = currRightSum;
        }
      }
      return leftSum + rightSum;
    }

  },

  /**
   * Think it generously.
   */
  BOTTOM_UP_METHOD_0 {

    @Override
    public int solve(int[] nums) {
      int max = Integer.MIN_VALUE, used = 0, notUsed = 0;
      for (int n : nums) {
        used = Math.max(used + n, n);
        notUsed = n;
        max = Math.max(max, used);
        max = Math.max(max, notUsed);
      }
      return max;
    }

  },

  /**
   * Implement it greedily, only 1 line is saved though.
   */
  BOTTOM_UP_METHOD_1() {

    @Override
    public int solve(int[] nums) {
      int sum = nums[0], max = nums[0];
      for (int i = 1; i < nums.length; i++) {
        if (sum < 0) sum = nums[i];
        else sum = sum + nums[i];
        max = Math.max(max, sum);
      }
      return max;
    }

  };

  /**
   * Method declaration shared by all instances.
   *
   * @param stock
   * @return
   */
  public abstract int solve(int[] nums);

  /**
   * Helper class used to test solutions.
   *
   * @author Jihan Chen
   *
   */
  public static class TestMaximumSubArray {

    private int[] nums = new TestData().intArr;
    private int solution = 43;

    @Test
    public void testSolutions() {
      Assert.assertEquals(solution, BRUTE_FORCE.solve(nums));
      Assert.assertEquals(solution, DIVIDE_AND_CONQUER.solve(nums));
      Assert.assertEquals(solution, BOTTOM_UP_METHOD_1.solve(nums));
    }

  }
}
