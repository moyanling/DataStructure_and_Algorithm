package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * There is a fence with n posts, each post can be painted with one of the k colors. <br>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same
 * color. <br>
 * Return the total number of ways you can paint the fence. <br>
 * Note: n and k are non-negative integers.<br>
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/paint-fence/">Paint Fence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PaintFence {

  /**
   * //TODO
   *
   */
  RECURSIVE_SOLUTION {

    @Override
    public int solve(int n, int k) {
      if (k == 0 || n <= 0) return 1;
      if (n == 1 && k >= 1) return k;
      if (n == 2 && k >= 1) return k * k;
      return (solve(n - 1, k) + solve(n - 2, k)) * (k - 1);
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int n, int k) {
      int a = 0, b = k, c = k * k;
      while (n-- > 0) {
        a = b;
        b = c;
        c = (a + b) * (k - 1);
      }
      return a;
    }

  };

  public abstract int solve(int n, int k);

  public static class TestPaintFence {

    private int n = 5;
    private int k = 3;
    private int expected = 180;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(n, k));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD.solve(n, k));
    }

  }
}