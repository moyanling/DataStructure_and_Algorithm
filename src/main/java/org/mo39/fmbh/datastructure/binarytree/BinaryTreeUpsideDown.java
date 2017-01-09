package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * 
 * 
 * For example:
 * Given a binary tree {1,2,3,4,5},
 * 
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * 
 * 
 * 
 * return the root of the binary tree [4,5,2,#,#,3,1].
 * 
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1  
 * 
 * 
 * 
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 * OJ's Binary Tree Serialization:
 * 
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 * 
 * 
 * Here's an example:
 * 
 *    1
 *   / \
 *  2   3
 *     /
 *    4
 *     \
 *      5
 * 
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-upside-down/">Binary Tree Upside Down</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryTreeUpsideDown {

  RECURSIVE_SOLUTION_0 {

    @Override
    public <T> TreeNode<T> solve(TreeNode<T> root) {
      if (root == null || root.left == null) return root;
      root.left = solve(root.left);
      TreeNode<T> rightMost = root.left, left = root.left;
      while (rightMost.right != null) {
        rightMost = rightMost.right;
      }
      rightMost.left = root.right;
      rightMost.right = root;
      root.left = null;
      root.right = null;
      return left;
    }

  },

  RECURSIVE_SOLUTION_1 {

    @Override
    public <T> TreeNode<T> solve(TreeNode<T> root) {
      if (root == null || root.left == null) { return root; }
      TreeNode<T> newRoot = solve(root.left);
      root.left.left = root.right; // node 2 left children
      root.left.right = root; // node 2 right children
      root.left = null;
      root.right = null;
      return newRoot;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public <T> TreeNode<T> solve(TreeNode<T> root) {
      TreeNode<T> curr = root, next = null, temp = null, prev = null;
      while (curr != null) {
        next = curr.left;

        // swap node
        curr.left = temp;
        temp = curr.right;
        curr.right = prev;

        prev = curr;
        curr = next;
      }
      return prev;
    }

  };

  public abstract <T> TreeNode<T> solve(TreeNode<T> root);

  public static class TestBinaryTreeUpsideDown {

    private TreeNode<Integer> root = new TreeNode<>(1);
    private TreeNode<Integer> expected = new TreeNode<>(4);

    @Before
    public void before() {
      root.left = new TreeNode<>(2);
      root.right = new TreeNode<>(3);
      root.left.left = new TreeNode<>(4);
      root.left.right = new TreeNode<>(5);
      // ---------
      expected.left = new TreeNode<>(5);
      expected.right = new TreeNode<>(2);
      expected.right.left = new TreeNode<>(3);
      expected.right.right = new TreeNode<>(1);
    }

    @Test
    public void testSolutions() {
      Z.verify(expected, RECURSIVE_SOLUTION_0.solve(root));
      Z.verify(expected, RECURSIVE_SOLUTION_1.solve(root));
      Z.verify(expected, ITERATIVE_SOLUTION.solve(root));
    }

  }

}
