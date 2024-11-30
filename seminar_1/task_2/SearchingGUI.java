package algos_data_structures.seminar_1.task_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

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
 * not be copied, shared, or used as a reference for similar projects.
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * =============================================================
 */

public class SearchingGUI extends JFrame {
    private JTextField targetField;
    private JTextArea resultArea;

    public SearchingGUI() {
        setTitle("Binary Search - GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        targetField = new JTextField("7");
        inputPanel.add(new JLabel("Enter Target Number:"));
        inputPanel.add(targetField);

        // Output area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Button to perform search
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener((ActionEvent e) -> performSearch());

        // Layout components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchButton, BorderLayout.SOUTH);
    }

    private void performSearch() {
        try {
            int target = Integer.parseInt(targetField.getText().trim());

            // Fetch numbers from file
            int[] arr = BinarySearch.fetchNumbersFromFile();
            Arrays.sort(arr);

            // Perform search
            boolean found = BinarySearch.binarySearch(arr, target);

            // Save results for visualization
            saveResults(target);

            // Display result
            resultArea.append("Array: " + Arrays.toString(arr) + "\n");
            resultArea.append("Target: " + target + "\n");
            resultArea.append("Result: " + (found ? "Found" : "Not Found") + "\n\n");
        } catch (Exception ex) {
            resultArea.append("Error: Invalid input. Please enter a valid number.\n");
        }
    }

    private void saveResults(int target) {
        try (FileWriter writer = new FileWriter("binary_search_results.csv")) {
            // Use semicolon as the delimiter
            writer.write("Input Size;Execution Time (µs);Log(n)\n");

            int[] inputSizes = {100, 1000, 10000, 100000, 1000000};
            for (int size : inputSizes) {
                // Generate a sorted array of the specified size
                int[] arr = generateArray(size);
                Arrays.sort(arr);

                // Measure execution time for binary search
                long startTime = System.nanoTime();
                BinarySearch.binarySearch(arr, target);
                long endTime = System.nanoTime();

                // Calculate metrics
                double executionTime = (endTime - startTime) / 1e3; // Convert to microseconds
                double logN = Math.log(arr.length) / Math.log(2);

                // Write the results to the CSV file
                writer.write(String.format(Locale.US, "%d;%.3f;%.6f\n", arr.length, executionTime, logN));
            }

            resultArea.append("Results saved to binary_search_results.csv\n");
        } catch (IOException e) {
            resultArea.append("Error saving results to CSV: " + e.getMessage() + "\n");
        }
    }

    private int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1; // Sequential numbers from 1 to size
        }
        return arr;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchingGUI gui = new SearchingGUI();
            gui.setVisible(true);
        });
    }
}

