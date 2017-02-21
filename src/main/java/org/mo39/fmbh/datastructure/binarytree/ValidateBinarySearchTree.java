package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * 
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's
 * key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * Example 1:
 * 
 *     2
 *    / \
 *   1   3
 * 
 * Binary tree [2,1,3], return true.
 * 
 * 
 * Example 2:
 * 
 *     1
 *    / \
 *   2   3
 * 
 * Binary tree [1,2,3], return false.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/validate-binary-search-tree/">Validate Binary Search
 *      Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ValidateBinarySearchTree {

  SOLUTION {

    @Override
    public boolean solve(TreeNode root) {
      return recur(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean recur(TreeNode root, long minVal, long maxVal) {
      if (root == null) return true;
      if (root.val >= maxVal || root.val <= minVal) return false;
      return recur(root.left, minVal, root.val) && recur(root.right, root.val, maxVal);
    }

  };

  public abstract boolean solve(TreeNode root);

}
