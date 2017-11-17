package org.mo39.fmbh.datastructure.hash;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import java.util.HashMap;
import java.util.Map;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * Given a non-empty array of non-negative integers nums, the degree of this array
 * is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray
 * of nums, that has the same degree as nums.
 *
 * Example 1:
 *
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 *
 *
 *
 *
 * Example 2:
 *
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 *
 *
 *
 * Note:
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 * </pre>
 *
 * @see <a href="https://leetcode.com/problems/degree-of-an-array/">Degree Of An Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DegreeOfAnArray {
  SOLUTION {
    @Override
    public int findShortestSubArray(int[] nums) {
      Map<Integer, int[]> map = new HashMap<>();
      int degree = 1;
      for (int i = 0; i < nums.length; i++) {
        int[] arr = map.getOrDefault(nums[i], new int[] {0, i, i});
        degree = Math.max(degree, ++arr[0]);
        arr[2] = i;
        map.put(nums[i], arr);
      }
      int min = nums.length;
      for (Integer key : map.keySet()) {
        int[] arr = map.get(key);
        if (arr[0] == degree) min = Math.min(min, arr[2] - arr[1] + 1);
      }
      return min;
    }
  };

  public abstract int findShortestSubArray(int[] nums);

  public static class DegreeOfAnArrayTest {

    int[] nums = {1, 2, 2, 3, 1};
    int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.findShortestSubArray(nums));
    }
  }
}
