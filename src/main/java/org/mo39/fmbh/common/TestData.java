package org.mo39.fmbh.common;

import java.util.Stack;

import org.mo39.fmbh.datastructure.binarytree.TreeNode;
import org.mo39.fmbh.datastructure.linkedlist.ListNode;

public class TestData {

  public int[] intArr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

  public Integer[] integerArr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

  public Integer[] noDupulicateIntegerArr0 = {7, 3, 2, 4, 5, 9};

  public Integer[] noDupulicateIntegerArr1 =
      {13, -3, -25, 20, -16, -23, 18, -7, 12, -5, -22, 15, -4, 7};

  public int[][] matrix0 = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 15, 16, 6}, {10, 9, 8, 7}};

  public int[][] matrix1 = {{1, 2, 3, 4}, {12, 0, 14, 5}, {11, 15, 3, 4}, {10, 9, 0, 7}};

  public ListNode<Integer> node0 = new ListNode<>(1);
  public ListNode<Integer> node1 = new ListNode<>(3);
  public ListNode<Integer> node2 = new ListNode<>(0);
  public ListNode<Integer> node3 = new ListNode<>(7);
  public ListNode<Integer> node4 = new ListNode<>(6);
  public ListNode<Integer> node5 = new ListNode<>(5);
  public ListNode<Integer> node6 = new ListNode<>(8);
  public ListNode<Integer> node7 = new ListNode<>(5);
  public ListNode<Integer> node8 = new ListNode<>(4);
  public ListNode<Integer> node9 = new ListNode<>(3);

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
  }

  public ListNode<Integer> head = node0;

  public ListNode<Integer> getCircleLinkedList() {
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

  public TreeNode root = new TreeNode(0);
  public TreeNode treeNode1 = new TreeNode(1);
  public TreeNode treeNode2 = new TreeNode(2);
  public TreeNode treeNode3 = new TreeNode(3);
  public TreeNode treeNode4 = new TreeNode(4);
  public TreeNode treeNode5 = new TreeNode(5);
  public TreeNode treeNode6 = new TreeNode(6);
  public TreeNode treeNode7 = new TreeNode(7);

  {
    root.left = treeNode1;
    root.right = treeNode2;
    treeNode1.right = treeNode3;
    treeNode2.right = treeNode4;
    treeNode3.left = treeNode5;
    treeNode3.right = treeNode6;
    treeNode4.right = treeNode7;
  }

  public TreeNode getCompleteTree() {
    root.left = treeNode1;
    root.right = treeNode2;
    treeNode1.left = treeNode3;
    treeNode1.right = treeNode4;
    treeNode2.left = treeNode5;
    treeNode2.right = treeNode6;
    treeNode3.left = treeNode7;
    return root;
  }

  public ListNode<Integer> sortedLinkedList0 = new ListNode<Integer>(3) {
    {
      next = new ListNode<Integer>(7) {
        {
          next = new ListNode<Integer>(10) {
            {
              next = new ListNode<Integer>(31);
            }
          };
        }
      };
    }
  };

  public ListNode<Integer> sortedLinkedList1 = new ListNode<Integer>(1) {
    {
      next = new ListNode<Integer>(2) {
        {
          next = new ListNode<Integer>(5) {
            {
              next = new ListNode<Integer>(6) {
                {
                  next = new ListNode<Integer>(8);
                }
              };
            }
          };
        }
      };
    }
  };

  public TreeNode bstRoot = new TreeNode(6);
  public TreeNode bstTreeNode1 = new TreeNode(2);
  public TreeNode bstTreeNode2 = new TreeNode(8);
  public TreeNode bstTreeNode3 = new TreeNode(0);
  public TreeNode bstTreeNode4 = new TreeNode(4);
  public TreeNode bstTreeNode5 = new TreeNode(7);
  public TreeNode bstTreeNode6 = new TreeNode(9);
  public TreeNode bstTreeNode7 = new TreeNode(3);
  public TreeNode bstTreeNode8 = new TreeNode(5);

  {
    bstRoot.left = bstTreeNode1;
    bstRoot.right = bstTreeNode2;
    bstTreeNode1.left = bstTreeNode3;
    bstTreeNode1.right = bstTreeNode4;
    bstTreeNode4.left = bstTreeNode7;
    bstTreeNode1.right = bstTreeNode8;
    bstTreeNode2.left = bstTreeNode5;
    bstTreeNode2.right = bstTreeNode6;
  }

}
