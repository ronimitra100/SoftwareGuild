package com.sg.objectinstantiation;

public class App {
    public static void main(String[] args){
        //Adder myAdder = new Adder();
        double myPI = Adder.PI;
        int sum = Adder.add(5,4);
        System.out.println("The sum is " + sum);
    }
}
