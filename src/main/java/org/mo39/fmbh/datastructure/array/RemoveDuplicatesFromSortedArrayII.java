package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * 
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * 
 * 
 * Your function should return length = 5, with the first five elements of nums
 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/">Remove
 *      Duplicates From Sorted Array II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RemoveDuplicatesFromSortedArrayII {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      if (nums.length < 3) return nums.length;
      int max = 2, j = 1, count = 1;
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i - 1]) {
          count++;
          if (count <= max) nums[j++] = nums[i];
        } else {
          count = 1;
          nums[j++] = nums[i];
        }
      }
      return j;
    }

  },

  /**
   * A different perspective that use greater than instead of count.
   */
  SOLUTION_1 {

    @Override
    public int solve(int[] nums) {
      int i = 0, max = 2;
      for (int n : nums) {
        if (i < max || n > nums[i - max]) nums[i++] = n;
      }
      return i;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestRemoveDuplicatesFromSortedArrayII {

    int[] expecteds = {1, 1, 2, 2, 3, 3};


    @Test
    public void testSolutions() {
      for (RemoveDuplicatesFromSortedArrayII sol : RemoveDuplicatesFromSortedArrayII.values()) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        sol.solve(nums);
        Assert.assertArrayEquals(expecteds, nums);
      }
    }

  }

}
