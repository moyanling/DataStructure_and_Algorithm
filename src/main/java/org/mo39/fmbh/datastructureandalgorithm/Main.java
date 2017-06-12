package org.mo39.fmbh.datastructureandalgorithm;

import java.time.LocalTime;
import java.util.Random;

import org.mo39.fmbh.common.Z;

public class Main {

  public static void main(String[] args) {
    int length = 100000;
    Random rand = new Random();
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = rand.nextInt(length);
    }
    Z.print(LocalTime.now());
    for (int i = 0; i < arr.length; i++) {
      int j = i;
      int key = -1;
      // Find the smallest key starts from i to the end of arr.
      for (; j < arr.length; j++) {
        if (key == -1 || arr[key] > arr[j]) key = j;
      }
      Z.swap(arr, i, key);
    }
    // Arrays.sort(arr);
    Z.print(LocalTime.now());
  }

}
