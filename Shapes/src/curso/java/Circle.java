package curso.java;


public class Circle extends Shape {

    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }

    @Override
    protected void finalize() {
        System.out.println("Crónica de la muerte de un Circle anunciada");
    }
}
