package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 
 * 
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * 
 * 
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * 
 * 
 * Note: The sequence of integers will be represented as a string.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/count-and-say/">Count And Say</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CountAndSay {

  SOLUTION {

    @Override
    public String solve(int n) {
      String toRet = "1";
      int count = 1;
      while (++count <= n) {
        char[] arr = toRet.toCharArray();
        int len = 1;
        char c = arr[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
          if (arr[i] == c) len++;
          else {
            sb.append(len).append(c);
            c = arr[i];
            len = 1;
          }
        }
        sb.append(len).append(c);
        toRet = sb.toString();
      }
      return toRet;
    }

  };

  public abstract String solve(int n);

  public static class TestCountAndSay {

    private String expected = "21";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(3));
    }

  }

}
