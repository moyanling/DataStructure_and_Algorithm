package org.mo39.fmbh.algorithm.divideandconquer;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.S;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">Kth Largest Element In An Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum KthLargestElementInAnArray {

  SORT_SOLUTION {

    @Override
    public int solve(int[] nums, int k) {
      Arrays.sort(nums);
      return nums[nums.length - k];
    }

  },

  HEAP_SOLUTION {

    @Override
    public int solve(int[] nums, int k) {
      PriorityQueue<Integer> heap = new PriorityQueue<>();
      for (int n : nums) {
        heap.add(n);
        if (heap.size() > k) heap.poll();
      }
      return heap.poll();
    }

  },

  DIVID_AND_CONQUER {

    @Override
    public int solve(int[] nums, int k) {
      int start = 0, end = nums.length - 1;
      while (start < end) {
        int q = S.randomPartition(nums, start, end);
        if (end - q == k - 1) return nums[q];
        if (k - 1 < end - q) start = q + 1;
        else {
          k = k - (end - q + 1);
          end = q - 1;
        }
      }
      return nums[start];
    }

  };

  public abstract int solve(int[] nums, int k);

  public static class TestKthLargestElementInAnArray {

    private int[] nums = {2, 4, 3, 1, 9, 7, 5};
    private int k = 2;
    private int expected = 7;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SORT_SOLUTION.solve(nums, k));
      Assert.assertEquals(expected, HEAP_SOLUTION.solve(nums, k));
      Assert.assertEquals(expected, DIVID_AND_CONQUER.solve(nums, k));
    }

  }

}