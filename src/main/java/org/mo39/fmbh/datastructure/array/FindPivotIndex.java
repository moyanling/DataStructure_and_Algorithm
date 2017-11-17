package org.mo39.fmbh.datastructure.array;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * Given an array of integers nums, write a method that returns the "pivot" index
 * of this array.
 *
 * We define the pivot index as the index where the sum of the numbers to the
 * left of the index is equal to the sum of the numbers to the right of the index.
 *
 * If no such index exists, we should return -1. If there are multiple pivot indexes,
 * you should return the left-most pivot index.
 *
 *
 * Example 1:
 *
 * Input:
 * nums = [1, 7, 3, 6, 5, 6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the
 * sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 *
 *
 *
 * Example 2:
 *
 * Input:
 * nums = [1, 2, 3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 *
 *
 *
 * Note:
 * The length of nums will be in the range [0, 10000].
 * Each element nums[i] will be an integer in the range [-1000, 1000].
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/find-pivot-index/">Find Pivot Index</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindPivotIndex {
  SOLUTION {
    @Override
    public int pivotIndex(int[] nums) {
      int sum = 0, i = 0, leftSum = 0;
      for (int n : nums) sum += n;
      for (; i < nums.length; i++) {
        if (leftSum * 2 == sum - nums[i]) return i;
        leftSum += nums[i];
      }
      return -1;
    }
  };

  public abstract int pivotIndex(int[] nums);

  public static class FindPivotIndexTest {

    int[] nums = {1, 7, 3, 6, 5, 6};
    int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.pivotIndex(nums));
    }

  }

}
