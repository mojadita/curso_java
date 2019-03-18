
package curso.java;


public class Line extends Shape {

    public void a() {

        System.out.println( "Line.a()" );
    }

    public Line() {

        System.out.println( "Esto es construir una Linea." );
    }



    @Override
    public void draw() {

        System.out.println( "Drawing a Line" );
    }

}
