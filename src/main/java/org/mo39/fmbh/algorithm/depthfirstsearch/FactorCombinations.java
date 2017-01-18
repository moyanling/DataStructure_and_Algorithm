package org.mo39.fmbh.algorithm.depthfirstsearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Numbers can be regarded as product of its factors. For example,  
 * 
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * 
 * Write a function that takes an integer n and return all possible combinations of its factors. 
 * 
 * 
 * Note: 
 * 
 * You may assume that n is always positive. 
 * Factors should be greater than 1 and less than n.
 * 
 * 
 * 
 * Examples: 
 *  input: 1
 *  output: 
 * 
 * []
 * 
 * input: 37
 *  output: 
 * 
 * []
 * 
 *  input: 12
 *  output:
 * 
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 * 
 *  input: 32
 *  output:
 * 
 * [
 *   [2, 16],
 *   [2, 2, 8],
 *   [2, 2, 2, 4],
 *   [2, 2, 2, 2, 2],
 *   [2, 4, 4],
 *   [4, 8]
 * ]
 * 
 * </pre>
 * 
 * Since this need to avoid different sequence within one combination, the back tracking won't work
 * here and it has to be solved by depth first search.
 * 
 * @see <a href="https://leetcode.com/problems/factor-combinations/">Factor Combinations</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FactorCombinations {

  WRONG_SOLUTION {

    Map<Integer, List<List<Integer>>> memo;

    @Override
    public List<List<Integer>> solve(int n) {
      memo = new HashMap<>();
      return recur(n);
    }

    private List<List<Integer>> recur(int n) {
      if (memo.containsKey(n)) return new ArrayList<>();
      List<List<Integer>> result = new ArrayList<>();
      for (int i = 2; i < n; i++) {
        if (n % i == 0) {
          List<List<Integer>> otherResult = recur(n / i);
          for (List<Integer> list : otherResult) {
            list.add(i);
          }
          result.addAll(otherResult);
        }
      }
      if (result.size() == 0) result.add(new ArrayList<>(Arrays.asList(n)));
      if (!memo.containsKey(n)) memo.put(n, result);
      return result;
    }

  },

  RECURSIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int n) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      recur(result, new ArrayList<Integer>(), n, 2);
      return result;
    }

    public void recur(List<List<Integer>> result, List<Integer> item, int n, int start) {
      if (n <= 1) {
        if (item.size() > 1) result.add(new ArrayList<Integer>(item));
        return;
      }
      for (int i = start; i <= n; ++i) {
        if (n % i != 0) {
          item.add(i);
          recur(result, item, n / i, i);
          item.remove(item.size() - 1);
        }
      }
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int n) {
      // TODO
      return null;
    }

  };

  public abstract List<List<Integer>> solve(int n);

  public static class TestFactorCombinations {

    private int n = 210;
    private List<List<Integer>> expected = new ArrayList<>();

    {
      expected.add(new ArrayList<>(Arrays.asList(2, 3, 5, 7)));
      expected.add(new ArrayList<>(Arrays.asList(2, 3, 35)));
      expected.add(new ArrayList<>(Arrays.asList(2, 5, 21)));
      expected.add(new ArrayList<>(Arrays.asList(2, 7, 15)));
      expected.add(new ArrayList<>(Arrays.asList(2, 105)));
      expected.add(new ArrayList<>(Arrays.asList(3, 5, 14)));
      expected.add(new ArrayList<>(Arrays.asList(3, 7, 10)));
      expected.add(new ArrayList<>(Arrays.asList(3, 70)));
      expected.add(new ArrayList<>(Arrays.asList(5, 6, 7)));
      expected.add(new ArrayList<>(Arrays.asList(5, 42)));
      expected.add(new ArrayList<>(Arrays.asList(6, 35)));
      expected.add(new ArrayList<>(Arrays.asList(7, 30)));
      expected.add(new ArrayList<>(Arrays.asList(10, 21)));
      expected.add(new ArrayList<>(Arrays.asList(14, 15)));
    }


    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(n));
    }

  }

}
