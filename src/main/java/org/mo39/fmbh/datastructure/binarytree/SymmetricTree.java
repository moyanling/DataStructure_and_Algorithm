package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).<br/>For example, this binary tree [1,2,2,3,4,4,3] is symmetric:<br/>    1<br/>   / \<br/>  2   2<br/> / \ / \<br/>3  4 4  3<br/>But the following [1,2,2,null,3,null,3]  is not:<br/>    1<br/>   / \<br/>  2   2<br/>   \   \<br/>   3    3<br/><br/>Note:<br/>Bonus points if you could solve it both recursively and iteratively.
 * @see <a href="https://leetcode.com/problems/symmetric-tree/">Symmetric Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SymmetricTree {

  RECURSIVE_SOLUTION {

    @Override
    public boolean solve(TreeNode<Integer> root) {
      if (root == null) return true;
      return recur(root.left, root.right);
    }

    public boolean recur(TreeNode<Integer> left, TreeNode<Integer> right) {
      if (left == null && right == null) return true;
      if (left == null || right == null) return false;
      return left.val == right.val && recur(left.left, right.right)
          && recur(left.right, right.left);
    }

  },

  /**
   * LTE
   * 
   */
  BAD_ITERATIVE_SOLUTION {

    @Override
    public boolean solve(TreeNode<Integer> root) {
      if (root == null) return true;
      List<TreeNode<Integer>> pre = new ArrayList<>();
      pre.add(root);
      while (pre.size() != 0) {
        boolean allNull = pre.size() == 1 ? false : true;
        for (int i = 0; i < pre.size() / 2; i++) {
          TreeNode<Integer> left = pre.get(i);
          TreeNode<Integer> right = pre.get(pre.size() - 1 - i);
          if (left != null && right != null) {
            allNull = false;
            if (left.val != right.val) return false;
          } else if (!(left == null && right == null)) return false;
        }
        if (allNull) break;
        List<TreeNode<Integer>> curr = new ArrayList<>();
        for (TreeNode<Integer> node : pre) {
          if (node != null) {
            curr.add(node.left);
            curr.add(node.right);
          } else {
            curr.add(null);
            curr.add(null);
          }
        }
        pre = curr;
      }
      return true;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public boolean solve(TreeNode<Integer> root) {
      Queue<TreeNode<Integer>> q = new LinkedList<TreeNode<Integer>>();
      if (root == null) return true;
      q.add(root.left);
      q.add(root.right);
      while (q.size() > 1) {
        TreeNode<Integer> left = q.poll(), right = q.poll();
        if (left == null && right == null) continue;
        if (left == null ^ right == null) return false;
        if (left.val != right.val) return false;
        q.add(left.left);
        q.add(right.right);
        q.add(left.right);
        q.add(right.left);
      }
      return true;
    }


  };

  public abstract boolean solve(TreeNode<Integer> root);

  public static class TestSymmetricTree {

    private TreeNode<Integer> isSymmetric = new TreeNode<>(1);
    private TreeNode<Integer> notSymmetric = new TreeNode<>(1);

    @Before
    public void before() {
      isSymmetric.left = new TreeNode<>(2);
      isSymmetric.right = new TreeNode<>(2);
      isSymmetric.left.left = new TreeNode<>(3);
      isSymmetric.right.right = new TreeNode<>(3);
      // ---------
      notSymmetric.left = new TreeNode<>(2);
      notSymmetric.right = new TreeNode<>(2);
      notSymmetric.left.right = new TreeNode<>(3);
      notSymmetric.right.right = new TreeNode<>(3);
    }

    @Test
    public void testSolutions() {
      Assert.assertTrue(RECURSIVE_SOLUTION.solve(isSymmetric));
      Assert.assertTrue(BAD_ITERATIVE_SOLUTION.solve(isSymmetric));
      Assert.assertFalse(RECURSIVE_SOLUTION.solve(notSymmetric));
      Assert.assertFalse(BAD_ITERATIVE_SOLUTION.solve(notSymmetric));
    }


  }

}