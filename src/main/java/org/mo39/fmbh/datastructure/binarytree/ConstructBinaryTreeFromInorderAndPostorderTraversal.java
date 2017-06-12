package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * </pre>
 * 
 * @see <a href=
 *      "https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">
 *      Construct Binary Tree From Inorder And Postorder Traversal</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ConstructBinaryTreeFromInorderAndPostorderTraversal {

  SOLUTION {

    @Override
    public TreeNode solve(int[] inorder, int[] postorder) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < inorder.length; i++) {
        map.put(inorder[i], i);
      }
      return recur(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, map);
    }

    TreeNode recur(int[] inorder, int[] postorder, int start1, int end1, int start2, int end2,
        Map<Integer, Integer> map) {
      if (start2 > end2) return null;
      if (start2 == end2) return new TreeNode(postorder[end2]);
      TreeNode root = new TreeNode(postorder[end2]);
      int posi = map.get(root.val);
      root.left =
          recur(inorder, postorder, start1, posi - 1, start2, start2 + posi - start1 - 1, map);
      root.right = recur(inorder, postorder, posi + 1, end1, start2 + posi - start1, end2 - 1, map);
      return root;
    }

  };

  public abstract TreeNode solve(int[] inorder, int[] postorder);

}
