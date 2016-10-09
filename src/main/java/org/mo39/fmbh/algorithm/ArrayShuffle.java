package org.mo39.fmbh.algorithm;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mo39.fmbh.algorithm.sort.ComparisonSort;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Shuffle an array randomly.
 * 
 * @author Jihan Chen
 *
 */
public enum ArrayShuffle {

  PERMUTE_BY_SORTING() {

    /**
     * A value that has a priority.
     * 
     * @author Jihan Chen
     *
     */
    class Value implements Comparable<Value> {
      public final int value;
      public final int priority;

      public Value(int value, int priority) {
        this.value = value;
        this.priority = priority;
      }

      @Override
      public int compareTo(Value o) {
        return this.priority - o.priority;
      }
    }

    private Random rand = new Random();
    private int bound;

    @Override
    public void shuffle(int[] arr) {
      bound = (int) Math.pow(arr.length, 3);
      Value[] newArr = new Value[arr.length];
      for (int i = 0; i < arr.length; i++) {
        newArr[i] = new Value(arr[i], rand.nextInt(bound));
      }
      ComparisonSort.MERGE_SORT.sort(newArr);
      for (int i = 0; i < arr.length; i++) {
        arr[i] = newArr[i].value;
      }
    }

  },

  RANDOMIZE_IN_PLACE() {

    private Random random = new Random();

    @Override
    public void shuffle(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
        int j = random.nextInt(arr.length - i) + i;
        if (i != j) {
          arr[i] += arr[j];
          arr[j] = arr[i] - arr[j];
          arr[i] -= arr[j];
        }
      }
    }

  };

  public abstract void shuffle(int[] arr);

  public static class TestArrayShuffle {

    private int[] arr = new TestData().intArr;
    private int[] sortedArr = new TestData().intArr;

    @BeforeClass
    public static void before() {
      TestArrayShuffle test = new TestArrayShuffle();
      Z.print("Original array : " + Arrays.toString(test.arr));
    }

    @Before
    public void sort() {
      Arrays.sort(sortedArr);
    }

    @Test
    public void testPermuteBySorting() {
      ArrayShuffle.PERMUTE_BY_SORTING.shuffle(arr);
      Z.print("Shuffled array : " + Arrays.toString(arr));
      Arrays.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testRandomizeInPlace() {
      ArrayShuffle.RANDOMIZE_IN_PLACE.shuffle(arr);
      Z.print("Shuffled array : " + Arrays.toString(arr));
      Arrays.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

  }

}
