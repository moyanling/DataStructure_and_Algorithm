package org.mo39.fmbh.datastructure.linkedlist;

public class ListNode {

  public int val;
  public ListNode next;

  public ListNode() {}

  public ListNode(int val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }
}
