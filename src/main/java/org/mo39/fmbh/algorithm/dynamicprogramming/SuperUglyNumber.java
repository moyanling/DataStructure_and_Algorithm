package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 *     Write a program to find the nth super ugly number.
 * 
 * 
 * 
 *     Super ugly numbers are positive numbers whose all prime factors are in
 * the given prime list
 *     primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28,
 * 32]
 *  is the sequence of the first 12 super ugly numbers given primes
 *     = [2, 7, 13, 19] of size 4.
 * 
 * 
 * 
 *     Note:
 *     (1) 1 is a super ugly number for any given primes.
 *     (2) The given numbers in primes are in ascending order.
 *     (3) 0 < k &le; 100, 0 < n &le; 106, 0 < primes[i] < 1000.
 *     (4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/super-ugly-number/">Super Ugly Number</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SuperUglyNumber {

  LTE_SOLUTION {

    @Override
    public int solve(int n, int[] primes) {
      int[] memo = new int[n];
      memo[0] = 1;
      while (--n > 0) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < primes.length; j++) {
          for (int i = 0; i < memo.length && memo[i] != 0; i++) {
            int cur = memo[i] * primes[j];
            if (cur > memo[memo.length - n - 1]) min = Math.min(min, cur);
          }
        }
        memo[memo.length - n] = min;
      }
      return memo[memo.length - 1];
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(int n, int[] primes) {
      int[] memo = new int[n];
      int[] indexes = new int[primes.length];
      memo[0] = 1;
      for (int i = 1; i < n; i++) {
        // find next
        memo[i] = Integer.MAX_VALUE;
        for (int j = 0; j < primes.length; j++) {
          memo[i] = Math.min(memo[i], primes[j] * memo[indexes[j]]);
        }
        // skip duplicate
        for (int j = 0; j < primes.length; j++) {
          while (primes[j] * memo[indexes[j]] <= memo[i]) {
            indexes[j]++;
          }
        }
      }
      return memo[n - 1];
    }

  },

  SOLUTION_2 {

    @Override
    public int solve(int n, int[] primes) {
      int[] memo = new int[n];
      int[] indexes = new int[primes.length];
      int[] nexts = new int[primes.length];
      Arrays.fill(nexts, 1);
      int next = 1;
      for (int i = 0; i < n; i++) {
        memo[i] = next;
        next = Integer.MAX_VALUE;
        for (int j = 0; j < primes.length; j++) {
          // skip duplicate and avoid extra multiplication
          if (nexts[j] == memo[i]) nexts[j] = memo[indexes[j]++] * primes[j];
          // find next ugly number
          next = Math.min(next, nexts[j]);
        }
      }
      return memo[n - 1];
    }

  },

  /**
   * The idea, matters.
   * <p>
   * So, start from 1, and find all possible next values and select the smallest among them. </br>
   * After a number is selected to be the new ugly value, in the next loop, we will see this ugly
   * value in previous possible nexts. So, if such number is found, we need to update it. How to
   * update? It's not going to use every one of the prime to multiply it and find the minimum. Just
   * make it the value of next result multiplies the prime at its position. This explaination is not
   * clear enough.
   * <p>
   * First, ignore the index, this is a tech to help clear coding. The basic idea is bottom up memo
   * building. The memo defines as int[][] memo = new int[n][primes.length]; For each row, the array
   * contains all possible next values. Every time we pick the smallest value among the array and
   * update this value so that it still contains all possible next values in next loop. So, the
   * problem is, if I pick out the smallest value, how should I update it then? </br>
   * 
   */
  BOTTOM_UP_METHOD {

    @Override
    public int solve(int n, int[] primes) {
      int len = primes.length;
      int[] result = new int[n], index = new int[len];
      result[0] = 1;
      int[][] memo1 = new int[n][len], memo2 = new int[n][len];
      Arrays.fill(memo1[0], 1);
      Arrays.fill(memo2[0], 1);
      for (int i = 1; i < n; i++) {
        result[i] = Integer.MAX_VALUE;
        for (int j = 0; j < len; j++) {
          if (primes[j] * result[index[j]] == result[i - 1]) index[j]++;
          result[i] = Math.min(result[i], primes[j] * result[index[j]]);
        }
        memo1[i] = IntStream.range(0, len).map(k -> result[index[k]]).toArray();
        memo2[i] = IntStream.range(0, len).map(k -> result[index[k]] * primes[k]).toArray();
      }
      return result[n - 1];
    }

  };

  public abstract int solve(int n, int[] primes);

  public static class TestSuperUglyNumber {

    int n = 12;
    int[] primes = {2, 7, 13, 19};
    int expected = 32;

    @Test
    public void testSolutions() {
      for (SuperUglyNumber sol : SuperUglyNumber.values()) {
        Assert.assertEquals(expected, sol.solve(n, primes));
      }
    }

  }

}
