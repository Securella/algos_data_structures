package task_3;

/**
 * Implementating Josephus Problem using custom LinkedList.
 */
public class JosephusWithLinkedList {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * Solving Josephus problem using custom LinkedList.
     *
     * @param n      # of people in circle.
     * @param m      Step count for elimination.
     * @param output Output handler for GUI (if null, logging to console).
     * @return winner.
     */
    public static int solveJosephus(int n, int m, StringBuilder output) {
        if (n <= 0 || m < 0) {
            throw new IllegalArgumentException("Invalid input: n must be > 0 and m must be >= 0.");
        }

        // Creating circular linked list
        Node head = createCircularLinkedList(n);

        // Logging initial list
        appendOutput(output, "Initial List: " + buildList(head));

        Node current = head;
        Node prev = null;

        // Continue till only 1 node remains
        while (current.next != current) {
            // Traversing m steps (corrected to include node itself in counting)
            for (int count = 0; count < m; count++) {
                prev = current;
                current = current.next;
            }

            // Eliminating current node
            appendOutput(output, "Eliminated: " + current.value);

            // Removing node by updating previous node's pointer
            prev.next = current.next;
            current = current.next; // Moving to next node
        }

        // Returning winner without appending it to output
        return current.value;
    }

    /**
     * Creating circular linked list with values from 1 to n.
     *
     * @param n # of nodes in circular linked list.
     * @return Head of circular linked list.
     */
    private static Node createCircularLinkedList(int n) {
        Node head = new Node(1);
        Node current = head;

        for (int i = 2; i <= n; i++) {
            current.next = new Node(i);
            current = current.next;
        }

        current.next = head; // Making list circular
        return head;
    }

    /**
     * Helper method to build list for logging.
     */
    private static String buildList(Node head) {
        StringBuilder sb = new StringBuilder();
        Node temp = head;

        do {
            sb.append(temp.value).append(temp.next != head ? ", " : "");
            temp = temp.next;
        } while (temp != head);

        return sb.toString();
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

