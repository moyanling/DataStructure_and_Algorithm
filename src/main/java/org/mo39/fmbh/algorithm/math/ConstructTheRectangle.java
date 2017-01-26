package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * For a web developer, it is very important to know how to design a web page's
 * size. So, given a specific rectangular web page’s area, your job by now is
 * to design a rectangular web page, whose length L and width W satisfy the following
 * requirements:
 * 1. The area of the rectangular web page you designed must equal to the given
 * target area.
 * 2. The width W should not be larger than the length L, which means L >= W.
 * 3. The difference between length L and width W should be as small as possible.
 * 
 * You need to output the length L and the width W of the web page you designed
 * in sequence.
 * 
 * 
 * 
 * Example:
 * 
 * Input: 4
 * Output: [2, 2]
 * Explanation: The target area is 4, and all the possible ways to construct it
 * are [1,4], [2,2], [4,1]. 
 * But according to requirement 2, [1,4] is illegal; according to requirement
 * 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width
 * W is 2.
 * 
 * 
 * 
 * Note:
 * 
 * The given area won't exceed 10,000,000 and is a positive integer
 * The web page's width and length you designed must be positive integers.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/construct-the-rectangle/">Construct The Rectangle</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ConstructTheRectangle {

  SOLUTION {

    @Override
    public int[] solve(int area) {
      double sqrt = Math.sqrt(area);
      int intSqrt = (int) sqrt;
      if (sqrt == intSqrt) return new int[] {intSqrt, intSqrt};
      for (int i = intSqrt + 1; i <= area; i++) {
        if (area % i == 0) return new int[] {i, area / i};
      }
      return null;
    }

  };

  public abstract int[] solve(int area);

  public static class TestConstructTheRectangle {

    private int area = 16;
    private int[] expected = {4, 4};

    @Test
    public void testSolutions() {
      for (ConstructTheRectangle sol : ConstructTheRectangle.values()) {
        Assert.assertArrayEquals(expected, sol.solve(area));
      }
    }


  }

}
