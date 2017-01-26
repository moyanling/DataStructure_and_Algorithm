package org.mo39.fmbh.algorithm.depthfirstsearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an integer array, your task is to find all the different possible increasing
 * subsequences of the given array, and the length of an increasing subsequence
 * should be at least 2 .
 * 
 * 
 * Example:
 * 
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7],
 * [4,7,7]]
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also
 * be considered as a special case of increasing sequence.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/increasing-subsequences/">Increasing Subsequences</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum IncreasingSubsequences {

  BACK_TRACKING {

    @Override
    public List<List<Integer>> findSubsequences(int[] nums) {
      Set<List<Integer>> result = new HashSet<>();
      recur(result, new LinkedList<>(), nums, 0);
      return new ArrayList<>(result);
    }

    void recur(Set<List<Integer>> result, LinkedList<Integer> cur, int[] nums, int start) {
      if (cur.size() > 1) result.add(new ArrayList<>(cur));
      for (int i = start; i < nums.length; i++) {
        if (cur.isEmpty() || !cur.isEmpty() && nums[i] >= cur.getLast()) {
          cur.add(nums[i]);
          recur(result, cur, nums, i + 1);
          cur.removeLast();
        }
      }
    }

  };

  public abstract List<List<Integer>> findSubsequences(int[] nums);

}
