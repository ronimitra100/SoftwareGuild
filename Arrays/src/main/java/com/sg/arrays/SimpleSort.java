package com.sg.arrays;

public class SimpleSort {
    public static void main(String[] args){
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};
        
        int[] wholeNumbers = new int[24];
        
        for (int i=0; i < 12; i++){
            wholeNumbers[i]=firstHalf[i];
        }
         for (int i=12; i < 24; i++){
            wholeNumbers[i]=secondHalf[i-12];
        }
         
         System.out.println("wholeNumbers after combining: ");
         for (int i=0; i<24; i++){
             System.out.print(wholeNumbers[i] + " ");
         }
         
         System.out.println("");
         
         for (int i=0; i<24; i++){
             for (int j=i+1; j<24; j++){
                 if (wholeNumbers[i] > wholeNumbers[j]){
                     int temp;
                     temp=wholeNumbers[i];
                     wholeNumbers[i] = wholeNumbers[j];
                     wholeNumbers[j]=temp;
                 }
             }
         }
         System.out.println("wholeNumbers after sorting: ");
         for (int i=0; i<24; i++){
             System.out.print(wholeNumbers[i] + " ");
         }
    }
}
