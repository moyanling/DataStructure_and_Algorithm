package org.mo39.fmbh.common;

import java.util.Stack;

import org.mo39.fmbh.datastructure.linkedlist.ListNode;

public class TestData {

	public int[] intArr = new int[] { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };
	public Integer[] integerArr = new Integer[] { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };

	public int[][] matrix0 = new int[][] { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 15, 16, 6 }, { 10, 9, 8, 7 } };

	public int[][] matrix1 = new int[][] { { 1, 2, 3, 4 }, { 12, 0, 14, 5 }, { 11, 15, 3, 4 }, { 10, 9, 0, 7 } };

	public ListNode node0 = new ListNode(100);
	public ListNode node1 = new ListNode(5);
	public ListNode node2 = new ListNode(0);
	public ListNode node3 = new ListNode(3);
	public ListNode node4 = new ListNode(4);
	public ListNode node5 = new ListNode(5);
	public ListNode node6 = new ListNode(4);
	public ListNode node7 = new ListNode(3);
	public ListNode node8 = new ListNode(0);
	public ListNode node9 = new ListNode(5);
	public ListNode node10 = new ListNode(100);

	{
		node0.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;
		node9.next = node10;
	}

	public ListNode head = node0;

	public ListNode getCircleLinkedList() {
		node0.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node0;
		return node0;
	}

	public Stack<Integer> stack = new Stack<>();

	{
		stack.push(1);
		stack.push(3);
		stack.push(10);
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(5);
	}

}
