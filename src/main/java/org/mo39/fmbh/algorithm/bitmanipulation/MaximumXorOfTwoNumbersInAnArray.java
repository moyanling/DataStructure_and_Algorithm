package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 &le; ai <
 * 231.
 * 
 * Find the maximum result of ai XOR aj, where 0 &le; i, j &lt; n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * 
 * Input: [3, 10, 5, 25, 2, 8]
 * 
 * Output: 28
 * 
 * Explanation: The maximum result is 5 ^ 25 = 28.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/">Maximum Xor
 *      Of Two Numbers In An Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum MaximumXorOfTwoNumbersInAnArray {

  BRUTE_FORCE {

    @Override
    public int solve(int[] nums) {
      if (nums.length < 2) return 0;
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i; j < nums.length; j++) {
          max = Math.max(max, nums[i] ^ nums[j]);
        }
      }
      return max;
    }

  },

  BAD_SOLUTION {

    @Override
    public int solve(int[] nums) {
      if (nums.length < 2) return 0;
      int max = Integer.MIN_VALUE, posi = 32;
      List<List<Integer>> bits = new LinkedList<>();
      for (int i = 0; i < 32; i++) {
        bits.add(new ArrayList<>());
        for (int j = 0; j < nums.length; j++) {
          if ((nums[j] >>> i & 1) == 1) bits.get(i).add(nums[j]);
        }
      }
      while (bits.get(--posi).size() == 0) {
      }
      for (int i = 0; i < nums.length; i++) {
        for (int j = 0; j < bits.get(posi).size(); j++) {
          max = Math.max(max, nums[i] ^ bits.get(posi).get(j));
        }
      }
      return max;
    }

  },

  /**
   * //TODO
   */
  SOLUTION {

    @Override
    public int solve(int[] nums) {
      int max = 0, mask = 0;
      for (int i = 31; i >= 0; i--) {
        mask = mask | 1 << i;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
          set.add(num & mask);
        }
        int tmp = max | 1 << i;
        for (int prefix : set) {
          if (set.contains(tmp ^ prefix)) {
            max = tmp;
            break;
          }
        }
      }
      return max;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestMaximumXorOfTwoNumbersInAnArray {

    private int[] nums = {3, 10, 5, 25, 2, 8};
    private int expected = 28;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, BRUTE_FORCE.solve(nums));
      Assert.assertEquals(expected, BAD_SOLUTION.solve(nums));
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}
