package algos_data_structures.seminar_1.task_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SortingGUI extends JFrame {
    private JTextField arraySizesField;
    private JTextArea outputArea;
    private JButton runButton;
    private JProgressBar progressBar;

    public SortingGUI() {
        setTitle("Seminar in Algorithms - Sorting GUI");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for user input
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        arraySizesField = new JTextField("100,1000,10000");
        topPanel.add(new JLabel("Enter Array Sizes (comma-separated):"));
        topPanel.add(arraySizesField);

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Output area for logs
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Run button
        runButton = new JButton("Run All Algorithms");
        runButton.addActionListener((ActionEvent e) -> runBulkTests());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
        add(runButton, BorderLayout.SOUTH);
    }

    private void runBulkTests() {
        String[] sizeStrings = arraySizesField.getText().split(",");
        int[] sizes = new int[sizeStrings.length];

        try {
            for (int i = 0; i < sizeStrings.length; i++) {
                sizes[i] = Integer.parseInt(sizeStrings[i].trim());
            }
        } catch (NumberFormatException e) {
            outputArea.append("Invalid array sizes. Please enter valid numbers.\n");
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
                    writer.write("Algorithm,Array Size,Execution Time (ms)\n");
                    String[] algorithms = {
                        "QuickSort - Recursive (Median Pivot)",
                        "QuickSort - Iterative (Random Pivot)",
                        "InsertionSort - Recursive",
                        "InsertionSort - Iterative"
                    };

                    progressBar.setMaximum(algorithms.length * sizes.length);
                    int progress = 0;

                    for (String algorithm : algorithms) {
                        for (int size : sizes) {
                            int[] array = loadArray(inputFile, size);
                            if (array == null) {
                                outputArea.append("Error loading array for size " + size + ".\n");
                                continue;
                            }

                            long startTime = System.nanoTime();
                            switch (algorithm) {
                                case "QuickSort - Recursive (Median Pivot)":
                                    QuickSort.quickSortRecursiveMedian(array, 0, array.length - 1);
                                    break;
                                case "QuickSort - Iterative (Random Pivot)":
                                    QuickSort.quickSortIterativeRandom(array);
                                    break;
                                case "InsertionSort - Recursive":
                                    InsertionSort.insertionSortRecursive(array, array.length);
                                    break;
                                case "InsertionSort - Iterative":
                                    InsertionSort.insertionSortIterative(array);
                                    break;
                            }
                            long endTime = System.nanoTime();

                            double executionTime = (endTime - startTime) / 1e6;
                            writer.write(String.format("%s,%d,%.2f\n", algorithm, size, executionTime));
                            outputArea.append(String.format("%s - Size %d: %.2f ms\n", algorithm, size, executionTime));

                            progressBar.setValue(++progress);
                        }
                    }
                    outputArea.append("All tests completed. Results saved to sorting_results.csv.\n");
                } catch (IOException e) {
                    outputArea.append("Error writing to results file.\n");
                }
                return null;
            }
        };

        worker.execute();
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingGUI gui = new SortingGUI();
            gui.setVisible(true);
        });
    }
}
