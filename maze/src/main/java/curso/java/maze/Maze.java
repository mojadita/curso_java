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
 * {@link #build(int, int)} method can be called to begin building the maze from
 * specified cell. It uses a depth first recursive algorithm to explore the
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
	public static final byte NORTH_WALL = (1 << 0);
	/**
	 * @see #EAST_WALL
	 */
	public static final byte EAST_WALL = (1 << 1);
	/**
	 * @see #EAST_WALL
	 */
	public static final byte SOUTH_WALL = (1 << 2);
	/**
	 * @see #EAST_WALL
	 */
	public static final byte WEST_WALL = (1 << 3);
	/**
	 * This mark indicates that the cell has been already visited. It can be used to
	 * force the algorithm not to enter into it, so zones in the {@link Maze} can be
	 * avoided.
	 * 
	 * @see #EAST_WALL
	 */
	public static final byte MARKED_CELL = (1 << 4);
	/**
	 * Bit mask with all the walls set. This is the initial mask used in each cell
	 * to begin calculating the random {@link Maze}.
	 * 
	 * @see #EAST_WALL
	 */
	public static final byte ALL_WALLS = NORTH_WALL | EAST_WALL | SOUTH_WALL | WEST_WALL;
	/**
	 * This constant opens the possibility of making more than one row for each
	 * {@link Maze}'s row.
	 * 
	 * Making this constant &gt; zero makes the {@link #toString()} method to draw
	 * {@code CELL_ROWS} extra rows for each {@link Maze}'s row with empty content
	 * and west and east walls.
	 */
	public static final int CELL_ROWS = 0;

	/**
	 * The next table allows to visit the adjacent cells in a random order in an
	 * efficient way. This obeys to a state table in which each direction is marked
	 * once it has been visited. The target is to start with a value of {@code 0}
	 * and grow upto {@code 15} by "filing" bits, according to the random result of
	 * the next possible states.
	 * 
	 * To use this table, you start with a state of {@code 0} and select a random
	 * number based on the number of entries of the cell second level array. The
	 * result of the random selects the next possible state (which represents a bit
	 * mask of the already tried directions) and the activated bit in the mask (that
	 * can be deducted by xor-ing the last state with the actual one) represents the
	 * wall tried. Finally, you get to a state with all masks active, represented by
	 * a {@code null} reference. This table allows for very efficient way to visit
	 * all the directions from a cell in a random order. In case of a 3D maze, we
	 * have to construct a table with two more bits, for the up and down walls.
	 */
	static private final int[][] table = new int[][] { //
			/* 0 */ new int[] { 1, 2, 4, 8 }, /* 1 */ new int[] { 3, 5, 9 }, /* 2 */ new int[] { 3, 6, 10 },
			/* 3 */ new int[] { 7, 11 }, /* 4 */ new int[] { 5, 6, 12 }, /* 5 */ new int[] { 7, 13 },
			/* 6 */ new int[] { 7, 14 }, /* 7 */ new int[] { 15 }, /* 8 */ new int[] { 9, 10, 12 },
			/* 9 */ new int[] { 11, 13 }, /* 10 */ new int[] { 11, 14 }, /* 11 */ new int[] { 15 },
			/* 12 */ new int[] { 13, 14 }, /* 13 */ new int[] { 15 }, /* 14 */ new int[] { 15 }, /* 15 */ null, };

	/**
	 * The array of cells of the maze. This is initialized by the
	 * {@link #init(int, int)} method.
	 */
	private byte[][] cells;
	/**
	 * Number of rows and columns of the exterior rectangle to this {@link Maze}.
	 */
	private int m_rows, m_cols;
	/**
	 * Number of cells that are left to visit.
	 * 
	 * This field is initialised to the product of rows by the number of columns of
	 * the {@link Maze}. After the recursive {@link #build(int, int)} algorithm
	 * finishes, it is left with the final number of unvisited cells. This can be
	 * nonzero, as you can preselect cells so they are not visited by the algorithm.
	 */
	private int m_toVisit;
	/**
	 * {@link Random} instance to take the random decisions.
	 */
	private Random m_random = new Random();

	/**
	 * Initializes maze to an all cells unconnected state.
	 * 
	 * @param rows is the number of rows of the maze.
	 * @param cols is the number of columns in the maze.
	 */
	public void init(int rows, int cols) {
		m_rows = rows;
		m_cols = cols;
		m_toVisit = rows * cols;
		cells = new byte[rows][];
		for (int r = 0; r < rows; r++) {
			cells[r] = new byte[cols];
			for (int c = 0; c < cols; c++)
				cells[r][c] = ALL_WALLS;
		}
	}

	/**
	 * Common part of the {@link #checkEast(int, int)},
	 * {@link #checkNorth(int, int)}, {@link #checkSouth(int, int)} and
	 * {@link #checkWest(int, int)} methods.
	 * 
	 * It follows the algorithm described in the description of the {@link #table}
	 * field to visit the next cell in each possible direction.
	 * 
	 * @param from auxiliar string to describe the direction of the movement made
	 *             (on which direction we are proceeding)
	 * @param row  next cell's row we are visiting now.
	 * @param col  next cell's column we are visiting now.
	 * @see #table
	 */
	private void checkPos(String from, int row, int col) {
		int state = 0;
		while (table[state] != null) {
			int next = table[state][m_random.nextInt(table[state].length)];
			int bit = state ^ next;
			if ((bit & NORTH_WALL) != 0)
				checkNorth(row, col);
			if ((bit & EAST_WALL) != 0)
				checkEast(row, col);
			if ((bit & SOUTH_WALL) != 0)
				checkSouth(row, col);
			if ((bit & WEST_WALL) != 0)
				checkWest(row, col);
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
	public void build(int row, int col) {
		cells[row][col] |= MARKED_CELL;
		m_toVisit--;
		checkPos("build", row, col);
	}

	/**
	 * Recursive call moving to the north.
	 * 
	 * @param row next cell's row.
	 * @param col next cell's column.
	 */
	private void checkNorth(int row, int col) {
		if (row == 0 || (cells[row - 1][col] & MARKED_CELL) != 0 || m_toVisit == 0)
			return; /* unvisitable or already marked */

		cells[row][col] &= ~NORTH_WALL;
		row--;
		cells[row][col] &= ~SOUTH_WALL;
		cells[row][col] |= MARKED_CELL;
		m_toVisit--;
		checkPos("checkNorth", row, col);
	}

	/**
	 * Recursive call moving to the east.
	 * 
	 * @param row next cell's row.
	 * @param col next cell's column.
	 */
	private void checkEast(int row, int col) {
		if (col + 1 == m_cols || (cells[row][col + 1] & MARKED_CELL) != 0 || m_toVisit == 0)
			return; /* unvisitable or already marked */
		cells[row][col] &= ~EAST_WALL;
		col++;
		cells[row][col] &= ~WEST_WALL;
		cells[row][col] |= MARKED_CELL;
		m_toVisit--;
		checkPos("checkEast", row, col);
	}

	/**
	 * Recursive call moving to the south.
	 * 
	 * @param row next cell's row.
	 * @param col next cell's column.
	 */
	private void checkSouth(int row, int col) {
		if (row + 1 == m_rows || (cells[row + 1][col] & MARKED_CELL) != 0 || m_toVisit == 0)
			return; /* unvisitable or already marked */
		cells[row][col] &= ~SOUTH_WALL;
		row++;
		cells[row][col] &= ~NORTH_WALL;
		cells[row][col] |= MARKED_CELL;
		m_toVisit--;
		checkPos("checkSouth", row, col);
	}

	/**
	 * Recursive call moving to the west.
	 * 
	 * @param row next cell's row.
	 * @param col next cell's col.
	 */
	private void checkWest(int row, int col) {
		if (col == 0 || (cells[row][col - 1] & MARKED_CELL) != 0 || m_toVisit == 0)
			return; /* unvisitable or already marked */
		cells[row][col] &= ~WEST_WALL;
		col--;
		cells[row][col] &= ~EAST_WALL;
		cells[row][col] |= MARKED_CELL;
		m_toVisit--;
		checkPos("checkWest", row, col);
	}

	/**
	 * Table of string to select the set of chars to use in the {@link #toString()}
	 * for the column at the common corrner of four cells.
	 * 
	 * The printing algorithm selects a string based on the set of walls common to
	 * cells at (row, col) and (row+1, col+1). This result in a character with walls
	 * emerging in the four cardinal directions: north, south, east and west. The
	 * table contents is the unicode line drawing character corresponding to such a
	 * set of walls. See the code in {@link #toString()} to see how the proper entry
	 * is selected.
	 * 
	 * Below are shown the cells used and the walls selected to build the table
	 * entry in {@link #line_chars} to select the south east column {@link String}
	 * to represent the column and the walls emerging from it.
	 * 
	 * <pre>
	 *        +- - - -+- - - -+
	 *        |       |       |
	 *          this       
	 *          cell >|
	 *        |   vv >|       |
	 *        +- -====+====- -+
	 *        |       |< ^^   |
	 *                |< and
	 *                   this
	 *        |       |  one  |
	 *        +- - - -+- - - -+
	 * </pre>
	 */
	private static final String[] line_chars = {
			// NORTH
			// NORTH EAST EAST
			"·"/* */, "\u2576", "\u2575", "\u2514", //
			"\u2574", "\u2500", "\u2518", "\u2534", // SOUTH
			"\u2577", "\u250c", "\u2502", "\u251c", // WEST
			"\u2510", "\u252c", "\u2524", "\u253c" // SOUTH WEST
	};

	/**
	 * Overriden {@link Object#toString()} method.
	 * 
	 * The {@link String} representation of a maze consists in a unicode
	 * representation of the maze made with line drawing characters. As those
	 * characters join at middle line points and center at joining corners, we must
	 * derive the line drawing representation by using sets of adjacent cells.
	 * 
	 * Beware that some fonts don't implement the full set of linedrawing characters
	 * and some may get substituted if you try to print them on a device that has no
	 * character representation of some of these (mainly the four lines that singly
	 * end in the center of the character from one side of it) While we have made
	 * our best effort in using a small subset of those, we cannot assume them all
	 * will be available when the user tries to represent them on his/her output
	 * device.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		/* first row, upper part of first maze's row. */
		sb.append("\u250c");
		for (int c = 0; c < m_cols - 1; c++) {
			sb.append((cells[0][c] & EAST_WALL) != 0 //
					? "\u2500\u252c" // T shaped symbol
					: "\u2500\u2500"); // horizontal line
		}
		sb.append("\u2500\u2510\n"); // uper right corner.

		/* inter middle rows column row */
		for (int r = 0; r < m_rows - 1; r++) {
			/* if we are printing more than one line per row */
			for (int l = 0; l < CELL_ROWS; l++) {
				sb.append(" \u2502"); // vertical line
				for (int c = 0; c < m_cols; c++) {
					sb.append((cells[r][c] & EAST_WALL) != 0 //
							? " \u2502" // east wall vertical line
							: "  "); // no east wall
				}
				sb.append("\n"); // end of line.
			}
			// west side wall.
			sb.append((cells[r][0] & SOUTH_WALL) != 0 //
					? "\u251c" // Left vertical wall with horizontal wall emerging to the right.
					: "\u2502"); // left vertical wall with no horizontal wall emerging from it.
			// southern side horizontal wall and southern/east corner column (with walls
			// emerging from it)
			for (int c = 0; c < m_cols - 1; c++) {
				int val = (cells[r][c] & (EAST_WALL | SOUTH_WALL)) | (cells[r + 1][c + 1] & (WEST_WALL | NORTH_WALL));
				sb.append((cells[r][c] & SOUTH_WALL) != 0 //
						? "\u2500" // horizontal southern wall
						: " ") // no southern wall.
						.append(line_chars[val]); // southern east corner column (with half walls emerging)
			}
			// southern east corner column.
			sb.append((cells[r][m_cols - 1] & SOUTH_WALL) != 0 //
					? "\u2500\u2524\n" // T rotated to the right.
					: " \u2502\n"); // vertical bar.
		}
		// bottom size of Maze.
		sb.append("\u2514"); // bottom left corner.
		for (int c = 0; c < m_cols - 1; c++)
			sb.append((cells[m_rows - 1][c] & EAST_WALL) != 0 //
					? "\u2500\u2534" // inverted T.
					: "\u2500\u2500"); // just horizontal bar.
		sb.append("\u2500\u2518\n"); // bottom right corner.

		return sb.toString();
	}
}
