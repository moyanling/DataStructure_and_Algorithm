package org.mo39.fmbh.datastructure.linkedlist;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.common.classes.RandomListNode;

/**
 * <pre>
 * 
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 * 
 * 
 * 
 * Return a deep copy of the list.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/copy-list-with-random-pointer/">Copy List With Random
 *      Pointer</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CopyListWithRandomPointer {

  BAD_SOLUTION {

    @Override
    public RandomListNode solve(RandomListNode head) {
      RandomListNode newHead = copyNext(head, null), headCopy = head, newHeadCopy = newHead;
      for (; head != null; head = head.next, newHead = newHead.next) {
        RandomListNode ptr1 = headCopy, ptr2 = newHeadCopy, dest = head.random;
        for (; ptr1 != dest; ptr1 = ptr1.next, ptr2 = ptr2.next);
        newHead.random = ptr2;
      }
      return newHeadCopy;
    }

    RandomListNode copyNext(RandomListNode head, RandomListNode cur) {
      if (head == null) return null;
      cur = new RandomListNode(head.label);
      cur.next = copyNext(head.next, cur.next);
      return cur;
    }

  },

  SOLUTION_0 {

    @Override
    public RandomListNode solve(RandomListNode head) {
      Map<RandomListNode, List<RandomListNode>> map = new HashMap<>();
      RandomListNode result = recur(head, null, map), cur = result;
      for (; cur != null; cur = cur.next, head = head.next) {
        if (map.containsKey(head)) {
          for (RandomListNode node : map.get(head)) {
            node.random = cur;
          }
        }
      }
      return result;
    }

    RandomListNode recur(RandomListNode head, RandomListNode cur,
        Map<RandomListNode, List<RandomListNode>> map) {
      if (head == null) return null;
      if (!map.containsKey(head.random)) map.put(head.random, new ArrayList<>());
      cur = new RandomListNode(head.label);
      map.get(head.random).add(cur);
      cur.next = recur(head.next, cur.next, map);
      return cur;
    }

  },

  SOLUTION_1 {

    @Override
    public RandomListNode solve(RandomListNode head) {
      if (head == null) return null;
      Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
      RandomListNode node = head;
      while (node != null) {
        map.put(node, new RandomListNode(node.label));
        node = node.next;
      }
      node = head;
      while (node != null) {
        map.get(node).next = map.get(node.next);
        map.get(node).random = map.get(node.random);
        node = node.next;
      }
      return map.get(head);
    }

  },

  SOLUTION_2 {

    @Override
    public RandomListNode solve(RandomListNode head) {
      // TODO
      // https://discuss.leetcode.com/topic/7594/a-solution-with-constant-space-complexity-o-1-and-linear-time-complexity-o-n
      return null;
    }

  };

  public abstract RandomListNode solve(RandomListNode head);

  public static class TestCopyListWithRandomPointer {

    RandomListNode head = new RandomListNode(1);

    {
      head.next = new RandomListNode(2);
      head.next.next = new RandomListNode(3);
    }

    @Test
    public void testSolutions() {}

  }

}
