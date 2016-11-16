package org.mo39.fmbh.algorithm.dynamicprogramming;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.Z;

import com.google.common.collect.Lists;

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

    private List<String> strList;

    @Override
    public List<String> solve(String s1, String s2) {
      List<Integer> starts = new ArrayList<>();
      char[] arr1 = s1.toCharArray();
      char[] arr2 = s2.toCharArray();
      int length = 0;
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
          if (len >= length) {
            if (len > length) starts = new ArrayList<>();
            starts.add(start1 - len);
            length = len;
          }
        }
      }
      return strList = toStringList(s1, starts, length);
    }

    @Override
    public int getLength(String s1, String s2) {
      return strList == null ? solve(s1, s2).size() : strList.size();
    }

  },

  /**
   * This is nothing but a brute force.<br/>
   * Suppose the performance of {@link Stream#filter(java.util.function.Predicate)} is O(n), then
   * the time complexity of this solution is <b>O(n^3)</b> and space complexity is <b>O(n)</b>.
   */
  BRUTE_FORCE_1 {

    private List<String> strList;

    public List<int[]> memo = new ArrayList<>();

    @Override
    public List<String> solve(String s1, String s2) {
      char[] arr1 = s1.toCharArray();
      char[] arr2 = s2.toCharArray();
      for (int i = 0; i < arr1.length; i++) {
        for (int j = 0; j < arr2.length; j++) {
          if (arr1[i] == arr2[j]) {
            memo.add(new int[] {i, j});
          }
        }
      }
      if (memo.size() == 0) return new ArrayList<>();
      int i = 1;
      int len = arr1.length > arr2.length ? arr2.length : arr1.length;
      for (; i < len; i++) {
        final int bias = i;
        List<int[]> newMemo = memo.stream().filter(pair -> pair[0] + bias < arr1.length
            && pair[1] + bias < arr2.length && arr1[pair[0] + bias] == arr2[pair[1] + bias])
            .collect(Collectors.toList());
        if (newMemo.size() == 0) return toStringList(s1, adapter(memo), i);
        else memo = newMemo;
      }
      return strList = toStringList(s1, adapter(memo), i);
    }

    private List<Integer> adapter(List<int[]> list) {
      return list.stream().map(pair -> pair[0]).collect(Collectors.toList());
    }

    @Override
    public int getLength(String s1, String s2) {
      return strList == null ? solve(s1, s2).size() : strList.size();
    }

  },

  RECURSIVE_SOLUTION {

    private int length = -1;
    private int start = -1;

    @Override
    public List<String> solve(String s1, String s2) {
      if (start == -1) getLength(s1, s2);
      return Lists.newArrayList(s1.substring(start, start + length));
    }

    @Override
    public int getLength(String s1, String s2) {
      return getLength(s1.toCharArray(), s1.length() - 1, s2.toCharArray(), s2.length() - 1, 0);
    }

    private int getLength(char[] s1, int end1, char[] s2, int end2, int len) {
      if (end1 < 0 || end2 < 0) return len;
      int length;
      if (s1[end1] == s2[end2])
        length = setValues(getLength(s1, end1 - 1, s2, end2 - 1, len + 1), end1);
      else length = Math.max(//
          length = setValues(getLength(s1, end1 - 1, s2, end2, 0), end1 - 1), //
          length = setValues(getLength(s1, end1, s2, end2 - 1, 0), end1));
      this.length = Math.max(this.length, length);
      return length;
    }

    private int setValues(int length, int end) {
      if (length <= this.length) return this.length;
      this.length = length;
      this.start = end;
      return length;
    }

  },

  TOP_DOWN_WITH_MEMO {

    @Override
    public List<String> solve(String s1, String s2) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int getLength(String s1, String s2) {
      // TODO Auto-generated method stub
      return 0;
    }

  },

  /**
   * //TODO http://www.cnblogs.com/ider/p/longest-common-substring-problem-optimization.html
   * 
   */
  BOTTOM_UP_METHOD {

    @Override
    public List<String> solve(String s1, String s2) {
      return null;
    }

    @Override
    public int getLength(String s1, String s2) {
      // TODO Auto-generated method stub
      return 0;
    }

  };

  private static List<String> toStringList(String s1, List<Integer> list, int len) {
    return list.stream().map(start -> s1.substring(start, start + len)).collect(toList());
  }

  public abstract List<String> solve(String s1, String s2);

  public abstract int getLength(String s1, String s2);

  public static class Result {

    public List<Integer> index = new ArrayList<>();
    public int length = 0;

    public int update(int length, int start) {
      if (length <= this.length) {
        if (length == this.length) index.add(start);
        return this.length;
      }
      this.length = length;
      index = Lists.newArrayList(start);
      return length;
    }

    public List<String> toStrList(Function<Integer, String> f) {
      return index.stream().map(f).collect(toList());
    }

  }

  @SuppressWarnings("serial")
  public static class TestLongestCommonSubstring {

    private String s1 = "ABCBDAB";
    private String s2 = "BDCABACB";

    private List<String> expected = new ArrayList<String>() {
      {
        add("AB");
        add("BD");
        add("AB");
        add("CB");
      }
    };

    @Test
    public void testSolutions() {
      Assert.assertThat(BRUTE_FORCE_0.solve(s1, s2), containsInAnyOrder(expected.toArray()));
      Assert.assertThat(BRUTE_FORCE_1.solve(s1, s2), containsInAnyOrder(expected.toArray()));
      Z.print(RECURSIVE_SOLUTION.getLength(s1, s2));
      Z.print(RECURSIVE_SOLUTION.solve(s1, s2));
    }


  }

}
