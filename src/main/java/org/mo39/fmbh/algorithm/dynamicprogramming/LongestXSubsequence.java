package org.mo39.fmbh.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Z;

/**
 *
 * @author Jihan Chen
 *
 */
public enum LongestXSubsequence {

  SOLUTION {

    @Override
    public <T> List<T> solve(T[] arr, BiPredicate<T, T> p) {
      List<LinkedList<T>> memo = new ArrayList<>();
      for (T t : arr) {
        memo.add(new LinkedList<>(Arrays.asList(t)));
      }
      int max = -1;
      for (int i = 1; i < arr.length; i++) {
        for (int j = 0; j < i; j++) {
          T last = memo.get(j).getLast();
          if (p.test(last, arr[i])) {
            memo.get(j).add(arr[i]);
            if (max == -1 || memo.get(j).size() > memo.get(max).size()) max = j;
          }
        }
      }
      Z.print(memo);
      return memo.get(max);
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

    @Test
    public void testLongestIncreasingSubsequence() {
      for (LongestXSubsequence sol : LongestXSubsequence.values()) {
        Assert.assertEquals(lis.expected, sol.solve(lis.arr, lis.p));
      }
    }

  }

}
