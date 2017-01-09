package org.mo39.fmbh.datastructure.string;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the
 * given abbreviation.
 * 
 * A string such as "word" contains only the following valid abbreviations:
 * 
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2",
 * "2r1", "3d", "w3", "4"]
 * 
 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other
 * string is not a valid abbreviation of "word".
 * 
 * Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and
 * digits.
 * 
 * Example 1:
 * 
 * Given s = "internationalization", abbr = "i12iz4n":
 * 
 * Return true.
 * 
 * 
 * Example 2:
 * 
 * Given s = "apple", abbr = "a2e":
 * 
 * Return false.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/valid-word-abbreviation/">Valid Word Abbreviation</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum ValidWordAbbreviation {

  SOLUTION {

    @Override
    public boolean solve(String word, String abbr) {
      int count = 0, i = 0, j = 0;
      while (i + count < word.length() && j < abbr.length()) {
        if (Character.isDigit(abbr.charAt(j))) count = 10 * count + abbr.charAt(j++) - '0';
        else {
          i += count;
          count = 0;
          if (word.charAt(i++) != abbr.charAt(j++)) return false;
        }
      }
      return i + count == word.length() && j == abbr.length();
    }

  },

  REGULAR_EXPRESSION {

    @Override
    public boolean solve(String word, String abbr) {
      return word.matches(abbr.replaceAll("[1-9]\\d*", ".{$0}"));
    }

  };

  public abstract boolean solve(String word, String abbr);

  public static class TestValidWordAbbreviation {

    @Test
    public void testSolutions() {
      Assert.assertTrue(SOLUTION.solve("word", "2r1"));
      Assert.assertFalse(SOLUTION.solve("apple", "a2e"));
      // ---------
      Assert.assertTrue(REGULAR_EXPRESSION.solve("word", "2r1"));
      Assert.assertFalse(REGULAR_EXPRESSION.solve("apple", "a2e"));
    }

  }

}
