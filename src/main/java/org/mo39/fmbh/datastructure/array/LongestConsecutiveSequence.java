package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive
 * elements sequence.
 * 
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length:
 * 4.
 * 
 * 
 * Your algorithm should run in O(n) complexity.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/longest-consecutive-sequence/">Longest Consecutive
 *      Sequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestConsecutiveSequence {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int result = 0;
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
      for (int n : nums) {
        if (!map.containsKey(n)) {
          int left = map.containsKey(n - 1) ? map.get(n - 1) : 0;
          int right = map.containsKey(n + 1) ? map.get(n + 1) : 0;
          int sum = left + right + 1;
          map.put(n, sum);
          result = Math.max(result, sum);
          map.put(n - left, sum);
          map.put(n + right, sum);
        }
      }
      return result;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestLongestConsecutiveSequence {

    private int[] nums = {5, 1, 4, 2, 3};
    private int expected = 5;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}
