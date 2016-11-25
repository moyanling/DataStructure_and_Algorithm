package org.mo39.fmbh.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentTemplate {

  private List<Element> elements = new ArrayList<>();

  public static class Builder {

    private CommentTemplate ct = new CommentTemplate();

    public Builder addElement(Element e) {
      ct.elements.add(e);
      return this;
    }

    public CommentTemplate build() {
      return ct;
    }

  }

  public List<String> toComment() {
    List<String> toRet = new ArrayList<>();
    toRet.add("/**");
    Collections.sort(elements);
    elements.stream().forEach(e -> toRet.add(" * " + e.format()));
    toRet.add(" */");
    return toRet;
  }

  public static abstract class Element implements Comparable<Element> {

    /**
     * The sequence for this element in the comments.
     */
    public final int sequence;

    public Element(int sequence) {
      this.sequence = sequence;
    }

    @Override
    public int compareTo(Element o) {
      return this.sequence - o.sequence;
    }

    @Override
    public String toString() {
      return format();
    }

    public abstract String format();

  }

  public static class Author extends Element {

    private String name;

    public Author(String name, int sequence) {
      super(sequence);
      this.name = name;
    }

    @Override
    public String format() {
      return "@author " + name;
    }

  }

}
