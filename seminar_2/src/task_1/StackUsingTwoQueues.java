package task_1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Stack using 2 queues.
 * 1 queue serves as primary storage, while other acts as temporary buffer.
 */
public class StackUsingTwoQueues<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;
    private int capacity;

    public StackUsingTwoQueues(int capacity) {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
        this.capacity = capacity;
    }

    /**
     * Pushing element onto stack.
     * @param item = element to push.
     * @throws Exception if stack is full.
     */
    public void push(T item) throws Exception {
        if (queue1.size() >= capacity) {
            throw new Exception("Stack Overflow: Stack is full.");
        }
        queue1.add(item);
    }

    /**
     * Removing and returning top element of stack.
     * @return top element.
     * @throws Exception if stack is empty.
     */
    public T pop() throws Exception {
        if (queue1.isEmpty()) {
            throw new Exception("Stack Underflow: Stack is empty.");
        }

        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        T poppedItem = queue1.poll();

        // Swap queues
        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return poppedItem;
    }

    /**
     * Empty stack check.
     * @return True if stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    /**
     * Returning current state of stack as string.
     * @return string representation of stack.
     */
    @Override
    public String toString() {
        return "Stack State: queue1 = " + queue1.toString() + ", queue2 = " + queue2.toString();
    }
}

