package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Sort a linked list in O(n log n) time using constant space complexity.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/sort-list/">Sort List</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SortList {

  /**
   * Pay attention to preSlow. There are two problems involved. </br>
   * 1. There cant be a fakeHead, otherwise the fakeHead may be sorted into list or hard to get rid
   * of it.</br>
   * 2. it does not matter wheather preSlow will be null or not because it is handled in merge
   * function.
   */
  SOLUTION {

    @Override
    public ListNode<Integer> solve(ListNode<Integer> head) {
      if (head == null || head.next == null) return head;
      ListNode<Integer> slow = head, fast = head, preSlow = null;
      for (; fast != null && fast.next != null; preSlow = slow, slow = slow.next, fast =
          fast.next.next);
      // Break them up then merge them together.
      preSlow.next = null;
      return MergeTwoSortedLists.SOLUTION.solve(solve(head), solve(slow));
    }

  };

  public abstract ListNode<Integer> solve(ListNode<Integer> head);

}
