package org.mo39.fmbh.algorithm.binarysearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index,
 * otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">Search In Rotated
 *      Sorted Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SearchInRotatedSortedArray {

  SOLUTION {

    @Override
    public int solve(int[] nums, int target) {
      if (nums.length == 0) return -1;
      int start = 0, end = nums.length - 1;
      while (start < end) {
        int mid = start + end >>> 1;
        if (nums[mid] >= nums[end]) start = mid + 1;
        else end = mid;
      }
      end = start + nums.length - 1;
      while (start <= end) {
        int mid = (start + end) / 2, i = mid % nums.length;
        if (nums[i] == target) return i;
        if (nums[i] < target) start = mid + 1;
        else end = mid - 1;
      }
      return -1;
    }

  };

  public abstract int solve(int[] nums, int target);

  public static class TestSearchInRotatedSortedArray {

    int[] nums = {4, 5, 6, 0, 1, 2};
    int target = 0;
    int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums, target));
    }

  }

}
