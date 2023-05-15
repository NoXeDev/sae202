package fr.sae202.Utils;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoreIO {
    /**
     * List all files in a directory
     * @param dir the directory
     * @return all files in a directory
     */
    public static Set<String> listFiles(String dir) {
        // Source : https://www.baeldung.com/java-list-directory-files
        return Stream.of(new File(dir).listFiles())
          .filter(file -> !file.isDirectory())
          .map(File::getName)
          .collect(Collectors.toSet());
    }
}
