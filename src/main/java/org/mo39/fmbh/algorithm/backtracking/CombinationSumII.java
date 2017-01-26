package org.mo39.fmbh.algorithm.backtracking;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * 
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * 
 * 
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 * A solution set is: 
 * 
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum-ii/">Combination Sum II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CombinationSumII {

  SET_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] candidates, int target) {
      Arrays.sort(candidates);
      Set<List<Integer>> result = new HashSet<>();
      recur(result, new LinkedList<>(), candidates, target, 0);
      return new ArrayList<>(result);
    }

    void recur(Set<List<Integer>> result, LinkedList<Integer> cur, int[] candidates, int target,
        int i) {
      if (i >= candidates.length || target <= 0) {
        if (target == 0) result.add(new ArrayList<>(cur));
        return;
      }
      recur(result, cur, candidates, target, i + 1);
      cur.add(candidates[i]);
      recur(result, cur, candidates, target - candidates[i], i + 1);
      cur.removeLast();
    }

  },

  SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<List<Integer>> list = new ArrayList<>();
      recur(list, new LinkedList<>(), candidates, target, 0);
      return list;
    }

    private void recur(List<List<Integer>> result, LinkedList<Integer> cur, int[] candidates,
        int target, int start) {
      if (start >= candidates.length || target <= 0) {
        if (target == 0) result.add(new ArrayList<>(cur));
        return;
      }
      for (int i = start; i < candidates.length; i++) {
        if (i > start && candidates[i] == candidates[i - 1]) continue;
        cur.add(candidates[i]);
        recur(result, cur, candidates, target - candidates[i], i + 1);
        cur.removeLast();
      }
    }

  };

  public abstract List<List<Integer>> solve(int[] candidates, int target);

  public static class TestCombinationSumII {

    int[] candidates = {10, 1, 2, 7, 6, 1, 5};
    int target = 8;
    List<List<Integer>> expected = new ArrayList<>();

    {
      expected.add(new ArrayList<>(Arrays.asList(1, 1, 6)));
      expected.add(new ArrayList<>(Arrays.asList(1, 2, 5)));
      expected.add(new ArrayList<>(Arrays.asList(1, 7)));
      expected.add(new ArrayList<>(Arrays.asList(2, 6)));
    }

    @Test
    public void testSolutions() {
      Assert.assertThat(SET_SOLUTION.solve(candidates, target),
          containsInAnyOrder(expected.toArray()));
      Assert.assertThat(SOLUTION.solve(candidates, target), containsInAnyOrder(expected.toArray()));
    }

  }

}
