package seminar_3.src.task_3;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Task3GUI extends JFrame {

    private final JTable gridTable;
    private final JTextArea resultsArea;
    private final DefaultTableModel gridModel;
    private int colorIndex = 0;
    private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.CYAN, Color.PINK};
    private final char[][] grid = {
            {'t', 'h', 'i', 's'},
            {'w', 'a', 't', 's'},
            {'o', 'a', 'h', 'g'},
            {'f', 'g', 'd', 't'}
    };
    private final List<String> words = Arrays.asList("this", "two", "fat", "that");
    private WordPuzzleSolver solver;

    public Task3GUI() {
        setTitle("Word Puzzle Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        solver = new WordPuzzleSolver(grid, words);

        setLayout(new BorderLayout());

        // Button Panel (BOTTOM)
        JButton solveButton = new JButton("Solve Puzzle");
        solveButton.setFont(new Font("Arial", Font.BOLD, 16));
        solveButton.addActionListener(e -> new Thread(() -> solvePuzzle()).start());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(solveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Grid Table (CENTER)
        String[] columnHeaders = {"", "1", "2", "3", "4"};
        gridModel = new DefaultTableModel(columnHeaders, 0);

        for (int row = 0; row < grid.length; row++) {
            Object[] rowData = new Object[grid[row].length + 1];
            rowData[0] = String.valueOf(row + 1);
            for (int col = 0; col < grid[row].length; col++) {
                rowData[col + 1] = grid[row][col];
            }
            gridModel.addRow(rowData);
        }

        gridTable = new JTable(gridModel);
        gridTable.setRowHeight(50);
        gridTable.setFont(new Font("Arial", Font.BOLD, 20));
        gridTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        gridTable.setRowSelectionAllowed(false);
        gridTable.setColumnSelectionAllowed(false);
        gridTable.setDefaultRenderer(Object.class, new HighlightRenderer());
        add(new JScrollPane(gridTable), BorderLayout.CENTER);

        // Results Panel (BEGINNING))
        JPanel resultsPanel = new JPanel(new BorderLayout());
        JLabel resultsLabel = new JLabel("Words Found:");
        resultsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        resultsPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        add(resultsPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void solvePuzzle() {
        Set<WordPuzzleSolver.FoundWord> foundWords = solver.solve();

        SwingUtilities.invokeLater(() -> {
            resultsArea.setText("");
            resetHighlighting();
            colorIndex = 0;
        });

        for (WordPuzzleSolver.FoundWord word : foundWords) {
            Color color = getNextColor();
            animateHighlightWord(word, color);
            SwingUtilities.invokeLater(() -> {
                if (resultsArea.getText().isEmpty()) {
                    resultsArea.append(word.word);
                } else {
                    resultsArea.append(", " + word.word);
                }
            });
        }
    }

    private void resetHighlighting() {
        ((HighlightRenderer) gridTable.getDefaultRenderer(Object.class)).clearHighlights();
        gridTable.repaint();
    }

    private void animateHighlightWord(WordPuzzleSolver.FoundWord word, Color color) {
        int dr = Integer.compare(word.endRow, word.startRow);
        int dc = Integer.compare(word.endCol, word.startCol);

        int r = word.startRow, c = word.startCol;
        while (r != word.endRow + dr || c != word.endCol + dc) {
            int currentRow = r;
            int currentCol = c;
            SwingUtilities.invokeLater(() -> {
                ((HighlightRenderer) gridTable.getDefaultRenderer(Object.class)).addHighlight(currentRow, currentCol + 1, color);
                gridTable.repaint();
            });
            try {
                Thread.sleep(500); // Delay for animation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r += dr;
            c += dc;
        }
    }

    private Color getNextColor() {
        Color color = colors[colorIndex % colors.length];
        colorIndex++;
        return color;
    }

    private class HighlightRenderer extends DefaultTableCellRenderer {
        private final Map<Point, Color> highlights = new HashMap<>();

        public void addHighlight(int row, int col, Color color) {
            highlights.put(new Point(col, row), color);
        }

        public void clearHighlights() {
            highlights.clear();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (highlights.containsKey(new Point(column, row))) {
                c.setBackground(highlights.get(new Point(column, row)));
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }
            return c;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task3GUI::new);
    }
}
