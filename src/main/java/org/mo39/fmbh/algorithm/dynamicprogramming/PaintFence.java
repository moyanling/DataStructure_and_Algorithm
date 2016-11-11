package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * @see <a href="https://leetcode.com/problems/paint-fence/">Paint Fence</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum PaintFence {

  BOTTOM_UP_METHOD {

    @Override
    public int solve(int n, int k) {
      // TODO Auto-generated method stub
      return 0;
    }

  };

  public abstract int solve(int n, int k);


}
