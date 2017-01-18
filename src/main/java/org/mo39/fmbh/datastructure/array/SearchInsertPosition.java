package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in order.
 * 
 * You may assume no duplicates in the array.
 * 
 * 
 * Here are few examples.
 * [1,3,5,6], 5 &#8594; 2
 * [1,3,5,6], 2 &#8594; 1
 * [1,3,5,6], 7 &#8594; 4
 * [1,3,5,6], 0 &#8594; 0
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/search-insert-position/">Search Insert Position</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SearchInsertPosition {

  CHEATER {

    @Override
    public int solve(int[] nums, int target) {
      int result = Arrays.binarySearch(nums, target);
      return result < 0 ? -result - 1 : result;
    }

  },

  SOLUTION {

    @Override
    public int solve(int[] nums, int target) {
      int upper = nums.length - 1, lower = 0;
      while (lower <= upper) {
        int mid = lower + upper >>> 1;
        if (target == nums[mid]) return mid;
        else if (target < nums[mid]) upper = mid - 1;
        else lower = mid + 1;
      }
      return lower;
    }

  };

  public abstract int solve(int[] nums, int target);

}
