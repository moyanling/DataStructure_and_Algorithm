package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to
 * bottom, column by column). If two nodes are in the same row and column, the order should be from
 * left to right.
 * Examples:
 * 
 * 
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 * 
 * 
 * 
 * return its vertical order traversal as:
 * 
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 * 
 * 
 * 
 * Given binary tree [3,9,8,4,0,1,7],
 * 
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 * 
 * 
 * 
 * return its vertical order traversal as:
 * 
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 * 
 * 
 * 
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 * 
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 * 
 * 
 * 
 * return its vertical order traversal as:
 * 
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-vertical-order-traversal/">Binary Tree
 *      Vertical Order Traversal</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryTreeVerticalOrderTraversal {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();
      if (root == null) return result;
      TreeMap<Integer, List<Integer>> map = new TreeMap<>();
      Queue<Wapper> queue = new LinkedList<>();
      queue.offer(Wapper.of(root, 0));
      while (!queue.isEmpty()) {
        for (int i = queue.size(); i > 0; i--) {
          Wapper w = queue.poll();
          if (map.get(w.index) == null) map.put(w.index, new ArrayList<>());
          map.get(w.index).add(w.node.val);
          if (w.node.left != null) queue.offer(Wapper.of(w.node.left, w.index - 1));
          if (w.node.right != null) queue.offer(Wapper.of(w.node.right, w.index + 1));
        }
      }
      while (!map.isEmpty()) {
        result.add(map.pollFirstEntry().getValue());
      }
      return result;
    }

  };

  static class Wapper {

    TreeNode node;
    int index;

    public static Wapper of(TreeNode node, int index) {
      Wapper wapper = new Wapper();
      wapper.index = index;
      wapper.node = node;
      return wapper;
    }

  }

  public abstract List<List<Integer>> solve(TreeNode root);

  public static class TestBinaryTreeVerticalOrderTraversal {

    TreeNode root = new TreeNode(3);
    List<List<Integer>> expected = new ArrayList<>();

    {
      root.left = new TreeNode(9);
      root.right = new TreeNode(20);
      root.right.left = new TreeNode(15);
      root.right.right = new TreeNode(7);
      expected.add(Lists.newArrayList(9));
      expected.add(Lists.newArrayList(3, 15));
      expected.add(Lists.newArrayList(20));
      expected.add(Lists.newArrayList(7));
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(root));
    }

  }

}
