import pandas as pd
import matplotlib.pyplot as plt

# Load CSV data
data = pd.read_csv("binary_search_results.csv", delimiter=';')  # Adjust for semicolon delimiter

# Plot the results
plt.figure(figsize=(10, 6))
plt.plot(data['Input Size'], data['Execution Time (µs)'], marker='o', label='Binary search')
plt.plot(data['Input Size'], data['Log(n)'], marker='o', label='log(n)')
plt.xscale('log')  # Set x-axis to log scale
plt.xlabel('Number of inputs')
plt.ylabel('Time (µs)')
plt.title('Binary Search Performance')
plt.legend()
plt.grid(True, which='both', linestyle='--', linewidth=0.5)
plt.tight_layout()

# Save the plot
plt.savefig("binary_search_performance.png")
plt.show()
