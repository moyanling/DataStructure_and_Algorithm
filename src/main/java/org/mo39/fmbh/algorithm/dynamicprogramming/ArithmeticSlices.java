package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

@ProblemSource(LEETCODE)
public enum ArithmeticSlices {

  BRUTE_FORCE {

    @Override
    public int solve(int[] nums) {
      List<Integer> l = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        l.add(i);
      }
      int count = 0, length = 2;
      while (l.size() != 0) {
        final int len = length;
        l = l.stream()
            .filter(o -> (o + len) < nums.length
                && nums[o + len] - nums[o + len - 1] == nums[o + 1] - nums[o])
            .collect(Collectors.toList());
        count += l.size();
        length++;
      }
      return count;
    }

  },

  /**
   * This is an alternative not using add, which might be easier to understand in some aspect.
   * 
   */
  BOTTOM_UP_METHOD_0 {

    @Override
    public int solve(int[] nums) {
      int count = 0;
      Integer start = null;
      for (int i = 2; i < nums.length; i++) {
        if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
          if (start == null) start = i;
        } else if (start != null) {
          count += (i - start + 1) * (i - start) / 2;
          start = null;
        }
      }
      if (start != null) count += (nums.length - start + 1) * (nums.length - start) / 2;
      return count;
    }

  },

  /**
   * The key points are:<br/>
   * 1. If a sequence is an Arithmetic, then all its subsequences that have a length larger than 3
   * are Arithmetic.<br/>
   * 2. The number of all these kind of subsequences for an Arithmetic are 1+2+3+...+(n-3) where n
   * is the length of this Arithmetic.
   * 
   */
  BOTTOM_UP_METHOD_1 {

    @Override
    public int solve(int[] nums) {
      int count = 0, total = 0;
      for (int i = 2; i < nums.length; i++) {
        if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) total += ++count;
        else count = 0;
      }
      return total;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestnumsrithmeticSlices {

    private int[] nums = {1, 2, 3, 8, 9, 10, 11, 12};
    private int expected = 7;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(nums));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD_0.solve(nums));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD_1.solve(nums));
    }

  }

}
