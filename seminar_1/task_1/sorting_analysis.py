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

# Define target array sizes
target_sizes = [100, 1000, 10000, 100000, 1000000]

# Step 1: Read the CSV file with adjusted column names
try:
    # The CSV file uses commas as both field separators and decimal separators.
    # We'll read the file with additional columns to capture the split decimal numbers.
    data = pd.read_csv(
        "sorting_results.csv",
        header=0,
        names=['Algorithm', 'Array Size', 'Time_Int', 'Time_Dec', 'Repetitions'],
        engine='python'
    )
    print("CSV loaded successfully.")
except Exception as e:
    print(f"Error reading CSV file: {e}")
    exit()

# Step 2: Debug raw data
print("\n--- Raw Data ---")
print(data.head())

# Step 3: Preprocess the data
try:
    # Combine 'Time_Int' and 'Time_Dec' into 'Execution Time (ms)'
    data['Execution Time (ms)'] = data['Time_Int'].astype(str) + '.' + data['Time_Dec'].astype(str)
    data['Execution Time (ms)'] = data['Execution Time (ms)'].astype(float)

    # Convert 'Array Size' and 'Repetitions' to numeric
    data['Array Size'] = pd.to_numeric(data['Array Size'], errors='coerce')
    data['Repetitions'] = pd.to_numeric(data['Repetitions'], errors='coerce')

    # Strip whitespace from 'Algorithm'
    data['Algorithm'] = data['Algorithm'].astype(str).str.strip()

    # Remove unnecessary columns
    data = data[['Algorithm', 'Array Size', 'Execution Time (ms)', 'Repetitions']]

    # Debug intermediate processed data
    print("\n--- Processed Data (After Cleaning) ---")
    print(data.head())

    # Average over repetitions
    data = data.groupby(['Algorithm', 'Array Size'], as_index=False).agg({'Execution Time (ms)': 'mean'})

    # Filter for the target array sizes
    data = data[data['Array Size'].isin(target_sizes)]

    # Ensure proper ordering
    data = data.sort_values(by='Array Size')

    # Debug filtered data
    print("\n--- Filtered Data (Target Sizes Only) ---")
    print(data.head())

except Exception as e:
    print(f"Error processing data: {e}")
    exit()

# Check if data is valid
if data.empty:
    print("Error: No valid data after preprocessing. Check your CSV file.")
    exit()

# Step 4: Create pivot table
try:
    table = data.pivot(index='Algorithm', columns='Array Size', values='Execution Time (ms)')
    print("\n--- Pivot Table ---")
    print(table)
except Exception as e:
    print(f"Error creating pivot table: {e}")
    exit()

# Ensure pivot table is not empty
if table.empty:
    print("Error: Pivot table is empty. Check the filtered data.")
    exit()

# Step 5: Save formatted table
table.to_csv("formatted_sorting_results.csv")
print("\nFormatted table saved as 'formatted_sorting_results.csv'.")

# Step 6: Define theoretical complexity lines
input_sizes = np.array(target_sizes, dtype=int)
quicksort_theoretical = input_sizes * np.log2(input_sizes)  # O(n log n)
insertion_theoretical = input_sizes ** 2                    # O(n^2)

# Step 7: Plot individual algorithm execution times
for algorithm in table.index:
    plt.figure(figsize=(8, 5))
    plt.plot(table.columns, table.loc[algorithm], marker='o', label=algorithm)
    plt.title(f"{algorithm} Execution Time")
    plt.xlabel("Array Size")
    plt.ylabel("Execution Time (ms)")
    plt.xscale("log")
    plt.yscale("log")
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.legend()
    plt.savefig(f"{algorithm}_performance_plot.png")
    plt.show()

# Step 8: Grouped plot for QuickSort with theoretical complexity
quick_sort_methods = [alg for alg in table.index if "QuickSort" in alg]
if quick_sort_methods:
    plt.figure(figsize=(10, 6))
    for algorithm in quick_sort_methods:
        plt.plot(table.columns, table.loc[algorithm], marker='o', label=algorithm)
    plt.plot(
        input_sizes,
        quicksort_theoretical / quicksort_theoretical[-1] * table.loc[quick_sort_methods[0]].max(),
        linestyle='--', color='gray', label="O(n log n) theoretical"
    )
    plt.title("QuickSort Variants with Theoretical O(n log n) Complexity")
    plt.xlabel("Array Size")
    plt.ylabel("Execution Time (ms)")
    plt.xscale("log")
    plt.yscale("log")
    plt.legend(title="QuickSort Method")
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.savefig("quick_sort_with_theoretical.png")
    plt.show()
else:
    print("Warning: No QuickSort methods found in the data. Skipping QuickSort plot.")

# Step 9: Grouped plot for InsertionSort with theoretical complexity
insertion_methods = [alg for alg in table.index if "InsertionSort" in alg]
if insertion_methods:
    plt.figure(figsize=(8, 5))
    for algorithm in insertion_methods:
        plt.plot(table.columns, table.loc[algorithm], marker='o', label=algorithm)
    plt.plot(
        input_sizes,
        insertion_theoretical / insertion_theoretical[-1] * table.loc[insertion_methods[0]].max(),
        linestyle='--', color='gray', label="O(n^2) theoretical"
    )
    plt.title("InsertionSort Variants with Theoretical O(n^2) Complexity")
    plt.xlabel("Array Size")
    plt.ylabel("Execution Time (ms)")
    plt.xscale("log")
    plt.yscale("log")
    plt.legend(title="InsertionSort Method")
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.savefig("insertion_sort_with_theoretical.png")
    plt.show()
