package org.mo39.fmbh.datastructure.linkedlist;

/**
 * There are several characteristics of Linked List that needs attention.<br>
 * 1. It's lack of constant time access;<br>
 * 2. Recursion;<br>
 * 3. Deleting a node;<br>
 * 4. Runner technique.<br>
 *
 * @author Jihan Chen
 *
 */
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
