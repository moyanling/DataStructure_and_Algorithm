package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of
 * two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes v and w as the lowest node in T that has both
 * v and w as descendants (where we allow a node to be a descendant of itself).”
 * 
 *         _______6______
 *        /              \
 *     ___2__          ___8__
 *    /      \        /      \
 *    0      _4       7       9
 *          /  \
 *          3   5
 * 
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another
 * example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
 * according to the LCA definition.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">
 *      Lowest Common Ancestor Of A Binary Search Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LowestCommonAncestorOfABinarySearchTree {

  BAD_SOLUTION {

    @Override
    public TreeNode<Integer> solve(TreeNode<Integer> root, TreeNode<Integer> p,
        TreeNode<Integer> q) {
      Stack<TreeNode<Integer>> stack = new Stack<>();
      trace(root, p, t -> {
        stack.push(t);
      });
      while (!stack.isEmpty()) {
        if (trace(p = stack.pop(), q, t -> {
          return;
        }) != null) return p;
      }
      return null;
    }

    public TreeNode<Integer> trace(TreeNode<Integer> root, TreeNode<Integer> target,
        Consumer<TreeNode<Integer>> consumer) {
      TreeNode<Integer> curr = root;
      while (curr != null) {
        consumer.accept(curr);
        int compareResult = target.val - curr.val;
        if (compareResult == 0) return curr;
        if (compareResult < 0) curr = curr.left;
        else curr = curr.right;
      }
      return null;
    }

  },

  SOLUTION {

    @Override
    public TreeNode<Integer> solve(TreeNode<Integer> root, TreeNode<Integer> p,
        TreeNode<Integer> q) {
      if (p.val < root.val && q.val < root.val) return solve(root.left, p, q);
      if (p.val > root.val && q.val > root.val) return solve(root.right, p, q);
      return root;
    }

  },

  ONE_LINER {

    @Override
    public TreeNode<Integer> solve(TreeNode<Integer> root, TreeNode<Integer> p,
        TreeNode<Integer> q) {
      return (root.val - p.val) * (root.val - q.val) < 1 ? root
          : solve(p.val < root.val ? root.left : root.right, p, q);
    }

  };

  public abstract TreeNode<Integer> solve(TreeNode<Integer> root, TreeNode<Integer> p,
      TreeNode<Integer> q);


  public static class TestLowestCommonAncestorOfABinarySearchTree {

    private TestData td = new TestData();
    private TreeNode<Integer> root = td.bstRoot;

    @Test
    public void testSolutions() {
      Assert.assertSame(td.bstRoot, BAD_SOLUTION.solve(root, td.bstTreeNode1, td.bstTreeNode2));
      Assert.assertSame(td.bstRoot, SOLUTION.solve(root, td.bstTreeNode1, td.bstTreeNode2));
      Assert.assertSame(td.bstRoot, ONE_LINER.solve(root, td.bstTreeNode1, td.bstTreeNode2));
    }
  }

}
