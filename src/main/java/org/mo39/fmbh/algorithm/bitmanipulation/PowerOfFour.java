package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 
 * Example: Given num = 16, return true. Given num = 5, return false.
 * 
 * 
 * Follow up: Could you solve it without loops/recursion?
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/power-of-four/">Power Of Four</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PowerOfFour {

  BIT_MANIPULATION {

    @Override
    public boolean solve(int num) {
      if (num < 1) return false;
      boolean found = false;
      for (int i = 0; i < 32; i++) {
        if (((num >>> i) & 1) == 1) {
          if (found) return false;
          found = true;
          if (i % 2 != 0) return false;
        }
      }
      return found;
    }

  },

  /**
   * power of 4 numbers have those 3 common features.First,greater than 0.Second,only have one '1'
   * bit in their binary notation,so we use x&(x-1) to delete the lowest '1',and if then it becomes
   * 0,it prove that there is only one '1' bit.Third,the only '1' bit should be locate at the odd
   * location,for example,16.It's binary is 00010000.So we can use '0x55555555' to check if the '1'
   * bit is in the right place.
   */
  ONE_LINER_0 {

    @Override
    public boolean solve(int num) {
      return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }


  },

  ONE_LINER_1 {

    @Override
    public boolean solve(int num) {
      return Integer.toString(num, 4).matches("10*");
    }


  };
  public abstract boolean solve(int num);

  public static class TestPowerOfFour {

    private int isTrue = 16;
    private int isFalse = 3;

    @Test
    public void testSolutions() {
      Assert.assertTrue(BIT_MANIPULATION.solve(isTrue));
      Assert.assertFalse(BIT_MANIPULATION.solve(isFalse));
      // ---------
      Assert.assertTrue(ONE_LINER_0.solve(isTrue));
      Assert.assertFalse(ONE_LINER_0.solve(isFalse));
      // ---------
      Assert.assertTrue(ONE_LINER_1.solve(isTrue));
      Assert.assertFalse(ONE_LINER_1.solve(isFalse));
    }

  }
}