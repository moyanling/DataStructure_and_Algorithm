package org.mo39.fmbh.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the source of the problem
 * 
 * @author Jihan Chen
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProblemSource {

  /**
   * The problem source value.
   * 
   * @return
   */
  SourceValue value();

  /**
   * Enum of the problem source.
   * 
   * @author Jihan Chen
   *
   */
  public static enum SourceValue {

    /**
     * <i>Introduction to Algorithm<br>
     * Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein<br>
     * Third Edition<br>
     * </i>
     * 
     */
    INTRODUCTION_TO_ALGORITHM,

    /**
     * <i>Cracking the Coding Interview<br>
     * Gayle Laakmann McDowell<br>
     * Fifth Eidtion<br>
     * </i>
     * 
     */
    CRACKING_THE_CODING_INTERVIEW,

    /**
     * LeetCode online judge.
     * 
     * @see <a href="https://leetcode.com/">LeetCode online judge</a>
     */
    LEETCODE;
  }

}
