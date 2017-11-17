package org.mo39.fmbh.datastructure.binarytree;

import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative
 * value, where each node in this tree has exactly two or zero sub-node. If the
 * node has two sub-nodes, then this node's value is the smaller value among its
 * two sub-nodes.
 *
 *
 *
 * Given such a binary tree, you need to output the second minimum value in the
 * set made of all the nodes' value in the whole tree.
 *
 *
 *
 * If no such second minimum value exists, output -1 instead.
 *
 *
 * Example 1:
 *
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 *
 *
 * Example 2:
 *
 * Input:
 *     2
 *    / \
 *   2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/">Second Minimum
 *     Node In A Binary Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SecondMinimumNodeInABinaryTree {
  SOLUTION {
    @Override
    public int findSecondMinimumValue(TreeNode root) {
      if (root == null || root.left == null) return -1;
      int left = root.left.val;
      int right = root.right.val;
      if (left < right) left = findSecondMinimumValue(root.left);
      else if (left > right) right = findSecondMinimumValue(root.right);
      else {
        left = findSecondMinimumValue(root.left);
        right = findSecondMinimumValue(root.right);
      }
      if (left == -1) return right;
      if (right == -1) return left;
      return Math.min(left, right);
    }
  };

  public abstract int findSecondMinimumValue(TreeNode root);
}
