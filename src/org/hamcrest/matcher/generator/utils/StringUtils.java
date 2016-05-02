package org.hamcrest.matcher.generator.utils;

public class StringUtils {
    public static boolean isBlank (String value) {
        return (value == null) || value.trim().equals("");
    }
}
