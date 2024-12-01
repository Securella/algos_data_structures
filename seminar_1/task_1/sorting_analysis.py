# =============================================================
# Project: Seminar 1 in Algorithms and Data Structures
# Author: Securella
# Year: 2024
# Description: This script processes sorting results from a CSV file,
#              generates performance plots as per examples provided,
#              and saves them as images and a PDF.
#
# Disclaimer:
# This is individual work created for a seminar assignment. It should
# not be copied, shared, or used as a reference for similar projects.
# Unauthorized use of this code is strictly prohibited by the educational institution.
# =============================================================

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages

# Define target array sizes
target_sizes = [100, 1000, 10000, 100000, 1000000]

# Load CSV data
try:
    # Specify delimiter and column names
    data = pd.read_csv(
        "sorting_results.csv",
        delimiter=';',
        header=0,
        names=['Scenario', 'Algorithm', 'Array Size', 'Execution Time (ms)', 'Repetitions']
    )
    print("CSV loaded successfully.")
except Exception as e:
    print(f"Error reading CSV file: {e}")
    exit()

# Preprocess data
data['Execution Time (µs)'] = data['Execution Time (ms)'] * 1000  # Convert ms to µs
data['Array Size'] = pd.to_numeric(data['Array Size'], errors='coerce')

# Filter for target array sizes
data = data[data['Array Size'].isin(target_sizes)]

# Define theoretical complexity lines
input_sizes = np.array(target_sizes)
quicksort_theoretical = input_sizes * np.log2(input_sizes)  # O(n log n)
insertion_theoretical = input_sizes ** 2                    # O(n^2)

# Quicksort Recursive Plot (Average Case)
recursive_algorithms = data[
    (data['Scenario'] == 'Average-Case') & 
    (data['Algorithm'].str.contains("QuickSort - Recursive"))
]
plt.figure(figsize=(10, 6))
for pivot_strategy in ['Median Pivot', 'Random Pivot', 'First Pivot']:
    subset = recursive_algorithms[recursive_algorithms['Algorithm'].str.contains(pivot_strategy)]
    plt.plot(
        subset['Array Size'],
        subset['Execution Time (µs)'],
        marker='o',
        label=f"{pivot_strategy.lower()}",
        linewidth=2
    )
plt.plot(input_sizes, quicksort_theoretical, linestyle='--', label="n*log(n)", color='magenta')
plt.xscale('log')
plt.yscale('linear')
plt.xlabel("Number of inputs")
plt.ylabel("Time (µs)")
plt.title("Quicksort recursive")
plt.legend(title="Pivot Strategy")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.tight_layout()
plt.savefig("quicksort_recursive_average.png")
plt.show()

# Quicksort Iterative Plot (Average Case)
iterative_algorithms = data[
    (data['Scenario'] == 'Average-Case') & 
    (data['Algorithm'].str.contains("QuickSort - Iterative"))
]
plt.figure(figsize=(10, 6))
for pivot_strategy in ['Median Pivot', 'Random Pivot', 'First Pivot']:
    subset = iterative_algorithms[iterative_algorithms['Algorithm'].str.contains(pivot_strategy)]
    plt.plot(
        subset['Array Size'],
        subset['Execution Time (µs)'],
        marker='o',
        label=f"{pivot_strategy.lower()}",
        linewidth=2
    )
plt.plot(input_sizes, quicksort_theoretical, linestyle='--', label="n*log(n)", color='magenta')
plt.xscale('log')
plt.yscale('linear')
plt.xlabel("Number of inputs")
plt.ylabel("Time (µs)")
plt.title("Quicksort iterative")
plt.legend(title="Pivot Strategy")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.tight_layout()
plt.savefig("quicksort_iterative_average.png")
plt.show()

# Insertion Sort Plot (Average Case)
insertion_algorithms = data[
    (data['Scenario'] == 'Average-Case') & 
    (data['Algorithm'].str.contains("InsertionSort"))
]
plt.figure(figsize=(10, 6))
for variant in ['Recursive', 'Iterative']:
    subset = insertion_algorithms[insertion_algorithms['Algorithm'].str.contains(variant)]
    plt.plot(
        subset['Array Size'],
        subset['Execution Time (µs)'],
        marker='o',
        label=variant,
        linewidth=2
    )
plt.plot(input_sizes, insertion_theoretical, linestyle='--', label="n^2", color='magenta')
plt.xscale('log')
plt.yscale('linear')
plt.xlabel("Number of inputs")
plt.ylabel("Time (µs)")
plt.title("Insertion sort")
plt.legend(title="Variant")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.tight_layout()
plt.savefig("insertion_sort_average.png")
plt.show()

# Bar Chart: Worst-Case, Average-Case, Best-Case for Input Size = 1,000,000
data_1000000 = data[data['Array Size'] == 1000000]
plt.figure(figsize=(12, 8))
scenarios = ['Best-Case', 'Average-Case', 'Worst-Case']
bar_width = 0.25
x = np.arange(len(data_1000000['Algorithm'].unique()))
for i, scenario in enumerate(scenarios):
    subset = data_1000000[data_1000000['Scenario'] == scenario]
    plt.bar(
        x + i * bar_width,
        subset['Execution Time (µs)'],
        width=bar_width,
        label=scenario
    )
plt.title("Time (µs) for n = 1,000,000")
plt.xlabel("Algorithm")
plt.ylabel("Time (µs)")
plt.xticks(x + bar_width, data_1000000['Algorithm'].unique(), rotation=45, ha='right')
plt.legend(title="Scenario")
plt.grid(axis='y', linestyle="--", linewidth=0.5)
plt.tight_layout()
plt.savefig("execution_time_input_1000000.png")
plt.show()

# Save All Plots to PDF
try:
    with PdfPages("all_plots.pdf") as pdf:
        for file in [
            "quicksort_recursive_average.png",
            "quicksort_iterative_average.png",
            "insertion_sort_average.png",
            "execution_time_input_1000000.png"
        ]:
            img = plt.imread(file)
            fig, ax = plt.subplots(figsize=(10, 8))
            ax.imshow(img)
            ax.axis('off')
            pdf.savefig(fig)
            plt.close(fig)
    print("All plots saved into 'all_plots.pdf'.")
except Exception as e:
    print(f"Error saving plots into PDF: {e}")
