package org.mo39.fmbh.datastructure.stringsandarrays.cc150;

import java.util.Arrays;

/**
 * Two questions to be considered: <br>
 * - If whitespace matters<br>
 * - If case matters
 * 
 * @author Jihan Chen
 *
 */
public class Problem1_3 {

  public static void main(String[] args) {

  }

  public boolean isPermutation0(String s, String t) {
    if (s == null && t == null) return true;
    if (s == null || t == null) return false;
    if (s.length() != t.length()) return false;
    int[] charArr = new int[s.length()];
    char[] sChar = s.toCharArray();
    char[] tChar = t.toCharArray();
    for (int i = 0; i < s.length(); i++) {
      charArr[sChar[i]]++;
      charArr[tChar[i]]--;
    }
    for (int i : charArr)
      if (i != 0) return false;
    return true;
  }

  public boolean isPermutation1(String s, String t) {
    if (s == null && t == null) return true;
    if (s == null || t == null) return false;
    if (s.length() != t.length()) return false;
    char[] tChar = t.toCharArray();
    char[] sChar = s.toCharArray();
    return Arrays.equals(tChar, sChar);
  }

  public boolean isPermutation2(String s, String t) {
    if (s == null && t == null) return true;
    if (s == null || t == null) return false;
    if (s.length() != t.length()) return false;
    int[] charArr = new int[s.length()];
    char[] sChar = s.toCharArray();
    char[] tChar = t.toCharArray();
    for (int i = 0; i < s.length(); i++) {
      charArr[sChar[i]]++;
    }
    for (char c : tChar) {
      if (--charArr[c] < 0) return false;
    }
    return true;
  }
}
