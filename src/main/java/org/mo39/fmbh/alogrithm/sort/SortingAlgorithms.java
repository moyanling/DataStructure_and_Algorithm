package org.mo39.fmbh.alogrithm.sort;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Loop Invariant is used to proof the algorithm.
 *
 * @author Jihan Chen
 *
 */
public enum SortingAlgorithms {

  /**
   * Insertion sort.<Br>
   * Every time find the smallest element and insert it to sorted sequence.<br>
   * So the sequence to the left of index i is sorted and invariant.<br>
   *
   */
  INSERTION_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      checkArgument(arr != null && !Arrays.asList(arr).contains(null));
      // When i is 0, the initial state satisfies the loop invariant. So i starts from 1.
      for (int i = 1; i < arr.length; i++) {
        T key = arr[i];
        int j = i - 1;
        // Insert arr[i] into the sorted sequence arr[0..i-1]
        for (; j > -1 && arr[j].compareTo(key) > 0; j--) {
          arr[j + 1] = arr[j];
        }
        /*
         * When exit the this loop, j is either at -1 or at the position where arr[j] is less than
         * key. And all elements larger than key is shifted to it's right position. So j+1 is spared
         * for key to put at.
         */
        arr[j + 1] = key;
      }
      // According to loop invariant, all elements from 0 to arr.length - 1 are sorted.
    }

  },

  BUBBEL_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - 1; j++) {
          if (arr[j].compareTo(arr[j + 1]) > 0) swap(arr, j, j + 1);
        }
      }
    }

  },

  /**
   * Selection sort.<br>
   * Each time select the smallest element and swap with the element at i.<br>
   * So the sequence to the left of index i is sorted and invariant.<br>
   *
   */
  SELECTION_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      checkArgument(arr != null && !Arrays.asList(arr).contains(null));
      // Initial state starts before 0. No element satisfies the loop invariant.
      for (int i = 0; i < arr.length; i++) {
        int j = i;
        int key = -1;
        // Find the smallest key starts from i to the end of arr.
        for (; j < arr.length; j++) {
          if (key == -1 || arr[key].compareTo(arr[j]) > 0) key = j;
        }
        swap(arr, i, key);
      }
    }

  },

  MERGE_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      checkArgument(arr != null && !Arrays.asList(arr).contains(null));
      mergeSort(arr, 0, arr.length);
    }

    /**
     * Merge and sort left part of arr and right part of arr.<br>
     * The pivot q is decided by (p + r) / 2.<br>
     * The left part starts from p (inclusive) to q (exclusive).<br>
     * The right part starts from q (inclusive) to r (exclusive).<br>
     * After comparing exclusive end posi and inclusive end posi, the exclusive one is adopted since
     * it's more elegant.
     *
     * @param arr
     * @param p
     * @param r
     */
    private <T extends Comparable<T>> void mergeSort(T[] arr, int p, int r) {
      if (r - p < 2) return;
      int q = (p + r) / 2;
      mergeSort(arr, p, q);
      mergeSort(arr, q, r);
      merge(arr, p, q, r);
    }

    private <T extends Comparable<T>> void merge(T[] arr, int p, int q, int r) {
      /*
       * This should create two new arrays representing two separate part left and right. But
       * generic array could not be created because the type of T is not known until runtime, so
       * ArrayList is used instead.
       */
      List<T> left = new ArrayList<>();
      List<T> right = new ArrayList<>();
      // starts from p (inclusive) to q (exclusive).
      for (int index = p; index < q; index++) {
        left.add(arr[index]);
      }
      // starts from q (inclusive) to r (exclusive).
      for (int index = q; index < r; index++) {
        right.add(arr[index]);
      }
      int i = 0, j = 0;
      for (int k = p; k < r; k++) {
        if (i >= left.size()) {
          arr[k] = right.get(j++);
          continue;
        }
        if (j >= right.size()) {
          arr[k] = left.get(i++);
          continue;
        }
        if (left.get(i).compareTo(right.get(j)) < 0) arr[k] = left.get(i++);
        else arr[k] = right.get(j++);
      }
    }

  };

  /**
   * Helper function that swaps two elements at position i and j.
   *
   * @param arr
   * @param i
   * @param j
   */
  private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * Method declaration shared by all instances of SortingAlogorithms.
   *
   * @param arr
   */
  public abstract <T extends Comparable<T>> void sort(T[] arr);

  /**
   * Helper class that tests sorting algorithm.
   *
   * @author Jihan Chen
   *
   */
  public static class TestSortingAlgorithm {

    private Integer[] arr = new TestData().arr;
    private Integer[] sortedArr = new TestData().sortedArr;

    @BeforeClass
    public static void before() {
      TestSortingAlgorithm test = new TestSortingAlgorithm();
      Z.print("Array to sort : " + Arrays.toString(test.arr));
      Z.print("Sorted  array : " + Arrays.toString(test.sortedArr));
    }

    @Test
    public void testInsertionSort() {
      SortingAlgorithms.INSERTION_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testBubbleSort() {
      SortingAlgorithms.BUBBEL_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testSelectionSort() {
      SortingAlgorithms.SELECTION_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testMergeSort() {
      SortingAlgorithms.MERGE_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }



  }

}
