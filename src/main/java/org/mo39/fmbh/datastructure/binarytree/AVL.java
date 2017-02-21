package org.mo39.fmbh.datastructure.binarytree;

import java.util.Comparator;

public class AVL extends BST {

  protected AVL(Comparator<Integer> comparator) {
    super(comparator);
  }

  /**
   * Create a new AVL with given a {@link Comparator}
   *
   * @return a new BST.
   */
  public static AVL init(Comparator<Integer> comparator) {
    return new AVL(comparator);
  }

  /**
   * Create a new BST with a {@link Comparable} generic type.
   *
   * @return a new BST.
   */
  public static AVL init() {
    return new AVL((o1, o2) -> o1.compareTo(o2));
  }

  @Override
  public void insert(int data) {
    // TODO
  }

  @Override
  public boolean delete(int data) {
    // TODO
    return false;
  }

}
