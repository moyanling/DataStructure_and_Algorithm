package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Iterator;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Implement an iterator to flatten a 2d vector.
 * 
 * 
 * For example,
 * Given 2d vector = 
 * 
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * 
 * 
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 * 
 * 
 * 
 *   How many variables do you need to keep track?
 *   Two variables is all you need. Try with x and y.
 *   Beware of empty rows. It could be the first few rows.
 *   To write correct code, think about the invariant to maintain. What is it?
 *   The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
 *   Not sure? Think about how you would implement hasNext(). Which is more complex?
 *   Common logic in two different places should be refactored into a common method.
 * 
 * 
 * 
 * Follow up:
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/flatten2-d-vector/">Flatten 2 D Vector</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Flatten2DVector {

  SOLUTION {

    private Iterator<List<Integer>> outter;
    private Iterator<Integer> inner;

    @Override
    public void init(List<List<Integer>> vec2d) {
      this.outter = vec2d.iterator();
      if (outter.hasNext()) this.inner = outter.next().iterator();
    }

    @Override
    public Integer next() {
      return inner.next();
    }

    @Override
    public boolean hasNext() {
      if (inner == null) return false;
      while (!inner.hasNext() && outter.hasNext()) {
        inner = outter.next().iterator();
      }
      return inner.hasNext();
    }

  };

  public abstract void init(List<List<Integer>> vec2d);

  public abstract Integer next();

  public abstract boolean hasNext();


}
