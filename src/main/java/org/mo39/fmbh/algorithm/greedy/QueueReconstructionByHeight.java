package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

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
   * Time Limit Exceeded. Time complexity is <b>O(n^3)</b>.
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

  };

  public abstract int[][] solve(int[][] people);

  public static class TestQueueReconstructionByHeight {

    private int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
    private int[][] expecteds = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};

    @Test
    public void testTLESolution() {
      TLE_SOLUTION.solve(people);
      Assert.assertArrayEquals(expecteds, people);
    }

  }

}
