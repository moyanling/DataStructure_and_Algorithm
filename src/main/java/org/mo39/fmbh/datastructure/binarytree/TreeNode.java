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

  public List<T> InOrderTraversal(InOrderTraversalSol sol) {
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
     * This way is pushing every node into the stack and controlled to walk through the tree from
     * root to left then right. This is not taking full advantage of stack.
     */
    ITERATIVE_SOLUTION_WITH_ROOT_STORED() {

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

    },

    /**
     * One of the best implementations. Take full advantage of stack to iterative over a tree.
     */
    ITERATIVE_SOLUTION_WITH_BOTH_CHILDREN_STORED() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
          root = stack.pop();
          toRet.add(root.val);
          if (root.right != null) stack.push(root.right);
          if (root.left != null) stack.push(root.left);
        }
        return toRet;
      }

    },

    /**
     * One advantage of this implementation is that the stack only stored the right child of the
     * node.
     */
    ITERATIVE_SOLUTION_WITH_RIGHT_CHILD_STORED() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        Stack<TreeNode<T>> rights = new Stack<>();
        while (root != null) {
          if (root.right != null) rights.push(root.right);
          toRet.add(root.val);
          root = root.left;
          if (root == null && !rights.isEmpty()) root = rights.pop();
        }
        return toRet;
      }

    };

    public abstract <T> List<T> solve(TreeNode<T> root);
  }

  private enum InOrderTraversalSol {

    RECURSIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root.left != null) toRet.addAll(solve(root.left));
        toRet.add(root.val);
        if (root.right != null) toRet.addAll(solve(root.right));
        return toRet;
      }

    },

    ITERATIVE_SOLUTION_WITH_RIGHT_AND_ROOT_STORED() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        Stack<T> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {

        }
        return null;
      }

    };

    public abstract <T> List<T> solve(TreeNode<T> root);

  }

  @SuppressWarnings("serial")
  public static class TestTreeNode {

    private TreeNode<Integer> root = new TestData().root;

    private List<List<Integer>> levelOrder = new ArrayList<List<Integer>>() {
      {
        add(Arrays.asList(new Integer[] {0}));
        add(Arrays.asList(new Integer[] {1, 2}));
        add(Arrays.asList(new Integer[] {3, 4, 5, 6}));
      }
    };

    private List<Integer> preOrder = new ArrayList<Integer>() {
      {
        addAll(Arrays.asList(new Integer[] {0, 1, 3, 4, 2, 5, 6}));
      }
    };

    private List<Integer> inOrder = new ArrayList<Integer>() {
      {
        addAll(Arrays.asList(new Integer[] {3, 1, 4, 0, 5, 2, 6}));
      }
    };


    @Test
    public void testLevelOrderTraversal() {
      Assert.assertEquals(levelOrder,
          root.levelOrderTraversal(LevelOrderTraversalSol.ITERATIVE_SOLUTION));
    }

    @Test
    public void testPreOrderTraversal() {
      Assert.assertEquals(preOrder,
          root.preOrderTraversal(PreOrderTraversalSol.RECRUSIVE_SOLUTION));
      Assert.assertEquals(preOrder,
          root.preOrderTraversal(PreOrderTraversalSol.ITERATIVE_SOLUTION_WITH_ROOT_STORED));
      Assert.assertEquals(preOrder, root
          .preOrderTraversal(PreOrderTraversalSol.ITERATIVE_SOLUTION_WITH_BOTH_CHILDREN_STORED));
      Assert.assertEquals(preOrder,
          root.preOrderTraversal(PreOrderTraversalSol.ITERATIVE_SOLUTION_WITH_RIGHT_CHILD_STORED));
    }

    @Test
    public void testInOrderTraversal() {
      Assert.assertEquals(inOrder, root.InOrderTraversal(InOrderTraversalSol.RECURSIVE_SOLUTION));
    }


  }


}
