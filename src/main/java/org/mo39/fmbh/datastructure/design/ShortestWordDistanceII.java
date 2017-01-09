package org.mo39.fmbh.datastructure.design;

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
 * This is a follow up of Shortest Word Distance. The only difference is now you are given
 * the list of words and your method will be called repeatedly many times with different parameters.
 * How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and implements a method that
 * takes two words word1 and word2 and return the shortest distance between these two words in the
 * list.
 * 
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3. 
 * Given word1 = "makes", word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the
 * list.
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-word-distance-ii/">Shortest Word Distance
 *      II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ShortestWordDistanceII {

  /**
   * <b>O(n^2)</b> to init, <b>O(1)</b> to query.
   */
  SOLUTION_0 {

    Map<String, Map<String, Integer>> map;

    @Override
    public void init(String[] words) {
      map = new HashMap<>();
      for (int i = 0; i < words.length; i++) {
        if (!map.containsKey(words[i])) map.put(words[i], new HashMap<>());
        for (int j = i + 1; j < words.length; j++) {
          if (!map.get(words[i]).containsKey(words[j])) map.get(words[i]).put(words[j], j - i);
          else map.get(words[i]).put(words[j], Math.min(map.get(words[i]).get(words[j]), j - i));
        }
      }
    }

    @Override
    public int solve(String word1, String word2) {
      if (!map.get(word1).containsKey(word2)) return map.get(word2).get(word1);
      if (!map.get(word2).containsKey(word1)) return map.get(word1).get(word2);
      return Math.min(map.get(word2).get(word1), map.get(word1).get(word2));
    }

  },

  /**
   * <b>O(n)</b> to init, <b>O(n)</b> to query.
   */
  SOLUTION_1 {

    Map<String, List<Integer>> map;

    @Override
    public void init(String[] words) {
      map = new HashMap<>();
      for (int i = 0; i < words.length; i++) {
        if (!map.containsKey(words[i])) map.put(words[i], new ArrayList<>());
        map.get(words[i]).add(i);
      }
    }

    @Override
    public int solve(String word1, String word2) {
      List<Integer> list1 = map.get(word1), list2 = map.get(word2);
      int distance = Integer.MAX_VALUE;
      for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
        if (list1.get(i) > list2.get(j)) {
          distance = Math.min(distance, list1.get(i) - list2.get(j));
          j++;
        } else {
          distance = Math.min(distance, list2.get(j) - list1.get(i));
          i++;
        }
      }
      return distance;
    }

  };

  public abstract void init(String[] words);

  public abstract int solve(String word1, String word2);

  public static class TestShortestWordDistanceII {

    private String[] words = {"practice", "makes", "perfect", "coding", "makes"};

    public void verify(ShortestWordDistanceII sol) {
      sol.init(words);
      Assert.assertEquals(3, sol.solve("coding", "practice"));
      Assert.assertEquals(1, sol.solve("makes", "coding"));
    }

    @Test
    public void testSolutions() {
      verify(SOLUTION_0);
      verify(SOLUTION_1);
    }

  }

}
