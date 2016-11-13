package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * This problem is quite intuitive after drawing the solution for <code>n = 5</code>. The occurrence
 * formula and recursive solution can be easily inferred after that. Symmetric can be used to reduce
 * half of the calculation.
 * 
 * @see <a href="https://leetcode.com/problems/unique-binary-search-trees/">Unique Binary Search
 *      Trees</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum UniqueBinarySearchTrees {

  /**
   * Time complexity is //TODO
   */
  RECURSIVE_SOLUTION {

    @Override
    public int solve(int n) {
      if (n == 0) return 1;
      int count = 0;
      for (int j = 0; j < n / 2; j++) {
        count += solve(j) * solve(n - 1 - j) * 2;
      }
      if (n % 2 != 0) count += solve(n / 2) * solve(n / 2);
      return count;
    }

  },

  /**
   * Time complexity is O(n^2).
   */
  BOTTOM_UP_METHOD {

    private int[] memo;

    @Override
    public int solve(int n) {
      memo = new int[n + 1];
      memo[0] = 1;
      for (int i = 1; i < n + 1; i++) {
        for (int j = 0; j < i / 2; j++) {
          memo[i] += memo[j] * memo[i - 1 - j] * 2;
        }
        if (i % 2 != 0) memo[i] += memo[i / 2] * memo[i / 2];
      }
      return memo[n];
    }

  };

  public abstract int solve(int n);

  public static class TestUniqueBinarySearchTrees {

    @Test
    public void testBottomUpMethod() {
      Assert.assertEquals(42, RECURSIVE_SOLUTION.solve(5));
      Assert.assertEquals(42, BOTTOM_UP_METHOD.solve(5));
    }

  }

}
