package algos_data_structures.seminar_1.task_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * =============================================================
 * Project: Seminar 1 in Algorithms and Data Structures
 * Author: Securella
 * Year: 2024
 * Description: This code is part of an individual project for the
 *              seminar and is intended for examination.
 * 
 * Disclaimer:
 * This is original work created for a seminar assignment. It should
 * not be copied, shared, or used as a reference for similar projects.
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * 
 * Source: The recursive binary search algorithm implementation is inspired by the 
 *         GeeksforGeeks article on "Recursive Binary Search" (https://www.geeksforgeeks.org/binary-search/)
 *         and adapted for educational purposes.
 * =============================================================
 */

public class BinarySearch {

    /**
     * Recursive Binary Search Method
     * 
     * @param arr    The sorted array in which to search.
     * @param target The number to find.
     * @param left   The starting index of search range.
     * @param right  The ending index of search range.
     * @return true if target is found, false otherwise.
     */
    public static boolean binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return false; // Base case: search range is empty
        }

        int mid = left + (right - left) / 2; // Prevent overflow

        if (arr[mid] == target) {
            return true; // Target found
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1); // Searching left half
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right); // Searching right half
        }
    }

    /**
     * Public method to perform binary search with error handling
     * 
     * @param arr    The array to search.
     * @param target The number to find.
     * @return true if target is found, false otherwise.
     */
    public static boolean binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            System.err.println("Error: Input array is null or empty.");
            return false;
        }
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    /**
     * Reading array of integers from provided file "Seminar 1 - File with random numbers.txt".
     * 
     * @return Array of integers from file.
     */
    public static int[] fetchNumbersFromFile() {
        try {
            File file = new File("algos_data_structures/Seminar 1 - File with random numbers.txt");
            if (!file.exists()) {
                System.err.println("Error: File 'Seminar 1 - File with random numbers.txt' not found.");
                return new int[0];
            }
    
            Scanner scanner = new Scanner(file);
            int[] numbers = scanner.tokens().mapToInt(Integer::parseInt).toArray();
            scanner.close();
    
            if (numbers.length == 0) {
                System.err.println("Error: The file is empty.");
            }
    
            return numbers;
        } catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new int[0];
        }
    }    
}

