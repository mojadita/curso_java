/*
 * Name: MainSort.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 15 mar. 2019 15:30:11
 * Project: SortFile
 * Package: curso.java.chaterz
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java.chaterz;


import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.IOException;
import java.util.ArrayList;



/**
 * @author lcu
 *
 */
public class MainSort {

    /**
     * @param args
     */
    public static void main( String[] args ) {

        try {
            /*
             * final String[] args1 = args;
             * String[] args2 =
             * {"hello hola hola ho la hshshshsh hola \n hoasdla hasdkdod"};
             */
            ArrayList<String> lineas = new ArrayList<String>();
            String linea = "";
            int char_read;
            while ( (char_read = in.read()) != -1 ) {
                switch ( char_read ) {
                case '\n':
                    lineas.add( linea );
                    linea = "";
                    break;
                default:
                    linea = linea + (char) char_read;
                    break;
                }
            } /* no more data left */
            for ( String l: lineas ) {
                out.println( "[" + l + "]" );
            }
        } catch ( IOException e ) {
            err.println( "I/O error: " + e );
        }

    }

}
