package curso.java;


public class Line extends Shape  {
    public void a() {
        System.out.println("Line.a()");
    }
    public void Line() {
        System.out.println("Esto es una Linea.");
    }



    @Override
    public void draw() {

        System.out.println("Drawing a Line");
    }
    
}
