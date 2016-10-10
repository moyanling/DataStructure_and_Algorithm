package org.mo39.fmbh.common;

/**
 * A value that has a priority.
 * <p>
 * This class is <b>immutable</b> and every instance should be treated differently.
 * 
 * @author Jihan Chen
 *
 */
public class Value<T> implements Comparable<Value<T>> {

  public final T value;
  public final int priority;

  public Value(T value, int priority) {
    this.value = value;
    this.priority = priority;
  }

  @Override
  public int compareTo(Value<T> o) {
    return this.priority - o.priority;
  }

  @Override
  public String toString() {
    return String.format("<%d, %d>", value, priority);
  }
}
