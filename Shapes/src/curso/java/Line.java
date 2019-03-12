package curso.java;


public class Line extends Shape {
    public void a() {
        System.out.println("Line.a()");
    }

    @Override
    public void draw() {

        System.out.println("Drawing a Line");
    }
    
}
