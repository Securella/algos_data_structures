# code looks nice and nead thanks to Copilot
"""Main module for running sorting and searching algorithms."""

import time
import random

from sorting_algos.insertion_sort import iterative_insertion_sort, recursive_insertion_sort
from sorting_algos.quick_sort import iterative_quick_sort, recursive_quick_sort
from searching_algos.binary_search import binary_search


def read_random_numbers(file_path):
    try:
        with open(file_path, 'r') as file:
            numbers = [int(line.strip()) for line in file]
            return numbers
    except FileNotFoundError:
        print("Error: File not found.")
        return []
    except Exception as e:
        print("Error reading file:", e)
        return []


def generate_sorted_list(size):
    return sorted(random.sample(range(-100, 100), size))


def run_algorithm(algorithm, numbers, number_of_runs, target):
    total_time = 0
    result = False
    for _ in range(number_of_runs):
        array = list(numbers)
        start_time = time.time()
        if algorithm == "Insertion Sort":
            iterative_insertion_sort(array)
        elif algorithm == "Recursive Insertion Sort":
            recursive_insertion_sort(array)
        elif algorithm == "Iterative Quick Sort":
            iterative_quick_sort(array)
        elif algorithm == "Recursive Quick Sort":
            recursive_quick_sort(array)
        elif algorithm == "Binary Search":
            result = binary_search(array, target, 0, len(array) - 1)
            if result:
                break
        else:
            continue
        end_time = time.time()
        total_time += (end_time - start_time)
    average_time = total_time / number_of_runs
    print(f"Average time for {algorithm}: {average_time:.6f} seconds")
    if algorithm == "Binary Search":
        if result:
            print("Target found in the array.")
        else:
            print("Target not found in the array.")
    else:
        print(f"Algorithm {algorithm}: Done.")


def main():
    choice = int(input("Enter 1 to read numbers from a text file or 2 to generate random numbers: "))

    if choice == 1:
        file_path = input("Enter the file path containing random numbers: ")
        numbers = read_random_numbers(file_path)
        if not numbers:
            print("Error: Could not read numbers from the file.")
            return
    elif choice == 2:
        array_size = int(input("Enter the size of the array: "))
        numbers = generate_sorted_list(array_size)
    else:
        print("Invalid choice. Please enter 1 or 2.")
        return

    algorithms = ["Iterative Insertion Sort", "Recursive Insertion Sort", "Iterative Quick Sort",
                  "Recursive Quick Sort", "Binary Search"]

    print("\nMenu:")
    for i, algorithm in enumerate(algorithms, start=1):
        print(f"{i}. {algorithm}")

    choice = int(input("\nEnter your choice (1-5): "))

    if 1 <= choice <= len(algorithms):
        if algorithms[choice - 1] == "Binary Search":
            target = int(input("Enter the target for binary search: "))
            run_algorithm(algorithms[choice - 1], numbers, 1, target)  # Only 1 run for binary search
        else:
            number_of_runs = int(input("Enter the number of runs: "))  # Prompt for number of runs for sorting algorithms
            run_algorithm(algorithms[choice - 1], numbers, number_of_runs, None)  # Pass None for target for sorting algorithms
    else:
        print("Invalid choice. Please enter a number between 1 and 5.")


if __name__ == "__main__":
    main()
