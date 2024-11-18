public class PositiveIntegerPrintout {

    // Constant for warning message
    private static final String WARNING_MESSAGE = "Warning: The number must be positive.";

    /**
     * Outputs positive integer or prints warning message if number is non-positive.
     * 
     * @param number Integer for processing.
     */
    public static void printPositiveInteger(int number) {
        if (number > 0) {
            printOut(String.valueOf(number)); // Converts number to String before printing
        } else {
            printOut(WARNING_MESSAGE);
        }
    }

    /**
     * Simulates output for demonstration purposes.
     * Replace this with appropriate I/O handling as needed.
     * 
     * @param message The message to output.
     */
    private static void printOut(String message) {
        System.out.println(message);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        printPositiveInteger(42);    // Outputs: 42
        printPositiveInteger(0);     // Outputs: Warning: The number must be positive.
        printPositiveInteger(-15);   // Outputs: Warning: The number must be positive.
    }
}
