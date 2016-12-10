package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two�s
 * complement method is used.
 *
 *
 * Note:
 *
 * All letters in hexadecimal (a-f) must be in lowercase. The hexadecimal string must not contain
 * extra leading 0s. If the number is zero, it is represented by a single zero character '0';
 * otherwise, the first character in the hexadecimal string will not be the zero character. The
 * given number is guaranteed to fit within the range of a 32-bit signed integer. You must not use
 * any method provided by the library which converts/formats the number to hex directly.
 *
 *
 *
 * Example 1:
 *
 * Input: 26
 *
 * Output: "1a"
 *
 *
 *
 * Example 2:
 *
 * Input: -1
 *
 * Output: "ffffffff"
 *
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/convert-a-number-to-hexadecimal/">Convert A Number To
 *      Hexadecimal</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ConvertANumberToHexadecimal {

  BIT_MANPULATION {

    @Override
    public String solve(int num) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 32; i = i + 4) {
        int sum = 0;
        sum += ((num << i + 0 & 0xa0000000) >>> 31) * 8;
        sum += ((num << i + 1 & 0xa0000000) >>> 31) * 4;
        sum += ((num << i + 2 & 0xa0000000) >>> 31) * 2;
        sum += ((num << i + 3 & 0xa0000000) >>> 31) * 1;
        if (sb.length() == 0 && sum == 0) continue;
        if (sum >= 10) sb.append((char) (sum - 10 + 'a'));
        else sb.append((char) (sum + '0'));
      }
      return sb.length() == 0 ? "0" : sb.toString();
    }

  };

  public abstract String solve(int num);

  public static class TestConvertANumberToHexadecimal {

    private int num = 26;
    private String expected = "1a";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BIT_MANPULATION.solve(num));
    }

  }

}
