package org.example.utils;

public class StringUtils {

    public static String removeParentheses(String text) {
        return text.replaceAll("[()]","");
    }
}
