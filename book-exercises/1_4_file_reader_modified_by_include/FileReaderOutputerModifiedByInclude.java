import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileReaderOutputerModifiedByInclude {

    private static final int MAX_DEPTH = 100; // Prevent excessive recursion
    private static final Set<String> visitedFiles = new HashSet<>(); // To avoid duplicate inclusions

    /**
     * Processes file and replaces #include statements with content of included files.
     *
     * @param filename = name of file for processing.
     * @param depth    Current recursion depth (to prevent infinite loops).
     * @throws IOException In case of I/O error.
     */
    public static void processFile(String filename, int depth) throws IOException {
        if (depth > MAX_DEPTH) {
            throw new IOException("Include depth exceeded for file: " + filename);
        }

        if (visitedFiles.contains(filename)) {
            System.out.println("// Skipping already included file: " + filename);
            return;
        }
        visitedFiles.add(filename);

        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("Error: File not found: " + filename);
            return;
        }

        System.out.println("// Begin processing file: " + filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Processing line: " + line); // Debug line content
                line = line.trim();

                // Check for #include statements
                if (line.startsWith("#include")) {
                    String includedFile = parseIncludeStatement(line);
                    if (includedFile != null) {
                        System.out.println("Including file: " + includedFile); // Debug included file
                        processFile(includedFile, depth + 1); // Recursively process included file
                    } else {
                        System.err.println("Error: Invalid #include syntax in file: " + filename);
                    }
                } else {
                    // Print the current line
                    System.out.println(line);
                }
            }
        }

        System.out.println("// End processing file: " + filename);
    }

    /**
     * Parses the #include statement to extract the filename.
     *
     * @param line = line containing the #include statement.
     * @return filename if valid, otherwise null.
     */
    private static String parseIncludeStatement(String line) {
        int start = line.indexOf('"');
        int end = line.lastIndexOf('"');
        if (start != -1 && end != -1 && start < end) {
            return line.substring(start + 1, end); // Extract filename
        }
        return null; // Invalid #include syntax
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("The only possible usage: java FileReaderOutputerModifiedByInclude <filename>");
            return;
        }

        String filename = args[0];
        try {
            processFile(filename, 0);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

