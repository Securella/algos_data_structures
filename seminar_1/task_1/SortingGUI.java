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
 * seminar and is intended for examination.
 * 
 * Disclaimer:
 * This is original work created for a seminar assignment. It should
 * not be copied, shared, or used as a reference for similar projects without permission.
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * =============================================================
 */

/**
 * SortingGUI provides graphical interface for Seminar 1 running and benchmarking 
 * multiple sorting algorithms on varying input sizes, including best-case, average-case,
 * and worst-case scenarios, with results saved to CSV file.
 */
public class SortingGUI extends JFrame {
    private JTextField arraySizesField;  // Field for specifying array sizes
    private JTextArea outputArea;        // Display area for logging results
    private JButton runButton;           // Button to trigger sorting tests
    private JProgressBar progressBar;    // Progress indicator for batch processing

    /**
     * Initializing SortingGUI interface with input fields, progress bar, and output display.
     */
    public SortingGUI() {
        setTitle("Seminar in Algorithms - Sorting GUI");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configure input panel for array size entry
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        arraySizesField = new JTextField("100,1000,10000,100000,1000000");  // Default input sizes
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
        String[] sizeStrings = arraySizesField.getText().split(",");
        int[] sizes = new int[sizeStrings.length];
    
        try {
            for (int i = 0; i < sizeStrings.length; i++) {
                sizes[i] = Integer.parseInt(sizeStrings[i].trim());
                if (sizes[i] <= 0) {
                    throw new NumberFormatException("Array size must be positive.");
                }
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid array sizes. Please enter valid positive integers.\n");
            return;
        }
    
        File inputFile = new File("algos_data_structures/Seminar 1 - File with random numbers.txt");
        if (!inputFile.exists()) {
            outputArea.append("Input file 'Seminar 1 - File with random numbers.txt' not found.\n");
            return;
        }
    
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try (FileWriter writer = new FileWriter("sorting_results.csv")) {
                    writer.write("Scenario,Algorithm,Array Size,Execution Time (ms),Repetitions\n");
    
                    String[] algorithms = {
                        "QuickSort - Recursive (Median Pivot)",
                        "QuickSort - Iterative (Random Pivot)",
                        "QuickSort - Recursive (First Pivot)",
                        "QuickSort - Iterative (Median Pivot)",
                        "QuickSort - Iterative (Random Pivot)",
                        "QuickSort - Iterative (First Pivot)",
                        "InsertionSort - Recursive",
                        "InsertionSort - Iterative"
                    };

                    String[] scenarios = {"Best-Case", "Average-Case", "Worst-Case"};
    
                    progressBar.setMaximum(algorithms.length * sizes.length * scenarios.length);
                    int progress = 0;
    
                    final int repetitions = 10; // Repeat each test 5 times
                    for (String scenario : scenarios) {
                        for (String algorithm : algorithms) {
                            for (int size : sizes) {
                                double totalExecutionTime = 0;
                                int[] array = generateArray(inputFile, size, scenario);
    
                                if (array == null || array.length < size) {
                                    outputArea.append("Error loading array for size " + size + ".\n");
                                    continue;
                                }
    
                                for (int rep = 0; rep < repetitions; rep++) {
                                    int[] copy = array.clone(); // Clone array for each repetition
                                    long startTime = System.nanoTime();
                                    try {
                                        switch (algorithm) {
                                            case "QuickSort - Recursive (Median Pivot)":
                                                QuickSort.quickSortRecursiveMedian(copy, 0, copy.length - 1);
                                                break;
                                            case "QuickSort - Iterative (Random Pivot)":
                                                QuickSort.quickSortIterativeRandom(copy);
                                                break;
                                            case "QuickSort - Recursive (First Pivot)":
                                                QuickSort.quickSortRecursiveFirstPivot(copy, 0, copy.length - 1);
                                                break;
                                            case "QuickSort - Iterative (First Pivot)":
                                                QuickSort.quickSortIterativeFirstPivot(copy);
                                                break;
                                            case "InsertionSort - Recursive":
                                                InsertionSort.insertionSortRecursive(copy, copy.length);
                                                break;
                                            case "InsertionSort - Iterative":
                                                InsertionSort.insertionSortIterative(copy);
                                                break;
                                        }
                                    } catch (Exception ex) {
                                        outputArea.append("Error in algorithm execution: " + ex.getMessage() + "\n");
                                        ex.printStackTrace();
                                        break;
                                    }
                                    long endTime = System.nanoTime();
                                    totalExecutionTime += (endTime - startTime) / 1e6;
                                }
    
                                double averageExecutionTime = totalExecutionTime / repetitions;
                                writer.write(String.format("%s,%s,%d,%.2f,%d\n", scenario, algorithm, size, averageExecutionTime, repetitions));
                                outputArea.append(String.format("%s - %s - Size %d (Avg over %d): %.2f ms\n", scenario, algorithm, size, repetitions, averageExecutionTime));
    
                                progressBar.setValue(++progress);
                            }
                        }
                    }
                    outputArea.append("All tests completed. Results successfully saved to sorting_results.csv.\n");

                    // Triggering Python visualization script
                    triggerPythonScript();

                } catch (IOException e) {
                    outputArea.append("Error writing to results file: " + e.getMessage() + "\n");
                    e.printStackTrace();
                }
                return null;
            }
        };

