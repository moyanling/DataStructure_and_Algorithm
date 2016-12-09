package org.mo39.fmbh.algorithm.math;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Write an algorithm to determine if a number is "happy".
 * 
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the
 * number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * 
 * Example:&nbsp;19 is a happy number
 * 
 * 
 * 12 + 92 = 82 82 + 22 = 68 62 + 82 = 100 12 + 02 + 02 = 1
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/happy-number/">Happy Number</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum HappyNumber {

  SOLUTION {

    @Override
    public boolean solve(int n) {
      Set<Integer> set = new HashSet<>();
      while (n != 1) {
        if (!set.add(n)) return false;
        int newN = 0;
        while (n != 0) {
          newN += (int) Math.pow(n % 10, 2);
          n = n / 10;
        }
        n = newN;
      }
      return true;
    }

  };

  public abstract boolean solve(int n);

  public static class TestHappyNumber {

    private int isHappy = 19;
    private int notHappy = 2;

    @Test
    public void testSolutions() {
      Assert.assertTrue(SOLUTION.solve(isHappy));
      Assert.assertFalse(SOLUTION.solve(notHappy));
    }

  }

}