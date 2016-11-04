package org.mo39.fmbh.common;

import org.mo39.fmbh.common.interfaces.HasPriority;

/**
 * A value that has a priority.
 * <p>
 * Field <code>value</code> is <b>immutable</b> and its priority can be changed.
 *
 * @author Jihan Chen
 *
 */
public class Value<T> implements Comparable<Value<T>>, HasPriority<Integer> {

  public final T value;
  public int priority;

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

  @Override
  public Integer getPriority() {
    return priority;
  }

  @Override
  public void setPriority(Integer priority) {
    this.priority = priority;
  }
}
