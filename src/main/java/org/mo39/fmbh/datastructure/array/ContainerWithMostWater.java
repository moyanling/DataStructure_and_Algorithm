package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 * 
 * Note: You may not slant the container and n is at least 2.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/container-with-most-water/">Container With Most
 *      Water</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ContainerWithMostWater {

  BRUTE_FORCE {

    @Override
    public int solve(int[] height) {
      int result = -1;
      for (int i = 0; i < height.length - 1; i++) {
        for (int j = i + 1; j < height.length; j++) {
          result = Math.max(result, (j - i) * Math.min(height[j], height[i]));
        }
      }
      return result;
    }

  },

  /**
   * //TODO go back and proof it.
   */
  SOLUTION {

    @Override
    public int solve(int[] height) {
      int len = height.length, low = 0, high = len - 1;
      int result = 0;
      while (low < high) {
        result = Math.max(result, (high - low) * Math.min(height[low], height[high]));
        if (height[low] < height[high]) low++;
        else high--;
      }
      return result;
    }

  };

  public abstract int solve(int[] height);

}
