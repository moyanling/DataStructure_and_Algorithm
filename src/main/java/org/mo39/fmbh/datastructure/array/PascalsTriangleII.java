package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * 
 * For example, given k = 3,
 * Return [1,3,3,1].
 * 
 * 
 * 
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/pascals-triangle-ii/">Pascals Triangle II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PascalsTriangleII {


  RECURSIVE_SOLUTION {

    @Override
    public List<Integer> solve(int rowIndex) {
      if (rowIndex == 0) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return list;
      }
      List<Integer> pre = solve(rowIndex - 1);
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < (rowIndex + 1) / 2; j++) {
        if (j == 0) {
          row.add(1);
          continue;
        }
        row.add(pre.get(j - 1) + pre.get(j));
      }
      if (rowIndex % 2 == 0) row.add(rowIndex / 2, pre.get(rowIndex / 2) * 2);
      for (int j = (rowIndex + 1) / 2; j > 0; j--) {
        row.add(row.get(j - 1));
      }
      return row;
    }
  },

  ITERATIVE_SOLUTION {

    @Override
    public List<Integer> solve(int rowIndex) {
      List<Integer> cur = null, pre = new ArrayList<>();
      pre.add(1);
      if (rowIndex == 0) return pre;
      int level = 0;
      while (++level <= rowIndex) {
        cur = new ArrayList<>();
        for (int j = 0; j < (level + 1) / 2; j++) {
          if (j == 0) {
            cur.add(1);
            continue;
          }
          cur.add(pre.get(j - 1) + pre.get(j));
        }
        if (level % 2 == 0) cur.add(level / 2, pre.get(level / 2) * 2);
        for (int j = (level + 1) / 2; j > 0; j--) {
          cur.add(cur.get(j - 1));
        }
        pre = cur;
      }
      return pre;
    }

  },

  SOLUTION {

    @Override
    public List<Integer> solve(int rowIndex) {
      List<Integer> toRet = new ArrayList<Integer>();
      for (int i = 0; i < rowIndex + 1; i++) {
        toRet.add(1);
        for (int j = i - 1; j > 0; j--) {
          toRet.set(j, toRet.get(j - 1) + toRet.get(j));
          Z.print(toRet);
        }
      }
      return toRet;
    }

  };

  public abstract List<Integer> solve(int rowIndex);

  public static class TestPascalsTriangleII {

    private int k = 3;
    private List<Integer> expected = Lists.newArrayList(1, 3, 3, 1);

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(k));
      Assert.assertEquals(expected, ITERATIVE_SOLUTION.solve(k));
      Assert.assertEquals(expected, SOLUTION.solve(k));
    }
  }

}
