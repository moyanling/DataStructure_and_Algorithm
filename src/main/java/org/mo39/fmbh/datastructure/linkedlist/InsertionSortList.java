package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Sort a linked list using insertion sort.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/insertion-sort-list/">Insertion Sort List</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum InsertionSortList {

  SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> head) {
      if (head == null) return head;
      ListNode<Integer> fakeHead = new ListNode<>(Integer.MIN_VALUE), cur = fakeHead;
      while (head != null) {
        /*
         * Before insert, the prev is at the last node of the sorted list. Only the last node's
         * value is larger than the current inserting node should we move the temp back to the head
         */
        if (cur.val >= head.val) cur = fakeHead;
        for (; cur.next != null && cur.next.val < head.val; cur = cur.next) {
        }
        ListNode<Integer> temp = head.next;
        head.next = cur.next;
        cur.next = head;
        head = temp;
      }
      return fakeHead.next;
    }

  };

  public abstract ListNode<Integer> solve(ListNode<Integer> head);

  public static class TestInsertionSortList {

    ListNode<Integer> head = new ListNode<>(0);
    Integer[] expected = {0, 2, 3, 4, 5};

    {
      head.next = new ListNode<>(5);
      head.next.next = new ListNode<>(3);
      head.next.next.next = new ListNode<>(2);
      head.next.next.next.next = new ListNode<>(4);
    }

    @Test
    public void testSolutions() {
      Z.verify(expected, SOLUTION.solve(head));
    }

  }

}
