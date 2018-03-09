package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    private final static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    private final static String regex = "[0-9]+";
    public static void main(String[] args) {

        System.out.println("123".matches(regex));
    }
}
