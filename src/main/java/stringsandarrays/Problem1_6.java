package stringsandarrays;

import java.util.Arrays;

public class Problem1_6 {

  public static void main(String[] args) {
    int[][] matrix = new int[][] {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 15, 16, 6}, {10, 9, 8, 7}};
    for (int[] arr : matrix) {
      System.out.println(Arrays.toString(arr));
    }
    new Problem1_6().rotate(matrix);
    System.out.println();
    for (int[] arr : matrix) {
      System.out.println(Arrays.toString(arr));
    }

  }

  public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int layer = 0; layer < n / 2; layer++) {
      for (int i = layer; i < n - layer - 1; i++) {
        // save top
        int temp = matrix[layer][i];
        // move left to top
        matrix[layer][i] = matrix[n - 1 - i][layer];
        // move bottom to left
        matrix[n - 1 - i][layer] = matrix[n - 1 - layer][n - 1 - i];
        // move right to bottom
        matrix[n - 1 - layer][n - 1 - i] = matrix[i][n - 1 - layer];
        // move temp to right;
        matrix[i][n - 1 - layer] = temp;
      }
    }
  }

}
