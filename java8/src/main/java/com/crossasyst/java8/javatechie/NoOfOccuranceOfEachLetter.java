package com.crossasyst.java8.javatechie;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NoOfOccuranceOfEachLetter {

    public static void main(String[] args) {
        String input = "RakeshChavan";
        Map<String, Long> collect = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
    }

}
