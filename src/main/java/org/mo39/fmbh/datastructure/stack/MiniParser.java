package org.mo39.fmbh.datastructure.stack;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.common.interfaces.NestedInteger;

/**
 * <pre>
 * Given a nested list of integers represented as a string, implement a parser
 * to deserialize it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Note:
 * You may assume that the string is well-formed:
 * 
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 * 
 * 
 * 
 * Example 1:
 * 
 * Given s = "324",
 * 
 * You should return a NestedInteger object which contains a single integer 324.
 * 
 * 
 * 
 * Example 2:
 * 
 * Given s = "[123,[456,[789]]]",
 * 
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 *     i.  An integer containing value 456.
 *     ii. A nested list with one element:
 *          a. An integer containing value 789.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/mini-parser/">Mini Parser</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MiniParser {

  SOLUTION {

    @Override
    public NestedInteger solve(String s) {
      NestedInteger result = null;
      Stack<NestedInteger> stack = new Stack<>();
      Integer value = null, sign = 1;
      for (char c : s.toCharArray()) {
        if (c == '[') stack.push(new NestedInteger());
        else if (Character.isDigit(c)) value = value == null ? c - '0' : 10 * value + c - '0';
        else if (c == ',' || c == ']') {
          if (value != null) {
            stack.peek().add(new NestedInteger(sign * value));
            value = null;
            sign = 1;
          }
          if (c == ']') {
            result = stack.pop();
            if (!stack.isEmpty()) stack.peek().add(result);
          }
        } else sign = -1;
      }
      return result == null ? new NestedInteger(sign * value) : result;
    }

  };

  public abstract NestedInteger solve(String s);

}
