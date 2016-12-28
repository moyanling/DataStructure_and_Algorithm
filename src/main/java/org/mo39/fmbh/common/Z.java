package org.mo39.fmbh.common;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.mo39.fmbh.datastructure.binarytree.TreeNode;
import org.mo39.fmbh.datastructure.binarytree.TreeNode.LevelOrderSol;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

import com.google.common.base.Joiner;

public class Z {

  public static void printnb(Object obj) {
    System.out.print(obj);
  }

  public static void print() {
    System.out.println();
  }

  public static void print(Object obj) {
    System.out.println(String.valueOf(obj));
  }

  public static <T> void print(T[] arr) {
    print(Arrays.toString(arr));
  }

  public static void print(int[] arr) {
    print(Arrays.toString(arr));
  }

  public static <T> void print(ListNode<T> head) {
    while (head != null) {
      printnb(head + " -> ");
      head = head.next;
    }
    print("null");
  }

  public static <T> void print(Stack<T> stack) {
    while (!stack.isEmpty()) {
      printnb(stack.pop() + " -> ");
    }
    print("null");
  }

  public static <T> void printTreeBfs(TreeNode<T> root) {
    root.bfs(LevelOrderSol.ITERATIVE_SOLUTION_WITH_NULL).stream()
        .forEach(l -> print(Joiner.on(',').join(l.stream()
            .map(o -> o == null ? '#' : String.valueOf(o)).collect(Collectors.toList()))));
  }

  /**
   * Helper function that swaps two elements at position i and j in an array.
   *
   * @param arr
   * @param i
   * @param j
   */
  public static <T> void swap(T[] arr, int i, int j) {
    if (i == j) return;
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * Helper function that swaps two elements at position i and j in an array.
   *
   * @param arr
   * @param i
   * @param j
   */
  public static void swap(int[] arr, int i, int j) {
    if (i == j) return;
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * Helper function that swaps two elements at position i and j in an ArrayList.
   *
   * @param arr
   * @param i
   * @param j
   */
  public static <T> void swap(List<T> list, int i, int j) {
    if (i == j) return;
    list.set(j, list.set(i, list.get(j)));
  }

  /**
   * Assert that one linked list is equal to a given array.
   *
   * @param expected
   * @param head
   */
  public static <T> void verify(T[] expected, ListNode<T> head) {
    for (int i = 0; i < expected.length; i++) {
      Assert.assertEquals(expected[i], head.val);
      head = head.next;
    }
    Assert.assertNull(head);
  }

  /**
   * Assert that two binary trees are equal.
   * <p>
   * Two binary tree are considered equal if they are structurally identical and the nodes have the
   * same value.
   * <p>
   * {@link TreeNode.LevelOrderSol#ITERATIVE_SOLUTION_WITH_NULL} can also be used as a solution. It
   * is not a fast solution but is valid (pass all the test cases on leetcode).
   *
   * @param root1
   * @param root2
   */
  public static <T> void verify(TreeNode<T> p, TreeNode<T> q) {
    if (p == null || q == null) {
      Assert.assertEquals(p, q);
      return;
    }
    Assert.assertEquals(p.val, q.val);
    verify(p.left, q.left);
    verify(p.right, q.right);
  }

}
