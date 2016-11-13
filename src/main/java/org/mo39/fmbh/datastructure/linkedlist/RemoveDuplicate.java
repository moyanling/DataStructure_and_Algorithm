package org.mo39.fmbh.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;

/**
 * Remove all duplicate elements.<br>
 * Two ListNode are considered duplicate when and only when {@link #val} are equal.
 * 
 * @author Jihan Chen
 *
 */
public enum RemoveDuplicate {

  /**
   * Take advantage of Set. Concise but use extra space.
   *
   * @param head
   * @return
   */
  SET_SOLUTION() {

    @Override
    public <T> void solve(ListNode<T> head) {
      ListNode<T> pre = null;
      Set<T> set = new HashSet<>();
      while (head != null) {
        if (!set.add(head.val)) pre.next = head.next;
        else pre = head;
        head = head.next;
      }
    }

  },

  /**
   * This solution use combination to find duplicate, which takes <b>O(n^2)</b>.<br>
   * Another is to sort a this linked list then visit one by one, which takes O(nlog(n)).<br>
   * But the sorting one is rather trouble if swap value is not allowed.<br>
   * And maybe we are not allowed to change the structure of linked list.
   */
  INPLACE_SOLUTION() {

    /**
     * Here is the way to do this using combination.
     *
     * @param head
     * @return
     */
    @Override
    public <T> void solve(ListNode<T> head) {
      if (head.next == null) return;
      while (head != null) {
        ListNode<T> newHead = head.next;
        ListNode<T> pre = head;
        while (newHead != null) {
          if (newHead.val == head.val) pre.next = newHead.next;
          else pre = newHead;
          newHead = newHead.next;
        }
        head = head.next;
      }
    }

  };

  public abstract <T> void solve(ListNode<T> head);

  public static class TestRemoveDuplicateListNode {

    private TestData testData = new TestData();
    private ListNode<Integer> head = testData.head;

    private void verifyNoDuplicate(ListNode<Integer> head) {
      Set<Integer> set = new HashSet<>();
      while (head != null) {
        Assert.assertTrue(set.add(head.val));
        head = head.next;
      }
    }

    @Test
    public void testRemoveDuplicateSetSolution() {
      SET_SOLUTION.solve(head);
      verifyNoDuplicate(head);
    }

    @Test
    public void testRemoveDuplicateInPlaceSolution() {
      INPLACE_SOLUTION.solve(head);
      verifyNoDuplicate(head);
    }
  }

}
