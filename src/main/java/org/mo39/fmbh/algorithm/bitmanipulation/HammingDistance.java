package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * The Hamming distance between two integers is the number of positions at which
 * the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Note:
 * 0 &le; x, y &lt; 2<sup>31</sup>.
 * 
 * Example:
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/hamming-distance/">Hamming Distance</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum HammingDistance {

  SOLUTION {

    @Override
    public int solve(int x, int y) {
      int count = 0;
      for (int i = 0; i < 32; i++) {
        if ((x >>> i & 0x1) != (y >>> i & 0x1)) count++;
      }
      return count;
    }

  },

  /**
   * "corresponding bits are different" -> XOR
   */
  ONE_LINER {

    @Override
    public int solve(int x, int y) {
      return Integer.bitCount(x ^ y);
    }

  };


  public abstract int solve(int x, int y);

  public static class TestHammingDistance {

    @Test
    public void testSolutions() {
      Assert.assertEquals(2, SOLUTION.solve(1, 4));
      Assert.assertEquals(2, ONE_LINER.solve(1, 4));
    }

  }

}
