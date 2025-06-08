package Bank;

public class ShapeArray {

	public static void main(String args[]){
		Shape[] shapeArray = new Shape[3];
        shapeArray[0] = new Sphere(3.0);
        shapeArray[1] = new Cylinder(1.0, 3.0);
        shapeArray[2] = new Cone(3.0, 5.0);

        for (Shape shape : shapeArray) {
            System.out.println(shape.toString());
            System.out.println("__________");
        }
	}
}