package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a list of words and two words word1 and word2, return the shortest distance between these
 * two words in the list. For example, Assume that words = ["practice", "makes", "perfect",
 * "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. 
 * 
 * Given word1 = "makes", word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the
 * list.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-word-distance/">Shortest Word Distance</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ShortestWordDistance {

  SOLUTION {

    @Override
    public int solve(String[] words, String word1, String word2) {
      int distance = words.length, posi = -1;
      for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1) || words[i].equals(word2)) {
          if (posi != -1 && !words[posi].equals(words[i])) distance = Math.min(distance, i - posi);
          posi = i;
        }
      }
      return distance;
    }

  };

  public abstract int solve(String[] words, String word1, String word2);

  public static class Tests {

    private String[] words = {"practice", "makes", "perfect", "coding", "makes"};

    @Test
    public void testSolutions() {
      Assert.assertEquals(3, SOLUTION.solve(words, "coding", "practice"));
      Assert.assertEquals(1, SOLUTION.solve(words, "makes", "coding"));
    }

  }

}
