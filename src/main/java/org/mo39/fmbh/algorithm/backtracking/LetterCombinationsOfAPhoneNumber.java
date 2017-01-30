package org.mo39.fmbh.algorithm.backtracking;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.LinkedList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * 
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * 
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * 
 * Note:
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/">Letter
 *      Combinations Of A Phone Number</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum LetterCombinationsOfAPhoneNumber {

  SOLUTION {

    @Override
    public List<String> solve(String digits) {
      LinkedList<String> result = new LinkedList<String>();
      if (digits == null || digits.equals("")) return result;
      String[] mapping =
          new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
      result.add("");
      for (int i = 0; i < digits.length(); i++) {
        int x = Character.getNumericValue(digits.charAt(i));
        while (result.peekFirst().length() == i) {
          String t = result.removeFirst();
          for (char s : mapping[x].toCharArray()) {
            result.add(t + s);
          }
        }
      }
      return result;
    }

  };

  public abstract List<String> solve(String digits);

}
