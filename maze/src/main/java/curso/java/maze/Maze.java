/* Name: Maze.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 6 abr. 2019 20:49:39
 * Project: maze
 * Package: curso.java.maze
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package curso.java.maze;

import java.util.Random;

/**
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class Maze {

    public static final byte NORTH_WALL  = (1 << 0);
    public static final byte EAST_WALL   = (1 << 1);
    public static final byte SOUTH_WALL  = (1 << 2);
    public static final byte WEST_WALL   = (1 << 3);
    public static final byte MARKED_CELL = (1 << 4);
    public static final byte ALL_WALLS   = NORTH_WALL | EAST_WALL | SOUTH_WALL
            | WEST_WALL;
    public static final int  CELL_ROWS   = 0;

    static final int[][] table = new int[][] { //
        /*    0 */ new int[] { 1, 2, 4, 8
        }, /* 1 */ new int[] { 3, 5, 9
        }, /* 2 */ new int[] { 3, 6, 10
        }, /* 3 */ new int[] { 7, 11
        }, /* 4 */ new int[] { 5, 6, 12
        }, /* 5 */ new int[] { 7, 13
        }, /* 6 */ new int[] { 7, 14
        }, /* 7 */ new int[] { 15
        }, /* 8 */ new int[] { 9, 10, 12
        }, /* 9 */ new int[] { 11, 13
        }, /*10 */ new int[] { 11, 14
        }, /*11 */ new int[] { 15
        }, /*12 */ new int[] { 13, 14
        }, /*13 */ new int[] { 15
        }, /*14 */ new int[] { 15
        }, /*15 */ null,
    };

    private byte[][] cells;
    private int      m_rows, m_cols;
    private int      m_toVisit;
    private Random   m_random = new Random();

    /**
     * Initializes maze to an all cells unconnected state.
     * 
     * @param rows is the number of rows of the maze.
     * @param cols is the number of columns in the maze.
     */
    public void init( int rows, int cols ) {
        m_rows    = rows;
        m_cols    = cols;
        m_toVisit = rows * cols;
        cells     = new byte[rows][];
        for ( int r = 0 ; r < rows ; r++ ) {
            cells[ r ] = new byte[cols];
            for ( int c = 0 ; c < cols ; c++ )
                cells[ r ][ c ] = ALL_WALLS;
        }
    }

    private void checkPos( String from, int row, int col ) {
        int state = 0;
        while ( table[ state ] != null ) {
            int next = table[ state ][ m_random
                    .nextInt( table[ state ].length ) ];
            int bit  = state ^ next;
            if ( (bit & NORTH_WALL) != 0 ) checkNorth( row, col );
            if ( (bit & EAST_WALL) != 0 ) checkEast( row, col );
            if ( (bit & SOUTH_WALL) != 0 ) checkSouth( row, col );
            if ( (bit & WEST_WALL) != 0 ) checkWest( row, col );
            // System.out.println( String.format( "%s: %d -> %d", from, state,
            // next ) );
            state = next;
        }

    }

    public void build( int row, int col ) {
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPos( "build", row, col );
    }

    private void checkNorth( int row, int col ) {
        if ( row == 0 || (cells[ row - 1 ][ col ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */

        cells[ row ][ col ] &= ~NORTH_WALL;
        row-- ;
        cells[ row ][ col ] &= ~SOUTH_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPos( "checkNorth", row, col );
    }

    private void checkEast( int row, int col ) {
        if ( col + 1 == m_cols || (cells[ row ][ col + 1 ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        cells[ row ][ col ] &= ~EAST_WALL;
        col++ ;
        cells[ row ][ col ] &= ~WEST_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPos( "checkEast", row, col );
    }

    private void checkSouth( int row, int col ) {
        if ( row + 1 == m_rows || (cells[ row + 1 ][ col ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        cells[ row ][ col ] &= ~SOUTH_WALL;
        row++ ;
        cells[ row ][ col ] &= ~NORTH_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPos( "checkSouth", row, col );
    }

    private void checkWest( int row, int col ) {
        if ( col == 0 || (cells[ row ][ col - 1 ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        cells[ row ][ col ] &= ~WEST_WALL;
        col-- ;
        cells[ row ][ col ] &= ~EAST_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPos( "checkWest", row, col );
    }

    private static final String[] line_chars = {
        /*         NORTH                NORTH
         *                   EAST       EAST
         */
        "·"/* */, "\u2576", "\u2575", "\u2514", //  
        "\u2574", "\u2500", "\u2518", "\u2534", // SOUTH
        "\u2577", "\u250c", "\u2502", "\u251c", //       WEST
        "\u2510", "\u252c", "\u2524", "\u253c"  // SOUTH WEST
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /* first row */
        sb.append( "\u250c" );
        for ( int c = 0 ; c < m_cols - 1 ; c++ ) {
            sb.append( (cells[ 0 ][ c ] & EAST_WALL) != 0 //
                    ? "\u2500\u252c" // T shaped symbol
                    : "\u2500\u2500" ); // horizontal line
        }
        sb.append( "\u2500\u2510\n" ); // uper right corner.
        for ( int r = 0 ; r < m_rows - 1 ; r++ ) {
            for ( int l = 0 ; l < CELL_ROWS ; l++ ) {
                sb.append( " \u2502" ); // vertical line
                for ( int c = 0 ; c < m_cols ; c++ ) {
                    sb.append( (cells[ r ][ c ] & EAST_WALL) != 0 
                            ? " \u2502" // vertical line 
                            : "  " );
                }
                sb.append( "\n" );
            }
            sb.append( (cells[ r ][ 0 ] & SOUTH_WALL) != 0 //
                    ? "\u251c" // Left wall with hor wall to the right.
                    : "\u2502" );
            for ( int c = 0 ; c < m_cols - 1 ; c++ ) {
                int val = (cells[ r ][ c ]         & (EAST_WALL | SOUTH_WALL))
                        | (cells[ r + 1 ][ c + 1 ] & (WEST_WALL | NORTH_WALL));
                sb.append( (cells[r][c] & SOUTH_WALL) != 0 ? "\u2500" : " " );
                sb.append( line_chars[ val ] );
            }
            sb.append( (cells[ r ][ m_cols - 1 ] & SOUTH_WALL) != 0
                    ? "\u2500\u2524\n" // T rotated to the right.
                    : " \u2502\n" ); // vertical bar.
        }
        sb.append( "\u2514" ); // bottom left corner.
        for ( int c = 0 ; c < m_cols - 1 ; c++ )
            sb.append(
                    (cells[ m_rows - 1 ][ c ] & EAST_WALL) != 0 
                    ? "\u2500\u2534" // inverted T.
                    : "\u2500\u2500" ); // horizontal bar.
        sb.append( "\u2500\u2518\n" ); // bottom right corner.
        return sb.toString();
    }
}
