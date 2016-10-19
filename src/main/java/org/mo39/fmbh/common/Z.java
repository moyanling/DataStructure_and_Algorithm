package org.mo39.fmbh.common;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

public class Z {

  public static void printnb(Object obj) {
    System.out.print(obj);
  }

  public static void print(Object obj) {
    System.out.println(obj);
  }

  public static void print() {
    System.out.println();
  }

  public static <T> void print(T[] arr) {
    print(Arrays.toString(arr));
  }

  public static void print(int[] arr) {
    print(Arrays.toString(arr));
  }

  public static <T> void printLinkedList(ListNode<T> head) {
    while (head != null) {
      printnb(head + " -> ");
      head = head.next;
    }
    print("null");
  }

  public static <T> void printStack(Stack<T> stack) {
    while (!stack.isEmpty()) {
      printnb(stack.pop() + " -> ");
    }
    printnb("null");
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

  public static void verifyAllNodes(Integer[] expected, ListNode<Integer> head) {
    for (int i = 0; i < expected.length; i++) {
      Assert.assertEquals(expected[i], head.val);
      head = head.next;
    }
    Assert.assertNull(head);
  }

}
