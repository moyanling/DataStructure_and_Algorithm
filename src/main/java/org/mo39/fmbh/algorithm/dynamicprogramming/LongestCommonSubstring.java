package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

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
  BRUTE_FORCE {

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
      return toStringList(s1, starts, length);
    }

  },

  /**
   * This is a nice solution yielded by myself.
   * <p>
   * Recurrence Formula:
   * 
   * <pre>
   * Given X = &lt;x[1], x[2], ..., x[m]&gt;
   * for i = 0 , 1, ..., m,
   * Define X[i] = &lt;x[1], x[2], ..., x[i]&gt; is the i<sup>th</sup> prefix of X.
   * And define c[i,j] is the length of the common substring of X[i] and Y[j].
   *
   *              | 0                   if i>X.length or j>Y.length or x[i] != y[j]
   * c[i+1,j+1] = | c[i,j] + 1          if i,j>0 and x[i] = y[j]
   * </pre>
   * 
   * So in order to get the base case, a combination is used to get all common sub-string of 1
   * single character. Then build up the common sub-string with the recurrence formula.
   * <p>
   * In stead of a table of m*n size, a list of index pair <code>List<int[]></code> is used to
   * reduce the space.
   * <p>
   * Suppose the performance of {@link Stream#filter(java.util.function.Predicate)} is O(n), then
   * the time complexity of this solution is <b>O(n^2)</b> and space complexity is <b>O(n)</b>.
   */
  DYNAMIC_PROGRAMMING_0 {

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
      return toStringList(s1, adapter(memo), i);
    }

    private List<Integer> adapter(List<int[]> list) {
      return list.stream().map(pair -> pair[0]).collect(Collectors.toList());
    }

  },

  /**
   * //TODO http://www.cnblogs.com/ider/p/longest-common-substring-problem-optimization.html
   * 
   */
  DYNAMIC_PROGRAMMING_1 {

    @Override
    public List<String> solve(String s1, String s2) {
      return null;
    }

  };

  private static List<String> toStringList(String s1, List<Integer> list, int len) {
    return list.stream().map(start -> s1.substring(start, start + len))
        .collect(Collectors.toList());
  }

  public abstract List<String> solve(String s1, String s2);

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
    public void testBruteForce() {
      Assert.assertThat(LongestCommonSubstring.BRUTE_FORCE.solve(s1, s2),
          containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void testDynamicProgramming() {
      Assert.assertThat(LongestCommonSubstring.DYNAMIC_PROGRAMMING_0.solve(s1, s2),
          containsInAnyOrder(expected.toArray()));
    }

  }

}
