package org.mo39.fmbh.common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class Constants {

  public static final Path ROOT = Paths.get(System.getProperty("user.dir"));
  public static final Path SRC_ROOT = ROOT.resolve("src");
  public static final Path README = Paths.get(ROOT.toString(), "README.md");
  public static final String PACKAGE_PREFIX = "org\\mo39\\fmbh\\";
  public static final String GIT_LINK =
      "<a href=\"https://github.com/chenjihan/DataStructure_and_Algorithm/blob/master/src/main/java/org/mo39/fmbh/%s.java\">%s</a>";
  
  public static final Predicate<Path> IS_PROBLEM =
      //@formatter:off
        p ->
          Files.isRegularFile(p)
          && p.toString().matches(".*(datastructure|algorithm).*")
          && p.getFileName().toString().contains(new String("java"))
          && !p.getFileName().toString().contains(new String("Problem"))
          && !p.getFileName().toString().contains(new String("package-info")
        );
      //@formatter:on

}
