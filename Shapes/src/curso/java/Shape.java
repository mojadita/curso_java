
package curso.java;


public abstract class Shape {

    public void a() {

        @SuppressWarnings( "unused" )
        Shape a = new Circle();
        System.out.println( "Shape.a()" );
    }

    public void b() {

        System.out.println( "Shape.b()" );
    }

    public void c() {

        System.out.println( "Shape.c()" );
    }

    public abstract void draw();

    public void t() {

        System.out.println(
                "Este método viene directamente de la clase Shape es el método t()" );
    }
}
