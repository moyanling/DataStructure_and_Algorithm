package org.mo39.fmbh.datastructure.binarytree;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * An abstract class for a binary search tree.
 * 
 * @author Jihan Chen
 *
 * @param
 */
public abstract class Tree {

  protected int size;
  protected TreeNode root;

  protected Comparator<Integer> comparator;

  /**
   * Return the size of this Tree.
   * 
   * @return
   */
  public int size() {
    return size;
  }

  /**
   * Return the root of this Tree.
   *
   * @return
   */
  public TreeNode getRoot() {
    return root;
  }

  public abstract TreeNode search(int key);

  public abstract void insert(int data);

  public abstract boolean delete(int key);

  public abstract TreeNode getMin();

  public abstract TreeNode getMax();

  public abstract TreeNode trace(int key, Consumer<TreeNode> consumer);

}
