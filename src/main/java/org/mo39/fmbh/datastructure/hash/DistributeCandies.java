package org.mo39.fmbh.datastructure.hash;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given an integer array with even length, where different numbers in this array
 * represent different kinds of candies. Each number means one candy of the corresponding
 * kind. You need to distribute these candies equally in number to brother and
 * sister. Return the maximum number of kinds of candies the sister could gain.
 * 
 * 
 * Example 1:
 * 
 * Input: candies = [1,1,2,2,3,3]
 * Output: 3
 * Explanation:
 * There are three different kinds of candies (1, 2 and 3), and two candies for
 * each kind.
 * Optimal distribution: The sister has candies [1,2,3] and the brother has candies
 * [1,2,3], too. 
 * The sister has three different kinds of candies. 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: candies = [1,1,2,3]
 * Output: 2
 * Explanation: For example, the sister has candies [2,3] and the brother has
 * candies [1,1]. 
 * The sister has two different kinds of candies, the brother has only one kind
 * of candies. 
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array is in range [2, 10,000], and will be even.
 * The number in given array is in range [-100,000, 100,000].
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/distribute-candies/">Distribute Candies</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DistributeCandies {

  SOLUTION_0 {

    @Override
    public int solve(int[] candies) {
      return Math.min(candies.length / 2,
          Arrays.stream(candies).boxed().collect(Collectors.toSet()).size());
    }

  },

  /**
   * In the loop, if the set's size reaches length/2, you could do an early termination which
   * improves a bit performance here.
   */
  SOLUTION_1 {

    @Override
    public int solve(int[] candies) {
      Set<Integer> set = new HashSet<>();
      for (int i : candies) {
        set.add(i);
        if (set.size() >= candies.length / 2) return candies.length / 2;
      }
      return set.size();
    }

  };

  public abstract int solve(int[] candies);


}
