package stacksandqueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import utils.Z;

public class Problem3_5 {

  public static void main(String[] args) {
    Queue<Integer> q = new LinkedList<Integer>();
    MyQueue<Integer> myQ = new MyQueue<>();
    myQ.add(1);
    myQ.add(2);
    myQ.add(3);
    myQ.add(4);
    Z.print(myQ.poll());
    Z.print(myQ.poll());
    Z.print(myQ.poll());
    myQ.add(5);
    myQ.add(6);
    Z.print(myQ.poll());
    Z.print(myQ.poll());
    Z.print(myQ.poll());
  }

  public static class MyQueue<T> {

    private Stack<T> inStack = new Stack<>();
    private Stack<T> outStack = new Stack<>();

    public MyQueue() {}

    public void add(T val) {
      inStack.push(val);
    }

    public T poll() {
      if (outStack.isEmpty()) shiftStack();
      return outStack.pop();
    }

    private void shiftStack() {
      while (!inStack.isEmpty()) {
        outStack.push(inStack.pop());
      }
    }
  }

}
