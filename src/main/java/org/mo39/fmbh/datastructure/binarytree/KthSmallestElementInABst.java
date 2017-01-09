package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.PriorityQueue;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary search tree, write a function kthSmallest to find the kth smallest
 * element in it.
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to
 * find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/">Kth Smallest Element
 *      In A Bst</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum KthSmallestElementInABst {

  HEAP_SOLUTION {

    @Override
    public int solve(TreeNode<Integer> root, int k) {
      PriorityQueue<Integer> q = new PriorityQueue<>((n1, n2) -> n2 - n1);
      recur(root, q, k);
      return q.poll();
    }

    private void recur(TreeNode<Integer> root, PriorityQueue<Integer> q, int k) {
      if (root == null) return;
      q.add(root.val);
      if (q.size() > k) {
        q.poll();
      }
      recur(root.left, q, k);
      recur(root.right, q, k);
    }

  },

  IN_ORDER_TRAVERSAL_SOLUTION {

    private int count, result;

    @Override
    public int solve(TreeNode<Integer> root, int k) {
      count = 0;
      recur(root, k);
      return result;
    }

    private void recur(TreeNode<Integer> root, int k) {
      if (root == null) return;
      recur(root.left, k);
      if (++count == k) result = root.val;
      recur(root.right, k);
    }

  };

  public abstract int solve(TreeNode<Integer> root, int k);

}
