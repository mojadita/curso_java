/* 
 * @formatter:off 
 *       Name: MazeGenerator.java 
 *     Author: Luis Colorado <luiscoloradourcola@gmail.com> 
 *       Date: 6 abr. 2019 21:19:42 
 *    Project: maze
 *    Package: curso.java.maze Copyright: (C) 2019 Luis Colorado. All rights reserved.
 * @formatter:on 
 */
package curso.java.maze;

/**
 * Maze generator.
 * <p>
 * This class has a {@code public static void} {@link #main(String[])} routine
 * to create a {@link RandomRecursiveDeepFirstMaze} of sizes passed as
 * parameters from the command line.
 * </p>
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 */
public class MazeGenerator {

    /**
     * Default rows in case they are not specified as arguments to
     * {@link #main(String[])}.
     */
    private static final int DEFAULT_ROWS = 30;
    /**
     * Default cols in case they are not specified as arguments to
     * {@link #main(String[])}.
     */
    private static final int DEFAULT_COLS = 40;

    /**
     * Main program. Generates a maze and prints it.
     * 
     * @param args the number of rows and columns of the
     *             {@link RandomRecursiveDeepFirstMaze} to be created.
     */
    public static void main( String[] args ) {
        int rows = DEFAULT_ROWS, cols = DEFAULT_COLS;

        switch ( args.length ) {
        case 2:
            rows = Integer.parseInt( args[ 0 ] );
            cols = Integer.parseInt( args[ 1 ] );
            break;
        case 1:
            rows = cols = Integer.parseInt( args[ 0 ] );
            break;
        }

        RandomRecursiveDeepFirstMaze maze = new RandomRecursiveDeepFirstMaze();
        maze.init( rows, cols );

        maze.setVisited( 0, 0, 6, 6, true );
        maze.setVisited( rows / 2 - 3, 0, rows / 2 + 3, 6, true );
        //maze.setVisited( rows - 6, 0, rows, 6, true );

        maze.setVisited( 0, cols / 2 - 3, 6, cols / 2 + 3, true );
        maze.setVisited( rows / 2 - 8, cols / 2 - 8, rows / 2 + 8, cols / 2 + 8,
                true );
        maze.setVisited( rows - 6, cols / 2 - 3, rows, cols / 2 + 3, true );

        //maze.setVisited( 0, cols - 6, 6, cols, true );
        maze.setVisited( rows / 2 - 3, cols - 6, rows / 2 + 3, cols, true );
        maze.setVisited( rows - 6, cols - 6, rows, cols, true );

        maze.normalizeMarkedCells();
        maze.buildFrom( 6, 6 );
        //maze.setCellCols( 5 );
        //maze.setCellRows( 2 );
        maze.destroyWallAt( 5, 5, Maze.EAST_WALL );
        maze.destroyWallAt( rows - 6, cols - 6, Maze.WEST_WALL );

        System.out.println( maze );
    }
}

