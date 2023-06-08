package com.premiumminds.internship.snail;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.sprugit.internship.tasks.Task;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {
	
	/**
	 * Method to get snailshell pattern
	 * 
	 * @param matrix matrix of numbers to go through
	 * @return order array of values thar represent a snail shell pattern
	 */
	public Future<int[]> getSnailShell(int[][] matrix) {
		ExecutorService es = Executors.newFixedThreadPool(5);
		try {
			Future<int[]> retval = es.submit(new Task(matrix, es));
			while(!retval.isDone()) {
			}
			return retval;
		} finally {
			es.shutdown();
		}
	};
}