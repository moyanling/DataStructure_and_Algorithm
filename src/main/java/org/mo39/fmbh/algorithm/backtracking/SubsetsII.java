package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * 
 * For example,
 * If nums = [1,2,2], a solution is:
 * 
 * 
 * 
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/subsets-ii/">Subsets II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SubsetsII {

  SET_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      Arrays.sort(nums);
      Set<List<Integer>> set = new HashSet<>();
      recur(set, new LinkedList<>(), nums, 0);
      return new ArrayList<>(set);
    }

    void recur(Set<List<Integer>> set, LinkedList<Integer> cur, int[] nums, int start) {
      set.add(new ArrayList<>(cur));
      for (int i = start; i < nums.length; i++) {
        cur.add(nums[i]);
        recur(set, cur, nums, i + 1);
        cur.removeLast();
      }
    }

  },

  RECURSIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      Arrays.sort(nums);
      List<List<Integer>> result = new ArrayList<>();
      recur(result, new LinkedList<>(), nums, 0);
      return result;
    }

    void recur(List<List<Integer>> result, LinkedList<Integer> cur, int[] nums, int start) {
      result.add(new ArrayList<>(cur));
      for (int i = start; i < nums.length; i++) {
        if (i == start || nums[i] != nums[i - 1]) {
          cur.add(nums[i]);
          recur(result, cur, nums, i + 1);
          cur.removeLast();
        }
      }
    }

  };

  public abstract List<List<Integer>> solve(int[] nums);

  public static class TestSubsetsII {

    int[] nums = {1, 2, 2};

    @Test
    public void testSolutions() {
      Z.print(SET_SOLUTION.solve(nums));
      Z.print(RECURSIVE_SOLUTION.solve(nums));
    }

  }

}
