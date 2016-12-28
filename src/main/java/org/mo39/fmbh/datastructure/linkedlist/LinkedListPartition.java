package org.mo39.fmbh.datastructure.linkedlist;

import java.util.function.Predicate;

import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

public enum LinkedListPartition {

  /**
   * Given a Predicate, take this ListNode as the head and make all nodes that satisfied this
   * Predicate come first and all others follow by. Stable.
   * <p>
   * Dummy head is a useful technique.<br>
   * The end state after loop needs more process.<br>
   *
   * @param val
   * @return new head for this linked list
   */
  SOLUTION {

    @Override
    public <T> ListNode<T> solve(ListNode<T> node, Predicate<ListNode<T>> p) {
      ListNode<T> head = node;
      ListNode<T> head1 = new ListNode<T>();
      ListNode<T> head2 = new ListNode<T>();
      ListNode<T> head1Copy = head1;
      ListNode<T> head2Copy = head2;
      while (head != null) {
        if (p.test(head)) {
          head1.next = head;
          head1 = head1.next;
        } else {
          head2.next = head;
          head2 = head2.next;
        }
        head = head.next;
      }
      head2.next = null;
      head1.next = head2Copy.next;
      return head1Copy.next;
    }

  };

  public abstract <T> ListNode<T> solve(ListNode<T> node, Predicate<ListNode<T>> p);

  public static class TestLinkedListPartition {

    private ListNode<Integer> head = new TestData().head;
    private Integer[] expected = {1, 3, 0, 4, 3, 7, 6, 5, 8, 5};

    @Test
    public void testPartition() {
      Z.verify(expected, SOLUTION.solve(head, node -> node.val < 5));
    }

  }


}
