package org.mo39.fmbh.datastructure.stacksandqueues;

import java.util.Stack;

import org.mo39.fmbh.common.TestData;
import org.mo39.fmbh.common.Z;

/**
 * There is a slightly better way to do this.//???
 * 
 * @author Jihan Chen
 *
 */
public class Problem3_6 {

	public static void main(String[] args) {
		Z.printStack(new TestData().stack);
		Z.print();
		Z.printStack(new Problem3_6().sortStack(new TestData().stack));
	}

	public <T extends Comparable<T>> Stack<T> sortStack(Stack<T> toSort) {
		Stack<T> sorted = new Stack<>();
		sorted.push(toSort.pop());
		while (!toSort.isEmpty()) {
			T temp = toSort.pop();
			int count = 0;
			while (!sorted.isEmpty() && sorted.peek().compareTo(temp) > 0) {
				count++;
				toSort.push(sorted.pop());
			}
			sorted.push(temp);
			while (count-- > 0) {
				sorted.push(toSort.pop());
			}
		}
		return sorted;
	}

}
