package com.sg.basicprogrammingconcepts;

public class SummativeSums {

    public static void main(String[] args) {

        int[][] arrayOfArrays = {
            {1, 90, -33, -55, 67, -16, 28, -55, 15},
            {999, -60, -77, 14, 160, 301},
            {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99}
        };

        for (int i = 0; i < arrayOfArrays.length; i++) {
            System.out.println("#" + (i + 1) + " Array Sum: " + calculateSumOfArrayElements(arrayOfArrays[i]));
        }
    }

    public static int calculateSumOfArrayElements(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
