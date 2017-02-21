package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * 
 * Input: 
 * 
 *           1
 *          / \
 *         3   2
 *        / \   \  
 *       5   3   9 
 * 
 * Output: [1, 3, 9]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-largest-value-in-each-tree-row/">Find Largest
 *      Value In Each Tree Row</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindLargestValueInEachTreeRow {

  SOLUTION {

    @Override
    public List<Integer> solve(TreeNode root) {
      List<Integer> result = new ArrayList<>();
      if (root == null) return result;
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty()) {
        int max = Integer.MIN_VALUE;
        for (int i = queue.size(); i > 0; i--) {
          TreeNode node = queue.poll();
          max = Math.max(max, node.val);
          if (node.left != null) queue.add(node.left);
          if (node.right != null) queue.add(node.right);
        }
        result.add(max);
      }
      return result;
    }

  };

  public abstract List<Integer> solve(TreeNode root);

}
