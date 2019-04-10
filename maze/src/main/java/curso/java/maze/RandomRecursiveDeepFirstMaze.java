/* @formatter:off
 *       Name: Maze.java 
 *     Author: Luis Colorado <luiscoloradourcola@gmail.com> 
 *       Date: 6 abr. 2019 20:49:39 
 *    Project: maze 
 *    Package: curso.java.maze 
 *  Copyright: (C) 2019 Luis Colorado. All rights reserved.
 * @formatter:on */
package curso.java.maze;

import java.util.Random;

/**
 * This class represents a {@link RandomRecursiveDeepFirstMaze}, that is, a
 * representation fo a rectangular maze of cells with four possible ways to the
 * next four cells, northwards, southwards, eastwards and westwards. The maze is
 * inited for the number of rows and columns specified, and then the
 * {@link #buildFrom(int, int)} method can be called to begin building the maze
 * from specified cell. It uses a depth first recursive algorithm to explore the
 * maze, opening walls to neighbour cells from the actually visited. As cells
 * are visited they are marked as accessible and the algorithm finishes upon
 * returning from all the reoursive calls.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 */
public class RandomRecursiveDeepFirstMaze implements Maze {

    /**
     * Table to calculate the already visited directions from this cell.
     * <p>
     * The next table allows to visit the adjacent cells in a random order in an
     * efficient way. This obeys to a state table in which each direction is
     * marked once it has been visited. The target is to start with a value of
     * {@code 0} and grow upto {@code 15} by "filing" bits, according to the
     * random result of the next possible states.
     * </p>
     * <p>
     * To use this table, you start with a state of {@code 0} and select a
     * random number based on the number of entries of the cell second level
     * array. The result of the random selects the next possible state (which
     * represents a bit mask of the already tried directions) and the activated
     * bit in the mask (that can be deducted by xor-ing the last state with the
     * actual one) represents the wall tried. Finally, you get to a state with
     * all masks active, represented by a {@code null} reference. This table
     * allows for very efficient way to visit all the directions from a cell in
     * a random order. In case of a 3D maze, we have to construct a table with
     * two more bits, for the up and down walls.
     * </p>
     */
    static private final int[][] table      = new int[][] {                 //@formatter:off
        /* 0 */ new int[] { 1, 2, 4, 8 },
        /* 1 */ new int[] { 3, 5, 9 },
        /* 2 */ new int[] { 3, 6, 10 },
        /* 3 */ new int[] { 7, 11 },
        /* 4 */ new int[] { 5, 6, 12 },
        /* 5 */ new int[] { 7, 13 },
        /* 6 */ new int[] { 7, 14 },
        /* 7 */ new int[] { 15 },
        /* 8 */ new int[] { 9, 10, 12 },
        /* 9 */ new int[] { 11, 13 },
        /* 10 */ new int[] { 11, 14 },
        /* 11 */ new int[] { 15 },
        /* 12 */ new int[] { 13, 14 },
        /* 13 */ new int[] { 15 },
        /* 14 */ new int[] { 15 },
        /* 15 */ null, 
        }; //@formatter:on
    /**
     * The array of cells of the maze. This is initialized by the
     * {@link #init(int, int)} method.
     */
    private byte[][]             m_cells;
    /**
     * This constant opens the possibility of making more than one row for each
     * {@link RandomRecursiveDeepFirstMaze}'s row. Making this constant &gt;
     * zero makes the {@link #toString()} method to draw {@link #m_cellRows}
     * extra rows for each {@link RandomRecursiveDeepFirstMaze}'s row with empty
     * content and west and east walls.
     */
    private int                  m_cellRows = 0;
    /**
     * This field represents the number of character columns per cell body. The
     * cell is constructed of cell body chars and intercell wall chars. This
     * number represents the number of columns dedicated to build the interior
     * of the cell.
     */
    private int                  m_cellCols = 1;
    /**
     * Number of rows of this {@link RandomRecursiveDeepFirstMaze}.
     */
    private int                  m_rows;
    /**
     * Number of rows this {@link RandomRecursiveDeepFirstMaze} has.
     */
    private int                  m_cols;
    /**
     * Number of cells that are left to visit.
     * <p>
     * This field is initialised to the product of rows by the number of columns
     * of the {@link RandomRecursiveDeepFirstMaze}. After the recursive
     * {@link #buildFrom(int, int)} algorithm finishes, it is left with the
     * final number of unvisited cells. This can be nonzero, as you can
     * preselect cells so they are not visited by the algorithm.
     * </p>
     */
    private int                  m_toVisit;
    /**
     * {@link Random} instance to take the random decisions.
     */
    private Random               m_random   = new Random();

