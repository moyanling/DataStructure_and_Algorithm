package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Calculate the sum of two integers a and b, but you are not allowed to use the
 * operator + and -.
 * 
 * Example:
 * Given a = 1 and b = 2, return 3.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-two-integers/">Sum Of Two Integers</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SumOfTwoIntegers {

  /**
   * The first problem is, how to decide a parttern at a place where the bit of a is 1 and the bit
   * of b is 1?<br/>
   * 1 & 1 == 1 ? Nope.<br/>
   * 1 ^ 1 == 0 ? Nope.<br/>
   * 1 | 1 == 1 ? Npoe.<br/>
   * 1 & 1 == 1 ? Yes.<br>
   * In all four logic operator, '&' is the only one that returns true in one case and false in all
   * three others. So if a specific parttern such as 0,1 is wanted, it should be ~0 & 1 == 1 (which
   * is used to decide the borrow in substraction). If 0,0 is wanted, it should be ~0 & ~0 == 1.
   * <br/>
   * The second problem is, why can we reach the result by these code?<br>
   * <ol>
   * <li>Add two number without carry</li>
   * <li>Then repeatedly calculate the new carry and add carry to the sum.</li>
   * </ol>
   * Consider this in decimal. If you want to add 9 and 1, follow the steps mentioned above, it
   * should be:<br/>
   * <ol>
   * <li>The sum should be 0 without carry</li>
   * <li>The carry is at the second digit, and it calculated as 1. Add it to the sum and we get 10
   * </li>
   * <li>No new carry, that's the end</li>
   * </ol>
   */
  STEP_BY_STEP {

    @Override
    public int solve(int a, int b) {
      int sum = a ^ b;
      int carry = (a & b) << 1;
      while (carry != 0) {
        int newCarry = (sum & carry) << 1;
        sum ^= carry;
        carry = newCarry;
      }
      return sum;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public int solve(int a, int b) {
      while (b != 0) {
        int carry = a & b;
        a = a ^ b;
        b = carry << 1;
      }
      return a;
    }

  },

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int a, int b) {
      if (b == 0) return a;
      int carry = a & b;
      a = a ^ b;
      b = carry << 1;
      return solve(a, b);
    }

  },

  ONE_LINER {

    @Override
    public int solve(int a, int b) {
      return b == 0 ? a : solve(a ^ b, (a & b) << 1);
    }

  };

  public abstract int solve(int a, int b);

  public static class TestSumOfTwoIntegers {

    private int a = 5;
    private int b = 6;
    private int expected = 11;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, STEP_BY_STEP.solve(a, b));
      Assert.assertEquals(expected, ITERATIVE_SOLUTION.solve(a, b));
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(a, b));
      Assert.assertEquals(expected, ONE_LINER.solve(a, b));
    }

  }

}
