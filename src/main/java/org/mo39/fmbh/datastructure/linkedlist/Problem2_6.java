package org.mo39.fmbh.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

public class Problem2_6 {

	public static void main(String[] args) {
		TestData td = new TestData();
		Z.print(new Problem2_6().findTheHeadOfCircluarLinkedList0(td.getCircleLinkedList()));
	}

	/**
	 * Set is convenient to check duplicates when extra space is available.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode findTheHeadOfCircluarLinkedList0(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		while (head != null) {
			if (!set.add(head)) return head;
			head = head.next;
		}
		return null;
	}

	/**
	 * Slow and fast runner.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode findTheHeadOfCircluarLinkedList1(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) return slow;
		}
		return null;
	}

}
