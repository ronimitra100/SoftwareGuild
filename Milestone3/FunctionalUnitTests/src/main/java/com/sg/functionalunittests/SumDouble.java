package com.sg.functionalunittests;

public class SumDouble {
    // Given two int values, return their sum. However, if the two 
    // values are the same, then return double their sum. 
    //
    // sumDouble(1, 2) -> 3
    // sumDouble(3, 2) -> 5
    // sumDouble(2, 2) -> 8

    public int sumDouble(int a, int b) {
        int result = 0;

        if (a == b) {
            result = 2 * (a+b);
        } else {
            result = a + b;
        }

        return result;
    }
}
