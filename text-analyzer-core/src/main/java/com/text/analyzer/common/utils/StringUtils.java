package com.text.analyzer.common.utils;

import lombok.experimental.UtilityClass;

import static java.util.Objects.isNull;

@UtilityClass
public class StringUtils {

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String WHITESPACE_REGEX = "\\s";

    public static boolean isEmpty(String s) {
        return isNull(s) || s.isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
