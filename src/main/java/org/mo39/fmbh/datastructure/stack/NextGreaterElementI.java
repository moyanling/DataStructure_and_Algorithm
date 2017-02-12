package org.mo39.fmbh.datastructure.stack;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s
 * elements are subset of nums2. Find all the next greater numbers for nums1's
 * elements in the corresponding places of nums2. 
 * 
 * 
 * 
 * The Next Greater Number of a number x in nums1 is the first greater number
 * to its right in nums2. If it does not exist, output -1 for this number.
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number
 * for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the
 * second array is 3.
 *     For number 2 in the first array, there is no next greater number for it
 * in the second array, so output -1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 *     For number 2 in the first array, the next greater number for it in the
 * second array is 3.
 *     For number 4 in the first array, there is no next greater number for it
 * in the second array, so output -1.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/next-greater-element-i/">Next Greater Element I</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum NextGreaterElementI {

  SOLUTION {

    @Override
    public int[] solve(int[] findNums, int[] nums) {
      int[] result = new int[findNums.length];
      Arrays.fill(result, -1);
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
      }
      for (int i = 0; i < result.length; i++) {
        for (int j = map.get(findNums[i]) + 1; j < nums.length; j++) {
          if (nums[j] > findNums[i]) {
            result[i] = nums[j];
            break;
          }
        }
      }
      return result;
    }

  },

  STACK_SOLUTION {

    @Override
    public int[] solve(int[] findNums, int[] nums) {
      Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
      Stack<Integer> stack = new Stack<>();
      for (int num : nums) {
        while (!stack.isEmpty() && stack.peek() < num) {
          map.put(stack.pop(), num);
        }
        stack.push(num);
      }
      for (int i = 0; i < findNums.length; i++) {
        findNums[i] = map.getOrDefault(findNums[i], -1);
      }
      return findNums;
    }

  };

  public abstract int[] solve(int[] findNums, int[] nums);

}
