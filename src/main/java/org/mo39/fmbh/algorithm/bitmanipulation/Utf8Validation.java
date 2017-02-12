package org.mo39.fmbh.algorithm.bitmanipulation;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following
 * rules:
 * 
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed
 * by n-1 bytes with most significant 2 bits being 10.
 * 
 * This is how the UTF-8 encoding would work:
 * 
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 
 * 
 * Given an array of integers representing the data, return whether it is a valid
 * utf-8 encoding.
 * 
 * 
 * Note:
 * The input is an array of integers. Only the least significant 8 bits of each
 * integer is used to store the data. This means each integer represents only
 * 1 byte of data.
 * 
 * 
 * 
 * Example 1:
 * 
 * data = [197, 130, 1], which represents the octet sequence: 11000101 10000010
 * 00000001.
 * 
 * Return true.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * data = [235, 140, 4], which represented the octet sequence: 11101011 10001100
 * 00000100.
 * 
 * Return false.
 * The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
 * The next byte is a continuation byte which starts with 10 and that's correct.
 * But the second continuation byte does not start with 10, so it is invalid.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/utf-8-validation/">Utf 8 Validation</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum Utf8Validation {

  SOLUTION {

    @Override
    public boolean solve(int[] data) {
      Integer len = 0;
      for (int i = 0; i < data.length;) {
        len = getLength(data[i++]);
        if (len == null) return false;
        for (; len > 0; i++, len--) {
          if (i >= data.length || (data[i] & 128) != 128) return false;
        }
      }
      return len == 0;
    }

  };

  protected Integer getLength(int n) {
    if ((n & 248) == 248) return null;
    if ((n & 240) == 240) return 3;
    if ((n & 224) == 224) return 2;
    if ((n & 192) == 192) return 1;
    if ((n & 128) == 0) return 0;
    return null;
  }

  public abstract boolean solve(int[] data);

  public static class TestUtf8Validation {

    int[] data = {240, 162, 138, 147, 17};
    boolean expected = true;

    @Test
    public void testSolusions() {
      Assert.assertEquals(expected, SOLUTION.solve(data));
    }

  }

}
