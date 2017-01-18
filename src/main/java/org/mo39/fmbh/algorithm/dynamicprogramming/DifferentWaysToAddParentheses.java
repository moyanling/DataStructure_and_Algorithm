package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string of numbers and operators, return all possible results from computing
 * all the different possible ways to group numbers and operators. The valid operators
 * are +, - and *.
 * 
 * Example 1
 * Input: "2-1-1". 
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 * 
 * Example 2
 * Input: "2*3-4*5" 
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/different-ways-to-add-parentheses/">Different Ways To
 *      Add Parentheses</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DifferentWaysToAddParentheses {

  RECURSIVE_SOLUTION_0 {


    final Set<Character> set = new HashSet<>(Arrays.asList('-', '+', '*'));

    @Override
    public List<Integer> solve(String input) {
      List<Integer> operators = new ArrayList<>();
      for (int i = 0; i < input.length(); i++) {
        if (set.contains(input.charAt(i))) operators.add(i);
      }
      return recur(input, 0, input.length() - 1, operators);
    }

    private List<Integer> recur(String input, int start, int end, List<Integer> ops) {
      if (!hasOperator(input, start, end)) {
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(input.substring(start, end + 1)));
        return list;
      }
      List<Integer> result = new ArrayList<>();
      for (int i = 0; i < ops.size(); i++) {
        int p = ops.get(i);
        char operator = input.charAt(p);
        List<Integer> left = recur(input, start, p - 1, sub(ops, 0, i));
        List<Integer> right = recur(input, p + 1, end, sub(ops, i + 1, ops.size()));
        for (int j = 0; j < left.size(); j++) {
          for (int k = 0; k < right.size(); k++) {
            result.add(cal(operator, left.get(j), right.get(k)));
          }
        }
      }
      return result;
    }

    private boolean hasOperator(String input, int i, int j) {
      for (int k = i; k <= j; k++) {
        if (set.contains(input.charAt(k))) return true;
      }
      return false;
    }

    private List<Integer> sub(List<Integer> operators, int i, int j) {
      List<Integer> toRet = new ArrayList<>();
      for (int k = i; k < j; k++) {
        toRet.add(operators.get(k));
      }
      return toRet;
    }

  },

  RECURSIVE_SOLUTION_1 {

    @Override
    public List<Integer> solve(String input) {
      List<Integer> res = new ArrayList<Integer>();
      for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == '-' || c == '+' || c == '*') {
          String a = input.substring(0, i);
          String b = input.substring(i + 1);
          List<Integer> al = solve(a);
          List<Integer> bl = solve(b);
          for (int x : al) {
            for (int y : bl) {
              res.add(cal(c, x, y));
            }
          }
        }
      }
      if (res.size() == 0) res.add(Integer.valueOf(input));
      return res;
    }

  },

  TOP_DOWN_WITH_MEMO {

    Map<String, List<Integer>> memo;

    @Override
    public List<Integer> solve(String input) {
      memo = new HashMap<>();
      return recur(input);
    }

    private List<Integer> recur(String input) {
      if (memo.containsKey(input)) return memo.get(input);
      List<Integer> result = new ArrayList<Integer>();
      for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == '-' || c == '+' || c == '*') {
          String a = input.substring(0, i);
          String b = input.substring(i + 1);
          List<Integer> al = recur(a);
          List<Integer> bl = recur(b);
          for (int x : al) {
            for (int y : bl) {
              result.add(cal(c, x, y));
            }
          }
        }
      }
      if (result.size() == 0) result.add(Integer.valueOf(input));
      if (!memo.containsKey(input)) memo.put(input, result);
      return result;
    }

  };

  public abstract List<Integer> solve(String input);

  protected int cal(char operator, int a, int b) {
    //@formatter:off
    switch (operator) {
      case '-': return a - b;
      case '+': return a + b;
      default:  return a * b;
    }
    //@formatter:on
  }

  public static class TestDifferentWaysToAddParentheses {

    private String input = "2*3-4*5";
    private Integer[] expected = {-34, -14, -10, -10, 10};

    @Test
    public void testSolutions() {
      Assert.assertThat(RECURSIVE_SOLUTION_0.solve(input), containsInAnyOrder(expected));
      Assert.assertThat(RECURSIVE_SOLUTION_1.solve(input), containsInAnyOrder(expected));
      Assert.assertThat(TOP_DOWN_WITH_MEMO.solve(input), containsInAnyOrder(expected));
    }

  }


}
