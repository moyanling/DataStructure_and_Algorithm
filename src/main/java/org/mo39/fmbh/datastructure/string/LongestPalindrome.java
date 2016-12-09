package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest
 * palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note: Assume the length of given string will not exceed 1,010.
 * 
 * 
 * Example:
 * 
 * Input: "abccccdd"
 * 
 * Output: 7
 * 
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/longest-palindrome/">Longest Palindrome</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LongestPalindrome {

  HASHMAP_SOLUTION {

    @Override
    public int solve(String s) {
      Map<Character, Integer> map = new HashMap<>();
      for (Character c : s.toCharArray()) {
        if (map.put(c, 1) != null) map.remove(c);
      }
      return s.length() - (map.size() == 0 ? 0 : map.size() - 1);
    }

  },

  SET_SOLUTION {

    @Override
    public int solve(String s) {
      Set<Character> set = new HashSet<>();
      for (Character c : s.toCharArray()) {
        if (!set.add(c)) set.remove(c);
      }
      return s.length() - (set.size() == 0 ? 0 : set.size() - 1);
    }

  };

  public abstract int solve(String s);

  public static class TestLongestPalindrome {

    private String s = "abccccdd";
    private int expected = 7;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, HASHMAP_SOLUTION.solve(s));
      Assert.assertEquals(expected, SET_SOLUTION.solve(s));
    }
  }

}