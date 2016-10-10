package org.mo39.fmbh.datastructure.heap;

import java.util.Comparator;

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
public abstract class AbstractHeap<T> {

  private int size;
  protected Comparator<T> c;
  protected T[] arr;

  /**
   * Take an array to build a new Heap and heapify from the parent of the last leaf node. No need to
   * heapify a single leaf node.
   * 
   */
  public AbstractHeap(T[] arr, Comparator<T> c) {
    this.c = c;
    this.arr = arr;
    size = arr.length;
    for (int i = arr.length / 2; i >= 0; i--) {
      heapify(i);
    }
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void heapify(int i) {
    iterativeHeapify(i);
  }

  @SuppressWarnings("unused")
  private void recusiveHeapify(int i) {
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int largest = i;
    if (left < size && c.compare(arr[left], arr[i]) > 0) largest = left;
    if (right < size && c.compare(arr[right], arr[largest]) > 0) largest = right;
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
      if (left < size && c.compare(arr[left], arr[i]) > 0) largest = left;
      if (right < size && c.compare(arr[right], arr[largest]) > 0) largest = right;
      if (i != largest) Z.swap(arr, i, largest);
    } while (i != largest);
  }

}
