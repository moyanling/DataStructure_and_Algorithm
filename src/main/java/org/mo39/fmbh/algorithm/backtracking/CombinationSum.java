package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a set of candidate numbers (C) (without duplicates) and a target number
 * (T), find all unique combinations in C where the candidate numbers sums to
 * T. 
 * 
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * 
 * 
 * For example, given candidate set [2, 3, 6, 7] and target 7, 
 * A solution set is: 
 * 
 * [
 *   [7],
 *   [2, 2, 3]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum/">Combination Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CombinationSum {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] candidates, int target) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, new LinkedList<>(), candidates, target, 0);
      return result;
    }

    void recur(List<List<Integer>> result, LinkedList<Integer> cur, int[] candidates, int target,
        int start) {
      if (target <= 0) {
        if (target == 0) result.add(new ArrayList<>(cur));
        return;
      }
      for (int i = start; i < candidates.length; i++) {
        cur.add(candidates[i]);
        recur(result, cur, candidates, target - candidates[i], i);
        cur.removeLast();
      }
    }

  };

  public abstract List<List<Integer>> solve(int[] candidates, int target);

}
