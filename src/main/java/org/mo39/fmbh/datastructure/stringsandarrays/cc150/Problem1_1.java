package org.mo39.fmbh.datastructure.stringsandarrays.cc150;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, coding is the very first thing to consider<br>
 * Only when it's coding in ASCII, the number of characters is limited to 256 <br>
 * In this case, add a check that return false if the length of the string is larger than 256 is
 * nice;
 * 
 * @author Jihan Chen
 *
 */
public class Problem1_1 {

  public static void main(String[] args) {

  }

  /**
   * Set.add returns a boolean which is super nice when trying to find duplicates when adding to set
   * sollection.
   * 
   * @param s
   * @return
   */
  public boolean hasAllUniqueChar0(String s) {
    Set<Character> set = new HashSet<>();
    for (char c : s.toCharArray()) {
      if (!set.add(c)) return false;
    }
    return true;
  }

  /**
   * Array is nice at storing certain numbers of data. Such as digits. 256 characters. 26 lower case
   * letters.
   * 
   * @param s
   * @return
   */
  public boolean hasAllUniqueChar1(String s) {
    int[] arr = new int[256];
    for (char c : s.toCharArray()) {
      if (arr[c] != 0) return false;
      arr[c]++;
    }
    return true;
  }

  /**
   * This is a combination of two in a collection.
   * 
   * @param s
   * @return
   */
  public boolean hasAllUniqueChar2(String s) {
    char[] charArr = s.toCharArray();
    for (int i = 0; i < charArr.length - 1; i++) {
      for (int j = 1; j < charArr.length; j++) {
        if (charArr[i] == charArr[j]) return false;
      }
    }
    return true;
  }

  /**
   * For this specific case, a boolean array is better. Boolean only requires 1 bit.
   * 
   * @param s
   * @return
   */
  public boolean hasAllUniqueChar3(String s) {
    boolean[] arr = new boolean[256];
    for (char c : s.toCharArray()) {
      if (arr[c]) return false;
      arr[c] = true;
    }
    return true;
  }

}
