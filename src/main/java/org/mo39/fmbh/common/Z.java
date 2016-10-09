package org.mo39.fmbh.common;

import java.util.Stack;

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

  public static void printLinkedList(ListNode head) {
    while (head != null) {
      printnb(head + " -> ");
      head = head.next;
    }
    printnb("null");
  }

  public static <T> void printStack(Stack<T> stack) {
    while (!stack.isEmpty()) {
      printnb(stack.pop() + " -> ");
    }
    printnb("null");
  }

  /**
   * Helper function that swaps two elements at position i and j.
   *
   * @param arr
   * @param i
   * @param j
   */
  public static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
