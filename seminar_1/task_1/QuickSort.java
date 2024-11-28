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
 * not be copied, shared, or used as a reference for similar projects.
 * Unauthorized use of this code is strictly prohibited by the educational institution.
 * 
 * Sources:
 * 1. "Data Structures and Algorithm Analysis in Java, 3rd Edition" 
 *    by Mark Allen Weiss, Pearson Education, 2012. ISBN: 978-0-273-75211-0.
 * 2. GeeksforGeeks: "QuickSort Algorithm - Multiple Pivot Strategies"
 *    URL: https://www.geeksforgeeks.org/quick-sort/
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

    // Recursive QuickSort with random pivot
    public static void quickSortRecursiveRandom(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionRandom(arr, low, high);
            quickSortRecursiveRandom(arr, low, pivotIndex - 1);
            quickSortRecursiveRandom(arr, pivotIndex + 1, high);
        }
    }

    // Iterative QuickSort with median-of-three pivot
    public static void quickSortIterativeMedian(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int[] stack = new int[arr.length * 2];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            if (low < high) {
                int pivotIndex = partitionMedian(arr, low, high);

                if (pivotIndex + 1 <= high) {
                    stack[++top] = pivotIndex + 1;
                    stack[++top] = high;
                }
                if (low <= pivotIndex - 1) {
                    stack[++top] = low;
                    stack[++top] = pivotIndex - 1;
                }
            }
        }
    }

    // Iterative QuickSort with random pivot
    public static void quickSortIterativeRandom(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int[] stack = new int[arr.length * 2];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            if (low < high) {
                int pivotIndex = partitionRandom(arr, low, high);

                if (pivotIndex + 1 <= high) {
                    stack[++top] = pivotIndex + 1;
                    stack[++top] = high;
                }
                if (low <= pivotIndex - 1) {
                    stack[++top] = low;
                    stack[++top] = pivotIndex - 1;
                }
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
        if (arr == null || arr.length <= 1) return;

        int[] stack = new int[arr.length * 2];
        int top = -1;

        stack[++top] = 0;
        stack[++top] = arr.length - 1;

        while (top >= 0) {
            int high = stack[top--];
            int low = stack[top--];

            if (low < high) {
                int pivotIndex = partitionFirst(arr, low, high);

                if (pivotIndex + 1 <= high) {
                    stack[++top] = pivotIndex + 1;
                    stack[++top] = high;
                }
                if (low <= pivotIndex - 1) {
                    stack[++top] = low;
                    stack[++top] = pivotIndex - 1;
                }
            }
        }
    }

    // Helper method: partition using median-of-three pivot
    private static int partitionMedian(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = median(arr[low], arr[mid], arr[high]);

        while (low <= high) {
            while (low <= high && arr[low] < pivot) low++;
            while (low <= high && arr[high] > pivot) high--;
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
            while (low <= high && arr[low] < pivot) low++;
            while (low <= high && arr[high] > pivot) high--;
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
        int left = low + 1;
        int right = high;

        while (left <= right) {
            while (left <= high && arr[left] <= pivot) left++;
            while (right > low && arr[right] > pivot) right--;
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, low, right);
        return right;
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

    // Debugging helper: print array
    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("Array is null.");
            return;
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}


