# Iterative Insertion Sort
def iterative_insertion_sort(arr):
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key


# Recursive Insertion Sort
def recursive_insertion_sort(arr):
    def insertion_sort_recursive(arr, n):
        if n <= 1:
            return
        insertion_sort_recursive(arr, n - 1)
        key = arr[n - 1]
        j = n - 2
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key

    insertion_sort_recursive(arr, len(arr))
