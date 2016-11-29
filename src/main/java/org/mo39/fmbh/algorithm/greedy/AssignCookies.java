package org.mo39.fmbh.algorithm.greedy;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <br/>
 * Assume you are an awesome parent and want to give your children some cookies. But, you should
 * give each child at most one cookie. Each child i has a greed factor gi, which is the minimum size
 * of a cookie that the child will be content with; and each cookie j has a size sj. If sj >= gi, we
 * can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize
 * the number of your content children and output the maximum number.<br/>
 * Note:<br/>
 * You may assume the greed factor is always positive. <br/>
 * You cannot assign more than one cookie to one child.<br/>
 * Example 1:<br/>
 * Input: [1,2,3], [1,1]<br/>
 * Output: 1<br/>
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * <br/>
 * And even though you have 2 cookies, since their size is both 1, you could only make the child
 * whose greed factor is 1 content.<br/>
 * You need to output 1.<br/>
 * Example 2:<br/>
 * Input: [1,2], [1,2,3]<br/>
 * Output: 2<br/>
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. <br/>
 * You have 3 cookies and their sizes are big enough to gratify all of the children, <br/>
 * You need to output 2.
 *
 * @see <a href="https://leetcode.com/problems/assign-cookies/">Assign Cookies</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum AssignCookies {

  GREEDY {

    @Override
    public int solve(int[] g, int[] s) {
      Arrays.sort(g);
      Arrays.sort(s);
      int i = 0;
      for (int j = 0; j < s.length; j++) {
        if (i < g.length && s[j] >= g[i]) i++;
      }
      return i;
    }

  };

  public abstract int solve(int[] g, int[] s);

  public static class TestAssignCookies {

    private int[] g = {1, 2};
    private int[] s = {1, 2, 3};
    private int expected = 2;

    @Test
    public void testSolutions() {
      Assert.assertEquals(expected, GREEDY.solve(g, s));
    }

  }

}
