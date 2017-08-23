package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given two non-empty binary trees s and t, check whether tree t has exactly
 * the same structure and node values with a subtree of s. A subtree of s is a
 * tree consists of a node in s and all of this node's descendants. The tree s
 * could also be considered as a subtree of itself.
 * 
 * 
 * Example 1:
 * 
 * Given tree s:
 * 
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 
 * Given tree t:
 * 
 *    4 
 *   / \
 *  1   2
 * 
 * Return true, because t has the same structure and node values with a subtree
 * of s.
 * 
 * 
 * Example 2:
 * 
 * Given tree s:
 * 
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 
 * Given tree t:
 * 
 *    4
 *   / \
 *  1   2
 * 
 * Return false.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/subtree-of-another-tree/">Subtree Of Another Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SubtreeOfAnotherTree {


  SOLUTION {

    @Override
    public boolean solve(TreeNode s, TreeNode t) {
      if (s == null) return false;
      return isSame(s, t) || solve(s.left, t) || solve(s.right, t);
    }

  };

  protected boolean isSame(TreeNode s, TreeNode t) {
    if (s == null && t == null) return true;
    if (s == null && t != null || s != null && t == null) return false;
    return (s.val == t.val ? true : false) && isSame(s.left, t.left) && isSame(s.right, t.right);
  }

  public abstract boolean solve(TreeNode s, TreeNode t);

  public static class TestSubtreeOfAnotherTree {

    TreeNode s = new TestData().root;
    TreeNode t = new TestData().getCompleteTree();

    @Test
    public void testSolutions() {
      Assert.assertFalse(SOLUTION.solve(s, t));
      Assert.assertTrue(SOLUTION.solve(s, s));
    }

  }

}
