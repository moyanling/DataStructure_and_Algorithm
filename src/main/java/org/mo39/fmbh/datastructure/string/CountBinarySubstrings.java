package org.mo39.fmbh.datastructure.string;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * Give a string s, count the number of non-empty (contiguous) substrings that
 * have the same number of 0's and 1's, and all the 0's and all the 1's in these
 * substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 * Example 1:
 *
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's
 * and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times
 * they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are
 * not grouped together.
 *
 *
 *
 * Example 2:
 *
 * Input: "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal
 * number of consecutive 1's and 0's.
 *
 *
 *
 * Note:
 * s.length will be between 1 and 50,000.
 * s will only consist of "0" or "1" characters.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/count-binary-substrings/">Count Binary Substrings</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CountBinarySubstrings {
  /** Stack over flow */
  SOLUTION {
    @Override
    public int countBinarySubstrings(String s) {
      return recur(s.toCharArray(), 0);
    }

    int recur(char[] bits, int i) {
      int j = i;
      while (i < bits.length && bits[i] == bits[j]) i++;
      if (i == bits.length) return 0;
      int len = i - j;
      j = i;
      while (i < bits.length && i < j + len && bits[j - 1] + bits[i] == 97) i++;
      return i - j + recur(bits, j);
    }
  },

  SOLUTION_1 {
    @Override
    public int countBinarySubstrings(String s) {
      char[] bits = s.toCharArray();
      int sum = 0;
      for (int i = 0; i < s.length(); ) {
        int j = i;
        while (i < bits.length && bits[i] == bits[j]) i++;
        if (i == bits.length) return sum;
        int len = i - j;
        j = i;
        while (i < bits.length && i < j + len && bits[j - 1] + bits[i] == 97) i++;
        sum += i - j;
        i = j;
      }
      return sum;
    }
  },

  SOLUTION_2 {
    @Override
    public int countBinarySubstrings(String s) {
      //TODO THere is a more concise way to do this.
      return 0;
    }
  };

  public abstract int countBinarySubstrings(String s);

  public static class CountBinarySubstringsTest {

    String s = "00110011";
    int expected = 6;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.countBinarySubstrings(s));
      Assert.assertEquals(expected, SOLUTION_1.countBinarySubstrings(s));
    }
  }
}
