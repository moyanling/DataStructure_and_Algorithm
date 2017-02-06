package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
 * most frequently occurred element) in the given BST.
 * 
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than or equal
 * to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal
 * to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * 
 * For example:
 * Given BST [1,null,2,2],
 * 
 *    1
 *     \
 *      2
 *     /
 *    2
 * 
 * 
 * 
 * return [2].
 * 
 * 
 * Note:
 * If a tree has more than one mode, you can return them in any order.
 * 
 * 
 * Follow up:
 * Could you do that without using any extra space? (Assume that the implicit
 * stack space incurred due to recursion does not count).
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree/">Find Mode In Binary
 *      Search Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindModeInBinarySearchTree {

  SOLUTION_0 {

    @Override
    public int[] solve(TreeNode<Integer> root) {
      Map<Integer, Integer> map = new HashMap<>();
      int count = recur(root, map);
      List<Integer> list = new ArrayList<>();
      for (Integer key : map.keySet()) {
        if (map.get(key) == count) list.add(key);
      }
      int[] result = new int[list.size()];
      for (int i = 0; i < result.length; i++) {
        result[i] = list.get(i);
      }
      return result;
    }

    int recur(TreeNode<Integer> root, Map<Integer, Integer> map) {
      if (root == null) return 0;
      int max = 0;
      map.put(root.val, map.getOrDefault(root.val, 0) + 1);
      max = Math.max(max, map.get(root.val));
      int left = recur(root.left, map);
      int right = recur(root.right, map);
      return Math.max(left, Math.max(max, right));
    }

  },

  SOLUTION_1 {

    @Override
    public int[] solve(TreeNode<Integer> root) {
      // TODO Follow up
      return null;
    }

  };

  public abstract int[] solve(TreeNode<Integer> root);

}
