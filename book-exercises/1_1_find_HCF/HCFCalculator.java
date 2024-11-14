import java.util.Random;

public class HCFCalculator {

    /*
     * Program to calculate the Highest Common Factor (HCF) of 'n' numbers.
     * using Euclidean algorithm to find HCF of 2 numbers and then 
     * apply it iteratively over an array of numbers to find the HCF of 'n' numbers.
     * This program generates multiple test cases with random values and measures the 
     * running time for each test case as the input size increases, displaying results in a table.
     */

    // Method to find HCF of 2 numbers using Euclidean algorithm
    public static int hcf(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Method to find HCF of array of numbers
    public static int hcfOfArray(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = hcf(result, arr[i]);
            if (result == 1) {
                // If HCF becomes 1, can break early as 1 is the minimum possible HCF
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Define max array size to test HCF with up to 10 numbers
        int maxArraySize = 10;
        Random random = new Random();

        System.out.printf("%-20s %-10s %-20s\n", "Number of Inputs", "HCF", "Running Time (ns)");
        System.out.println("---------------------------------------------------------");

        for (int i = 2; i <= maxArraySize; i++) { // Start from 2 elements up to maxArraySize
            int[] numbers = new int[i];

            // Fill array with random numbers from 1 to 1000
            for (int j = 0; j < i; j++) {
                numbers[j] = random.nextInt(1000) + 1;
            }

            // Measure running time
            long startTime = System.nanoTime();
            int hcf = hcfOfArray(numbers); // Calculate HCF of current test case
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            // Display result in formatted table
            System.out.printf("%-20d %-10d %-20d\n", numbers.length, hcf, duration);
        }
    }
}

