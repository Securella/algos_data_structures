# =============================================================
# Project: Seminar 1 in Algorithms and Data Structures
# Author: Securella
# Year: 2024
# Description: This code is part of an individual project for the
# seminar and is intended for examination.
#
# Disclaimer:
# This is individual work created for a seminar assignment. It should
# not be copied, shared, or used as a reference for similar project.
# Unauthorized use of this code is strictly prohibited by the educational institution.
# =============================================================

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages

# Define target array sizes
target_sizes = [100, 1000, 10000, 100000, 1000000]

# Step 1: Read the CSV file with adjusted column names
try:
    # The CSV file uses commas as both field separators and decimal separators.
    # We'll read the file with additional columns to capture the split decimal numbers.
    data = pd.read_csv(
        "sorting_results.csv",
        header=0,
        names=['Scenario', 'Algorithm', 'Array Size', 'Time_Int', 'Time_Dec', 'Repetitions'],
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

    # Strip whitespace from 'Algorithm' and 'Scenario'
    data['Algorithm'] = data['Algorithm'].astype(str).str.strip()
    data['Scenario'] = data['Scenario'].astype(str).str.strip()

    # Remove unnecessary columns
    data = data[['Scenario', 'Algorithm', 'Array Size', 'Execution Time (ms)', 'Repetitions']]

    # Debug intermediate processed data
    print("\n--- Processed Data (After Cleaning) ---")
    print(data.head())

    # Average over repetitions
    data = data.groupby(['Scenario', 'Algorithm', 'Array Size'], as_index=False).agg({'Execution Time (ms)': 'mean'})

    # Filter for target array sizes
    data = data[data['Array Size'].isin(target_sizes)]

    # Ensure proper ordering
    data = data.sort_values(by=['Scenario', 'Algorithm', 'Array Size'])

    # Debug filtered data
    print("\n--- Filtered Data (Target Sizes Only) ---")
    print(data.head())

    # Check for missing methods
    expected_methods = [
        "QuickSort - Recursive (Median Pivot)",
        "QuickSort - Recursive (Random Pivot)",
        "QuickSort - Recursive (First Pivot)",
        "QuickSort - Iterative (Median Pivot)",
        "QuickSort - Iterative (Random Pivot)",
        "QuickSort - Iterative (First Pivot)",
        "InsertionSort - Recursive",
        "InsertionSort - Iterative"
    ]
    missing_methods = [method for method in expected_methods if method not in data['Algorithm'].unique()]
    if missing_methods:
        print(f"Warning: The following methods are missing from the dataset: {missing_methods}")

except Exception as e:
    print(f"Error processing data: {e}")
    exit()

# Check if data is valid
if data.empty:
    print("Error: No valid data after preprocessing. Check your CSV file.")
    exit()

# Step 4: Create pivot table
try:
    table = data.pivot(index=['Scenario', 'Algorithm'], columns='Array Size', values='Execution Time (ms)')
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

# Step 7: Plot individual algorithm execution times for each scenario
for scenario in data['Scenario'].unique():
    scenario_data = data[data['Scenario'] == scenario]
    for algorithm in scenario_data['Algorithm'].unique():
        subset = scenario_data[scenario_data['Algorithm'] == algorithm]
        plt.figure(figsize=(10, 6))
        plt.plot(
            subset['Array Size'],
            subset['Execution Time (ms)'],
            marker='o',
            linestyle='-',
            label=f"{algorithm} ({scenario})",
            linewidth=2
        )
        plt.title(f"{algorithm} Execution Time ({scenario} Case)", fontsize=14)
        plt.xlabel("Array Size", fontsize=12)
        plt.ylabel("Execution Time (ms)", fontsize=12)
        plt.xscale("log")
        plt.yscale("log")
        plt.grid(True, which="both", linestyle="--", linewidth=0.5)
        plt.legend()
        plt.tight_layout()
        plt.savefig(f"{algorithm}_{scenario}_performance_plot.png")
        plt.show()

# Step 8: Combined plot for each algorithm across all scenarios
for algorithm in data['Algorithm'].unique():
    plt.figure(figsize=(12, 8))
    for scenario in data['Scenario'].unique():
        subset = data[(data['Algorithm'] == algorithm) & (data['Scenario'] == scenario)]
        plt.plot(
            subset['Array Size'],
            subset['Execution Time (ms)'],
            marker='o',
            linestyle='-',
            label=f"{scenario} Case",
            linewidth=2
        )
    plt.title(f"{algorithm} - Performance Across Scenarios", fontsize=16)
    plt.xlabel("Array Size", fontsize=14)
    plt.ylabel("Execution Time (ms)", fontsize=14)
    plt.xscale("log")
    plt.yscale("log")
    plt.legend(fontsize=12, title="Scenario")
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)
    plt.tight_layout()
    plt.savefig(f"{algorithm}_scenarios_comparison.png")
    plt.show()

# Step 9: Diagram for input size 1000000
try:
    specific_size = 1000000
    data_1000000 = data[data['Array Size'] == specific_size]

    if not data_1000000.empty:
        plt.figure(figsize=(12, 8))
        bar_width = 0.2  # Width for grouped bars
        x = np.arange(len(data_1000000['Algorithm'].unique()))
        for i, scenario in enumerate(data_1000000['Scenario'].unique()):
            subset = data_1000000[data_1000000['Scenario'] == scenario]
            plt.bar(
                x + i * bar_width,
                subset['Execution Time (ms)'],
                width=bar_width,
                label=scenario
            )
        plt.title(f"Execution Time for Input Size {specific_size} Across Scenarios", fontsize=16)
        plt.xlabel("Algorithm", fontsize=14)
        plt.ylabel("Execution Time (ms)", fontsize=14)
        plt.xticks(x + bar_width, data_1000000['Algorithm'].unique(), rotation=45, ha='right', fontsize=12)
        plt.legend(title="Scenario", fontsize=12)
        plt.grid(axis='y', linestyle="--", linewidth=0.5)
        plt.tight_layout()
        plt.savefig(f"execution_time_input_{specific_size}_scenarios.png")
        plt.show()
    else:
        print(f"No data found for input size {specific_size}.")
except Exception as e:
    print(f"Error creating diagram for input size {specific_size}: {e}")

# Step 10: Save all graphs into a single PDF
try:
    with PdfPages("all_plots.pdf") as pdf:
        for algorithm in data['Algorithm'].unique():
            for scenario in data['Scenario'].unique():
                subset = data[(data['Algorithm'] == algorithm) & (data['Scenario'] == scenario)]
                if not subset.empty:
                    fig = plt.figure()
                    plt.plot(
                        subset['Array Size'],
                        subset['Execution Time (ms)'],
                        marker='o',
                        label=f"{algorithm} ({scenario})"
                    )
                    plt.title(f"{algorithm} ({scenario}) Performance", fontsize=14)
                    plt.xlabel("Array Size", fontsize=12)
                    plt.ylabel("Execution Time (ms)", fontsize=12)
                    plt.legend()
                    pdf.savefig(fig)
                    plt.close(fig)
        print("All plots saved into 'all_plots.pdf'.")
except Exception as e:
    print(f"Error saving plots into PDF: {e}")
