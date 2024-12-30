/**
 *   GUI with:
 *   (a) Step-by-step insertion (Algorithm 1)
 *   (b) Linear-time build (Algorithm 2)
 *   (c) 4 traversals (in-order, pre-order, post-order, level-order)
 *   (d) Complexity measurement using data from "Seminar 1 - File with random numbers.txt"
 *   (e) Comparing insert vs. deleteMin complexity
 *
 * Reference:
 *   Mark Allen Weiss, "Data Structures and Algorithm Analysis in Java" (Chapter #6) variation
 */
package seminar_3.src.task_1;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Task1GUI extends JFrame {
    private BinaryHeap stepHeap;      // heap used for step-by-step insertion
    private JTextArea outputArea;     // text area to display results
    private int[] input;              // fixed array for tasks (a), (b), (c)
    private int stepIndex;            // tracking how many inserts have been done

    public Task1GUI() {
        setTitle("Task 1: Binary Heap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // (a) Array for step-by-step insertion (Algorithm 1) and for
        //     linear-time build (Algorithm 2). Taken from problem statement.
        input = new int[] {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};

        // Initializing heap with capacity = input.length for step-by-step insertion
        stepHeap = new BinaryHeap(input.length);
        stepIndex = 0;

        // Main output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Creating buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));

        // 1) Step-by-Step Insertion (Algorithm 1)
        JButton stepButton = new JButton("Step-by-Step Insertion");
        stepButton.addActionListener(e -> insertStep());
        buttonPanel.add(stepButton);

        // 2) Building Using Linear-Time (Algorithm 2)
        JButton linearBuildButton = new JButton("Linear-Time Build");
        linearBuildButton.addActionListener(e -> buildLinearHeap());
        buttonPanel.add(linearBuildButton);

        // 3) Showing Traversals
        JButton traverseButton = new JButton("Show Traversals");
        traverseButton.addActionListener(e -> showTraversals());
        buttonPanel.add(traverseButton);

        // 4) Measuring Complexity (reads from "Seminar 1 - File with random numbers.txt")
        JButton complexityButton = new JButton("Measure Complexity");
        complexityButton.addActionListener(e -> measureComplexity());
        buttonPanel.add(complexityButton);

        // 5) Comparing Insert vs. DeleteMin
        JButton compareOpsButton = new JButton("Compare Insert vs DeleteMin");
        compareOpsButton.addActionListener(e -> compareOperations());
        buttonPanel.add(compareOpsButton);

        // Clearing/Resetting the step-heap
        JButton clearButton = new JButton("Clear/Reset");
        clearButton.addActionListener(e -> clearHeap());
        buttonPanel.add(clearButton);

        // Button panel
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // (a) Step-by-step insertion
    private void insertStep() {
        if (stepIndex < input.length) {
            int value = input[stepIndex];
            stepHeap.insert(value);
            outputArea.append("Step " + (stepIndex + 1) + ": Insert " + value + "\n");
            outputArea.append("Heap: " + stepHeap + "\n\n");
            stepIndex++;
        } else {
            outputArea.append("All elements have been inserted (Algorithm 1 complete).\n\n");
        }
    }

    // (b) Building using linear-time
    private void buildLinearHeap() {
        BinaryHeap linearHeap = BinaryHeap.buildHeap(input);
        outputArea.append("Heap built using Linear-Time Algorithm (Algorithm 2):\n");
        outputArea.append("Heap: " + linearHeap + "\n\n");
    }

    // (c) Showing 4 traversals
    private void showTraversals() {
        // building fresh heap using linear-time
        BinaryHeap heap = BinaryHeap.buildHeap(input);

        outputArea.append("===== Four Traversals (Algorithm 2 final tree) =====\n");
        outputArea.append("In-order:    " + heap.inOrderTraversal() + "\n");
        outputArea.append("Pre-order:   " + heap.preOrderTraversal() + "\n");
        outputArea.append("Post-order:  " + heap.postOrderTraversal() + "\n");
        outputArea.append("Level-order: " + heap.levelOrderTraversal() + "\n\n");
    }

    // (d) Measuring complexity for different input sizes from file
    private void measureComplexity() {
        outputArea.append("===== Complexity Measurement =====\n");

        // reading 1 big file with 10,000+ lines
        int[] allData = readNumbersFromFile("Seminar 1 - File with random numbers.txt");
        if (allData.length < 10000) {
            outputArea.append("ERROR: The file must have at least 10000 integers!\n\n");
            return;
        }

        // testing sub-arrays of size 100, 1000, 10,000
        int[] testSizes = {100, 1000, 10000};

        for (int n : testSizes) {
            // extracting first n integers
            int[] testData = Arrays.copyOfRange(allData, 0, n);

            // Algorithm 1: inserting one-by-one
            BinaryHeap heap1 = new BinaryHeap(n + 5); // +5 to avoid overflow
            long startTime = System.nanoTime();
            for (int val : testData) {
                heap1.insert(val);
            }
            long endTime = System.nanoTime();
            long timeAlgo1 = endTime - startTime;

            // Algorithm 2: building in linear time
            startTime = System.nanoTime();
            BinaryHeap heap2 = BinaryHeap.buildHeap(testData);
            endTime = System.nanoTime();
            long timeAlgo2 = endTime - startTime;

            // print results
            outputArea.append(String.format(
                "n = %d -> Insert-one-by-one: %d ns, Linear-Build: %d ns\n",
                n, timeAlgo1, timeAlgo2
            ));

            // ===========================
            // FURTHER VERIFICATION STEP:
            // Check if both heaps are valid min-heaps
            // and contain the same elements.
            // ===========================
            boolean valid1 = heap1.isMinHeap();
            boolean valid2 = heap2.isMinHeap();
            Set<Integer> set1 = new HashSet<>(heap1.levelOrderTraversal());
            Set<Integer> set2 = new HashSet<>(heap2.levelOrderTraversal());

            if (valid1 && valid2) {
                outputArea.append("  Both heaps are valid min-heaps.\n");
            } else {
                outputArea.append("  WARNING: At least one heap is not a valid min-heap.\n");
            }

            if (set1.equals(set2)) {
                outputArea.append("  Both heaps contain the same elements.\n");
            } else {
                outputArea.append("  WARNING: The two heaps have different contents.\n");
            }

            outputArea.append("\n");
        }
        outputArea.append("\n");
    }

    // (e) Comparing insertion vs deleteMin
    private void compareOperations() {
        outputArea.append("===== Compare Insert vs DeleteMin =====\n");

        // building heap with capacity input.length + 5 to avoid overflow
        BinaryHeap heap = new BinaryHeap(input.length + 5);
        // insert the 15 elements from input
        for (int val : input) {
            heap.insert(val);
        }

        // 5 new insertions
        int[] newValues = {25, 17, 30, 2, 19};
        long start = System.nanoTime();
        for (int val : newValues) {
            heap.insert(val);
        }
        long end = System.nanoTime();
        long insertTime = end - start;

        // 5 deletions
        start = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            heap.deleteMin();
        }
        end = System.nanoTime();
        long deleteTime = end - start;

        outputArea.append("Inserted 5 elements in " + insertTime + " ns\n");
        outputArea.append("Deleted 5 min elements in " + deleteTime + " ns\n\n");
    }

    // Clearing stepHeap and resetting stepIndex
    private void clearHeap() {
        stepHeap.clear();
        stepIndex = 0;
        outputArea.append("Heap has been cleared. Step index reset.\n\n");
    }

    // Reading integers from file
    private int[] readNumbersFromFile(String filePath) {
        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get(filePath));
            return lines.stream()
                        .mapToInt(Integer::parseInt)
                        .toArray();
        } catch (IOException e) {
            e.printStackTrace();
            outputArea.append("ERROR: Could not read file: " + filePath + "\n");
            return new int[0];
        }
    }

    // GUI launch
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task1GUI());
    }
}

