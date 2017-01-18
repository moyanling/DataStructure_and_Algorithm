package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a binary tree, imagine yourself standing on the right side of it, return
 * the values of the nodes you can see ordered from top to bottom.
 * 
 * 
 * For example:
 * Given the following binary tree,
 * 
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * 
 * 
 * 
 * You should return [1, 3, 4].
 * 
 * </pre>
 * 
 * <lo>
 * <li>First it follows the root->right->left pattern traversal</li>
 * <li>Second, a node is added only when its depth is large than a threshold</li>
 * <li>Third, the threshold always growing according to the max depth</li></lo>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-right-side-view/">Binary Tree Right Side
 *      View</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryTreeRightSideView {

  SOLUTION {

    @Override
    public List<Integer> solve(TreeNode<Integer> root) {
      List<Integer> result = new ArrayList<>();
      recur(result, root, 0, -1);
      return result;
    }

    /**
     * 
     * @param result - the final result
     * @param root - the node to be processed
     * @param depth - the depth of current node
     * @param threshold - the threshold for a node to be seen
     */
    private int recur(List<Integer> result, TreeNode<Integer> root, int depth, int threshold) {
      if (root == null) return threshold;
      if (depth > threshold) {
        result.add(root.val);
        threshold = depth;
      }
      int rightThreshold = recur(result, root.right, depth + 1, threshold);
      int leftThreshold = recur(result, root.left, depth + 1, rightThreshold);
      return leftThreshold;
    }


  };

  public abstract List<Integer> solve(TreeNode<Integer> root);

  public static class TestBinaryTreeRightSideView {

    private TreeNode<Integer> root = new TreeNode<>(1);
    private List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 6));

    {
      root.right = new TreeNode<>(3);
      root.right.left = new TreeNode<>(6);
      root.left = new TreeNode<>(2);
      root.left.right = new TreeNode<>(5);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(root));
    }

  }

}
