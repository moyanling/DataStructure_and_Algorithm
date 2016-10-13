package org.mo39.fmbh.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;

import com.google.common.base.Preconditions;

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
public class ListNode {

  /**
   * The value of this ListNode
   */
  public int val;

  /**
   * The variable member that holds the next ListNode.
   */
  public ListNode next;

  public ListNode() {}

  public ListNode(int val) {
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
   * Take this ListNode as the head and find the last k element in this linked list.
   * 
   * @param sol
   * @param k
   * @return
   */
  public ListNode findLastKElement(FindLastKElementSol sol, int k) {
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
  public ListNode partition(Predicate<ListNode> p) {
    ListNode head = this;
    ListNode head1 = new ListNode();
    ListNode head2 = new ListNode();
    ListNode head1Copy = head1;
    ListNode head2Copy = head2;
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



  /**
   * Take this ListNode as the head and remove the input element.<br>
   * The input ListNode should not be the last element.
   * 
   * @param node
   */
  public static void delete(ListNode node) {
    Preconditions.checkArgument(node.next != null);
    node.val = node.next.val;
    node.next = node.next.next;
  }

  private enum RemoveDuplicateSol {

    /**
     * Take advantage of Set. Concise but use extra space.
     *
     * @param head
     * @return
     */
    SET_SOLUTION() {

      @Override
      public void solve(ListNode head) {
        ListNode pre = null;
        Set<Integer> set = new HashSet<>();
        while (head != null) {
          if (!set.add(head.val)) pre.next = head.next;
          else pre = head;
          head = head.next;
        }
      }

    },

    /**
     * This solution use combination to find duplicate, which takes O(n^2).<br>
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
      public void solve(ListNode head) {
        if (head.next == null) return;
        while (head != null) {
          ListNode newHead = head.next;
          ListNode pre = head;
          while (newHead != null) {
            if (newHead.val == head.val) pre.next = newHead.next;
            else pre = newHead;
            newHead = newHead.next;
          }
          head = head.next;
        }
      }

    };

    public abstract void solve(ListNode head);
  }

  private enum FindLastKElementSol {

    /**
     * Count the number after recursion return.
     */
    RECURSIVE_SOLUTION() {

      private int count;
      private ListNode toRet;

      private void traverseLinkedList(ListNode head, int k) {
        if (head == null) return;
        traverseLinkedList(head.next, k);
        count++;
        if (count == k) {
          toRet = head;
        }
      }

      @Override
      public ListNode solve(ListNode head, int k) {
        traverseLinkedList(head, k);
        return toRet;
      }

    },

    /**
     * This solution will go over the linked list twice. For the first time this will find the
     * length of the linked list. For the second time use count to find the element.
     */
    ITERATIVE_SOLUTION() {

      @Override
      public ListNode solve(ListNode head, int k) {
        int len = 0;
        ListNode headCopy = head;
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

    public abstract ListNode solve(ListNode head, int k);

  }



  public static class TestListNode {

    private TestData testData = new TestData();
    private ListNode head = testData.head;

    private void verifyNoDuplicate(ListNode head) {
      Set<Integer> set = new HashSet<>();
      while (head != null) {
        Assert.assertTrue(set.add(head.val));
        head = head.next;
      }
    }

    private void verifyAllNodes(int[] expected, ListNode head) {
      for (int i = 0; i < expected.length; i++) {
        Assert.assertEquals(expected[i], head.val);
        head = head.next;
      }
      Assert.assertNull(head);
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
      Assert.assertEquals(5, head.findLastKElement(FindLastKElementSol.RECURSIVE_SOLUTION, 3).val);
    }

    @Test
    public void testFindLastKElementIterativeSolution() {
      Assert.assertEquals(5, head.findLastKElement(FindLastKElementSol.ITERATIVE_SOLUTION, 3).val);
    }

    @Test
    public void testDelete() {
      ListNode.delete(head);
      verifyAllNodes(new int[] {3, 0, 7, 6, 5, 8, 5, 4, 3}, head);
    }

    @Test
    public void testPartition() {
      verifyAllNodes(new int[] {1, 3, 0, 4, 3, 7, 6, 5, 8, 5},
          head.partition(node -> node.val < 5));

    }

  }

}
