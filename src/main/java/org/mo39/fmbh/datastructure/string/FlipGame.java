package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * You are playing the following Flip Game with your friend: Given a string that contains only these
 * two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the
 * winner.
 * 
 * 
 * 
 * Write a function to compute all possible states of the string after one valid move.
 * 
 * 
 * 
 * For example, given s = "++++", after one move, it may become one of the following states: [
 * "--++", "+--+", "++--" ]
 * 
 * </pre>
 * 
 * If there is no valid move, return an empty list [].
 * 
 * @see <a href="https://leetcode.com/problems/flip-game/">Flip Game</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FlipGame {

  SOLUTION {

    @Override
    public List<String> solve(String s) {
      List<String> list = new ArrayList<>();
      for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
          list.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
        }
      }
      return list;
    }

  };

  public abstract List<String> solve(String s);

  public static class TestFlipGame {

    private String s = "++++";
    private List<String> expected = Arrays.asList("--++", "+--+", "++--");

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(s));
    }

  }

}
