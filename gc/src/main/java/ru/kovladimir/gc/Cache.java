package ru.kovladimir.gc;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Cache
 */
public class Cache {

    /**
     * Map.
     */
    private Map<String, SoftReference<String>> map = new HashMap<>();

    /**
     * Get content of file by filename.
     * @param filename String.
     * @return content.
     */
    public String getContent(String filename) {
        SoftReference<String> content = map.get(filename);
        if (content == null || content.get() == null) {
            content = new SoftReference<>(readFile(filename));
            map.put(filename, content);
        }
        return content.get();
    }

    /**
     * Read all lines in file.
     * @param filename String.
     * @return content.
     */
    private String readFile(String filename) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(Paths.get(filename))) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
