package algos_data_structures.algos_playground;
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

    public static void main(String[] args) {
        int number = 12345;
        System.out.print("Output: ");
        printOut(number);
        System.out.println(); // Print a newline at the end for clarity
    }
}

