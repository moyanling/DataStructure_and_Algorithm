package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <br/>
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <br/>
 * Note: Do not modify the linked list.<br/>
 * Follow up:<br/>
 * Can you solve it without using extra space?
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-cycle-ii/">Linked List Cycle II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LinkedListCycleII {

  RUNNER_TECHNIQUE {

    @Override
    public <T> boolean handle(ListNode<T> node) {
      ListNode<T> slowRunner = node;
      ListNode<T> fastRunner = node;
      while (fastRunner != null && fastRunner.next != null) {
        slowRunner = slowRunner.next;
        fastRunner = fastRunner.next.next;
        if (slowRunner == fastRunner) ; // TODO
      }
      return false;
    }

  };

  protected abstract <T> boolean handle(ListNode<T> node);

  public <T> boolean solve(ListNode<T> node) {
    Assert.assertNotNull(node);
    return handle(node);
  }

}
