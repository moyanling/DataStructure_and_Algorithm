package org.mo39.fmbh.datastructure.design;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.ArrayList;
import java.util.List;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over
 * the network and is decoded back to the original list of strings.
 * 
 * Machine 1 (sender) has the function:
 * 
 * string encode(vector&lt;string&gt; strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * 
 * Machine 2 (receiver) has the function:
 * 
 * vector&lt;string&gt; decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * 
 * 
 * 
 * So Machine 1 does:
 * string encoded_string = encode(strs);
 * 
 * 
 * 
 * and Machine 2 does:
 * vector&lt;string&gt; strs2 = decode(encoded_string);
 * 
 * 
 * 
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * 
 * 
 * Implement the encode and decode methods.
 * 
 * 
 * Note:
 * 
 * 
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm
 * should be generalized enough to work on any possible characters. Do not use class
 * member/global/static variables to store states. Your encode and decode algorithms should be
 * stateless. Do not rely on any library method such as eval or serialize methods. You should
 * implement your own encode/decode algorithm.
 * 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/encode-and-decode-strings/">Encode And Decode
 *      Strings</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public enum EncodeAndDecodeStrings {

  SOLUTION {

    @Override
    public String encode(List<String> strs) {
      StringBuilder sb = new StringBuilder();
      for (String s : strs) {
        sb.append(s.length()).append('#').append(s);
      }
      return sb.toString();
    }

    @Override
    public List<String> decode(String s) {
      List<String> ret = new ArrayList<String>();
      for (int i = 0, start, len; i < s.length(); i = start + len + 1) {
        start = s.indexOf('#', i);
        len = Integer.valueOf(s.substring(i, start));
        ret.add(s.substring(start + 1, start + len + 1));
      }
      return ret;
    }

  };

  public abstract String encode(List<String> strs);

  public abstract List<String> decode(String s);

}
