package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/add-two-numbers/">Add Two Numbers</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum AddTwoNumbers {

  SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> l1, ListNode<Integer> l2) {
      int carry = 0;
      ListNode<Integer> fakeHead = new ListNode<>(-1), head = fakeHead;
      while (l1 != null || l2 != null || carry != 0) {
        int digit = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
        head.next = new ListNode<>(digit % 10);
        carry = digit / 10;
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
        head = head.next;
      }
      return fakeHead.next;
    }

  };

  public abstract ListNode<Integer> solve(ListNode<Integer> l1, ListNode<Integer> l2);

}
