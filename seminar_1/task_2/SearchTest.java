package algos_data_structures.seminar_1.task_2;

import java.util.Arrays;

/*
 * =============================================================
 * Project: Seminar 1 in Algorithms and Data Structures
 * Author: Securella
 * Year: 2024
 * Description: This class tests the BinarySearch implementation.
 * 
 * Disclaimer:
 * This is individual work created for a seminar assignment. It should
 * not be copied, shared, or used as a reference for similar projects.
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * =============================================================
 */

public class SearchTest {

    public static void main(String[] args) {
        // Fetch numbers from the file
        int[] numbers = BinarySearch.fetchNumbersFromFile();

        // Check if the file was properly read
        if (numbers == null || numbers.length == 0) {
            System.err.println("Error: The file 'Seminar 1 - File with random numbers.txt' is empty or not found.");
            return;
        }

        // Ensure the array is sorted for binary search
        Arrays.sort(numbers);

        // Display the array
        System.out.println("Array: " + Arrays.toString(numbers));

        // Test cases
        int target1 = 7;
        int target2 = 20;

        System.out.println("Searching for " + target1 + ": " + BinarySearch.binarySearch(numbers, target1));
        System.out.println("Searching for " + target2 + ": " + BinarySearch.binarySearch(numbers, target2));

        // Edge case - Empty Array
        int[] emptyArray = new int[0];
        System.out.println("Edge Case - Empty Array: " + BinarySearch.binarySearch(emptyArray, target1));

        // Edge case - Single Element Array (target exists)
        int[] singleElementArray = {7};
        System.out.println("Edge Case - Single Element Array (7): " + BinarySearch.binarySearch(singleElementArray, 7));

        // Edge case - Single Element Array (target does not exist)
        System.out.println("Edge Case - Single Element Array (10): " + BinarySearch.binarySearch(singleElementArray, 10));
    }
}

