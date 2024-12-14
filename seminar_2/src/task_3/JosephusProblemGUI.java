package task_3;

import javax.swing.*;
import java.awt.*;

/**
 * GUI with all Josephus Problem implementations.
 */
public class JosephusProblemGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JosephusProblemGUI().createGUI());
    }

    public void createGUI() {
        JFrame frame = new JFrame("Josephus Problem Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel labelN = new JLabel("Enter number of people, any (N):");
        JTextField inputN = new JTextField();
        JLabel labelM = new JLabel("Enter step count, preferably 0 or 1 (M):");
        JTextField inputM = new JTextField();

        inputPanel.add(labelN);
        inputPanel.add(inputN);
        inputPanel.add(labelM);
        inputPanel.add(inputM);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 4, 10, 10));

        JButton buttonArrayList = new JButton("ArrayList");
        JButton buttonIterator = new JButton("ArrayList Iterator");
        JButton buttonLinkedList = new JButton("LinkedList");
        JButton buttonLinkedListIterator = new JButton("LinkedList Iterator");

        buttonsPanel.add(buttonArrayList);
        buttonsPanel.add(buttonIterator);
        buttonsPanel.add(buttonLinkedList);
        buttonsPanel.add(buttonLinkedListIterator);

        // Output Panel
        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Adding panels to frame
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Action Listeners for Buttons
        buttonArrayList.addActionListener(e -> solveJosephusUsingArrayList(inputN, inputM, outputArea));
        buttonIterator.addActionListener(e -> solveJosephusUsingIterator(inputN, inputM, outputArea));
        buttonLinkedList.addActionListener(e -> solveJosephusUsingLinkedList(inputN, inputM, outputArea));
        buttonLinkedListIterator.addActionListener(e -> solveJosephusUsingLinkedListIterator(inputN, inputM, outputArea));

        frame.setVisible(true);
    }

    private void solveJosephusUsingArrayList(JTextField inputN, JTextField inputM, JTextArea outputArea) {
        StringBuilder output = new StringBuilder();
        try {
            int n = Integer.parseInt(inputN.getText().trim());
            int m = Integer.parseInt(inputM.getText().trim());
            validateInput(n, m);
            output.append("Solving Josephus Problem using ArrayList...\n");
            int winner = JosephusWithArrayList.solveJosephus(n, m, output);
            output.append("\n>> Winner is: ").append(winner).append(" <<");
            outputArea.setText(output.toString());
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void solveJosephusUsingIterator(JTextField inputN, JTextField inputM, JTextArea outputArea) {
        StringBuilder output = new StringBuilder();
        try {
            int n = Integer.parseInt(inputN.getText().trim());
            int m = Integer.parseInt(inputM.getText().trim());
            validateInput(n, m);
            output.append("Solving Josephus Problem using ArrayList with Iterator...\n");
            int winner = JosephusWithIterator.solveJosephus(n, m, output);
            output.append("\n>> Winner is: ").append(winner).append(" <<");
            outputArea.setText(output.toString());
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void solveJosephusUsingLinkedList(JTextField inputN, JTextField inputM, JTextArea outputArea) {
        StringBuilder output = new StringBuilder();
        try {
            int n = Integer.parseInt(inputN.getText().trim());
            int m = Integer.parseInt(inputM.getText().trim());
            validateInput(n, m);
            output.append("Solving Josephus Problem using LinkedList...\n");
            int winner = JosephusWithLinkedList.solveJosephus(n, m, output);
            output.append("\n>> Winner is: ").append(winner).append(" <<");
            outputArea.setText(output.toString());
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void solveJosephusUsingLinkedListIterator(JTextField inputN, JTextField inputM, JTextArea outputArea) {
        StringBuilder output = new StringBuilder();
        try {
            int n = Integer.parseInt(inputN.getText().trim());
            int m = Integer.parseInt(inputM.getText().trim());
            validateInput(n, m);
            output.append("Solving Josephus Problem using LinkedList with Iterator...\n");
            int winner = JosephusWithLinkedListIterator.solveJosephus(n, m, output);
            output.append("\n>> Winner is: ").append(winner).append(" <<");
            outputArea.setText(output.toString());
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void validateInput(int n, int m) {
        if (n <= 0 || m < 0) {
            throw new IllegalArgumentException("N must be greater than 0 and M must be non-negative.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

