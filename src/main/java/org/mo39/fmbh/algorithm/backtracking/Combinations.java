package org.mo39.fmbh.algorithm.backtracking;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.
 * 
 * 
 * For example,
 * If n = 4 and k = 2, a solution is:
 * 
 * 
 * 
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/combinations/">Combinations</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Combinations {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(int n, int k) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, new ArrayList<>(), n, k, 1);
      return result;
    }

    private void recur(List<List<Integer>> result, List<Integer> cur, int n, int k, int start) {
      if (k == 0) {
        result.add(new ArrayList<>(cur));
        return;
      }
      for (int i = start; i <= n; i++) {
        cur.add(i);
        recur(result, cur, n, k - 1, i + 1);
        cur.remove(cur.size() - 1);
      }
    }

  };

  public abstract List<List<Integer>> solve(int n, int k);

  public static class TestCombinations {

    private int n = 4;
    private int k = 2;
    private List<List<Integer>> expected = new ArrayList<>();

    {
      expected.add(new ArrayList<>(Arrays.asList(2, 4)));
      expected.add(new ArrayList<>(Arrays.asList(3, 4)));
      expected.add(new ArrayList<>(Arrays.asList(2, 3)));
      expected.add(new ArrayList<>(Arrays.asList(1, 2)));
      expected.add(new ArrayList<>(Arrays.asList(1, 3)));
      expected.add(new ArrayList<>(Arrays.asList(1, 4)));
    }

    @Test
    public void testSolutions() {
      Assert.assertThat(SOLUTION.solve(n, k), containsInAnyOrder(expected.toArray()));
    }

  }

}
