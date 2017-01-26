package org.mo39.fmbh.datastructure.design;

import java.util.Iterator;

public class PeekingIterator<T> implements Iterator<T> {

  Iterator<T> itor;
  T placeHolder;

  public PeekingIterator(Iterator<T> iterator) {
    this.itor = iterator;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public T peek() {
    if (placeHolder == null) placeHolder = itor.next();
    return placeHolder;
  }

  @Override
  public boolean hasNext() {
    return itor.hasNext() || placeHolder != null;
  }

  @Override
  public T next() {
    if (placeHolder != null) {
      T temp = placeHolder;
      placeHolder = null;
      return temp;
    }
    return itor.next();
  }

}
