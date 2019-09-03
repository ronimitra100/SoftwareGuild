package com.sg.arrays;

public class FruitBasket {
    public static void main(String[] args){
        String[] fruits = {"Orange", "Apple", "Orange", "Apple", "Orange", 
            "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", 
            "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", 
            "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", 
            "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", 
            "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", 
            "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", 
            "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", 
            "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        
        int totalNumOfFruits =0;
        int totalNumOfApples =0;
        int totalNumOfOranges=0;
        
        for (int i=0; i<fruits.length; i++){
            totalNumOfFruits++;
            if (fruits[i]=="Apple"){
                totalNumOfApples++;
            }
            if (fruits[i]=="Orange"){
                totalNumOfOranges++;
            }           
        }
        System.out.println("Total# of Fruit in Basket: " + totalNumOfFruits);
        System.out.println("Number of Apples: " + totalNumOfApples);
        System.out.println("Number of Oranges: " + totalNumOfOranges);
    }
    
}
