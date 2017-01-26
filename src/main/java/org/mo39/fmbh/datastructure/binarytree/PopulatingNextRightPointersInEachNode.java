package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.LinkedList;
import java.util.Queue;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a binary tree
 * 
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * 
 * 
 * 
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 * 
 * 
 * 
 * 
 * For example,
 * Given the following perfect binary tree,
 * 
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * 
 * 
 * 
 * After calling your function, the tree should look like:
 * 
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node/">
 *      Populating Next Right Pointers In Each Node</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PopulatingNextRightPointersInEachNode {

  SOLUTION {

    @Override
    public <T> void solve(TreeLinkNode<T> root) {
      if (root == null) return;
      Queue<TreeLinkNode<T>> queue = new LinkedList<>();
      queue.add(root);
      while (!queue.isEmpty()) {
        int size = queue.size();
        TreeLinkNode<T> pre = null, cur = null;
        for (int i = 0; i < size; i++) {
          cur = queue.poll();
          if (pre != null) pre.next = cur;
          if (cur.left != null) queue.add(cur.left);
          if (cur.right != null) queue.add(cur.right);
          pre = cur;
        }
      }
    }

  };

  public abstract <T> void solve(TreeLinkNode<T> root);


}
