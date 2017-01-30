package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array of strings, group anagrams together.
 * 
 * 
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * Return:
 * 
 * [
 *   ["ate", "eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 
 * Note: All inputs will be in lower-case.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/group-anagrams/">Group Anagrams</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum GroupAnagrams {

  LTE_SOLUTION {

    @Override
    public List<List<String>> solve(String[] strs) {
      return new ArrayList<>(
          Arrays.stream(strs).collect(Collectors.groupingBy(str -> count(str))).values());
    }

    Map<Character, Integer> count(String str) {
      Map<Character, Integer> result = new HashMap<>();
      for (Character c : str.toCharArray()) {
        result.compute(c, (k, v) -> v == null ? 1 : v + 1);
      }
      return result;
    }

  },

  SOLUTION {

    @Override
    public List<List<String>> solve(String[] strs) {
      return new ArrayList<>(
          Arrays.stream(strs).collect(Collectors.groupingBy(str -> sort(str))).values());
    }

    String sort(String str) {
      char[] arr = str.toCharArray();
      Arrays.sort(arr);
      return String.valueOf(arr);
    }

  };

  public abstract List<List<String>> solve(String[] strs);

}
