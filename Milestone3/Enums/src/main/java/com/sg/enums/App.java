package com.sg.enums;

public class App 
{
    public static void main( String[] args )
    {
        IntMath intMath = new IntMath();
        int sum = intMath.calculate(MathOperator.PLUS, 7, 3);
        int difference = intMath.calculate(MathOperator.MINUS, 7, 3);
        int product = intMath.calculate(MathOperator.MULTIPLY, 7, 3);
        int dividend = intMath.calculate(MathOperator.DIVIDE, 7, 3);
        
        System.out.println("Sum : " + sum);
        System.out.println("difference : " + difference);
        System.out.println("product : " + product);
        System.out.println("dividend : " + dividend);
    }
}
