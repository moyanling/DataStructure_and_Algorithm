package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.CRACKING_THE_CODING_INTERVIEW;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * find the last k element in this linked list.
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(CRACKING_THE_CODING_INTERVIEW)
public enum FindLastKElement {

  /**
   * Count the number after recursion return.
   */
  RECURSIVE_SOLUTION() {

    private int count;
    private ListNode<?> toRet;

    private <T> void traverseLinkedList(ListNode<T> head, int k) {
      if (head == null) return;
      traverseLinkedList(head.next, k);
      count++;
      if (count == k) {
        toRet = head;
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ListNode<T> solve(ListNode<T> head, int k) {
      traverseLinkedList(head, k);
      return (ListNode<T>) toRet;
    }

  },

  /**
   * This solution will go over the linked list twice. For the first time this will find the length
   * of the linked list. For the second time use count to find the element.
   */
  ITERATIVE_SOLUTION() {

    @Override
    public <T> ListNode<T> solve(ListNode<T> head, int k) {
      int len = 0;
      ListNode<T> headCopy = head;
      while (head != null) {
        head = head.next;
        len++;
      }
      k = len - k;
      len = 0;
      while (headCopy != null) {
        headCopy = headCopy.next;
        if (++len == k) return headCopy;
      }
      return null;
    }

  };

  public abstract <T> ListNode<T> solve(ListNode<T> head, int k);

  public static class TestFindLastKElement {

    private TestData testData = new TestData();
    private ListNode<Integer> head = testData.head;

    @Test
    public void testFindLastKElementRecursiveSolution() {
      Assert.assertEquals((Integer) 5, RECURSIVE_SOLUTION.solve(head, 3).val);
      Assert.assertEquals((Integer) 5, ITERATIVE_SOLUTION.solve(head, 3).val);
    }

  }

}
