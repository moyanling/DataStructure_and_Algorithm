package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

/**
 * <pre>
 * Given a singly linked list where elements are sorted in ascending order, convert
 * it to a height balanced BST.
 * </pre>
 * @see <a href="https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/">Convert Sorted List To Binary Search Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ConvertSortedListToBinarySearchTree {

  SOLUTION {

    @Override
    public TreeNode<Integer> solve(ListNode<Integer> head) {
      if (head == null) return null;
      return recur(head, null);
    }

    public TreeNode<Integer> recur(ListNode<Integer> head, ListNode<Integer> tail) {
      ListNode<Integer> slow = head;
      ListNode<Integer> fast = head;
      if (head == tail) return null;
      while (fast != tail && fast.next != tail) {
        fast = fast.next.next;
        slow = slow.next;
      }
      TreeNode<Integer> root = new TreeNode<>(slow.val);
      root.left = recur(head, slow);
      root.right = recur(slow.next, tail);
      return root;
    }
  };

  public abstract TreeNode<Integer> solve(ListNode<Integer> head);

}