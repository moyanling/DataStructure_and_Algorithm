package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may
 * exist one celebrity. The definition of a celebrity is that all the other n - 1 people know
 * him/her but he/she does not know any of them.
 * 
 * 
 * 
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you
 * are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether
 * A knows B. You need to find out the celebrity (or verify there is not one) by asking as few
 * questions as possible (in the asymptotic sense).
 * 
 * 
 * 
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a
 * function int findCelebrity(n), your function should minimize the number of calls to knows.
 * 
 * 
 * 
 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label
 * if there is a celebrity in the party. If there is no celebrity, return -1.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-celebrity/">Find The Celebrity</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum FindTheCelebrity {

  /**
   * The idea matters. From the intuitive perspective, get a knowledge pool from the first person
   * and try to find the celebrity from the pool + the first person. Not good enough but can pass
   * leetcode.
   */
  SOLUTION_0 {

    @Override
    public int solve(int n) {
      List<Integer> pool = new ArrayList<>();
      for (int i = 1; i < n; i++) {
        if (api.knows(0, i)) pool.add(i);
      }
      pool.add(0);
      int i = 1;
      for (; pool.size() > 0 && i < n; i++) {
        final int j = i;
        pool = pool.stream().filter(p -> j == p || api.knows(j, p)).collect(Collectors.toList());
      }
      if (pool.size() != 1) return -1;
      int theOne = pool.get(0);
      for (int j = 0; j < n; j++) {
        if (theOne != j && api.knows(theOne, j)) return -1;
      }
      return theOne;
    }

  },

  /**
   * The idea matters. Pay attention to exactly one celebrity and optimize the first filter loop.
   */
  SOLUTION_1 {

    @Override
    public int solve(int n) {
      int candidate = 0;
      for (int i = 1; i < n; i++) {
        if (api.knows(candidate, i)) candidate = i;
      }
      for (int i = 0; i < n; i++) {
        if (i != candidate && (api.knows(candidate, i) || !api.knows(i, candidate))) return -1;
      }
      return candidate;
    }

  };

  protected API api;

  public abstract int solve(int n);

  public interface API {
    boolean knows(int a, int b);
  }

}

