package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mo39.fmbh.common.annotation.ProblemSource;

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

}
