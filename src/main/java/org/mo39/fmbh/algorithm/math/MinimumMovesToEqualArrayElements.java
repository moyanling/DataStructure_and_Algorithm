package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

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
