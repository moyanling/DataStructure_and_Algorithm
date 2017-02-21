package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree.
 * 
 * 
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes v and w as the lowest node in T that has both
 * v and w as descendants (where we allow a node to be a descendant of itself).”
 * 
 * 
 * 
 *         _______3______
 *        /              \
 *     ___5__          ___1__
 *    /      \        /      \
 *    6      _2       0       8
 *          /  \
 *          7   4
 * 
 * 
 * 
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another
 * example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
 * according to the LCA definition.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/">Lowest
 *      Common Ancestor Of A Binary Tree</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LowestCommonAncestorOfABinaryTree {

  SOLUTION {

    @Override
    public TreeNode solve(TreeNode root, TreeNode p, TreeNode q) {
      if (root == null || root == p || root == q) return root;
      TreeNode left = solve(root.left, p, q);
      TreeNode right = solve(root.right, p, q);
      return left == null ? right : right == null ? left : root;
    }

  };

  public abstract TreeNode solve(TreeNode root, TreeNode p, TreeNode q);


  public static class TestLowestCommonAncestorOfABinaryTree {

    TreeNode root = new TreeNode(0);

    {
      root.left = new TreeNode(1);
      root.right = new TreeNode(2);
      root.left.left = new TreeNode(3);
      root.left.left.left = new TreeNode(4);
    }

    TreeNode expected = root.left.left;

    @Test
    public void testSolutions() {
      for (LowestCommonAncestorOfABinaryTree sol : LowestCommonAncestorOfABinaryTree.values()) {
        Assert.assertEquals(expected, sol.solve(root, root.left.left.left, root.left.left));
      }
    }

  }

}
