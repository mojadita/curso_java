package curso.java;


public class Main {
    
    public static void doStuff(Shape s) {
        System.out.println(s);
        s.a();
        s.b();
        s.c();
    }

    public static void main(String[] args) {
        Circle c = new Circle();
        Triangle t = new Triangle();
        Line l = new Line();
        Integer i = new Integer(35);
        doStuff(c);
        doStuff(t);
        doStuff(l);
        //doStuff(i);
    }
}
