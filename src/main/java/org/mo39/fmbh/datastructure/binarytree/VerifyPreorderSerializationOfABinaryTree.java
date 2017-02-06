package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter
 * a non-null node, we record the node's value. If it is a null node, we record
 * using a sentinel value such as #.
 * 
 * 
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * 
 * 
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 * where # represents a null node.
 * 
 * 
 * Given a string of comma separated values, verify whether it is a correct preorder
 * traversal serialization of a binary tree. Find an algorithm without reconstructing
 * the tree.
 * 
 * Each comma separated value in the string must be either an integer or a character
 * '#' representing null pointer.
 * 
 * You may assume that the input format is always valid, for example it could
 * never contain two consecutive commas such as "1,,3".
 * 
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * Example 2:
 * "1,#"
 * Return false
 * Example 3:
 * "9,#,#,1"
 * Return false
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/">
 *      Verify Preorder Serialization Of A Binary Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum VerifyPreorderSerializationOfABinaryTree {

  /**
   * Nice recursion.
   */
  SOLUTION {

    @Override
    public boolean solve(String preorder) {
      String[] arr = preorder.split(",");
      Integer root = recur(arr, 0);
      return root == null ? false : root == arr.length ? true : false;
    }

    Integer recur(String[] arr, int start) {
      if (start >= arr.length) return null;
      if (arr[start++].equals("#")) return start;// Verify root.
      Integer left = recur(arr, start);
      if (left == null || left > arr.length) return null;
      Integer right = recur(arr, left);
      if (right == null || right > arr.length) return null;
      return right;
    }

  },

  /**
   * Consider it as a graph with in and out degree. If we consider null as leaves, then
   * <ol>
   * <li>all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except
   * root.</li>
   * <li>all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).</li>
   * </ol>
   * Suppose we try to build this tree. During building, we record the difference between out degree
   * and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by
   * 1, because the node provides an in degree. If the node is not null, we increase diff by 2,
   * because it provides two out degrees. If a serialization is correct, diff should never be
   * negative and diff will be zero when finished.
   */
  GRAPH_SOLUTION {

    @Override
    public boolean solve(String preorder) {
      String[] nodes = preorder.split(",");
      int diff = 1;
      for (String node : nodes) {
        if (--diff < 0) return false;
        if (node.charAt(0) != '#') diff += 2;
      }
      return diff == 0;
    }

  };

  public abstract boolean solve(String preorder);

  public static class TestVerifyPreorderSerializationOfABinaryTree {

    String preorder = "1,#";
    boolean expected = false;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(preorder));
    }

  }

}
