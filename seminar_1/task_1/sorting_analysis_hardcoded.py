# =============================================================
# Project: Seminar 1 in Algorithms and Data Structures
# Author: Securella
# Year: 2024
# Description: This script generates performance plots from hardcoded data.
#
# Disclaimer:
# This is individual work created for a seminar assignment. It should
# not be copied, shared, or used as a reference for similar projects.
# Unauthorized use of this code is strictly prohibited by the educational institution.
# =============================================================

import matplotlib.pyplot as plt
import numpy as np

# Step 1: Hardcoded data from CSV
data = {
    "Best-Case": {
        "QuickSort - Recursive (Median-of-Three Pivot)": 15.56,
        "QuickSort - Recursive (Random Pivot)": 34.21,
        "QuickSort - Recursive (First Pivot)": 2272.23,
        "QuickSort - Iterative (Median-of-Three Pivot)": 0.01,
        "QuickSort - Iterative (Random Pivot)": 37.85,
        "QuickSort - Iterative (First Pivot)": 2275.11,
        "InsertionSort - Recursive": 4.38,
        "InsertionSort - Iterative": 0.17,
    },
    "Average-Case": {
        "QuickSort - Recursive (Median-of-Three Pivot)": 34.21,
        "QuickSort - Recursive (Random Pivot)": 53.12,
        "QuickSort - Recursive (First Pivot)": 2295.51,
        "QuickSort - Iterative (Median-of-Three Pivot)": 0.03,
        "QuickSort - Iterative (Random Pivot)": 53.22,
        "QuickSort - Iterative (First Pivot)": 2434.05,
        "InsertionSort - Recursive": 153244.76,
        "InsertionSort - Iterative": 70145.82,
    },
    "Worst-Case": {
        "QuickSort - Recursive (Median-of-Three Pivot)": 15.93,
        "QuickSort - Recursive (Random Pivot)": 35.37,
        "QuickSort - Recursive (First Pivot)": 65440.96,
        "QuickSort - Iterative (Median-of-Three Pivot)": 0.05,
        "QuickSort - Iterative (Random Pivot)": 35.17,
        "QuickSort - Iterative (First Pivot)": 65237.46,
        "InsertionSort - Recursive": 301368.80,
        "InsertionSort - Iterative": 137806.57,
    },
}

# Theoretical data for line plots
input_sizes = np.array([100, 1000, 10000, 100000, 1000000])
theoretical_nlogn = input_sizes * np.log2(input_sizes)  # O(n log n)
theoretical_n2 = input_sizes ** 2                      # O(n^2)

# Step 2: Bar chart for all scenarios only at n=1,000,000
algorithms = list(data["Best-Case"].keys())
x = np.arange(len(algorithms))  # x positions for bars
width = 0.25  # Perfect width of each bar

plt.figure(figsize=(14, 8))
for i, scenario in enumerate(["Best-Case", "Average-Case", "Worst-Case"]):
    execution_times = [data[scenario][algorithm] for algorithm in algorithms]
    plt.bar(
        x + i * width,
        execution_times,
        width=width,
        label=scenario
    )

plt.xticks(x + width, algorithms, rotation=45, ha="right", fontsize=10)
plt.title("Execution Times for Scenarios (n=1,000,000)")
plt.ylabel("Execution Time (ms)")
plt.legend(title="Scenario")
plt.grid(axis="y", linestyle="--", linewidth=0.5)
plt.tight_layout()
plt.savefig("execution_times_for_scenarios_n_1_million.png")
plt.show()

# Step 3: Line plots for each sorting algorithm (only average case)
average_case_data = {
    "InsertionSort": {
        "Recursive": [0.04, 1.25, 15.15, 1564.07, 153244.76],
        "Iterative": [0.03, 0.25, 6.86, 688.46, 70145.82],
    },
    "QuickSort - Recursive": {
        "Median-of-Three Pivot": [0.02, 0.06, 0.34, 3.36, 34.21],
        "Random Pivot": [0.02, 0.14, 0.69, 5.14, 53.12],
        "First Pivot": [0.02, 0.25, 0.56, 25.99, 2295.51],
    },
    "QuickSort - Iterative": {
        "Median-of-Three Pivot": [0.01, 0.03, 0.20, 2.50, 15.00],
        "Random Pivot": [0.02, 0.14, 0.69, 5.20, 53.22],
        "First Pivot": [0.01, 0.05, 0.56, 25.98, 2434.05],
    },
}

# Plot 1: Insertion Sort
plt.figure(figsize=(10, 6))
for variant, times in average_case_data["InsertionSort"].items():
    plt.plot(
        input_sizes,
        times,
        label=variant,
        marker="o"
    )
plt.plot(
    input_sizes,
    theoretical_n2,
    linestyle="--",
    color="magenta",
    label="n^2 (Theoretical)"
)
plt.xscale("log")
plt.yscale("log")
plt.title("Insertion Sort (Average Case)")
plt.xlabel("Number of Inputs")
plt.ylabel("Execution Time (ms)")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.legend(title="Variant")
plt.tight_layout()
plt.savefig("insertion_sort_average_case.png")
plt.show()

# Plot 2: QuickSort Recursive
plt.figure(figsize=(10, 6))
for variant, times in average_case_data["QuickSort - Recursive"].items():
    plt.plot(
        input_sizes,
        times,
        label=variant,
        marker="o"
    )
plt.plot(
    input_sizes,
    theoretical_nlogn,
    linestyle="--",
    color="magenta",
    label="n*log(n) (Theoretical)"
)
plt.xscale("log")
plt.yscale("log")
plt.title("QuickSort Recursive (Average Case)")
plt.xlabel("Number of Inputs")
plt.ylabel("Execution Time (ms)")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.legend(title="Pivot Strategy")
plt.tight_layout()
plt.savefig("quicksort_recursive_average_case.png")
plt.show()

# Plot 3: QuickSort Iterative
plt.figure(figsize=(10, 6))
for variant, times in average_case_data["QuickSort - Iterative"].items():
    plt.plot(
        input_sizes,
        times,
        label=variant,
        marker="o"
    )
plt.plot(
    input_sizes,
    theoretical_nlogn,
    linestyle="--",
    color="magenta",
    label="n*log(n) (Theoretical)"
)
plt.xscale("log")
plt.yscale("log")
plt.title("QuickSort Iterative (Average Case)")
plt.xlabel("Number of Inputs")
plt.ylabel("Execution Time (ms)")
plt.grid(True, which="both", linestyle="--", linewidth=0.5)
plt.legend(title="Pivot Strategy")
plt.tight_layout()
plt.savefig("quicksort_iterative_average_case.png")
plt.show()

