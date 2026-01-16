public class ShapeFactory {
    
    public Shape createShape(double radius){
        return new Circle(radius);
    }

    public Shape createShape(double length, double width){
        return new Rectangle(length, width);
    }

    public Shape createShape(double side1, double side2, double side3){
        return new Triangle(side1, side2, side3);
    }


}
