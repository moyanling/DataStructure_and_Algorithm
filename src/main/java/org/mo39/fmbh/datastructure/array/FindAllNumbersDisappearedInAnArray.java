package org.mo39.fmbh.datastructure.array;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements
 * appear twice and others appear once.
 * 
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * 
 * Could you do it without extra space and in O(n) runtime? You may assume the
 * returned list does not count as extra space.
 * 
 * Example:
 * 
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [5,6]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/">Find All
 *      Numbers Disappeared In An Array</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindAllNumbersDisappearedInAnArray {

  /**
   * Accepted. Time complexity is <b>O(n)</b>. But it's using extra space and it's ugly.
   */
  BAD_SOLUTION {

    @Override
    public List<Integer> solve(int[] nums) {
      Set<Integer> set = new HashSet<>();
      for (int i = 1; i <= nums.length; i++) {
        set.add(i);
      }
      for (int i : nums) {
        set.remove(i);
      }
      List<Integer> list = new ArrayList<>();
      for (int i : set) {
        list.add(i);
      }
      return list;
    }

  },

  /**
   * The index of an array is a piece of information.
   */
  LINEAR_SOLUTION {

    @Override
    public List<Integer> solve(int[] nums) {
      List<Integer> toRet = new ArrayList<Integer>();
      int n = nums.length;
      for (int i = 0; i < nums.length; i++) {
        nums[(nums[i] - 1) % n] += n;
      }
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] <= n) toRet.add(i + 1);
      }
      return toRet;
    }

  };

  public abstract List<Integer> solve(int[] nums);

  public static class TestFindAllNumbersDisappearedInAnArray {

    private int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};

    @Test
    public void testSolutions() {
      Assert.assertThat(LINEAR_SOLUTION.solve(nums), containsInAnyOrder(5, 6));
    }

  }

}
