package algos_data_structures.seminar_1.task_1;

import java.util.Random;

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

public class QuickSort {
    
    // Recursive QuickSort with median-of-three pivot
    public static void quickSortRecursiveMedian(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionMedian(arr, low, high);
            quickSortRecursiveMedian(arr, low, pivotIndex - 1);
            quickSortRecursiveMedian(arr, pivotIndex + 1, high);
        }
    }

    // Iterative QuickSort with random pivot
    public static void quickSortIterativeRandom(int[] arr) {
        int[] stack = new int[arr.length];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];
            if (low < high) {
                int pivotIndex = partitionRandom(arr, low, high);
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }

    // Recursive QuickSort with pivot = array[0]
    public static void quickSortRecursiveFirstPivot(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionFirst(arr, low, high);
            quickSortRecursiveFirstPivot(arr, low, pivotIndex - 1);
            quickSortRecursiveFirstPivot(arr, pivotIndex + 1, high);
        }
    }

    // Iterative QuickSort with pivot = array[0]
    public static void quickSortIterativeFirstPivot(int[] arr) {
        int[] stack = new int[arr.length];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];
            if (low < high) {
                int pivotIndex = partitionFirst(arr, low, high);
                stack[++top] = low;
                stack[++top] = pivotIndex - 1;
                stack[++top] = pivotIndex + 1;
                stack[++top] = high;
            }
        }
    }

    // Helper method: partition using median-of-three pivot
    private static int partitionMedian(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = median(arr[low], arr[mid], arr[high]);
        while (low <= high) {
            while (arr[low] < pivot) low++;
            while (arr[high] > pivot) high--;
            if (low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    // Helper method: partition using random pivot
    private static int partitionRandom(int[] arr, int low, int high) {
        int pivotIndex = new Random().nextInt(high - low + 1) + low;
        int pivot = arr[pivotIndex];
        while (low <= high) {
            while (arr[low] < pivot) low++;
            while (arr[high] > pivot) high--;
            if (low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    // Helper method: partition using pivot = array[0]
    private static int partitionFirst(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low <= high) {
            while (arr[low] < pivot) low++;
            while (arr[high] > pivot) high--;
            if (low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    // Median of three
    private static int median(int a, int b, int c) {
        return a > b ? (b > c ? b : Math.min(a, c)) : (a > c ? a : Math.min(b, c));
    }

    // Swap helper
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

