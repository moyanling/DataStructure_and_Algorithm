package org.mo39.fmbh.common;

import java.util.ArrayList;
import java.util.List;

public class CommentTemplate {

  private List<CommentElement> elements = new ArrayList<>();

  public static class Builder {

    private CommentTemplate ct = new CommentTemplate();

    public Builder addElement(CommentElement e) {
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
    elements.stream().forEach(e -> toRet.add(e.format()));
    toRet.add(" */");
    return toRet;
  }

  public static interface CommentElement {

    public abstract String format();

  }

  public static class Author implements CommentElement {

    private String name;

    public Author(String name) {
      this.name = name;
    }

    @Override
    public String format() {
      return " * @author " + name;
    }

  }

}
