package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * 
 * 
 * For example,
 * Given input array nums = [1,1,2],
 * 
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively. It doesn't matter what you leave beyond the new
 * length.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/">Remove
 *      Duplicates From Sorted Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RemoveDuplicatesFromSortedArray {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      if (nums.length < 2) return nums.length;
      int j = 1;
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] != nums[i - 1]) nums[j++] = nums[i];
      }
      return j;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestRemoveDuplicatesFromSortedArray {

    int[] nums = {1, 1, 2, 2, 2, 4};
    int[] expecteds = {1, 2, 4, 2, 2, 4};

    @Test
    public void testSolutions() {
      SOLUTION.solve(nums);
      Assert.assertArrayEquals(expecteds, nums);
    }
  }

}
