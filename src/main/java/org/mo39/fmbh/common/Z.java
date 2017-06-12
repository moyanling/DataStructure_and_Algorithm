package org.mo39.fmbh.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.mo39.fmbh.datastructure.binarytree.TreeNode;
import org.mo39.fmbh.datastructure.binarytree.TreeNode.LevelOrderSol;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

import com.google.common.base.Joiner;

public class Z {

  // ----------------------------------------
  // ------------ Printers ------------------
  // ----------------------------------------

  public static void printnb(Object obj) {
    System.out.print(obj);
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

  public static void print(double[] arr) {
    print(Arrays.toString(arr));
  }

  public static <T> void print(ListNode<T> head) {
    while (head != null) {
      printnb(head + " -> ");
      head = head.next;
    }
    print("null");
  }

  public static void print(TreeNode root) {
    if (root == null) return;
    root.bfs(LevelOrderSol.ITERATIVE_SOLUTION_WITH_NULL).stream()
        .forEach(l -> print(Joiner.on(',').join(l.stream()
            .map(o -> o == null ? '#' : String.valueOf(o)).collect(Collectors.toList()))));
  }

  // ----------------------------------------
  // --------- Matrix Printers --------------
  // ----------------------------------------

  public static void print(int[][] matrix) {
    Z.print("[");
    Arrays.stream(matrix).forEach(arr -> Z.print(" " + Arrays.toString(arr) + ","));
    Z.print("]");
  }

  public static void print(boolean[][] matrix) {
    Z.print("[");
    Arrays.stream(matrix).forEach(arr -> Z.print(" " + Arrays.toString(arr) + ","));
    Z.print("]");
  }

  public static void print(double[][] matrix) {
    Z.print("[");
    Arrays.stream(matrix).forEach(arr -> Z.print(" " + Arrays.toString(arr) + ","));
    Z.print("]");
  }

  public static void print(char[][] matrix) {
    Z.print("[");
    Arrays.stream(matrix).forEach(arr -> Z.print(" " + Arrays.toString(arr) + ","));
    Z.print("]");
  }

  public static void print(List<List<Integer>> matrix) {
    Z.print("[");
    matrix.stream().forEach(list -> Z.print(" " + list + ","));
    Z.print("]");
  }

  // ----------------------------------------
  // ------------ Swappers ------------------
  // ----------------------------------------

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
  public static void swap(char[] arr, int i, int j) {
    if (i == j) return;
    char temp = arr[i];
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
    arr[i] = arr[i] ^ arr[j]; // now a is 6 and b is 4
    arr[j] = arr[i] ^ arr[j]; // now a is 6 but b is 2 (original value of a)
    arr[i] = arr[i] ^ arr[j];
  }

  /**
   * Helper function to reverse the array of a certain range. Inclusive start and exclusive end.
   * 
   * @param arr
   * @param start
   * @param end
   */
  public static void reverse(int[] arr, int start, int end) {
    if (start == end) return;
    for (int i = start; i < (start + end) / 2; i++) {
      swap(arr, i, end - (i - start) - 1);
    }
  }

  /**
   * Helper function to reverse the array of a certain range. Inclusive start and exclusive end.
   * 
   * @param arr
   * @param start
   * @param end
   */
  public static void reverse(char[] arr, int start, int end) {
    if (start == end) return;
    for (int i = start; i < (start + end) / 2; i++) {
      swap(arr, i, end - (i - start) - 1);
    }
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

  // ----------------------------------------
  // ------------ Verifies ------------------
  // ----------------------------------------

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
  public static <T> void verify(TreeNode p, TreeNode q) {
    if (p == null || q == null) {
      Assert.assertEquals(p, q);
      return;
    }
    Assert.assertEquals(p.val, q.val);
    verify(p.left, q.left);
    verify(p.right, q.right);
  }

}
