package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <br/>
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
 * all the values along the path equals the given sum.<br/>
 * For example:<br/>
 * Given the below binary tree and sum = 22,<br/>
 * 5<br/>
 * / \<br/>
 * 4 8<br/>
 * / / \<br/>
 * 11 13 4<br/>
 * / \ \<br/>
 * 7 2 1<br/>
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 * @see <a href="https://leetcode.com/problems/path-sum/">Path Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PathSum {

  RECURSIVE_SOLUTION_0 {

    private boolean result;

    @Override
    public boolean solve(TreeNode<Integer> root, int target) {
      if (root == null) return false;
      result = false;
      recur(root, 0, target);
      return result;
    }

    private void recur(TreeNode<Integer> root, int currSum, int target) {
      if (root == null) return;
      if (root.left == null && root.right == null && currSum + root.val == target) result = true;
      else {
        recur(root.left, currSum + root.val, target);
        recur(root.right, currSum + root.val, target);
      }
    }

  },

  RECURSIVE_SOLUTION_1 {

    @Override
    public boolean solve(TreeNode<Integer> root, int target) {
      if (root == null) return false;
      return recur(root, 0, target);
    }

    private boolean recur(TreeNode<Integer> root, int currSum, int target) {
      if (root == null) return false;
      if (root.left == null && root.right == null && currSum + root.val == target) return true;
      return recur(root.left, currSum + root.val, target)
          || recur(root.right, currSum + root.val, target);
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public boolean solve(TreeNode<Integer> root, int target) {
      if (root == null) return false;
      Stack<MyNode> stack = new Stack<>();
      stack.push(MyNode.valueOf(root));
      while (!stack.isEmpty()) {
        MyNode curr = stack.pop();
        if (curr.node.left == null && curr.node.right == null && curr.sum == target) return true;
        else {
          if (curr.node.left != null) stack.push(curr.update(curr.node.left));
          if (curr.node.right != null) stack.push(curr.update(curr.node.right));
        }
      }
      return false;
    }

  };

  private static class MyNode {

    public final TreeNode<Integer> node;
    public final Integer sum;

    private MyNode(TreeNode<Integer> node, int val) {
      this.node = node;
      this.sum = val;
    }

    public static MyNode valueOf(TreeNode<Integer> node) {
      return new MyNode(node, node.val);
    }

    public MyNode update(TreeNode<Integer> node) {
      return new MyNode(node, this.sum + node.val);
    }

  }

  public abstract boolean solve(TreeNode<Integer> root, int target);

  public static class TestPathSum {

    private int expected = 13;
    private int unexpected = 20;
    private TreeNode<Integer> root = new TestData().root;

    @Test
    public void testSolutions() {
      Assert.assertTrue(RECURSIVE_SOLUTION_0.solve(root, expected));
      Assert.assertTrue(RECURSIVE_SOLUTION_1.solve(root, expected));
      Assert.assertTrue(ITERATIVE_SOLUTION.solve(root, expected));
      // ---------
      Assert.assertFalse(RECURSIVE_SOLUTION_0.solve(root, unexpected));
      Assert.assertFalse(RECURSIVE_SOLUTION_1.solve(root, unexpected));
      Assert.assertFalse(ITERATIVE_SOLUTION.solve(root, unexpected));
    }

  }

}
