package seminar_3.src.task_2;

import java.util.*;

/**
 * Contains classes for:
 *  (a) Separate Chaining
 *  (b) Linear Probing (with rehashing + collision count)
 *  (c) Quadratic Probing
 *
 * References:
 *   Mark Allen Weiss, "Data Structures and Algorithm Analysis in Java" (Hashing)
 *   only standard hashing approaches
 */
public class HashTables {

    /**
     * (a) Separate Chaining Hash Table
     * Using array of LinkedLists<Integer>.
     * also tracking "max chain length" for basic analysis.
     */
    @SuppressWarnings("unchecked")
    public static class SeparateChainingHashTable {
        private final LinkedList<Integer>[] table;

        public SeparateChainingHashTable(int size) {
            table = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                table[i] = new LinkedList<>();
            }
        }

        /**
         * Inserting value using h(x) = x mod tableSize
         */
        public void insert(int value) {
            int index = value % table.length;
            // Avoid duplicates
            if (!table[index].contains(value)) {
                table[index].add(value);
            }
        }

        public int getCapacity() {
            return table.length;
        }

        /**
         * For demonstration purpose, finding longest chain's size.
         */
        public int getMaxChainLength() {
            int max = 0;
            for (LinkedList<Integer> chain : table) {
                max = Math.max(max, chain.size());
            }
            return max;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Separate Chaining Table (size=").append(table.length).append(")\n");
            for (int i = 0; i < table.length; i++) {
                sb.append("[").append(i).append("]: ").append(table[i]).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * (b) Linear Probing Hash Table
     * Using array of int, with -1 as EMPTY.
     * Features:
     *  - rehash() method to double capacity
     *  - collisionCount for analysis
     */
    public static class LinearProbingHashTable {
        private static final int EMPTY = -1;
        private int[] table;
        private int size;            // # of elements actually stored
        private int collisionCount;  // track how many collisions happen

        public LinearProbingHashTable(int capacity) {
            table = new int[capacity];
            Arrays.fill(table, EMPTY);
            size = 0;
            collisionCount = 0;
        }

        /**
         * Inserting value using linear probing (index + 1 mod tableSize).
         */
        public void insert(int value) {
            int index = value % table.length;
            int startIndex = index;
            while (table[index] != EMPTY) {
                if (table[index] == value) {
                    // already present
                    return;
                }
                // Each time when find full slot, that's collision
                collisionCount++;

                index = (index + 1) % table.length;
                if (index == startIndex) {
                    // table is full
                    return;
                }
            }
            table[index] = value;
            size++;
        }

        /**
         * Doubling table size and reinserting all elements
         */
        public void rehash() {
            int newCap = table.length * 2;
            int[] oldTable = table;

            table = new int[newCap];
            Arrays.fill(table, EMPTY);
            size = 0;
            collisionCount = 0;  // resetting collisions

            for (int val : oldTable) {
                if (val != EMPTY) {
                    insert(val);
                }
            }
        }

        public int getCapacity() {
            return table.length;
        }

        public int getCollisionCount() {
            return collisionCount;
        }

        /**
         * We now read 'size' here so the compiler won't warn about it.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Linear Probing Table (capacity=")
              .append(table.length)
              .append(", size=")
              .append(size)  // <-- using 'size'
              .append(", collisions=")
              .append(collisionCount)
              .append(")\n");

            for (int i = 0; i < table.length; i++) {
                if (table[i] == EMPTY) {
                    sb.append("[").append(i).append("]: EMPTY\n");
                } else {
                    sb.append("[").append(i).append("]: ").append(table[i]).append("\n");
                }
            }
            return sb.toString();
        }
    }

    /**
     * (c) Quadratic Probing Hash Table
     * Collisions resolved by (index + i^2) mod capacity
     * (Note: No collision count added here)
     */
    public static class QuadraticProbingHashTable {
        private static final int EMPTY = -1;
        private int[] table;
        private int size;

        public QuadraticProbingHashTable(int capacity) {
            table = new int[capacity];
            Arrays.fill(table, EMPTY);
            size = 0;
        }

        public void insert(int value) {
            int index = value % table.length;
            int i = 0;
            while (true) {
                int attempt = (index + i * i) % table.length;
                if (table[attempt] == EMPTY) {
                    table[attempt] = value;
                    size++;
                    return;
                } else if (table[attempt] == value) {
                    // already present
                    return;
                }
                i++;
                if (i >= table.length) {
                    // table is full or no slot found
                    return;
                }
            }
        }

        public int getCapacity() {
            return table.length;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Quadratic Probing Table (capacity=").append(table.length).append(")\n");
            for (int i = 0; i < table.length; i++) {
                if (table[i] == EMPTY) {
                    sb.append("[").append(i).append("]: EMPTY\n");
                } else {
                    sb.append("[").append(i).append("]: ").append(table[i]).append("\n");
                }
            }
            return sb.toString();
        }
    }
}

