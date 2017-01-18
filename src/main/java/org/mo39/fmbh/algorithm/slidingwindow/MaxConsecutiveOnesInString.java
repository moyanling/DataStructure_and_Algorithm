package org.mo39.fmbh.algorithm.slidingwindow;

import org.junit.Assert;
import org.junit.Test;

/**
 * I missed the one condition in {@link org.mo39.fmbh.datastructure.array.MaxConsecutiveOnes} that
 * the array will only contain 0 and 1 and thus fall into this solution. Whereas it's a good
 * practice for sliding window.
 * <p>
 * So, given a String contained only 1 and 0, return the max length of consecutive 1s.
 * 
 * @author Jihan Chen
 *
 */
public enum MaxConsecutiveOnesInString {

  SLIDING_WINDOW {

    @Override
    public int solve(String s) {
      int i = 0, j = -1, len = 0;
      while (++j < s.length()) {
        if (s.charAt(j) == '0') i = j + 1;
        else len = Math.max(len, j - i + 1);
      }
      return len;
    }

  };

  public abstract int solve(String s);

  public static class TestMaxConsecutiveOnes {

    private String s = "101101";
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SLIDING_WINDOW.solve(s));
    }

  }

}
