import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class FactorialCalculator {

    /*
     * This program calculates factorial of given number or calculate automatically.
     * The user is prompted to choose to:
     * 1. Input a number manually and calculate its factorial, or
     * 2. Generate 10 random numbers, calculate their factorials, and display results in ascending order.
     */

    // Method to calculate factorial of number using BigInteger
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Prompt user until they provide a valid choice (1 or 2)
        while (choice != 1 && choice != 2) {
            System.out.println("Choose an option:");
            System.out.println("1. Enter a number manually to calculate its factorial.");
            System.out.println("2. Automatically calculate factorials for 10 random numbers (results in ascending order).");

            // Check if input is integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                // If input is not integer, clear buffer and prompt again
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next(); // Clear the invalid input
            }
        }

        if (choice == 1) {
            // Manual input
            System.out.print("Enter a number to calculate its factorial: ");
            while (!scanner.hasNextInt()) { // Ensure user enters 1 integer
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
            int number = scanner.nextInt();
            try {
                BigInteger fact = factorial(number);
                System.out.println("Factorial of " + number + " is: " + fact);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 2) {
            // Automatic calculation for 10 random numbers
            Random random = new Random();
            int[] numbers = new int[10];

            // Generate 10 random numbers between 1 and 20 (to avoid extremely large outputs)
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = random.nextInt(20) + 1;
            }

            // Calculate factorials and store them as pairs (number, factorial)
            BigInteger[][] results = new BigInteger[10][2];
            for (int i = 0; i < numbers.length; i++) {
                results[i][0] = BigInteger.valueOf(numbers[i]); // Store the number
                results[i][1] = factorial(numbers[i]);          // Store its factorial
            }

            // Sort results by number in ascending order
            Arrays.sort(results, Comparator.comparing(a -> a[0]));

            // Display results
            System.out.println("Number   Factorial");
            System.out.println("-------------------");
            for (BigInteger[] pair : results) {
                System.out.printf("%-9d %s\n", pair[0], pair[1]);
            }
        }

        scanner.close();
    }
}

