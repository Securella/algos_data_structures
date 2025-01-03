// Word puzzle
// Requirements:
// 1. Store all prefixes of each word in dictionary.
// 2. Scan grid for words in all 8 possible directions.
// 3. Terminate scans early if the current string is not a valid prefix.
// 4. Efficiently find words and return their positions.
// 5. Optimize running time to O(R⋅C+W), where R,C are rows and columns, and 
// W is number of words.

package seminar_3.src.task_3;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class WordPuzzleSolver {

    private final char[][] grid; // Requirement #1: 2D array of letters
    private final Set<String> dictionary; // Requirement #1: Store list of words
    private final Set<String> prefixes; // Requirement #2: Store all prefixes of words

    /**
     * Constructor to initialize WordPuzzleSolver with grid and word list.
     * - Requirement #1: Accepting 2D grid of letters and list of words.
     * - Requirement #2: Storing all prefixes of given words for optimization.
     * @param grid 2D array of characters representing puzzle grid.
     * @param words List of words to search for in grid.
     */
    public WordPuzzleSolver(char[][] grid, List<String> words) {
        this.grid = grid;
        this.dictionary = new HashSet<>(words); // Store all words for fast lookup (O(1)).
        this.prefixes = new HashSet<>();

        // Generating prefixes for all words in dictionary.
        // Requirement #2: Storing prefixes to terminate invalid scans early.
        for (String word : words) {
            for (int i = 1; i <= word.length(); i++) {
                prefixes.add(word.substring(0, i));
            }
        }
    }

    /**
     * Solving word puzzle by finding all words in grid.
     * - Requirement #3: Finding words in all 8 directions.
     * - Requirement #4: Early termination for invalid prefixes.
     * - Requirement #5: Optimizing for larger grids.
     */
    public Set<FoundWord> solve() {
        Set<FoundWord> foundWords = new HashSet<>();
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions for 8 possible movements
        // Requirement #3: Horizontal, vertical, and diagonal scanning.
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // vertical and horizontal
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // diagonal
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : directions) {
                    StringBuilder currentWord = new StringBuilder();
                    int r = row, c = col;

                    while (r >= 0 && r < rows && c >= 0 && c < cols) {
                        currentWord.append(grid[r][c]);

                        String currentString = currentWord.toString();
                        // Requirement #4: Terminate if not valid prefix.
                        if (!prefixes.contains(currentString)) break;

                        if (dictionary.contains(currentString)) {
                            // Found valid word in dictionary
                            foundWords.add(new FoundWord(currentString, row, col, r, c));
                        }

                        r += dir[0];
                        c += dir[1];
                    }
                }
            }
        }
        return foundWords;
    }

    /**
     * Found word with its start and end positions in grid.
     */
    public static class FoundWord {
        public final String word;
        public final int startRow;
        public final int startCol;
        public final int endRow;
        public final int endCol;

        public FoundWord(String word, int startRow, int startCol, int endRow, int endCol) {
            this.word = word;
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
        }
    }
}
