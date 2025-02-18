package org.example.utils;

import java.nio.charset.StandardCharsets;

public abstract class StringUtils {

    public static String removeParentheses(String text) {
        return text.replaceAll("[()]","");
    }

    public static String toUTF8(String str) {
        return new String(str.getBytes(), StandardCharsets.UTF_8);
    }
}
