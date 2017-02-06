package org.mo39.fmbh.algorithm.depthfirstsearch;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a collection of numbers that might contain duplicates, return all possible
 * unique permutations.
 * 
 * 
 * 
 * For example,
 * [1,1,2] have the following unique permutations:
 * 
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/permutations-ii/">Permutations II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PermutationsII {

  /**
   * Cant' get rid of duplicates without a set.
   */
  LTE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      LinkedList<List<Integer>> result = new LinkedList<>();
      Arrays.sort(nums);
      result.add(new LinkedList<>());
      for (int n : nums) {
        for (int j = result.size(); j > 0; j--) {
          List<Integer> list = result.poll();
          for (int k = 0; k <= list.size(); k++) {
            if (k != list.size() && n == list.get(k)) continue;
            LinkedList<Integer> copy = new LinkedList<>(list);
            copy.add(k, n);
            result.add(copy);
          }
        }
      }
      return new ArrayList<>(new HashSet<>(result));
    }

  },

  /**
   * //TODO understand this dfs
   */
  SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      Arrays.sort(nums);
      List<List<Integer>> result = new ArrayList<>();
      solve(0, nums, result, new LinkedList<Integer>(), new boolean[nums.length]);
      return result;
    }

    void solve(int depth, int[] nums, List<List<Integer>> result, LinkedList<Integer> cur,
        boolean[] used) {
      if (depth == nums.length) {
        result.add(new ArrayList<Integer>(cur));
        return;
      }
      for (int i = 0; i < nums.length; i++) {
        if (used[i] || i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
        used[i] = true;
        cur.add(nums[i]);
        solve(depth + 1, nums, result, cur, used);
        cur.removeLast();
        used[i] = false;
      }
    }

  };

  public abstract List<List<Integer>> solve(int[] nums);

  public static class TestPermutationsII {

    int[] nums = {1, 1, 2};
    List<List<Integer>> expected = new ArrayList<>();

    {
      expected.add(new ArrayList<>(Arrays.asList(1, 1, 2)));
      expected.add(new ArrayList<>(Arrays.asList(2, 1, 1)));
      expected.add(new ArrayList<>(Arrays.asList(1, 2, 1)));
    }

    @Test
    public void testSolutions() {
      Assert.assertThat(LTE_SOLUTION.solve(nums), containsInAnyOrder(expected.toArray()));
      Assert.assertThat(SOLUTION.solve(nums), containsInAnyOrder(expected.toArray()));
    }

  }

}
