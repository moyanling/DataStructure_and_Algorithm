package org.mo39.fmbh.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Given two string, find the longest common sub-string.
 *
 * @author Jihan Chen
 *
 */
public enum LongestCommonSubstring {

  SOLUTION {

    public List<List<int[]>> pre = new ArrayList<>();
    public List<List<int[]>> memo = new ArrayList<>();

    @Override
    public String[] solve(String s1, String s2) {
      char[] arr1 = s1.toCharArray();
      char[] arr2 = s2.toCharArray();
      for (int i = 0; i < arr1.length; i++) {
        int index = s2.indexOf(arr1[i]);
        if (index != -1) memo.add(Lists.newArrayList(new int[] {i, index}));
      }
      while (memo.size() != 0) {
        pre.addAll(memo);
        for (int i = 0; i < memo.size(); i++) {
          memo.get(i).stream();
        }


      }

      return null;
    }

  };

  public abstract String[] solve(String s1, String s2);

}
