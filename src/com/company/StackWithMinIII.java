package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithMinIII {
    Deque<Integer> stack;
    // min used to store elements contribute to min
    Deque<Integer> minStack;
    Deque<Integer> firstSeen;

    public StackWithMinIII() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        firstSeen = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        StackWithMinIII s = new StackWithMinIII();
        s.push(6);
        System.out.println(s.min());    // 6
        s.push(5);
        s.push(9);
        System.out.println(s.top());    // 9
        System.out.println(s.min());    // 5
        s.pop();
        System.out.println(s.min());    // 5
        System.out.println(s.top());    // 5
        System.out.println(s.min());    // 5
        s.pop();
        System.out.println(s.min());    // 6
    }

    public int pop() {
        // when the popped value is the same minValue as top value of minStack, the size of stack is
        // the firstSeenSize of this minValue
        if (stack.isEmpty()) {
            return -1;
        }
        int tmp = stack.pollFirst();
        if (tmp == minStack.peekFirst() && stack.size() + 1 == firstSeen.size()) {
            minStack.pollFirst();
            firstSeen.pollFirst();
        }
        return tmp;
    }

    public void push(int element) {
        stack.offerFirst(element);
        // when minStack is empty or first time seen this minValue
        // also need to push the value to minStack.
        if (minStack.isEmpty() || element < minStack.peekFirst()) {
            minStack.offerFirst(element);
            firstSeen.offerFirst(stack.size());
        }
    }

    public int top() {
        return stack.isEmpty() ? -1 : stack.peekFirst();
    }

    public int min() {
        return minStack.isEmpty() ? -1 : minStack.peekFirst();
    }
}
