package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target,
 * S. Now you have 2 symbols + and -. For each integer, you should choose one
 * from + and - as its new symbol.
 *  
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to target
 * S.  
 * 
 * 
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array is positive and will not exceed 20. 
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/target-sum/">Target Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum TargetSum {

  RECURSIVE_SOLUTION {

    @Override
    public int solve(int[] nums, int S) {
      return recur(nums, S, nums.length - 1);
    }

    private int recur(int[] nums, int S, int i) {
      if (i < 0) {
        if (S == 0) return 1;
        return 0;
      }
      return recur(nums, S - nums[i], i - 1) + recur(nums, S + nums[i], i - 1);
    }

  },

  /**
   * Keep an eye on the loop. It is wrong to put the loop over nums inside. There is a sharp
   * contrast to another problem.
   */
  WRONG_BOTTOM_UP_METHOD {

    @Override
    public int solve(int[] nums, int S) {
      Map<Integer, Integer> memo = new HashMap<>();
      memo.put(0, 1);
      for (int i = 0; i <= S; i++) {
        for (int j = 0; j < nums.length; j++) {
          if (memo.containsKey(i)) {
            memo.compute(i + nums[j], (k, v) -> v == null ? 1 : v + 1);
            memo.compute(i - nums[j], (k, v) -> v == null ? 1 : v + 1);
          }
        }
      }
      return memo.get(S) == null ? 0 : memo.get(S);
    }

  },

  /**
   * This is kind of 'nice' idea but will run out of space and time. Say I have 1000 numbers, I
   * would poll each one of them and make 2000 numbers in next queue.
   */
  BOTTOM_UP_METHOD_0 {

    @Override
    public int solve(int[] nums, int S) {
      Queue<Integer> queue = new LinkedList<>();
      queue.add(0);
      int count = 0;
      for (int i = 0; i < nums.length; i++) {
        for (int j = queue.size(); j > 0; j--) {
          int pre = queue.poll();
          if (i != nums.length - 1) {
            queue.add(pre + nums[i]);
            queue.add(pre - nums[i]);
          } else {
            if (pre + nums[i] == S) count++;
            if (pre - nums[i] == S) count++;
          }
        }
      }
      return count;
    }

  },

  BOTTOM_UP_METHOD_1 {

    @Override
    public int solve(int[] nums, int S) {
      int sum = 0;
      for (int i : nums) {
        sum += i;
      }
      if (S > sum || S < -sum) return 0;
      int[] dp = new int[2 * sum + 1];
      dp[0 + sum] = 1;
      for (int i = 0; i < nums.length; i++) {
        int[] next = new int[2 * sum + 1];
        for (int k = 0; k < 2 * sum + 1; k++) {
          if (dp[k] != 0) {
            next[k + nums[i]] += dp[k];
            next[k - nums[i]] += dp[k];
          }
        }
        dp = next;
      }
      return dp[sum + S];
    }

  },

  BOTTOM_UP_METHOD_2 {

    @Override
    public int solve(int[] nums, int S) {
      Map<Integer, Integer> memo = new HashMap<>();
      memo.put(0, 1);
      for (int i = 0; i < nums.length; i++) {
        Map<Integer, Integer> newMemo = new HashMap<>();
        for (Integer key : memo.keySet()) {
          int count = memo.get(key);
          newMemo.compute(key + nums[i], (k, v) -> v == null ? count : v + count);
          newMemo.compute(key - nums[i], (k, v) -> v == null ? count : v + count);
        }
        memo = newMemo;
      }
      return memo.get(S) == null ? 0 : memo.get(S);
    }

  };

  public abstract int solve(int[] nums, int S);

  public static class TestTargetSum {

    int[] nums = {34, 16, 5, 38, 20, 20, 8, 43, 3, 46, 24, 12, 28, 19, 22, 28, 9, 46, 25, 36};
    int S = 0;
    int expected = 6638;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, RECURSIVE_SOLUTION.solve(nums, S));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD_0.solve(nums, S));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD_1.solve(nums, S));
      Assert.assertEquals(expected, BOTTOM_UP_METHOD_2.solve(nums, S));
    }

  }

}
