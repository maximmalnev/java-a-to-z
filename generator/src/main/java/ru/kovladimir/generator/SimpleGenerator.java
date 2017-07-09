package ru.kovladimir.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple implementation of Generator.
 */
public class SimpleGenerator implements Generator {

    /**
     * Pattern.
     */
    private Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");

    /**
     * Generate string where all keys were replaces by values.
     * @param template String.
     * @param data Object[].
     * @return String.
     * @throws ParamNotFoundException
     */
    @Override
    public String generate(String template, Pair[] data) throws ParamNotFoundException {
        if (template == null)
            return null;
        if (data == null) {
            return template;
        }
        Matcher matcher = pattern.matcher(template);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = getValue(key, data);
            matcher.appendReplacement(buffer, value);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * Finder key in the array and return appropriate value.
     * @param key String.
     * @param data Pair[].
     * @return String.
     * @throws ParamNotFoundException
     */
    private String getValue(String key, Pair[] data) throws ParamNotFoundException {
        for (Pair pair : data) {
            if (pair != null && key.equals(pair.getKey())) {
                String value = pair.getValue();
                if (value != null) {
                    return value;
                }
            }
        }
        throw new ParamNotFoundException();
    }
}
