/**
 * Implementing Min-Heap with:
 *  - Algorithm 1: insertion one by one
 *  - Algorithm 2: buildHeap in O(n) time
 *  - deleteMin (remove root)
 *  - optional clear() method
 *  - 4 tree traversals (in-order, pre-order, post-order, level-order)
 *
 * Based on:
 *   Mark Allen Weiss, "Data Structures and Algorithm Analysis in Java", Chapter 6 (Heaps),
 *   and standard algorithms from texts or online references like GeeksforGeeks.
 */
package seminar_3.src.task_1;

import java.util.*;

public class BinaryHeap {
    private int[] heap;   // 1-based index array
    private int size;     // number of elements currently in heap

    /**
     * Constructs new BinaryHeap with given capacity.
     * Note: heap[0] is unused for convenience; elements start from index 1.
     */
    public BinaryHeap(int capacity) {
        // +1 because index 0 is unused
        heap = new int[capacity + 1];
        size = 0;
    }

    /**
     * Algorithm 1 (Insert one by one)
     * Inserts new value into heap in O(log n) time.
     * 
     * @param value integer value to insert
     * @throws IllegalStateException if heap is already at full capacity
     */
    public void insert(int value) {
        if (size >= heap.length - 1) {
            throw new IllegalStateException("Heap overflow");
        }
        // Placing new value at the end of array
        heap[++size] = value;
        // Bubbling it up to maintain heap order
        bubbleUp(size);
    }

    /**
     * Deleting and returning minimum element (root) in O(log n) time.
     * 
     * @return min/root value
     * @throws NoSuchElementException if heap is empty
     */
    public int deleteMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int minVal = heap[1];    // root

        // Moving last element into root
        heap[1] = heap[size];
        size--;

        // Bubbling down from root to restore order
        bubbleDown(1);

        return minVal;
    }

    /**
     * Algorithm 2 (Linear-time build).
     * Building binary heap from existing array of values in O(n) time.
     * 
     * @param values array of values to place into heap
     * @return new BinaryHeap containing given values
     */
    public static BinaryHeap buildHeap(int[] values) {
        // Creating heap object with capacity = values.length
        BinaryHeap bh = new BinaryHeap(values.length);

        // Copying values into internal array (1-based)
        System.arraycopy(values, 0, bh.heap, 1, values.length);
        bh.size = values.length;

        // Bubble down for every non-leaf node
        for (int i = bh.size / 2; i > 0; i--) {
            bh.bubbleDown(i);
        }
        return bh;
    }

    /**
     * Clearing heap (useful for resetting).
     */
    public void clear() {
        Arrays.fill(heap, 0);
        size = 0;
    }

    /**
     * @return number of elements in heap
     */
    public int getSize() {
        return size;
    }

    // Additional: Checking if this BinaryHeap is valid
    // min-heap for further verification.
    public boolean isMinHeap() {
        // each child should be >= its parent
        for (int i = 2; i <= size; i++) {
            int parent = i / 2;
            if (heap[i] < heap[parent]) {
                return false;
            }
        }
        return true;
    }

    // Internal bubble-up
    private void bubbleUp(int index) {
        while (index > 1 && heap[index] < heap[index / 2]) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    // Internal bubble-down
    private void bubbleDown(int index) {
        while (2 * index <= size) {
            int child = 2 * index;
            // If there's a right child and it's smaller, choose that
            if (child < size && heap[child] > heap[child + 1]) {
                child++;
            }
            // Stop if parent is smaller or equal
            if (heap[index] <= heap[child]) {
                break;
            }
            swap(index, child);
            index = child;
        }
    }

    // Internal swap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Traversal Methods

    /**
     * @return list of elements in in-order traversal
     */
    public List<Integer> inOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(1, result);
        return result;
    }

    private void inOrderTraversal(int index, List<Integer> result) {
        if (index > size) return;
        inOrderTraversal(index * 2, result);     // left child
        result.add(heap[index]);                 // current
        inOrderTraversal(index * 2 + 1, result); // right child
    }

    /**
     * @return list of elements in pre-order traversal
     */
    public List<Integer> preOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        preOrderTraversal(1, result);
        return result;
    }

    private void preOrderTraversal(int index, List<Integer> result) {
        if (index > size) return;
        result.add(heap[index]);                 // current
        preOrderTraversal(index * 2, result);    // left
        preOrderTraversal(index * 2 + 1, result);// right
    }

    /**
     * @return list of elements in post-order traversal
     */
    public List<Integer> postOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        postOrderTraversal(1, result);
        return result;
    }

    private void postOrderTraversal(int index, List<Integer> result) {
        if (index > size) return;
        postOrderTraversal(index * 2, result);    // left
        postOrderTraversal(index * 2 + 1, result);// right
        result.add(heap[index]);                  // current
    }

    /**
     * @return list of elements in level-order traversal
     */
    public List<Integer> levelOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            result.add(heap[i]);
        }
        return result;
    }

    /**
     * @return simple string representation of heap in form of array
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i <= size; i++) {
            sb.append(heap[i]);
            if (i < size) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
