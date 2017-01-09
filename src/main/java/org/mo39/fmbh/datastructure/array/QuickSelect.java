package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.INTRODUCTION_TO_ALGORITHM;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.algorithm.math.MinimumMovesToEqualArrayElementsII;
import org.mo39.fmbh.common.S;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * The worth case obviously is <b>O(n^2)</b>. But it is hard to understand <b>O(n)</b> for expected
 * cases.
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(INTRODUCTION_TO_ALGORITHM)
public enum QuickSelect {

  /**
   * This should work fine but it has a stack over flow problem at large test cases.
   */
  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[] nums, int k) {
      return kthSmallestOf(nums, k, 0, nums.length - 1);
    }

    /**
     * Inclusive start and end.
     * 
     * @param nums
     * @param k
     * @param start
     * @param end
     * @return
     */
    private int kthSmallestOf(int[] nums, int k, int start, int end) {
      if (start == end) return nums[start];
      int q = S.randomPartition(nums, start, end);
      if (q - start == k - 1) return nums[q];
      if (k - 1 < q - start) return kthSmallestOf(nums, k, start, q - 1);
      return kthSmallestOf(nums, k - q + start - 1, q + 1, end);
    }

  },

  /**
   * A iterative version for above idea. Nothing changed and can pass all test cases in leetcode.
   * 
   * @see MinimumMovesToEqualArrayElementsII
   */
  ITERATIVE_SOLUTION {

    @Override
    public int solve(int[] nums, int k) {
      int start = 0, end = nums.length - 1;
      while (start < end) {
        int q = S.randomPartition(nums, start, end);
        if (q - start == k - 1) return nums[q];
        if (k - 1 < q - start) end = q - 1;
        else {
          k = k - q + start - 1;
          start = q + 1;
        }
      }
      return nums[start];
    }

  };

  public abstract int solve(int[] nums, int k);

  public static class TestQuickSelect {

    private int[] nums = {3, 7, 2, 5, 9};
    private int k = 3;
    private int expected = 5;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums, k));
    }

    @Test
    public void testIterativeSolution() {
      Assert.assertEquals(expected, ITERATIVE_SOLUTION.solve(nums, k));
    }

  }

}
