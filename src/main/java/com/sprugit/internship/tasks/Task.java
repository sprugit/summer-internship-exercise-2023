package com.sprugit.internship.tasks;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Task implements Callable<int[]>{
	
	private final int[][] matrix;
	private final ExecutorService es;
	
	public Task(int[][] matrix, ExecutorService es) {
		this.matrix = matrix;
		this.es = es;
	}

	@Override
	public int[] call() throws Exception {
		int N = matrix.length, rindex = 0, limit = N%2 == 0 ? (N-1)/2 : N/2 ;
		int[] result = new int[N*N];
		System.out.println(result.length);
		ArrayList<Future<int[]>> lines = new ArrayList<Future<int[]>>();
		for(int i = 0; i < limit; i++) {
			System.out.println(i);
			System.out.println(limit); //ERRO É AQUI
			lines.add(es.submit(new SubTask(matrix[i], SubTaskType.LINE, new int[]{i,N-1-i}))); //get upper line (line i)
			lines.add(es.submit(new SubTask(matrix, SubTaskType.COL, new int[]{N-1-i,i+1,N-(i+1)}))); //get right column (column N-i)
			lines.add(es.submit(new SubTask(matrix[N-1-i], SubTaskType.REVLINE, new int[]{i,N-1-i}))); //get lower line (line N-i)
			lines.add(es.submit(new SubTask(matrix, SubTaskType.REVCOL, new int[]{i,i+1,N-(i+1)}))); //get left column (column i)
			System.out.println("prefor");
			while(lines.size() > 0) {
				for(int crcl : lines.remove(0).get()) {
					result[rindex++] = crcl;
					String out = "Current state:";
					for(int r : result) {
						out += r+",";
						System.out.println(out);
					}
				}
			}
		}
		for(int crcl2 : es.submit(new SubTask(matrix, SubTaskType.CENTER, null)).get()) {
			System.out.println(crcl2);
			result[rindex++] = crcl2;
		}
		return result;
	}	
}