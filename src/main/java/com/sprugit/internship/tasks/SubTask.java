package com.sprugit.internship.tasks;

import java.util.concurrent.Callable;

public class SubTask implements Callable<int[]> {

	private final Object array; // can be int[] or int[][]
	private final SubTaskType t;
	private final int[] params;

	protected SubTask(Object rarray, SubTaskType t, int[] params) {
		this.array = rarray;
		this.t = t;
		this.params = params;
	}

	/**
	 * Prints an Array. Useful for debugging
	 * 
	 * @param array
	 */
	synchronized private void printArray(int[] array) {
		String result = "Resulting Array:";
		for (int i : array) {
			result += String.valueOf(i);
		}
		System.out.println(result);
	}

	/**
	 * Array reversal Function
	 * 
	 * @param array
	 * @return
	 */
	synchronized private int[] flipArray(int[] array) {
		System.out.println("Flipping Array");
		int[] res = new int[array.length];
		for (int i = 0; i <= array.length / 2; i++) {
			res[i] = array[array.length - 1 - i];
			res[array.length - 1 - i] = array[i];
		}
		printArray(res);
		return res;
	}

	/**
	 * Splits an Array
	 * 
	 * @param array
	 * @param start
	 * @param end
	 * @return
	 */
	synchronized private int[] subArray(int[] array, int start, int end) {
		System.out.println("Break array starting at " + start + ", ending at " + end);
		System.out.println("Array size: " + array.length);
		int[] res = new int[end - start + 1];
		System.out.println("Array size: " + res.length);
		int ii = 0;
		for (int i = start; i <= end; i++) {
			res[ii++] = array[i];
		}
		printArray(res);
		return res;
	}

	/**
	 * Returns a column of a matrix as an array
	 * 
	 * @param matrix
	 * @param col
	 * @param start
	 * @param end
	 * @return
	 */
	synchronized private int[] getColumn(int[][] matrix, int col, int start, int end) {
		System.out.println("Get Column: " + col + ", starts at " + start + ", ends at " + end);
		System.out.println("Matrix line size: " + matrix.length);
		int[] res = new int[end - start];
		System.out.println("Array size: " + res.length);
		int ii = 0;
		for (int i = start; i < end; i++) {
			res[ii++] = matrix[i][col]; // matrix[y][x]
		}
		printArray(res);
		return res;
	}

	/**
	 * Returns the center of a given matrix
	 * 
	 * @param matrix
	 * @return
	 */
	synchronized private int[] getCenter(int[][] matrix) {
		System.out.println("Getting the Center");
		int i = 0, N = matrix.length / 2;
		int[] res = null;
		if (matrix.length % 2 == 0) {
			res = new int[4];
			for (int[] vec : new int[][] { { -1, -1 }, { -1, 0 }, { 0, 0 }, { 0, -1 } }) { // reminder matrix[y][x]
				int y = vec[0], x = vec[1];
				System.out.println("Em "+(N+y)+","+(N+x)+" temos "+matrix[N + y][N + x]);
				res[i++] = matrix[N + y][N + x];
			}
		} else {
			res = new int[] { matrix[N][N] };
		}
		printArray(res);
		return res;
	}

	@Override
	public int[] call() throws Exception {
		int[] result = null;
		switch (this.t) {
		case LINE:
			System.out.println("Getting line");
			result = subArray((int[]) array, params[0], params[1]);
			break;
		case COL:
			System.out.println("Getting column");
			result = getColumn((int[][]) array, params[0], params[1], params[2]);
			break;
		case REVLINE:
			System.out.println("Getting flipped line");
			result = flipArray(subArray((int[]) array, params[0], params[1]));
			break;
		case REVCOL:
			System.out.println("Getting column");
			result = flipArray(getColumn((int[][]) array, params[0], params[1], params[2]));
			break;
		case CENTER:
			result = getCenter((int[][]) array);
			break;
		}
		return result;
	}
}