package org.mo39.fmbh.datastructure.heap;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a n x n matrix where each of the rows and columns are sorted in ascending
 * order, find the kth smallest element in the matrix.
 * 
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct
 * element.
 * 
 * 
 * Example:
 * 
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 
 * return 13.
 * 
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/">Kth
 *      Smallest Element In A Sorted Matrix</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum KthSmallestElementInASortedMatrix {

  HEAP_SOLUTION {

    @Override
    public int solve(int[][] matrix, int k) {
      PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          heap.add(matrix[i][j]);
          if (heap.size() > k) heap.poll();
        }
      }
      return heap.poll();
    }

  };

  public abstract int solve(int[][] matrix, int k);

  public static class TestKthSmallestElementInASortedMatrix {

    private int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
    private int k = 8;
    private int expected = 13;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, HEAP_SOLUTION.solve(matrix, k));
    }
  }

}
