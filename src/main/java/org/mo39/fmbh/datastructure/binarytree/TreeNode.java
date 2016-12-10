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

  public final T val;
  public TreeNode<T> left;
  public TreeNode<T> right;

  public TreeNode(T val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

  /**
   * Breadth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<List<T>> bfs(LevelOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Deepth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<T> dfs(PreOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Deepth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<T> dfs(InOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Deepth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<T> dfs(PostOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Get the depth of this TreeNode.
   * 
   * @return depth
   */
  public int depth() {
    return depthOf(this);
  }

  /**
   * A tree is balanced if the heights of two sub-trees of any node never differ by more than one.
   * 
   * @return true if isBalanced. false otherwise.
   */
  public boolean isBalanced() {
    return isBalanced(this);
  }

  /**
   * Nice and clean one line recursive solution. DFS is not a good iterative solution for this
   * problem.
   * 
   * @param root
   * @return
   */
  public static <T> int depthOf(TreeNode<T> root) {
    return root == null ? 0 : Math.max(depthOf(root.left), depthOf(root.right)) + 1;
  }

  private boolean isBalanced(TreeNode<T> root) {
    return root == null || Math.abs(depthOf(root.left) - depthOf(root.right)) <= 1
        && isBalanced(root.left) && isBalanced(root.right);
  }

  public enum IsBalancedSol {

    /**
     * Time complexity is <b>O(nlog(n))</b>.
     */
    TOP_DOWN_METHOD {

      @Override
      public <T> boolean solve(TreeNode<T> root) {
        return root == null || Math.abs(depthOf(root.left) - depthOf(root.right)) <= 1
            && solve(root.left) && solve(root.right);
      }

    },

    /**
     * Time complexity is <b>O(n)</b>.
     */
    BOTTOM_UP_METHOD {

      private boolean result;

      @Override
      public <T> boolean solve(TreeNode<T> root) {
        result = true;
        recur(root);
        return result;
      }

      public <T> int recur(TreeNode<T> root) {
        if (root == null || !result) return 0;
        int l = recur(root.left);
        int r = recur(root.right);
        if (Math.abs(l - r) > 1) result = false;
        return 1 + Math.max(l, r);
      }

    };

    public abstract <T> boolean solve(TreeNode<T> root);

  }


  public enum LevelOrderSol {

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

    },

    /**
     * Level order traversal with null value. Presents a complete tree.
     * 
     */
    ITERATIVE_SOLUTION_WITH_NULL() {

      @Override
      public <T> List<List<T>> solve(TreeNode<T> root) {
        List<List<T>> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
          List<T> level = new ArrayList<>();
          int size = queue.size();
          boolean nullLevel = true;
          for (int i = 0; i < size; i++) {
            TreeNode<T> node = queue.poll();
            level.add(node == null ? null : node.val);
            if (node == null) {
              queue.offer(null);
              queue.offer(null);
              continue;
            }
            nullLevel = false;
            queue.offer(node.left);
            queue.offer(node.right);
          }
          if (nullLevel) return toRet;
          toRet.add(level);
        }
        return toRet;
      }

    };

    public abstract <T> List<List<T>> solve(TreeNode<T> root);

  }

  public enum PreOrderSol {

    /**
     * Neat and clean recursive solution.
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

  public enum InOrderSol {

    /**
     * Neat and clean recursive solution.
     */
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
        Stack<TreeNode<T>> stack = new Stack<>();
        do {
          while (root != null) {
            stack.push(root);
            root = root.left;
          }
          root = stack.pop();
          toRet.add(root.val);
          root = root.right;
        } while (stack.size() != 0 || root != null);
        return toRet;
      }

    };

    public abstract <T> List<T> solve(TreeNode<T> root);

  }

  public enum PostOrderSol {

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
     * The key point is to add the check of <code>pre</code> when going from the root to either left
     * or right so that when the node goes back to it's parent, it won't repeat the route.
     *
     */
    ITERATIVE_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        List<T> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> pre = null;
        do {
          while (root != null && pre != root) {
            stack.push(root);
            pre = root;
            root = root.left;
          }
          if (stack.peek().right != null && pre != stack.peek().right) {
            root = stack.peek().right;
            continue;
          }
          root = stack.pop();
          toRet.add(root.val);
          pre = root;
        } while (stack.size() != 0);
        return toRet;
      }
    },

    /**
     * This is a smart method to get the correct result of the post-order traversal.<br>
     * Pay attention to the usage of LinkedList.
     */
    REVERSED_PREORDER_SOLUTION() {

      @Override
      public <T> List<T> solve(TreeNode<T> root) {
        LinkedList<T> toRet = new LinkedList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        do {
          if (root != null) {
            stack.push(root);
            toRet.addFirst(root.val);
            root = root.right;
          } else {
            root = stack.pop();
            root = root.left;
          }
        } while (stack.size() != 0 || root != null);
        return toRet;
      }

    };

    public abstract <T> List<T> solve(TreeNode<T> root);

  }

  @SuppressWarnings("serial")
  public static class TestTreeNode {

    private TreeNode<Integer> root = new TestData().root;
    private int depth = 4;

    private List<List<Integer>> levelOrder = new ArrayList<List<Integer>>() {
      {
        add(Arrays.asList(new Integer[] {0}));
        add(Arrays.asList(new Integer[] {1, 2}));
        add(Arrays.asList(new Integer[] {3, 4}));
        add(Arrays.asList(new Integer[] {5, 6, 7}));
      }
    };

    private List<List<Integer>> levelOrderWithNull = new ArrayList<List<Integer>>() {
      {
        add(Arrays.asList(new Integer[] {0}));
        add(Arrays.asList(new Integer[] {1, 2}));
        add(Arrays.asList(new Integer[] {null, 3, null, 4}));
        add(Arrays.asList(new Integer[] {null, null, 5, 6, null, null, null, 7}));
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
    public void testLevelOrder() {
      Assert.assertEquals(levelOrder, root.bfs(LevelOrderSol.ITERATIVE_SOLUTION));
      Assert.assertEquals(levelOrderWithNull, root.bfs(LevelOrderSol.ITERATIVE_SOLUTION_WITH_NULL));
    }

    @Test
    public void testPreOrder() {
      Assert.assertEquals(preOrder, root.dfs(PreOrderSol.RECRUSIVE_SOLUTION));
      Assert.assertEquals(preOrder, root.dfs(PreOrderSol.ITERATIVE_SOLUTION_WITH_ROOT_STORED));
      Assert.assertEquals(preOrder,
          root.dfs(PreOrderSol.ITERATIVE_SOLUTION_WITH_BOTH_CHILDREN_STORED));
      Assert.assertEquals(preOrder,
          root.dfs(PreOrderSol.ITERATIVE_SOLUTION_WITH_RIGHT_CHILD_STORED));
    }

    @Test
    public void testInOrder() {
      Assert.assertEquals(inOrder, root.dfs(InOrderSol.RECURSIVE_SOLUTION));
      Assert.assertEquals(inOrder, root.dfs(InOrderSol.ITERATIVE_SOLUTION));
    }

    @Test
    public void testPostOrder() {
      Assert.assertEquals(postOrder, root.dfs(PostOrderSol.RECURSIVE_SOLUTION));
      Assert.assertEquals(postOrder, root.dfs(PostOrderSol.ITERATIVE_SOLUTION));
      Assert.assertEquals(postOrder, root.dfs(PostOrderSol.REVERSED_PREORDER_SOLUTION));
    }

    @Test
    public void testDepth() {
      Assert.assertEquals(depth, root.depth());
    }

    @Test
    public void testIsBalanced() {
      Assert.assertFalse(root.isBalanced());
      Assert.assertTrue(new TestData().getCompleteTree().isBalanced());
    }

  }


}
