
package curso.java;


public class Main2 {

    public static void main( String[] args ) {

        Fibonacci f = new Fibonacci();

        for ( Long fib: f ) {
            System.out.println( fib );
        }
    }
}
