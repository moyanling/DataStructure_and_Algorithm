package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.common.interfaces.NestedInteger;

/**
 * <pre>
 * Given a nested list of integers, return the sum of all integers in the list weighted by their
 * depth.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other
 * lists.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 * 
 * Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6
 * at depth 3; 1 + 4*2 + 6*3 = 27) *
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/nested-list-weight-sum/">Nested List Weight Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NestedListWeightSum {

  RECURSIVE_SOLUTION {

    int depth = 0;
    int sum = 0;

    @Override
    public int solve(List<NestedInteger> nestedList) {
      if (nestedList == null) return 0;
      depth++;
      for (NestedInteger i : nestedList) {
        sum += i.isInteger() ? i.getInteger() * depth : solve(i.getList());
      }
      depth--;
      return sum;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public int solve(List<NestedInteger> nestedList) {
      if (nestedList == null) return 0;
      int sum = 0, depth = 0;
      while (!nestedList.isEmpty()) {
        depth++;
        List<NestedInteger> next = new ArrayList<>();
        for (NestedInteger i : nestedList) {
          if (i.isInteger()) sum += i.getInteger() * depth;
          else next.addAll(i.getList());
        }
        nestedList = next;
      }
      return sum;
    }

  };

  public abstract int solve(List<NestedInteger> nestedList);

}
