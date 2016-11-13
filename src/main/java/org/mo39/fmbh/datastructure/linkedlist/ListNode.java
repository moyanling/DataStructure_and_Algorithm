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
public class ListNode<T> {

  /**
   * The value of this ListNode
   */
  public T val;

  /**
   * The variable member that holds the next ListNode.
   */
  public ListNode<T> next;

  public ListNode() {}

  public ListNode(T val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

}
