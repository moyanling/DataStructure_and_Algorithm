package org.mo39.fmbh.datastructure.heap;

/**
 * Abstract Heap.
 * <p>
 * //TODO T[] should be changed to ArrayList<T> so that new element can be inserted into heap.
 * 
 * @author Jihan Chen
 *
 * @param <T>
 */
public abstract class AbstractHeap<T extends Comparable<T>> {

  private int size;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public abstract void heapify(int i);

}
