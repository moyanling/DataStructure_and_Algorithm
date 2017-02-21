package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/">Convert
 *      Sorted Array To Binary Search Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ConvertSortedArrayToBinarySearchTree {

  RECURSIVE_SOLUTION {

    @Override
    public TreeNode solve(int[] arr) {
      return recur(arr, 0, arr.length - 1);
    }

    private TreeNode recur(int[] arr, int low, int high) {
      if (low > high) return null;
      int mid = low + (high - low) / 2;
      TreeNode node = new TreeNode(arr[mid]);
      node.left = recur(arr, low, mid - 1);
      node.right = recur(arr, mid + 1, high);
      return node;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public TreeNode solve(int[] arr) {
      Stack<MyNode> stack = new Stack<>();
      int mid = 0 + (arr.length - 1 - 0) / 2;
      TreeNode root = new TreeNode(arr[mid]);
      MyNode MyRoot = new MyNode(0, arr.length - 1, root);
      stack.push(MyRoot);
      while (!stack.isEmpty()) {
        MyNode curr = stack.pop();
        int oldMid = curr.start + (curr.end - curr.start) / 2;
        if (oldMid - 1 >= curr.start) {
          mid = curr.start + (oldMid - 1 - curr.start) / 2;
          root = new TreeNode(arr[mid]);
          stack.push(new MyNode(curr.start, oldMid - 1, root));
          curr.node.left = root;
        }
        if (oldMid + 1 <= curr.end) {
          mid = oldMid + 1 + (curr.end - oldMid - 1) / 2;
          root = new TreeNode(arr[mid]);
          stack.push(new MyNode(oldMid + 1, curr.end, root));
          curr.node.right = root;
        }
      }
      return MyRoot.node;
    }
  };

  public static class MyNode {
    TreeNode node;
    int start;
    int end;

    public MyNode(int start, int end, TreeNode node) {
      this.start = start;
      this.end = end;
      this.node = node;
    }
  }

  public abstract TreeNode solve(int[] arr);

}
