package org.mo39.fmbh.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.mo39.fmbh.common.Z;

/**
 * Given two string, find the longest common sub-sequence.
 *
 * @author Jihan Chen
 *
 */
public enum LongestCommonSubsequence {

  SOLUTION() {

    @Override
    public List<List<Character>> solve(String s1, String s2) {
      List<CharList> pre = new ArrayList<>();
      List<CharList> curr = new ArrayList<>();

      for (int len = 2; len < s1.length() + 1; len++) {
        for (CharList cList : pre) {

        }
      }
      return toList(curr);
    }

    private List<List<Character>> toList(List<CharList> cList) {
      return cList.stream().map(o -> o.cList).collect(Collectors.toList());
    }

  };

  public abstract List<List<Character>> solve(String s1, String s2);

  private class CharList {

    public List<Character> cList;
    public int lastIndex;

    public CharList() {
      cList = new ArrayList<>();
    }

  }

  public static class TestLongestCommonSubsequence {

    @Test
    public void testSolution() {
      Z.print(LongestCommonSubsequence.SOLUTION.solve("ABCBDAB", "BDCABA"));
      Z.print(String.valueOf(new char[] {'C', 'B'}));
    }

  }

}
