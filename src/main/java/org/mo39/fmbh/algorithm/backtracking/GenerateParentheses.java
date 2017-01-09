package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/generate-parentheses/">Generate Parentheses</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum GenerateParentheses {

  SOLUTION_0 {

    @Override
    public List<String> solve(int n) {
      List<String> result = new ArrayList<>();
      if (n == 0) return result;
      char[] cur = new char[n * 2];
      cur[0] = '(';
      recur(result, cur, 1, n - 1);
      return result;
    }

    public void recur(List<String> result, char[] cur, int i, int n) {
      if (i >= cur.length - 1) {
        if (n == 0) {
          boolean add = true;
          Stack<Character> stack = new Stack<>();
          for (int j = 0; j < cur.length; j++) {
            if (cur[j] != '(') {
              if (stack.isEmpty()) {
                add = false;
                break;
              }
              cur[j] = ')';
              stack.pop();
            } else {
              stack.push('(');
            }
          }
          if (add) result.add(String.valueOf(cur));
        }
        return;
      }
      char[] put = new char[cur.length], notPut = new char[cur.length];
      System.arraycopy(cur, 0, put, 0, cur.length);
      System.arraycopy(cur, 0, notPut, 0, cur.length);
      put[i] = '(';
      recur(result, put, i + 1, n - 1);
      recur(result, notPut, i + 1, n);
    }

  },

  SOLUTION_1 {

    @Override
    public List<String> solve(int n) {
      List<String> list = new ArrayList<String>();
      recur(list, "", 0, 0, n);
      return list;
    }

    private void recur(List<String> list, String str, int open, int close, int max) {
      if (str.length() == max * 2) {
        list.add(str);
        return;
      }
      if (open < max) recur(list, str + "(", open + 1, close, max);
      if (close < open) recur(list, str + ")", open, close + 1, max);
    }

  },

  SOLUTION_2 {

    @Override
    public List<String> solve(int n) {
      List<String> result = new LinkedList<>();
      char[] sample = new char[2 * n];
      recur(sample, 0, 0, result);
      return result;
    }

    private void recur(char[] s, int open, int closed, List<String> result) {
      if (open + closed == s.length) {
        result.add(new String(s));
        return;
      }
      if (open < s.length / 2) {
        s[open + closed] = '(';
        recur(s, open + 1, closed, result);
      }
      if (open > closed) {
        s[open + closed] = ')';
        recur(s, open, closed + 1, result);
      }
    }

  };

  public abstract List<String> solve(int n);

  public static class TestGenerateParentheses {

    private int n = 3;
    private List<String> expected =
        Lists.newArrayList("((()))", "(()())", "(())()", "()(())", "()()()");

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION_0.solve(n));
      Assert.assertEquals(expected, SOLUTION_1.solve(n));
      Assert.assertEquals(expected, SOLUTION_2.solve(n));
    }

  }

}
