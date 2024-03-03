import sys
import random

# Set recursion limit to avoid RecursionError
sys.setrecursionlimit(10**6)


def median_of_three(arr, low, high):
    mid = (low + high) // 2
    if arr[low] < arr[mid]:
        if arr[mid] < arr[high]:
            return mid
        elif arr[low] < arr[high]:
            return high
        else:
            return low
    else:
        if arr[low] < arr[high]:
            return low
        elif arr[mid] < arr[high]:
            return high
        else:
            return mid


def random_pivot(arr, low, high):
    return random.randint(low, high)


def first_element_pivot(arr, low, high):
    return low


def iterative_quick_sort(arr, pivot_selector=random_pivot):  # Updated function signature to accept pivot selector
    stack = []
    stack.append(0)
    stack.append(len(arr) - 1)
    while stack:
        high = stack.pop()
        low = stack.pop()
        pivot_index = partition(arr, low, high, pivot_selector)  # Pass pivot selector to partition
        if pivot_index - 1 > low:
            stack.append(low)
            stack.append(pivot_index - 1)
        if pivot_index + 1 < high:
            stack.append(pivot_index + 1)
            stack.append(high)


def recursive_quick_sort(arr, pivot_selector=random_pivot):  # Updated function signature to accept pivot selector
    def quick_sort_recursive(arr, low, high):
        if low < high:
            pivot_index = partition(arr, low, high, pivot_selector)  # Pass pivot selector to partition
            quick_sort_recursive(arr, low, pivot_index - 1)
            quick_sort_recursive(arr, pivot_index + 1, high)

    quick_sort_recursive(arr, 0, len(arr) - 1)


def partition(arr, low, high, pivot_selector):
    pivot_index = pivot_selector(arr, low, high)  # Use pivot selector to select pivot index
    arr[pivot_index], arr[high] = arr[high], arr[pivot_index]

    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1
