package curso.java;


import com.sun.istack.internal.NotNull;

public class Main {

    public static void herencia1(Shape t) {
        //estos métodos estan enlazados con Shape y se ejecutan al iniciar la aplicación.
        t.t();
        System.out.println(t);

    }
    
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

    public static void main(String[] args) { //el main es el corazón de la aplicación al desplegar empiza a ejecutarse desde aquí Ex: Línea 27!
        String Inicio = "SALUDOS";
        char uu0 = Inicio.charAt(0);
        char uu1 = Inicio.charAt(1);
        char uu2 = Inicio.charAt(2);
        char uu3 = Inicio.charAt(3);
        char uu4 = Inicio.charAt(4);
        char uu5 = Inicio.charAt(5);
        char uu6 = Inicio.charAt(6);
        System.out.println(uu0);
        System.out.println("="+uu1);
        System.out.println("=="+""+uu1);
        System.out.println("==="+""+""+uu2);
        System.out.println("===="+""+""+""+uu3);
        System.out.println("====="+""+""+""+uu4);
        System.out.println("======"+""+""+""+uu5);
        System.out.println("======="+""+""+""+uu6);
        System.out.println("===============");
        Circle c = new Circle();
        System.out.println("Soy un Círculo con el Siguiente Path ===>"+ c);
        //ademas dibujamos el círculo a boligrafo
        c.boli();
        System.out.println("===============");
        Rectangle u = new Rectangle();
        u.rectanble();
        doStuff(u);
        System.out.println("===============");
        Triangle t = new Triangle();
        t.triangle();
        doStuff(t);
        System.out.println("===============");
        Line l = new Line();
        doStuff(l);
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
        herencia1(t); //hay que llamar a la anotación de la función que está enlazada con la clase principal para que se ejecute dicho método.
        System.gc(); // que son los que se liberan al llamar al garbage collector.*/
        herencia1(t);
   }
}
