package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given scores of N athletes, find their relative ranks and the people with the
 * top three highest scores, who will be awarded medals: "Gold Medal", "Silver
 * Medal" and "Bronze Medal".
 * 
 * Example 1:
 * 
 * Input: [5, 4, 3, 2, 1]
 * Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * Explanation: The first three athletes got the top three highest scores, so
 * they got "Gold Medal", "Silver Medal" and "Bronze Medal". For the left two
 * athletes, you just need to output their relative ranks according to their scores.
 * 
 * 
 * 
 * Note:
 * 
 * N is a positive integer and won't exceed 10,000.
 * All the scores of athletes are guaranteed to be unique.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/relative-ranks/">Relative Ranks</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum RelativeRanks {

  SOLUTION {

    @Override
    public String[] solve(int[] nums) {
      String[] medals = {"Gold Medal", "Silver Medal", "Bronze Medal"};
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
      }
      Arrays.sort(nums);
      String[] result = new String[nums.length];
      for (int i = result.length - 1; i >= 0; i--) {
        result[map.get(nums[nums.length - i - 1])] = i < 3 ? medals[i] : String.valueOf(i + 1);
      }
      return result;
    }

  };

  public abstract String[] solve(int[] nums);

}
