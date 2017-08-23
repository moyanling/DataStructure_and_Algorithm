package org.mo39.fmbh.common;

import static org.mo39.fmbh.common.Constants.IS_PROBLEM;
import static org.mo39.fmbh.common.Constants.PACKAGE_PREFIX;
import static org.mo39.fmbh.common.Constants.SRC_ROOT;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mo39.fmbh.common.annotation.ProblemSource;
import org.mo39.fmbh.common.annotation.ProblemSource.SourceValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;


public class Leetdoc {

  private static final Logger log = LoggerFactory.getLogger(Leetdoc.class);

  private static String getFullyQualifiedName(Path path) {
    String p = path.toString();
    String dir = p.substring(p.lastIndexOf(PACKAGE_PREFIX), p.length() - 5);
    return dir.replace(File.separatorChar, '.');
  }

  private static String getClassName(Path path) {
    String p = path.toString();
    return p.substring(p.lastIndexOf(File.separator) + 1, p.length() - 5);
  }

  private static String linkOf(String name) {
    char[] charArr = name.toCharArray();
    List<Character> charList = new ArrayList<>();
    for (int i = 0; i < charArr.length; i++) {
      if (i != 0 && Character.isUpperCase(charArr[i])
          && (charArr[i - 1] != 'I'
              || i < charArr.length - 1 && Character.isLowerCase(charArr[i + 1]))// ) {
          || !Character.isLetter(charArr[i])) {
        charList.add('-');
      }
      charList.add(Character.toLowerCase(charArr[i]));
    }
    return "https://leetcode.com/problems/" + Joiner.on("").join(charList) + "/";
  }

  private static String aliasOf(String name) {
    char[] charArr = name.toCharArray();
    List<Character> charList = new ArrayList<>();
    for (int i = 0; i < charArr.length; i++) {
      if (i != 0 && Character.isUpperCase(charArr[i])
          && (charArr[i - 1] != 'I'
              || i < charArr.length - 1 && Character.isLowerCase(charArr[i + 1]))
          || !Character.isLetter(charArr[i])) {
        charList.add(' ');
      }
      charList.add(charArr[i]);
    }
    return Joiner.on("").join(charList);
  }

  public static final Predicate<Path> IS_LEETCODE_PROBLEM = IS_PROBLEM.and(str -> {
    Class<?> klass;
    try {
      klass = Class.forName(getFullyQualifiedName(str));
    } catch (ClassNotFoundException e) {
      log.error("Class not found : " + e.getMessage());
      return false;
    }
    ProblemSource ps = klass.getAnnotation(ProblemSource.class);
    if (ps == null || ps.value().length != 1)
      return false;
    return Arrays.asList(ps.value()).contains(SourceValue.LEETCODE);
  });

  public static final Consumer<Path> UPDATE_COMMENTS = path -> {
    String name = getClassName(path);
    String link = linkOf(name);
    List<String> lines = readLines(path);
    if (lines == null)
      return;
    int index = -1;
    // First move down, find the line of class definition.
    while (!lines.get(++index).matches(".*(class|enum) " + name + ".* \\{")) {
    }
    // Already has a comment. Skipped.
    for (int i = 0; i < index; i++) {
      if (lines.get(i).contains("/**"))
        return;
    }
    // Then move up, skip annotations.
    while (lines.get(--index).trim().length() > 0) {
    }
    String description = fetchDescription(path);
    // Build comment
    CommentTemplate.Builder ctBuilder = new CommentTemplate.Builder();
    CommentTemplate ct = ctBuilder//
        .addElement(() -> " * <pre>")//
        .addElement(new LeetcodeDescription(description))//
        .addElement(() -> " * </pre>")//
        .addElement(new LeetcodeLink(link, aliasOf(name)))
        .addElement(new CommentTemplate.Author("Jihan Chen")).build();
    List<String> comments = ct.toComment();
    // Insert to file.
    for (int i = comments.size() - 1; i >= 0; i--) {
      lines.add(index + 1, comments.get(i));
    }
    try {
      com.google.common.io.Files.write(Joiner.on("\n").join(lines).getBytes(), path.toFile());
      log.info(name + " is updated");
    } catch (IOException e) {
      log.error("Error writing " + path.toString());
      return;
    }
  };

  public static String fetchDescription(Path path) {
    String name = getClassName(path);
    String link = linkOf(name);
    Document doc = null;
    try {
      doc = Jsoup.connect(link).get();
    } catch (Exception e) {
      log.error("Error getting html for " + link);
      return null;
    }
    // Get description from html document. Sometimes this would fetch an unrelated description.
    String description = doc.select("meta[name=description]").first().attr("content");
    if (description.contains("LeetCode Online Judge is a platform")) {
      log.error("Error description for " + link);
      return null;
    }
    return description;
  }

  public static List<String> readLines(Path path) {
    List<String> lines = null;
    try {
      lines = Files.readAllLines(path);
    } catch (IOException e) {
      log.error("Error reading " + path.toString() + ". The message is: " + e.getMessage());
      return null;
    }
    return lines;
  }

  public static class LeetcodeDescription implements CommentTemplate.CommentElement {

    private String description;

    public LeetcodeDescription(String description) {
      this.description = description;
    }

    public static final int LENGTH_BOUND = 75;

    private static Function<String, String> mapper = str -> {
      if (str.length() < LENGTH_BOUND)
        return " * " + str;
      char[] arr = str.toCharArray();
      StringBuilder sb = new StringBuilder(" * ");
      int i = -1, len = 0;
      while (++i < arr.length) {
        if (++len >= LENGTH_BOUND && Character.isSpaceChar(arr[i])) {
          len = 0;
          sb.append("\r\n * ");
        } else
          sb.append(arr[i]);
      }
      return sb.toString();
    };

    @Override
    public String format() {
      return this.description == null ? ""
          : Joiner.on("\r\n").join(Arrays.asList(description.split("\r\n")).stream()
              .filter(c -> !c.contains("Credits")).map(mapper).iterator());
    }

  }

  public static class LeetcodeLink implements CommentTemplate.CommentElement {

    private String link;
    private String name;

    public LeetcodeLink(String link, String name) {
      this.link = link;
      this.name = name;
    }

    @Override
    public String format() {
      return String.format(" * @see <a href=\"%s\">%s</a>", link, name);
    }

  }

  public static void main(String[] args) throws Exception {
    Files.walk(SRC_ROOT).filter(IS_LEETCODE_PROBLEM).forEach(UPDATE_COMMENTS);
  }

}
