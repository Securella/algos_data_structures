# Task 2: binary_search.py
"""method uses Binary Search algorithm to find out if number sent as
parameter to method existing in array that is also sent to method as parameter.
BinarySearch method must be implemented as recursive method and shall
return true if the number is found in the array and false if it is not found."""

from typing import List


def binary_search(numbers: List[int], number_to_find: int, low: int, high: int) -> bool:
    """Search for number in sorted array.

    Args:
        numbers (List[int]): The sorted array to search in.
        number_to_find (int): The number to search for.
        low (int): The lower index of the current search range.
        high (int): The upper index of the current search range.

    Returns:
        bool: True if the number is found in the array, False otherwise.
    """
    if not numbers or low > high:
        return False  # Element not found or invalid input

    middle_position = low + (high - low) // 2
    middle_number = numbers[middle_position]

    if number_to_find == middle_number:
        return True
    elif number_to_find < middle_number:
        return binary_search(numbers, number_to_find, low, middle_position - 1)
    else:
        return binary_search(numbers, number_to_find, middle_position + 1, high)

# TODO: error handling and boundary checks for cases where the input list is not sorted
# or if the target is out of bounds.