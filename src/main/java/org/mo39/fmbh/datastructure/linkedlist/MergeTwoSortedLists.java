package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Merge two sorted linked lists and return it as a new list. The new list should
 * be made by splicing together the nodes of the first two lists.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/merge-two-sorted-lists/">Merge Two Sorted Lists</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MergeTwoSortedLists {

  SOLUTION {

    @Override
    public <T extends Comparable<T>> ListNode<T> solve(ListNode<T> l1, ListNode<T> l2) {
      ListNode<T> fakeHead = new ListNode<>(), cur = fakeHead;
      while (l1 != null || l2 != null) {
        if (l1 == null || l2 != null && l1.val.compareTo(l2.val) > 0) {
          cur.next = l2;
          l2 = l2.next;
        } else {
          cur.next = l1;
          l1 = l1.next;
        }
        cur = cur.next;
      }
      return fakeHead.next;
    }

  };

  public abstract <T extends Comparable<T>> ListNode<T> solve(ListNode<T> head0, ListNode<T> head1);

  public static class TestMergeTwoSortedLinkedList {

    private ListNode<Integer> head0 = new TestData().sortedLinkedList0;
    private ListNode<Integer> head1 = new TestData().sortedLinkedList1;
    private Integer[] expected = {1, 2, 3, 5, 6, 7, 8, 10, 31};

    @Test
    public void testLinearSolution() {
      Z.verify(expected, SOLUTION.solve(head0, head1));
    }

  }

}
