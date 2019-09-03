package com.sg.shapes;

public class App {

    public static void main(String[] args) {
        Square square = new Square(5);
        square.displayAreaAndPerimeterCalculations();

        Rectangle rectangle = new Rectangle(12.5, 20.98);
        rectangle.displayAreaAndPerimeterCalculations();

        Circle circle = new Circle(10);
        circle.displayAreaAndPerimeterCalculations();

        Triangle traingle = new Triangle(5, 12, 13);
        traingle.displayAreaAndPerimeterCalculations();
    }
}
