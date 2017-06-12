package org.mo39.fmbh.algorithm.dynamicprogramming;

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
      LinkedList<Integer> result = new LinkedList<>();
      Arrays.sort(nums);
      int max = 0, pre = -1;
      int[] pres = new int[nums.length], memo = new int[nums.length];
      for (int i = 0; i < nums.length; i++) {
        memo[i] = 1;
        pres[i] = -1;
        for (int j = 0; j < i; j++) {
          if (nums[i] % nums[j] == 0 && memo[j] + 1 > memo[i]) {
            pres[i] = j;
            memo[i] = memo[j] + 1;
          }
        }
        if (memo[i] > max) {
          max = memo[i];
          pre = i;
        }
      }
      Z.print(memo);
      while (pre != -1) {
        result.addFirst(nums[pre]);
        pre = pres[pre];
      }
      return result;
    }

  };

  public abstract List<Integer> solve(int[] nums);

  public static class TestLargestDivisibleSubset {

    int[] nums = {2, 3, 4, 9, 8};
    List<Integer> expected = new ArrayList<>(Arrays.asList(2, 4, 8));

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }


  }

}
