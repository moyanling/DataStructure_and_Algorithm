package org.mo39.fmbh.algorithm.binarysearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array of integers sorted in ascending order, find the starting and
 * ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/search-for-a-range/">Search For A Range</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SearchForARange {

  SOLUTION {

    @Override
    public int[] solve(int[] nums, int target) {
      int start = search(nums, target - 0.5);
      int end = search(nums, target + 0.5) - 1;
      return start > end ? new int[] {-1, -1} : new int[] {start, end};
    }

    int search(int[] nums, double n) {
      int start = 0, end = nums.length - 1;
      while (start <= end) {
        int mid = start + end >>> 1;
        if (nums[mid] < n) start = mid + 1;
        else end = mid - 1;
      }
      return start;
    }

  };

  public abstract int[] solve(int[] nums, int target);

  public static class TestSearchForARange {

    int[] nums = {5, 7, 7, 8, 8, 10};
    int target = 5;
    int[] expecteds = {0, 0};

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expecteds, SOLUTION.solve(nums, target));
    }

  }

}
