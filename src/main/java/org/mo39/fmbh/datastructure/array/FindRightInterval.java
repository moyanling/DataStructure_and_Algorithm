package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.Interval;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a set of intervals, for each of the interval i, check if there exists
 * an interval j whose start point is bigger than or equal to the end point of
 * the interval i, which can be called that j is on the "right" of i.
 * 
 * 
 * 
 * For any interval i, you need to store the minimum interval j's index, which
 * means that the interval j has the minimum start point to build the "right"
 * relationship for interval i. If the interval j doesn't exist, store -1 for
 * the interval i. Finally, you need output the stored value of each interval
 * as an array.
 * 
 * 
 * Note:
 * 
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [ [1,2] ]
 * 
 * Output: [-1]
 * 
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [ [3,4], [2,3], [1,2] ]
 * 
 * Output: [-1, 0, 1]
 * 
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [ [1,4], [2,3], [3,4] ]
 * 
 * Output: [-1, 2, -1]
 * 
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-right-interval/">Find Right Interval</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindRightInterval {

  BINARY_SEARCH_SOLUTION {

    @Override
    public int[] solve(Interval[] intervals) {
      if (intervals.length < 2) return new int[] {-1};
      Index[] arr = new Index[intervals.length];
      int[] result = new int[intervals.length];
      for (int i = 0; i < intervals.length; i++) {
        arr[i] = new Index(intervals[i], i);
      }
      Arrays.sort(arr);
      for (int i = 0; i < intervals.length; i++) {
        int posi = Arrays.binarySearch(arr, new Index(intervals[i].end));
        if (posi >= 0) result[i] = arr[posi].index;
        else result[i] = -(posi + 1) == arr.length ? result[i] = -1 : arr[-(posi + 1)].index;
      }
      return result;
    }

  },

  TREE_MAP_SOLUTION {

    @Override
    public int[] solve(Interval[] intervals) {
      int[] result = new int[intervals.length];
      TreeMap<Integer, Integer> intervalMap = new TreeMap<>();
      for (int i = 0; i < intervals.length; ++i) {
        intervalMap.put(intervals[i].start, i);
      }
      for (int i = 0; i < intervals.length; ++i) {
        Map.Entry<Integer, Integer> entry = intervalMap.ceilingEntry(intervals[i].end);
        result[i] = entry != null ? entry.getValue() : -1;
      }
      return result;
    }

  };

  private static class Index implements Comparable<Index> {

    public final int index;
    public final Interval interval;

    public Index(Interval interval, int index) {
      this.interval = interval;
      this.index = index;
    }

    public Index(int start) {
      this.interval = new Interval(start, 0);
      this.index = 0;
    }

    @Override
    public int compareTo(Index that) {
      return this.interval.start - that.interval.start;
    }

  }

  public abstract int[] solve(Interval[] intervals);

  public static class TestFindRightInterval {

    private Interval[] intervals;
    private int[] expecteds = {-1, 2, -1};

    @Before
    public void before() {
      intervals = new Interval[3];
      intervals[0] = new Interval(1, 4);
      intervals[1] = new Interval(2, 3);
      intervals[2] = new Interval(3, 4);
    }

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expecteds, BINARY_SEARCH_SOLUTION.solve(intervals));
      Assert.assertArrayEquals(expecteds, TREE_MAP_SOLUTION.solve(intervals));
    }

  }

}
