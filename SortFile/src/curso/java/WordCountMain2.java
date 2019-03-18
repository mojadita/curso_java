/*
 * Name: WordCountMain.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 14 mar. 2019 16:17:24
 * Project: SortFile
 * Package: curso.java
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java;


import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.IOException;



/**
 * @author lcu
 *
 */
public class WordCountMain2 {

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
            @SuppressWarnings( "unused" )
            String a = "hello mother";
            int char_read;
            long chars = 0, words = 0, lines = 0;
            boolean in_word = false;
            while ( (char_read = in.read()) != -1 ) {
                chars++ ;
                if ( char_read == '\n' ) {
                    lines++ ;
                    in_word = false;
                } else if ( (char_read >= 'a' && char_read <= 'z')
                        || (char_read >= 'A' && char_read <= 'Z')
                        || (char_read >= '0' && char_read <= '9') ) {
                    // dentro de palabra
                    if ( !in_word ) {
                        words++ ;
                        in_word = true;
                    }
                } else {
                    // fuera de palabra
                    in_word = false;
                }
            } /* no more data left */
            out.println( "chars = " + chars + "\n"
                    + "words = " + words + "\n"
                    + "lines = " + lines );
        } catch ( IOException e ) {
            err.println( "I/O error: " + e );
        }
    }
}
