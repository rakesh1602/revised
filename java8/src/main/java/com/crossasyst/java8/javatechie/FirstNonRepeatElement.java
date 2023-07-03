package com.crossasyst.java8.javatechie;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatElement {

    public static void main(String[] args) {
        String input = "ilovejavatechie";

        //NOTE : By default Array.stream take the Hashmap which don't maintain the insertion order hence we
        //are getting "c" as first note repeat element.
        //hence we use linked hashmap to maintain the insertion order now output is l.
        String key = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .findFirst()
                .get().getKey();

        System.out.println(key);
    }
}
