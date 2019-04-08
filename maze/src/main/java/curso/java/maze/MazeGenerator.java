/* Name: MazeGenerator.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 6 abr. 2019 21:19:42
 * Project: maze
 * Package: curso.java.maze
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package curso.java.maze;

import java.util.Random;

/**
 * Maze generator.
 * 
 * This class has a {@code public static void} {@link #main(String[])} routine
 * to create a {@link Maze} of sizes passed as parameters from the command line.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class MazeGenerator {

	/**
	 * Default rows and columns in case they are not specified as arguments to
	 * {@link #main(String[])}.
	 */
	private static final int DEFAULT_ROWS = 10, DEFAULT_COLS = 15;

	/**
	 * Main program. Generates a maze and prints it.
	 * 
	 * @param args the number of rows and columns of the {@link Maze} to be created.
	 */
	public static void main(String[] args) {
		int rows = DEFAULT_ROWS, cols = DEFAULT_COLS;

		switch (args.length) {
		case 2:
			rows = Integer.parseInt(args[0]);
			cols = Integer.parseInt(args[1]);
			break;
		case 1:
			rows = cols = Integer.parseInt(args[0]);
			break;
		}

		Maze maze = new Maze();
		maze.init(rows, cols);
		Random r = new Random();
		maze.build(r.nextInt(rows), r.nextInt(cols));
		System.out.println(maze);
	}
}
