package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

import com.google.common.collect.Lists;

/**
 * <pre>
 * Given an array of integers, 1 &le; a[i] &le; n (n = size of array), some elements
 * appear twice and others appear once.
 * 
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 * 
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [2,3]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-duplicates-in-an-array/">Find All Duplicates
 *      In An Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindAllDuplicatesInAnArray {

  SOLUTION_0 {

    @Override
    public List<Integer> solve(int[] nums) {
      List<Integer> toRet = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        nums[nums[i] % (nums.length + 1) - 1] += nums.length + 1;
      }
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] / (nums.length + 1) > 1) toRet.add(i + 1);
      }
      return toRet;
    }

  },

  SOLUTION_1 {

    @Override
    public List<Integer> solve(int[] nums) {
      List<Integer> toRet = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        int index = nums[i] % (nums.length + 1) - 1;
        if (nums[index] / (nums.length + 1) > 0) toRet.add(nums[i] % (nums.length + 1));
        nums[index] += nums.length + 1;
      }
      return toRet;
    }

  },

  SOLUTION_2 {

    @Override
    public List<Integer> solve(int[] nums) {
      List<Integer> toRet = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        int index = Math.abs(nums[i]) - 1;
        if (nums[index] < 0) toRet.add(Math.abs(nums[i]));
        else nums[index] = -nums[index];
      }
      return toRet;
    }

  };
  ;

  public abstract List<Integer> solve(int[] nums);

  public static class TestFindAllDuplicatesInAnArray {

    private int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
    private List<Integer> expected = Lists.newArrayList(2, 3);

    @Before
    public void before() {

    }

    @Test
    public void testSolution0() {
      Assert.assertEquals(expected, SOLUTION_0.solve(nums));
    }

    @Test
    public void testSolution1() {
      Assert.assertEquals(expected, SOLUTION_1.solve(nums));
    }

    @Test
    public void testSolution2() {
      Assert.assertEquals(expected, SOLUTION_2.solve(nums));
    }


  }

}
