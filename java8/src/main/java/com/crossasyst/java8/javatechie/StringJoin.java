package com.crossasyst.java8.javatechie;

import java.util.Arrays;
import java.util.List;

public class StringJoin {

    public static void main(String[] args) {
        List<String> nos = Arrays.asList("1","2","3");

        String join = String.join("-", nos);
        System.out.println(join);
    }
}
