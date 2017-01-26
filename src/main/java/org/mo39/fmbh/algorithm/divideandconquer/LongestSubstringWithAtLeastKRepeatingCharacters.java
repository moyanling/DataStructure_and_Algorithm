package org.mo39.fmbh.algorithm.divideandconquer;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Find the length of the longest substring T of a given string (consists of lowercase
 * letters only) such that every character in T appears no less than k times.
 * 
 * 
 * Example 1:
 * 
 * Input:
 * s = "aaabb", k = 3
 * 
 * Output:
 * 3
 * 
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "ababbc", k = 2
 * 
 * Output:
 * 5
 * 
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated
 * 3 times.
 * </pre>
 * 
 * @see <a href=
 *      "https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/">
 *      Longest Substring With At Least K Repeating Characters</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestSubstringWithAtLeastKRepeatingCharacters {

  /**
   * The idea is to keep finding the character which does not satisfy the requirement. (repeat less
   * than k times). And divide the problem into 2 parts, to find the longest substring on the lhs
   * and rhs of the breaking point.
   */
  SOLUTION {

    @Override
    public int solve(String s, int k) {
      return recur(s.toCharArray(), 0, s.length(), k);
    }

    int recur(char[] arr, int start, int end, int k) {
      if (end - start < k) return 0;
      int[] counts = new int[26];
      for (int i = start; i < end; i++) {
        counts[arr[i] - 'a']++;
      }
      for (int i = 0; i < 26; i++) {
        if (counts[i] < k && counts[i] > 0) {
          for (int j = start; j < end; j++) {
            if (arr[j] == i + 'a')
              return Math.max(recur(arr, start, j, k), recur(arr, j + 1, end, k));
          }
        }
      }
      return end - start;
    }

  };

  public abstract int solve(String s, int k);

}
