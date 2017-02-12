package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * 
 * For example, given s = "aab",
 * 
 * Return
 * 
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/palindrome-partitioning/">Palindrome Partitioning</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum PalindromePartitioning {

  SOLUTION {

    @Override
    public List<List<String>> solve(String s) {
      LinkedList<List<String>> result = new LinkedList<>();
      recur(result, new LinkedList<>(), s, 0);
      return result;
    }

    void recur(List<List<String>> result, LinkedList<String> cur, String s, int start) {
      if (start >= s.length()) {
        result.add(new ArrayList<>(cur));
        return;
      }
      for (int i = start; i < s.length(); i++) {
        if (isPalindrome(s, start, i)) {
          cur.add(s.substring(start, i + 1));
          recur(result, cur, s, i + 1);
          cur.removeLast();
        }
      }
    }

    boolean isPalindrome(String s, int start, int end) {
      while (start < end) {
        if (s.charAt(start++) != s.charAt(end--)) return false;
      }
      return true;
    }

  };


  public abstract List<List<String>> solve(String s);

}
