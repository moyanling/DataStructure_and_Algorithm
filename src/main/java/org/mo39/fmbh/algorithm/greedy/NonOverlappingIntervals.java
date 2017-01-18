package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.common.classes.Interval;

/**
 * <pre>
 * 
 * Given a collection of intervals, find the minimum number of intervals you need
 * to remove to make the rest of the intervals non-overlapping.
 * 
 * 
 * Note:
 * 
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap
 * each other.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 * 
 * Output: 1
 * 
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [ [1,2], [1,2], [1,2] ]
 * 
 * Output: 2
 * 
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [ [1,2], [2,3] ]
 * 
 * Output: 0
 * 
 * Explanation: You don't need to remove any of the intervals since they're already
 * non-overlapping.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/non-overlapping-intervals/">Non Overlapping
 *      Intervals</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NonOverlappingIntervals {

  /**
   * This is called brute force because each one has the chance to appear in the final result so
   * <b>O(n^2)</b> can iterate all possibilities.
   */
  BRUTE_FORCE {

    @Override
    public int solve(Interval[] intervals) {
      Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
      int max = 0;
      for (int i = 0; i < intervals.length; i++) {
        int count = 1, posi = i, left = i - 1, right = i + 1;
        while (left > -1) {
          if (intervals[left].end <= intervals[posi].start) {
            posi = left;
            count++;
          }
          left--;
        }
        posi = i;
        while (right < intervals.length) {
          if (intervals[posi].end <= intervals[right].start) {
            posi = right;
            count++;
          }
          right++;
        }
        max = Math.max(max, count);
      }
      return intervals.length - max;
    }

  },

  GREEDY {

    @Override
    public int solve(Interval[] intervals) {
      if (intervals.length == 0) return 0;
      Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
      int count = 0, end = intervals[0].end;
      for (int i = 1; i < intervals.length; i++) {
        if (intervals[i].start < end) {
          count += 1;
          if (intervals[i].end < end) end = intervals[i].end;
        } else end = intervals[i].end;
      }
      return count;
    }

  };

  public abstract int solve(Interval[] intervals);

  public static class TestNonOverlappingIntervals {

    private Interval[] intervals =
        {new Interval(2, 12), new Interval(1, 100), new Interval(11, 22), new Interval(1, 11)};
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(intervals));
      Assert.assertEquals(expected, GREEDY.solve(intervals));
    }

  }

}
