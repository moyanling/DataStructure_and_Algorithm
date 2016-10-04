package org.mo39.fmbh.datastructure.linkedlist.cc150;

import java.util.HashSet;
import java.util.Set;

import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

public class Problem2_1 {

	public static void main(String[] args) {
		Z.printLinkedList(new TestData().head);
		Z.print();
		Z.printLinkedList(new Problem2_1().removeDuplicate1(new TestData().head));
	}

	/**
	 * This code itself is nice.<br>
	 * But no idea how to do this without extra space.<br>
	 * If do this without considering efficiency, there are two ways to do so.
	 * <br>
	 * One is like combination. This is O(n^2/2) and is relatively easier.<br>
	 * Another is to sort a this linked list then visit one by one. This is
	 * O(n(log(n)+1)).<br>
	 * But the sorting one is rather trouble if swap value is not allowed.<br>
	 * And maybe we are not allowed to change the structure of linked list.
	 *
	 * @param head
	 * @return
	 */
	public ListNode removeDuplicate0(ListNode head) {
		ListNode toRet = head;
		ListNode pre = null;
		Set<Integer> set = new HashSet<>();
		while (head != null) {
			if (!set.add(head.val)) pre.next = head.next;
			else pre = head;
			head = head.next;
		}
		return toRet;
	}

	/**
	 * Here is the way to do this using combination.
	 *
	 * @param head
	 * @return
	 */
	public ListNode removeDuplicate1(ListNode head) {
		ListNode toRet = head;
		if (head.next == null) return toRet;
		while (head != null) {
			ListNode newHead = head.next;
			ListNode pre = head;
			while (newHead != null) {
				if (newHead.val == head.val) pre.next = newHead.next;
				else pre = newHead;
				newHead = newHead.next;
			}
			head = head.next;
		}
		return toRet;
	}

}
