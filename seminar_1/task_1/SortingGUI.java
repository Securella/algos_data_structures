package algos_data_structures.seminar_1.task_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * =============================================================
 * Project: Seminar 1 in Algorithms and Data Structures
 * Author: Securella
 * Year: 2024
 * Description: This code is part of an individual project for the
 *              seminar and is intended for examination.
 * 
 * Disclaimer:
 * This is original work created for a seminar assignment. It should
 * not be copied, shared, or used as a reference for similar projects without permission
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * =============================================================
 */


/**
 * SortingGUI provides graphical interface for Seminar 1 running and benchmarking 
 * multiple sorting algorithms on varying input sizes, with results saved to CSV file.
 */
public class SortingGUI extends JFrame {
    private JTextField arraySizesField;  // Field for specifying array sizes
    private JTextArea outputArea;        // Display area for logging results
    private JButton runButton;           // Button to trigger sorting tests
    private JProgressBar progressBar;    // Progress indicator for batch processing

    /**
     * Initializes the SortingGUI interface with input fields, progress bar, and output display.
     */
    public SortingGUI() {
        setTitle("Seminar in Algorithms - Sorting GUI");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configure input panel for array size entry
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        arraySizesField = new JTextField("100,1000,10000");  // Default input sizes
        topPanel.add(new JLabel("Enter Array Sizes (comma-separated):"));
        topPanel.add(arraySizesField);

        // Set up progress bar to track testing progress
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Configure output area for displaying results and logs
        outputArea = new JTextArea();
        outputArea.setEditable(false);     // Non-editable display
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Configure button to start tests and link to action
        runButton = new JButton("Run All Algorithms");
        runButton.addActionListener((ActionEvent e) -> runBulkTests());

        // Add components to main layout
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
        add(runButton, BorderLayout.SOUTH);
    }

    /**
     * Parses user-defined array sizes, then runs each sorting algorithm on these sizes.
     * Logs execution times and saves results to CSV file.
     */
    private void runBulkTests() {
        // Parse array sizes from input field
        String[] sizeStrings = arraySizesField.getText().split(",");
        int[] sizes = new int[sizeStrings.length];

        try {
            for (int i = 0; i < sizeStrings.length; i++) {
                sizes[i] = Integer.parseInt(sizeStrings[i].trim()); // Convert each input to integer
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid array sizes. Please enter valid numbers.\n");
            return;
        }

        // Verify that input file exists
        File inputFile = new File("algos_data_structures/Seminar 1 - File with random numbers.txt");
        if (!inputFile.exists()) {
            outputArea.append("Input file 'Seminar 1 - File with random numbers.txt' not found.\n");
            return;
        }

        // Execute sorting tests in background thread to keep UI responsive
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try (FileWriter writer = new FileWriter("sorting_results.csv")) {
                    writer.write("Algorithm,Array Size,Execution Time (ms)\n"); // CSV header

                    // List of sorting algorithms to execute
                    String[] algorithms = {
                        "QuickSort - Recursive (Median Pivot)",
                        "QuickSort - Iterative (Random Pivot)",
                        "QuickSort - Recursive (First Pivot)",
                        "QuickSort - Iterative (First Pivot)",
                        "InsertionSort - Recursive",
                        "InsertionSort - Iterative"
                    };

                    // Configure progress bar to reflect the number of tests
                    progressBar.setMaximum(algorithms.length * sizes.length);
                    int progress = 0;

                    // Execute each algorithm on each array size
                    for (String algorithm : algorithms) {
                        for (int size : sizes) {
                            int[] array = loadArray(inputFile, size); // Load random data array
                            if (array == null) {
                                outputArea.append("Error loading array for size " + size + ".\n");
                                continue;
                            }

                            // Record start time, run sorting algorithm, and record end time
                            long startTime = System.nanoTime();
                            switch (algorithm) {
                                case "QuickSort - Recursive (Median Pivot)":
                                    QuickSort.quickSortRecursiveMedian(array, 0, array.length - 1);
                                    break;
                                case "QuickSort - Iterative (Random Pivot)":
                                    QuickSort.quickSortIterativeRandom(array);
                                    break;
                                case "QuickSort - Recursive (First Pivot)":
                                    QuickSort.quickSortRecursiveFirstPivot(array, 0, array.length - 1);
                                    break;
                                case "QuickSort - Iterative (First Pivot)":
                                    QuickSort.quickSortIterativeFirstPivot(array);
                                    break;
                                case "InsertionSort - Recursive":
                                    InsertionSort.insertionSortRecursive(array, array.length);
                                    break;
                                case "InsertionSort - Iterative":
                                    InsertionSort.insertionSortIterative(array);
                                    break;
                            }
                            long endTime = System.nanoTime();

                            // Calculate and log execution time in milliseconds
                            double executionTime = (endTime - startTime) / 1e6;
                            writer.write(String.format("%s,%d,%.2f\n", algorithm, size, executionTime));
                            outputArea.append(String.format("%s - Size %d: %.2f ms\n", algorithm, size, executionTime));

                            // Update progress bar
                            progressBar.setValue(++progress);
                        }
                    }
                    outputArea.append("All tests completed. Results successfully saved to sorting_results.csv.\n");
                } catch (IOException e) {
                    outputArea.append("Error writing to results file.\n");
                }
                return null;
            }
        };

        worker.execute(); // Start task in the background
    }

    /**
     * Loads array of integers from file.
     * Reads number of integers defined by size from file.
     *
     * @param inputFile = file containing random integers.
     * @param size = size of array to load.
     * @return = array of specified size, or null if error occurs.
     */
    private int[] loadArray(File inputFile, int size) {
        int[] array = new int[size];
        try (Scanner scanner = new Scanner(inputFile)) {
            for (int i = 0; i < size && scanner.hasNextInt(); i++) {
                array[i] = scanner.nextInt();
            }
        } catch (IOException e) {
            return null;
        }
        return array;
    }

    /**
     * Main method to launch application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingGUI gui = new SortingGUI();
            gui.setVisible(true);
        });
    }
}

