package org.mo39.fmbh.algorithm.reservoirsampling;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

/**
 * <pre>
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 * 
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 * 
 * 
 * Example:
 * 
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * 
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should
 * have equal probability of returning.
 * solution.getRandom();
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-random-node/">Linked List Random Node</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LinkedListRandomNode {

  SOLUTION_0 {

    private Random ran;
    private List<Integer> list;

    @Override
    public void init(ListNode<Integer> head) {
      ran = new Random();
      list = new ArrayList<>();
      while (head != null) {
        list.add(head.val);
        head = head.next;
      }

    }

    @Override
    public int getRandom() {
      return list.get(ran.nextInt(list.size()));
    }

  },

  SOLUTION_1 {

    ListNode<Integer> head;
    Random ran;

    @Override
    public void init(ListNode<Integer> head) {
      this.head = head;
      ran = new Random();
    }

    @Override
    public int getRandom() {
      ListNode<Integer> cur = head, reservoir = head;
      for (int i = 1; cur != null; i++) {
        /*
         * <code>ran.nextInt(1 + i) <= 1</code> and <code>ran.nextInt(i) == 0</code> both correct.
         * Here 1 stands for the number of elements in the reservoir.
         */
        if (ran.nextInt(1 + i) <= 1) {
          /*
           * <code>reservoir.val = cur.val;</code> is wrong. It modifies the initial value of
           * reservoir in next getRandom();
           */
          reservoir = cur;
        }
        cur = cur.next;
      }
      return reservoir.val;
    }

  };

  public abstract void init(ListNode<Integer> head);

  public abstract int getRandom();


}
