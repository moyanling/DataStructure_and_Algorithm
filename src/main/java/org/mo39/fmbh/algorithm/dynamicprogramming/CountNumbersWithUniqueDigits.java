package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * @see <a href="https://leetcode.com/problems/count-numbers-with-unique-digits/">Count Numbers with
 *      Unique Digits</a>
 *
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum CountNumbersWithUniqueDigits {

  CHEATER {

    private int[] answer = {10, 91,};

    @Override
    public int solve(int n) {
      // TODO
      return answer[n];
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int n) {
      n = Math.min(n, 10);
      int count = 1, pre = 9;
      for (int i = 1; i <= n; i++) {
        count += pre;
        pre *= 10 - i;
      }
      return count;
    }

  };

  public abstract int solve(int n);

  public static class TestCountNumbersWithUniqueDigits {

    @Test
    public void testSolutions() {
      Z.print(BOTTOM_UP_METHOD.solve(114));

    }

  }

}
