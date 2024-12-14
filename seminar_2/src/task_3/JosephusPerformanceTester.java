package task_3;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Performance tester for Josephus problem implementations.
 */
public class JosephusPerformanceTester {

    public static void main(String[] args) {
        int[] inputSizes = {1, 2, 3, 4, 5, 10, 50, 100, 1000, 5000}; // Revised input sizes
        int stepSize = 3;

        System.out.printf("%-20s %-15s %-15s %-15s %-15s%n",
                "Input Size", "ArrayList (ms)", "ArrayList Iter (ms)", "LinkedList (ms)", "LinkedList Iter (ms)");

        // Prepare CSV file writer
        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.append("Input Size,ArrayList (ms),ArrayList Iter (ms),LinkedList (ms),LinkedList Iter (ms)\n");

            for (int n : inputSizes) {
                // Number of repetitions for averaging
                int repetitions = 10;

                // Track total execution time for averaging
                long totalTimeArrayList = 0;
                long totalTimeIterator = 0;
                long totalTimeLinkedList = 0;
                long totalTimeLinkedListIterator = 0;

                for (int i = 0; i < repetitions; i++) {
                    // ArrayList
                    totalTimeArrayList += measureExecutionTime(() -> {
                        StringBuilder sb = new StringBuilder();
                        JosephusWithArrayList.solveJosephus(n, stepSize, sb);
                    });

                    // ArrayList with Iterator
                    totalTimeIterator += measureExecutionTime(() -> {
                        StringBuilder sb = new StringBuilder();
                        JosephusWithIterator.solveJosephus(n, stepSize, sb);
                    });

                    // Custom LinkedList
                    totalTimeLinkedList += measureExecutionTime(() -> {
                        StringBuilder sb = new StringBuilder();
                        JosephusWithLinkedList.solveJosephus(n, stepSize, sb);
                    });

                    // LinkedList with Iterator
                    totalTimeLinkedListIterator += measureExecutionTime(() -> {
                        StringBuilder sb = new StringBuilder();
                        JosephusWithLinkedListIterator.solveJosephus(n, stepSize, sb);
                    });
                }

                // Calculate averages
                double avgArrayList = (totalTimeArrayList / repetitions) / 1_000_000.0;
                double avgIterator = (totalTimeIterator / repetitions) / 1_000_000.0;
                double avgLinkedList = (totalTimeLinkedList / repetitions) / 1_000_000.0;
                double avgLinkedListIterator = (totalTimeLinkedListIterator / repetitions) / 1_000_000.0;

                // Display average execution time for each input size
                System.out.printf("%-20d %-15.3f %-15.3f %-15.3f %-15.3f%n",
                        n, avgArrayList, avgIterator, avgLinkedList, avgLinkedListIterator);

                // Save results to CSV
                writer.append(String.format("%d,%.3f,%.3f,%.3f,%.3f\n",
                        n, avgArrayList, avgIterator, avgLinkedList, avgLinkedListIterator));
            }

            System.out.println("Results saved to results.csv.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    /**
     * Measures the execution time of a given task.
     *
     * @param task The task to be measured.
     * @return The execution time in nanoseconds.
     */
    private static long measureExecutionTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        return (endTime - startTime); // Time in nanoseconds
    }
}
