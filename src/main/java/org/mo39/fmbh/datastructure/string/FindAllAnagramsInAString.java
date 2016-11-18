package org.mo39.fmbh.datastructure.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.mo39.fmbh.common.Z;

import com.google.common.collect.Lists;

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
        map.computeIfPresent(s.charAt(i), (k, v) -> v == 1 ? null : v - 1);
      }
      if (map.size() == 0) toRet.add(0);
      boolean wasAdded = map.size() == 0;
      for (int i = 1; i < s.length() - p.length() + 1; i++) {
        Character oldC = s.charAt(i - 1);
        Character newC = s.charAt(i + p.length() - 1);
        if (oldC.equals(newC) && wasAdded) {
          toRet.add(i);
          continue;
        }
        // map.compute(oldC, (k,v) -> v ==null? );
        if (set.contains(oldC)) map.compute(oldC, (k, v) -> v == null ? 1 : v + 1);
        if (set.contains(newC)) map.compute(newC, (k, v) -> v == null ? 1 : v - 1);
        if (wasAdded = map.size() == 0) toRet.add(i);
        Z.print(map);
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
      Z.print(SOLUTION.solve("baa", "aa"));
      Z.print(SOLUTION.solve(s, p));
      Z.print(SOLUTION.solve("cbaebabacd", "abc"));

    }

  }

}
