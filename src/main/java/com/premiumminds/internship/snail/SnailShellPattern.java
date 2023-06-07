package com.premiumminds.internship.snail;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {
	
  public static void main(String[] args) throws InterruptedException, ExecutionException {
	  for(int i : (new SnailShellPattern().getSnailShell(new int[][]{{1,2},{2,3}}).get())) {
		  System.out.println(i);
	  }
  }

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    int N = matrix.length;
    ExecutorService es = Executors.newFixedThreadPool(5);
    return es.submit(() -> { //replace with not lambda
    	while(false);// Calcular 
    	return new int[] {1,2,3};
    });
  };
  
  /**
   * 
   * @param array
   * @return
   */
  private int[] flipArray(int[] array){
	  int[] res = new int[array.length];
	  for(int i = 0; i < array.length; i++) {
		  res[i] = array[array.length-i];
	  }
	  return res;
  }
  
  /**
   * 
   * @param array
   * @param start
   * @param end
   * @return
   */
  private int[] subArray(int[] array, int start, int end) {
	  int[] res = new int[end-start+1]; 
	  int ii = 0;
	  for(int i = start; i <= end; i++) {
		  res[ii++] = array[i];
	  }
	  return res;
  }
  
  /**
   * 
   * @param matrix
   * @param col
   * @param start
   * @param end
   * @return
   */
  private int[] getColumn(int[][] matrix, int col, int start, int end) {
	  int[] res = new int[end-start+1];
	  int ii = 0;
	  for(int i = start; i <= end; i++) {
		  res[ii++] = matrix[i][col];  //matrix[y][x]
	  }
	  return res;
  }
}