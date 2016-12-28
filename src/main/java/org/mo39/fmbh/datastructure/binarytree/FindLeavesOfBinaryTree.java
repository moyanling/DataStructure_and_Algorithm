package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;

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

    private Set<TreeNode<Integer>> set;

    @Override
    public List<List<Integer>> solve(TreeNode<Integer> root) {
      set = new HashSet<>();
      while (!isVisited(root, set)) {
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
          root = stack.pop();
          if (root.right != null) stack.push(root.right);
          if (root.left != null) stack.push(root.left);
        }
      }

      return null;
    }

    public boolean isVisited(TreeNode<Integer> node, Set<TreeNode<Integer>> set) {
      return node == null || set.contains(node);
    }

  };

  public abstract List<List<Integer>> solve(TreeNode<Integer> root);

  public static class TestFindLeavesOfBinaryTree {

  }

}
