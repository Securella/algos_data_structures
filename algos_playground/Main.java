package algos_data_structures.algos_playground;
// Class for my playground in algorithms and data structures
public class Main {

    // Method to print each digit of a non-negative integer n
    public static void printOut(int n) {
        if (n >= 10)
            printOut(n / 10); // Recursive call with the number divided by 10

        printDigit(n % 10); // Print the last digit
    }

    // Method to print a single digit
    public static void printDigit(int digit) {
        System.out.print(digit); // Print the digit without a newline
    }

    // Class for simulating a simple memory cell
    static class MemoryCell {
        private Object storedValue;

        public void write(Object x) {
            storedValue = x;
        }

        public Object read() {
            return storedValue;
        }
    }

    public static void main(String[] args) {
        // Part 1: Output for Recursive routine to print an integer
        int number = 12345;
        System.out.print("Output for Recursive routine to print an integer: ");
        printOut(number);
        System.out.println(); // Print a newline for clarity

        // Part 2: Output for Integer wrapper class
        MemoryCell m = new MemoryCell();
        m.write(Integer.valueOf(37)); // Using Integer wrapper class with Integer.valueOf
        Integer wrapperVal = (Integer) m.read(); // Reading the Integer from MemoryCell
        int val = wrapperVal.intValue(); // Converting Integer to int
        System.out.println("Output for Integer wrapper class: Contents are: " + val);
    }
}

