package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.HashMap;
import java.util.Map;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are
 * such that A[i] + B[j] + C[k] + D[l] is zero.
 * 
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 &le; N &le; 500. All
 * integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * 
 * Example:
 * 
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/4sum-ii/">4 Sum II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FourSumII {

  /**
   * There's a better implementation.
   */
  SOLUTION_0 {

    @Override
    public int solve(int[] A, int[] B, int[] C, int[] D) {
      Map<Integer, Integer> ab = sumMapOf(A, B), cd = sumMapOf(C, D);
      int count = 0;
      for (Integer key : ab.keySet()) {
        if (cd.containsKey(-key)) count += ab.get(key) * cd.get(-key);
      }
      return count;
    }

    private Map<Integer, Integer> sumMapOf(int[] A, int[] B) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < A.length; i++) {
        for (int j = 0; j < B.length; j++) {
          map.compute(A[i] + B[j], (k, v) -> v == null ? 1 : v + 1);
        }
      }
      return map;
    }

  },

  SOLUTION_1 {

    @Override
    public int solve(int[] A, int[] B, int[] C, int[] D) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < C.length; i++) {
        for (int j = 0; j < D.length; j++) {
          int sum = C[i] + D[j];
          map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
      }
      int count = 0;
      for (int i = 0; i < A.length; i++) {
        for (int j = 0; j < B.length; j++) {
          count += map.getOrDefault(-1 * (A[i] + B[j]), 0);
        }
      }
      return count;
    }


  };

  public abstract int solve(int[] A, int[] B, int[] C, int[] D);

}
