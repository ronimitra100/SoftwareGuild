package com.sg.functionalunittests;

public class Diff21 {
    // Given an int n, return the absolute value of the difference 
    // between n and 21, except return double the absolute value 
    // of the difference if n is over 21. 
    //
    // diff21(23) -> 4
    // diff21(10) -> 11
    // diff21(21) -> 0
    public int diff21(int n) {
        if (n>21){
            return 2*(n-21);
        }
        else{
            return (21-n);
        }
    }

}
