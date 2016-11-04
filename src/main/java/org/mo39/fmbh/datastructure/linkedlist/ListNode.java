package org.mo39.fmbh.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * There are several characteristics of Linked List that needs attention.<br>
 * 1. It's lack of constant time access;<br>
 * 2. Recursion;<br>
 * 3. Deleting a node;<br>
 * 4. Runner technique.<br>
 *
 * @author Jihan Chen
 *
 */
public class ListNode<T> {

  /**
   * The value of this ListNode
   */
  public T val;

  /**
   * The variable member that holds the next ListNode.
   */
  public ListNode<T> next;

  public ListNode() {}

  public ListNode(T val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

  /**
   * Take this ListNode as the head and remove all duplicate elements.<br>
   * Two ListNode are considered duplicate when and only when {@link #val} are equal.
   *
   * @param sol
   */
  public void removeDuplicate(RemoveDuplicateSol sol) {
    sol.solve(this);
  }

  /**
   * Take this ListNode as the head and remove all duplicate elements.<br>
   * Two ListNode are considered duplicate when and only when {@link #val} are equal.
   * <p>
   * Note: {@link RemoveDuplicateSol#SET_SOLUTION} is used.
   *
   * @param sol
   */
  public void removeDuplicate() {
    RemoveDuplicateSol.SET_SOLUTION.solve(this);
  }

  /**
   * Take this ListNode as the head and find the last k element in this linked list.
   *
   * @param sol
   * @param k
   * @return
   */
  public ListNode<T> findLastKElement(FindLastKElementSol sol, int k) {
    return sol.solve(this, k);
  }

  /**
   * Given a Predicate, take this ListNode as the head and make all nodes that satisfied this
   * Predicate come first and all others follow by. Stable.
   * <p>
   * Dummy head is a useful technique.<br>
   * The end state after loop needs more process.<br>
   *
   * @param val
   * @return new head for this linked list
   */
  public ListNode<T> partition(Predicate<ListNode<T>> p) {
    ListNode<T> head = this;
    ListNode<T> head1 = new ListNode<T>();
    ListNode<T> head2 = new ListNode<T>();
    ListNode<T> head1Copy = head1;
    ListNode<T> head2Copy = head2;
    while (head != null) {
      if (p.test(head)) {
        head1.next = head;
        head1 = head1.next;
      } else {
        head2.next = head;
        head2 = head2.next;
      }
      head = head.next;
    }
    head2.next = null;
    head1.next = head2Copy.next;
    return head1Copy.next;
  }

  public enum RemoveDuplicateSol {

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
  }

  public enum FindLastKElementSol {

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
     * This solution will go over the linked list twice. For the first time this will find the
     * length of the linked list. For the second time use count to find the element.
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

  }



  public static class TestListNode {

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
      head.removeDuplicate(RemoveDuplicateSol.SET_SOLUTION);
      verifyNoDuplicate(head);
    }

    @Test
    public void testRemoveDuplicateInPlaceSolution() {
      head.removeDuplicate(RemoveDuplicateSol.INPLACE_SOLUTION);
      verifyNoDuplicate(head);
    }

    @Test
    public void testFindLastKElementRecursiveSolution() {
      Assert.assertEquals((Integer) 5,
          head.findLastKElement(FindLastKElementSol.RECURSIVE_SOLUTION, 3).val);
    }

    @Test
    public void testFindLastKElementIterativeSolution() {
      Assert.assertEquals((Integer) 5,
          head.findLastKElement(FindLastKElementSol.ITERATIVE_SOLUTION, 3).val);
    }

    @Test
    public void testPartition() {
      Z.verifyListNodes(new Integer[] {1, 3, 0, 4, 3, 7, 6, 5, 8, 5},
          head.partition(node -> node.val < 5));

    }

  }

}
