package org.mo39.fmbh.datastructure.heap;

import java.util.Comparator;

public class Heap<T extends Comparable<T>> extends AbstractHeap<T> {

  private Heap(T[] arr, Comparator<T> c) {
    super(arr, c);
  }

  public static <T extends Comparable<T>> Heap<T> newMaxHeap(T[] arr) {
    return new Heap<>(arr, (o1, o2) -> o1.compareTo(o2));
  }

  public static <T extends Comparable<T>> Heap<T> newMinHeap(T[] arr) {
    return new Heap<>(arr, (o1, o2) -> o2.compareTo(o1));
  }

}
