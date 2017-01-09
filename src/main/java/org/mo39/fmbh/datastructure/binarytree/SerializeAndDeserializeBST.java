package org.mo39.fmbh.datastructure.binarytree;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Serialization is the process of converting a data structure or object into
 * a sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment. 
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary search tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/serialize-and-deserialize-bst/">Serialize And
 *      Deserialize Bst</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SerializeAndDeserializeBST {

  /**
   * //TODO
   */
  SOLUTION {

    @Override
    public String serialize(TreeNode<Integer> root) {
      return null;
    }

    @Override
    public TreeNode<Integer> deserialize(String data) {
      return null;
    }

  };

  public abstract String serialize(TreeNode<Integer> root);

  public abstract TreeNode<Integer> deserialize(String data);

  public static class TestSerializeAndDeserializeBST {

    private TreeNode<Integer> root = new TreeNode<>(5);

    @Before
    public void before() {
      root.left = new TreeNode<>(3);
      root.right = new TreeNode<>(6);
      root.left.left = new TreeNode<>(2);
      root.left.right = new TreeNode<>(4);
      root.left.left.left = new TreeNode<>(1);
    }

    @Test
    public void testSolutions() {}
  }

}
