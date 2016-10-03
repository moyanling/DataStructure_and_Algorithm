package org.mo39.fmbh.datastructure.linkedlist;

import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Since it's decribed like "In the middle of a singly linked list", so the
 * given should not be the last one. Then some edge case can be omitted.
 *
 * @author Jihan Chen
 *
 */
public class Problem2_3 {

	public static void main(String[] args) {
		TestData td = new TestData();
		ListNode head = td.head;
		ListNode node = td.node5;
		Z.printLinkedList(head);
		Z.print();
		Z.printLinkedList(node);
		Z.print();
		new Problem2_3().removeSpecifiedNode(node);
		Z.printLinkedList(head);
	}

	public void removeSpecifiedNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

}
