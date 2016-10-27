package org.mo39.fmbh.algorithm.sort;

import static com.google.common.base.Preconditions.checkArgument;
import static org.mo39.fmbh.common.Z.swap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.datastructure.array.ArrayPartition;
import org.mo39.fmbh.datastructure.heap.ArrayHeap;

/**
 * Loop Invariant is used to proof the algorithm.
 *
 * @author Jihan Chen
 *
 */
public enum ComparisonSort {

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
   */
  INSERTION_SORT() {

    @Override
    public <T extends Comparable<T>> void sortArr(T[] arr) {
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
   */
  BUBBEL_SORT() {

    @Override
    public <T extends Comparable<T>> void sortArr(T[] arr) {
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
   * Selection sort. (Unstable)<br>
   * Each time select the smallest element and swap with the element at i.<br>
   * <p>
   * Initial state: i = 0. No element is sorted.<br>
   * Loop invariant: each time after swap, the sequence to the left of index i (inclusive) is
   * sorted.<br>
   * End state: i = arr.length and all elements to its left are sorted. So arr is sorted.
   * <p>
   * Best case and worst case are always <b>O(n^2)</b>.<br>
   *
   */
  SELECTION_SORT() {

    @Override
    public <T extends Comparable<T>> void sortArr(T[] arr) {
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

  /**
   * Merge sort. (Stable)<br>
   * Split and merge left part and right part recursively.<br>
   * <p>
   * Initial state: before first merge, k = 0 and left and right have 1 element respectively.<br>
   * Loop invariant: each merge get the smallest element from left and right. The merged part is
   * always sorted and thus arr from k to r (inclusive) is sorted.<br>
   * End state: k ends at r + 1 and r finally is arr.length - 1. So index from k = 0 to k = r =
   * arr.length - 1 of arr is sorted.<br>
   * <p>
   * The most intuitive way to understand time complexity is the height of a tree. Each recursion
   * the arr is split into two sub array and thus it's a tree structure. The process to split takes
   * O(1) because it's just calculating the split point q. In the merge process, it reverse the
   * split process and will take log</b> loop to complete the merge because it's the height of a
   * tree. In each merge, it is merging two sub array into one, it takes O(n). So the overall time
   * complexity is <b>O(nlog(n))</b>
   *
   */
  MERGE_SORT() {

    @Override
    public <T extends Comparable<T>> void sortArr(T[] arr) {
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

  },

  /**
   * Heap sort. (Unstable)<br>
   * Build a max heap, then every time swap the top element to the end of heap, make heap exclude
   * that element use heapify to maintain the rest elements of max heap.
   * <p>
   * Initial state: after the max heap is built, the first element is the largest element.<br>
   * Loop invariant: each time swap the largest element with the last element in the heap, make the
   * size minus 1 and use heapify to maintain the structure so that after every loop, the top
   * element is always the largest and the tail of the array are sorted.<br>
   * End state: the loop stops after i = 1. So all elements larger than arr[0] is moved to the tail
   * (the sorted sequence) and arr[0] itself is the smallest element<br>
   * <p>
   * It takes O(n) to build a heap, O(log(n)) to heapify and will heapify n - 1 times. So the time
   * complexity of heap sort is <b>O(nlog(n))</b>
   */
  HEAP_SORT() {

    @Override
    protected <T extends Comparable<T>> void sortArr(T[] arr) {
      ArrayHeap<T> maxHeap = ArrayHeap.newMaxHeap(arr);
      for (int i = arr.length - 1; i > 0; i--) {
        swap(arr, 0, i);
        maxHeap.setSize(maxHeap.getSize() - 1);
        maxHeap.heapify(0);
      }
    }

  },

  /**
   * Quick sort (Unstable)<br>
   * Use divide and conquer. Divide the array into two sub array. For each sub array, take the last
   * element as pivot and do array partition.
   * <p>
   * This loop invariant verifies {@link ArrayPartition.LINEAR_SOLUTION}.<br>
   * Initial state: starts from j = start - 1 to i = end, no element is left to j.<br>
   * Loop invariant: if arr[start] is less than or equal to pivot <code>arr[r]</code>, swap it to
   * the element at j and make j plus 1. So j is always pointing to the last element of a sequence
   * whose elements are all smaller than or equal to pivot.<br>
   * End: i = end and all elements are visited. So all elements smaller than or equal to pivot are
   * swapped to the left of j.
   * <p>
   * partition takes O(n) to complete. Divide and conquer will make partition happens log(n) times.
   * So the time complexity is <b>O(nlog(n))</b>
   *
   */
  QUICK_SORT() {

    @Override
    public <T extends Comparable<T>> void sortArr(T[] arr) {
      quickSort(arr, 0, arr.length - 1);
    }

    private <T extends Comparable<T>> void quickSort(T[] arr, int p, int r) {
      if (p >= r) return;
      int q = partition(arr, p, r);
      quickSort(arr, p, q - 1);
      quickSort(arr, q + 1, r);
    }

    /**
     * Part the array around pivot <code>arr[r]</code> so that from index p (inclusive) to r - 1
     * (inclusive) are smaller than pivot and all the others are larger than pivot.<br>
     * <p>
     * Index i is pointing to the last element of the sequence, whose elements are all smaller than
     * the pivot.
     *
     * @param arr
     * @param p
     * @param r
     * @return
     */
    private <T extends Comparable<T>> int partition(T[] arr, int p, int r) {
      return ArrayPartition.LINEAR_SOLUTION.solve(arr, num -> num.compareTo(arr[r]) <= 0, p, r + 1);
    }

  };

  public <T extends Comparable<T>> void sort(T[] arr) {
    checkArgument(arr != null && !Arrays.asList(arr).contains(null));
    sortArr(arr);
  }

  /**
   * Method declaration shared by all instances of SortingAlogorithms.
   *
   * @param arr
   */
  protected abstract <T extends Comparable<T>> void sortArr(T[] arr);

  /**
   * Helper class that tests sorting algorithm.
   *
   * @author Jihan Chen
   *
   */
  public static class TestSortingAlgorithm {

    private Integer[] arr = new TestData().integerArr;
    private Integer[] sortedArr = new TestData().integerArr;

    @Before
    public void sort() {
      Arrays.sort(sortedArr);
    }

    @Test
    public void testInsertionSort() {
      ComparisonSort.INSERTION_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);

    }

    @Test
    public void testBubbleSort() {
      ComparisonSort.BUBBEL_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testSelectionSort() {
      ComparisonSort.SELECTION_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testMergeSort() {
      ComparisonSort.MERGE_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testHeapSort() {
      ComparisonSort.HEAP_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testQuickSort() {
      ComparisonSort.QUICK_SORT.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

  }

}
