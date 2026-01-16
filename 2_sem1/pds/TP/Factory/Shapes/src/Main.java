public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape circleInstance = shapeFactory.createShape(5);
        System.out.println("The circle has an area of " + circleInstance.getArea() + " and a perimeter of " + circleInstance.getPerimeter());

        Shape rectangleInstance = shapeFactory.createShape(5, 10);
        System.out.println("The rectangle has an area of " + rectangleInstance.getArea() + " and a perimeter of " + rectangleInstance.getPerimeter());
        
        Shape triangleInstance = shapeFactory.createShape(3, 4, 5);
        System.out.println("The triangle has an area of " + triangleInstance.getArea() + " and a perimeter of " + triangleInstance.getPerimeter());
    }
}
