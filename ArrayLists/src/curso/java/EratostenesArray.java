
package curso.java;


public class EratostenesArray {

    public static final int N = 10;
    
    

    public static void main( String[] args ) {

        boolean[] array = new boolean[N];
        for ( int i = 2; i < N; i++ ) {
            if ( array[i] ) {
                continue;
            }
            // no hay marca, luego marcamos todos los multiplos.
            for (int j = 2*i; j < N; j += i)
                array[j] = true;
            System.out.println(i);
        }
    }
}
