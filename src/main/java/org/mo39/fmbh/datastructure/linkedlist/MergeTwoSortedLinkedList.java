package org.mo39.fmbh.datastructure.linkedlist;

import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Given Two sorted Linked list, merge them into one sorted Linked List.
 *
 * @author Jihan Chen
 *
 */
public enum MergeTwoSortedLinkedList {

  LINEAR_SOLUTION() {

    @Override
    public <T extends Comparable<T>> ListNode<T> solve(ListNode<T> head0, ListNode<T> head1) {
      ListNode<T> dummyHead = new ListNode<>();
      ListNode<T> dummyHeadCopy = dummyHead;
      while (head0 != null || head1 != null) {
        if (head0 == null || head0 != null && head1 != null && head0.val.compareTo(head1.val) > 0) {
          dummyHead.next = head1;
          head1 = head1.next;
        } else {
          dummyHead.next = head0;
          head0 = head0.next;
        }
        dummyHead = dummyHead.next;
      }
      return dummyHeadCopy.next;
    }

  };

  public abstract <T extends Comparable<T>> ListNode<T> solve(ListNode<T> head0, ListNode<T> head1);

  public static class TestMergeTwoSortedLinkedList {

    private ListNode<Integer> head0 = new TestData().sortedLinkedList0;
    private ListNode<Integer> head1 = new TestData().sortedLinkedList1;
    private Integer[] expected = {1, 2, 3, 5, 6, 7, 8, 10, 31};

    @Test
    public void testLinearSolution() {
      Z.verify(expected, LINEAR_SOLUTION.solve(head0, head1));
    }

  }

}
