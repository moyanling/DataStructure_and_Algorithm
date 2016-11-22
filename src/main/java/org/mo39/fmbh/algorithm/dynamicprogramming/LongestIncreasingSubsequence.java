package org.mo39.fmbh.algorithm.dynamicprogramming;

import static java.util.stream.Collectors.toList;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

@ProblemSource(LEETCODE)
public enum LongestIncreasingSubsequence {

  /**
   * Time complexity is <b>O(n^4)</b>. while is O(n), and the stream is doing filter on a O(n^2)
   * list, where each filter function runs O(n) complexity, so it's O(n^4);
   *
   */
  BRUTE_FORCE {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      Result.nums = nums;
      List<Result> results = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          if (nums[j] > nums[i]) results.add(new Result(i, j));
        }
      }
      int len = 1;
      while (len < nums.length) {
        List<Result> newResults = results.stream().filter(r -> r.update()).collect(toList());
        if (newResults.size() == 0) return results.stream().map(r -> r.toValue()).collect(toList());
        else results = newResults;
      }
      return results.stream().map(r -> r.toValue()).collect(toList());
    }

  },

  BOTTOM_UP_METHOD {

    int[][] memo;

    @Override
    public List<List<Integer>> solve(int[] nums) {
      memo = new int[nums.length][nums.length];
      memo[0] = nums;
      for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < nums.length; j++) {

        }
      }

      return null;
    }

  };


  private static class Result {

    public static int[] nums;
    public List<Integer> sequence = new ArrayList<>();

    public Result(int i, int j) {
      sequence.add(i);
      sequence.add(j);
    }

    public boolean update() {
      int lastIndex = sequence.get(sequence.size() - 1);
      for (int i = lastIndex + 1; i < nums.length; i++) {
        if (nums[i] > nums[lastIndex]) {
          sequence.add(i);
          return true;
        }
      }
      return false;
    }

    public List<Integer> toValue() {
      return sequence.stream().map(i -> nums[i]).collect(toList());
    }

  }

  public abstract List<List<Integer>> solve(int[] nums);

  public static class TestLongestIncreasingSubsequence {

    private int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
    private int expected = 4;

    @Test
    public void testSolutions() {
      // Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums));
      // Assert.assertEquals(expected, TOP_DOWN_WITH_MEMO.solve(nums));
      Z.print(BRUTE_FORCE.solve(new int[] {1, 3, 6, 7, 9, 4, 10, 5, 6}));
      // Z.print(RECURSIVE_SOLUTION.solve(nums));
    }

  }

}
