package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given two binary strings, return their sum (also a binary string).
 * 
 * 
 * 
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 * </pre>
 * 
 * Always use append and reverse instead of insert.
 * 
 * @see <a href=
 *      "http://stackoverflow.com/questions/5931261/java-use-stringbuilder-to-insert-at-the-beginning">
 *      Complexity of inserting at the beginning</a>
 * @see <a href="https://leetcode.com/problems/add-binary/">Add Binary</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum AddBinary {

  SOLUTION {

    @Override
    public String solve(String a, String b) {
      int carry = 0, i = a.length() - 1, j = b.length() - 1;
      StringBuilder sb = new StringBuilder();
      while (i > -1 || j > -1 || carry == 1) {
        int r = (i > -1 ? a.charAt(i--) - '0' : 0) + (j > -1 ? b.charAt(j--) - '0' : 0) + carry;
        carry = r / 2;
        sb.append(r % 2);
      }
      return sb.reverse().toString();
    }

  };

  public abstract String solve(String a, String b);

  public static class TestAddBinary {

    private String a = "11";
    private String b = "1";
    private String expected = "100";

    @Test
    public void testSolutsions() {
      Assert.assertEquals(expected, SOLUTION.solve(a, b));
    }

  }

}
