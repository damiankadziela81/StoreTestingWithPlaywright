package org.example.utils;

public abstract class StringUtils {

    public static String removeParentheses(String text) {
        return text.replaceAll("[()]","");
    }
}
