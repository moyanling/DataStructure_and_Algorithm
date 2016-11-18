package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

@ProblemSource(LEETCODE)
public enum AddStrings {

  RECURSIVE_SOLUTION {

    StringBuilder sb = new StringBuilder();

    @Override
    public String solve(String num1, String num2) {
      recur(num1, num2, 0, 0);
      return sb.toString();
    }

    private void recur(String num1, String num2, int i, int carry) {
      if (i >= num1.length() && i >= num2.length()) return;
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

    @Test
    public void testSolutions() {
      Z.print(ITERATIVE_SOLUTION.solve("123456789", "987654321"));
    }

  }

}
