package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * 
 * 
 * The string S is magical because concatenating the number of contiguous occurrences
 * of characters '1' and '2' generates the string S itself.
 * 
 * 
 * 
 * The first few elements of string S is the following:
 * S = "1221121221221121122……"
 * 
 * 
 * 
 * If we group the consecutive '1's and '2's in S, it will be:
 * 
 * 
 * 1   22  11  2  1  22  1  22  11  2  11  22 ......
 * 
 * 
 * and the occurrences of '1's or '2's in each group are:
 * 
 * 
 * 1   2	   2    1   1    2     1    2     2    1    2    2 ......
 * 
 * 
 * 
 * You can see that the occurrence sequence above is the S itself. 
 * 
 * 
 * 
 * Given an integer N as input, return the number of '1's in the first N number
 * in the magical string S.
 * 
 * 
 * Note:
 * N will not exceed 100,000.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "12211" and it contains
 * three 1's, so return 3.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/magical-string/">Magical String</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MagicalString {

  RECURSIVE_SOLUTION {


    @Override
    public int solve(int n) {
      if (n < 1) return 0;
      if (n < 4) return 1;
      List<int[]> s = new ArrayList<>();
      s.add(new int[] {1, 1});
      s.add(new int[] {2, 1});
      s.add(new int[] {2, 1});
      return recur(n, s, 2, 2);
    }

    private int recur(int n, List<int[]> s, int i, int pre) {
      if (s.size() >= n) return s.get(n - 1)[1];
      int cur = pre == 1 ? 2 : 1;
      for (int j = 0; j < s.get(i)[0]; j++) {
        int count = s.get(s.size() - 1)[1];
        s.add(new int[] {cur, cur == 1 ? count + 1 : count});
      }
      return recur(n, s, i + 1, cur);
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public int solve(int n) {
      if (n < 1) return 0;
      if (n < 4) return 1;
      List<int[]> s = new ArrayList<>();
      s.add(new int[] {1, 1});
      s.add(new int[] {2, 1});
      s.add(new int[] {2, 1});
      int i = 2, pre = 2;
      while (s.size() < n) {
        int cur = pre == 1 ? 2 : 1;
        for (int j = 0; j < s.get(i)[0]; j++) {
          int count = s.get(s.size() - 1)[1];
          s.add(new int[] {cur, cur == 1 ? count + 1 : count});
        }
        pre = cur;
        i++;
      }
      return s.get(n - 1)[1];
    }

  },

  DEQUE_SOLUTION {

    @Override
    public int solve(int n) {
      if (n < 1) return 0;
      Deque<Integer> q = new LinkedList<>();
      q.addLast(2);
      int cur = 2, count = 1;
      while (n - 3 > 0) {
        cur = 3 - cur;
        int groupLen = q.pop();
        for (int j = 0; j < groupLen; j++, n--) {
          q.addLast(cur);
          if (cur == 1) count++;
        }
      }
      return n - 3 == 0 ? count : q.peekLast() == 1 ? count - 1 : count;
    }

  };

  public abstract int solve(int n);

  public static class TestMagicalString {

    private int n = 1500;
    private int expected = 754;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(n));
      Assert.assertEquals(expected, ITERATIVE_SOLUTION.solve(n));
      Assert.assertEquals(expected, DEQUE_SOLUTION.solve(n));
    }

  }

}
