package org.mo39.fmbh.datastructure.stack;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element
 * in constant time.
 * 
 * 
 * push(x) -- Push element x onto stack.
 * 
 * 
 * pop() -- Removes the element on top of the stack.
 * 
 * 
 * top() -- Get the top element.
 * 
 * 
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * </pre>
 * 
 * Keep a state for each element. Can be also implemented using {@link ImmutablePair}.
 * 
 * @see <a href="https://leetcode.com/problems/min-stack/">Min Stack</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public class MinStack<T extends Comparable<T>> {

  Stack<T> stack = new Stack<>();
  T min = null;

  public void push(T x) {
    if (min == null || x.compareTo(min) <= 0) {
      stack.push(min);
      min = x;
    }
    stack.push(x);
  }

  public void pop() {
    if (stack.peek() == min) {
      stack.pop();
      min = stack.pop();
    } else stack.pop();
  }

  public T top() {
    return stack.peek();
  }

  public T getMin() {
    return min;
  }

}
