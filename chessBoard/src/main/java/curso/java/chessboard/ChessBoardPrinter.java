/* Name: ChessBoardPrinter.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 6 abr. 2019 12:53:29
 * Project: chessBoard
 * Package: curso.java.chessboard
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package curso.java.chessboard;

import java.io.PrintStream;

/**
 * This class prints a Chess board in ASCII.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class ChessBoardPrinter {

    public static final int DEFAULT_SIZE = 8;

    /**
     * Print a Chess Board of size {@code size}.
     * 
     * The format of the Board must be similar to this one:
     *
     * <pre>
     * +===+===+===+===+
     * |###|   |###|   |
     * |###|   |###|   |
     * +---+---+---+---+
     * |   |###|   |###|
     * |   |###|   |###|
     * +---+---+---+---+
     * |###|   |###|   |
     * |###|   |###|   |
     * +---+---+---+---+
     * |   |###|   |###|
     * |   |###|   |###|
     * +===+===+===+===+
     * </pre>
     * 
     * for a {@code size} of {@code 4}.
     *
     * @param size the number of cells the side of the board has.
     */
    public static void printBoard( int size, PrintStream out ) {
        /* TODO:  You must work here, the board to be printed has to be worked in here.
         * The printing must be done calling to {@code out.println()} or similar calls.
         * Don't call {@code System.out.println()}, but call to {@code out.println()} 
         * instead.
         *  
         */
        int lin, col;
        for ( lin = 0 ; lin < size ; lin++ ) {
            /* first line */
            for ( col = 0 ; col < size ; col++ )
                out.append( lin == 0 ? "+===" : "+---" );
            out.append( "+\n" );
            for ( int row = 0 ; row < 2 ; row++ ) {
                for ( col = 0 ; col < size ; col++ )
                    out.append( (lin + col) % 2 == 0 ? "|###" : "|   " );
                out.append( "|\n" );
            }
        }
        for (col = 0; col < size; col++)
            out.append( "+===" );
        out.append( "+\n" );
    }

    /**
     * Main program. Don't touch anything here.
     * 
     * @param args You can specify a single argument specifying the
     *             number of cells the size of the board has. If you pass more
     *             arguments than one, the rest are ignored.
     */
    public static void main( String[] args ) {
        int size = DEFAULT_SIZE;
        if ( args.length > 0 ) {
            size = Integer.parseInt( args[ 0 ] );
        }
        printBoard( size, System.out );
    }
}
