package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s
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
      for (int i = 0; i < 32; i = i + 4) {
        Z.print((num << i + 0 & 0xa0000000) >>> 31);
        int sum = 0;
        sum += (num << i + 0 & 0xa0000000) >>> 31 * 1;
        sum += (num << i + 1 & 0xa0000000) >>> 31 * 2;
        sum += (num << i + 2 & 0xa0000000) >>> 31 * 4;
        sum += (num << i + 3 & 0xa0000000) >>> 31 * 8;
        // if (i == 0 && sum == 0) continue;
        char c;
        if (sum >= 10) c = (char) (sum - 10 + 'a');
        else c = (char) (sum + '0');
//        Z.print(c);
      }
      return null;
    }

  };

  public abstract String solve(int num);

  public static class TestConvertANumberToHexadecimal {

    private int num0 = -1;
    private int num1 = 26;

    @Test
    public void testSolutions() {
      BIT_MANPULATION.solve(num0);
      Z.print("");
      BIT_MANPULATION.solve(num1);
    }

  }

}
