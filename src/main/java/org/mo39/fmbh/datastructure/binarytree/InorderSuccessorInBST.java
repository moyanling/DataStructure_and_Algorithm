package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * 
 * Note: If the given node has no in-order successor in the tree, return null.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/inorder-successor-in-bst/">Inorder Successor In
 *      Bst</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum InorderSuccessorInBST {

  RECURSIVE_BRUTE_FORCE {

    TreeNode<?> result;

    @SuppressWarnings("unchecked")
    @Override
    public <T> TreeNode<T> solve(TreeNode<T> root, TreeNode<T> p) {
      recur(root, p, new ArrayList<>());
      return (TreeNode<T>) result;
    }

    private <T> void recur(TreeNode<T> root, TreeNode<T> p, List<TreeNode<T>> list) {
      if (root == null) return;
      recur(root.left, p, list);
      list.add(root);
      if (list.size() - 2 > -1 && list.get(list.size() - 2) == p) {
        result = root;
        return;
      }
      recur(root.right, p, list);
    }

  },

  ITERATIVE_SOLUTION {


    @Override
    public <T> TreeNode<T> solve(TreeNode<T> root, TreeNode<T> p) {
      LinkedList<TreeNode<T>> result = new LinkedList<>();
      Stack<TreeNode<T>> stack = new Stack<>();
      do {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        root = stack.pop();
        if (result.size() > 0 && result.getLast() == p) return root;
        result.add(root);
        root = root.right;
      } while (stack.size() != 0 || root != null);
      return null;
    }

  },

  SOLUTION {

    @Override
    public <T> TreeNode<T> solve(TreeNode<T> root, TreeNode<T> p) {
      // TODO https://discuss.leetcode.com/topic/25076/share-my-java-recursive-solution
      return null;
    }

  };

  public abstract <T> TreeNode<T> solve(TreeNode<T> root, TreeNode<T> p);

  public static class TestInorderSuccessorInBST {

    private TreeNode<Integer> root = new TreeNode<>(0);
    private TreeNode<Integer> p = new TreeNode<>(3);

    {
      root.left = new TreeNode<>(1);
      root.right = new TreeNode<>(2);
      root.left.right = p;
    }

    @Test
    public void testSolutions() {
      for (InorderSuccessorInBST sol : InorderSuccessorInBST.values()) {
        Assert.assertEquals(root, sol.solve(root, p));
      }
    }

  }

}
