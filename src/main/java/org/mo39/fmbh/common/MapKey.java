package org.mo39.fmbh.common;

import java.util.Objects;


public class MapKey {

  public final int i;
  public final int j;

  private MapKey(int i, int j) {
    this.i = i;
    this.j = j;
  }

  public static MapKey valueOf(int i, int j) {
    return new MapKey(i, j);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj.getClass() != this.getClass()) return false;
    MapKey other = MapKey.class.cast(obj);
    if (this.i == other.i && this.j == other.j) return true;
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(i, j);
  }
}
