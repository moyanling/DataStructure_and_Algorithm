package org.mo39.fmbh.datastructure.queue;

public interface HasPriority<P extends Comparable<P>> {

  P getPriority();

  void setPriority(P priority);

}
