package org.mo39.fmbh.algorithm.backtracking;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at
 * upside down). Find all strobogrammatic numbers that are of length = n. For example, Given n = 2,
 * 
 * return ["11","69","88","96"].
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/strobogrammatic-number-ii/">Strobogrammatic Number
 *      II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum StrobogrammaticNumberII {

  SOLUTION {

    Map<Character, Character> map = new HashMap<>();
    {
      map.put('0', '0');
      map.put('1', '1');
      map.put('8', '8');
      map.put('9', '6');
      map.put('6', '9');
    }

    @Override
    public List<String> solve(int n) {
      List<String> result = new ArrayList<>();
      StringBuilder cur = new StringBuilder();
      recur(result, cur, n / 2, n % 2 == 1);
      return result;
    }

    void recur(List<String> result, StringBuilder cur, int n, boolean isOdd) {
      if (n == 0) {
        if (cur.length() > 0 && cur.charAt(0) == '0') return;
        int i = cur.length() - 1, length = cur.length();
        if (isOdd) cur.append('1');
        for (; i > -1; i--) {
          cur.append(map.get(cur.charAt(i)));
        }
        result.add(cur.toString());
        if (isOdd) {
          cur.replace(cur.length() / 2, cur.length() / 2 + 1, "8");
          result.add(cur.toString());
          cur.replace(cur.length() / 2, cur.length() / 2 + 1, "0");
          result.add(cur.toString());
        }
        cur.setLength(length);
        return;
      }
      for (Character c : map.keySet()) {
        recur(result, cur.append(c), n - 1, isOdd);
        cur.setLength(cur.length() - 1);
      }
    }

  };

  public abstract List<String> solve(int n);

  public static class TestStrobogrammaticNumberII {

    private int n = 2;
    private String[] expected = {"11", "69", "96", "88"};

    @Test
    public void testSolutions() {
      Assert.assertThat(SOLUTION.solve(n), containsInAnyOrder(expected));
    }

  }

}
