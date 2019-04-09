/* Name: Maze.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 6 abr. 2019 20:49:39
 * Project: maze
 * Package: curso.java.maze
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package curso.java.maze;

import java.util.Random;

/**
 * This class represents a {@link Maze}, that is, a representation fo a
 * rectangular maze of cells with four possible ways to the next four cells,
 * northwards, southwards, eastwards and westwards.
 * 
 * The maze is inited for the number of rows and columns specified, and then the
 * {@link #buildFrom(int, int)} method can be called to begin building the maze
 * from specified cell. It uses a depth first recursive algorithm to explore the
 * maze, opening walls to neighbour cells from the actually visited. As cells
 * are visited they are marked as accessible and the algorithm finishes upon
 * returning from all the reoursive calls.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class Maze {

    /**
     * Bit mask corresponding to the presence or absence of a north wall in this
     * cell.
     */
    public static final byte     NORTH_WALL  = (1 << 0);
    /**
     * @see #EAST_WALL
     */
    public static final byte     EAST_WALL   = (1 << 1);
    /**
     * @see #EAST_WALL
     */
    public static final byte     SOUTH_WALL  = (1 << 2);
    /**
     * @see #EAST_WALL
     */
    public static final byte     WEST_WALL   = (1 << 3);
    /**
     * This mark indicates that the cell has been already visited. It can be
     * used to
     * force the algorithm not to enter into it, so zones in the {@link Maze}
     * can be
     * avoided.
     * 
     * @see #EAST_WALL
     */
    public static final byte     MARKED_CELL = (1 << 4);
    /**
     * Bit mask with all the walls set. This is the initial mask used in each
     * cell
     * to begin calculating the random {@link Maze}.
     * 
     * @see #EAST_WALL
     */
    public static final byte     ALL_WALLS   = NORTH_WALL | EAST_WALL
            | SOUTH_WALL | WEST_WALL;
    /**
     * The next table allows to visit the adjacent cells in a random order in an
     * efficient way. This obeys to a state table in which each direction is
     * marked
     * once it has been visited. The target is to start with a value of
     * {@code 0}
     * and grow upto {@code 15} by "filing" bits, according to the random result
     * of
     * the next possible states.
     * 
     * To use this table, you start with a state of {@code 0} and select a
     * random
     * number based on the number of entries of the cell second level array. The
     * result of the random selects the next possible state (which represents a
     * bit
     * mask of the already tried directions) and the activated bit in the mask
     * (that
     * can be deducted by xor-ing the last state with the actual one) represents
     * the
     * wall tried. Finally, you get to a state with all masks active,
     * represented by
     * a {@code null} reference. This table allows for very efficient way to
     * visit
     * all the directions from a cell in a random order. In case of a 3D maze,
     * we
     * have to construct a table with two more bits, for the up and down walls.
     */
    static private final int[][] table       = new int[][] {         //
        /* 0 */ new int[] { 1, 2, 4, 8
        }, /* 1 */ new int[] { 3, 5, 9
        }, /* 2 */ new int[] { 3, 6, 10
        }, /* 3 */ new int[] { 7, 11
        }, /* 4 */ new int[] { 5, 6, 12
        }, /* 5 */ new int[] { 7, 13
        }, /* 6 */ new int[] { 7, 14
        }, /* 7 */ new int[] { 15
        }, /* 8 */ new int[] { 9, 10, 12
        }, /* 9 */ new int[] { 11, 13
        }, /* 10 */ new int[] { 11, 14
        }, /* 11 */ new int[] { 15
        }, /* 12 */ new int[] { 13, 14
        }, /* 13 */ new int[] { 15
        }, /* 14 */ new int[] { 15
        }, /* 15 */ null,
    };
    /**
     * The array of cells of the maze. This is initialized by the
     * {@link #init(int, int)} method.
     */
    private byte[][]             m_cells;
    /**
     * This constant opens the possibility of making more than one row for each
     * {@link Maze}'s row.
     * 
     * Making this constant &gt; zero makes the {@link #toString()} method to
     * draw {@link #m_cellRows} extra rows for each {@link Maze}'s row with
     * empty content and west and east walls.
     */
    private int                  m_cellRows  = 0, m_cellCols = 1;
    /**
     * Number of rows and columns of the exterior rectangle to this
     * {@link Maze}.
     */
    private int                  m_rows, m_cols;
    /**
     * Number of cells that are left to visit.
     * 
     * This field is initialised to the product of rows by the number of columns
     * of
     * the {@link Maze}. After the recursive {@link #buildFrom(int, int)}
     * algorithm
     * finishes, it is left with the final number of unvisited cells. This can
     * be
     * nonzero, as you can preselect cells so they are not visited by the
     * algorithm.
     */
    private int                  m_toVisit;
    /**
     * {@link Random} instance to take the random decisions.
     */
    private Random               m_random    = new Random();

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
        m_cells   = new byte[rows][];
        for ( int r = 0 ; r < rows ; r++ ) {
            m_cells[ r ] = new byte[cols];
            for ( int c = 0 ; c < cols ; c++ )
                m_cells[ r ][ c ] = ALL_WALLS;
        }
    }

    /**
     * Common part of the {@link #checkEastTo(int, int)},
     * {@link #checkNorthTo(int, int)}, {@link #checkSouthTo(int, int)} and
     * {@link #checkWestTo(int, int)} methods.
     * 
     * It follows the algorithm described in the description of the
     * {@link #table}
     * field to visit the next cell in each possible direction.
     * 
     * @param from auxiliar string to describe the direction of the movement
     *             made
     *             (on which direction we are proceeding)
     * @param row  next cell's row we are visiting now.
     * @param col  next cell's column we are visiting now.
     * @see        #table
     */
    private void checkPosTo( String from, int row, int col ) {
        int state = 0;
        while ( table[ state ] != null ) {
            int next = table[ state ][ m_random
                    .nextInt( table[ state ].length ) ];
            int bit  = state ^ next;
            if ( (bit & NORTH_WALL) != 0 ) checkNorthTo( row, col );
            if ( (bit & EAST_WALL) != 0 ) checkEastTo( row, col );
            if ( (bit & SOUTH_WALL) != 0 ) checkSouthTo( row, col );
            if ( (bit & WEST_WALL) != 0 ) checkWestTo( row, col );
            // System.out.println( String.format( "%s: %d -> %d", from, state,
            // next ) );
            state = next;
        }
    }

    /**
     * Entry method to the recursive algorithm to build the {@link Maze}.
     * 
     * @param row cell's row where we init the building process.
     * @param col cell's column where we init the building process.
     */
    public void buildFrom( int row, int col ) {
        m_cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPosTo( "buildFrom", row, col );
    }

    /**
     * Recursive call moving to the north.
     * 
     * @param row next cell's row.
     * @param col next cell's column.
     */
    private void checkNorthTo( int row, int col ) {
        if ( row == 0 || (m_cells[ row - 1 ][ col ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */

        m_cells[ row ][ col ] &= ~NORTH_WALL;
        row-- ;
        m_cells[ row ][ col ] &= ~SOUTH_WALL;
        m_cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkNorthTo", row, col );
    }

    /**
     * Recursive call moving to the east.
     * 
     * @param row next cell's row.
     * @param col next cell's column.
     */
    private void checkEastTo( int row, int col ) {
        if ( col + 1 == m_cols || (m_cells[ row ][ col + 1 ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        m_cells[ row ][ col ] &= ~EAST_WALL;
        col++ ;
        m_cells[ row ][ col ] &= ~WEST_WALL;
        m_cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkEastTo", row, col );
    }

    /**
     * Recursive call moving to the south.
     * 
     * @param row next cell's row.
     * @param col next cell's column.
     */
    private void checkSouthTo( int row, int col ) {
        if ( row + 1 == m_rows || (m_cells[ row + 1 ][ col ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        m_cells[ row ][ col ] &= ~SOUTH_WALL;
        row++ ;
        m_cells[ row ][ col ] &= ~NORTH_WALL;
        m_cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkSouthTo", row, col );
    }

    /**
     * Recursive call moving to the west.
     * 
     * @param row next cell's row.
     * @param col next cell's col.
     */
    private void checkWestTo( int row, int col ) {
        if ( col == 0 || (m_cells[ row ][ col - 1 ] & MARKED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        m_cells[ row ][ col ] &= ~WEST_WALL;
        col-- ;
        m_cells[ row ][ col ] &= ~EAST_WALL;
        m_cells[ row ][ col ] |= MARKED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkWestTo", row, col );
    }

    /**
     * Getter for cell at {@code row} and {@code col}.
     * 
     * @param  row row of the cell we want.
     * @param  col column of the cell we want.
     * @return     the value of the cell at that point.
     */
    public byte getCellAt( int row, int col ) {
        return m_cells[ row ][ col ];
    }

    /**
     * Setter for cell at {@code row} and {@code col}.
     * 
     * @param row the row of the cell we want to set.
     * @param col the column of the cell we want to set.
     * @param val the value to set.
     */
    public void setCellAt( int row, int col, byte val ) {
        this.m_cells[ row ][ col ] = val;
    }

    public void buildWallAt( int row, int col, int which_walls ) {
        m_cells[ row ][ col ] |= which_walls;
        if ( (which_walls & NORTH_WALL) != 0 && row > 0 )
            m_cells[ row - 1 ][ col ] |= SOUTH_WALL;
        if ( (which_walls & SOUTH_WALL) != 0 && row < m_rows - 1 )
            m_cells[ row + 1 ][ col ] |= NORTH_WALL;
        if ( (which_walls & EAST_WALL) != 0 && col < m_cols - 1 )
            m_cells[ row ][ col + 1 ] |= WEST_WALL;
        if ( (which_walls & WEST_WALL) != 0 && col > 0 )
            m_cells[ row ][ col - 1 ] |= EAST_WALL;
    }

    public void destroyWallAt( int row, int col, int which_walls ) {
        m_cells[ row ][ col ] &= ~which_walls;
        if ( (which_walls & NORTH_WALL) != 0 && row > 0 )
            m_cells[ row - 1 ][ col ] &= ~SOUTH_WALL;
        if ( (which_walls & SOUTH_WALL) != 0 && row < m_rows - 1 )
            m_cells[ row + 1 ][ col ] &= ~NORTH_WALL;
        if ( (which_walls & EAST_WALL) != 0 && col < m_cols - 1 )
            m_cells[ row ][ col + 1 ] &= ~WEST_WALL;
        if ( (which_walls & WEST_WALL) != 0 && col > 0 )
            m_cells[ row ][ col - 1 ] &= ~EAST_WALL;
    }

    /**
     * Getter for the {@code rows} attribute.
     * 
     * @return the {@code int} value of the {@code rows} attribute.
     */
    public int getRows() {
        return m_rows;
    }

    /**
     * Getter for the {@code int} {@code cols} attribute.
     * 
     * @return the {@code int} value of the {@code cols} attribute.
     */
    public int getCols() {
        return m_cols;
    }

    /**
     * @return the {@code int} {@code cellRows}.
     */
    public int getCellRows() {
        return m_cellRows;
    }


    /**
     * @param cellRows the {@code int} {@code cellRows} to set
     */
    public void setCellRows( int cellRows ) {
        m_cellRows = cellRows;
    }


    /**
     * @return the {@code int} {@code cellCols}.
     */
    public int getCellCols() {
        return m_cellCols;
    }


    /**
     * @param cellCols the {@code int} {@code cellCols} to set
     */
    public void setCellCols( int cellCols ) {
        m_cellCols = cellCols;
    }

    /**
     * Getter fot the {@code int} {@code toVisit} attribute.
     * 
     * @return the {@code int} value of the {@code toVisit} attribute.
     */
    public int getToVisit() {
        return m_toVisit;
    }

    /**
     * Getter for the {@link Random} {@code random} attribute.
     * 
     * @return the {@link Random} value of the {@code random} attribute.
     */
    public Random getRandom() {
        return m_random;
    }

    /**
     * This method sets/resets the {@link #MARKED_CELL} bit of all cells in the
     * rectangle between position(included) {@code [row0, col0]} and (excluded)
     * {@code [row1, col1]}, based on the value of the {@code val} parameter.
     * 
     * @param row0 is the upper row (included) of the rectangle of the region of
     *             cells to mark/unmark.
     * @param col0 is the left column (included) of the rectangle of the region
     *             of
     *             cells to mark/unmark.
     * @param row1 is the lower row (excluded) of the rectangle of the region of
     *             cells to mark/unmark.
     * @param col1 is the right column (excluded) of the rectangle of the region
     *             of
     *             cells to mark/unmark.
     * @param val  is {@code true} if you want to mark the region as already
     *             visited
     *             (not permitted to visit in the building algorithm) or
     *             {@code false} if you want to mark the cell as allowed to be
     *             visited by the algorithm.
     */
    public void setVisited( int row0, int col0, int row1, int col1,
            boolean val ) {
        for ( int r = row0 ; r < row1 ; r++ )
            for ( int c = col0 ; c < col1 ; c++ )
                if ( val )
                    m_cells[ r ][ c ] |= MARKED_CELL;
                else m_cells[ r ][ c ] &= ~MARKED_CELL;
    }

    public void normalizeMarkedCells() {
        for ( int r = 0 ; r < m_rows ; r++ ) {
            for ( int c = 0 ; c < m_cols ; c++ ) {
                if ( (m_cells[ r ][ c ] & MARKED_CELL) != 0 ) {
                    // north
                    if ( r > 0 && (m_cells[ r - 1 ][ c ] & MARKED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~NORTH_WALL;
                        m_cells[ r - 1 ][ c ] &= ~SOUTH_WALL;
                    }
                    // east
                    if ( c < m_cols - 1
                            && (m_cells[ r ][ c + 1 ] & MARKED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~EAST_WALL;
                        m_cells[ r ][ c + 1 ] &= ~WEST_WALL;
                    }
                    // south
                    if ( r < m_rows - 1
                            && (m_cells[ r + 1 ][ c ] & MARKED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~SOUTH_WALL;
                        m_cells[ r + 1 ][ c ] &= ~NORTH_WALL;
                    }

                    // west
                    if ( c > 0 && (m_cells[ r ][ c - 1 ] & MARKED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~WEST_WALL;
                        m_cells[ r ][ c - 1 ] &= ~EAST_WALL;
                    }
                }
            }
        }
    }

    /**
     * Overriden {@link Object#toString()} method.
     * 
     * The {@link String} representation of a maze consists in a unicode
     * representation of the maze made with line drawing characters. As those
     * characters join at middle line points and center at joining corners, we
     * must
     * derive the line drawing representation by using sets of adjacent cells.
     * 
     * Beware that some fonts don't implement the full set of linedrawing
     * characters
     * and some may get substituted if you try to print them on a device that
     * has no
     * character representation of some of these (mainly the four lines that
     * singly
     * end in the center of the character from one side of it) While we have
     * made
     * our best effort in using a small subset of those, we cannot assume them
     * all
     * will be available when the user tries to represent them on his/her output
     * device.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /* North side of maze */
        sb.append( encodeNorthWest( m_cells[ 0 ][ 0 ] ) ); // north-west corner
        for ( int c = 0 ; c < m_cols - 1 ; c++ ) {
            for ( int l = 0 ; l < m_cellCols ; l++ ) // north line of cell
                sb.append( encodeNorth( m_cells[ 0 ][ c ] ) );
            sb.append(
                    encodeNorth( m_cells[ 0 ][ c ], m_cells[ 0 ][ c + 1 ] ) ); // inter-cell
            // north
            // column
        }
        for ( int ic = 0 ; ic < m_cellCols ; ic++ ) // north line of last cell.
            sb.append( encodeNorth( m_cells[ 0 ][ m_cols - 1 ] ) );
        sb.append( encodeNorthEast( m_cells[ 0 ][ m_cols - 1 ] ) ); // north-east
                                                                    // corner.
        sb.append( "\n" );

        /* inner rows */
        for ( int r = 0 ; r < m_rows - 1 ; r++ ) {
            /* cell body part line */
            for ( int ir = 0 ; ir < m_cellRows ; ir++ ) {
                sb.append( encodeWest( m_cells[ r ][ 0 ] ) ); // West wall
                for ( int c = 0 ; c < m_cols ; c++ ) {
                    for ( int ic = 0 ; ic < m_cellCols ; ic++ )
                        sb.append( encodeInterior() );
                    sb.append( encodeEast( m_cells[ r ][ c ] ) );
                }
                sb.append( "\n" ); // end of line.
            }
            // southern interrow wall
            sb.append( encodeWest( m_cells[ r ][ 0 ], m_cells[ r + 1 ][ 0 ] ) );
            for ( int c = 0 ; c < m_cols - 1 ; c++ ) {
                for ( int l = 0 ; l < m_cellCols ; l++ )
                    sb.append( encodeSouth( m_cells[ r ][ c ] ) );
                sb.append( encode( m_cells[ r ][ c ],
                        m_cells[ r + 1 ][ c + 1 ] ) );
            }
            // southeastern column of eastern wall.
            for ( int ic = 0 ; ic < m_cellCols ; ic++ )
                sb.append( encodeSouth( m_cells[ r ][ m_cols - 1 ] ) );
            sb.append( encodeEast( m_cells[ r ][ m_cols - 1 ],
                    m_cells[ r + 1 ][ m_cols - 1 ] ) );
            sb.append( "\n" );
        }
        // southern size of Maze.
        for ( int ir = 0 ; ir < m_cellRows ; ir++ ) {
            sb.append( encodeWest( m_cells[ m_rows - 1 ][ 0 ] ) );
            for ( int c = 0 ; c < m_cols ; c++ ) {
                for ( int ic = 0 ; ic < m_cellCols ; ic++ )
                    sb.append( encodeInterior() );
                sb.append( encodeEast( m_cells[ m_rows - 1 ][ c ] ) );
            }
            sb.append( "\n" );
        }
        sb.append( encodeSouthWest( m_cells[ m_rows - 1 ][ 0 ] ) ); // bottom
                                                                    // left
                                                                    // corner.
        for ( int c = 0 ; c < m_cols - 1 ; c++ ) {
            for ( int ic = 0 ; ic < m_cellCols ; ic++ )
                sb.append( encodeSouth( m_cells[ m_rows - 1 ][ c ] ) );
            sb.append( encodeSouth( m_cells[ m_rows - 1 ][ c ],
                    m_cells[ m_rows - 1 ][ c + 1 ] ) );
        }
        for ( int ic = 0 ; ic < m_cellCols ; ic++ )
            sb.append( encodeSouth( m_cells[ m_rows - 1 ][ m_cols - 1 ] ) );
        sb.append( encodeSouthEast( m_cells[ m_rows - 1 ][ m_cols - 1 ] ) ); // bottom
                                                                             // right
                                                                             // corner.

        return sb.toString();
    }

    /**
     * Table of string to select the set of chars to use in the
     * {@link #toString()}
     * for the column at the common corrner of four cells.
     * 
     * The printing algorithm selects a string based on the set of walls common
     * to
     * cells at (row, col) and (row+1, col+1). This result in a character with
     * walls
     * emerging in the four cardinal directions: north, south, east and west.
     * The
     * table contents is the unicode line drawing character corresponding to
     * such a
     * set of walls. See the code in {@link #toString()} to see how the proper
     * entry
     * is selected.
     * 
     * Below are shown the cells used and the walls selected to build the table
     * entry in {@link #line_chars_interior} to select the south east column
     * {@link String} to represent the column and the walls emerging from it.
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        | this  |       |
     *        | cell  |       |
     *        |      &lt;|       |
     *        |   vv &lt;|       |
     *        +---====+====---+
     *        |       |&lt; ^^   |
     *        |       |&lt; and  |
     *        |       |  this |
     *        |       |  one  |
     *        +- - - -+- - - -+
     * </pre>
     */
    private static final String[] line_chars_interior = {
        // NORTH
        // NORTH EAST EAST
        " "/* */, "\u2576", "\u2575", "\u2514", //
        "\u2574", "\u2500", "\u2518", "\u2534", // SOUTH
        "\u2577", "\u250c", "\u2502", "\u251c", // WEST
        "\u2510", "\u252c", "\u2524", "\u253c" // SOUTH WEST
    };

    private String encodeNorth( int west, int east ) {
        int val = (east & (NORTH_WALL | WEST_WALL));
        val |= (west & NORTH_WALL) != 0 ? SOUTH_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeNorthEast( int corner ) {
        int val = (corner & EAST_WALL) != 0 ? WEST_WALL : 0;
        val |= (corner & NORTH_WALL) != 0 ? SOUTH_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeEast( int north, int south ) {
        int val = north & (EAST_WALL | SOUTH_WALL);
        val |= (south & EAST_WALL) != 0 ? WEST_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeEast( int cell ) {
        return encodeEast( cell & EAST_WALL, cell & EAST_WALL );
    }

    private String encodeSouthEast( int corner ) {
        int val = corner & (SOUTH_WALL | EAST_WALL);
        return line_chars_interior[ val ];
    }

    private String encodeSouth( int west, int east ) {
        int val = west & (SOUTH_WALL | EAST_WALL);
        val |= (east & SOUTH_WALL) != 0 ? NORTH_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeSouth( int cell ) {
        return encodeSouth( cell & SOUTH_WALL, cell & SOUTH_WALL );
    }

    private String encodeSouthWest( int corner ) {
        int val = (corner & WEST_WALL) != 0 ? EAST_WALL : 0;
        val |= (corner & SOUTH_WALL) != 0 ? NORTH_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeWest( int north, int south ) {
        int val = south & (NORTH_WALL | WEST_WALL);
        val |= (north & WEST_WALL) != 0 ? EAST_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeWest( int cell ) {
        return encodeWest( cell & WEST_WALL, cell & WEST_WALL );
    }

    private String encodeNorthWest( int corner ) {
        int val = corner & (NORTH_WALL | WEST_WALL);
        return line_chars_interior[ val ];
    }

    private String encodeNorth( int cell ) {
        int val = cell & (NORTH_WALL);
        val |= (cell & NORTH_WALL) != 0 ? SOUTH_WALL : 0;
        return line_chars_interior[ val ];
    }

    private String encodeInterior() {
        return line_chars_interior[ 0 ];
    }

    private String encode( int north_west, int south_east ) {
        int val = north_west & (SOUTH_WALL | EAST_WALL);
        val |= south_east & (NORTH_WALL | WEST_WALL);
        return line_chars_interior[ val ];
    }
}
