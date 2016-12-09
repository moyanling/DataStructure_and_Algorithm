package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * Given a sequence of words, check whether it forms a valid word square.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same
 * string, where 0 &amp;le; k &amp;lt; max(numRows, numColumns).
 *
 * Note:
 *
 * The number of words given is at least 1 and does not exceed 500. Word length will be at least 1
 * and does not exceed 500. Each word contains only lowercase English alphabet a-z.
 *
 *
 *
 * Example 1:
 *
 * Input: [ "abcd", "bnrt", "crmy", "dtye" ]
 *
 * Output: true
 *
 * Explanation: The first row and first column both read "abcd". The second row and second
 * column both read "bnrt". The third row and third column both read "crmy". The
 * fourth row and fourth column both read "dtye".
 *
 * Therefore, it is a valid word square.
 *
 *
 *
 * Example 2:
 *
 * Input: [ "abcd", "bnrt", "crm", "dt" ]
 *
 * Output: true
 *
 * Explanation: The first row and first column both read "abcd". The second row and second
 * column both read "bnrt". The third row and third column both read "crm". The
 * fourth row and fourth column both read "dt".
 *
 * Therefore, it is a valid word square.
 *
 *
 *
 * Example 3:
 *
 * Input: [ "ball", "area", "read", "lady" ]
 *
 * Output: false
 *
 * Explanation: The third row reads "read" while the third column reads "lead".
 *
 * Therefore, it is NOT a valid word square.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/valid-word-square/">Valid Word Square</a>
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum ValidWordSquare {

  SOLUTION {

    @Override
    public boolean solve(List<String> words) {
      List<char[]> charSquare =
          words.stream().map(str -> str.toCharArray()).collect(Collectors.toList());
      for (int i = 0; i < charSquare.size(); i++) {
        for (int j = 0; j < charSquare.get(i).length; j++) {
          if (j >= charSquare.size() || i >= charSquare.get(j).length
              || charSquare.get(i)[j] != charSquare.get(j)[i])
            return false;
        }
      }
      return true;
    }

  };

  public abstract boolean solve(List<String> words);

  public static class TestValidWordSquare {

    private List<String> notTrue = Lists.newArrayList("ball", "asee", "lett", "le");
    private List<String> isTrue = Lists.newArrayList("abcd", "bnrt", "crmy", "dtye");

    @Test
    public void testSolutions() {
      Assert.assertTrue(SOLUTION.solve(isTrue));
      Assert.assertFalse(SOLUTION.solve(notTrue));
    }

  }

}
