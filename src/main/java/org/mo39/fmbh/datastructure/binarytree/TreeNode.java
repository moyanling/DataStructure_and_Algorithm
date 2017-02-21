package org.mo39.fmbh.datastructure.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;

import com.google.common.collect.Lists;


public class TreeNode {

  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }

  /**
   * Breadth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<List<Integer>> bfs(LevelOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Deepth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<Integer> dfs(PreOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Deepth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<Integer> dfs(InOrderSol sol) {
    return sol.solve(this);
  }

  /**
   * Deepth-first search traversal taking this TreeNode as root.
   *
   * @param sol
   * @return
   */
  public List<Integer> dfs(PostOrderSol sol) {
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
  public static int depthOf(TreeNode root) {
    return root == null ? 0 : Math.max(depthOf(root.left), depthOf(root.right)) + 1;
  }

  private boolean isBalanced(TreeNode root) {
    return root == null || Math.abs(depthOf(root.left) - depthOf(root.right)) <= 1
        && isBalanced(root.left) && isBalanced(root.right);
  }

  public enum IsBalancedSol {

    /**
     * Time complexity is <b>O(nlog(n))</b>.
     */
    TOP_DOWN_METHOD {

      @Override
      public boolean solve(TreeNode root) {
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
      public boolean solve(TreeNode root) {
        result = true;
        recur(root);
        return result;
      }

      public int recur(TreeNode root) {
        if (root == null || !result) return 0;
        int l = recur(root.left);
        int r = recur(root.right);
        if (Math.abs(l - r) > 1) result = false;
        return 1 + Math.max(l, r);
      }

    };

    public abstract boolean solve(TreeNode root);

  }


  public enum LevelOrderSol {

    /**
     * Pay attention to the usage of Queue.<br>
     * Pay attention to how T are added to the result.
     *
     */
    ITERATIVE_SOLUTION() {

      @Override
      public List<List<Integer>> solve(TreeNode root) {
        List<List<Integer>> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
          List<Integer> level = new ArrayList<>();
          int size = queue.size();
          for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
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

      private int count = 0;

      @Override
      public List<List<Integer>> solve(TreeNode root) {
        List<List<Integer>> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
          List<Integer> level = new ArrayList<>();
          int size = queue.size();
          boolean nullLevel = true;
          for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
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
          if (count++ > 20) return toRet;
        }
        return toRet;
      }

    };

    public abstract List<List<Integer>> solve(TreeNode root);

  }

  public enum PreOrderSol {

    /**
     * Neat and clean recursive solution.
     */
    RECRUSIVE_SOLUTION() {

      @Override
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
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
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Stack<TreeNode> stack = new Stack<>();
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
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Stack<TreeNode> stack = new Stack<>();
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
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Stack<TreeNode> rights = new Stack<>();
        while (root != null) {
          if (root.right != null) rights.push(root.right);
          toRet.add(root.val);
          root = root.left;
          if (root == null && !rights.isEmpty()) root = rights.pop();
        }
        return toRet;
      }

    };

    public abstract List<Integer> solve(TreeNode root);
  }

  public enum InOrderSol {

    /**
     * Neat and clean recursive solution.
     */
    RECURSIVE_SOLUTION() {

      @Override
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
        if (root == null) return toRet;
        toRet.addAll(solve(root.left));
        toRet.add(root.val);
        toRet.addAll(solve(root.right));
        return toRet;
      }

    },

    ITERATIVE_SOLUTION() {

      @Override
      public List<Integer> solve(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        do {
          while (root != null) {
            stack.push(root);
            root = root.left;
          }
          root = stack.pop();
          result.add(root.val);
          root = root.right;
        } while (stack.size() != 0 || root != null);
        return result;
      }

    };

    public abstract List<Integer> solve(TreeNode root);

  }

  public enum PostOrderSol {

    RECURSIVE_SOLUTION() {

      @Override
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
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
      public List<Integer> solve(TreeNode root) {
        List<Integer> toRet = new ArrayList<>();
        if (root == null) return toRet;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
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
      public List<Integer> solve(TreeNode root) {
        LinkedList<Integer> toRet = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
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

    public abstract List<Integer> solve(TreeNode root);

  }

  @SuppressWarnings("serial")
  public static class TestTreeNode {

    private TreeNode root = new TestData().root;
    private int depth = 4;

    private List<List<Integer>> levelOrder = new ArrayList<List<Integer>>() {
      {
        add(Lists.newArrayList(0));
        add(Lists.newArrayList(1, 2));
        add(Lists.newArrayList(3, 4));
        add(Lists.newArrayList(5, 6, 7));
      }
    };

    private List<List<Integer>> levelOrderWithNull = new ArrayList<List<Integer>>() {
      {
        add(Lists.newArrayList(0));
        add(Lists.newArrayList(1, 2));
        add(Lists.newArrayList(null, 3, null, 4));
        add(Lists.newArrayList(null, null, 5, 6, null, null, null, 7));
      }
    };

    private List<Integer> preOrder = Lists.newArrayList(0, 1, 3, 5, 6, 2, 4, 7);
    private List<Integer> inOrder = Lists.newArrayList(1, 5, 3, 6, 0, 2, 4, 7);
    private List<Integer> postOrder = Lists.newArrayList(5, 6, 3, 1, 7, 4, 2, 0);

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
