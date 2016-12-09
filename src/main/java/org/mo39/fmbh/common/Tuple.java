package org.mo39.fmbh.common;

import java.util.Objects;


public class Tuple<T, K> {

  public final T a;
  public final K b;

  private Tuple(T i, K j) {
    this.a = i;
    this.b = j;
  }

  public static <T, K> Tuple<T, K> valueOf(T a, K b) {
    return new Tuple<T, K>(a, b);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj.getClass() != this.getClass()) return false;
    Tuple<?, ?> other = Tuple.class.cast(obj);
    if (Objects.equals(this.a, other.a) && Objects.equals(this.b, other.b)) return true;
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }

  @Override
  public String toString() {
    return String.format("<%s,%s>", String.valueOf(a), String.valueOf(b));
  }

}
