package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * 
 * 
 * return its zigzag level order traversal as:
 * 
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/">Binary
 *      Tree Zigzag Level Order Traversal</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryTreeZigzagLevelOrderTraversal {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(TreeNode root) {
      if (root == null) return new ArrayList<>();
      List<List<Integer>> result = new ArrayList<>();
      Deque<TreeNode> deque = new ArrayDeque<>();
      boolean leftToRight = true;
      deque.add(root);
      while (!deque.isEmpty()) {
        List<Integer> level = new ArrayList<>();
        for (int i = deque.size(); i > 0; i--) {
          if (leftToRight) {
            TreeNode node = deque.pollFirst();
            level.add(node.val);
            if (node.left != null) deque.addLast(node.left);
            if (node.right != null) deque.addLast(node.right);
          } else {
            TreeNode node = deque.pollLast();
            level.add(node.val);
            if (node.right != null) deque.addFirst(node.right);
            if (node.left != null) deque.addFirst(node.left);
          }
        }
        leftToRight = !leftToRight;
        result.add(level);
      }
      return result;
    }

  };

  public abstract List<List<Integer>> solve(TreeNode root);
}
