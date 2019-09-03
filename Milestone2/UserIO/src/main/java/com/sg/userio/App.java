package com.sg.userio;

public class App 
{
    public static void main( String[] args )
    {    
        SimpleCalculator calculator = new SimpleCalculator();
        int mathOperationTobePerformed = calculator.getOperationToBePerformedFromUser();
        
        while (mathOperationTobePerformed!=0){
            float operand1 = calculator.getFirstOperandFromUser();
            float operand2 = calculator.getSecondOperandFromUser();
            calculator.displayOperationResult(mathOperationTobePerformed, operand1, operand2);
            System.out.println("===================================================================================\n");
            mathOperationTobePerformed = calculator.getOperationToBePerformedFromUser();
        }
    }
    
}
