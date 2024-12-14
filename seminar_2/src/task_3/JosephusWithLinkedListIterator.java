package task_3;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Implementing Josephus Problem using LinkedList with Iterator.
 * Reference: Adapted from iterator-based LinkedList, geeksforgeeks.
 */
public class JosephusWithLinkedListIterator {

    /**
     * Solving Josephus problem using LinkedList with Iterator.
     * @param n Number of people in the circle.
     * @param m Step count for elimination.
     * @param output Output handler for GUI (if null, logging to console).
     * @return winner.
     */
    public static int solveJosephus(int n, int m, StringBuilder output) {
        if (n <= 0 || m < 0) {
            throw new IllegalArgumentException("Invalid input: n must be > 0 and m must be >= 0.");
        }

        // Creating circle using LinkedList
        LinkedList<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        // Logging initial list
        appendOutput(output, "Initial List: " + circle);

        ListIterator<Integer> iterator = circle.listIterator();
        while (circle.size() > 1) {
            // Traversing to m-th person
            for (int count = 0; count <= m; count++) {
                if (!iterator.hasNext()) {
                    iterator = circle.listIterator(); // Resetting iterator if end reached
                }
                if (count == m) {
                    int eliminated = iterator.next();
                    appendOutput(output, "Eliminated: " + eliminated);
                    iterator.remove();
                } else {
                    iterator.next();
                }
            }
        }

        // Returning last remaining person
        return circle.get(0);
    }

    /**
     * Helper method to append messages to output (GUI in my case).
     */
    private static void appendOutput(StringBuilder output, String message) {
        if (output != null) {
            output.append(message).append("\n");
        } else {
            System.out.println(message);
        }
    }
}

