package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.LinkedList;
import java.util.Queue;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary tree, find the leftmost value in the last row of the tree. 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * 
 *     2
 *    / \
 *   1   3
 * 
 * Output:
 * 1
 * 
 * 
 * 
 *   Example 2: 
 * 
 * Input:
 * 
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 * 
 * Output:
 * 7
 * 
 * 
 * 
 * Note:
 * You may assume the tree (i.e., the given root node) is not NULL.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-bottom-left-tree-value/">Find Bottom Left Tree
 *      Value</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindBottomLeftTreeValue {

  SOLUTION {

    @Override
    public int solve(TreeNode root) {
      Queue<TreeNode> pre = new LinkedList<>();
      pre.add(root);
      while (true) {
        Queue<TreeNode> cur = new LinkedList<>();
        int result = pre.peek().val;
        for (int i = pre.size(); i > 0; i--) {
          TreeNode node = pre.poll();
          if (node.left != null) cur.add(node.left);
          if (node.right != null) cur.add(node.right);
        }
        if (cur.size() == 0) return result;
        else pre = cur;
      }
    }

  };

  public abstract int solve(TreeNode root);

}
