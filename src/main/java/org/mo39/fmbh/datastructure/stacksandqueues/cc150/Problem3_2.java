package org.mo39.fmbh.datastructure.stacksandqueues.cc150;

import java.util.ArrayList;
import java.util.List;

import org.mo39.fmbh.common.Z;

public class Problem3_2 {

	public static void main(String[] args) {
		StackImpl0<Integer> stackImpl0 = new StackImpl0<>();
		stackImpl0.push(1);
		stackImpl0.push(5);
		stackImpl0.push(2);
		stackImpl0.push(0);
		while (stackImpl0.hasMoreElements()) {
			Z.print("Min value is : " + stackImpl0.getMin() + "\t" + "Pop : " + stackImpl0.pop());
		}
	}

	/**
	 * This is a generic version of Stack.<br>
	 * During the interview, this should be a bonus point.<br>
	 *
	 * @author Jihan Chen
	 *
	 * @param <T>
	 */
	public static class StackImpl0<T extends Comparable<T>> {

		private List<T> minValues = new ArrayList<>();
		private List<T> values = new ArrayList<>();

		public boolean hasMoreElements() {
			return !values.isEmpty();
		}

		public void push(T val) {
			values.add(val);
			if (minValues.size() == 0) {
				minValues.add(val);
				return;
			}
			T lastMinValue = minValues.get(minValues.size() - 1);
			if (val.compareTo(lastMinValue) < 0) minValues.add(val);
			else minValues.add(lastMinValue);
		}

		public T pop() {
			minValues.remove(minValues.size() - 1);
			return values.remove(values.size() - 1);
		}

		public T getMin() {
			return minValues.get(minValues.size() - 1);
		}
	}

}
