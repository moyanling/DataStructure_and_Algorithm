package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * 
 * 
 * Note: Do not modify the linked list.
 * 
 * 
 * Follow up: Can you solve it without using extra space?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-cycle-ii/">Linked List Cycle II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LinkedListCycleII {

  SET_SOLUTION {

    @Override
    public <T> ListNode<T> handle(ListNode<T> head) {
      Set<ListNode<T>> set = new HashSet<>();
      while (head != null) {
        if (!set.add(head)) return head;
        head = head.next;
      }
      return null;
    }

  },


  RUNNER_TECHNIQUE {

    @Override
    public <T> ListNode<T> handle(ListNode<T> head) {
      ListNode<T> slowRunner = head;
      ListNode<T> fastRunner = head;
      while (fastRunner != null && fastRunner.next != null) {
        slowRunner = slowRunner.next;
        fastRunner = fastRunner.next.next;
        if (slowRunner == fastRunner) {
          while (slowRunner != head) {
            slowRunner = slowRunner.next;
            head = head.next;
          }
          return slowRunner;
        }
      }
      return null;
    }

  };

  protected abstract <T> ListNode<T> handle(ListNode<T> head);

  public <T> ListNode<T> solve(ListNode<T> node) {
    Assert.assertNotNull(node);
    return handle(node);
  }

}
