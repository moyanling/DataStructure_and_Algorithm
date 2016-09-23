package linkedlist;

import utils.TestData;
import utils.Z;

public class Problem2_3 {

  public static void main(String[] args) {
    Z.printLinkedList(new TestData().head);
    Z.println();
    Z.printLinkedList(new Problem2_3().linkedListPartition(new TestData().head, 5));
  }

  /**
   * In place O(n)<br>
   * Key point is to take care of the junction and the secondHead outside the loop
   * 
   * @param head
   * @param val
   * @return
   */
  public ListNode linkedListPartition(ListNode head, int val) {
    if (head == null) return null;
    ListNode toRet = null;
    ListNode junction = null;
    ListNode firstHead = new ListNode(-1);
    ListNode secondHead = new ListNode(-2);
    while (head != null) {
      if (head.val < val) {
        firstHead.next = head;
        firstHead = firstHead.next;
        if (toRet == null) toRet = firstHead;
      } else {
        secondHead.next = head;
        secondHead = secondHead.next;
        if (junction == null) junction = secondHead;
      }
      head = head.next;
    }
    secondHead.next = null;
    firstHead.next = junction;
    return toRet;
  }

}
