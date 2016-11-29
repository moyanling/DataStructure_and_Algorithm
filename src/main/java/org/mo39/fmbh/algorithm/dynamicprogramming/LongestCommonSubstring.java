package org.mo39.fmbh.algorithm.dynamicprogramming;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Tuple;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Given two string, find the longest common sub-string.
 *
 * @author Jihan Chen
 *
 */
public enum LongestCommonSubstring {

  /**
   * Brute force.
   *
   */
  BRUTE_FORCE_0 {

    @Override
    public List<String> solve(String s1, String s2) {
      Result result = new Result(s1);
      char[] arr1 = s1.toCharArray();
      char[] arr2 = s2.toCharArray();
      for (int i = 0; i < arr1.length; i++) {
        for (int j = 0; j < arr2.length; j++) {
          int start1 = i;
          int start2 = j;
          int len = 0;
          while (start1 < arr1.length && start2 < arr2.length && arr1[start1] == arr2[start2]) {
            start1++;
            start2++;
            len++;
          }
          result.update(len, start1 - len);
        }
      }
      return result.toStrList();
    }

  },

  /**
   * This is nothing but a brute force.<br/>
   * Suppose the performance of {@link Stream#filter(java.util.function.Predicate)} is O(n), then
   * the time complexity of this solution is <b>O(n^3)</b> and space complexity is <b>O(n^2)</b>.
   */
  BRUTE_FORCE_1 {

    public List<int[]> pairs = new ArrayList<>();

    @Override
    public List<String> solve(String s1, String s2) {
      char[] arr1 = s1.toCharArray();
      char[] arr2 = s2.toCharArray();
      for (int i = 0; i < arr1.length; i++) {
        for (int j = 0; j < arr2.length; j++) {
          if (arr1[i] == arr2[j]) {
            pairs.add(new int[] {i, j});
          }
        }
      }
      if (pairs.size() == 0) return new ArrayList<>();
      int i = 1;
      int len = arr1.length > arr2.length ? arr2.length : arr1.length;
      for (; i < len; i++) {
        final int bias = i;
        List<int[]> newMemo = pairs.stream()
            .filter(pair -> pair[0] + bias < arr1.length//
                && pair[1] + bias < arr2.length //
                && arr1[pair[0] + bias] == arr2[pair[1] + bias])
            .collect(toList());
        if (newMemo.size() == 0) return toStringList(s1, pairs, i);
        else pairs = newMemo;
      }
      return toStringList(s1, pairs, i);
    }

    private List<String> toStringList(String s1, List<int[]> pairs, int len) {
      return pairs.stream().map(pair -> s1.substring(pair[0], pair[0] + len)).collect(toList());
    }

  },

  /**
   * There are three sub-cases for this problem.<br>
   * The key point is: the function need to go through all three sub-cases, so <code>else</else>
   * cannot be used.
   *
   */
  RECURSIVE_SOLUTION {

    Result result;

    @Override
    public List<String> solve(String s1, String s2) {
      result = new Result(s1);
      recur(s1, s1.length() - 1, s2, s2.length() - 1, 0);
      return result.toStrList();
    }

    private int recur(String s1, int end1, String s2, int end2, int len) {
      result.update(len, end1 + 1);
      if (end1 < 0 || end2 < 0) return len;
      int length1 = -1;
      if (s1.charAt(end1) == s2.charAt(end2)) length1 = recur(s1, end1 - 1, s2, end2 - 1, len + 1);
      int length2 = Math.max(recur(s1, end1, s2, end2 - 1, 0), recur(s1, end1 - 1, s2, end2, 0));
      return Math.max(length1, length2);
    }

  },

