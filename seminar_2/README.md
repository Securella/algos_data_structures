# Seminar 2: Algorithms and Data Structures

This folder contains the tasks for Seminar 2, focusing on implementing data structures and solving algorithmic problems in Java. Each task is designed to deepen understanding of how data structures operate and explore problem-solving strategies for real-world scenarios.

---

## Table of Contents
1. [Task 1: Queue and Stack Implementations](#task-1-queue-and-stack-implementations)
2. [Task 2: Address Book with Linked List](#task-2-address-book-with-linked-list)
3. [Task 3: Josephus Problem](#task-3-josephus-problem)
4. [How to Run the Code](#how-to-run-the-code)

---

## Task 1: Queue and Stack Implementations

### Description
This task involves implementing **Queues** and **Stacks** using unconventional methods:
1. **Queue Using 2 Stacks**: Simulates a queue using two stack data structures.
2. **Queue Using 1 Stack**: Simulates a queue using only one stack, with recursive operations.
3. **Stack Using 2 Queues**: Simulates a stack using two queue data structures.
4. **Stack Using 1 Queue**: Simulates a stack using a single queue, where elements are reorganized to ensure stack behavior.

### Features
- Handles **overflow** and **underflow** with appropriate warnings.
- Fully functional routines with professional error handling.

### Files
- `QueueUsingTwoStacks.java`
- `QueueUsingOneStack.java`
- `StackUsingTwoQueues.java`
- `StackUsingOneQueue.java`

### Try It Out
1. Compile and run any of the files. For example:
   ```bash
   javac QueueUsingTwoStacks.java
   java QueueUsingTwoStacks
   ```
2. Replace <QueueUsingTwoStacks> with the desired file name.

## Task 2: Address Book with Linked List

### Description
This task implements an Address Book using a custom Linked List. Each contact is stored as a node containing:
**Name:** String for the contact's name.
**Address:** String for the contact's address.

### Features
- Add a contact to the list.
- Remove a contact by index.
- Retrieve contact details by index.
- Print the entire address book to the console.

### Files
- `AddressBook.java` The main file to handle linked list operations.
- `ContactNode.java` Represents each contact as a node in the linked list.

### Try It Out
1. Compile and run any of the files. For example:
   ```bash
   javac AddressBook.java
   java AddressBook
   ```
2. Follow the console prompts to add, remove, or view contacts.

## Task 3: Josephus Problem

### Description
The Josephus Problem involves N people sitting in a circle. Starting from person 1, a "hot potato" is passed around. After M passes, the person holding the potato is eliminated, and the game continues until one person remains.

This task solves the problem using different data structures:

1. ArrayList: Simple index-based elimination.
2. ArrayList with Iterator: Efficient elimination using an iterator.
3. LinkedList: Custom implementation of linked list for dynamic resizing.
4. LinkedList with Iterator: Iterator-based elimination for improved efficiency.

### Features
- Supports any input size N (number of people) and M (number of passes).
- Benchmarks included to compare the performance of each implementation.

### Files
- `JosephusWithArrayList.java`
- `JosephusWithIterator.java`
- `JosephusWithLinkedList.java`
- `JosephusWithLinkedListIterator.java`

### Try It Out
1. Compile and run any of the files. For example:
   ```bash
   javac JosephusWithArrayList.java
   java JosephusWithArrayList
   ```
2. Follow the prompts to input N (number of people) and M (steps before elimination).

## Benchmark Visualization
1. Results are stored in results.csv. Python script visualize_results.py to generate runtime comparison charts:
```bash
   python visualize_results.py
   ```

## How to Run the Code

### Prerequisites
1. Install Python libraries (optional, for task 3)
```bash
   pip install matplotlib pandas
   ```
2. Clone the repository:
```bash
   git clone https://github.com/Securella/algos_data_structures.git
   cd algos_data_structures/seminar_2/
   ```
3. Navigate to the task folder you want to try. Compile the files as usual.