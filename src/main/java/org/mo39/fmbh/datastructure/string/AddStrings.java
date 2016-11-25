package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and
 * num2.<br/>
 * Note:<br/>
 * The length of both num1 and num2 is < 5100.<br/>
 * Both num1 and num2 contains only digits 0-9.<br/>
 * Both num1 and num2 does not contain any leading zero.<br/>
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @see <a href="https://leetcode.com/problems/add-strings/">Add Strings</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum AddStrings {

  RECURSIVE_SOLUTION {

    StringBuilder sb;

    @Override
    public String solve(String num1, String num2) {
      sb = new StringBuilder();
      recur(num1, num2, 0, 0);
      return sb.toString();
    }

    private void recur(String num1, String num2, int i, int carry) {
      if (i >= num1.length() && i >= num2.length() && carry == 0) return;
      int newDigit = getDigit(num1, i) + getDigit(num2, i) + carry;
      if (newDigit >= 10) {
        newDigit -= 10;
        carry = 1;
      } else carry = 0;
      recur(num1, num2, i + 1, carry);
      sb.append(newDigit);
    }

  },

  /**
   * 1. iterative is better than recursive. <br/>
   * 2. it's nice to add <code>carry == 1</code> to while condition.<br/>
   * 3. use % and / instead of if condition;
   */
  ITERATIVE_SOLUTION {

    @Override
    public String solve(String num1, String num2) {
      StringBuilder sb = new StringBuilder();
      int i = 0, carry = 0;
      while (i < num1.length() || i < num2.length() || carry == 1) {
        int newDigit = getDigit(num1, i) + getDigit(num2, i) + carry;
        carry = newDigit / 10;
        i++;
        sb.insert(0, newDigit % 10);
      }
      return sb.toString();
    }

  };

  protected int getDigit(String s, int len) {
    if (len >= s.length()) return 0;
    return s.charAt(s.length() - len - 1) - '0';
  }

  public abstract String solve(String num1, String num2);

  public static class TestAddStrings {

    private String num1 = "123456789";
    private String num2 = "987654321";
    private String expected = "1111111110";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, ITERATIVE_SOLUTION.solve(num1, num2));
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(num1, num2));
    }

  }

}
