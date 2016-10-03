package org.mo39.fmbh.datastructure.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.mo39.fmbh.common.Z;

public class Problem3_3 {

	public static void main(String[] args) {

		SetOfStacks<Integer> setOfStacks = new SetOfStacks<>(1);
		setOfStacks.push(1);
		setOfStacks.push(2);
		setOfStacks.push(4);
		setOfStacks.push(5);
		setOfStacks.push(3);
		setOfStacks.push(1);
		for (int i = 0; i < 6; i++) {
			Z.print(setOfStacks.pop());
		}
	}

	public static class SetOfStacks<T> {

		private List<Stack<T>> list;
		private int curr;
		private int threshold;

		public SetOfStacks(int threshold) {
			if (threshold < 1) throw new IllegalArgumentException();
			this.threshold = threshold;
			curr = 0;
			list = new ArrayList<>();
			list.add(new Stack<>());
		}

		public void push(T val) {
			if (list.get(curr).size() >= threshold) {
				curr++;
				list.add(new Stack<>());
			}
			list.get(curr).push(val);
		}

		public T pop() {
			if (list.get(curr).isEmpty()) curr--;
			return list.get(curr).pop();
		}

		public T popAt(int index) {
			return list.get(index).pop();
		}

	}

}
