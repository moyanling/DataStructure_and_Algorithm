package org.mo39.fmbh.common;

import java.util.Objects;


public class Tuple<T> {

  public final T a;
  public final T b;

  private Tuple(T i, T j) {
    this.a = i;
    this.b = j;
  }

  public static <T> Tuple<T> valueOf(T i, T j) {
    return new Tuple<T>(i, j);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj.getClass() != this.getClass()) return false;
    Tuple<?> other = Tuple.class.cast(obj);
    if (Objects.equals(this.a, other.a) && Objects.equals(this.b, other.b)) return true;
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }
}
