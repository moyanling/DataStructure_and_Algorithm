package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s
 * will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p
 * are present in s. In particular, your input is the string p and you need to output the number of
 * different non-empty substrings of p in the string s.
 *
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 *
 * Example 1:
 *
 * Input: "a" Output: 1
 *
 * Explanation: Only the substring "a" of string "a" is in the string.
 *
 *
 *
 * Example 2:
 *
 * Input: "cac" Output: 2 Explanation: There are two substrings "a", "c" of string "cac" in the
 * string s.
 *
 *
 *
 * Example 3:
 *
 * Input: "zab" Output: 6 Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of
 * string "zab" in the string s.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/unique-substrings-in-wraparound-string/">Unique
 *      Substrings In Wraparound String</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum UniqueSubstringsInWraparoundString {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(String p) {
      // char[] arr = p.toCharArray();

      return 0;
    }

    // public int recur(char[] arr, int i) {
    // if (i < 1) return 1;
    // if (isConsecutive(arr[i-1], arr[i])) {
    // int tmp = recur(arr,i-1);
    // return tmp +
    // }
    // }

  },

  BOTTOM_UP_METHOD {

    @Override
    public int solve(String p) {
      if (p == null || p.length() == 0) return 0;
      int total = 1;
      char[] arr = p.toCharArray();
      Map<Character, Integer> map = new HashMap<>();
      map.put(arr[0], 1);
      for (int i = 1; i < arr.length; i++) {
      }


      return total;
    }

  };

  public static boolean isConsecutive(char a, char b) {
    if (b - a == 1) return true;
    if (b == 'a' && a == 'z') return true;
    return false;
  }

  public static char warp(int a) {
    return (char) (a > 'z' ? a - 'z' : a);
  }

  public abstract int solve(String p);

  public static class TestUniqueSubstringsInWraparoundString {

    @Test
    public void testSolutions() {
      // Assert.assertEquals(6, BOTTOM_UP_METHOD.solve("zab"));
      // Assert.assertEquals(3, BOTTOM_UP_METHOD.solve("zaz"));
      // Assert.assertEquals(3, BOTTOM_UP_METHOD.solve("abaab"));
      // Assert.assertEquals(9, BOTTOM_UP_METHOD.solve("zababc"));
      // Z.print(BOTTOM_UP_METHOD.solve(
      // "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
    }

  }

}