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
      return 0;
    }

  };

  public abstract int solve(int n);

  public static class TestCountNumbersWithUniqueDigits {

    @Test
    public void testSolutions() {
      Z.print(CHEATER.solve(2));

    }

  }

}
