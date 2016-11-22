package org.mo39.fmbh.datastructure.array;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Separate the array into two parts which all the elements in the first part satisfy a
 * <code>Predicate</code> while the second part does not.
 * 
 * @author Jihan Chen
 *
 */
public enum ArrayPartition {

  LINEAR_SOLUTION() {

    @Override
    public <T> int solve(T[] arr, Predicate<T> predicate, int start, int end) {
      int j = start - 1;
      for (int i = start; i < end; i++) {
        if (predicate.test(arr[i])) Z.swap(arr, i, ++j);
      }
      return j;
    }

  };

  public <T> int solve(T[] arr, Predicate<T> predicate) {
    return solve(arr, predicate, 0, arr.length);
  };

  /**
   * Inclusive start and exclusive end.
   * 
   * @param arr
   * @param predicate
   * @param start
   * @param end
   * @return
   */
  public abstract <T> int solve(T[] arr, Predicate<T> predicate, int start, int end);

  public static class TestArrayPartition {

    private Integer[] arr = new TestData().integerArr;

    private <T> void verify(T[] arr, Predicate<T> predicate) {
      int i = -1;
      while (predicate.test(arr[++i])) {
      }
      while (++i < arr.length) {
        Assert.assertFalse(predicate.test(arr[i]));
      }
    }

    @Test
    public void testEvenOddPartition() {
      Predicate<Integer> predicate = num -> num % 2 == 0;
      ArrayPartition.LINEAR_SOLUTION.solve(arr, predicate);
      verify(arr, predicate);
    }

    @Test
    public void testOddEvenPartition() {
      Predicate<Integer> predicate = num -> num % 2 == 1;
      ArrayPartition.LINEAR_SOLUTION.solve(arr, predicate);
      verify(arr, predicate);
    }

    @Test
    public void testPivotPartition() {
      int pivot = arr[arr.length - 1];
      Predicate<Integer> predicate = num -> num.compareTo(pivot) <= 0;
      ArrayPartition.LINEAR_SOLUTION.solve(arr, predicate);
      verify(arr, predicate);
    }

  }

}
