package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/">Linked List Cycle</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum LinkedListCycle {

  /**
   * This solution takes advantage of {@link ListNode} since it's not overriding
   * {@link Object#equals(Object)} method and thus, when adding to a set collection, reference will
   * be compared.
   * 
   */
  SET_SOLUTION {

    @Override
    public <T> boolean handle(ListNode<T> node) {
      Set<ListNode<T>> set = new HashSet<>();
      while (node != null) {
        if (!set.add(node)) return true;
        node = node.next;
      }
      return false;
    }

  },

  RUNNER_TECHNIQUE {

    @Override
    public <T> boolean handle(ListNode<T> node) {
      ListNode<T> slowRunner = node;
      ListNode<T> fastRunner = node;
      while (fastRunner != null && fastRunner.next != null) {
        slowRunner = slowRunner.next;
        fastRunner = fastRunner.next.next;
        if (slowRunner == fastRunner) return true;
      }
      return false;
    }

  },

  /**
   * This one is a nice solution but will modify the structure of the linked list.
   * 
   */
  POINT_TO_HEAD_SOLUTION {

    @Override
    protected <T> boolean handle(ListNode<T> head) {
      ListNode<T> node = head;
      while (head != null) {
        if (head.next == node) return true;
        ListNode<T> temp = head.next;
        head.next = node;
        head = temp;
      }
      return false;
    }

  };

  protected abstract <T> boolean handle(ListNode<T> node);

  public <T> boolean solve(ListNode<T> node) {
    Assert.assertNotNull(node);
    return handle(node);
  }

  public static class TestLinkedListCycle {

    private ListNode<Integer> cycle = new TestData().getCircleLinkedList();
    private ListNode<Integer> nonCycle = new TestData().head;

    @Test
    public void testCycle() {
      Assert.assertTrue(SET_SOLUTION.solve(cycle));
      Assert.assertTrue(RUNNER_TECHNIQUE.solve(cycle));
      Assert.assertTrue(POINT_TO_HEAD_SOLUTION.solve(cycle));
    }

    @Test
    public void testNonCycle() {
      Assert.assertFalse(SET_SOLUTION.solve(nonCycle));
      Assert.assertFalse(RUNNER_TECHNIQUE.solve(nonCycle));
      Assert.assertFalse(POINT_TO_HEAD_SOLUTION.solve(nonCycle));
    }

  }

}
