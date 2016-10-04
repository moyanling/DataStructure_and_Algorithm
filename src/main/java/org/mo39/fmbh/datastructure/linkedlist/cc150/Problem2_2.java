package org.mo39.fmbh.datastructure.linkedlist.cc150;

import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

public class Problem2_2 {

  public static void main(String[] args) {
    Z.printLinkedList(new TestData().head);
    Z.print();
    Z.printLinkedList(new Problem2_2().findKthLast1(new TestData().head, 5));
  }

  /**
   * Recursion.
   *
   * @param head
   * @param k
   * @return
   */
  public ListNode findKthLast0(ListNode head, int k) {
    if (head == null) return head;
    traveLinkedList(head, k);
    return toRet;
  }

  private ListNode toRet;
  private int count;

  private ListNode traveLinkedList(ListNode head, int k) {
    if (head == null) return null;
    traveLinkedList(head.next, k);
    count++;
    if (count == k) toRet = head;
    return head;
  }

  /**
   * Iteration
   *
   * @param head
   * @param k
   * @return
   */
  public ListNode findKthLast1(ListNode head, int k) {
    int length = 0;
    ListNode origin = head;
    while (head != null) {
      length++;
      head = head.next;
    }
    if (k > length) return null;
    int count = 0;
    while (origin != null) {
      count++;
      if (count == length - k + 1) return origin;
      origin = origin.next;
    }
    return null;
  }

}
