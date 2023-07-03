package com.crossasyst.java8.javatechie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberStartWith1 {

    public static void main(String[] args) {
        int [] number = {1,2,3,12,11,31};

        List<String> startWith1 = Arrays.stream(number)
                .boxed()
                .map(s -> s + "")
                .filter(s -> s.startsWith("1"))
                .collect(Collectors.toList());

        System.out.println(startWith1);
    }

}
