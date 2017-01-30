package org.mo39.fmbh.algorithm.reversereverse;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>

 * </pre>
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string-ii/">Reverse Words In A String II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ReverseWordsInAStringII {

  SOLUTION {

    @Override
    public void solve(char[] s) {
      reverse(s, 0, s.length - 1);
      int start = 0;
      for (int i = 0; i < s.length; i++) {
        if (s[i] == ' ') {
          reverse(s, start, i - 1);
          start = i + 1;
        }
      }
      reverse(s, start, s.length - 1);
    }

    public void reverse(char[] s, int start, int end) {
      for (; start < end; start++, end--) {
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
      }
    }

  };

  public abstract void solve(char[] s);

}