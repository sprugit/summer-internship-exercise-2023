package com.sprugit.internship.tasks;

import java.util.concurrent.Callable;

public class SubTask implements Callable<int[]> {
	
	private final Object array; //can be int[] or int[][]
	private final SubTaskType t;
	private final int[] params; 
	
	protected SubTask(Object rarray, SubTaskType t, int[] params ) {
		this.array = rarray;
		this.t = t;
		this.params = params;
	}
	
	 /**
	   * Array reversal Function
	   * 
	   * @param array
	   * @return
	   */
	  synchronized private int[] flipArray(int[] array){
		  int[] res = new int[array.length];
		  for(int i = 0; i < array.length; i++) {
			  res[i] = array[array.length-1-i];
		  }
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
		  System.out.println("Break array starting at "+start+", ending at "+end);
		  System.out.println("Array size: "+array.length);
		  int[] res = new int[end-start+1]; 
		  System.out.println("Array size: "+res.length);
		  int ii = 0;
		  for(int i = start; i <= end; i++) {
			  res[ii++] = array[i];
		  }
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
		  System.out.println("Get Column: "+col+", starts at "+start+", ends at "+end);
		  System.out.println("Matrix line size: "+matrix.length);
		  int[] res = new int[end-start];
		  System.out.println("Array size: "+res.length);
		  int ii = 0;
		  for(int i = start; i < end; i++) {
			  res[ii++] = matrix[i][col];  //matrix[y][x]
		  }
		  return res;
	  }
	  
	  /**
	   * Returns the center of a given matrix
	   * 
	   * @param matrix
	   * @return
	   */
	  synchronized private int[] getCenter(int[][] matrix) {
		  int c = matrix.length/2;
		  int[] res = null;
		  if(matrix.length/2 == 0) {
			  //res = new int[4];
			  res = new int[] {1,2,3,4};
		  } else {
			  res = new int[]{matrix[c][c]};
		  } 
		  return res;
	  }

	@Override
	public int[] call() throws Exception {
		int[] result = null;
		switch(this.t) {
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