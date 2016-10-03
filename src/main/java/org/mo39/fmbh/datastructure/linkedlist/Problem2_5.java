package org.mo39.fmbh.datastructure.linkedlist;

import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

public class Problem2_5 {

  public static void main(String[] args) {
    ListNode head1 = new TestData().node4;
    ListNode head2 = new TestData().node5;
    Z.printLinkedList(head1);
    Z.print();
    Z.printLinkedList(head2);
    Z.print();
    ListNode newHead = new Problem2_5().addTwoLinkedListInReverseOrder(head1, head2);
    Z.printLinkedList(newHead);
  }

  /**
   * Use recursion to add since the first digit.
   *
   * @param head
   * @return
   */
  public ListNode addTwoLinkedList(ListNode head1, ListNode head2, int carry) {
    if (head1 == null && head2 == null && carry == 0) return null;
    ListNode newNode = new ListNode();
    int value = carry;
    if (head1 != null) value += head1.val;
    if (head2 != null) value += head2.val;
    newNode.val = value % 10;
    if (head1 != null || head2 != null) {
      newNode.next = addTwoLinkedList(head1 == null ? null : head1.next,
          head2 == null ? null : head2.next, value / 10);
    }
    return newNode;
  }

  /**
   * Use recursion to add since the last digit.
   *
   * @param head1
   * @param head2
   * @param carry
   * @return
   */
  public ListNode addTwoLinkedListInReverseOrder(ListNode head1, ListNode head2) {
    ListNode toRet = helperFunction(head1, head2);
    if (toRet.val >= 10) {
      ListNode newHead = new ListNode(1);
      toRet.val = toRet.val % 10;
      newHead.next = toRet;
      return newHead;
    }
    return toRet;
  }

  private ListNode helperFunction(ListNode head1, ListNode head2) {
    if (head1 == null && head2 == null) return null;
    ListNode head = new ListNode();
    if (head1 != null || head2 != null) {
      ListNode next = helperFunction(//
          head1 == null ? null : head1.next, //
          head2 == null ? null : head2.next);
      if (next != null) {
        int value = next.val / 10;
        next.val = next.val % 10;
        if (head1 != null) value += head1.val;
        if (head2 != null) value += head2.val;
        head.val = value;
      }
      head.next = next;
    }
    return head;
  }


}
