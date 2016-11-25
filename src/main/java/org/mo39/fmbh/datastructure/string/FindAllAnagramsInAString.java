package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.<br/>
 * Strings consists of lowercase English letters only and the length of both strings s and p will
 * not be larger than 20,100.<br/>
 * The order of output does not matter.<br/>
 * Example 1:<br/>
 * Input:<br/>
 * s: "cbaebabacd" p: "abc"<br/>
 * Output:<br/>
 * [0, 6]<br/>
 * Explanation:<br/>
 * The substring with start index = 0 is "cba", which is an anagram of "abc".<br/>
 * The substring with start index = 6 is "bac", which is an anagram of "abc".<br/>
 * Example 2:<br/>
 * Input:<br/>
 * s: "abab" p: "ab"<br/>
 * Output:<br/>
 * [0, 1, 2]<br/>
 * Explanation:<br/>
 * The substring with start index = 0 is "ab", which is an anagram of "ab".<br/>
 * The substring with start index = 1 is "ba", which is an anagram of "ab".<br/>
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * 
 * @see <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">Find All Anagrams In
 *      A String</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindAllAnagramsInAString {

  SOLUTION {

    @Override
    public List<Integer> solve(String s, String p) {
      List<Integer> toRet = new ArrayList<>();
      if (s == null || s.length() < p.length()) return toRet;
      Set<Character> set = new HashSet<>();
      Map<Character, Integer> map = new HashMap<>();
      for (char c : p.toCharArray()) {
        set.add(c);
        map.compute(c, (k, v) -> v == null ? 1 : v + 1);
      }
      for (int i = 0; i < p.length(); i++) {
        Character c;
        if (set.contains((c = s.charAt(i))))
          map.compute(c, (k, v) -> v == null ? Integer.valueOf(-1) : (v != 1) ? (v - 1) : null);
      }
      if (map.size() == 0) toRet.add(0);
      boolean wasAdded = map.size() == 0;
      for (int i = 1; i < s.length() - p.length() + 1; i++) {
        Character oldC = s.charAt(i - 1);
        Character newC = s.charAt(i + p.length() - 1);
        if (oldC.equals(newC)) {
          if (wasAdded) toRet.add(i);
          continue;
        }
        if (set.contains(oldC))
          map.compute(oldC, (k, v) -> v == null ? Integer.valueOf(1) : (v != -1) ? (v + 1) : null);
        if (set.contains(newC))
          map.compute(newC, (k, v) -> v == null ? Integer.valueOf(-1) : (v != 1) ? (v - 1) : null);
        if (wasAdded = map.size() == 0) toRet.add(i);
      }
      return toRet;
    }

  };

  public abstract List<Integer> solve(String s, String p);

  public static class TestFindAllAnagramsInAString {

    private String s = "abab";
    private String p = "ab";
    private List<Integer> expected = Lists.newArrayList(0, 1, 2);

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(s, p));
    }

  }

}
