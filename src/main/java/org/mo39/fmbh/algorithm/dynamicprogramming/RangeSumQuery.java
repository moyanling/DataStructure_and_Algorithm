package org.mo39.fmbh.algorithm.dynamicprogramming;

/**
 * 
 * @see <a href="https://leetcode.com/problems/range-sum-query-immutable/">Range Sum Query -
 *      Immutable</a>
 * 
 * @author Jihan Chen
 *
 */
public enum RangeSumQuery {

  /**
   * This is a top-down with memoization. Time complexity should be <b>O(1)</b> to initiate.
   * <b>O(n)</b> to solve.
   * <p>
   * Recurrence Formula:<br>
   *
   * <pre>
   *              | nums[i]                         if i=j
   * sumof[i,j] = | sumof[i,j-1] + nums[j]          if i<j
   * </pre>
   * 
   */
  TOP_DOWN_WITH_MEMO {

    protected int[] nums;
    protected Integer[][] memo;

    @Override
    public void init(int[] nums) {
      this.nums = nums;
      this.memo = new Integer[nums.length][nums.length];
    }

    @Override
    public int solve(int i, int j) {
      if (memo[i][j] != null) return memo[i][j];
      if (i == j) return nums[i];
      int sum = solve(i, j - 1);
      memo[i][j - 1] = sum;
      return sum + nums[j];
    }
  },

  /**
   * Time complexity is <b>O(n^2)</b> to initiate. <b>O(1)</b> to solve.
   * 
   */
  BOTTOM_UP_SOLUTION {

    protected int[] nums;
    protected Integer[][] memo;

    @Override
    public void init(int[] nums) {
      this.nums = nums;
      this.memo = new Integer[nums.length][nums.length];
      for (int len = 2; len <= nums.length; len++) {
        for (int i = 0; i + len <= nums.length; i++) {
          memo[i][i + len - 1] = (len == 2 ? nums[i] : memo[i][i + len - 2]) + nums[i + len - 1];
        }
      }
    }

    @Override
    public int solve(int i, int j) {
      return i == j ? nums[i] : memo[i][j];
    }

  },

  /**
   * Time complexity is <b>O(n)</b> to initiate. <b>O(1)</b> to solve.
   * 
   */
  BOTTOM_UP_METHOD {

    protected int[] nums;

    @Override
    public void init(int[] nums) {
      for (int i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
      }
      this.nums = nums;
    }

    @Override
    public int solve(int i, int j) {
      if (i == 0) return nums[j];
      return nums[j] - nums[i - 1];
    }

  };

  public abstract void init(int[] nums);

  public abstract int solve(int i, int j);

}
