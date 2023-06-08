package com.premiumminds.internship.snail;

import static org.junit.Assert.assertArrayEquals;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2023.
 */
@RunWith(JUnit4.class)
public class SnailShellPatternTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public SnailShellPatternTest() {
  };
  
  /**
   * Sub-function that generates a result and compares it the expected result
   * 
   * @param matrix
   * @param expected
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws TimeoutException
   */
  private void test(int[][] matrix, int[] expected) throws InterruptedException, ExecutionException, TimeoutException {
	  int[] result = new SnailShellPattern().getSnailShell(matrix).get(10, TimeUnit.SECONDS);
	  assertArrayEquals(result, expected);
  }
  
  /**
   * Silly Test 1 : Testing for a 1 by 1 matrix
   * 
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws TimeoutException
   */
  @Test
  public void Silly1X1() throws InterruptedException, ExecutionException, TimeoutException {
	  int[][] matrix = { {1} };
	  int[] expected = {1};
	  test(matrix,expected);
  }
  
  /**
   * Silly Test 2 : Testing for a 2 by 2 matrix
   * 
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws TimeoutException
   */
  @Test
  public void Silly2X2() throws InterruptedException, ExecutionException, TimeoutException {
	  int[][] matrix = { {1,2},{3,4} };
	  int[] expected = {1,2,4,3};
	  test(matrix,expected);
  }
  
  @Test
  /**
   * Unit test for the first git example
   * 
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws TimeoutException
   */
  public void TesteExemplo1() 
		  throws InterruptedException, ExecutionException, TimeoutException {
	  int[][] matrix = { {1,2,3} , {4,5,6} , {7,8,9} };
	  int[] expected = {1,2,3,6,9,8,7,4,5};
	  test(matrix,expected);
  }
  
  @Test
  /**
   * Unit test for the second git example
   * 
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws TimeoutException
   */
  public void TesteExemplo2() 
		  throws InterruptedException, ExecutionException, TimeoutException {
	  int[][] matrix = { {1,2,3,1},{4,5,6,4},{7,8,9,7},{7,8,9,7} };
	  int[] expected = {1,2,3,1,4,7,7,9,8,7,7,4,5,6,9,8};
	  test(matrix,expected);
  }

  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    assertArrayEquals(result, expected);
  }
  
  @Test
  public void Matriz5X5Test() throws InterruptedException, ExecutionException, TimeoutException {
	  int[][] matrix = { {1,2,3,2,1} , {4,5,6,5,4} , {7,8,9,8,7} , {4,5,6,5,4}, {1,2,3,2,1} };
	  int[] expected = {1,2,3,2,1,4,7,4,1,2,3,2,1,4,7,4,5,6,5,8,5,6,5,8,9};
	  test(matrix,expected);
  }
  
  @Test
  public void Matriz6x6Test() throws InterruptedException, ExecutionException, TimeoutException {
	  int[][] matrix = { {1,2,3,2,1,0} , {4,5,6,5,4,3} , {7,8,9,8,7,6} , {4,5,6,5,4,3}, {1,2,3,2,1,0}, {4,5,6,5,4,3}};
	  int[] expected = {1,2,3,2,1,0,3,6,3,0,3,4,5,6,5,4,1,4,7,4,5,6,5,4,7,4,1,2,3,2,5,8,9,8,5,6};
	  test(matrix,expected);
  } 
}