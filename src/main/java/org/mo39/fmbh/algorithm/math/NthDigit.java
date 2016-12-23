package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8,
 * 9, 10, 11, ... 
 * 
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n <
 * 231).
 * 
 * 
 * Example 1:
 * 
 * Input:
 * 3
 * 
 * Output:
 * 3
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * 11
 * 
 * Output:
 * 0
 * 
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a
 * 0, which is part of the number 10.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/nth-digit/">Nth Digit</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NthDigit {

  /**
   * Two changes are made in order to pass all the test cases.</br>
   * 1. use <code>int b = 10</code> and <code>b *= 10</code> instead of
   * {@link Math#pow(double, double)} to improve speed;</br>
   * 2. use <code>count > n - len</code> instead of <code>count + len > n</code> to eliminate
   * integer overflow
   * 
   */
  SOLUTION {

    @Override
    public int solve(int n) {
      int count = 1, len = 1, b = 10;
      for (int i = 1;; i++) {
        if (i == b) {
          len++;
          b *= 10;
        }
        if (count > n - len) return String.valueOf(i).charAt(-count + n) - '0';
        count += len;
      }
    }

  };

  public abstract int solve(int n);

  public static class TestNthDigit {

    @Test
    public void testSolutions() {
      Z.print(SOLUTION.solve(2147483647));
    }

  }

}
