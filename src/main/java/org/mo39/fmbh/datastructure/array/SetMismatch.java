package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * The set S originally contains numbers from 1 to n. But unfortunately, due to
 * the data error, one of the numbers in the set got duplicated to another number
 * in the set, which results in repetition of one number and loss of another number.
 * 
 * 
 * 
 * 
 * Given an array nums representing the data status of this set after the error.
 * Your task is to firstly find the number occurs twice and then find the number
 * that is missing. Return them in the form of an array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * 
 * 
 * 
 * Note:
 * 
 * The given array size will in the range [2, 10000].
 * The given array's numbers won't have any order.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/set-mismatch/">Set Mismatch</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum SetMismatch {

  SOLUTION {

    @Override
    public int[] solve(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      int dup = 0, sum = 0;
      for (int i : nums) {
        sum += i;
        int count = map.getOrDefault(i, 0) + 1;
        map.put(i, count);
        if (count >= 2) dup = i;
      }
      return new int[] {dup, (1 + nums.length) * nums.length / 2 - sum + dup};
    }

  };

  public abstract int[] solve(int[] nums);

}
