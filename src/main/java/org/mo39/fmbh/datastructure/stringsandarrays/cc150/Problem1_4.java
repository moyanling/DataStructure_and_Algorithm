package org.mo39.fmbh.datastructure.stringsandarrays.cc150;

public class Problem1_4 {

  public static void main(String[] args) {
    System.err.println(new Problem1_4().replaceSpaces(" "));
  }

  public String replaceSpaces(String s) {
    if (s == null) return null;
    int count = 0;
    char[] charArr = s.toCharArray();
    for (int i = 0; i < charArr.length; i++) {
      if (charArr[i] == ' ') count++;
    }
    char[] newCharArr = new char[charArr.length + count * 2];
    int j = 0;
    for (int i = 0; i < charArr.length; i++) {
      if (charArr[i] == ' ') {
        newCharArr[j++] = '%';
        newCharArr[j++] = '2';
        newCharArr[j++] = '0';
      } else {
        newCharArr[j++] = charArr[i];
      }
    }
    return String.valueOf(newCharArr);
  }

}
