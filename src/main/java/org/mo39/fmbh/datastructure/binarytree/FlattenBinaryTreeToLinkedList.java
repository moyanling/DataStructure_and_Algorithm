package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * 
 * 
 * For example,
 * Given
 * 
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * 
 * 
 * 
 * The flattened tree should look like:
 * 
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * 
 * 
 * click to show hints.
 * 
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child points
 * to the next node of a pre-order traversal.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/">Flatten Binary
 *      Tree To Linked List</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FlattenBinaryTreeToLinkedList {

  SOLUTION {

    @Override
    public void solve(TreeNode root) {
      if (root == null) return;
      TreeNode right = root.right;
      solve(root.left);
      root.right = root.left;
      root.left = null;
      while (root.right != null) {
        root = root.right;
      }
      root.right = right;
      solve(right);
    }

  };

  public abstract void solve(TreeNode root);

  public static class TestFlattenBinaryTreeToLinkedList {

    TreeNode expected = new TreeNode(1);

    {
      expected.right = new TreeNode(2);
      expected.right.right = new TreeNode(3);
      expected.right.right.right = new TreeNode(4);
      expected.right.right.right.right = new TreeNode(5);
      expected.right.right.right.right.right = new TreeNode(6);
    }

    @Test
    public void testSolutions() {
      for (FlattenBinaryTreeToLinkedList sol : FlattenBinaryTreeToLinkedList.values()) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        sol.solve(root);
        Z.verify(expected, root);
      }
    }

  }

}
