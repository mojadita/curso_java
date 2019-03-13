package curso.java;


public class Triangle extends Shape  {

    public void triangle() {
        System.out.println("Esto es un TriAngulo.");
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Triangle");
    }

}
