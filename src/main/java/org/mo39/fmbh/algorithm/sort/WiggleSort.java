package org.mo39.fmbh.algorithm.sort;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

@ProblemSource(LEETCODE)
public enum WiggleSort {

  /**
   * Time complexity is <b>O(nlogn)</b>.
   * 
   */
  BAD_SOLUTION {

    @Override
    public void solve(int[] nums) {
      if (nums == null || nums.length < 2) return;
      Arrays.sort(nums);
      int end = nums.length % 2 == 1 ? nums.length : nums.length + 1;
      for (int i = 1; i < end / 2; i += 2) {
        Z.swap(nums, i, end - i - 1);
      }
      int pre = nums[1] - nums[0];
      for (int i = 2; i < nums.length; i++) {
        if ((pre * (nums[i] - nums[i - 1])) >= 0) {
          Z.swap(nums, i, i - 1);
        }
        pre = nums[i] - nums[i - 1];
      }
    }


  },

  BAD_IMPLEMENTATION {

    @Override
    public void solve(int[] nums) {
      if (nums == null || nums.length < 2) return;
      if (nums[1] < nums[0]) Z.swap(nums, 0, 1);
      Sign expected = Sign.GT;
      for (int i = 2; i < nums.length; i++) {
        Sign actual = Sign.signOf(nums[i - 1], nums[i]);
        if (actual != Sign.ET && expected != actual) {
          Z.swap(nums, i, i - 1);
        }
        expected = expected == Sign.GT ? Sign.LT : Sign.GT;
      }

    }

  },

  LINEAR_SOLUTION {

    @Override
    public void solve(int[] nums) {
      for (int i = 1; i < nums.length; i++) {
        if (i % 2 == 1 ? nums[i] <= nums[i - 1] : nums[i] >= nums[i - 1]) Z.swap(nums, i, i - 1);
      }
    }

  };

  private static enum Sign {

    GT, LT, ET;

    public static Sign signOf(int a, int b) {
      if (a < b) return LT;
      if (a > b) return GT;
      else return ET;
    }

  }



  public abstract void solve(int[] nums);



  public static class TestWiggleSort {

    private int[] nums = {3, 5, 2, 1, 6, 4};

    private void verify(int[] nums) {
      for (int i = 1; i < nums.length; i++) {
        Assert.assertTrue(i % 2 == 1 ? nums[i] >= nums[i - 1] : nums[i] <= nums[i - 1]);
      }
    }

    @Test
    public void testSolutions() {
      BAD_SOLUTION.solve(nums);
      verify(nums);
    }

    @Test
    public void testBadImplementation() {
      BAD_IMPLEMENTATION.solve(nums);
      verify(nums);
    }

    @Test
    public void testLinearSolution() {
      LINEAR_SOLUTION.solve(nums);
      verify(nums);
    }

  }

}
