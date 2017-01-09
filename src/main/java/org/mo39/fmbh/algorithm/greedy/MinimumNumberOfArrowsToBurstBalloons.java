package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * There are a number of spherical balloons spread in two-dimensional space. For
 * each balloon, provided input is the start and end coordinates of the horizontal
 * diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates
 * of start and end of the diameter suffice. Start is always smaller than end.
 * There will be at most 10^4 balloons.
 * 
 * An arrow can be shot up exactly vertically from different points along the
 * x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart
 * &le; x &le; xend. There is no limit to the number of arrows that can be shot.
 * An arrow once shot keeps travelling up infinitely. The problem is to find the
 * minimum number of arrows that must be shot to burst all balloons. 
 * 
 * Example:
 * 
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8]
 * and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/">Minimum
 *      Number Of Arrows To Burst Balloons</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MinimumNumberOfArrowsToBurstBalloons {

  SOLUTION {

    @Override
    public int solve(int[][] points) {
      if (points.length < 1) return 0;
      Arrays.sort(points, (p1, p2) -> p1[1] - p2[1]);
      int count = 1, posi = points[0][1];
      for (int i = 0; i < points.length; i++) {
        if (points[i][0] > posi) {
          count++;
          posi = points[i][1];
        }
      }
      return count;
    }

  };

  public abstract int solve(int[][] points);

  public static class TestMinimumNumberOfArrowsToBurstBalloons {

    private int[][] points =
        {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}};
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(points));
    }

  }

}
