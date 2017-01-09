package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * 
 * Find all possible combinations of k numbers that add up to a number n, given
 * that only numbers from 1 to 9 can be used and each combination should be a
 * unique set of numbers.
 * 
 * 
 * 
 *  Example 1:
 * Input:  k = 3,  n = 7
 * Output: 
 * 
 * [[1,2,4]]
 * 
 * 
 *  Example 2:
 * Input:  k = 3,  n = 9
 * Output: 
 * 
 * [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum-iii/">Combination Sum III</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CombinationSumIII {

  SOLUTION_0 {

    @Override
    public List<List<Integer>> solve(int k, int n) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, k, n, new ArrayList<>(), 1, 0);
      return result;
    }

    public void recur(List<List<Integer>> result, int k, int n, List<Integer> list, int i,
        int sum) {
      if (i > 9) {
        if (list.size() == k && sum == n) result.add(list);
        return;
      }
      List<Integer> use = new ArrayList<>();
      List<Integer> notUse = new ArrayList<>();
      use.addAll(list);
      notUse.addAll(list);
      use.add(i);
      recur(result, k, n, use, i + 1, sum + i);
      recur(result, k, n, notUse, i + 1, sum);
    }

  },

  SOLUTION_1 {

    @Override
    public List<List<Integer>> solve(int k, int n) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, new ArrayList<Integer>(), k, 1, n);
      return result;
    }

    private void recur(List<List<Integer>> result, List<Integer> comb, int k, int start, int n) {
      if (comb.size() == k && n == 0) {
        List<Integer> li = new ArrayList<Integer>(comb);
        result.add(li);
        return;
      }
      for (int i = start; i <= 9; i++) {
        comb.add(i);
        recur(result, comb, k, i + 1, n - i);
        comb.remove(comb.size() - 1);
      }
    }

  };

  public abstract List<List<Integer>> solve(int k, int n);

  public static class TestCombinationSumIII {

    private int k = 3;
    private int n = 9;
    private List<List<Integer>> expected = Lists.newArrayList();

    {
      expected.add(Lists.newArrayList(1, 2, 6));
      expected.add(Lists.newArrayList(1, 3, 5));
      expected.add(Lists.newArrayList(2, 3, 4));
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(k, n));
      Assert.assertEquals(expected, SOLUTION_1.solve(k, n));
    }

  }

}
