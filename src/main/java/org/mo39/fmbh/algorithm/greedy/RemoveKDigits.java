package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-negative integer num represented as a string, remove k digits from
 * the number so that the new number is the smallest possible.
 * 
 * 
 * Note:
 * 
 * The length of num is less than 10002 and will be &ge; k.
 * The given num does not contain any leading zero.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219
 * which is the smallest.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output
 * must not contain leading zeroes.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing
 * which is 0.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/remove-k-digits/">Remove K Digits</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RemoveKDigits {

  RECURSIVE_SOLUTION {

    @Override
    public String solve(String num, int k) {
      StringBuilder sb = new StringBuilder();
      recur(sb, num, num.length() - k, 0);
      if (sb.length() == 0) sb.append('0');
      return sb.toString();
    }

    void recur(StringBuilder sb, String num, int k, int start) {
      if (k <= 0) return;
      int j = -1;
      for (int i = start; i <= num.length() - k; i++) {
        if (j == -1 || num.charAt(i) < num.charAt(j)) j = i;
      }
      if (sb.length() != 0 || num.charAt(j) != '0') sb.append(num.charAt(j));
      recur(sb, num, k - 1, j + 1);
    }

  },

  /**
   * 1. There's no need for a fast break. because there's no process to pick new minimum value and
   * substring will do the iteration as well</br>
   * 2. There's duplicate in the minimum selection. Should be avoid.<br>
   * 
   */
  ITERATIVE_SOLUTION {

    @Override
    public String solve(String num, int k) {
      StringBuilder sb = new StringBuilder();
      k = num.length() - k;
      int i = 0;
      for (; k > 0; k--) {
        int min = -1;
        for (int j = i; j <= num.length() - k; j++) {
          if (min == -1 || num.charAt(j) < num.charAt(min)) min = j;
        }
        if (sb.length() != 0 || num.charAt(min) != '0') sb.append(num.charAt(min));
        i = min + 1;
      }
      if (sb.length() == 0) sb.append('0');
      return sb.toString();
    }

  },

  /**
   * This one is doing removal instead of picking digits.
   */
  STACK_SOLUTION {

    @Override
    public String solve(String num, int k) {
      int digits = num.length() - k;
      char[] stack = new char[num.length()];
      int top = 0;
      for (int i = 0; i < num.length(); i++) {
        char c = num.charAt(i);
        while (top > 0 && stack[top - 1] > c && k > 0) {
          top--;
          k--;
        }
        stack[top++] = c;
      }
      int idx = 0;
      while (idx < digits && stack[idx] == '0') {
        idx++;
      }
      return idx == digits ? "0" : new String(stack, idx, digits - idx);
    }

  };

  public abstract String solve(String num, int k);

  public static class TestRemoveKDigits {

    String num = "1432219";
    int k = 3;
    String expected = "1219";

    @Test
    public void testSolutions() {
      for (RemoveKDigits sol : RemoveKDigits.values()) {
        Assert.assertEquals(expected, sol.solve(num, k));
      }
    }

  }

}
