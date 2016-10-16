package org.mo39.fmbh.datastructure.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;


public class TreeNode<T> {

  public T val;
  public TreeNode<T> left;
  public TreeNode<T> right;

  public TreeNode(T val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

  public List<List<T>> levelOrderTraversal(LevelOrderTraversalSol sol) {
    return sol.solve(this);
  }

  public List<T> preOrderTraversal(PreOrderTraversalSol sol) {
    return sol.solve(this);
  }

  private enum LevelOrderTraversalSol {

    /**
     * Pay attention to the usage of Queue.<br>
     * Pay attention to how T are added to the result.
     * 
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> List<List<T>> solve(TreeNode<T> root) {
        List<List<T>> toRet = new ArrayList<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
          List<T> level = new ArrayList<>();
          int size = queue.size();
          for (int i = 0; i < size; i++) {
            TreeNode<T> node = queue.poll();
            level.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
          }
          toRet.add(level);
        }
        return toRet;
      }

    };

    public abstract <T> List<List<T>> solve(TreeNode<T> root);

  }

  private enum PreOrderTraversalSol {

    /**
     * Neat and clean.
     */
    RECRUSIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root == null) return toRet;
        toRet.add(root.val);
        toRet.addAll(solve(root.left));
        toRet.addAll(solve(root.right));
        return toRet;
      }

    },

    /**
     * //TODO other ways of iterative solution:<br>
     * https://discuss.leetcode.com/topic/6493/accepted-iterative-solution-in-java-using-stack/2
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        do {
          while (root != null) {
            toRet.add(root.val);
            stack.push(root);
            root = root.left;
          }
          root = stack.pop().right;
        } while (!stack.isEmpty() || root != null);
        return toRet;
      }

    };

    public abstract <T> List<T> solve(TreeNode<T> root);
  }

  public static class TestTreeNode {

    private TreeNode<Integer> root = new TestData().root;

    @SuppressWarnings("serial")
    private List<List<Integer>> levelOrder = new ArrayList<List<Integer>>() {
      {
        add(Arrays.asList(new Integer[] {0}));
        add(Arrays.asList(new Integer[] {1, 2}));
        add(Arrays.asList(new Integer[] {3, 4, 5, 6}));
      }
    };

    @SuppressWarnings("serial")
    private List<Integer> preOrder = new ArrayList<Integer>() {
      {
        addAll(Arrays.asList(new Integer[] {0, 1, 3, 4, 2, 5, 6}));
      }
    };


    @Test
    public void testLevelOrderTraversal() {
      Assert.assertEquals(levelOrder,
          root.levelOrderTraversal(LevelOrderTraversalSol.ITERATIVE_SOLUTION));
    }

    @Test
    public void testPreorderTraversal() {
      Assert.assertEquals(preOrder,
          root.preOrderTraversal(PreOrderTraversalSol.RECRUSIVE_SOLUTION));
      Assert.assertEquals(preOrder,
          root.preOrderTraversal(PreOrderTraversalSol.ITERATIVE_SOLUTION));
    }


  }



}
