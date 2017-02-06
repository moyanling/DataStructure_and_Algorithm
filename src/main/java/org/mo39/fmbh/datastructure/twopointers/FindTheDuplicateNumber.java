package org.mo39.fmbh.datastructure.twopointers;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist. Assume
 * that there is only one duplicate number, find the duplicate one.
 * 
 * 
 * 
 * Note:
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more
 * than once.
 * 
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-duplicate-number/">Find The Duplicate
 *      Number</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindTheDuplicateNumber {

  SOLUTION {

    @Override
    public int solve(int[] nums) {
      return -1;
    }

  };

  public abstract int solve(int[] nums);

  public static class TestFindTheDuplicateNumber {

    int[] nums = {1, 3, 4, 2, 1};
    int expected = 1;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(nums));
    }

  }

}
