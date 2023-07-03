package com.crossasyst.java8.javatechie;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondHighestNumberFromList {

    public static void main(String[] args) {
        int[] number = {1, 2, 22, 11, 3, 5, 6};

        Integer secondHighest = Arrays.stream(number) // Convert int array to IntStream
                .boxed() // Convert IntStream to Stream<Integer>
                .sorted(Comparator.reverseOrder()) // Sort the numbers in descending order
                .skip(1) // Skip the first highest number
                .findFirst() // Get the first element from the remaining stream, which is the second highest number
                .get(); // Retrieve the second highest number as an Integer

        System.out.println("Second highest number: " + secondHighest);

        Integer integer1 = Arrays.stream(number)
                .boxed()
                .sorted()
                .skip(1)
                .findFirst()
                .get();
        System.out.println("Second lowest number " + integer1);
    }
}