package org.mo39.fmbh.datastructure.array;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.INTRODUCTION_TO_ALGORITHM;
import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * Shuffle an array randomly.
 *
 * @see <a href="https://leetcode.com/problems/shuffle-an-array/">Shuffle an Array</a>
 * 
 * @author Jihan Chen
 *
 */
@ProblemSource({LEETCODE, INTRODUCTION_TO_ALGORITHM})
public enum ArrayShuffle {

  /**
   * This is using a randomly generated number as a comparator. Not sure if all elements have equal
   * possibility.//TODO
   * 
   */
  PERMUTE_BY_SORTING() {

    @Override
    public <T> void shuffle(T[] arr) {
      int bound = (int) Math.pow(arr.length, 3);
      Arrays.sort(arr, (o1, o2) -> rand.nextInt(bound) - rand.nextInt(bound));
      // @SuppressWarnings("unchecked")
      // Value<T>[] newArr = new Value[arr.length];
      // for (int i = 0; i < arr.length; i++) {
      // newArr[i] = new Value<T>(arr[i], rand.nextInt(bound));
      // }
      // Arrays.sort(newArr);
      // for (int i = 0; i < arr.length; i++) {
      // arr[i] = newArr[i].value;
      // }
    }

  },

  RANDOMIZE_IN_PLACE() {

    @Override
    public <T> void shuffle(T[] arr) {
      for (int i = 0; i < arr.length; i++) {
        int j = rand.nextInt(arr.length - i) + i;
        Z.swap(arr, i, j);
      }
    }

  };

  private static Random rand = new Random();

  public abstract <T> void shuffle(T[] arr);

  public static class TestArrayShuffle {

    private Integer[] arr = new TestData().integerArr;
    private Integer[] sortedArr = new TestData().integerArr;

    @Before
    public void sort() {
      Arrays.sort(sortedArr);
    }

    @Test
    public void testPermuteBySorting() {
      ArrayShuffle.PERMUTE_BY_SORTING.shuffle(arr);
      Arrays.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void testRandomizeInPlace() {
      ArrayShuffle.RANDOMIZE_IN_PLACE.shuffle(arr);
      Arrays.sort(arr);
      Assert.assertArrayEquals(sortedArr, arr);
    }

  }

}
