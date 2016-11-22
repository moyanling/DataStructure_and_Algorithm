package org.mo39.fmbh.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.base.Joiner;

public class ToC {

  private static final Path ROOT = Paths.get(System.getProperty("user.dir"));
  private static final Path README = Paths.get(ROOT.toString(), "README.md");
  private static final String PACKAGE_PREFIX = "org\\mo39\\fmbh\\";
  private static final String HREF_FORMAT =
      "<a href=\"https://github.com/chenjihan/DataStructure_and_Algorithm/blob/master/src/main/java/org/mo39/fmbh/%s.java\">%s</a>";

  private static final Function<Path, String[]> MAPPER = p -> {
    String str = p.toString();
    int index = str.lastIndexOf(PACKAGE_PREFIX);
    return str.substring(index + PACKAGE_PREFIX.length(), str.length() - 5).split("\\\\");
  };

  private static final Predicate<Path> PREDICATE =
      //@formatter:off
        p ->
          Files.isRegularFile(p)
          && p.toString().matches(".*(datastructure|algorithm).*")
          && p.getFileName().toString().contains(new String("java"))
          && !p.getFileName().toString().contains(new String("Problem"))
          && !p.getFileName().toString().contains(new String("package-info")
        );
      //@formatter:on

  /**
   * Get a list of problems. Each problem is presented as a String array.
   * 
   * @return
   */
  public static List<String[]> getAllProblems() {
    try (Stream<Path> paths = Files.walk(ROOT)) {
      return paths.filter(PREDICATE).map(MAPPER).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  /**
   * Print table of content to the screen using a specific format.
   *
   */
  private static String format(List<String[]> listOfProblems, Function<String, String> translator) {
    StringBuilder sb = new StringBuilder();
    Map<String, List<String[]>> dict =
        listOfProblems.stream().collect(Collectors.groupingBy(s -> s[0]));
    for (String key : dict.keySet()) {
      if (key.equals("algorithm")) sb.append("Algorithms:\n");
      else sb.append("Data Structures:\n");
      List<String[]> list = dict.get(key);
      Map<String, List<String[]>> group = list.stream().collect(Collectors.groupingBy(s -> s[1]));
      for (String category : group.keySet()) {
        sb.append("\t" + category + "\n");
        for (String[] arr : group.get(category)) {
          sb.append(
              "\t" + "\t" + "- "
                  + (translator == null
                      ? String.format(HREF_FORMAT, Joiner.on('/').join(arr), arr[2])
                      : translator.apply(arr[2]))
                  + "\n");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }


  public static void printHtml() {
    Z.print(format(getAllProblems(), null));
  }

  public static void print() {
    Z.print(format(getAllProblems(), s -> s + ".java"));
  }

  public static void updateReadMe() throws IOException {
    List<String> lines = Files.readAllLines(README);
    int start = lines.indexOf("<div class='tableofcontent'>") + 1;
    int end = 0;
    for (int i = start; i < lines.size(); i++) {
      if (lines.get(i).equals("</div>")) end = i - 1;
    }
    final int finalEnd = end;
    List<String> newLines =
        IntStream.range(0, lines.size()).filter(i -> i <= start || i >= finalEnd)
            .mapToObj(i -> lines.get(i)).collect(Collectors.toList());
    String[] newToC = format(getAllProblems(), null).split("\n");
    for (int i = newToC.length - 1; i >= 0; i--) {
      newLines.add(start + 1, newToC[i]);
    }
    com.google.common.io.Files.write(Joiner.on('\n').join(newLines).getBytes(), README.toFile());
  }

  public static void main(String[] args) throws IOException {
    ToC.updateReadMe();
  }

}
