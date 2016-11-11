package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * @see <a href="https://leetcode.com/problems/counting-bits/">Counting Bits</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum CountingBits {

  BOTTOM_UP_METHOD {

    @Override
    public int[] solve(int n) {
      int bias = 0, pow = 1;
      int[] toRet = new int[n + 1];
      for (int i = 1; i < n + 1; i++) {
        if (i == pow) {
          bias = 0;
          pow *= 2;
        }
        toRet[i] = toRet[bias++] + 1;
      }
      return toRet;
    }

  };

  public abstract int[] solve(int n);

  public static class TestCountingBits {

    private int n = 7;
    private int[] expected = {0, 1, 1, 2, 1, 2, 2, 3};

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expected, BOTTOM_UP_METHOD.solve(n));
    }

  }

}
