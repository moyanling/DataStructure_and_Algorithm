package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may
 * not exist a stone. The frog can jump on a stone, but it must not jump into the water.<br/>
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is
 * able to cross the river by landing on the last stone. Initially, the frog is on the first stone
 * and assume the first jump must be 1 unit.<br/>
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
 * Note that the frog can only jump in the forward direction.<br/>
 * Note:<br/>
 * The number of stones is &ge; 2 and is < 1,100.<br/>
 * Each stone's position will be a non-negative integer < 231.<br/>
 * The first stone's position is always 0.<br/>
 * Example 1:<br/>
 * [0,1,3,5,6,8,12,17]<br/>
 * There are a total of 8 stones.<br/>
 * The first stone at the 0th unit, second stone at the 1st unit,<br/>
 * third stone at the 3rd unit, and so on...<br/>
 * The last stone at the 17th unit.<br/>
 * Return true. The frog can jump to the last stone by jumping <br/>
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then <br/>
 * 2 units to the 4th stone, then 3 units to the 6th stone, <br/>
 * 4 units to the 7th stone, and 5 units to the 8th stone.<br/>
 * Example 2:<br/>
 * [0,1,2,3,4,8,9,11]<br/>
 * Return false. There is no way to jump to the last stone as <br/>
 * the gap between the 5th and 6th stone is too large.
 *
 * @see <a href="https://leetcode.com/problems/frog-jump/">Frog Jump</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FrogJump {

  RECURSIVE_SOLUTION {

    @Override
    public boolean solve(int[] stones) {
      // TODO Auto-generated method stub
      return false;
    }

  },

  BOTTOM_UP_METHOD {

    @Override
    public boolean solve(int[] stones) {
      return false;
    }

  };


  public abstract boolean solve(int[] stones);

  public static class TestFrogJump {

    private int[] stones0 = {0, 1, 3, 5, 6, 8, 12, 17};
    private boolean expected0 = true;

    private int[] stones1 = {0, 1, 2, 3, 4, 5, 6, 12};
    private boolean expected1 = false;

    public void testSolutions() {
      Assert.assertEquals(expected0, BOTTOM_UP_METHOD.solve(stones0));
      Assert.assertEquals(expected1, BOTTOM_UP_METHOD.solve(stones1));
    }

  }

}
