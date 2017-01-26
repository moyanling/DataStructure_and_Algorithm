package org.mo39.fmbh.datastructure.binarytree;

public class TreeLinkNode<T> {

  public final T val;

  TreeLinkNode<T> left, right, next;

  TreeLinkNode(T val) {
    this.val = val;
  }

}
