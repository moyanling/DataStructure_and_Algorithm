package org.mo39.fmbh.datastructure.binarytree;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * An abstract class for a binary search tree.
 * 
 * @author Jihan Chen
 *
 * @param <T>
 */
public abstract class Tree<T> {

  protected int size;
  protected TreeNode<T> root;
  protected Comparator<T> comparator;

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
  public TreeNode<T> getRoot() {
    return root;
  }

  public abstract TreeNode<T> search(T key);

  public abstract void insert(T data);

  public abstract boolean delete(T key);

  public abstract TreeNode<T> getMin();

  public abstract TreeNode<T> getMax();

  public abstract TreeNode<T> trace(T key, Consumer<TreeNode<T>> consumer);

}
