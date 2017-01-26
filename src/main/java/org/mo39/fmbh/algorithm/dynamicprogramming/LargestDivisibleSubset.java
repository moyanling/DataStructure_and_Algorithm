package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj
 * % Si = 0.
 * 
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * 
 * Example 1:
 * 
 * nums: [1,2,3]
 * 
 * Result: [1,2] (of course, [1,3] will also be ok)
 * 
 * 
 * 
 * Example 2:
 * 
 * nums: [1,2,4,8]
 * 
 * Result: [1,2,4,8]
 * 
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/largest-divisible-subset/">Largest Divisible
 *      Subset</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LargestDivisibleSubset {

  SOLUTION {

    @Override
    public List<Integer> solve(int[] nums) {
      if (nums.length < 1) return new ArrayList<>();
      Arrays.sort(nums);
      List<LinkedList<Integer>> memo = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(nums[i]);
        memo.add(list);
      }
      int max = 0;
      for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < memo.size(); j++) {
          if (i > j && nums[i] % memo.get(j).getLast() == 0) {
            memo.get(j).add(nums[i]);
            if (memo.get(j).size() > memo.get(max).size()) max = j;
          }
        }
      }
      return memo.get(max);
    }

  };

  public abstract List<Integer> solve(int[] nums);

  public static class TestLargestDivisibleSubset {

    int[] nums = {1, 2, 4, 8, 16};
    List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 16));

    @Test
    public void testSolutions() {
      for (LargestDivisibleSubset sol : LargestDivisibleSubset.values()) {
        Assert.assertEquals(expected, sol.solve(nums));
      }
    }


  }

}
