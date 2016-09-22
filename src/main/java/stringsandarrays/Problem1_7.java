package stringsandarrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import utils.TestData;

public class Problem1_7 {

  public static void main(String[] args) {
    int[][] matrix = new TestData().matrix1;
    for (int[] arr : matrix) {
      System.out.println(Arrays.toString(arr));
    }
    new Problem1_7().setZerosInMatrix(matrix);
    System.out.println();
    for (int[] arr : matrix) {
      System.out.println(Arrays.toString(arr));
    }
  }

  public void setZerosInMatrix(int[][] matrix) {
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

  public void setColAndRow(int[][] matrix, int row, int col) {
    for (int i = 0; i < matrix.length; i++)
      matrix[i][col] = 0;
    for (int j = 0; j < matrix[0].length; j++)
      matrix[row][j] = 0;
  }

}
