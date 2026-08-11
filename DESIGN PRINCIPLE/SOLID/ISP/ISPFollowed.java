package solid.ISP;


// Separate interface for 2D shapes
interface TwoDimensionalShape {
    double area();
}

// Separate interface for 3D shapes
interface ThreeDimensionalShape {
    double area();
    double volume();
}

// Square implements only the 2D interface
class Squares implements TwoDimensionalShape {
    private double side;

    public Squares(double s) {
        this.side = s;
    }

    @Override
    public double area() {
        return side * side;
    }
}

// Rectangle implements only the 2D interface
class Rectangles implements TwoDimensionalShape {
    private double length, width;

    public Rectangles(double l, double w) {
        this.length = l;
        this.width  = w;
    }

    @Override
    public double area() {
        return length * width;
    }
}

// Cube implements the 3D interface
class Cubes implements ThreeDimensionalShape {
    private double side;

    public Cubes(double s) {
        this.side = s;
    }

    @Override
    public double area() {
        return 6 * side * side;
    }

    @Override
    public double volume() {
        return side * side * side;
    }
}

public class ISPFollowed {
    public static void main(String[] args) {
        TwoDimensionalShape square    = new Squares(5);
        TwoDimensionalShape rectangle = new Rectangles(4, 6);
        ThreeDimensionalShape cube     = new Cubes(3);

        System.out.println("Square Area: "    + square.area());
        System.out.println("Rectangle Area: " + rectangle.area());
        System.out.println("Cube Area: "      + cube.area());
        System.out.println("Cube Volume: "    + cube.volume());
    }
}
