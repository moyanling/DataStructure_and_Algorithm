package org.mo39.fmbh.datastructure.stringsandarrays.cc150;


public class Problem1_8 {

  public static void main(String[] args) {
    System.out.println(Problem1_8.isSubstring("12345", "51234"));
    System.out.println(new Problem1_8().isRotation("12345", "51234"));
  }

  public static boolean isSubstring(String s, String t) {
    return s.contains(t.subSequence(0, t.length()));
  }

  /**
   * This method is really interesting.
   * 
   * @param s
   * @param t
   * @return
   */
  public boolean isRotation(String s, String t) {
    if (s == null && t == null) return true;
    if (s == null || t == null) return false;
    if (s.length() != t.length()) return false;
    return isSubstring(s + s, t);
  }

}
