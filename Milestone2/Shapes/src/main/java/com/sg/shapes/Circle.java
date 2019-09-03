package com.sg.shapes;

public class Circle extends Shape {

    double radius;
    final double PI = 3.14;

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * PI * radius;
    }

    public void displayAreaAndPerimeterCalculations() {
        System.out.println("The area and perimeter of a circle with radius " + radius + " are equal to " + getArea() + " and " + getPerimeter() + " respectively.");
    }
}
