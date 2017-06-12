package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Given a word, you need to judge whether the usage of capitals in it is right
 * or not.
 * 
 * 
 * 
 * We define the usage of capitals in a word to be right when one of the following
 * cases holds:
 * 
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital if it has more than one letter,
 * like "Google".
 * 
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "USA"
 * Output: True
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "FlaG"
 * Output: False
 * 
 * 
 * 
 * Note:
 * The input will be a non-empty word consisting of uppercase and lowercase latin
 * letters.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/detect-capital/">Detect Capital</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum DetectCapital {

  REGEX {

    @Override
    public boolean solve(String word) {
      return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }

  },

  SOLUTION {

    @Override
    public boolean solve(String word) {
      if (word.length() < 2) return true;
      char[] arr = word.toCharArray();
      boolean second = Character.isUpperCase(arr[1]);
      if (!Character.isUpperCase(arr[0]) && second) return false;
      for (int i = 2; i < arr.length; i++) {
        if (Character.isUpperCase(arr[i]) != second) return false;
      }
      return true;
    }

  };

  public abstract boolean solve(String word);

}
