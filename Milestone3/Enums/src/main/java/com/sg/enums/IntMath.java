package com.sg.enums;

public class IntMath {
    public int calculate(MathOperator operator, int operator1,int operator2 ){
        switch(operator){
            case PLUS:
                return operator1 + operator2;
            case MINUS:
                return operator1 - operator2;
            case MULTIPLY:
                return operator1 * operator2;
            case DIVIDE:
                return operator1 / operator2;
            default:
                throw new UnsupportedOperationException();
                
        }
    }
}
