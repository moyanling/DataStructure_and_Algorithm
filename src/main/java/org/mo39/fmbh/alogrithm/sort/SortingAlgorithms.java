package org.mo39.fmbh.alogrithm.sort;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
   * Insertion sort. (Stable)<br>
   * Every time find the smallest element and insert it to sorted sequence. <br>
   * <p>
   * Initial state: i = 1. One element is sorted.<br>
   * Loop invariant: The sequence to the left of index i (exclusive) is sorted and invariant.<br>
   * End state: i = arr.length and all elements to its left are sorted. So arr is sorted.
   * <p>
   * Then best case is when the array is already sorted. The time complexity is <b>O(n)</b>.<br>
   * The worst case is when the array is reverse sorted. The time complexity is <b>O(n^2)</b>.<br>
   * <p>
   * If binary search is used when insert the key to already sorted sequence (loop invariant), then
   * it's improved slightly and so called Binary Insertion Sort.
   * 
   * @author Jihan Chen
   * 
   */
  INSERTION_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      checkArgument(arr != null && !Arrays.asList(arr).contains(null));
      // When i is 0, the initial state satisfies the loop invariant. So i
      // starts from 1.
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
      // According to loop invariant, all elements from 0 to arr.length -
      // 1 are sorted.
    }

  },

  /**
   * Bubble sort. (Stable)<br>
   * Every time compare two adjacent elements and swap them if one is smaller than the other.<br>
   * <p>
   * Initial state: i = 0. No element is sorted.<br>
   * Loop invariant: i elements are sorted at the end of arr. The largest element is bubbled to the
   * end each time.<br>
   * End state: i = arr.length. All elements are sorted.
   * <p>
   * Then best case is when the array is already sorted. The time complexity is <b>O(n)</b>.<br>
   * The worst case is when the array is reverse sorted. The time complexity is <b>O(n^2)</b>.<br>
   * <p>
   * Such adaptive property only applys when it exits immediately after no swap happens in one loop.
   * <br>
   * If flag <code>isSwapped</code> is not provided, bubble sort would not have such property.
   * 
   * @author Jihan Chen
   * 
   */
  BUBBEL_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      for (int i = 0; i < arr.length - 1; i++) {
        boolean isSwapped = false;
        for (int j = 0; j < arr.length - 1; j++) {
          if (arr[j].compareTo(arr[j + 1]) > 0) {
            swap(arr, j, j + 1);
            isSwapped = true;
          }
        }
        if (!isSwapped) return;
      }
    }

  },

  /**
   * Selection sort. (Not stable)<br>
   * Each time select the smallest element and swap with the element at i.<br>
   * <p>
   * Initial state: i = 0. No element is sorted.<br>
   * Loop invariant: each time after swap, the sequence to the left of index i (inclusive) is
   * sorted.<br>
   * End state: i = arr.length and all elements to its left are sorted. So arr is sorted.
   * <p>
   * Best case and worst case are always <b>O(n^2)</b>.<br>
   *
   * @author Jihan Chen
   *
   */
  SELECTION_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      checkArgument(arr != null && !Arrays.asList(arr).contains(null));
      // Initial state starts before 0. No element satisfies the loop
      // invariant.
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

  /**
   * Merge sort. (Stable)<br>
   * Split and merge left part and right part recursively.<br>
   * <p>
   * Initial state: before first merge, k = 0 and left and right have 1 element respectively.<br>
   * Loop invariant: each merge get the smallest T from left and right. The merged part is always
   * sorted and thus arr from k to r (inclusive) is sorted.<br>
   * End state: k ends at r + 1 and r finally is arr.length - 1. So index from k = 0 to k = r =
   * arr.length - 1 of arr is sorted.<br>
   * <p>
   * The most intuitive way to understand time complexity is the height of a tree. Each recursion
   * the arr is split into two sub array and thus it's a tree structure. The process to split takes
   * O(1) because it's just calculating the split point q. In the merge process, it reverse the
   * split process and will take log</b> loop to complete the merge because it's the height of a
   * tree. In each merge, it is merging two sub array into one, it takes O(n). So the overall time
   * complexity is <b>nlog(n)</b>
   * 
   * @author Jihan Chen
   * 
   */
  MERGE_SORT() {

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
      checkArgument(arr != null && !Arrays.asList(arr).contains(null));
      mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Merge and sort left part of arr and right part of arr.<br>
     * The pivot q is decided by (p + r) / 2.<br>
     * The left part starts from p (inclusive) to q (inclusive).<br>
     * The right part starts from q + 1 (inclusive) to r (inclusive).
     *
     * @param arr
     * @param p
     * @param r
     */
    private <T extends Comparable<T>> void mergeSort(T[] arr, int p, int r) {
      if (r == p) return;
      int q = (p + r) / 2;
      mergeSort(arr, p, q);
      mergeSort(arr, q + 1, r);
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
      // starts from p (inclusive) to q (inclusive).
      for (int index = p; index <= q; index++) {
        left.add(arr[index]);
      }
      // starts from q (inclusive) to r (inclusive).
      for (int index = q + 1; index <= r; index++) {
        right.add(arr[index]);
      }
      int i = 0, j = 0;
      for (int k = p; k <= r; k++) {
        /*
         * Instead of add it one by one, this can be slightly better if do it by copy all the rest
         * elements to arr then ends with a break. But since here it's using an ArrayList, so
         * System.arraycopy can not be used and it will have to go through a loop, which is not
         * elegant and thus not implemented.
         */
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

    private Integer[] arr = new TestData().integerArr;
    private Integer[] sortedArr = new TestData().integerArr;

    @BeforeClass
    public static void before() {
      TestSortingAlgorithm test = new TestSortingAlgorithm();
      Z.print("Array to sort : " + Arrays.toString(test.arr));
      Z.print("Sorted  array : " + Arrays.toString(test.sortedArr));
    }

    @Before
    public void sortArr() {
      Arrays.sort(sortedArr);
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
