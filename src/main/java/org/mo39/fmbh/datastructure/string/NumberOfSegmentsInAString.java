package org.mo39.fmbh.datastructure.string;

import static java.lang.Character.isWhitespace;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.<br/>Please note that the string does not contain any non-printable characters.<br/>Example:<br/>Input: "Hello, my name is John"<br/>Output: 5
 * @see <a href="https://leetcode.com/problems/number-of-segments-in-a-string/">Number Of Segments In A String</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NumberOfSegmentsInAString {

  SOLUTION {

    @Override
    public int solve(String s) {
      int count = 0;
      s = s.trim();
      if (s.length() == 0) return 0;
      char[] arr = s.toCharArray();
      for (int i = 0; i < arr.length; i++) {
        if (isWhitespace(arr[i]) && i > 0 && !isWhitespace(arr[i - 1])) count++;
      }
      return count + 1;
    }

  },

  /**
   * It is quite funny that the split function working on Strings like " X " reserves a empty String
   * "" at the beginning of the splited array but does not reserve such empty String at the end.
   */
  REGULAR_EXPRESSION {

    @Override
    public int solve(String s) {
      return ("X " + s).split("\\s+").length - 1;
    }

  };

  public abstract int solve(String s);

  public static class TestNumberOfSegmentsInAString {

    private String s = "Hello, my name is mo39.fmbh";
    private int expected = 5;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(s));
      Assert.assertEquals(expected, REGULAR_EXPRESSION.solve(s));
    }

  }

}