package org.mo39.fmbh.datastructure.twopointers;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
 * unique triplets in the array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/3sum/">Three Sum</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ThreeSum {

  SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      Arrays.sort(nums);
      List<List<Integer>> result = new ArrayList<>();
      for (int i = 0; i < nums.length - 2; i++) {
        if (i == 0 || nums[i] != nums[i - 1]) {
          int start = i + 1, end = nums.length - 1;
          while (start < end) {
            int sum = nums[i] + nums[start] + nums[end];
            if (sum > 0) end--;
            else if (sum < 0) start++;
            else {
              result.add(new ArrayList<>(Arrays.asList(nums[i], nums[start], nums[end])));
              while (start < end && nums[start] == nums[start+1]) start++;
              while (start < end && nums[end] == nums[end-1]) end--;
              start++;
              end--;
            }
          }
        }
      }
      return result;
    }

  };

  public abstract List<List<Integer>> solve(int[] nums);

  public static class TestThreeSum {

    int[] nums = {-1, 0, 1, 2, -1, -4};

    @Test
    public void testSolutions() {
      Z.print(SOLUTION.solve(nums));
    }



  }

}
