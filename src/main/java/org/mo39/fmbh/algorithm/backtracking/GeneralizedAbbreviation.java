package org.mo39.fmbh.algorithm.backtracking;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Write a function to generate the generalized abbreviations of a word.
 * 
 * 
 * Example:
 * 
 * Given word = "word", return the following list (order does not matter): ["word", "1ord", "w1rd",
 * "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/generalized-abbreviation/">Generalized
 *      Abbreviation</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum GeneralizedAbbreviation {

  RECURSIVE_SOLUTION_0 {

    @Override
    public List<String> solve(String word) {
      List<String> toRet = recur("", word);
      toRet.add(word);
      return toRet;
    }

    private List<String> recur(String pre, String cur) {
      List<String> toRet = new ArrayList<>();
      for (int i = 1; i <= cur.length(); i++) {
        for (int j = 0; j + i <= cur.length(); j++) {
          String newPre = pre + cur.substring(0, j) + i;
          toRet.add(pre + cur.substring(0, j) + i + cur.substring(i + j, cur.length()));
          if (i + j < cur.length())
            toRet.addAll(recur(newPre + cur.charAt(i + j), cur.substring(i + j + 1, cur.length())));
        }
      }
      return toRet;
    }

  },

  /**
   * This shares the same idea as the above one but has a better implementataion.
   */
  RECURSIVE_SOLUTION_1 {

    @Override
    public List<String> solve(String word) {
      List<String> res = new ArrayList<String>();
      int len = word.length();
      res.add(len == 0 ? "" : String.valueOf(len));
      for (int i = 0; i < len; i++) {
        for (String right : solve(word.substring(i + 1))) {
          String leftNum = i > 0 ? String.valueOf(i) : "";
          res.add(leftNum + word.substring(i, i + 1) + right);
        }
      }
      return res;
    }

  },

  /**
   * This is the best implementation.<br>
   * Pay attention to these points:<br>
   * <lo>
   * <li>The number of abbreviation is 2^n</li>
   * <li>This is because we can choose to abbreviate each character or not</li>
   * <li>A recursive 2^n solution is similar to a BinaryTree, so it should recursive twice</li></lo>
   */
  BACK_TRACKING {

    @Override
    public List<String> solve(String word) {
      List<String> list = new ArrayList<>();
      recur(list, word, -1, "", 0);
      return list;
    }

    private void recur(List<String> result, String word, int i, String pre, int count) {
      if (++i > word.length() - 1) {
        if (count != 0) pre = pre + count;
        result.add(pre);
        return;
      }
      recur(result, word, i, pre, count + 1);
      recur(result, word, i, count == 0 ? pre + word.charAt(i) : pre + count + word.charAt(i), 0);
    }

  };

  public abstract List<String> solve(String word);

  public static class TestGeneralizedAbbreviation {

    private String word = "word";
    private String[] expected = {"word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2",
        "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"};

    @Test
    public void testSolutions() {
      Assert.assertThat(RECURSIVE_SOLUTION_0.solve(word), containsInAnyOrder(expected));
      Assert.assertThat(RECURSIVE_SOLUTION_1.solve(word), containsInAnyOrder(expected));
      Assert.assertThat(BACK_TRACKING.solve(word), containsInAnyOrder(expected));
    }

  }

}