    /**
     * Initializes maze to an all cells unconnected state.
     * <p>
     * This routine sets all cells to a fixed state, with all walls up and
     * unvisited.
     * </p>
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
     * <p>
     * It follows the algorithm described in the description of the
     * {@link #table} field to visit the next cell in each possible direction.
     * </p>
     * 
     * @param from auxiliar string to describe the direction of the movement
     *             made (on which direction we are proceeding)
     * @param row  next cell's row we are visiting now.
     * @param col  next cell's column we are visiting now.
     * @see        #table
     * @see        #checkEastTo(int, int)
     * @see        #checkNorthTo(int, int)
     * @see        #checkSouthTo(int, int)
     * @see        #checkWestTo(int, int)
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
     * Entry method to the recursive algorithm to build the
     * {@link RandomRecursiveDeepFirstMaze}.
     * <p>
     * The recursive algorithm tries to visit each non-visited cell from this
     * one, using any possible directions in a random order, until all
     * possibilities have been exhausted. Once all possibilities have been used,
     * the algorithm backtracks to the previous cell and continues there. If
     * some of the cells have been already marked as visited, the algorithm
     * doesn't enter into those cells, so this can be used to prepare the maze
     * prior to be generated with banned zones. See the
     * {@link #setVisited(int, int, int, int, boolean)} method to see how to do
     * this.
     * </p>
     * 
     * @param row cell's row where we begin the building process.
     * @param col cell's column where we begin the building process.
     * @SEE       {@link #setVisited(int, int, int, int, boolean)}
     */
    public void buildFrom( int row, int col ) {
        m_cells[ row ][ col ] |= VISITED_CELL;
        m_toVisit-- ;
        checkPosTo( "buildFrom", row, col );
    }

