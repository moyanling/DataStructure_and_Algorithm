package org.mo39.fmbh.algorithm.reservoirsampling;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an array of integers with possible duplicates, randomly output the index
 * of a given target number. You can assume that the given target number must
 * exist in the array.
 * 
 * 
 * 
 * Note:
 * The array size can be very large. Solution that uses too much extra space will
 * not pass the judge.
 * 
 * 
 * Example:
 * 
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should
 * have equal probability of returning.
 * solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/random-pick-index/">Random Pick Index</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RandomPickIndex {

  SOLUTION_0 {

    Random ran;
    Map<Integer, List<Integer>> map;

    @Override
    public void init(int[] nums) {
      ran = new Random();
      map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        if (!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
        map.get(nums[i]).add(i);
      }
    }

    @Override
    public int pick(int target) {
      List<Integer> list = map.get(target);
      return list.get(ran.nextInt(list.size()));
    }

  },

  SOLUTION_1 {

    Random ran;
    int[] nums;

    @Override
    public void init(int[] nums) {
      ran = new Random();
      this.nums = nums;
    }

    @Override
    public int pick(int target) {
      Integer reservoir = null, count = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == target && ran.nextInt(++count) == 0) reservoir = i;
      }
      return reservoir;
    }

  };

  public abstract void init(int[] nums);

  public abstract int pick(int target);

}
