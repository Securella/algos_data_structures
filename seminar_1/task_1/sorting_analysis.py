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

# Read and clean CSV file
data = pd.read_csv("sorting_results.csv", delimiter=",")  # Ensure correct delimiter

# Check if "Execution Time (ms)" column is string
if data["Execution Time (ms)"].dtype == "object":
    # Clean data: Replace commas with dots in numeric fields
    data["Execution Time (ms)"] = data["Execution Time (ms)"].str.replace(",", ".").astype(float)
else:
    # Ensure numeric type
    data["Execution Time (ms)"] = pd.to_numeric(data["Execution Time (ms)"], errors="coerce")

data["Array Size"] = pd.to_numeric(data["Array Size"], errors="coerce")
data["Algorithm"] = data["Algorithm"].astype(str).str.strip()

# Check for duplicates caused by repetitions
if "Repetitions" in data.columns:
    # Calculate average execution time over repetitions
    data = data.groupby(["Algorithm", "Array Size"], as_index=False).agg({"Execution Time (ms)": "mean"})

# Data cleaning: Drop rows with missing or invalid values
data = data.dropna()  # Drop rows with NaN values
data = data[data["Array Size"] > 0]  # Remove zero or negative array sizes
data = data.sort_values(by="Array Size")  # Sort data by Array Size for logical ordering

# Pivot data to structure it by method and input size for easier plotting
try:
    table = data.pivot(index="Algorithm", columns="Array Size", values="Execution Time (ms)")
except ValueError as e:
    print("Error pivoting data:", e)
    print(data.head())
    exit()

# Display table for reference
print("Execution Time Table (ms):")
print(table)

# Save formatted table as CSV file
table.to_csv("formatted_sorting_results.csv")

# Define input sizes for plotting theoretical complexity lines
input_sizes = np.array(table.columns, dtype=int)
if input_sizes.min() <= 0:
    raise ValueError("Array Size contains non-positive values, cannot use log scale.")

# Theoretical complexity curves for reference
quicksort_theoretical = input_sizes * np.log2(input_sizes)  # O(n log n) for QuickSort
insertion_theoretical = input_sizes ** 2                    # O(n^2) for InsertionSort

# 1. Separate plots for each sorting algorithm
for algorithm in table.index:
    plt.figure(figsize=(8, 5))
    plt.plot(table.columns, table.loc[algorithm], marker='o', color='b', label=algorithm)
    plt.title(f"{algorithm} Execution Time (Averaged Over Repetitions)")
    plt.xlabel("Array Size")
    plt.ylabel("Execution Time (ms)")
    plt.xscale("log")
    plt.yscale("log")
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.legend()
    plt.savefig(f"{algorithm}_performance_plot.png")
    plt.show()

# 2. Grouped plot for all QuickSort variants with O(n log n) theoretical line
# Debug: Check table index
print("Table Index (Algorithms):", list(table.index))

quick_sort_methods = [alg for alg in table.index if "QuickSort" in alg]
if not quick_sort_methods:
    print("Warning: No QuickSort methods found in the data. Skipping QuickSort plot.")
else:
    plt.figure(figsize=(10, 6))
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
insertion_methods = [alg for alg in table.index if "InsertionSort" in alg]
if not insertion_methods:
    print("Warning: No InsertionSort methods found in the data. Skipping InsertionSort plot.")
else:
    plt.figure(figsize=(8, 5))
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
