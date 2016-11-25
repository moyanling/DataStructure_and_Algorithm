package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors. <br>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same
 * color. <br>
 * Return the total number of ways you can paint the fence. <br>
 * Note: n and k are non-negative integers.<br>
 * 
 * @see <a href="https://leetcode.com/problems/paint-fence/">Paint Fence</a>
 * @author Jihan Chen
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
