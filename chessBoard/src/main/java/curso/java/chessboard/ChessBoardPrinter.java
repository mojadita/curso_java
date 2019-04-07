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



        //printBoard(4,); how to use this method ? need something more after the number 4
        String a_single_header_is_this = "+===+===+===+===+";
        String a_single_line = "|";
        String a_single_board =
                 " +=== +=== +=== +=== + \n" +
                 " |###|   |###|   | \n" +
                 " |###|   |###|   | \n" +

                 " +---+---+---+---+ \n" +
                 " |   |###|   |###| \n" +
                 " |   |###|   |###| \n" +

                 " +---+---+---+---+ \n" +
                 " |###|   |###|   | \n" +
                 " |###|   |###|   | \n" +

                 " +---+---+---+---+ \n" +
                 " |   |###|   |###| \n" +
                 " |   |###|   |###| \n" +

                 " +===+===+===+===+   " ;

        String myboard_Start_game = "Beginning the Game...Let's Cheez!!";
        String myboard =    " +===+===+===+===+ \n"   +
                            " |T|A|C|R|R|C|A|T| \n"   +
                            " |P|P|P|P|P|P|P| | \n"   +
                            " |-|-|-|-|-|-|-|P| \n"   +
                            " |-|-|-|-|-|-|-|-| \n"   +
                            " |-|-|-|-|-|-|-|-| \n"   +
                            " |P|-|-|-|-|-|-|-| \n"   +
                            " | |P|P|P|P|P|P|P| \n"   +
                            " |T|A|C|R|R|C|A|T|  \n"  +
                            " +===+===+===+===+ \n" ;
        out.println(myboard);

        final String esq = "+";
        final String dob = "^^";
        final String simp = "---";
        final String spac = "   ";
        final String vert = "|";
        final String rell = "###";
        final String top = "===";

        //Printing the Table
        out.println(myboard_Start_game);
        // linea de cabecera
        imprimeLineaDeSeparacion(esq, dob, top, esq, size, out);
        for (int r = 0; r < size; r++) {
            if (r != 0)
                // linea de separacion
                imprimeLineaDeSeparacion(esq, simp, simp, esq, size, out);
            // contenido
            if (r % 2 == 0) {
                imprimeLineaDeSeparacion(vert, rell, spac, vert, size, out);
                imprimeLineaDeSeparacion(vert, rell, spac, vert, size, out);
            } else {
                imprimeLineaDeSeparacion(vert, spac, rell, vert, size, out);
                imprimeLineaDeSeparacion(vert, spac, rell, vert, size, out);
            }
        }
        // linea de cierre
        imprimeLineaDeSeparacion(esq, top, top, esq, size, out);
    }

    public static void imprimeLineaDeSeparacion(String patt1, String patt1bis, String patt1tris, String patt2, int cols, PrintStream out) {
        for (int c = 0; c < cols; c++){
            out.print(patt1);
            if (c % 2 == 0)
                out.print(patt1bis);
            else out.print(patt1tris);
        }
        out.println(patt2);
    }

    /**
     * Main program.  Don't touch anything here.
     * @param args You can specify a single argument specifying the
     * number of cells the size of the board has.  If you pass more
     * arguments than one, the rest are ignored.
     */
    public static void main( String[] args ) {
        int size = DEFAULT_SIZE;
        if ( args.length > 0 ) {
            size = Integer.parseInt( args[ 0 ] );
        }
        printBoard( size, System.out );
    }
}
