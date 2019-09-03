package com.sg.shapes;

public class Rectangle extends Shape {

    double length;
    double breadth;

    public Rectangle(double length, double breadth) {
        super();
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public double getArea() {
        return length * breadth;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + breadth);
    }

    public void displayAreaAndPerimeterCalculations() {
        Rectangle rectangle = new Rectangle(length, breadth);
        double area = rectangle.getArea();
        double perimeter = rectangle.getPerimeter();
        System.out.println("The area and perimeter of a rectangle with length " + length + " and " + breadth + " are " + area + " and " + perimeter + " respectively");
    }
}
