package org.mo39.fmbh.datastructure.array;

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
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">Find Minimum
 *      In Rotated Sorted Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindMinimumInRotatedSortedArray {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int i = 0;
      while (nums[i] > nums[i = getPrevious(nums, i)]) {
      }
      return nums[i + 1 >= nums.length ? 0 : i + 1];
    }

    private int getPrevious(int[] nums, int i) {
      if (i == 0) return nums.length - 1;
      return i - 1;
    }

  },

  BAD_TRY_AND_ERROR_SOLUTION {

    @Override
    public int solve(int[] nums) {
      int lower = 0, upper = nums.length - 1;
      while (lower <= upper) {
        int mid = lower + upper >>> 1;
        if (lower == mid) return Math.min(nums[lower], nums[upper]);
        if (nums[lower] <= nums[mid] && nums[mid + 1] <= nums[upper])
          return Math.min(nums[lower], nums[mid + 1]);
        if (nums[lower] < nums[mid]) lower = mid + 1;
        else if (nums[mid] < nums[upper]) upper = mid;
        else return nums[mid];
      }
      return nums[lower];
    }

  },

  /**
   * </br>
   * loop invariant: </br>
   * 1. low &lt high </br>
   * 2. mid != high and thus A[mid] != A[high] (no duplicate exists) </br>
   * 3. minimum is between [low, high] </br>
   * The proof that the loop will exit: after each iteration either the 'high' decreases or the
   * 'low' increases, so the interval [low, high] will always shrink.
   */
  BINARY_SEARCH_SOLUTION {

    @Override
    public int solve(int[] nums) {
      int low = 0, high = nums.length - 1;
      while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] < nums[high]) high = mid;
        else if (nums[mid] > nums[high]) low = mid + 1;
      }
      return nums[low];
    }

  };

  public abstract int solve(int[] nums);

  public static class TestFindMinimumInRotatedSortedArray {

    private int[] nums = {4, 5, 6, 7, 0, 1, 2};
    private int expected = 0;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
      Assert.assertEquals(expected, BAD_TRY_AND_ERROR_SOLUTION.solve(nums));
      Assert.assertEquals(expected, BINARY_SEARCH_SOLUTION.solve(nums));
    }

  }

}
