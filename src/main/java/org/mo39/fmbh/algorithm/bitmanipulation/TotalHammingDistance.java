package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * The Hamming distance between two integers is the number of positions at which
 * the corresponding bits are different.
 * 
 * Now your job is to find the total Hamming distance between all pairs of the
 * given numbers.
 * 
 * 
 * Example:
 * 
 * Input: 4, 14, 2
 * 
 * Output: 6
 * 
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is
 * 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2
 * + 2 + 2 = 6.
 * 
 * 
 * 
 * Note:
 * 
 * Elements of the given array are in the range of 0  to 10^9
 * Length of the array will not exceed 10^4. 
 * </pre>
 * @see <a href="https://leetcode.com/problems/total-hamming-distance/">Total Hamming Distance</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum TotalHammingDistance {


  BRUTE_FORCE {

    @Override
    public int solve(int[] nums) {
      int total = 0;
      for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          total += HammingDistance.ONE_LINER.solve(nums[i], nums[j]);
        }
      }
      return total;
    }

  },

  /**
   * For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array
   * which have that bit set. Then, if there are n integers in the array and k of them have a
   * particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the
   * total.
   */
  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int total = 0, n = nums.length;
      for (int j = 0; j < 32; j++) {
        int bitCount = 0;
        for (int i = 0; i < n; i++) {
          bitCount += nums[i] >> j & 1;
        }
        total += bitCount * (n - bitCount);
      }
      return total;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestTotalHammingDistance {

    @Test
    public void testSolutions() {
      int x = 1;
      int y = 3124523;
      int z = 75984823;
      Z.print(Integer.bitCount(x ^ y) + Integer.bitCount(x ^ z));
      Z.print(Integer.bitCount(y ^ z));
    }

  }

}