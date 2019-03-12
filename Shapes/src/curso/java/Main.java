package curso.java;


public class Main {
    
    public static void doStuff(Shape s) {
        System.out.println(s);
        s.a();
        s.b();
        s.c();
        s.draw();
    }
    
    public static void doStuff2(Triangle t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        Circle c = new Circle();
        Triangle t = new Triangle();
        Line l = new Line();
        Shape s = new Triangle();
        Shape s2 = new Circle();
        Integer i = new Integer(35);
        doStuff(c); // <<< aquí se genera un objeto local a
        doStuff(t); // <<< aquí se genera un objeto local a
        doStuff(l);
        doStuff(s); // <<< aquí se genera un objeto local a
        //doStuff(i);
        doStuff2(t);
        //doStuff2(l);
        doStuff2((Triangle)s);
        //doStuff2(i);
        //doStuff2((Triangle)s2);  // esta es la que da la excepción.
        System.gc(); // que son los que se liberan al llamar al garbage collector.
   }
}
