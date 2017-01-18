package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a positive integer, output its complement number. The complement strategy
 * is to flip the bits of its binary representation.
 * 
 * Note:
 * 
 * The given integer is guaranteed to fit within the range of a 32-bit signed
 * integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits),
 * and its complement is 010. So you need to output 2.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and
 * its complement is 0. So you need to output 0.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/number-complement/">Number Complement</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NumberComplement {

  SOLUTION {

    @Override
    public int solve(int num) {
      int i = 32;
      while ((num >>> --i & 1) == 0) {
        num = 1 << i | num;
      }
      return ~num;
    }

  };

  public abstract int solve(int num);

  public static class TestNumberComplement {

    private int n = 5;
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(n));
    }

  }


}
