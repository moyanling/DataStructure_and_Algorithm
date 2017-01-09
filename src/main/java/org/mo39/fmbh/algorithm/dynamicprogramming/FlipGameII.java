package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.datastructure.string.FlipGame;

/**
 * <pre>
 * You are playing the following Flip Game with your friend: Given a string that contains only these
 * two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the
 * winner.
 * 
 * 
 * Write a function to determine if the starting player can guarantee a win.
 * 
 * 
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping
 * the middle "++" to become "+--+".
 * 
 * </pre>
 * 
 * Follow up: Derive your algorithm's runtime complexity.
 * 
 * @see <a href="https://leetcode.com/problems/flip-game-ii/">Flip Game II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FlipGameII {

  /**
   * This is back tracking. But it has a lot of over-lap in the sub-cases. For example, two players
   * can exchange their moves and create same String.</br>
   * The time complexity is <b>O(n!!)</b>.
   */
  RECURSIVE_SOLUTION {

    @Override
    public boolean solve(String s) {
      for (String str : FlipGame.SOLUTION.solve(s)) {
        if (!solve(str)) return true;
      }
      return false;
    }

  },

  TOP_DOWN_WITH_MEMO {

    private Map<String, Boolean> map = new HashMap<>();

    @Override
    public boolean solve(String s) {
      for (String str : FlipGame.SOLUTION.solve(s)) {
        if (!map.containsKey(str)) map.put(str, solve(str));
        if (!map.get(str)) return true;
      }
      return false;
    }

  };

  public abstract boolean solve(String s);

  public static class TestFlipGameII {

    private String isTrue = "++++";
    private String notTrue = "+++++++++";

    @Test
    public void testSolutions() {
      Assert.assertTrue(RECURSIVE_SOLUTION.solve(isTrue));
      Assert.assertFalse(RECURSIVE_SOLUTION.solve(notTrue));
    }

  }

}
