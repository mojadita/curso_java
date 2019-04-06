/*      Name: MazeGenerator.java
 *    Author: Luis Colorado <luiscoloradourcola@gmail.com>
 *      Date: 6 abr. 2019 21:19:42
 *   Project: maze
 *   Package: curso.java.maze
 * Copyright: (C) 2019 Luis Colorado.  All rights reserved.
 */
package curso.java.maze;


/**
 * Maze generator.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class MazeGenerator {

    /**
     * Main program.  Generates a maze and prints it.
     * @param args
     */
    public static void main( String[] args ) {
        Maze maze = new Maze();
        maze.init( 8, 8 );
        maze.build( 3, 4 );
        System.out.println(maze);
    }

}
