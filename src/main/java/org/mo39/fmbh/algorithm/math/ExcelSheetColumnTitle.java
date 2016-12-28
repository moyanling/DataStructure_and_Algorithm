package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 * 
 * For example:
 * 
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/excel-sheet-column-title/">Excel Sheet Column
 *      Title</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ExcelSheetColumnTitle {

  SOLUTION {

    @Override
    public String solve(int n) {
      StringBuilder sb = new StringBuilder();
      while (--n > -1) {
        sb.append((char) ('A' + n % 26));
        n /= 26;
      }
      return sb.reverse().toString();
    }

  };

  public abstract String solve(int n);

  public static class TestExcelSheetColumnTitle {

    private int n = 52;
    private String expected = "AZ";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(n));
    }

  }

}
