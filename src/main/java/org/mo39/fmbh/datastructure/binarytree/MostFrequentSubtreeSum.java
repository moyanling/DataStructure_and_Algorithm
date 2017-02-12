package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given the root of a tree, you are asked to find the most frequent subtree sum.
 * The subtree sum of a node is defined as the sum of all the node values formed
 * by the subtree rooted at that node (including the node itself). So what is
 * the most frequent subtree sum value? If there is a tie, return all the values
 * with the highest frequency in any order.
 * 
 * 
 * Examples 1
 * Input:
 * 
 *   5
 *  /  \
 * 2   -3
 * 
 * return [2, -3, 4], since all the values happen only once, return all of them
 * in any order.
 * 
 * 
 * Examples 2
 * Input:
 * 
 *   5
 *  /  \
 * 2   -5
 * 
 * return [2], since 2 happens twice, however -5 only occur once.
 * 
 * 
 * Note:
 * You may assume the sum of values in any subtree is in the range of 32-bit signed
 * integer.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/most-frequent-subtree-sum/">Most Frequent Subtree
 *      Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MostFrequentSubtreeSum {

  SOLUTION {

    int max;

    @Override
    public int[] solve(TreeNode<Integer> root) {
      max = 0;
      Map<Integer, Integer> map = new HashMap<>();
      recur(root, map);
      return map.keySet().stream().filter(key -> map.get(key) == max).mapToInt(i -> i).toArray();
    }

    int recur(TreeNode<Integer> root, Map<Integer, Integer> map) {
      if (root == null) return 0;
      int sum = root.val + recur(root.left, map) + recur(root.right, map);
      map.put(sum, map.getOrDefault(sum, 0) + 1);
      max = Math.max(max, map.get(sum));
      return sum;
    }

  };

  public abstract int[] solve(TreeNode<Integer> root);

}
