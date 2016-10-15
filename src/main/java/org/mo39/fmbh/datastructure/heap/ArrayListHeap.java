
package org.mo39.fmbh.datastructure.heap;

import java.util.ArrayList;
import java.util.List;

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
public class ArrayListHeap<T extends Comparable<T>> extends AbstractHeap<T> {

  private int size;
  protected List<T> list = new ArrayList<>();

  /**
   * Take an array to build a new Heap and heapify from the parent of the last leaf node. No need to
   * heapify a single leaf node.
   * 
   */
  public ArrayListHeap(T[] arr) {
    for (T t : arr) {
      list.add(t);
    }
    size = arr.length;
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
    if (left < size && list.get(left).compareTo(list.get(i)) > 0) largest = left;
    if (right < size && list.get(right).compareTo(list.get(largest)) > 0) largest = right;
    if (largest != i) {
      Z.swap(list, i, largest);
      recusiveHeapify(largest);
    }
  }

  private void iterativeHeapify(int i) {
    int largest = i;
    do {
      i = largest;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      if (left < size && list.get(left).compareTo(list.get(i)) > 0) largest = left;
      if (right < size && list.get(right).compareTo(list.get(largest)) > 0) largest = right;
      if (i != largest) Z.swap(list, i, largest);
    } while (i != largest);
  }

}
