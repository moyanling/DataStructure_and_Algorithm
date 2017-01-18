package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * 
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code sequence.
 * Sorry about that.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/gray-code/">Gray Code</a>
 * @see <a href=https://en.wikipedia.org/wiki/Gray_code>Gray code</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum GrayCode {

  /**
   * This is not the instance Leetcode asked for, but it's good to go.<b>O(n^2)</b> time
   * complextity, not too bad. This can be improved by bit manipulate directly on the integer
   * instead of storing each one bit.
   */
  REFLECTION_0 {

    @Override
    public List<Integer> solve(int n) {
      if (n == 0) return new ArrayList<>(Arrays.asList(0));
      List<List<Integer>> result = new ArrayList<>();
      result.add(new LinkedList<>(Arrays.asList(0)));
      result.add(new LinkedList<>(Arrays.asList(1)));
      int count = 1;
      while (count++ < n) {
        for (int i = result.size() - 1; i > -1; i--) {
          result.add(new LinkedList<>(result.get(i)));
        }
        for (int i = 0; i < result.size(); i++) {
          result.get(i).add(0, i < result.size() / 2 ? 0 : 1);
        }
      }
      return result.stream().map(code -> toNum(code)).collect(Collectors.toList());
    }

  },

  REFLECTION_1 {

    @Override
    public List<Integer> solve(int n) {
      List<Integer> result = new ArrayList<>(Arrays.asList(0));
      for (int count = 0; count < n; count++) {
        int prefix = 1 << count;
        for (int i = result.size() - 1; i > -1; i--) {
          result.add(prefix | result.get(i));
        }
      }
      return result;
    }

  },


  /**
   * //TODO
   */
  LINE_ARRANGEMENT {

    @Override
    public List<Integer> solve(int n) {
      List<Integer> result = new LinkedList<>();
      for (int i = 0; i < 1 << n; i++) {
        result.add(i ^ i >>> 1);
      }
      return result;
    }

  };

  protected int toNum(List<Integer> code) {
    int result = 0, weight = 1;
    for (int i = 0; i < code.size(); i++) {
      result += weight * code.get(i);
      weight *= 2;
    }
    return result;
  }

  public abstract List<Integer> solve(int n);

  public static class TestGrayCode {

    private int n = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(new ArrayList<>(Arrays.asList(0, 2, 3, 1)), REFLECTION_0.solve(n));
      Assert.assertEquals(new ArrayList<>(Arrays.asList(0, 1, 3, 2)), REFLECTION_1.solve(n));
      Assert.assertEquals(new ArrayList<>(Arrays.asList(0, 1, 3, 2)), LINE_ARRANGEMENT.solve(n));
    }

  }

}
