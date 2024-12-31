package seminar_3.src.task_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Demonstrating:
 *  (a) Separate Chaining
 *  (b) Linear Probing
 *  (c) Quadratic Probing
 *  (d) Complexity discussion
 *  (e) Rehashing to double size (Linear table)
 *  (f) Additional rehashing approaches
 *
 * "Being creative" -> showing collision notes, chain length, etc.
 * References:
 *   Mark Allen Weiss (Hashing)
 */
public class Task2GUI extends JFrame {
    private JTextArea outputArea;

    // Required input
    private static final int[] INPUT = {4371, 1323, 6173, 4199, 4344, 9679, 1989};

    // 3 hash tables
    private HashTables.SeparateChainingHashTable chainTable;
    private HashTables.LinearProbingHashTable linearTable;
    private HashTables.QuadraticProbingHashTable quadTable;

    public Task2GUI() {
        super("Task 2: Hashing Demonstration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);

        // Main output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));

        // 1) Inserting input into all tables
        JButton initButton = new JButton("Insert into All Tables");
        initButton.addActionListener(this::initializeTables);
        buttonPanel.add(initButton);

        // 2) Showing Separate Chaining
        JButton chainButton = new JButton("Show Separate Chaining");
        chainButton.addActionListener(e -> {
            outputArea.append("\n===== (a) Separate Chaining =====\n");
            outputArea.append(chainTable.toString());
            // showing note about max chain length
            int maxLen = chainTable.getMaxChainLength();
            outputArea.append("Longest chain length: " + maxLen + "\n");
        });
        buttonPanel.add(chainButton);

        // 3) Showing Linear Probing
        JButton linearButton = new JButton("Show Linear Probing");
        linearButton.addActionListener(e -> {
            outputArea.append("\n===== (b) Linear Probing =====\n");
            outputArea.append(linearTable.toString());
            outputArea.append("Collisions encountered: " + linearTable.getCollisionCount() + "\n");
        });
        buttonPanel.add(linearButton);

        // 4) Showing Quadratic Probing
        JButton quadButton = new JButton("Show Quadratic Probing");
        quadButton.addActionListener(e -> {
            outputArea.append("\n===== (c) Quadratic Probing =====\n");
            outputArea.append(quadTable.toString());
            // no collision count by default
        });
        buttonPanel.add(quadButton);

        // 5) Rehashing Linear Table (to double capacity)
        JButton rehashButton = new JButton("Rehash (Linear) -> Double");
        rehashButton.addActionListener(e -> {
            outputArea.append("\n===== (e) Rehash (Double Size) for Linear Table =====\n");
            linearTable.rehash();
            outputArea.append("New capacity: " + linearTable.getCapacity() + "\n");
            outputArea.append(linearTable.toString());
            outputArea.append("Collisions after rehash: " + linearTable.getCollisionCount() + "\n");
        });
        buttonPanel.add(rehashButton);

        // 6) Complexity / Other rehashing
        JButton discussButton = new JButton("Discuss Complexity");
        discussButton.addActionListener(this::showDiscussion);
        buttonPanel.add(discussButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Initializing all tables with capacity=10, inserting required input,
     * showing quick notes in text area.
     */
    private void initializeTables(ActionEvent e) {
        chainTable = new HashTables.SeparateChainingHashTable(10);
        linearTable = new HashTables.LinearProbingHashTable(10);
        quadTable  = new HashTables.QuadraticProbingHashTable(10);

        // Inserting required input
        for (int val : INPUT) {
            chainTable.insert(val);
            linearTable.insert(val);
            quadTable.insert(val);
        }

        outputArea.append("All tables initialized (size=10) and inserted:\n");
        outputArea.append(Arrays.toString(INPUT) + "\n\n");

        // immediate notes
        outputArea.append("Linear collisions so far: " + linearTable.getCollisionCount() + "\n");
        outputArea.append("Max chain length so far (chaining): " + chainTable.getMaxChainLength() + "\n\n");
    }

    /**
     * (d) Complexity & (f) Other rehashing
     */
    private void showDiscussion(ActionEvent e) {
        outputArea.append("\n===== (d) Complexity Discussion =====\n");
        outputArea.append("1) Separate chaining: average O(1) if load factor small, worst O(n).\n");
        outputArea.append("2) Linear probing: average O(1) for small load factor, clustering can degrade performance.\n");
        outputArea.append("3) Quadratic probing: similar average, reduces primary clustering, worst O(n) still.\n");

        outputArea.append("\n===== (f) Other Rehashing Functions =====\n");
        outputArea.append("- Next prime > 2 * oldSize is often used instead of just doubling.\n");
        outputArea.append("- Could apply different hash function for the new table.\n");
        outputArea.append("- Rehash often triggers at load factor ~ 0.75.\n\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task2GUI::new);
    }
}
