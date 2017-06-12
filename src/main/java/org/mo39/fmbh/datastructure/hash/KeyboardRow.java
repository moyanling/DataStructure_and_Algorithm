package org.mo39.fmbh.datastructure.hash;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Given a List of words, return the words that can be typed using letters of
 * alphabet on only one row's of American keyboard like the image below. 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 * 
 * 
 * 
 * Note:
 * 
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/keyboard-row/">Keyboard Row</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum KeyboardRow {

  SOLUTION {

    @Override
    public String[] solve(String[] words) {
      List<String> list = new ArrayList<>();
      for (String word : words) {
        char[] arr = word.toCharArray();
        Integer row = null;
        for (int i = 0; i < arr.length; i++) {
          int j = map.get(Character.toLowerCase(arr[i]));
          if (row == null) row = j;
          if (row != j) break;
          if (i == arr.length - 1) list.add(word);
        }
      }
      String[] result = new String[list.size()];
      for (int i = 0; i < result.length; i++) {
        result[i] = list.get(i);
      }
      return result;
    }

  },

  ONE_LINER {

    @Override
    public String[] solve(String[] words) {
      return Stream.of(words)
          .filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*"))
          .toArray(String[]::new);
    }

  };

  static Map<Character, Integer> map = new HashMap<>();

  static {
    map.put('q', 1);
    map.put('w', 1);
    map.put('e', 1);
    map.put('r', 1);
    map.put('t', 1);
    map.put('y', 1);
    map.put('u', 1);
    map.put('i', 1);
    map.put('o', 1);
    map.put('p', 1);
    map.put('a', 2);
    map.put('s', 2);
    map.put('d', 2);
    map.put('f', 2);
    map.put('g', 2);
    map.put('h', 2);
    map.put('j', 2);
    map.put('k', 2);
    map.put('l', 2);
    map.put('z', 3);
    map.put('x', 3);
    map.put('c', 3);
    map.put('v', 3);
    map.put('b', 3);
    map.put('n', 3);
    map.put('m', 3);
  }

  public abstract String[] solve(String[] words);

}
