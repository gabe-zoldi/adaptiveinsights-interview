/*
 * *****************************************************************
 *  Copyright (c) 2017 Adaptive Insights, Inc.  All rights reserved.
 * *****************************************************************
 */
package com.adaptiveinsights.qa.eval.gabe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gabez on 1/23/17.
 */
public class Utils {

    /**
     * Given 2 integer values, returns the integer that is closest to 1000.
     *
     * For example, given two integers 100, 101, return 101.
     *
     * @param   value1  the first value
     * @param   value2  the second value
     * @return          the value closest 1000
     * @throws  EquidistantException
     *                  thrown if both distances are equal to 1000
     */
    public int closestTo1000(int value1, int value2) throws EquidistantException {

        int toValue = 1000;

        // distance to value
        int delta1 = Math.abs(toValue - value1);
        int delta2 = Math.abs(toValue - value2);

        // find shortest distance
        if (delta1 == delta2)
            throw new EquidistantException(
                String.format("Both values %s, %s are equidistant to %s", value1, value2, toValue)
            );
        else if (delta1 < delta2)
            return value1;
        else
            return value2;
    }

    /**
     * Given a string, returns a string where every character in the original is doubled.
     *
     * For example, given the string "xyz", return the string "xxyyzz".
     *
     * @param   word    the string
     * @return          the string with characters doubled
     */
    public String doubleChar(String word) {

        // chech NPE
        if (word == null)
            return null;

        StringBuilder doubled = new StringBuilder();

        // iterate thru each char
        for (char c : word.toCharArray())
            // append character twice
            for (int i = 1; i <= 2; i++)
                doubled.append(c);

        return doubled.toString();
    }

    /**
     * Given an array of integers, return true if there is a way to split the array in two so that
     * the sum of the numbers on one side of the split equals the sum of the numbers on the other side.
     *
     * For example, given the integer array {1, 2, 3}, return true,
     *           or given the integer array {0, 1, 2}, return false.
     *
     * @param   array   the integer array
     * @return          whether array can be split with equal sums
     */
    public boolean isSplitable(int[] array) {

        // convert int[] to Integer[]
        List<Integer> list = Arrays.stream( array ).boxed().collect( Collectors.toList() );
        int end = list.size();
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        /*
         * check for all possible split combinations
         * e.g., if input [1, 2, 3, 4]   checking...[left]   [right]
         * ==> [1]   [2, 3, 4]
         * ==> [1, 2]   [3, 4]
         * ==> [1, 2, 3]   [4]
         */
        for (int splitIndex = 1; splitIndex < end; splitIndex++) {
            // create left/right split list
            List<Integer> left  = list.subList(0, splitIndex);
            List<Integer> right = list.subList(splitIndex, end);

            // sum left/right list
            int leftSum = left.stream().mapToInt(Integer::intValue).sum();
            int rightSum = right.stream().mapToInt(Integer::intValue).sum();

            log(left + "   " + right + " ==> " + leftSum + "  " + rightSum);

            // check if left/right sums are equal
            // TODO: [caveat] returns true on first one it finds
            // TODO: we may want to refactor to return all splitable patterns
            if (leftSum == rightSum)
                return true;
        }

        return false;
    }

    /**
     * Given a string, return the string with an asterisk inserted between every character using recursion.
     *
     * For example, given the string "test", return "t*e*s*t".
     *
     * @param   word    the string
     * @return          the original string with asterisks inserted
     */
    public String asterisk(String word) {
        /*
         * asterisk("test")
         * "t*" + (asterisk("est"))
         * "t*" + ("e*" + (asterisk("st")))
         * "t*" + ("e*" + ("s*" + (asterisk("t"))))
         * "t*" + ("e*" + ("s*" + ("t")))
         * "t*e*s*t"
         *
         */
        if ( (word == null) || (word.length() <= 1) )
            return word;

        return word.charAt(0) + "*" + asterisk( word.substring(1) );
    }



    public static void main(String[] args) throws EquidistantException
    {
        // Problem 1
        log("closestTo1000()  10, 1", new Utils().closestTo1000(10, 1) );

        // Problem 2
        String input2 = "xyz";
        log( "doubleChar()  " + input2, new Utils().doubleChar(input2) );

        // Problem 3
        int[] input3 = new int[] { 1, 2, 3, 4 };
        log( "isSplitable()  " + Arrays.toString(input3), new Utils().isSplitable(input3) );

        // Problem 4
        String input4 = "test";
        log( "asterisk()  " + input4, new Utils().asterisk(input4) );
    }


    private static void log(String input, Object result) {
        System.out.println(
                String.format("><)))^>  %s ==> %s", input, result)
        );
    }

    private static void log(String message) {
        log(null, message);
    }

}