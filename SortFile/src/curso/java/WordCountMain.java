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
public class WordCountMain {

    /**
     * @param args
     */
    public static void main( String[] args ) {

        try {
            int char_read;
            long chars = 0, words = 0, lines = 0;
            boolean in_word = false;
            while ( (char_read = in.read()) != -1 ) {
                chars++ ;
                switch ( Character.toLowerCase( char_read ) ) {
                case '\n':
                    lines++ ;
                    in_word = false;
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '_':
                    if ( !in_word ) {
                        words++ ;
                        in_word = true;
                    }
                    break;
                default:
                    in_word = false;
                    break;
                } // switch
            } // while
            /* no more data left */
            out.println( "chars = " + chars + "\n"
                    + "words = " + words + "\n"
                    + "lines = " + lines );
        } catch ( IOException e ) {
            err.println( "I/O error: " + e );
        }
    }
}
