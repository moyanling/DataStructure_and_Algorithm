package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 * 
 * 
 * 
 * Example:
 * Given binary tree 
 * 
 *           1
 *          / \
 *         2   3
 *        / \     
 *       4   5    
 * 
 * 
 * 
 * Returns [4, 5, 3], [2], [1].
 * 
 * 
 * 
 * Explanation:
 * 
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 * 
 *           1
 *          / 
 *         2          
 * 
 * 
 * 
 * 2. Now removing the leaf [2] would result in this tree:
 * 
 *           1          
 * 
 * 
 * 
 * 3. Now removing the leaf [1] would result in the empty tree:
 * 
 *           []         
 * 
 * 
 * 
 * 
 * Returns [4, 5, 3], [2], [1].
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-leaves-of-binary-tree/">Find Leaves Of Binary
 *      Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindLeavesOfBinaryTree {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      recur(root, res);
      return res;
    }

    private int recur(TreeNode node, List<List<Integer>> res) {
      if (null == node) return -1;
      int level = 1 + Math.max(recur(node.left, res), recur(node.right, res));
      if (res.size() < level + 1) res.add(new ArrayList<>());
      res.get(level).add(node.val);
      return level;
    }


  };

  public abstract List<List<Integer>> solve(TreeNode root);

  public static class TestFindLeavesOfBinaryTree {

    private TreeNode root = new TestData().root;

    @SuppressWarnings("serial")
    private ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>() {
      {
        add(Lists.newArrayList(5, 6, 7));
        add(Lists.newArrayList(3, 4));
        add(Lists.newArrayList(1, 2));
        add(Lists.newArrayList(0));
      }
    };

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(root));
    }

  }

}
