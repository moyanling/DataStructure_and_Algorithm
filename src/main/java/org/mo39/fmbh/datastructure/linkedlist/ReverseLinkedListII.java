package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * 
 * 
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * 
 * 
 * return 1->4->3->2->5->NULL.
 * 
 * 
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-linked-list-ii/">Reverse Linked List II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ReverseLinkedListII {

  /**
   * Try make impl better.
   */
  SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> head, int m, int n) {
      if (m == n) return head;
      ListNode<Integer> fakeHead = new ListNode<>(-1);
      ListNode<Integer> pre = fakeHead, cur = head;
      ListNode<Integer> nodeM = null, preM = null, nodeN = null;
      fakeHead.next = head;
      int count = 1;
      for (; cur != null; count++) {
        if (count == m) {
          preM = pre;
          nodeM = cur;
        }
        if (m < count && count <= n) {
          ListNode<Integer> next = cur.next;
          cur.next = pre;
          pre = cur;
          cur = next;
        } else {
          pre = cur;
          cur = cur.next;
        }
        if (count == n) {
          nodeN = pre;
          break;
        }
      }
      if (cur == null) {
        preM.next = nodeN;
        nodeM.next = cur;
      } else {
        nodeM.next = cur;
        preM.next = pre;
      }
      return fakeHead.next;
    }

  };

  public abstract ListNode<Integer> solve(ListNode<Integer> head, int m, int n);

  public static class TestReverseLinkedListII {

    ListNode<Integer> head = new ListNode<>(1);
    int m = 1, n = 5;
    int[] expected = {1, 2, 3, 4, 5};

    {
      head.next = new ListNode<>(2);
      head.next.next = new ListNode<>(3);
      head.next.next.next = new ListNode<>(4);
      head.next.next.next.next = new ListNode<>(5);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(head, m, n));
    }

  }

}
