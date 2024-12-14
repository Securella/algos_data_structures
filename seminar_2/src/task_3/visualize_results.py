import pandas as pd
import matplotlib.pyplot as plt

# Read results from CSV
data = pd.read_csv("results.csv")

# Plot
plt.figure(figsize=(10, 6))
for method in ["ArrayList", "Iterator", "Custom LinkedList", "Built-in LinkedList"]:
    plt.plot(data["N"], data[method], label=method)

plt.xlabel("Input Size (N)")
plt.ylabel("Execution Time (ms)")
plt.title("Josephus Problem: Runtime Comparison")
plt.legend()
plt.grid()
plt.savefig("josephus_runtime_comparison.png")
plt.show()
