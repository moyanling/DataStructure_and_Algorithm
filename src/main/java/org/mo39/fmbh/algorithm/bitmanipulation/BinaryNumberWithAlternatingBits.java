package org.mo39.fmbh.algorithm.bitmanipulation;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * Given a positive integer, check whether it has alternating bits: namely, if
 * two adjacent bits will always have different values.
 *
 * Example 1:
 *
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 *
 *
 *
 * Example 2:
 *
 * Input: 7
 * Output: False
 * Explanation:
 * The binary representation of 7 is: 111.
 *
 *
 *
 * Example 3:
 *
 * Input: 11
 * Output: False
 * Explanation:
 * The binary representation of 11 is: 1011.
 *
 *
 *
 * Example 4:
 *
 * Input: 10
 * Output: True
 * Explanation:
 * The binary representation of 10 is: 1010.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/binary-number-with-alternating-bits/">Binary Number
 *     With Alternating Bits</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum BinaryNumberWithAlternatingBits {
  SOLUTION {
    @Override
    public boolean hasAlternatingBits(int n) {
      // TODO hopefully binary solution is the last thing I need to worry about.
      return false;
    }
  },

  /**
   * First idea is to enum and try using {@link Integer#toBinaryString(int)} function. This has to
   * be done manually in interview. However, the results are, 0, 1, 5, 10, 21. No clear pattern is
   * found. So just check the string.
   */
  SOLUTION_1 {
    @Override
    public boolean hasAlternatingBits(int n) {
      String s = Integer.toBinaryString(n);
      char[] buffer = new char[s.length()];
      for (int i = 0; i < buffer.length; i++) {
        buffer[i] = i % 2 == 0 ? '1' : '0';
      }
      return String.valueOf(buffer).equals(s);
    }
  },

  /** Of course we can use REGX to match the String instead of doing that manually. */
  SOLUTION_2 {
    @Override
    public boolean hasAlternatingBits(int n) {
      return Integer.toBinaryString(n).matches("(10)*1?");
    }
  };

  public abstract boolean hasAlternatingBits(int n);

  public static class BinaryNumberWithAlternatingBitsTest {

    @Test
    public void testSolutions() {
      Assert.assertEquals(true, SOLUTION_1.hasAlternatingBits(5));
      Assert.assertEquals(true, SOLUTION_1.hasAlternatingBits(21));
      Assert.assertEquals(false, SOLUTION_1.hasAlternatingBits(11));

      Assert.assertEquals(true, SOLUTION_2.hasAlternatingBits(5));
      Assert.assertEquals(true, SOLUTION_2.hasAlternatingBits(21));
      Assert.assertEquals(false, SOLUTION_2.hasAlternatingBits(11));
    }
  }
}
