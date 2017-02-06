package org.mo39.fmbh.algorithm.dynamicprogramming;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a triangle, find the minimum path sum from top to bottom. Each step you
 * may move to adjacent numbers on the row below.
 * 
 * 
 * For example, given the following triangle
 * 
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 
 * 
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * 
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n
 * is the total number of rows in the triangle.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/triangle/">Triangle</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Triangle {

  SOLUTION {

    @Override
    public int solve(List<List<Integer>> triangle) {
      for (int i = triangle.size() - 1; i > 0; i--) {
        for (int j = 0; j < triangle.get(i).size() - 1; j++) {
          triangle.get(i - 1).set(j, triangle.get(i - 1).get(j)
              + Math.min(triangle.get(i).get(j), triangle.get(i).get(j + 1)));
        }
      }
      return triangle.get(0).get(0);
    }

  };

  public abstract int solve(List<List<Integer>> triangle);

  public static class TestTriangle {

    List<List<Integer>> triangle = new ArrayList<>();
    int expected = 11;

    {
      triangle.add(new ArrayList<>(Arrays.asList(2)));
      triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
      triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
      triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
    }

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(triangle));
    }

  }

}
