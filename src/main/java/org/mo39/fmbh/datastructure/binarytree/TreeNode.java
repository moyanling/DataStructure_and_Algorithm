package org.mo39.fmbh.datastructure.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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

  public List<T> inOrderTraversal(InOrderTraversalSol sol) {
    return sol.solve(this);
  }

  public List<T> postOrderTraversal(PostOrderTraversalSol sol) {
    return sol.solve(this);
  }

  public enum LevelOrderTraversalSol {

    /**
     * Pay attention to the usage of Queue.<br>
     * Pay attention to how T are added to the result.
     *
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> List<List<T>> solve(TreeNode<T> root) {
        List<List<T>> toRet = new ArrayList<>();
        if (root == null) return toRet;
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

  public enum PreOrderTraversalSol {

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
     * This way is pushing every node into the stack and is controlled to walk through the tree from
     * root to left then right. This is not taking full advantage of stack.
     */
    ITERATIVE_SOLUTION_WITH_ROOT_STORED() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root == null) return toRet;
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
        if (root == null) return toRet;
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
        if (root == null) return toRet;
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

  public enum InOrderTraversalSol {

    RECURSIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root == null) return toRet;
        toRet.addAll(solve(root.left));
        toRet.add(root.val);
        toRet.addAll(solve(root.right));
        return toRet;
      }

    },

    ITERATIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Stack<TreeNode<T>> stack = new Stack<>();
        do {
          while (root != null) {
            stack.push(root);
            root = root.left;
          }
          root = stack.pop();
          toRet.add(root.val);
          root = root.right;
        } while (!stack.isEmpty() || root != null);
        return toRet;
      }

    };

    public abstract <T> List<T> solve(TreeNode<T> root);

  }

  public enum PostOrderTraversalSol {

    RECURSIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root == null) return toRet;
        toRet.addAll(solve(root.left));
        toRet.addAll(solve(root.right));
        toRet.add(root.val);
        return toRet;
      }

    },

    /**
     * //TODO
     *
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        return null;
      }
    },

    REVERSED_PREORDER_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        Deque<TreeNode<T>> deque = new ArrayDeque<>();
        do {
          while (root != null) {
            deque.push(root);
            toRet.add(root.val);
            root = root.left;
          }
          root = deque.pop();
          root = root.right;
        } while (deque.size() != 0 || root != null);
        return toRet;
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
        add(Arrays.asList(new Integer[] {3, 4}));
        add(Arrays.asList(new Integer[] {5, 6, 7}));
      }
    };

    private List<Integer> preOrder = new ArrayList<Integer>() {
      {
        addAll(Arrays.asList(new Integer[] {0, 1, 3, 5, 6, 2, 4, 7}));
      }
    };

    private List<Integer> inOrder = new ArrayList<Integer>() {
      {
        addAll(Arrays.asList(new Integer[] {1, 5, 3, 6, 0, 2, 4, 7}));
      }
    };

    private List<Integer> postOrder = new ArrayList<Integer>() {
      {
        addAll(Arrays.asList(new Integer[] {5, 6, 3, 1, 7, 4, 2, 0}));
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
      Assert.assertEquals(preOrder, // FIXME
          root.postOrderTraversal(PostOrderTraversalSol.REVERSED_PREORDER_SOLUTION));
    }

    @Test
    public void testInOrderTraversal() {
      Assert.assertEquals(inOrder, root.inOrderTraversal(InOrderTraversalSol.RECURSIVE_SOLUTION));
      Assert.assertEquals(inOrder, root.inOrderTraversal(InOrderTraversalSol.ITERATIVE_SOLUTION));
    }

    @Test
    public void testPostOrderTraversal() {
      Assert.assertEquals(postOrder,
          root.postOrderTraversal(PostOrderTraversalSol.RECURSIVE_SOLUTION));
      // Assert.assertEquals(postOrder,
      // root.postOrderTraversal(PostOrderTraversalSol.ITERATIVE_SOLUTION));
    }


  }


}
