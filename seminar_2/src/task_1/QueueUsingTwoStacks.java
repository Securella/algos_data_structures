package task_1;

import java.util.Stack;

/**
 * Queue using 2 stacks.
 * Stack1 is used for enqueue operations, Stack2 is used for dequeue operations.
 */
public class QueueUsingTwoStacks<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;
    private int capacity;

    public QueueUsingTwoStacks(int capacity) {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
        this.capacity = capacity;
    }

    /**
     * Adding element to queue.
     * @param item = element to enqueue.
     * @throws Exception if queue is full.
     */
    public void enqueue(T item) throws Exception {
        if (stack1.size() + stack2.size() >= capacity) {
            throw new Exception("Queue Overflow: Queue is full.");
        }
        stack1.push(item);
    }

    /**
     * Removing and returning front element of queue.
     * @return front element.
     * @throws Exception if queue is empty.
     */
    public T dequeue() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("Queue Underflow: Queue is empty.");
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * Empty queue check.
     * @return True if queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    /**
     * Returning current state of queue as string.
     * @return string representation of queue.
     */
    @Override
    public String toString() {
        return "Queue State: stack1 = " + stack1.toString() + ", stack2 = " + stack2.toString();
    }
}
