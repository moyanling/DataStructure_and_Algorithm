package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).<br/>Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).<br/>Example:<br/>Input:<br/>[[0,0],[1,0],[2,0]]<br/>Output:<br/>2<br/>Explanation:<br/>The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 * @see <a href="https://leetcode.com/problems/number-of-boomerangs/">Number Of Boomerangs</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NumberOfBoomerangs {

  /**
   * I tried a memo to saev half of the calculation, but it seems not that helpful.
   */
  HASHMAP_SOLUTION {

    @Override
    public int solve(int[][] points) {
      int total = 0;
      double[][] memo = new double[points.length][points.length];
      for (int i = 0; i < points.length; i++) {
        int sum = 0;
        Map<Double, Integer> map = new HashMap<>();
        for (int j = 0; j < points.length; j++) {
          if (i == j) continue;
          if (i < j) memo[i][j] = distanceOf(points, i, j);
          else memo[i][j] = memo[j][i];
          Integer count = null;
          if ((count = map.get(memo[i][j])) == null) map.put(memo[i][j], 1);
          else {
            sum += count * 2;
            map.put(memo[i][j], count + 1);
          }
        }
        total += sum;
      }
      return total;
    }

  };

  public static double distanceOf(int[][] points, int i, int j) {
    return Math
        .sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
  }

  public abstract int solve(int[][] points);

  public static class TestNumberOfBoomerangs {

    private int[][] points = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int expected = 20;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, HASHMAP_SOLUTION.solve(points));
    }
  }

}