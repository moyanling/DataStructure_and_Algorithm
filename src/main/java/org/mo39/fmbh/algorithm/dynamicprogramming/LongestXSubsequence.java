package org.mo39.fmbh.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jihan Chen
 *
 */
public enum LongestXSubsequence {

  /**
   * //TODO urgent, dp
   */
  SOLUTION {

    @Override
    public <T> List<T> solve(T[] nums, BiPredicate<T, T> p) {
      int[] count = new int[nums.length], pre = new int[nums.length];
      int max = 0, index = -1;
      for (int i = 0; i < nums.length; i++) {
        count[i] = 1;
        pre[i] = -1;
        for (int j = i - 1; j >= 0; j--) {
          if (p.test(nums[j], nums[i]) && 1 + count[j] > count[i]) {
            count[i] = count[j] + 1;
            pre[i] = j;
          }
        }
        if (count[i] > max) {
          max = count[i];
          index = i;
        }
      }
      LinkedList<T> result = new LinkedList<>();
      for (; index != -1; result.add(0, nums[index]), index = pre[index]) {
      }
      return result;
    }

  };

  public abstract <T> List<T> solve(T[] arr, BiPredicate<T, T> p);

  public static class TestLongestXSubsequence {

    static class TestSuite<T> {
      T[] arr;
      List<T> expected;
      BiPredicate<T, T> p;
    }

    /**
     * Longest increasing subsequence.
     */
    TestSuite<Integer> lis;

    {
      lis = new TestSuite<>();
      lis.arr = new Integer[] {4, 8, 1, 9, 2, 5, 7};
      lis.expected = new ArrayList<>(Arrays.asList(1, 2, 5, 7));
      lis.p = (i1, i2) -> i1 - i2 <= 0;
    }

    /**
     * Longest divisible subsequence.
     */
    TestSuite<Integer> lds;

    {
      lds = new TestSuite<>();
      lds.arr = new Integer[] {9, 54, 108, 90, 180, 1, 360, 4, 2, 720};
      lds.expected = new ArrayList<>(Arrays.asList(9, 90, 180, 360, 720));
      lds.p = (i1, i2) -> i2 % i1 == 0;
    }

    @Test
    public void testLongestIncreasingSubsequence() {
      Assert.assertEquals(lis.expected, SOLUTION.solve(lis.arr, lis.p));
    }

    @Test
    public void testLongestDivisibleSubsequence() {
      Assert.assertEquals(lds.expected, SOLUTION.solve(lds.arr, lds.p));
    }

  }

}
