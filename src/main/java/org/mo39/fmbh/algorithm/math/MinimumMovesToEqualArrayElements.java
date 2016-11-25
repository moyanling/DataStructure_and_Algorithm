package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all
 * array elements equal, where a move is incrementing n - 1 elements by 1.<br/>
 * Example:<br/>
 * Input:<br/>
 * [1,2,3]<br/>
 * Output:<br/>
 * 3<br/>
 * Explanation:<br/>
 * Only three moves are needed (remember each move increments two elements):<br/>
 * [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
 * 
 * @see <a href="https://leetcode.com/problems/minimum-moves-to-equal-array-elements/">Minimum Moves
 *      To Equal Array Elements</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MinimumMovesToEqualArrayElements {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      Integer min = null;
      Integer max = null;
      for (int n : nums) {
        if (min == null || n < min) min = n;
        if (max == null || n > max) max = n;
      }
      return max == min ? 0 : max - min + 1;
    }

  };

  public abstract int solve(int[] nums);

}
