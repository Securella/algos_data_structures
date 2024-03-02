import sys

# Set recursion limit to avoid RecursionError
sys.setrecursionlimit(10**6)


# Iterative Quick Sort
def iterative_quick_sort(arr):
    # Stack for storing indices
    stack = []
    # Push initial values of low and high to stack
    stack.append(0)
    stack.append(len(arr) - 1)
    # Loop to pop from stack while it is not empty
    while stack:
        high = stack.pop()
        low = stack.pop()
        # Partition the array
        pivot = arr[high]
        i = low - 1
        for j in range(low, high):
            if arr[j] < pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        pivot_index = i + 1
        # Push subarrays to stack
        if pivot_index - 1 > low:
            stack.append(low)
            stack.append(pivot_index - 1)
        if pivot_index + 1 < high:
            stack.append(pivot_index + 1)
            stack.append(high)


def recursive_quick_sort(arr):
    # Partition function remains the same
    def partition(arr, low, high):
        pivot = arr[high]
        i = low - 1
        for j in range(low, high):
            if arr[j] <= pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        return i + 1

    # Recursive quick sort function
    def quick_sort_recursive(arr, low, high):
        if low < high:
            if high - low + 1 <= 1:
                return
            pivot_index = partition(arr, low, high)
            quick_sort_recursive(arr, low, pivot_index - 1)
            quick_sort_recursive(arr, pivot_index + 1, high)

    quick_sort_recursive(arr, 0, len(arr) - 1)
