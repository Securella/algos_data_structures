# Seminar 3: Data Structures and Algorithms

This folder contains the tasks for **Seminar 3**:
1. **Binary Heaps** (implementing 2 algorithms and traversals).
2. **Hash Tables** (separate chaining, linear probing, and quadratic probing).
3. **Word Puzzle Solver** (using hash tables).

---

## Table of Contents
1. [Task 1: Binary Heap](#task-1-binary-heap)
2. [Task 2: Hash Tables](#task-2-hash-tables)
3. [Task 3: Word Puzzle Solver](#task-3-word-puzzle-solver)
4. [How to Run the Code](#how-to-run-the-code)

---

## Task 1: Binary Heap

### Description
**Binary Heap** implementation in 2 ways:
1. **Algorithm 1**: Inserting elements one by one into an initially empty heap.  
2. **Algorithm 2**: Building heap in linear time from an unsorted array.

**4 traversals** are used: in-order, pre-order, post-order, and level-order, measuring **complexity** for various input sizes, and comparing **insertion** vs. **deleteMin** operations to see which is more expensive in a priority queue.

### Features
- **Heap Implementation**:
  - `insert(int)` (Algorithm 1)
  - `buildHeap(int[])` (Algorithm 2, linear time)
  - `deleteMin()` (removes the root)
  - **4 traversals**: in-order, pre-order, post-order, level-order
- **Complexity Measurement** with input sizes of 100, 1000, 10,000 from a file.

### Files
- **`BinaryHeap.java`**  
  Implements the heap structure (insertion, deleteMin, buildHeap, traversals).
- **`Task1GUI.java`**  
  - Presenting **step-by-step** insertion results.
  - Performing and displaying **linear-time build**.
  - Showing all **4 traversals**.
  - Measuring **complexity** with large input data from file.
  - Comparing insertion vs. deleteMin operation times.

### Try It Out

In GUI:
   - Click **"Step-by-Step Insertion"** to insert each element incrementally (Algorithm 1).
   - Click **"Linear-Time Build"** to build the heap using Algorithm 2.
   - Click **"Show Traversals"** to see in-order, pre-order, post-order, and level-order outputs.
   - Click **"Measure Complexity"** for larger-scale input timing.
   - Click **"Compare Insert vs DeleteMin"** to see performance differences.

---

## Task 2: Hash Tables

### Description
This task implements **hash tables** using hash function **`h(x) = x mod 10`** with collision resolution via:
1. **Separate Chaining**
2. **Linear Probing**
3. **Quadratic Probing**

It also requires discussing **complexity**, demonstrating **rehashing** (doubling table size) in at least 1 approach, and addressing **other rehashing** possibilities.

### Features
- **Separate Chaining**: Collisions stored in linked lists.
- **Linear Probing**: Simple `(index + 1) mod capacity` collision resolution.
- **Quadratic Probing**: `(index + i^2) mod capacity` collision resolution.
- **Rehashing** in linear probing: Doubles table size and reinserts all elements.

### Files
- **`HashTables.java`**  
  - `SeparateChainingHashTable`: Array of `LinkedList<Integer>` with chaining.  
  - `LinearProbingHashTable`: Single array, linear collision resolution, `rehash()` method.  
  - `QuadraticProbingHashTable`: Single array with quadratic collision resolution.
- **`Task2GUI.java`**  
    GUI to:
  - **Insert** given input set into all 3 tables.
  - **Show** final layout for each approach.
  - **Rehash** linear table (doubling capacity).
  - **Discuss** complexities and rehashing options.

### Try It Out

In GUI:
   - Click **"Insert into All Tables"** to initialize and populate each hash table.
   - Click **"Show Separate Chaining"**, **"Show Linear Probing"**, or **"Show Quadratic Probing"** to see final distribution.
   - Click **"Rehash (Linear) -> Double"** to expand linear table from size 10 to 20.
   - Click **"Discuss Complexity"** to read about complexities and other rehashing approaches.

---

## Task 3: Word Puzzle Solver

### Description
This task involves solving a **2D word puzzle** using the following approach:
1. Building **hash table** to store all words and their prefixes from word list.
2. For each starting cell in the 2D grid, attempting to form word in **8 possible directions**.
3. Using hash table for quick lookups:
   - If prefix isn't in hash table, stop scanning that direction early.
4. Reporting starting and ending coordinates of any found words.

### Example Puzzle and Word List
Puzzle:
```
1 2 3 4
t h i s
w a t s
o a h g
f g d t
```

Word List:
- **this**: (1,1) to (1,4)
- **two**: (1,1) to (3,1)
- **fat**: (4,1) to (2,3)
- **that**: (4,4) to (1,1)

### Files
- **`WordPuzzleSolver.java`**  
  Implements optimized word puzzle solver:
  - Hash table to store words and prefixes.
  - Scans in all 8 directions.
  - Outputs found words with their coordinates.
- **`Task3GUI.java`**  
    GUI to:
  - Load word puzzle grid.
  - Input word list.
  - Display found words with their start and end coordinates.

### Try It Out

In GUI:
   - Load **puzzle grid** (in given example, Fig. 2).
   - Input **word list**.
   - Click **"Solve"** to see all found words and their coordinates.

---

## How to Run the Code

### Prerequisites
- **Java 8+** (for GUI).

### Steps

1. **Clone or download** this repository:
   ```bash
   git clone https://github.com/<your-repo>/algos_data_structures.git
   cd algos_data_structures/seminar_3/
   ```

2. **Compile** files for the task you want to run:
   - For **Task 1**:
     ```bash
     javac BinaryHeap.java Task1GUI.java
     java Task1GUI
     ```
   - For **Task 2**:
     ```bash
     javac HashTables.java Task2GUI.java
     java Task2GUI
     ```
   - For **Task 3**:
     ```bash
     javac WordPuzzleSolver.java Task3GUI.java
     java Task3GUI
     ```

3. **Interact** with GUI:
   - **Binary Heap**: Step-by-step insertion, linear build, traversals.
   - **Hash Tables**: Insert, rehash, and analyze collision behavior.
   - **Word Puzzle Solver**: Find words and view their coordinates.

---