package org.mo39.fmbh.algorithm.backtracking;

import org.mo39.fmbh.common.annotation.ProblemSource;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

/**
 *
 *
 * <pre>
 * We have two special characters. The first character can be represented by one
 * bit 0. The second character can be represented by two bits (10 or 11).
 *
 * Now given a string represented by several bits. Return whether the last character
 * must be a one-bit character or not. The given string will always end with a
 * zero.
 *
 * Example 1:
 *
 * Input:
 * bits = [1, 0, 0]
 * Output: True
 * Explanation:
 * The only way to decode it is two-bit character and one-bit character. So the
 * last character is one-bit character.
 *
 *
 *
 * Example 2:
 *
 * Input:
 * bits = [1, 1, 1, 0]
 * Output: False
 * Explanation:
 * The only way to decode it is two-bit character and two-bit character. So the
 * last character is NOT one-bit character.
 *
 *
 *
 * Note:
 * 1 <= len(bits) <= 1000.
 * bits[i] is always 0 or 1.
 * </pre>
 *
 * @author Jihan Chen
 * @see <a href="https://leetcode.com/problems/1-bit-and-2-bit-characters/description/">One Bit And
 *     Two Bit Characters</a>
 */
@ProblemSource(LEETCODE)
public enum OneBitAndTwoBitCharacters {
  SOLUTION {
    @Override
    public boolean isOneBitCharacter(int[] bits) {
      return recur(bits, 0);
    }

    /** i presents the index that has not been decoded yet. */
    boolean recur(int[] bits, int i) {
      if (i >= bits.length - 1) return i != bits.length;
      if (bits[i] == 0) return recur(bits, i + 1);
      else return recur(bits, i + 2);
    }

  };

  public abstract boolean isOneBitCharacter(int[] bits);
}
