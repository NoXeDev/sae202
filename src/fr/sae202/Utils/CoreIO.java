package fr.sae202.Utils;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoreIO {
    // Source : https://www.baeldung.com/java-list-directory-files
    public static Set<String> listFiles(String dir) {
        return Stream.of(new File(dir).listFiles())
          .filter(file -> !file.isDirectory())
          .map(File::getName)
          .collect(Collectors.toSet());
    }
}
