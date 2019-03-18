
package curso.java;


import java.util.ArrayList;



public class EratostenesArrayList {

    public static final int N = 10;

    public static void main( String[] args ) {

        ArrayList<Boolean> al = new ArrayList<Boolean>( N );
        for ( int i = 0; i < N; i++ )
            al.add( false );
        for ( int i = 2; i < N; i++ ) {
            if ( al.get( i ) )
                continue;

            System.out.println( i );
            // no hay marca.
            // System.out.println(i);
            for ( int j = 2 * i; j < N; j += i )
                al.set( j, true );
        }
        for ( int i = 0; i < N; i++ )
            System.out.println( al.get( i ) + "Esto despues del bucle" );
    }

}
