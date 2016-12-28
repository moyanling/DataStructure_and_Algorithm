package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Winter is coming! Your first job during the contest is to design a standard
 * heater with fixed warm radius to warm all the houses.
 * 
 * Now, you are given positions of houses and heaters on a horizontal line, find
 * out minimum radius of heaters so that all houses could be covered by those
 * heaters.
 * 
 * So, your input will be the positions of houses and heaters seperately, and
 * your expected output will be the minimum radius standard of heaters.
 * 
 * Note:
 * 
 * Numbers of houses and heaters you are given are non-negative and will not exceed
 * 25000.
 * Positions of houses and heaters you are given are non-negative and will not
 * exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the
 * radius 1 standard, then all the houses can be warmed.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to
 * use radius 1 standard, then all the houses can be warmed.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/heaters/">Heaters</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Heaters {

  /**
   * Find the closest heater to each house and take maximum of the closest distances.<br/>
   * Take care of the index.
   */
  BINARY_SEARCH {

    @Override
    public int solve(int[] houses, int[] heaters) {
      Arrays.sort(houses);
      Arrays.sort(heaters);
      int radius = Integer.MIN_VALUE;
      for (int h : houses) {
        int posi = Arrays.binarySearch(heaters, h);
        if (posi < 0) posi = -posi - 1;
        int distance = 0;
        if (posi == heaters.length) distance = h - heaters[posi - 1];
        else if (posi == 0) distance = heaters[posi] - h;
        else distance = Math.min(heaters[posi] - h, h - heaters[posi - 1]);
        radius = Math.max(radius, distance);
      }
      return radius;
    }

  };

  public abstract int solve(int[] houses, int[] heaters);

  public static class TestHeaters {

    private int[] houses = {1, 2, 3};
    private int[] heaters = {2};
    private int expected = 1;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BINARY_SEARCH.solve(houses, heaters));
    }

  }

}
