package org.mo39.fmbh.common;

public class Interval {
  public int start;
  public int end;

  public Interval() {
    start = 0;
    end = 0;
  }

  public Interval(int s, int e) {
    start = s;
    end = e;
  }

  @Override
  public String toString() {
    return String.format("<%d,%d>", start, end);
  }
}
