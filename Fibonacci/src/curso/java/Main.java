package curso.java;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        Iterator<Long> it = f.iterator();
        while (it.hasNext()) {
            long fib = it.next();
            System.out.println(fib);
        }
    }
}
