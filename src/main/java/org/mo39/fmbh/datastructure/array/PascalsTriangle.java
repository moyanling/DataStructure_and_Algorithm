package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * 
 * For example, given numRows = 5,
 * Return
 * 
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/pascals-triangle/">Pascals Triangle</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PascalsTriangle {

  RECURSIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int numRows) {
      if (numRows == 1) {
        List<List<Integer>> toRet = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        toRet.add(list);
        return toRet;
      }
      List<List<Integer>> pre = solve(numRows - 1);
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < numRows / 2; j++) {
        if (j == 0) {
          row.add(1);
          continue;
        }
        row.add(pre.get(numRows - 2).get(j - 1) + pre.get(numRows - 2).get(j));
      }
      if (numRows % 2 == 1) row.add(numRows / 2, pre.get(numRows - 2).get(numRows / 2) * 2);
      for (int j = numRows / 2; j > 0; j--) {
        row.add(row.get(j - 1));
      }
      pre.add(row);
      return pre;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int numRows) {
      List<List<Integer>> toRet = new ArrayList<>();
      if (numRows < 1) return toRet;
      for (int i = 1; i <= numRows; i++) {
        List<Integer> row = new ArrayList<>(i);
        if (i == 1) {
          row.add(1);
          toRet.add(row);
          continue;
        }
        for (int j = 0; j < i / 2; j++) {
          if (j == 0) {
            row.add(1);
            continue;
          }
          row.add(toRet.get(i - 2).get(j - 1) + toRet.get(i - 2).get(j));
        }
        if (i % 2 == 1) row.add(i / 2, toRet.get(i - 2).get(i / 2) * 2);
        for (int j = i / 2; j > 0; j--) {
          row.add(row.get(j - 1));
        }
        toRet.add(row);
      }
      return toRet;
    }

  };

  public abstract List<List<Integer>> solve(int numRows);

  public static class TestPascalsTriangle {

    public static List<List<Integer>> expected;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void beforeClass() {
      List<Integer> first = Lists.newArrayList(1);
      List<Integer> second = Lists.newArrayList(1, 1);
      List<Integer> third = Lists.newArrayList(1, 2, 1);
      List<Integer> fourth = Lists.newArrayList(1, 3, 3, 1);
      List<Integer> fifth = Lists.newArrayList(1, 4, 6, 4, 1);
      expected = Lists.newArrayList(first, second, third, fourth, fifth);
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(5));
      Assert.assertEquals(expected, ITERATIVE_SOLUTION.solve(5));
    }

  }

}
