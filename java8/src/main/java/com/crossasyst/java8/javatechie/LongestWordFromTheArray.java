package com.crossasyst.java8.javatechie;

import java.util.Arrays;

public class LongestWordFromTheArray {

    public static void main(String[] args) {

        String[] words = {"java", "python", "spring", "iam the longest word"};

        String longWord = Arrays.stream(words)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2)
                .get();
        System.out.println("Longest word " + longWord);

    }
}
