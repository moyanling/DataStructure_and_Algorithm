package org.mo39.fmbh.datastructure.queue;

import static org.mo39.fmbh.common.annotation.ProblemSource.SourceValue.LEETCODE;

import java.util.Stack;

import org.mo39.fmbh.common.annotation.ProblemSource;

/**
 * <pre>
 * Implement the following operations of a queue using stacks.
 * 
 * 
 * push(x) -- Push element x to the back of queue.
 * 
 * 
 * pop() -- Removes the element from in front of queue.
 * 
 * 
 * peek() -- Get the front element.
 * 
 * 
 * empty() -- Return whether the queue is empty.
 * 
 * 
 * Notes:
 * 
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from
 * top, size, and is empty operations are valid. Depending on your language, stack may not be
 * supported natively. You may simulate a stack by using a list or deque (double-ended queue), as
 * long as you use only standard operations of a stack. You may assume that all operations are valid
 * (for example, no pop or peek operations will be called on an empty queue).
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/implement-queue-using-stacks/">Implement Queue Using
 *      Stacks</a>
 * @author Jihan Chen
 */
@ProblemSource(LEETCODE)
public class ImplementQueueUsingStacks<T> {

  Stack<T> inStack = new Stack<>();
  Stack<T> outStack = new Stack<>();

  public void push(T x) {
    while (!outStack.isEmpty()) {
      inStack.push(outStack.pop());
    }
    inStack.push(x);
    while (!inStack.isEmpty()) {
      outStack.push(inStack.pop());
    }
  }


  public void pop() {
    outStack.pop();
  }


  public T peek() {
    return outStack.peek();
  }


  public boolean empty() {
    return outStack.isEmpty();
  }

}


