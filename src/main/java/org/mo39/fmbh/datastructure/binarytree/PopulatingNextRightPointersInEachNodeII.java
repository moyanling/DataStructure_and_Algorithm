package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution
 * still work?
 * 
 * Note:
 * You may only use constant extra space.
 * 
 * 
 * For example,
 * Given the following binary tree,
 * 
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * 
 * 
 * 
 * After calling your function, the tree should look like:
 * 
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/">
 *      Populating Next Right Pointers In Each Node II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PopulatingNextRightPointersInEachNodeII {

  /**
   * So the problem is how to locate the left most node in next level.
   */
  SOLUTION {

    @Override
    public void solve(TreeLinkNode root) {
      if (root == null) return;
      TreeLinkNode leftMost = root;
      while (true) {
        TreeLinkNode parent = leftMost;
        // Find the leftMost in next level.
        for (; parent != null; parent = parent.next) {
          if ((leftMost = parent.left) != null) break;
          if ((leftMost = parent.right) != null) break;
        }
        if (leftMost == null) break;
        TreeLinkNode head = leftMost;
        // Connect all nodes in next level.
        for (; parent != null; parent = parent.next) {
          if (parent.left != null && head != parent.left) {
            head.next = parent.left;
            head = head.next;
          }
          if (parent.right != null && head != parent.right) {
            head.next = parent.right;
            head = head.next;
          }
        }
      }
    }

  };

  public abstract void solve(TreeLinkNode root);

}
