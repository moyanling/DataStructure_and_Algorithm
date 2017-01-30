package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Reverse a singly linked list.
 * 
 * click to show more hints.
 * 
 * Hint:
 * A linked list can be reversed either iteratively or recursively. Could you
 * implement both?
 * </pre>
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">Reverse Linked List</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ReverseLinkedList {

  ITERATIVE_SOLUTION {

    @Override
    public <T> ListNode<T> solve(ListNode<T> head) {
      if (head == null) return null;
      ListNode<T> pre = null;
      while (head != null) {
        ListNode<T> next = head.next;
        head.next = pre;
        pre = head;
        head = next;
      }
      return pre;
    }

  },

  RECURSIVE_SOLUTION {

    @Override
    public <T> ListNode<T> solve(ListNode<T> head) {
      return recur(head, null);
    }

    <T> ListNode<T> recur(ListNode<T> cur, ListNode<T> pre) {
      if (cur == null) return pre;
      ListNode<T> next = cur.next;
      cur.next = pre;
      return recur(next, cur);
    }

  };

  public abstract <T> ListNode<T> solve(ListNode<T> head);

  public static class TestReverseLinkedList {

    Integer[] expected = new Integer[] {3, 2, 1, 0};

    @Test
    public void testSolutions() {
      for (ReverseLinkedList sol : ReverseLinkedList.values()) {
        ListNode<Integer> head = new ListNode<>(0);
        head.next = new ListNode<>(1);
        head.next.next = new ListNode<>(2);
        head.next.next.next = new ListNode<>(3);
        Z.verify(expected, sol.solve(head));
      }
    }
  }

}