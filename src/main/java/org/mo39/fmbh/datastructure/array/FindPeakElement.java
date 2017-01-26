package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should
 * return the index number 2.
 * 
 * click to show spoilers.
 * 
 * Note:
 * Your solution should be in logarithmic complexity.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-peak-element/">Find Peak Element</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindPeakElement {

  BAD_SOLUTION {

    @Override
    public int solve(int[] nums) {
      for (int i = 0; i < nums.length; i++) {
        if (get(nums, i) >= get(nums, i - 1) && get(nums, i) >= get(nums, i + 1)) return i;
      }
      return 0;
    }

    private int get(int[] nums, int i) {
      if (i < 0 || i >= nums.length) return Integer.MIN_VALUE;
      return nums[i];
    }

  },

  /**
   * Consider that each local maximum is one valid peak. Find one local maximum with binary search.
   * <p>
   * Again, the idea matters.
   */
  BINARY_SEARCH {

    @Override
    public int solve(int[] nums) {
      int lower = 0, upper = nums.length - 1;
      while (lower < upper) {
        int mid = (lower + upper) / 2;
        if (nums[mid] < nums[mid + 1]) lower = mid + 1;
        else upper = mid;
      }
      return lower;
    }

  };

  public abstract int solve(int[] nums);

}
