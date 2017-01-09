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
 * Different from the previous question where weight is increasing from root to leaf, now the weight
 * is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level
 * integers have the largest weight.
 * 
 * Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * 
 * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6
 * at depth 1; 1*3 + 4*2 + 6*1 = 17)
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/nested-list-weight-sum-ii/">Nested List Weight Sum
 *      II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NestedListWeightSumII {

  RECURSIVE_SOLUTION {


    @Override
    public int solve(List<NestedInteger> nestedList) {
      return recur(nestedList, 0);
    }

    public int recur(List<NestedInteger> nestedList, int pre) {
      List<NestedInteger> next = new ArrayList<>();
      for (NestedInteger ni : nestedList) {
        if (ni.isInteger()) pre += ni.getInteger();
        else next.addAll(ni.getList());
      }
      if (next.size() == 0) return pre;
      return pre + recur(next, pre);
    }

  },

  BAD_ITERATIVE_SOLUTION {

    @Override
    public int solve(List<NestedInteger> nestedList) {
      int sum = 0;
      List<List<Integer>> list = new ArrayList<>();
      while (!nestedList.isEmpty()) {
        list.add(new ArrayList<>());
        List<NestedInteger> next = new ArrayList<>();
        for (NestedInteger i : nestedList) {
          if (i.isInteger()) list.get(list.size() - 1).add(i.getInteger());
          else next.addAll(i.getList());
        }
        nestedList = next;
      }
      for (int i = 0; i < list.size(); i++) {
        for (Integer v : list.get(i)) {
          sum += v * (list.size() - i);
        }
      }
      return sum;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public int solve(List<NestedInteger> nestedList) {
      int unweighted = 0, weighted = 0;
      while (!nestedList.isEmpty()) {
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
          if (ni.isInteger()) unweighted += ni.getInteger();
          else nextLevel.addAll(ni.getList());
        }
        weighted += unweighted;
        nestedList = nextLevel;
      }
      return weighted;
    }

  };

  public abstract int solve(List<NestedInteger> nestedList);

}
