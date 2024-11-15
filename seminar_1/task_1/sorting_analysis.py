# =============================================================
# Project: Seminar 1 in Algorithms and Data Structures
# Author: Securella
# Year: 2024
# Description: This code is part of an individual project for the
#              seminar and is intended for examination.
#
# Disclaimer:
# This is individual work created for a seminar assignment. It should
# not be copied, shared, or used as a reference for similar project.
# Unauthorized use of this code is strictly prohibited by the educational institution.
# =============================================================

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Read CSV file with algo results into pandas DataFrame
data = pd.read_csv("sorting_results.csv")

# Pivot data to structure it by method and input size for easier plotting
table = data.pivot(index="Algorithm", columns="Array Size", values="Execution Time (ms)")

# Display table for reference
print("Execution Time Table (ms):")
print(table)

# Save formatted table as CSV file
table.to_csv("formatted_sorting_results.csv")

# Define input sizes for plotting theoretical complexity lines
input_sizes = np.array(table.columns)

# Theoretical complexity curves for reference
quicksort_theoretical = input_sizes * np.log2(input_sizes)  # O(n log n) for QuickSort
insertion_theoretical = input_sizes ** 2                    # O(n^2) for InsertionSort

# 1. Separate plots for each sorting algorithm
for algorithm in table.index:
    plt.figure(figsize=(8, 5))
    plt.plot(table.columns, table.loc[algorithm], marker='o', color='b', label=algorithm)
    plt.title(f"{algorithm} Execution Time")
    plt.xlabel("Array Size")
    plt.ylabel("Execution Time (ms)")
    plt.xscale("log")
    plt.yscale("log")
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.legend()
    plt.savefig(f"{algorithm}_performance_plot.png")
    plt.show()

# 2. Grouped plot for all QuickSort variants with O(n log n) theoretical line
plt.figure(figsize=(10, 6))
quick_sort_methods = [alg for alg in table.index if "QuickSort" in alg]
for algorithm in quick_sort_methods:
    plt.plot(table.columns, table.loc[algorithm], marker='o', label=algorithm)
plt.plot(input_sizes, quicksort_theoretical / quicksort_theoretical[-1] * table.loc[quick_sort_methods[0]].max(),
         linestyle='--', color='gray', label="O(n log n) theoretical")
plt.title("QuickSort Variants with Theoretical O(n log n) Complexity")
plt.xlabel("Array Size")
plt.ylabel("Execution Time (ms)")
plt.xscale("log")
plt.yscale("log")
plt.legend(title="QuickSort Method")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.savefig("quick_sort_with_theoretical.png")
plt.show()

# 3. Grouped plot for all InsertionSort variants with O(n^2) theoretical line
plt.figure(figsize=(8, 5))
insertion_methods = [alg for alg in table.index if "InsertionSort" in alg]
for algorithm in insertion_methods:
    plt.plot(table.columns, table.loc[algorithm], marker='o', label=algorithm)
plt.plot(input_sizes, insertion_theoretical / insertion_theoretical[-1] * table.loc[insertion_methods[0]].max(),
         linestyle='--', color='gray', label="O(n^2) theoretical")
plt.title("InsertionSort Variants with Theoretical O(n^2) Complexity")
plt.xlabel("Array Size")
plt.ylabel("Execution Time (ms)")
plt.xscale("log")
plt.yscale("log")
plt.legend(title="InsertionSort Method")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.savefig("insertion_sort_with_theoretical.png")
plt.show()
