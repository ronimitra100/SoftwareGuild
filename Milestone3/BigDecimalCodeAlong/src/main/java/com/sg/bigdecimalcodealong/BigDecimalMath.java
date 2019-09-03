package com.sg.bigdecimalcodealong;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalMath {
    public BigDecimal calculate(MathOperator operator, BigDecimal operator1,BigDecimal operator2 ){
        switch(operator){
            case PLUS:
                return operator1.add(operator2);
            case MINUS:
                return operator1.subtract(operator2);
            case MULTIPLY:
                return operator1.multiply(operator2);
            case DIVIDE:
                return operator1.divide(operator2, 2, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedOperationException("Unknown Math operator");
        }
    }
}
