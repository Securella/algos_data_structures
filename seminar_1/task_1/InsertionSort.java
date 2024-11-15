package algos_data_structures.seminar_1.task_1;

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
 * not be copied, shared, or used as a reference for similar projects without permission
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * =============================================================
 */

public class InsertionSort {
    // Recursive InsertionSort
    public static void insertionSortRecursive(int[] arr, int n) {
        if (n <= 1) return;
        insertionSortRecursive(arr, n - 1);
        int last = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > last) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = last;
    }

    // Iterative InsertionSort
    public static void insertionSortIterative(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

