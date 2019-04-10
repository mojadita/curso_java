/* Name: MazeIface.java Author: Luis Colorado <lcu@basen.net> Date: 10 Apr 2019
 * 9.39.27 Project: maze Package: curso.java.maze Copyright: (C) 2019 Luis
 * Colorado. All rights reserved. */
package curso.java.maze;


/**
 * Interface for a {@link Maze}.
 * <p>
 * This interface defines the interface for a read-only maze. It allows to get
 * the cell contents and see how you can move along of it.
 * </p>
 * 
 * @author Luis Colorado {@code <lcu@basen.net>}
 */
public interface Maze {

    /**
     * Bit mask corresponding to the presence or absence of a north wall in this
     * cell.
     */
    byte NORTH_WALL  = (1 << 0);
    /**
     * @see #EAST_WALL
     */
    byte EAST_WALL   = (1 << 1);
    /**
     * @see #EAST_WALL
     */
    byte SOUTH_WALL  = (1 << 2);
    /**
     * @see #EAST_WALL
     */
    byte WEST_WALL   = (1 << 3);
    /**
     * This mark indicates that the cell has been already visited. It can be
     * used to force the algorithm not to enter into it, so zones in the
     * {@link RandomRecursiveDeepFirstMaze} can be avoided.
     * 
     * @see #EAST_WALL
     */
    byte VISITED_CELL = (1 << 4);
    /**
     * Bit mask with all the walls set. This is the initial mask used in each
     * cell to begin calculating the random
     * {@link RandomRecursiveDeepFirstMaze}.
     * 
     * @see #EAST_WALL
     */
    byte ALL_WALLS   = NORTH_WALL | EAST_WALL | SOUTH_WALL | WEST_WALL;

    /**
     * Getter for cell at {@code row} and {@code col}.
     * 
     * @param  row row of the cell we want.
     * @param  col column of the cell we want.
     * @return     the value of the cell at that point.
     */
    byte getCellAt( int row, int col );

    /**
     * Getter for the {@code rows} attribute.
     * 
     * @return the {@code int} value of the {@code rows} attribute.
     */
    int getRows();

    /**
     * Getter for the {@code int} {@code cols} attribute.
     * 
     * @return the {@code int} value of the {@code cols} attribute.
     */
    int getCols();

}
