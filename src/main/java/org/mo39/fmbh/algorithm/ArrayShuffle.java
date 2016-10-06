package org.mo39.fmbh.algorithm;

import java.util.Arrays;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mo39.fmbh.alogrithm.sort.SortingAlgorithms;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

public enum ArrayShuffle {

  PERMUTE_BY_SORTING() {

    class ValueWithPriority implements Comparable<ValueWithPriority> {
      public final int value;
      public final int priority;

      public ValueWithPriority(int value, int priority) {
        this.value = value;
        this.priority = priority;
      }

      @Override
      public int compareTo(ValueWithPriority o) {
        return this.priority - o.priority;
      }
    }

    private Random rand = new Random();

    @Override
    public void shuffle(int[] arr) {
      ValueWithPriority[] newArr = new ValueWithPriority[arr.length];
      for (int i = 0; i < arr.length; i++) {
        newArr[i] = new ValueWithPriority(arr[i], rand.nextInt((int) Math.pow(arr.length, 3)));
      }
      SortingAlgorithms.MERGE_SORT.sort(newArr);
      for (int i = 0; i < arr.length; i++) {
        arr[i] = newArr[i].value;
      }
    }



  },

  RANDOMIZE_IN_PLACE() {

    @Override
    public void shuffle(int[] arr) {}

  };

  public abstract void shuffle(int[] arr);

  public static class TestArrayShuffle {

    private int[] arr = new TestData().intArr;

    @BeforeClass
    public static void before() {
      TestArrayShuffle test = new TestArrayShuffle();
      Z.print("Original array : " + Arrays.toString(test.arr));
    }

    @Test
    public void testPermuteBySorting() {
      ArrayShuffle.PERMUTE_BY_SORTING.shuffle(arr);
      Z.print("Shuffled array : " + Arrays.toString(arr));
    }

  }

}
