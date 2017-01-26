package org.mo39.fmbh.algorithm.sort;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red, white
 * and blue.
 * 
 * 
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * 
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * 
 * click to show follow up.
 * 
 * 
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite
 * array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/sort-colors/">Sort Colors</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SortColors {

  SORT_SOLUTION {

    @Override
    public void solve(int[] nums) {
      Arrays.sort(nums);
    }

  },

  COUNTING_SORT_SOLUTION {

    @Override
    public void solve(int[] nums) {
      int[] counts = new int[3];
      for (int n : nums) {
        counts[n]++;
      }
      for (int i = 0; i < nums.length; i++) {
        if (counts[0]-- > 0) nums[i] = 0;
        else if (counts[1]-- > 0) nums[i] = 1;
        else if (counts[2]-- > 0) nums[i] = 2;
      }
    }

  },

  /**
   * The idea is to sweep all 0s to the left and all 2s to the right, then all 1s are left in the
   * middle.
   * <p>
   * Again, the idea matters.
   */
  ARRAY_PARTITION_SOLUTION {

    @Override
    public void solve(int[] nums) {
      if (nums == null || nums.length == 1) return;
      int zeros = 0, twos = 0, length = nums.length;
      for (int i = 0; i < length - twos; i++) {
        if (nums[i] == 2) {
          nums[i--] = nums[length - 1 - twos];
          nums[length - 1 - twos++] = 2;
        } else if (nums[i] == 0) {
          nums[i] = nums[zeros];
          nums[zeros++] = 0;
        }
      }
      return;
    }

  };

  public abstract void solve(int[] nums);

  public static class TestSortColors {

    private int[] expecteds = {0, 1, 2};

    @Test
    public void testSolutions() {
      for (SortColors sol : SortColors.values()) {
        int[] nums = new int[] {2, 1, 0};
        sol.solve(nums);
        Assert.assertArrayEquals(expecteds, nums);
      }
    }
  }

}
