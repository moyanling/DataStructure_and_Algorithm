package org.mo39.fmbh.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * Find the contiguous subarray within a one-dimensional array of numbers which
 * has the largest sum. Return a 3 length arr with first two as indexes and the
 * third element as the maximum sum.
 * 
 * @author Jihan Chen
 *
 */
public enum MaximumSubArray {

	/**
	 * This is a way to solve maximum sub array using brute force. It's
	 * converted to problem "Best time to buy and sell stock". This problem is
	 * cited as below
	 * <p>
	 * Given an int arr presenting the price of a stock. Find a day to buy the
	 * stock and a day to sell the stock so that one can earn most money (the
	 * difference is the largest).
	 * <p>
	 * This problem can be converted to MaximumSubArray:<br>
	 * Consider the difference between each day, then find the MaximumSubArray
	 * of these differences.
	 * <p>
	 * Take the combination of two elements in arr and take the largest diff.
	 * <br>
	 * The time complexity is <b>O(n^2)</b>
	 * 
	 * @author Jihan Chen
	 * 
	 */
	BRUTE_FORCE() {

		private Integer largestDiff;
		private int[] result = new int[3];

		@Override
		public int[] solve(int[] arr) {
			int[] newArr = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				newArr[i] = (i == 0 ? 0 : newArr[i - 1]) + arr[i];
			}
			Z.print(Arrays.toString(newArr));
			for (int i = 0; i < newArr.length - 1; i++) {
				for (int j = i + 1; j < newArr.length; j++) {
					if (largestDiff == null || newArr[j] - newArr[i] > largestDiff) {
						largestDiff = newArr[j] - newArr[i];
						result[0] = i;
						result[1] = j;
						result[2] = largestDiff;
					}
				}
			}
			result[0]++;
			return result;
		}

	},

	/**
	 * Divide and conquer.<br>
	 * There are three sub cases for this problem after division. The maximum
	 * sum is at the left part of division. The maximum sum is at the right part
	 * of division. Or the maximum sum goes across the mid. The first two cases
	 * can be solved using recursion. And the third case needs a helper
	 * function.
	 * <p>
	 * It takes log(n) to split and finish each recursion. And in each
	 * recursion, it takes O(n) to find the maximum sum across mid. So the
	 * overall time complexity is <b>nlog(n)</b>.
	 * 
	 * @param stockDiff
	 * @param low
	 * @param high
	 * @return
	 */
	DIVIDE_AND_CONQUER() {

		@Override
		public int[] solve(int[] stockDiff) {
			return findMaximumSubArr(stockDiff, 0, stockDiff.length - 1);
		}

		private int[] findMaximumSubArr(int[] arr, int low, int high) {
			if (low == high) return new int[] { low, high, arr[low] };
			int mid = (low + high) / 2;
			int[] cross = findMaximumSubArrCrossMid(arr, low, mid, high);
			int[] left = findMaximumSubArr(arr, low, mid);
			int[] right = findMaximumSubArr(arr, mid + 1, high);
			if (cross[2] >= left[2] && cross[2] >= right[2]) return cross;
			else if (left[2] >= cross[2] && left[2] >= right[2]) return left;
			return right;
		}

		/**
		 * Helper function that calculates the maximum sum that goes across the
		 * mid. The time complexity for this process is <b>O(n)</b>.
		 * 
		 * @param stockDiff
		 * @param low
		 * @param mid
		 * @param high
		 * @return
		 */
		private int[] findMaximumSubArrCrossMid(int[] arr, int low, int mid, int high) {
			int left = 0;
			int currLeftSum = 0;
			Integer leftSum = null;
			for (int i = mid; i >= low; i--) {
				currLeftSum += arr[i];
				if (leftSum == null || leftSum < currLeftSum) {
					left = i;
					leftSum = currLeftSum;
				}
			}
			int right = -1;
			int currRightSum = 0;
			Integer rightSum = null;
			for (int i = mid + 1; i <= high; i++) {
				currRightSum += arr[i];
				if (rightSum == null || rightSum < currRightSum) {
					right = i;
					rightSum = currRightSum;
				}
			}
			return new int[] { left, right, leftSum + rightSum };
		}

	},

	LINEAR_SOLUTION() {

		@Override
		public int[] solve(int[] arr) {
			Map<Integer, Integer[]> map = new HashMap<>();
			/*
			 * If want to find a index that satisfies a condition, use ++i
			 * instead of i++.
			 */
			int firstPositive = -1;
			while (arr[++firstPositive] <= 0) {
			}
			map.put(firstPositive, new Integer[] { firstPositive, arr[firstPositive] });
			for (int index = firstPositive + 1; index < arr.length; index++) {
				Integer maximum = null;
				for (int i = map.get(index - 1)[0]; i <= index; i++) {
					// if (maximum == null || maximum < )
				}
			}

			return null;
		}

	};

	/**
	 * Method declaration shared by all instances.
	 * 
	 * @param stock
	 * @return
	 */
	public abstract int[] solve(int[] arr);

	/**
	 * Helper class used to test solutions of StockBuyAndSell problem.
	 * 
	 * @author Jihan Chen
	 *
	 */
	public static class TestMaximumSubArray {

		private int[] arr = new TestData().intArr;
		private int[] expectedsolve = new int[] { 7, 10, 43 };

		@Test
		public void testBruteForce() {
			Assert.assertArrayEquals(expectedsolve, MaximumSubArray.BRUTE_FORCE.solve(arr));
		}

		@Test
		public void testDivideAndConquer() {
			Assert.assertArrayEquals(expectedsolve, MaximumSubArray.DIVIDE_AND_CONQUER.solve(arr));
		}

		@Test
		public void testLinearSolution() {
			MaximumSubArray.LINEAR_SOLUTION.solve(arr);

		}

	}
}
