package curso.java;


public class Circle extends Shape {

   public void boli() {
       System.out.println("Dibujamos un cículo a boli");
   }
    public void rotulador() {
        System.out.println("Dibujamos un círculo a rotulador");
    }

    public void circle() {
        System.out.println("Esto es un circulo.");
    }


    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }

    @Override
    protected void finalize() {
        System.out.println("Crónica de la muerte de un Circle anunciada");
    }
}
