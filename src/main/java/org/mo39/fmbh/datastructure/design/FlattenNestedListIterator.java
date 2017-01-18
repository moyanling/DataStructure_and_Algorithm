package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.common.interfaces.NestedInteger;

/**
 * <pre>
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,1,2,1,1].
 * 
 * 
 * 
 * Example 2:
 * Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,4,6].
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/flatten-nested-list-iterator/">Flatten Nested List
 *      Iterator</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FlattenNestedListIterator implements Iterator<Integer> {

  /**
   * Remember to make the RESPONSIBILITY of a method clear and simple.
   */
  SOLUTION {

    private Integer cur;
    private Iterator<NestedInteger> iterator;
    private Deque<Iterator<NestedInteger>> stack;

    @Override
    public void init(List<NestedInteger> nestedList) {
      stack = new ArrayDeque<>();
      iterator = nestedList.iterator();
    }

    /**
     * The responsibility is to return the next value indicated by cur.
     */
    @Override
    public Integer next() {
      return cur;
    }

    /**
     * The responsibility is find next value and pass it to cur.
     */
    @Override
    public boolean hasNext() {
      while (iterator.hasNext() || !stack.isEmpty()) {
        if (!iterator.hasNext()) {
          iterator = stack.pop();
          continue;
        }
        NestedInteger next = iterator.next();
        if (next.isInteger()) {
          cur = next.getInteger();
          return true;
        }
        stack.push(iterator);
        iterator = next.getList().iterator();
      }
      return false;
    }

  };


  public abstract void init(List<NestedInteger> nestedList);

  @Override
  public abstract Integer next();

  @Override
  public abstract boolean hasNext();
}