    /**
     * Recursive build call moving to the north.
     * <p>
     * This call tries (if possible) the northern cell to the one we are in. If
     * it can, it drops the northern wall of the cell, moves to it, and
     * decrements the {@link #m_toVisit} field.
     * </p>
     * 
     * @param row next cell's row.
     * @param col next cell's column.
     */
    private void checkNorthTo( int row, int col ) {
        if ( row == 0 || (m_cells[ row - 1 ][ col ] & VISITED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */

        m_cells[ row ][ col ] &= ~NORTH_WALL;
        row-- ;
        m_cells[ row ][ col ] &= ~SOUTH_WALL;
        m_cells[ row ][ col ] |= VISITED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkNorthTo", row, col );
    }

    /**
     * Recursive call moving to the east.
     * <p>
     * This call tries (if possible) the eastern cell to the one we are in. If
     * it can, it drops the eastern wall of the cell, moves to it, and
     * decrements the {@link #m_toVisit} field.
     * </p>
     * 
     * @param row next cell's row.
     * @param col next cell's column.
     */
    private void checkEastTo( int row, int col ) {
        if ( col + 1 == m_cols
                || (m_cells[ row ][ col + 1 ] & VISITED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        m_cells[ row ][ col ] &= ~EAST_WALL;
        col++ ;
        m_cells[ row ][ col ] &= ~WEST_WALL;
        m_cells[ row ][ col ] |= VISITED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkEastTo", row, col );
    }

    /**
     * Recursive call moving to the south.
     * <p>
     * This call tries (if possible) the southern cell to the one we are in. If
     * it can, it drops the southern wall of the cell, moves to it, and
     * decrements the {@link #m_toVisit} field.
     * </p>
     * 
     * @param row next cell's row.
     * @param col next cell's column.
     */
    private void checkSouthTo( int row, int col ) {
        if ( row + 1 == m_rows
                || (m_cells[ row + 1 ][ col ] & VISITED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        m_cells[ row ][ col ] &= ~SOUTH_WALL;
        row++ ;
        m_cells[ row ][ col ] &= ~NORTH_WALL;
        m_cells[ row ][ col ] |= VISITED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkSouthTo", row, col );
    }

    /**
     * Recursive call moving to the west.
     * <p>
     * This call tries (if possible) the western cell to the one we are in. If
     * it can, it drops the western wall of the cell, moves to it, and
     * decrements the {@link #m_toVisit} field.
     * </p>
     * 
     * @param row next cell's row.
     * @param col next cell's col.
     */
    private void checkWestTo( int row, int col ) {
        if ( col == 0 || (m_cells[ row ][ col - 1 ] & VISITED_CELL) != 0
                || m_toVisit == 0 )
            return; /* unvisitable or already marked */
        m_cells[ row ][ col ] &= ~WEST_WALL;
        col-- ;
        m_cells[ row ][ col ] &= ~EAST_WALL;
        m_cells[ row ][ col ] |= VISITED_CELL;
        m_toVisit-- ;
        checkPosTo( "checkWestTo", row, col );
    }

    /* (non-Javadoc)
     * 
     * @see curso.java.maze.MazeIface#getCellAt(int, int) */
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

    /* (non-Javadoc)
     * 
     * @see curso.java.maze.MazeIface#getRows() */
    public int getRows() {
        return m_rows;
    }

    /* (non-Javadoc)
     * 
     * @see curso.java.maze.MazeIface#getCols() */
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
     * Set the visited attribute to a selection of cells.
     * <p>
     * This method sets/resets the {@link #VISITED_CELL} bit of all cells in the
     * rectangle between position(included) {@code [row0, col0]} and (excluded)
     * {@code [row1, col1]}, based on the value of the {@code val} parameter.
     * </p>
     * 
     * @param row0 is the upper row (included) of the rectangle of the region of
     *             cells to mark/unmark.
     * @param col0 is the left column (included) of the rectangle of the region
     *             of cells to mark/unmark.
     * @param row1 is the lower row (excluded) of the rectangle of the region of
     *             cells to mark/unmark.
     * @param col1 is the right column (excluded) of the rectangle of the region
     *             of cells to mark/unmark.
     * @param val  is {@code true} if you want to mark the region as already
     *             visited (not permitted to visit in the building algorithm) or
     *             {@code false} if you want to mark the cell as allowed to be
     *             visited by the algorithm.
     */
    public void setVisited( int row0, int col0, int row1, int col1,
            boolean val ) {
        for ( int r = row0 ; r < row1 ; r++ )
            for ( int c = col0 ; c < col1 ; c++ )
                if ( val )
                    m_cells[ r ][ c ] |= VISITED_CELL;
                else m_cells[ r ][ c ] &= ~VISITED_CELL;
    }

    /**
     * Normalize the cells.
     * <p>
     * This function iterates all the cells of the
     * {@link RandomRecursiveDeepFirstMaze}, searching for visited cells. On
     * each cell, if the cell is visited, connects it to all surrounding cells
     * (dropping the walls) if they are also visisted.
     * </p>
     * <p>
     * <b>Note:</b> this algorithm <b>only</b> drops the walls between visited
     * cells, but doesn't build any, so consider how the walls leave in case you
     * play too much with it
     * </p>
     */
    public void normalizeMarkedCells() {
        for ( int r = 0 ; r < m_rows ; r++ ) {
            for ( int c = 0 ; c < m_cols ; c++ ) {
                if ( (m_cells[ r ][ c ] & VISITED_CELL) != 0 ) {
                    // north
                    if ( r > 0
                            && (m_cells[ r - 1 ][ c ] & VISITED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~NORTH_WALL;
                        m_cells[ r - 1 ][ c ] &= ~SOUTH_WALL;
                    }
                    // east
                    if ( c < m_cols - 1
                            && (m_cells[ r ][ c + 1 ] & VISITED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~EAST_WALL;
                        m_cells[ r ][ c + 1 ] &= ~WEST_WALL;
                    }
                    // south
                    if ( r < m_rows - 1
                            && (m_cells[ r + 1 ][ c ] & VISITED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~SOUTH_WALL;
                        m_cells[ r + 1 ][ c ] &= ~NORTH_WALL;
                    }

                    // west
                    if ( c > 0
                            && (m_cells[ r ][ c - 1 ] & VISITED_CELL) != 0 ) {
                        m_cells[ r ][ c ]     &= ~WEST_WALL;
                        m_cells[ r ][ c - 1 ] &= ~EAST_WALL;
                    }
                }
            }
        }
    }

    /**
     * Overriden {@link Object#toString()} method.
     * <p>
     * The {@link String} representation of a maze consists in a unicode
     * representation of the maze made with line drawing characters. As those
     * characters join at middle line points and center at joining corners, we
     * must derive the line drawing representation by using sets of adjacent
     * cells. Beware that some fonts don't implement the full set of linedrawing
     * characters and some may get substituted if you try to print them on a
     * device that has no character representation of some of these (mainly the
     * four lines that singly end in the center of the character from one side
     * of it) While we have made our best effort in using a small subset of
     * those, we cannot assume them all will be available when the user tries to
     * represent them on his/her output device.
     * </p>
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for ( int row = 0 ; row < m_rows ; row++ ) {
            if ( row == 0 )
                addFirstLine( sb );
            else addInterRowLine( row, sb );
            for ( int n = 0 ; n < m_cellRows ; n++ )
                addBodyRowLine( row, sb );
        }
        addLastLine( sb );
        return sb.toString();
    }

    /**
     * Add the first line to {@link #toString()}.
     * <p>
     * This is called from {@link #toString()} to add the first line (northest)
     * to the {@link RandomRecursiveDeepFirstMaze}.
     * </p>
     * 
     * @param sb
     */
    private void addFirstLine( StringBuffer sb ) {
        for ( int col = 0 ; col < m_cols ; col++ ) {
            if ( col == 0 )
                addFirstLineFirstChar( sb );
            else addInterColTopChar( col, sb );
            for ( int n = 0 ; n < m_cellCols ; n++ )
                addCellBodyTopChar( 0, col, sb );
        }
        addFirstLineLastChar( sb );
        sb.append( '\n' );
    }

    /**
     * Add the inter row line.
     * <p>
     * This method adds the inter-row line of walls between rows.
     * </p>
     * 
     * @param row is the row number this line is underneath.
     * @param sb  the {@link StringBuffer} this method is appending to.
     */
    private void addInterRowLine( int row, StringBuffer sb ) {
        for ( int col = 0 ; col < m_cols ; col++ ) {
            if ( col == 0 )
                addInterRowFirstChar( row, sb );
            else addInterRowInterCellChar( row, col, sb );
            for ( int n = 0 ; n < m_cellCols ; n++ )
                addCellBodyTopChar( row, col, sb );
        }
        addInterRowLastCellChar( row, sb );
        sb.append( '\n' );
    }

    /**
     * Add the body cell lines to a row.
     * <p>
     * This method adds the lines of cell bodies to a row.
     * </p>
     * 
     * @param row is the row number of this row.
     * @param sb  is the {@link StringBuffer} this method is to append to.
     */
    private void addBodyRowLine( int row, StringBuffer sb ) {
        for ( int col = 0 ; col < m_cols ; col++ ) {
            addBodyRowInterCellLeftChar( row, col, sb );
            for ( int n = 0 ; n < m_cellCols ; n++ )
                addCellBodyChar( sb );
        }
        addCellBodyLastChar( row, sb );
        sb.append( '\n' );
    }

    /**
     * Add the last {@link RandomRecursiveDeepFirstMaze} line.
     * <p>
     * This method is called by {@link #toString()} to generate the last line of
     * walls of the {@link RandomRecursiveDeepFirstMaze}.
     * </p>
     * 
     * @param sbis the {@link StringBuffer} this method is to append to.
     */
    private void addLastLine( StringBuffer sb ) {
        for ( int col = 0 ; col < m_cols ; col++ ) {
            if ( col == 0 )
                addLastLineFirstChar( sb );
            else addLastLineInterCellChar( col, sb );
            for ( int n = 0 ; n < m_cellCols ; n++ )
                addCellBodyBottomChar( col, sb );
        }
        addLastLineLastChar( sb );
    }

    /**
     * Table of chars to select the set of chars to use in the
     * {@link #toString()} for the column at the common corrner of four cells.
     * <p>
     * The printing algorithm selects a char based on the set of walls common to
     * cells at {@code (row-1, col-1)} and {@code (row+1, col)}. This result in
     * a character with walls emerging in the four cardinal directions: north,
     * south, east and west.
     * </p>
     * <p>
     * The table contents is the unicode line drawing character corresponding to
     * such a set of walls.
     * </p>
     * <p>
     * Below are shown the cells used and the walls selected to build the table
     * entry in {@link #line_chars_interior} to select the south east column
     * {@link String} to represent the column and the walls emerging from it.
     * </p>
     * <p>
     * If you see the code on the individual drawing routines, you'll see that
     * some of the cell contents are moved previously, before masking to select
     * the proper index in this table.
     * </p>
     * <p>
     * <b>NOTE:</b> The {@link #line_chars_interior} index, depends on the bit
     * positions of the {@link #NORTH_WALL}, {@link #SOUTH_WALL},
     * {@link #EAST_WALL} and {@link #WEST_WALL}, so this array will need to be
     * remade if those bit positions change.
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        | this  |       |
     *        | cell  @       |
     *        |       @       |
     *        |       @       |
     *        +---@@@@@@@@@---+
     *        |       @       |
     *        |       @  and  |
     *        |       @  this |
     *        |       |  one  |
     *        +- - - -+- - - -+
     * </pre>
     * </p>
     * 
     * @see #addFirstLineFirstChar(StringBuffer)
     * @see #addInterColTopChar(int, StringBuffer)
     * @see #addCellBodyTopChar(int, int, StringBuffer)
     * @see #addFirstLineLastChar(StringBuffer)
     * @see #addInterRowFirstChar(int, StringBuffer)
     * @see #addInterRowInterCellChar(int, int, StringBuffer)
     * @see #addInterRowLastCellChar(int, StringBuffer)
     * @see #addBodyRowInterCellLeftChar(int, int, StringBuffer)
     * @see #addCellBodyChar(StringBuffer)
     * @see #addCellBodyLastChar(int, StringBuffer)
     * @see #addLastLineFirstChar(StringBuffer)
     * @see #addLastLineInterCellChar(int, StringBuffer)
     * @see #addCellBodyBottomChar(int, StringBuffer)
     * @see #addLastLineLastChar(StringBuffer)
     */
    private static final char[] line_chars_interior = {
        // NORTH
        // NORTH EAST EAST
        ' '/* */, '\u2576', '\u2575', '\u2514', //
        '\u2574', '\u2500', '\u2518', '\u2534', // SOUTH
        '\u2577', '\u250c', '\u2502', '\u251c', // WEST
        '\u2510', '\u252c', '\u2524', '\u253c' // SOUTH WEST
    };

    /**
     * This is the top left corner char of the maze.
     * <p>
     * It is constructed based on the following graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |       |       |
     *        |       |       |
     *        |       |       |
     *        +-------@@@@@---+
     *        |       @       |
     *        |       @       |
     *        |       @  (1)  |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell contents at position {@code (0,0)}.
     * </p>
     * 
     * @param sb {@link StringBuffer} where we build the {@link #toString()}
     *           result.
     * @see      #line_chars_interior
     */
    private void addFirstLineFirstChar( StringBuffer sb ) {
        sb.append( line_chars_interior[ m_cells[ 0 ][ 0 ]
                & (NORTH_WALL | WEST_WALL) ] );
    }

    /**
     * This is the top intercolumn top char of the maze.
     * <p>
     * It is printed based on the following graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |   A   |       |
     *        |   |   |       |
     *        |   |   |       |
     *        +---@@@@@@@@@---+
     *        |   |   @       |
     *        |   |   @       |
     *        |  (1)  @  (2)  |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (2)} is the cell at {@code (0,col)}.
     * </p>
     * 
     * @param col is the column number corresponding to cell {@code (2)} in the
     *            graph.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addInterColTopChar( int col, StringBuffer sb ) {
        sb.append( line_chars_interior[ move_north( m_cells[ 0 ][ col - 1 ] )
                | (m_cells[ 0 ][ col ] & (NORTH_WALL | WEST_WALL)) ] );
    }

    /**
     * This is the top char of a cell of the maze.
     * <p>
     * It is printed based on the following graph:
     * 
     * <pre>
     *        +- - - - - - - -+
     *        |               |
     *        |   A           |
     *        |   |           |
     *        |   |           |
     *        +---@@@@@@@@@---+
     *        |   |           |
     *        |   |           |
     *        |   `--(1)--&gt;   |
     *        |               |
     *        +- - - - - - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, col)}.
     * </p>
     * 
     * @param row is the row corresponding to {@code (1)} in the graph.
     * @param col is the column corresponding to {@code (1)} in the graph.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addCellBodyTopChar( int row, int col, StringBuffer sb ) {
        sb.append( line_chars_interior[ move_north( m_cells[ row ][ col ] )
                | (m_cells[ row ][ col ] & NORTH_WALL) ] );
    }

    /**
     * This is the top right corner char of the maze.
     * <p>
     * It is printed based on the following graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |   A   |       |
     *        |   |   |       |
     *        |   |   |       |
     *        +---@@@@@-------+
     *        |   |   @       |
     *        |   |   @       |
     *        |  (1)--@---&gt;   |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (0,m_cols - 1)}.
     * </p>
     * 
     * @param sb {@link StringBuffer} where we build the {@link #toString()}
     *           result.
     * @see      #line_chars_interior
     */
    private void addFirstLineLastChar( StringBuffer sb ) {
        sb.append( line_chars_interior[ move_north( m_cells[ 0 ][ m_cols - 1 ] )
                | move_east( m_cells[ 0 ][ m_cols - 1 ] ) ] );
    }

    /**
     * This is the inter row first char of the maze.
     * <p>
     * It is printed based on the following graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |   &lt;---@--(2)  |
     *        |       @       |
     *        |       @       |
     *        +-------@@@@@---+
     *        |       @       |
     *        |       @       |
     *        |       @  (1)  |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, 0)} and {@code (2)} is the
     * cell at {@code (row-1, 0)}.
     * </p>
     * 
     * @param row is the {@code row} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addInterRowFirstChar( int row, StringBuffer sb ) {
        sb.append( line_chars_interior[ move_west( m_cells[ row - 1 ][ 0 ] )
                | (m_cells[ row ][ 0 ] & (NORTH_WALL | WEST_WALL)) ] );
    }

    /**
     * This is the inter row intercell char of the maze.
     * <p>
     * It is printed based on the following graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |  (2)  @       |
     *        |       @       |
     *        |       @       |
     *        +---@@@@@@@@@---+
     *        |       @       |
     *        |       @       |
     *        |       @  (1)  |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, 0)} and {@code (2)} is the
     * cell at {@code (row-1, col-1)}.
     * </p>
     * 
     * @param row is the {@code row} of {@code (1)}.
     * @param col is the {@code col} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addInterRowInterCellChar( int row, int col, StringBuffer sb ) {
        sb.append( line_chars_interior[ (m_cells[ row - 1 ][ col - 1 ]
                & (SOUTH_WALL | EAST_WALL))
                | (m_cells[ row ][ col ] & (NORTH_WALL | WEST_WALL)) ] );
    }

    /**
     * This is the inter row las char of the line.
     * <p>
     * It is printed based on the following graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |  (2)  @       |
     *        |       @       |
     *        |       @       |
     *        +---@@@@@-------+
     *        |       @       |
     *        |       @       |
     *        |  (1)--@---&gt;   |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, m_cols-1)} and {@code (2)}
     * is the cell at {@code (row-1, m_cols-1)}.
     * </p>
     * 
     * @param row is the {@code row} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addInterRowLastCellChar( int row, StringBuffer sb ) {
        sb.append(
                line_chars_interior[ move_east( m_cells[ row ][ m_cols - 1 ] )
                        | (m_cells[ row - 1 ][ m_cols - 1 ]
                                & (SOUTH_WALL | EAST_WALL)) ] );
    }

    /**
     * This is the row intercell char of the line.
     * <p>
     * It is printed at the beginning of the line, and between cells, based on
     * the next graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |  &lt;--------+   |
     *        |       @   |   |
     *        |       @   |   |
     *        |       @  (1)  |
     *        |       @   |   |
     *        |       @   |   |
     *        |       @   V   |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, 0)}.
     * </p>
     * 
     * @param row is the {@code row} of {@code (1)}.
     * @param col is the {@code col} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addBodyRowInterCellLeftChar( int row, int col,
            StringBuffer sb ) {
        sb.append( line_chars_interior[ move_west( m_cells[ row ][ col ] )
                | (m_cells[ row ][ col ] & WEST_WALL) ] );
    }

    /**
     * Draw the cell body.
     * <p>
     * This is the cell body char of the cell, based on the next graph:
     * 
     * <pre>
     *        +- - - - - - - -+
     *        |               |
     *        |               |
     *        |               |
     *        |               |
     *        |      (1)      |
     *        |               |
     *        |               |
     *        |               |
     *        |               |
     *        +- - - - - - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, col)}.
     * </p>
     * 
     * @param sb {@link StringBuffer} where we build the {@link #toString()}
     *           result.
     * @see      #line_chars_interior
     */
    private void addCellBodyChar( StringBuffer sb ) {
        sb.append( line_chars_interior[ 0 ] );
    }

    /**
     * This is the row east wall last char of the line.
     * <p>
     * It is printed at the end of the line, based on the next graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |       |       |
     *        |  A    @       |
     *        |  |    @       |
     *        |  |    @       |
     *        | (1)   @       |
     *        |  |    @       |
     *        |  |    @       |
     *        |  +----@---&gt;   |
     *        |       |       |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (row, m_cols-1)}.
     * </p>
     * 
     * @param row is the {@code row} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addCellBodyLastChar( int row, StringBuffer sb ) {
        sb.append(
                line_chars_interior[ move_east( m_cells[ row ][ m_cols - 1 ] )
                        | (m_cells[ row ][ m_cols - 1 ] & EAST_WALL) ] );
    }

    /**
     * This is the row east wall last char of the line.
     * <p>
     * It is printed at the end of the line, based on the next graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |               |
     *        |   &lt;---@--(1)  |
     *        |       @   |   |
     *        |       @   |   |
     *        +       @@@@@   +
     *        |           |   |
     *        |           |   |
     *        |           V   |
     *        |               |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (m_rows-1, 0)}.
     * </p>
     * 
     * @param sb {@link StringBuffer} where we build the {@link #toString()}
     *           result.
     * @see      #line_chars_interior
     */
    private void addLastLineFirstChar( StringBuffer sb ) {
        sb.append( line_chars_interior[ move_south( m_cells[ m_rows - 1 ][ 0 ] )
                | move_west( m_cells[ m_rows - 1 ][ 0 ] ) ] );
    }

    /**
     * This is the row east wall last char of the line.
     * <p>
     * It is printed at the end of the line, based on the next graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |               |
     *        |  (2)  @  (1)  |
     *        |       @   |   |
     *        |       @   |   |
     *        +   @@@@@@@@@   +
     *        |           |   |
     *        |           |   |
     *        |           V   |
     *        |               |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (m_rows-1, col)}.
     * </p>
     * 
     * @param col is the {@code col} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addLastLineInterCellChar( int col, StringBuffer sb ) {
        sb.append(
                line_chars_interior[ move_south( m_cells[ m_rows - 1 ][ col ] )
                        | (m_cells[ m_rows - 1 ][ col - 1 ]
                                & (SOUTH_WALL | EAST_WALL)) ] );
    }

    /**
     * This is the row east wall last char of the line.
     * <p>
     * It is printed at the end of the line, based on the next graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |               |
     *        |   &lt;--(1)--+   |
     *        |           |   |
     *        |           |   |
     *        +   @@@@@@@@@   +
     *        |           |   |
     *        |           |   |
     *        |           V   |
     *        |               |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (m_rows-1, col)}.
     * </p>
     * 
     * @param col is the {@code col} of {@code (1)}.
     * @param sb  {@link StringBuffer} where we build the {@link #toString()}
     *            result.
     * @see       #line_chars_interior
     */
    private void addCellBodyBottomChar( int col, StringBuffer sb ) {
        sb.append(
                line_chars_interior[ move_south( m_cells[ m_rows - 1 ][ col ] )
                        | (m_cells[ m_rows - 1 ][ col ] & (SOUTH_WALL)) ] );
    }

    /**
     * This is the row east wall last char of the line.
     * <p>
     * It is printed at the end of the line, based on the next graph:
     * 
     * <pre>
     *        +- - - -+- - - -+
     *        |               |
     *        |  (1)  @       |
     *        |       @       |
     *        |       @       |
     *        +   @@@@@       +
     *        |               |
     *        |               |
     *        |               |
     *        |               |
     *        +- - - -+- - - -+
     * </pre>
     * 
     * where {@code (1)} is the cell at {@code (m_rows-1, m_cols -1)}.
     * </p>
     * 
     * @param sb {@link StringBuffer} where we build the {@link #toString()}
     *           result.
     * @see      #line_chars_interior
     */
    private void addLastLineLastChar( StringBuffer sb ) {
        sb.append( line_chars_interior[ m_cells[ m_rows - 1 ][ m_cols - 1 ]
                & (SOUTH_WALL | EAST_WALL) ] );
    }

    /**
     * Generates the cell contents of the cell northest to this one.
     * <p>
     * It does it based on this cell's walls.
     * </p>
     * 
     * @param  arg cell contents. Only the direction wall is considered, and it
     *             is moved to the opposite side.
     * @return     cell contents after moving the wall in the direction of the
     *             move to the opposite side.
     */
    private static int move_north( int arg ) {
        return (arg & NORTH_WALL) != 0 ? SOUTH_WALL : 0;
    }

    /**
     * Generates the cell contents of the cell eastest to this one.
     * <p>
     * It does it based on this cell's walls.
     * </p>
     * 
     * @param  arg cell contents. Only the direction wall is considered, and it
     *             is moved to the opposite side.
     * @return     cell contents after moving the wall in the direction of the
     *             move to the opposite side.
     */
    private static int move_east( int arg ) {
        return (arg & EAST_WALL) != 0 ? WEST_WALL : 0;
    }

    /**
     * Generates the cell contents of the cell southest to this one.
     * <p>
     * It does it based on this cell's walls.
     * </p>
     * 
     * @param  arg cell contents. Only the direction wall is considered, and it
     *             is moved to the opposite side.
     * @return     cell contents after moving the wall in the direction of the
     *             move to the opposite side.
     */
    private static int move_south( int arg ) {
        return (arg & SOUTH_WALL) != 0 ? NORTH_WALL : 0;
    }

    /**
     * Generates the cell contents of the cell westest to this one.
     * <p>
     * It does it based on this cell's walls.
     * </p>
     * 
     * @param  arg cell contents. Only the direction wall is considered, and it
     *             is moved to the opposite side.
     * @return     cell contents after moving the wall in the direction of the
     *             move to the opposite side.
     */
    private static int move_west( int arg ) {
        return (arg & WEST_WALL) != 0 ? EAST_WALL : 0;
    }
}