  TOP_DOWN_WITH_MEMO {

    Integer[][] memo;
    Result result;
    String s1;
    String s2;

    @Override
    public List<String> solve(String s1, String s2) {
      this.s1 = s1;
      this.s2 = s2;
      result = new Result(s1);
      memo = new Integer[s1.length() + 1][s2.length() + 1];
      recur(s1, s1.length() - 1, s2, s2.length() - 1, 0);
      return result.toStrList();
    }

    private int recur(String s1, int end1, String s2, int end2, int len) {
      if (end1 < 0 || end2 < 0) return len;
      int length1 = -1;
      if (s1.charAt(end1) == s2.charAt(end2)) {
        length1 = handleMemo(end1 + 1, end2 + 1, end1 - 1, end2 - 1, len + 1);
      }
      int length2 = handleMemo(end1 + 1, end2, end1, end2 - 1, 0);
      int length3 = handleMemo(end1, end2 + 1, end1 - 1, end2, 0);
      return Math.max(Math.max(length1, length2), length3);
    }

    private int handleMemo(int i, int j, int p, int q, int len) {
      if (memo[i][j] == null) {
        int length = recur(s1, p, s2, q, len);
        memo[i][j] = length;
      }
      result.update(len, i - 1);
      return memo[i][j];
    }

  },

  /**
   * So nice and elegant.
   */
  BOTTOM_UP_METHOD_0 {

    @Override
    public List<String> solve(String s1, String s2) {
      Result result = new Result(s1);
      int[][] memo = new int[s1.length() + 1][s2.length() + 1];
      for (int i = 0; i < s1.length(); i++) {
        for (int j = 0; j < s2.length(); j++) {
          if (s1.charAt(i) == s2.charAt(j)) {
            int len = (i < 1 || j < 1 ? 0 : memo[i - 1][j - 1]) + 1;
            memo[i][j] = len;
            result.update(len, i - len + 1);
          }
        }
      }
      return result.toStrList();
    }

  },

  BOTTOM_UP_METHOD_1 {

    @Override
    public List<String> solve(String s1, String s2) {
      Map<Tuple<Integer, Integer>, Integer> map = new HashMap<>();
      Result result = new Result(s1);
      for (int i = 0; i < s1.length(); i++) {
        for (int j = 0; j < s2.length(); j++) {
          if (s1.charAt(i) == s2.charAt(j)) {
            int len = map.getOrDefault(Tuple.valueOf(i - 1, j - 1), 0) + 1;
            map.put(Tuple.valueOf(i, j), len);
            result.update(len, i - len + 1);
          }
        }
      }
      return result.toStrList();
    }

  };

  public abstract List<String> solve(String s1, String s2);

  public static class Result {

    public Set<Integer> index = new HashSet<>();
    public int length = 0;
    public final String s;

    public Result(String s) {
      this.s = s;
    }

    public void update(int length, int start) {
      if (length == this.length) index.add(start);
      if (length > this.length) {
        this.length = length;
        index = Sets.newHashSet(start);
      }
    }

    public List<String> toStrList() {
      return index.stream().map(i -> s.substring(i, i + length)).collect(toList());
    }

  }

  public static class TestLongestCommonSubstring {

    private String s1 = "ABCBDAB";
    private String s2 = "BDCABACB";

    private String[] expected = new String[4];

    {
      /*
       * This result is made against s1. If it's made against s2, there will be only 3 sub-string.
       * Therefore, all solutions result should use s1 as the base string to build sub-string.
       */
      Lists.newArrayList("AB", "BD", "AB", "CB").toArray(expected);
    }

    @Test
    public void testSolutions() {
      Assert.assertThat(BRUTE_FORCE_0.solve(s1, s2), containsInAnyOrder(expected));
      Assert.assertThat(BRUTE_FORCE_1.solve(s1, s2), containsInAnyOrder(expected));
      Assert.assertThat(RECURSIVE_SOLUTION.solve(s1, s2), containsInAnyOrder(expected));
      Assert.assertThat(TOP_DOWN_WITH_MEMO.solve(s1, s2), containsInAnyOrder(expected));
      Assert.assertThat(BOTTOM_UP_METHOD_0.solve(s1, s2), containsInAnyOrder(expected));
      Assert.assertThat(BOTTOM_UP_METHOD_1.solve(s1, s2), containsInAnyOrder(expected));
    }


  }

}
