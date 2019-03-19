
package curso.java;


import java.util.Iterator;



public class Main5 {


    public static void main( String[] args ) {


        NewIterator a = new NewIterator() {

            long z = 0;

            @Override
            public Iterator<Long> iterator() {

                return this;
            }

            @Override
            public boolean hasNext() {

                return z < 1000;
            }

            @Override
            public Long next() {

                return ++z;
            }
        };
        
        for ( Iterator<Long> aa = a.iterator(); aa.hasNext(); ) {
            Long elemento = aa.next();
            System.out.println( elemento );
        }
        // for (Long elemento: a) {
        // System.out.println(elemento);
        // }


        for ( long t = 1; t < 15; t++ ) {
            System.out.println( "Mensaje Nº" + t );
            // if (a.iterator() = 10) {
            // System.out.println("Hemos llegado al Nº 10, este es un mensaje
            // extra");
            // }
        }


    }
}
