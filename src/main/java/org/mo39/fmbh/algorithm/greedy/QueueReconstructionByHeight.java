package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * 
 * Note:
 * The number of people is less than 1,100.
 * 
 * 
 * Example
 * 
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/queue-reconstruction-by-height/">Queue Reconstruction
 *      By Height</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum QueueReconstructionByHeight {

  /**
   * Time Limit Exceeded. Time complexity is <b>O(n^3)</b>.</br>
   * This one looks like a brute force selection sort. Except the greedy step that it choosing the
   * one from candidate. The step choose the one with lowest height so that it won't affect other
   * candidates' position. For example, at the second time of loop, [5,0], [7,0] are sorted and thus
   * the candidates are [5,2], [6,1] and [7,1]. Then [5,2] is choose to be the next one and the
   * sorted array becomes [5,0], [7,0], [5,2]. Ohterwise, if [6,1] is choosed from candidates, [5,2]
   * has to be changed to [5,3] to satisfy the rule.
   * 
   */
  TLE_SOLUTION {

    @Override
    public int[][] solve(int[][] people) {
      for (int i = 0; i < people.length; i++) {
        Integer k = null;
        int j = i;
        for (; j < people.length; j++) {
          if ((k == null || people[j][0] < people[k][0]) && isGreater(people, j, i)) k = j;
        }
        Z.swap(people, i, k);
      }
      return people;
    }

    private boolean isGreater(int[][] people, int j, int i) {
      int count = 0;
      for (int k = 0; k < i; k++) {
        if (people[k][0] >= people[j][0]) count++;
      }
      return count == people[j][1];
    }

  },

  SOLUTION {

    @Override
    public int[][] solve(int[][] people) {
      Arrays.sort(people, (o1, o2) -> o2[0] - o1[0] == 0 ? o1[1] - o2[1] : o2[0] - o1[0]);
      List<int[]> list = new LinkedList<>();
      for (int[] p : people) {
        list.add(p[1], p);
      }
      int[][] toRet = new int[people.length][];
      list.toArray(toRet);
      return toRet;
    }

  };

  public abstract int[][] solve(int[][] people);

  public static class TestQueueReconstructionByHeight {

    private int[][] people = {{7, 1}, {4, 4}, {7, 0}, {5, 0}, {6, 1}, {5, 2}};
    private int[][] expecteds = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};

    @Test
    public void testTLESolution() {
      Assert.assertArrayEquals(expecteds, TLE_SOLUTION.solve(people));
    }

    @Test
    public void testSolution() {
      Assert.assertArrayEquals(expecteds, SOLUTION.solve(people));
    }

  }

}
