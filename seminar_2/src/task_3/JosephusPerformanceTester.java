package task_3;

import java.util.function.Supplier;

/**
 * Performance tester for Josephus problem implementations.
 */
public class JosephusPerformanceTester {

    public static void main(String[] args) {
        int[] inputSizes = {1, 2, 3, 4, 5, 10, 50, 100, 1000}; // Revised input sizes
        int stepSize = 3;

        System.out.printf("%-20s %-15s %-15s %-15s %-15s%n", "Input Size", "ArrayList", "Iterator", "LinkedList", "LinkedList Iterator");

        for (int n : inputSizes) {
            long timeArrayList = measureExecutionTime(() -> {
                StringBuilder sb = new StringBuilder();
                JosephusWithArrayList.solveJosephus(n, stepSize, sb);
            });

            long timeIterator = measureExecutionTime(() -> {
                StringBuilder sb = new StringBuilder();
                JosephusWithIterator.solveJosephus(n, stepSize, sb);
            });

            long timeLinkedList = measureExecutionTime(() -> {
                StringBuilder sb = new StringBuilder();
                JosephusWithLinkedList.solveJosephus(n, stepSize, sb);
            });

            long timeLinkedListIterator = measureExecutionTime(() -> {
                StringBuilder sb = new StringBuilder();
                JosephusWithLinkedListIterator.solveJosephus(n, stepSize, sb);
            });

            System.out.printf("%-20d %-15d %-15d %-15d %-15d%n",
                    n, timeArrayList, timeIterator, timeLinkedList, timeLinkedListIterator);
        }
    }

    /**
     * Measures the execution time of a given task.
     *
     * @param task The task to be measured.
     * @return The execution time in milliseconds.
     */
    private static long measureExecutionTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
}