        worker.execute(); // Starting task in the background
    }

    /**
     * Generates an array based on the specified scenario.
     * @param inputFile File to read random numbers.
     * @param size Size of array.
     * @param scenario Scenario type: "Best-Case", "Average-Case", "Worst-Case".
     * @return Generated array.
     */
    private int[] generateArray(File inputFile, int size, String scenario) {
        try {
            // Reading numbers from file into a base array
            int[] baseArray = new int[size];
            try (Scanner scanner = new Scanner(inputFile)) {
                for (int i = 0; i < size && scanner.hasNextInt(); i++) {
                    baseArray[i] = scanner.nextInt();
                }
            }

            // Rearrange numbers based on different scenarios
            switch (scenario) {
                case "Best-Case":
                    java.util.Arrays.sort(baseArray); // Sort in ascending order
                    break;
                case "Average-Case":
                    break; // Use as-is
                case "Worst-Case":
                    java.util.Arrays.sort(baseArray); // Sort in descending order
                    for (int i = 0, j = baseArray.length - 1; i < j; i++, j--) {
                        int temp = baseArray[i];
                        baseArray[i] = baseArray[j];
                        baseArray[j] = temp;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid scenario: " + scenario);
            }

            return baseArray;

        } catch (Exception e) {
            outputArea.append("Error generating array for scenario " + scenario + " with size " + size + ": " + e.getMessage() + "\n");
            e.printStackTrace();
            return null;
        }
    }

    /**
 * Triggering Python script for visualizations.
 */
private void triggerPythonScript() {
    try {
        File pythonScript = new File("sorting_analysis.py");
        if (!pythonScript.exists()) {
            outputArea.append("Error: Python script 'sorting_analysis.py' not found.\n");
            return;
        }

        outputArea.append("Starting visualization script...\n");
        
        // Using ProcessBuilder with full path to python3 (if needed)
        ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScript.getAbsolutePath());
        processBuilder.redirectErrorStream(true); // Combine error stream with output stream
        Process process = processBuilder.start();

        // Reading output from script
        Scanner scriptOutput = new Scanner(process.getInputStream());
        while (scriptOutput.hasNextLine()) {
            outputArea.append(scriptOutput.nextLine() + "\n");
        }
        scriptOutput.close();

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            outputArea.append("Visualization script completed successfully.\n");
        } else {
            outputArea.append("Visualization script failed with exit code: " + exitCode + "\n");
        }
    } catch (IOException | InterruptedException e) {
        outputArea.append("Failed to execute visualization script: " + e.getMessage() + "\n");
        e.printStackTrace();
    }
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

/*
 * To prevent stack overflow for deep recursion, increase the stack size
 * by adding the JVM option -Xss100m when running the application.
 * Source: https://www.baeldung.com/jvm-configure-stack-sizes
 */