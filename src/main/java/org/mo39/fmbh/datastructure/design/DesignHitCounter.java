package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * 
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that
 * calls are being made to the system in chronological order (ie, the timestamp is monotonically
 * increasing). You may assume that the earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 * 
 * Example:
 * 
 * HitCounter counter = new HitCounter();
 * 
 * // hit at timestamp 1. counter.hit(1);
 * 
 * // hit at timestamp 2. counter.hit(2);
 * 
 * // hit at timestamp 3. counter.hit(3);
 * 
 * // get hits at timestamp 4, should return 3. counter.getHits(4);
 * 
 * // hit at timestamp INTERVAL. counter.hit(INTERVAL);
 * 
 * // get hits at timestamp INTERVAL, should return 4. counter.getHits(INTERVAL);
 * 
 * // get hits at timestamp 301, should return 3. counter.getHits(301);
 * 
 * 
 * 
 * Follow up: What if the number of hits per second could be very large? Does your design scale?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/design-hit-counter/">Design Hit Counter</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DesignHitCounter {

  /**
   * The time complexity is <b>O(log(n))</b> where n is the number of hits at different
   * timestamp.</br>
   * e.g. repeatedly hit at 1 second 10 times, n equals 1.
   */
  BINARY_SEARCH_SOLUTION {

    List<TimeAndCount> list;

    @Override
    public void init() {
      list = new ArrayList<>();
    }

    @Override
    public void hit(int timestamp) {
      if (list.size() != 0) {
        if (timestamp == list.get(list.size() - 1).timestamp) list.get(list.size() - 1).count++;
        else list.add(new TimeAndCount(timestamp, list.get(list.size() - 1).count + 1));
      } else list.add(new TimeAndCount(timestamp, 1));
    }


    @Override
    public int getHits(int timestamp) {
      if (list.size() == 0) return 0;
      int index = Collections.binarySearch(list, new TimeAndCount(timestamp - INTERVAL, 0));
      int pre = 0;
      if (index < 0) {
        index = -index - 1;
        if (index == 0 || index == list.size()) pre = 0;
        else pre = list.get(index - 1).count;
      } else pre = list.get(index).count;
      int cur = list.get(list.size() - 1).count;
      if (list.get(list.size() - 1).timestamp + INTERVAL < timestamp) cur = 0;
      return cur - pre;
    }

  },

  /**
   * The time complexity is <b>O(n)</b> where n is the number of hits</br>
   * e.g. repeatedly hit at 1 second 10 times, n equals 10.
   * <p>
   * Personally I like this one.
   */
  QUEUE_SOLUTION {

    Queue<Integer> q = null;

    @Override
    public void init() {
      q = new LinkedList<Integer>();
    }

    @Override
    public void hit(int timestamp) {
      q.offer(timestamp);
    }

    @Override
    public int getHits(int timestamp) {
      while (!q.isEmpty() && timestamp - q.peek() >= 300) {
        q.poll();
      }
      return q.size();
    }

  },


  ARRAY_SOLUTION {

    @Override
    public void init() {
      // TODO Auto-generated method stub

    }

    @Override
    public void hit(int timestamp) {
      // TODO Auto-generated method stub

    }

    @Override
    public int getHits(int timestamp) {
      // TODO Auto-generated method stub
      return 0;
    }

  };

  private static class TimeAndCount implements Comparable<TimeAndCount> {

    public final int timestamp;
    public int count;

    public TimeAndCount(int timestamp, int count) {
      this.timestamp = timestamp;
      this.count = count;
    }

    @Override
    public int compareTo(TimeAndCount o) {
      return this.timestamp - o.timestamp;
    }

    @Override
    public String toString() {
      return String.format("<%d,%d>", this.timestamp, this.count);
    }

  }

  /** Initialize your data structure here. */
  public abstract void init();

  /**
   * Record a hit.
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public abstract void hit(int timestamp);

  /**
   * Return the number of hits in the past 5 minutes.
   * 
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public abstract int getHits(int timestamp);

  public final static int INTERVAL = 300;

  public static class TestDesignHitCounter {

    public void verify(DesignHitCounter sol) {
      sol.init();
      sol.hit(2);
      sol.hit(3);
      sol.hit(4);
      Assert.assertEquals(3, sol.getHits(300));
      Assert.assertEquals(3, sol.getHits(301));
      Assert.assertEquals(2, sol.getHits(302));
      Assert.assertEquals(1, sol.getHits(303));
      Assert.assertEquals(0, sol.getHits(304));
      sol.hit(501);
      Assert.assertEquals(1, sol.getHits(600));
    }

    @Test
    public void TestSolutions() {
      verify(BINARY_SEARCH_SOLUTION);
    }

  }

}
