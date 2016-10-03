package org.mo39.fmbh.datastructure.linkedlist;

import org.mo39.fmbh.common.TestData;

public class Problem2_7 {

	public static void main(String[] args) {
		ListNode head = new TestData().head;
		System.err.println(new Problem2_7().isPalidrome0(head));
	}

	/**
	 * Use slow and fast runner then there are two case.<br>
	 * If the linked list is odd, then slow stops before the mid and fast stops
	 * at the last node<br>
	 * If the linked list is even, then slow stops before the center and fast
	 * stops at null.<br>
	 * Stack is a good idea to go through a linked list backward. But, if an
	 * extra data structure is allowed, why not do it ArrayList and do random
	 * access?<br>
	 * NO extra data structures in LinkedList. Though stack is indeed a good
	 * idea.
	 * 
	 * @param head
	 * @return
	 */
	public boolean isPalidrome0(ListNode head) {
		origin = head;
		travelLinkedList(head);
		return result;
	}

	private boolean result = true;
	private ListNode origin;

	private ListNode travelLinkedList(ListNode head) {
		if (head == null) return head;
		travelLinkedList(head.next);
		if (head != null) if (origin.val != head.val) result = false;
		origin = origin.next;
		return head;
	}

}
