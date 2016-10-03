package org.mo39.fmbh.datastructure.stringsandarrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mo39.fmbh.common.TestData;

public class Problem1_7 {

  public static void main(String[] args) {
    int[][] matrix = new TestData().matrix1;
    for (int[] arr : matrix) {
      System.out.println(Arrays.toString(arr));
    }
    new Problem1_7().setZerosInMatrix1(matrix);
    System.out.println();
    for (int[] arr : matrix) {
      System.out.println(Arrays.toString(arr));
    }
  }

  /**
   * This one is slightly better than the one given by the book.
   * 
   * @param matrix
   */
  public void setZerosInMatrix0(int[][] matrix) {
    Set<Integer> colSet = new HashSet<>();
    int i = 0;
    while (i < matrix.length) {
      int j = 0;
      while (j < matrix[0].length) {
        if (i >= matrix.length) break;
        if (!colSet.contains(j)) {
          if (matrix[i][j] == 0) {
            setColAndRow(matrix, i, j);
            colSet.add(j);
            j = 0;
            i++;
            continue;
          }
        }
        j++;
      }
      i++;
    }
  }

  private void setColAndRow(int[][] matrix, int row, int col) {
    for (int i = 0; i < matrix.length; i++)
      matrix[i][col] = 0;
    for (int j = 0; j < matrix[0].length; j++)
      matrix[row][j] = 0;
  }

  /**
   * This one goes over the matrix twice. but it's fairly neat and clean.
   * 
   * @param matrix
   */
  public void setZerosInMatrix1(int[][] matrix) {
    boolean[] row = new boolean[matrix.length];
    boolean[] col = new boolean[matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          row[i] = true;
          col[j] = true;
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (row[i] || col[j]) matrix[i][j] = 0;
      }
    }
  }



}
