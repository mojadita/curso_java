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
    public static final int  CELL_ROWS   = 2;

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

    public void build( int row, int col ) {
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkNorth( row, col );
        checkEast( row, col );
        checkSouth( row, col );
        checkWest( row, col );
    }

    private void checkNorth( int row, int col ) {
        if ( row == 0 || (cells[ row - 1 ][ col ] & MARKED_CELL) != 0
                || m_toVisit == 0 || m_random.nextBoolean() )
            return; /* unvisitable or already marked */
        
        cells[row][col] &= ~NORTH_WALL;
        row-- ;
        cells[row][col] &= ~SOUTH_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkNorth( row, col );
        checkEast( row, col );
        checkSouth( row, col );
        checkWest( row, col );
    }

    private void checkEast( int row, int col ) {
        if ( col + 1 == m_cols || (cells[ row ][ col + 1 ] & MARKED_CELL) != 0
                || m_toVisit == 0 || m_random.nextBoolean() )
            return; /* unvisitable or already marked */
        cells[row][col] &= ~EAST_WALL;
        col++ ;
        cells[row][col] &= ~WEST_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkNorth( row, col );
        checkEast( row, col );
        checkSouth( row, col );
        checkWest( row, col );
    }

    private void checkSouth( int row, int col ) {
        if ( row + 1 == m_rows || (cells[ row + 1 ][ col ] & MARKED_CELL) != 0
                || m_toVisit == 0 || m_random.nextBoolean() )
            return; /* unvisitable or already marked */
        cells[row][col] &= ~SOUTH_WALL;
        row++ ;
        cells[row][col] &= ~NORTH_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkNorth( row, col );
        checkEast( row, col );
        checkSouth( row, col );
        checkWest( row, col );
    }

    private void checkWest( int row, int col ) {
        if ( col == 0 || (cells[ row ][ col - 1 ] & MARKED_CELL) != 0
                || m_toVisit == 0 || m_random.nextBoolean() )
            return; /* unvisitable or already marked */
        cells[row][col] &= ~WEST_WALL;
        col-- ;
        cells[row][col] &= ~EAST_WALL;
        cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkNorth( row, col );
        checkEast( row, col );
        checkSouth( row, col );
        checkWest( row, col );
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( int r = 0 ; r < m_rows ; r++ ) {
            /* line above cells row */
            for ( int c = 0 ; c < m_cols ; c++ ) {
                sb.append(
                        (cells[ r ][ c ] & NORTH_WALL) != 0 ? "+--" : "+  " );
            }
            sb.append( "+\n" ); /* top right corner of last cell */
            for ( int l = 0 ; l < CELL_ROWS ; l++ ) {
                for ( int c = 0 ; c < m_cols ; c++ ) {
                    sb.append( (cells[ r ][ c ] & WEST_WALL) != 0 ? "|  "
                            : "   " );
                }
                sb.append( "|\n" );
            }
        }
        for ( int c = 0 ; c < m_cols ; c++ )
            sb.append( "+--" );
        sb.append( "+\n" );
        return sb.toString();
    }
}
