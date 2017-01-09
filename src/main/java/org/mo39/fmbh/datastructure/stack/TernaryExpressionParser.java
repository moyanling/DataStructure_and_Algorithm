package org.mo39.fmbh.datastructure.stack;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. 
 * You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, 
 * T and F (T and F represent True and False respectively).
 * 
 * Note:
 * 
 * The length of the given string is &le; 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most languages).
 * The condition will always be either T or F. That is, the condition will never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T or F.
 * 
 * 
 * Example 1:
 * 
 * Input: "T?2:3"
 * 
 * Output: "2"
 * 
 * Explanation: If true, then result is 2; otherwise result is 3.
 * 
 * 
 * Example 2:
 * 
 * Input: "F?1:T?4:5"
 * 
 * Output: "4"
 * 
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 * 
 *              "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 *           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 *           -> "4"                                    -> "4"
 * 
 * 
 * Example 3:
 * 
 * Input: "T?T?F:5:3"
 * 
 * Output: "F"
 * 
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 * 
 *              "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 *           -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 *           -> "F"                                    -> "F"
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/ternary-expression-parser/">Ternary Expression
 *      Parser</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum TernaryExpressionParser {

  REGULAR_EXPRESSION {

    @Override
    public String solve(String expression) {
      Pattern p = Pattern.compile(".*((T|F)\\?.\\:.(?!\\?)).*");
      while (expression.length() > 3) {
        Matcher m = p.matcher(expression);
        m.matches();
        String ternary = m.group(1);
        expression = expression.replaceFirst("((T|F)\\?.\\:.(?!\\?))", ternary.charAt(0) == 'T'
            ? String.valueOf(ternary.charAt(2)) : String.valueOf(ternary.charAt(4)));
      }
      return expression;
    }

  },

  STACK_SOLUTION {

    @Override
    public String solve(String expression) {
      if (expression == null || expression.length() == 0) return "";
      Stack<Character> stack = new Stack<>();
      for (int i = expression.length() - 1; i >= 0; i--) {
        if (!stack.isEmpty() && stack.peek() == '?') {
          stack.pop(); // pop '?'
          char first = stack.pop();
          stack.pop(); // pop ':'
          char second = stack.pop();
          if (expression.charAt(i) == 'T') stack.push(first);
          else stack.push(second);
        } else stack.push(expression.charAt(i));
      }
      return String.valueOf(stack.peek());
    }

  },

  SOLUTION {

    @Override
    public String solve(String expression) {
      return String.valueOf(recur(expression, 0));
    }

    public char recur(String expression, int i) {
      if (i + 1 < expression.length() && expression.charAt(i + 1) != '?'
          || i >= expression.length() - 1)
        return expression.charAt(i);
      if (expression.charAt(i) == 'T') return recur(expression, i + 2);
      int count = 0;
      for (i += 2; i < expression.length(); i++) {
        if (expression.charAt(i) == '?') count++;
        if (expression.charAt(i) == ':') {
          if (count == 0) break;
          count--;
        }
      }
      return recur(expression, i + 1);
    }

  };

  public abstract String solve(String expression);

  public static class TestTernaryExpressionParser {

    private String expression = "F?T:F?T?1:2:F?3:4";
    private String expected = "4";

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, REGULAR_EXPRESSION.solve(expression));
      Assert.assertEquals(expected, STACK_SOLUTION.solve(expression));
      Assert.assertEquals(expected, SOLUTION.solve(expression));
    }

  }

}
