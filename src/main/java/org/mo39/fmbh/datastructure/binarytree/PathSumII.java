package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given
 * sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 * 
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 
 * 
 * 
 * return
 * 
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/path-sum-ii/">Path Sum II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PathSumII {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(TreeNode root, int sum) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, new LinkedList<>(), root, 0, sum);
      return result;
    }

    void recur(List<List<Integer>> result, LinkedList<Integer> cur, TreeNode root, int sum,
        int target) {
      if (root == null) return;
      if (root.left == null && root.right == null) {
        if (sum + root.val == target) {
          cur.add(root.val);
          result.add(new ArrayList<>(cur));
          cur.removeLast();
        }
        return;
      }
      sum += root.val;
      cur.add(root.val);
      recur(result, cur, root.left, sum, target);
      recur(result, cur, root.right, sum, target);
      cur.removeLast();
      sum -= root.val;
    }

  };

  public abstract List<List<Integer>> solve(TreeNode root, int sum);

}
