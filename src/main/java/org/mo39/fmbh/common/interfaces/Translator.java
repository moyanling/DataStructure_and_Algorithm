package org.mo39.fmbh.common.interfaces;

/**
 * 
 * This is a functional interface that translates an argument of the first generic type T to the
 * second generic type K, where T and K can be the same type.
 *
 * @author Jihan Chen
 *
 * @param <T> the generic type of the input argument
 * @param <K> the generic type of the return
 */
@FunctionalInterface
public interface Translator<T, K> {

  /**
   * @see Translator
   * @param t of generic type T.
   * @return an object of generic type K.
   */
  K translate(T t);

}
