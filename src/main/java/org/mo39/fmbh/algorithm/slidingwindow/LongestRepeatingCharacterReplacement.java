package org.mo39.fmbh.algorithm.slidingwindow;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string that consists of only uppercase English letters, you can replace
 * any letter in the string with another letter at most k times. Find the length
 * of a longest substring containing all repeating letters you can get after performing
 * the above operations.
 * 
 * Note:
 * Both the string's length and k will not exceed 104.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * s = "ABAB", k = 2
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "AABABBA", k = 1
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * </pre>
 * 
 * //TODO
 * 
 * @see <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">Longest
 *      Repeating Character Replacement</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestRepeatingCharacterReplacement {

  SLIDING_WINDOW_0 {

    @Override
    public int solve(String s, int k) {
      int len = s.length();
      int[] count = new int[26];
      int start = 0, maxCount = 0, maxLength = 0;
      for (int end = 0; end < len; end++) {
        maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
        while (end - start + 1 - maxCount > k) {
          count[s.charAt(start) - 'A']--;
          start++;
        }
        maxLength = Math.max(maxLength, end - start + 1);
      }
      return maxLength;
    }

  },

  SLIDING_WINDOW_1 {

    @Override
    public int solve(String s, int k) {
      int[] count = new int[128];
      int max = 0;
      int start = 0;
      for (int end = 0; end < s.length(); end++) {
        max = Math.max(max, ++count[s.charAt(end)]);
        if (max + k <= end - start) count[s.charAt(start++)]--;
      }
      return s.length() - start;
    }

  };

  public abstract int solve(String s, int k);

  public static class TestLongestRepeatingCharacterReplacement {

    private String s = "ABBB";
    private int k = 2;
    private int expected = 4;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SLIDING_WINDOW_0.solve(s, k));
    }

  }

}
