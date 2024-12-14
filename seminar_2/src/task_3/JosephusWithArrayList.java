package task_3;

import java.util.ArrayList;

/**
 * Implementation of the Josephus Problem using ArrayList.
 * Logs the initial list, each elimination step, and the winner.
 * Reference: Adapted from GeeksForGeeks:
 * https://www.geeksforgeeks.org/josephus-problem/
 */
public class JosephusWithArrayList {

    /**
     * Solving Josephus problem using ArrayList.
     * @param n # of people in circle.
     * @param m Step count for elimination.
     * @param output Output handler for GUI (if null, logging to console).
     * @return winner.
     */
    public static int solveJosephus(int n, int m, StringBuilder output) {
        if (n <= 0 || m < 0) {
            throw new IllegalArgumentException("Invalid input: n must be > 0 and m must be >= 0.");
        }

        // Creating a circle using ArrayList
        ArrayList<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        // Logging initial list
        appendOutput(output, "Initial List: " + circle);

        int index = 0;
        while (circle.size() > 1) {
            // Calculating next person to be eliminated
            index = (index + m) % circle.size();
            appendOutput(output, "Eliminated: " + circle.get(index));
            circle.remove(index);
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


