package org.mo39.fmbh.algorithm.depthfirstsearch;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * 
 * 
 * For example,
 * [1,2,3] have the following permutations:
 * 
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/permutations/">Permutations</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Permutations {

  RECURSIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, new ArrayList<>(), nums, 0);
      return result;
    }

    private void recur(List<List<Integer>> result, List<Integer> cur, int[] nums, int start) {
      if (start > nums.length - 1) {
        result.add(new ArrayList<>(cur));
        return;
      }
      for (int i = start; i < nums.length; i++) {
        cur.add(nums[i]);
        Z.swap(nums, i, start);
        recur(result, cur, nums, start + 1);
        Z.swap(nums, i, start);
        cur.remove(cur.size() - 1);
      }
    }

  },

  /**
   * //TODO
   */
  ITERATIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
      result.add(new ArrayList<Integer>());
      for (int n : nums) {
        int size = result.size();
        for (; size > 0; size--) {
          List<Integer> r = result.pollFirst();
          for (int i = 0; i <= r.size(); i++) {
            List<Integer> t = new LinkedList<Integer>(r);
            t.add(i, n);
            result.add(t);
          }
        }
      }
      return result;
    }

  };

  public abstract List<List<Integer>> solve(int[] nums);

  public static class TestPermutations {

    private int[] nums = {1, 2, 3};
    private List<List<Integer>> expected = new ArrayList<>();

    {
      expected.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
      expected.add(new ArrayList<>(Arrays.asList(1, 3, 2)));
      expected.add(new ArrayList<>(Arrays.asList(2, 1, 3)));
      expected.add(new ArrayList<>(Arrays.asList(2, 3, 1)));
      expected.add(new ArrayList<>(Arrays.asList(3, 2, 1)));
      expected.add(new ArrayList<>(Arrays.asList(3, 1, 2)));
    }

    @Test
    public void testSolutions() {
      Assert.assertThat(RECURSIVE_SOLUTION.solve(nums), containsInAnyOrder(expected.toArray()));
      Assert.assertThat(ITERATIVE_SOLUTION.solve(nums), containsInAnyOrder(expected.toArray()));
    }

  }

}
