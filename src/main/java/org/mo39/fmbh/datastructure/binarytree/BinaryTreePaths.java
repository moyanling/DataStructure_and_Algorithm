package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;


/**
 * <pre>
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * 
 * For example, given the following binary tree:
 * 
 * 
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 
 * All root-to-leaf paths are: ["1->2->5", "1->3"]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-paths/">Binary Tree Paths</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryTreePaths {

  RECURSIVE_SOLUTION {

    @Override
    public <T> List<List<T>> solve(TreeNode<T> root) {
      List<List<T>> toRet = new ArrayList<>();
      if (root.left == null && root.right == null) {
        List<T> leave = new ArrayList<>();
        leave.add(root.val);
        toRet.add(leave);
        return toRet;
      }
      if (root.left != null) toRet.addAll(solve(root.left));
      if (root.right != null) toRet.addAll(solve(root.right));
      for (List<T> path : toRet) {
        path.add(0, root.val);
      }
      return toRet;
    }

  };

  public abstract <T> List<List<T>> solve(TreeNode<T> root);

  public static class TestBinaryTreePaths {

    private TreeNode<Integer> root = new TestData().root;
    private List<List<Integer>> expected = Lists.newArrayList();

    @Before
    public void before() {
      expected.add(Lists.newArrayList(0, 1, 3, 5));
      expected.add(Lists.newArrayList(0, 1, 3, 6));
      expected.add(Lists.newArrayList(0, 2, 4, 7));
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(root));
    }

  }

}
