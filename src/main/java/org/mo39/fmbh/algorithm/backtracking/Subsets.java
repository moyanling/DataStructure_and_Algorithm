package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * 
 * For example,
 * If nums = [1,2,3], a solution is:
 * 
 * 
 * 
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/subsets/">Subsets</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Subsets {

  RECURSIVE_SOLUTION_0 {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      recur(result, nums, 0);
      return result;
    }

    void recur(List<List<Integer>> result, int[] nums, int start) {
      if (start >= nums.length - 1) {
        result.add(new ArrayList<>());
        result.add(new ArrayList<>(Arrays.asList(nums[start])));
        return;
      }
      recur(result, nums, start + 1);
      for (int i = result.size() - 1; i > -1; i--) {
        List<Integer> list = new ArrayList<>(result.get(i));
        list.add(nums[start]);
        result.add(list);
      }
    }

  },

  RECURSIVE_SOLUTION_1 {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      // TODO Auto-generated method stub
      return null;
    }

  },

  ITERATIVE_SOLUTION {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      // TODO Auto-generated method stub
      return null;
    }

  },

  /**
   * <pre>
   * 0) 0 0 0  -> Dont take 3 , Dont take 2 , Dont take 1 = { } 
   * 1) 0 0 1  -> Dont take 3 , Dont take 2 ,   take 1    = { 1 } 
   * 2) 0 1 0  -> Dont take 3 ,    take 2   , Dont take 1 = { 2 } 
   * 3) 0 1 1  -> Dont take 3 ,    take 2   ,   take 1    = { 1 , 2 } 
   * 4) 1 0 0  ->    take 3   , Dont take 2 , Dont take 1 = { 3 } 
   * 5) 1 0 1  ->    take 3   , Dont take 2 ,   take 1    = { 1 , 3 } 
   * 6) 1 1 0  ->    take 3   ,    take 2   , Dont take 1 = { 2 , 3 } 
   * 7) 1 1 1  ->    take 3   ,    take 2   ,   take 1    = { 1 , 2 , 3 }
   * </pre>
   */
  BIT_MAPPING {

    @Override
    public List<List<Integer>> solve(int[] nums) {
      int totalNumber = 1 << nums.length;
      List<List<Integer>> result = new ArrayList<List<Integer>>(totalNumber);
      for (int i = 0; i < totalNumber; i++) {
        List<Integer> list = new LinkedList<Integer>();
        for (int j = 0; j < nums.length; j++) {
          if ((i & 1 << j) != 0) list.add(nums[j]);
        }
        result.add(list);
      }
      return result;
    }

  };

  public abstract List<List<Integer>> solve(int[] nums);

}
