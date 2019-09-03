package com.sg.shapes;

public class Square extends Shape {

    double sideLength;

    public Square(double sideLength) {
        super();
        this.sideLength = sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    public void displayAreaAndPerimeterCalculations() {
        System.out.println("The area and perimeter of a square with " + sideLength + " as length of each side are equal to " + getArea() + " and " + getPerimeter() + " respectively.");
    }
}
