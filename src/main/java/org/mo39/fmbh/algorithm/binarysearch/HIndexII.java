package org.mo39.fmbh.algorithm.binarysearch;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you
 * optimize your algorithm?
 * 
 * 
 * 
 * Expected runtime complexity is in O(log n) and the input is sorted.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/h-index-ii/">H Index II</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum HIndexII {

  SOLUTION {

    @Override
    public int solve(int[] citations) {
      if (citations.length == 0) return 0;
      int start = 0, end = citations.length - 1;
      while (true) {
        int mid = start + end >>> 1;
        if (start > end) return citations.length - start;
        if (citations[mid] < citations.length - mid) start = mid + 1;
        else {
          if (mid < 1 || citations[mid - 1] < citations.length - mid + 1)
            return citations.length - mid;
          end = mid - 1;
        }
      }
    }

  };

  public abstract int solve(int[] citations);

  public static class TestHIndexII {

    int[] citations = {1, 2, 3, 3, 5};
    int expected = 3;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, SOLUTION.solve(citations));
    }


  }

}
