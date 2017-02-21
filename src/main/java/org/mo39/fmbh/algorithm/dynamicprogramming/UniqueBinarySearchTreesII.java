package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.datastructure.binarytree.TreeNode;

/**
 * <pre>
 * Given an integer n, generate all structurally unique BST's (binary search trees)
 * that store values 1...n.
 * 
 * 
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * </pre>
 * @see <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">Unique Binary Search Trees II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum UniqueBinarySearchTreesII {

  SOLUTION {

    @Override
    public List<TreeNode> solve(int n) {
      @SuppressWarnings("unchecked")
      List<TreeNode>[] memo = new List[n + 1];
      memo[0] = new ArrayList<TreeNode>();
      memo[0].add(null);
      for (int i = 1; i <= n; i++) {
        memo[i] = new ArrayList<TreeNode>();
        for (int j = 0; j < i; j++) {
          for (TreeNode left : memo[j]) {
            for (TreeNode right : memo[i - j - 1]) {
              TreeNode root = new TreeNode(j + 1);
              root.left = left;
              root.right = copyOf(right, j + 1);
              memo[i].add(root);
            }
          }
        }
      }
      return memo[n];
    }

    TreeNode copyOf(TreeNode root, int offset) {
      if (root == null) return null;
      TreeNode node = new TreeNode(root.val + offset);
      node.left = copyOf(root.left, offset);
      node.right = copyOf(root.right, offset);
      return node;
    }

  };

  public abstract List<TreeNode> solve(int n);

  public static class TestUniqueBinarySearchTreesII {

    int n = 1;

    @Test
    public void testSolutions() {
      SOLUTION.solve(3);
    }

  }

}