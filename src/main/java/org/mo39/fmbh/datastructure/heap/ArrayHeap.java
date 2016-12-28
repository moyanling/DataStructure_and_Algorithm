package org.mo39.fmbh.datastructure.heap;

import java.util.Comparator;

import org.mo39.fmbh.common.Z;

/**
 * ArrayHeap used only for sort.
 * <p>
 * Heap should support insert operation. But array does not support insertion otherwise an ArrayList
 * has to be used to maintain the elements, which conflicts the in-place sorting. So this class is
 * only designed for heap sort and insertion is not supported. For Heap with insertion,
 * {@see ListHeap}
 *
 * @author Jihan Chen
 *
 * @param <T>
 */
public class ArrayHeap<T> extends Heap<T> {

  protected T[] arr;

  public static <T extends Comparable<T>> ArrayHeap<T> newMaxHeap(T[] arr) {
    return new ArrayHeap<>(arr, (o1, o2) -> o1.compareTo(o2));
  }

  public static <T extends Comparable<T>> ArrayHeap<T> newMinHeap(T[] arr) {
    return new ArrayHeap<>(arr, (o1, o2) -> o2.compareTo(o1));
  }

  private Comparator<T> c;

  /**
   * Take an array to build a new Heap and heapify from the parent of the last leaf node. No need to
   * heapify a single leaf node.
   *
   */
  public ArrayHeap(T[] arr, Comparator<T> c) {
    this.c = c;
    this.arr = arr;
    this.size = arr.length;
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
    if (left < getSize() && c.compare(arr[left], arr[i]) > 0) largest = left;
    if (right < getSize() && c.compare(arr[right], arr[largest]) > 0) largest = right;
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
      if (left < getSize() && c.compare(arr[left], arr[i]) > 0) largest = left;
      if (right < getSize() && c.compare(arr[right], arr[largest]) > 0) largest = right;
      if (i != largest) Z.swap(arr, i, largest);
    } while (i != largest);
  }

  @Override
  public void insert(T t) {
    throw new UnsupportedOperationException();
  }

}
