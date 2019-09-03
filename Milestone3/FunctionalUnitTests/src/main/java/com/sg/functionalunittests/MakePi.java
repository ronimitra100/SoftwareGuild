package com.sg.functionalunittests;

public class MakePi {
    // Return an int array length n containing the first n digits of pi.
    //
    // makePi(3) -> {3, 1, 4}

    public int[] makePi(int n) {
        int[] result = new int[n];
        double temp = Math.PI;
        
        for (int i=0; i<n; i++){
            result[i]= ((int)(temp)) % 10;
            temp *= 10;
        }
        
        return result;
    }
    
 
    
}
