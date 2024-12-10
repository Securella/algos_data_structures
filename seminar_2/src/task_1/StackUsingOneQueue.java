package task_1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Stack using single queue.
 * Rearranging queue during push to ensure stack behavior.
 */
public class StackUsingOneQueue<T> {
    private Queue<T> queue;
    private int capacity;

    public StackUsingOneQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    /**
     * Pushing element onto stack.
     * @param item = element to push.
     * @throws Exception if stack is full.
     */
    public void push(T item) throws Exception {
        if (queue.size() >= capacity) {
            throw new Exception("Stack Overflow: Stack is full.");
        }
        queue.add(item);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
    }

    /**
     * Removing and returning top element of stack.
     * @return top element.
     * @throws Exception if stack is empty.
     */
    public T pop() throws Exception {
        if (queue.isEmpty()) {
            throw new Exception("Stack Underflow: Stack is empty.");
        }
        return queue.poll();
    }

    /**
     * Empty stack check.
     * @return True when stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returning current state of stack as string.
     * @return string representation of stack.
     */
    @Override
    public String toString() {
        return "Stack State: queue = " + queue.toString();
    }
}
