package com.sg.arrays;

public class FruitSalad {
    public static void main(String[] args){
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", 
            "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", 
            "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  
            "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", 
            "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", 
            "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        
        String fruitSalad[];
        int numOfBerries = numOfElementsContaining(fruit, "berry");
        int numOfApples = numOfElementsContaining(fruit, "Apple");
        int numOfOranges = numOfElementsContaining(fruit, "Orange");
        int numOfTomatoes = numOfElementsContaining(fruit, "Tomato");
        int numOfNonSpecialFruits = fruit.length - numOfBerries - numOfApples - numOfOranges - numOfTomatoes;
        
        System.out.println("Number of berries: " + numOfBerries);
        System.out.println("Number of apples: " + numOfApples);
        System.out.println("Number of oranges: " + numOfOranges);
        System.out.println("Number of tomatoes: " + numOfTomatoes);
        System.out.println("Number of fruits different from berries, apples, oranges or tomatoes : " + numOfNonSpecialFruits);
        
        String[] listOfBerries = listOfElementsContaining(fruit, "berry");
        String[] listOfApples = listOfElementsContaining(fruit, "Apple");
        String[] listOfOranges = listOfElementsContaining(fruit, "Orange");
        String[] listOfTomatoes = listOfElementsContaining(fruit, "Tomato");
        String[] listOfNonSpecialFruits = listOfElementsContaining(fruit, "berry");
    }
    
    public static int numOfElementsContaining(String[] arr, String str){
        int result = 0;
        for (int i=0; i<arr.length;i++ ){
            if (arr[i].contains(str)){
                result++;
            }
        }
        return result;
    }
    
    public static String[] listOfElementsContaining(String[] arr, String str){
        int listLength = numOfElementsContaining(arr, str);
        String[] result = new String[listLength];
        int resultCurrentIndex = 0;
        for (int i=0; i<arr.length; i++){
            if (arr[i].contains(str)){
                result[resultCurrentIndex] = arr[i];
                resultCurrentIndex++;
            }
        }
        return result;
    }
    
    /*public static String[] arrayResidue (String[] originalArr, String[] undesiredSubstrings){
        String[] temp = new String[originalArr.length];
        int countOfNullsInTemp = 0;
        for (int i=0; i<originalArr.length; i++){
            for (int j=0; j<undesiredSubstrings.length; j++){
                if (originalArr[i].contains(undesiredSubstrings[j])){
                    temp[i] = null;
                }
                else{
                    temp[i] = originalArr[i];
                }
            }
        }
        
        for (int i=0; i< temp.length; i++){
            if (temp[i]== null){
                countOfNullsInTemp++;
            }
        }
        
        String[] result = new String[originalArr.length - countOfNullsInTemp];
        
        for (int i=0; i<result.length; i++){
            
        }
        return result;
    }*/
    
    
    
}
