package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same
 * as word2.
 * 
 * Given a list of words and two words word1 and word2, return the shortest distance between these
 * two words in the list.
 * 
 * word1 and word2 may be the same and they represent two individual words in the list. For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * 
 * Given word1 = “makes”, word2 = “coding”, return 1. 
 * Given word1 = "makes", word2 = "makes", return 3.
 * 
 * Note: You may assume word1 and word2 are both in the list.
 * 
 * </pre>
 * 
 * 
 * @see <a href="https://leetcode.com/problems/shortest-word-distance-iii/">Shortest Word Distance
 *      III</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ShortestWordDistanceIII {

  SOLUTION {

    @Override
    public int solve(String[] words, String word1, String word2) {
      boolean flag = word1.equals(word2);
      int distance = words.length, posi = -1;
      for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1) || words[i].equals(word2)) {
          if (posi != -1 && !(!flag && words[posi].equals(words[i])))
            distance = Math.min(distance, i - posi);
          posi = i;
        }
      }
      return distance;
    }

  };

  public abstract int solve(String[] words, String word1, String word2);

  public static class TestShortestWordDistanceIII {

    private String[] words = {"a", "b", "c", "d", "d"};

    @Test
    public void testSolutions() {
      Assert.assertEquals(3, SOLUTION.solve(words, "a", "d"));
      Assert.assertEquals(1, SOLUTION.solve(words, "d", "d"));
    }

  }

}
