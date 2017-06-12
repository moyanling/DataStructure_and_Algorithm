package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an array of 2n integers, your task is to group these integers into n
 * pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of
 * min(ai, bi) for all i from 1 to n as large as possible.
 * 
 * 
 * Example 1:
 * 
 * Input: [1,4,3,2]
 * 
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4.
 * 
 * 
 * 
 * Note:
 * 
 * n is a positive integer, which is in the range of [1, 10000].
 * All the integers in the array will be in the range of [-10000, 10000].
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/array-partition-i/">Array Partition I</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ArrayPartitionI {

  /**
   * The key point is that the largest number is always going to be wasted. Just be greedy.
   */
  SOLUTION {

    @Override
    public int solve(int[] nums) {
      Arrays.sort(nums);
      int result = 0;
      for (int i = 0; i < nums.length; i += 2) {
        result += nums[i];
      }
      return result;
    }

  };

  public abstract int solve(int[] nums);

}
