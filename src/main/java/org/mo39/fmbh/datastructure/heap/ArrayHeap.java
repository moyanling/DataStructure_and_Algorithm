package org.mo39.fmbh.datastructure.heap;

import org.mo39.fmbh.common.Z;

/**
 * Abstract Heap.
 * <p>
 * //TODO T[] should be changed to ArrayList<T> so that new element can be inserted into heap.
 * 
 * @author Jihan Chen
 *
 * @param <T>
 */
public class ArrayHeap<T extends Comparable<T>> extends AbstractHeap<T> {

  protected T[] arr;

  public static <T extends Comparable<T>> ArrayHeap<T> newMaxHeap(T[] arr) {
    return new ArrayHeap<>(arr);
  }

  public static <T extends Comparable<T>> ArrayHeap<T> newMinHeap(T[] arr) {
    return new ArrayHeap<>(arr);
  }

  /**
   * Take an array to build a new Heap and heapify from the parent of the last leaf node. No need to
   * heapify a single leaf node.
   * 
   */
  protected ArrayHeap(T[] arr) {
    this.arr = arr;
    super.setSize(arr.length);
    for (int i = arr.length / 2; i >= 0; i--) {
      heapify(i);
    }
  }

  @Override
  public void heapify(int i) {
    iterativeHeapify(i);
  }

  @SuppressWarnings("unused")
  private void recusiveHeapify(int i) {
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int largest = i;
    if (left < getSize() && arr[left].compareTo(arr[i]) > 0) largest = left;
    if (right < getSize() && arr[right].compareTo(arr[largest]) > 0) largest = right;
    if (largest != i) {
      Z.swap(arr, i, largest);
      recusiveHeapify(largest);
    }
  }

  private void iterativeHeapify(int i) {
    int largest = i;
    do {
      i = largest;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < getSize() && arr[left].compareTo(arr[i]) > 0) largest = left;
      if (right < getSize() && arr[right].compareTo(arr[largest]) > 0) largest = right;
      if (i != largest) Z.swap(arr, i, largest);
    } while (i != largest);
  }

}
