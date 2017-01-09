package org.mo39.fmbh.common;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import org.mo39.fmbh.datastructure.array.QuickSelect;

public class S {

  /**
   * Part the array around pivot <code>arr[r]</code> so that from index p (inclusive) to r
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
  public static <T> int partition(T[] arr, Predicate<T> predicate, int start, int end) {
    int j = start - 1;
    for (int i = start; i <= end; i++) {
      if (predicate.test(arr[i])) Z.swap(arr, i, ++j);
    }
    return j;
  }

  public static <T> int partition(T[] arr, Predicate<T> predicate) {
    return partition(arr, predicate, 0, arr.length - 1);
  };

  public static int randomPartition(int[] arr, int start, int end) {
    int pivot = arr[ThreadLocalRandom.current().nextInt(start, end + 1)];
    return partition(arr, start, end, pivot);
  }

  public static int partition(int[] arr, int start, int end, int pivot) {
    int j = start - 1;
    for (int i = start; i <= end; i++) {
      if (arr[i] <= pivot) Z.swap(arr, i, ++j);
    }
    return j;
  }

  public static int medianOf(int[] nums) {
    return QuickSelect.ITERATIVE_SOLUTION.solve(nums, nums.length / 2 + 1);
  }

}
