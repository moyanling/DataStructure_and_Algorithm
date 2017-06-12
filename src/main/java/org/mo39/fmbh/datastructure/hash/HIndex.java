package org.mo39.fmbh.datastructure.hash;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given an array of citations (each citation is a non-negative integer) of a
 * researcher, write a function to compute the researcher's h-index.
 * 
 * 
 * 
 * According to the definition of h-index on Wikipedia: "A scientist has index
 * h if h of his/her N papers have at least h citations each, and the other N
 * − h papers have no more than h citations each."
 * 
 * 
 * 
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher
 * has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations
 * respectively. Since the researcher has 3 papers with at least 3 citations each
 * and the remaining two with no more than 3 citations each, his h-index is 3.
 * 
 * 
 * 
 * Note: If there are several possible values for h, the maximum one is taken
 * as the h-index.
 * 
 * 
 * 
 *   An easy approach is to sort the array first.
 *   What are the possible values of h-index?
 *   A faster approach is to use extra space.
 * 
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/h-index/">H Index</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum HIndex {

  SORT_SOLUTION {

    @Override
    public int solve(int[] citations) {
      Arrays.sort(citations);
      for (int i = 0; i < citations.length; i++) {
        if (citations.length - i <= citations[i]) return citations.length - i;
      }
      return 0;
    }

  },

  SOLUTION {

    @Override
    public int solve(int[] citations) {
      int n = citations.length;
      int[] papers = new int[n + 1];
      // counting papers for each citation number
      for (int c : citations) {
        papers[Math.min(n, c)]++;
      }
      // finding the h-index
      int k = n;
      for (int s = papers[n]; k > s; s += papers[k]) {
        k--;
      }
      return k;
    }

  };

  public abstract int solve(int[] citations);

}
