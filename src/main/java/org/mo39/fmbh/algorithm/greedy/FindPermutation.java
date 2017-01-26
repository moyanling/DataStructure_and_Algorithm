package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a
 * decreasing relationship between two numbers, 'I' represents an increasing relationship between
 * two numbers. And our secret signature was constructed by a special integer array, which contains
 * uniquely all the different number from 1 to n (n is the length of the secret signature plus 1).
 * For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't
 * be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string
 * that can't represent the "DI" secret signature.
 * 
 * 
 * 
 * On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2,
 * ... n] could refer to the given secret signature in the input.
 * 
 * 
 * Example 1:
 * 
 * Input: "I"
 * Output: [1,2]
 * Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I",
 * where the number 1 and 2 construct an increasing relationship.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "DI"
 * Output: [2,1,3]
 * Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI", but since we want
 * to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 * 
 * 
 * 
 * Note: The input string will only contain the character 'D' and 'I'. The length of input string is
 * a positive integer and will not exceed 10,000
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-permutation/">Find Permutation</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindPermutation {

  SOLUTION_0 {

    @Override
    public int[] solve(String s) {
      int[] result = new int[s.length() + 1];
      int smallest = 1;
      result[0] = smallest++;
      for (int i = 1; i < result.length; i++) {
        if (s.charAt(i - 1) == 'D') {
          for (int j = i - 1; j > -1 && s.charAt(j) == 'D'; j--) {
            result[j]++;
          }
          result[i] = result[i - 1] - 1;
        } else result[i] = smallest;
        smallest++;
      }
      return result;
    }

  },

  /**
   * IIIIII : 1,2|3,4|5,6</br>
   * IIDDII : 1,2|4,3|5,6
   */
  SOLUTION_1 {

    @Override
    public int[] solve(String s) {
      int n = s.length(), arr[] = new int[n + 1];
      for (int i = 0; i <= n; i++)
        arr[i] = i + 1; // sorted
      for (int h = 0; h < n; h++) {
        if (s.charAt(h) == 'D') {
          int l = h;
          while (h < n && s.charAt(h) == 'D')
            h++;
          reverse(arr, l, h);
        }
      }
      return arr;
    }

    void reverse(int[] arr, int l, int h) {
      while (l < h) {
        arr[l] ^= arr[h];
        arr[h] ^= arr[l];
        arr[l] ^= arr[h];
        l++;
        h--;
      }
    }

  };

  public abstract int[] solve(String s);

  public static class TestFindPermutation {

    String s = "DDIIDI";
    int[] expecteds = {3, 2, 1, 4, 6, 5, 7};

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expecteds, SOLUTION_0.solve(s));
      Assert.assertArrayEquals(expecteds, SOLUTION_1.solve(s));
    }

  }

}
