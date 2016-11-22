package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that
 * they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and
 * index2) are not zero-based.
 * <p>
 * You may assume that each input would have exactly one solution.
 *
 * @see <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">Two Sum II - Input
 *      array is sorted</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource(LEETCODE)
public enum TwoSumII {

  BRUTE_FORCE {

    @Override
    public int[] solve(int[] numbers, int target) {
      for (int i = 0; i < numbers.length; i++) {
        for (int j = i + 1; j < numbers.length; j++) {
          if (numbers[i] + numbers[j] > target) break;
          if (numbers[i] + numbers[j] == target) return new int[] {i + 1, j + 1};
        }
      }
      return null;
    }

  },

  HASHMAP_SOLUTION {

    @Override
    public int[] solve(int[] numbers, int target) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < numbers.length; i++) {
        if (map.containsKey(target - numbers[i]))
          return new int[] {map.get(target - numbers[i]), 1 + i};
        map.put(numbers[i], i + 1);
      }
      return null;
    }

  },

  SHRINK_RANGE {

    @Override
    public int[] solve(int[] numbers, int target) {
      int low = 0, high = numbers.length - 1, sum;
      while ((sum = numbers[low] + numbers[high]) != target) {
        if (sum < target) low++;
        else high--;
      }
      return new int[] {low + 1, high + 1};
    }

  };

  public abstract int[] solve(int[] numbers, int target);

}
