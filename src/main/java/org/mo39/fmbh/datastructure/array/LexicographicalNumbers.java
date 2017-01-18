package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * 
 * Given an integer n, return 1 - n in lexicographical order.
 * 
 * 
 * 
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 
 * 
 * 
 * Please optimize your algorithm to use less time and space. The input size may
 * be as large as 5,000,000.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/lexicographical-numbers/">Lexicographical Numbers</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LexicographicalNumbers {

  /**
   * <b>O(nlog(n))</b>. Pay attention to the usage of String.valueOf(int). This is much easier to do
   * it numerically.
   */
  SORT_SOLUTION {

    @Override
    public List<Integer> solve(int n) {
      List<Integer> result = new ArrayList<>();
      for (int i = 1; i <= n; i++) {
        result.add(i);
      }
      Collections.sort(result, new LexicographicalComparator());
      return result;
    }

  },

  /**
   * The key idea is to find the next value to add and cacluate that value for different conditions.
   */
  SOLUTION {

    @Override
    public List<Integer> solve(int n) {
      List<Integer> result = new ArrayList<>(n);
      int cur = 1;
      for (int i = 1; i <= n; i++) {
        result.add(cur);
        if (cur * 10 <= n) cur *= 10;
        else if (cur % 10 != 9 && cur + 1 <= n) cur++;
        else {
          while (cur / 10 % 10 == 9) {
            cur /= 10;
          }
          cur = cur / 10 + 1;
        }
      }
      return result;
    }

  };

  protected static class LexicographicalComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
      String s1 = String.valueOf(o1), s2 = String.valueOf(o2);
      for (int i = 0; i < s1.length() || i < s2.length(); i++) {
        if (i >= s1.length()) return -1;
        if (i >= s2.length()) return 1;
        if (s1.charAt(i) > s2.charAt(i)) return 1;
        if (s1.charAt(i) < s2.charAt(i)) return -1;
      }
      return 0;
    }

  }

  public abstract List<Integer> solve(int n);

  public static class TestLexicographicalNumbers {

    private int n = 13;
    private List<Integer> expected = Lists.newArrayList(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SORT_SOLUTION.solve(n));
      Assert.assertEquals(expected, SOLUTION.solve(n));
    }

  }

}
