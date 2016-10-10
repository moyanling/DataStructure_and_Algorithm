package org.mo39.fmbh.algorithm.sort;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Assumption: all input elements are not smaller than 0 and are smaller than k.
 * 
 * @author Jihan Chen
 *
 */
public abstract class CountingSort {

  /**
   * k - 1 is the largest number in arr. No elements in arr is smaller than 0.
   * 
   * @param arr
   * @param k
   */
  public static int[] sort(int[] arr, int k) {
    int[] toRet = new int[arr.length];
    int[] kStore = new int[k];
    for (int i = 0; i < arr.length; i++) {
      kStore[arr[i]]++;
    }
    for (int i = 1; i < k; i++) {
      kStore[i] += kStore[i - 1];
    }
    // If iterate from o to arr.length, equal element will be put in reverse order.
    for (int i = arr.length - 1; i > -1; i--) {
      toRet[--kStore[arr[i]]] = arr[i];
    }

    return toRet;
  }

  public static class TestCountingSort {

    private int[] arr = {5, 7, 3, 4, 2, 2, 1};

    @Test
    public void testCountingSort() {
      int[] sortedArr = CountingSort.sort(arr, 8);
      Arrays.sort(arr);
      Assert.assertArrayEquals(arr, sortedArr);
    }

  }

}
