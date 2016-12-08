package org.mo39.fmbh.datastructure.binarytree;

import java.util.Comparator;

public class AVL<T> extends BST<T> {

  protected AVL(Comparator<T> comparator) {
    super(comparator);
  }

  /**
   * Create a new AVL with given a {@link Comparator}
   *
   * @return a new BST.
   */
  public static <T> AVL<T> init(Comparator<T> comparator) {
    return new AVL<T>(comparator);
  }

  /**
   * Create a new BST with a {@link Comparable} generic type.
   *
   * @return a new BST.
   */
  public static <T extends Comparable<T>> AVL<T> init() {
    return new AVL<T>((o1, o2) -> o1.compareTo(o2));
  }

  @Override
  public void insert(T data) {
    // TODO
  }

  @Override
  public boolean delete(T data) {
    // TODO
    return false;
  }

}
