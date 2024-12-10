package task_1;

import java.util.Stack;

/**
 * Queue using single stack.
 * Using recursion for dequeue operations.
 */
public class QueueUsingOneStack<T> {
    private Stack<T> stack;
    private int capacity;

    public QueueUsingOneStack(int capacity) {
        this.stack = new Stack<>();
        this.capacity = capacity;
    }

    /**
     * Adding element to queue.
     * @param item = element to enqueue.
     * @throws Exception when queue is full.
     */
    public void enqueue(T item) throws Exception {
        if (stack.size() >= capacity) {
            throw new Exception("Queue Overflow: Queue is full.");
        }
        stack.push(item);
    }

    /**
     * Removing and returning front element of queue.
     * @return front element.
     * @throws Exception when queue is empty.
     */
    public T dequeue() throws Exception {
        if (stack.isEmpty()) {
            throw new Exception("Queue Underflow: Queue is empty.");
        }
        if (stack.size() == 1) {
            return stack.pop();
        }
        T top = stack.pop();
        T result = dequeue();
        stack.push(top);
        return result;
    }

    /**
     * Empty queue check.
     * @return True if queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returning current state of queue as string.
     * @return A string representation of queue.
     */
    @Override
    public String toString() {
        return "Queue State: stack = " + stack.toString();
    }
}
