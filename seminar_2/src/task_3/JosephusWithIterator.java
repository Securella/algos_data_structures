package task_3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementation of the Josephus Problem using ArrayList with Iterator.
 * Reference: iterator-based approach from geeksforgeeks.
 */
public class JosephusWithIterator {

    /**
     * Solving Josephus problem using ArrayList with Iterator.
     * @param n # of people in the circle.
     * @param m Step count to eliminate.
     * @param output Output handler for GUI (if null, logging to console).
     * @return winner.
     */
    public static int solveJosephus(int n, int m, StringBuilder output) {
        if (n <= 0 || m < 0) {
            throw new IllegalArgumentException("Invalid input: n must be > 0 and m must be >= 0.");
        }

        // Creating circle using ArrayList
        ArrayList<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        // Logging initial list
        appendOutput(output, "Initial List: " + circle);

        Iterator<Integer> iterator = circle.iterator();
        while (circle.size() > 1) {
            // Step through circle via iterator
            for (int count = 0; count <= m; count++) {
                if (!iterator.hasNext()) {
                    iterator = circle.iterator(); // Resetting iterator if end reached
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

