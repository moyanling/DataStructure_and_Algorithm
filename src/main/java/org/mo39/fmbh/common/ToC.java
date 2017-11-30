package org.mo39.fmbh.common;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static com.google.common.base.Joiner.on;
import static java.lang.String.format;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.mo39.fmbh.common.Constants.*;

public class ToC {

  private static final Function<Path, String[]> TO_STR_ARR =
      p -> {
        String str = p.toString();
        int index = str.lastIndexOf(PACKAGE_PREFIX);
        return str.substring(index + PACKAGE_PREFIX.length(), str.length() - 5)
            .split(File.separator);
      };

  private static final Function<String[], String> SIMPLE_NAME_STYLE =
      strArr -> strArr[strArr.length - 1];
  private static final Function<String[], String> HREF_LINK_STYLE =
      a -> format(GIT_LINK, on('/').join(a), a[a.length - 1]);

  /** Get table of content as a String. */
  private static String getToC(Function<String[], String> func) throws IOException {
    StringBuilder sb = new StringBuilder();
    Map<String, List<String[]>> dict =
        Files.walk(SRC_ROOT).filter(IS_PROBLEM).map(TO_STR_ARR).collect(groupingBy(s -> s[0]));
    for (String key : dict.keySet()) {
      switch (key) {
        case "algorithm":
          sb.append("Algorithms:\n");
          break;
        case "datastructure":
          sb.append("Data Structures:\n");
          break;
        case "uncategorized":
          sb.append("Uncategorized:\n");
          break;
        default:
          throw new RuntimeException("Package " + key + " not found");
      }
      List<String[]> list = dict.get(key);
      Map<String, List<String[]>> group = list.stream().collect(groupingBy(s -> s[1]));
      for (String category : group.keySet()) {
        sb.append("\t").append(category).append("\n");
        for (String[] arr : group.get(category)) {
          sb.append("\t\t- ");
          sb.append(func.apply(arr));
          sb.append("\n");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
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
        IntStream.range(0, lines.size())
            .filter(i -> i <= start || i >= finalEnd)
            .mapToObj(lines::get)
            .collect(toList());
    String[] newToC = getToC(HREF_LINK_STYLE).split("\n");
    for (int i = newToC.length - 1; i >= 0; i--) {
      newLines.add(start + 1, newToC[i]);
    }
    com.google.common.io.Files.write(Joiner.on('\n').join(newLines).getBytes(), README.toFile());
  }

  public static void main(String[] args) throws IOException {
    Z.print(getToC(SIMPLE_NAME_STYLE));
    ToC.updateReadMe();
  }
}
