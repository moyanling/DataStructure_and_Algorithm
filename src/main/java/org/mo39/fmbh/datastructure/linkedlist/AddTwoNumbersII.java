package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You are given two linked lists representing two non-negative numbers. The most
 * significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists
 * is not allowed.
 * 
 * 
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/add-two-numbers-ii/">Add Two Numbers II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum AddTwoNumbersII {

  SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> l1, ListNode<Integer> l2) {
      Stack<Integer> s1 = new Stack<>();
      Stack<Integer> s2 = new Stack<>();
      while (l1 != null) {
        s1.push(l1.val);
        l1 = l1.next;
      }
      while (l2 != null) {
        s2.push(l2.val);
        l2 = l2.next;
      }
      int carry = 0;
      ListNode<Integer> tail = null, curr = null;
      while (s1.size() != 0 || s2.size() != 0 || carry != 0) {
        int digit = (s1.size() == 0 ? 0 : s1.pop()) + (s2.size() == 0 ? 0 : s2.pop()) + carry;
        curr = new ListNode<>(digit % 10);
        carry = digit / 10;
        curr.next = tail;
        tail = curr;
      }
      return curr;
    }

  };

  public abstract ListNode<Integer> solve(ListNode<Integer> l1, ListNode<Integer> l2);

}
