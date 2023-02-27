package com.dq.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {

    private static final List<String> REPLACE_STR = new ArrayList<>();

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(?<![0-9])(?:(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2}))(?![0-9])");

        Matcher matcher = pattern.matcher("256.0.0.0");
        System.out.println(matcher.matches());
        REPLACE_STR.addAll(Arrays.asList("/, ,$".trim().split(",")));
        REPLACE_STR.add(",");
        for (String s : REPLACE_STR) {
            System.out.println("s :[" + s + "]");
        }
    }
}
