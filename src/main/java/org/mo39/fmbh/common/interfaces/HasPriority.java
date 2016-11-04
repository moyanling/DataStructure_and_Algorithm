package org.mo39.fmbh.common.interfaces;

public interface HasPriority<P extends Comparable<P>> {

  P getPriority();

  void setPriority(P priority);

}
