package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non negative integer number num. For every numbers i in the range 0 &le; i &le; num
 * calculate the number of 1's in their binary representation and return them as an array.
 * 
 * 
 * Example: For num = 5 you should return [0,1,1,2,1,2].
 * 
 * 
 * Follow up:
 * 
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it
 * in linear time O(n) /possibly in a single pass? Space complexity should be O(n). Can you do it
 * like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
 * other language.
 * 
 * 
 * 
 * 
 * You should make use of what you have produced already. Divide the numbers in ranges like [2-3],
 * [4-7], [8-15] and so on. And try to generate new range from previous. Or does the odd/even status
 * of the number help you in calculating the number of 1s?
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/counting-bits/">Counting Bits</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum CountingBits {

  BOTTOM_UP_METHOD {

    @Override
    public int[] solve(int n) {
      int bias = 0, pow = 1;
      int[] toRet = new int[n + 1];
      for (int i = 1; i < n + 1; i++) {
        if (i == pow) {
          bias = 0;
          pow *= 2;
        }
        toRet[i] = toRet[bias++] + 1;
      }
      return toRet;
    }

  };

  public abstract int[] solve(int n);

  public static class TestCountingBits {

    private int n = 7;
    private int[] expected = {0, 1, 1, 2, 1, 2, 2, 3};

    @Test
    public void testSolutions() {
      Assert.assertArrayEquals(expected, BOTTOM_UP_METHOD.solve(n));
    }

  }

}