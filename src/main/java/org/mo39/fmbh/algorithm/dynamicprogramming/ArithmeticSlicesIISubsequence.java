package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the
 * difference between any two consecutive elements is the same.<br/>
 * For example, these are arithmetic sequences:<br/>
 * 1, 3, 5, 7, 9<br/>
 * 7, 7, 7, 7<br/>
 * 3, -1, -5, -9<br/>
 * The following sequence is not arithmetic. 1, 1, 2, 5, 7 <br/>
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any
 * sequence of integers (P0, P1, ..., Pk) such that 0 &le; P0 < P1 < ... < Pk < N.<br/>
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0],
 * A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k &ge; 2.<br/>
 * The function should return the number of arithmetic subsequence slices in the array A. <br/>
 * The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 &le; N &le;
 * 1000. The output is guaranteed to be less than 231-1.<br/>
 * Example:<br/>
 * Input: [2, 4, 6, 8, 10]<br/>
 * Output: 7<br/>
 * Explanation:<br/>
 * All arithmetic subsequence slices are:<br/>
 * [2,4,6]<br/>
 * [4,6,8]<br/>
 * [6,8,10]<br/>
 * [2,4,6,8]<br/>
 * [4,6,8,10]<br/>
 * [2,4,6,8,10]<br/>
 * [2,6,10]
 *
 * @see <a href="https://leetcode.com/problems/arithmetic-slices-ii-subsequence/">Arithmetic Slices
 *      II Subsequence</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ArithmeticSlicesIISubsequence {

  /**
   * Map<Tuple<difference, lastIndex>, len>
   *
   */
  SOLUTION {

    @Override
    public int solve(int[] A) {
      return 0;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestArithmeticSlicesIISubsequence {

    private int[] nums = {1, 2, 3, 4, 5, 6};

    @Test
    public void testSolutions() {
      Z.print(SOLUTION.solve(nums));
    }

  }

}
