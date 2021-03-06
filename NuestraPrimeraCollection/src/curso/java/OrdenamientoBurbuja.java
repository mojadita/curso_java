/*
 * Name: OrdenamientoBurbuja.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 19 mar. 2019 16:14:01
 * Project: NuestraPrimeraCollection
 * Package: curso.java
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java;


/**
 * @author lcu
 *
 */
public class OrdenamientoBurbuja {

    /**
     * @param args
     */
    public static void main( String[] args ) {

        NuestraLista<String> conjunto = new NuestraLista<String>( args.length );
        // inicializamos la lista
        for ( int i = 0; i < args.length; i++ )
            conjunto.set( i, args[i] );
        // la imprimimos
        for ( String el: conjunto )
            System.out.println( "[" + el + "]" );
        // variable auxiliar para saber si cuando hagamos una pasada hay que volver a pasar.
        boolean hay_que_seguir;
        do {
            hay_que_seguir = false; // inicializamos a falso al comienzo de cada pasada.
            for ( int i = 0; i < args.length - 1; i++ ) { // para todas las parejas.
                String izq = conjunto.get( i ), der = conjunto.get( i + 1 );
                if ( izq.compareTo( der ) < 0 ) {
                    conjunto.set( i, der );
                    conjunto.set( i + 1, izq );
                    hay_que_seguir = true;
                }
            }
        } while ( hay_que_seguir );
        System.out.println();
        for( String elemento: conjunto ) {
            System.out.println("<" + elemento + ">");
        }
    }

}
