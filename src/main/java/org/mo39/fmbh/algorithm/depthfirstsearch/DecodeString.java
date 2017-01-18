package org.mo39.fmbh.algorithm.depthfirstsearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an encoded string, return it's decoded string.
 * 
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 * 
 * 
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any digits
 * and that digits are only for those repeat numbers, k. For example, there won't
 * be input like 3a or 2[4].
 * 
 * 
 * Examples:
 * 
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/decode-string/">Decode String</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DecodeString {

  SOLUTION {

    @Override
    public String solve(String s) {
      while (true) {
        int open = -1, close = -1, i = 0;
        for (; i < s.length(); i++) {
          char c = s.charAt(i);
          if (c == '[') open = i;
          else if (c == ']') {
            close = i;
            break;
          }
        }
        if (i >= s.length() && open == -1) break;
        String toRepeat = s.substring(open + 1, close);
        int times = 0, weight = 1;
        while (--open > -1 && Character.isDigit(s.charAt(open))) {
          times += weight * (s.charAt(open) - '0');
          weight *= 10;
        }
        StringBuilder sb = new StringBuilder(s.substring(0, open + 1));
        while (times-- > 0) {
          sb.append(toRepeat);
        }
        s = sb.append(s.substring(close + 1)).toString();
      }
      return s;
    }

  },

  STACK_SOLUTION {

    @Override
    public String solve(String s) {
      // TODO Auto-generated method stub
      return null;
    }

  };

  public abstract String solve(String s);

  public static class TestDecodeString {

    private String s = "2[abc]3[cd]ef";
    private String expected = "abcabccdcdcdef";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(s));
    }


  }

}
