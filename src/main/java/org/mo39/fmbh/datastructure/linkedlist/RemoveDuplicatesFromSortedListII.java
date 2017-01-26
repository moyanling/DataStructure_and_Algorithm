package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving
 * only distinct numbers from the original list.
 * 
 * 
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/">Remove
 *      Duplicates From Sorted List II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RemoveDuplicatesFromSortedListII {

  /**
   * The idea is, if a node does not equal the previous one and next one, then create a new node for
   * new linked list.</br>
   * Code is bad because not using fake head so have to do a if condition before while. Idea is bad
   * because have to create new node.
   */
  BAD_SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> head) {
      if (head == null || head.next == null) return head;
      ListNode<Integer> newHead = null, cur = null, pre = head;
      head = head.next;
      if (pre.val != head.val) {
        newHead = new ListNode<>(pre.val);
        cur = newHead;
      }
      while (head != null) {
        if (head.val != pre.val && (head.next == null || head.val != head.next.val)) {
          if (newHead == null) {
            newHead = new ListNode<>(head.val);
            cur = newHead;
          } else {
            cur.next = new ListNode<>(head.val);
            cur = cur.next;
          }
        }
        pre = head;
        head = head.next;
      }
      return newHead;
    }

  },

  /**
   * The idea is, find the node where the value is going change in next node. If cur is pointing to
   * the same one, there's no duplicate. Forward the cur. Otherwise, there's duplicate. Jump over
   * node between cur and head.next. Make cur.next point to head.next. This jump over and fake head
   * is helpful.
   */
  SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> head) {
      if (head == null) return null;
      ListNode<Integer> fakeHead = new ListNode<>(0), cur = fakeHead;
      fakeHead.next = head;
      for (; head != null; head = head.next) {
        while (head.next != null && head.val == head.next.val) {
          head = head.next;
        }
        if (cur.next == head) cur = cur.next;
        else cur.next = head.next;
      }
      return fakeHead.next;
    }

  };

  public abstract ListNode<Integer> solve(ListNode<Integer> head);

  public static class TestRemoveDuplicatesFromSortedListII {

    ListNode<Integer> head = new ListNode<>(1);

    {
      head.next = new ListNode<>(1);
      head.next.next = new ListNode<>(1);
      head.next.next.next = new ListNode<>(2);
      head.next.next.next.next = new ListNode<>(3);
      head.next.next.next.next.next = new ListNode<>(4);
      head.next.next.next.next.next.next = new ListNode<>(4);
    }

    @Test
    public void testSolutions() {
      for (RemoveDuplicatesFromSortedListII sol : RemoveDuplicatesFromSortedListII.values()) {
        Z.verify(new Integer[] {2, 3}, sol.solve(head));
      }
    }

  }

}
