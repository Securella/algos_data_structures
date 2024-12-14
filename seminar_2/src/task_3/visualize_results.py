import pandas as pd
import matplotlib.pyplot as plt

# Reading results from CSV
data = pd.read_csv("results.csv")

# Validating columns in dataset
if not all(col in data.columns for col in ["Input Size", "ArrayList (ms)", "Iterator (ms)", "LinkedList (ms)", "LinkedList Iter (ms)"]):
    raise ValueError("The CSV file does not contain the required columns.")

# Plot results
plt.figure(figsize=(12, 8))

# Plot each method
for method in ["ArrayList (ms)", "Iterator (ms)", "LinkedList (ms)", "LinkedList Iter (ms)"]:
    plt.plot(data["Input Size"], data[method], label=method)

# Add plot details
plt.xlabel("Input Size (N)", fontsize=12)
plt.ylabel("Execution Time (ms)", fontsize=12)
plt.title("Josephus Problem: Runtime Comparison", fontsize=14)
plt.legend(title="Methods")
plt.grid()

# Saving plot to file
plt.savefig("josephus_runtime_comparison.png", dpi=300)

# Showing plot
plt.show()

# Displaying data in table format
print("Performance Results:")
print(data.to_string(index=False))
