package utils;

import linkedlist.ListNode;

public class Z {

  public static void println(Object obj) {
    System.out.println(obj);
  }

  public static void print(Object obj) {
    System.out.print(obj);
  }

  public static void println() {
    System.out.println();
  }

  public static void printLinkedList(ListNode head) {
    while (head != null) {
      print(head + " -> ");
      head = head.next;
    }
    print("null");
  }
}
