package org.mo39.fmbh.datastructure.stack;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/valid-parentheses/">Valid Parentheses</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ValidParentheses {

  SOLUTION {

    @Override
    public boolean solve(String s) {
      Stack<Character> stack = new Stack<Character>();
      for (char c : s.toCharArray()) {
        if (c == '(') stack.push(')');
        else if (c == '{') stack.push('}');
        else if (c == '[') stack.push(']');
        else if (stack.isEmpty() || stack.pop() != c) return false;
      }
      return stack.isEmpty();
    }

  };

  public abstract boolean solve(String s);

}
