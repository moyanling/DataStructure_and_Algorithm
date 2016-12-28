package org.mo39.fmbh.datastructure.heap;

public abstract class Heap<T> {

  protected int size;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public abstract void heapify(int i);

  public abstract void insert(T t);

}
