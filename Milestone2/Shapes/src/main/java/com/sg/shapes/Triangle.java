package com.sg.shapes;

public class Triangle extends Shape {

    double side1;
    double side2;
    double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double getPerimeter() {
        return (side1 + side2 + side3);
    }

    @Override
    public double getArea() {
        double p = (getPerimeter()) / 2;
        double area = Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
        return area;
    }

    public void displayAreaAndPerimeterCalculations() {
        System.out.println("The area and perimeter of a triangle with sides of length " + side1 + ", " + side2 + " and " + side3 + " are " + getArea() + " and " + getPerimeter() + " respectively.");
    }
}
