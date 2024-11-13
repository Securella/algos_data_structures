package algos_data_structures.algos_playground;

// Class for my playground in algorithms and data structures
public class Main {

    // Method to print each digit of a non-negative integer n
    public static void printOut(int n) {
        if (n >= 10)
            printOut(n / 10); // Recursive call with the number divided by 10

        printDigit(n % 10); // Print the last digit
    }

    // Method to print a single digit
    public static void printDigit(int digit) {
        System.out.print(digit); // Print the digit without a newline
    }

    // Class for simulating a simple memory cell
    static class MemoryCell {
        private Object storedValue;

        public void write(Object x) {
            storedValue = x;
        }

        public Object read() {
            return storedValue;
        }
    }

    // Generic method to find the maximum element in an array of Comparable<T> objects
    public static <T extends Comparable<T>> T findMax(T[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    // Shape class implementing Comparable, with subclasses Circle, Square, and Rectangle
    static abstract class Shape implements Comparable<Shape> {
        public abstract double area();

        @Override
        public int compareTo(Shape other) {
            return Double.compare(this.area(), other.area());
        }
    }

    static class Circle extends Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public String toString() {
            return "Circle with area: " + area();
        }
    }

    static class Square extends Shape {
        private double side;

        public Square(double side) {
            this.side = side;
        }

        @Override
        public double area() {
            return side * side;
        }

        @Override
        public String toString() {
            return "Square with area: " + area();
        }
    }

    static class Rectangle extends Shape {
        private double width, height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public String toString() {
            return "Rectangle with area: " + area();
        }
    }

    // Generic version of the MemoryCell class
    public static class GenericMemoryCell<AnyType> {
        private AnyType storedValue;

        // Method to read the stored value
        public AnyType read() {
            return storedValue;
        }

        // Method to write a value of generic type
        public void write(AnyType x) {
            storedValue = x;
        }
    }

    public static void main(String[] args) {
        // Part 1: Output for Recursive routine to print an integer
        int number = 12345;
        System.out.print("Output for Recursive routine to print an integer: ");
        printOut(number);
        System.out.println(); // Print a newline for clarity

        // Part 2: Output for Integer wrapper class
        MemoryCell m = new MemoryCell();
        m.write(Integer.valueOf(37)); // Using Integer wrapper class with Integer.valueOf
        Integer wrapperVal = (Integer) m.read(); // Reading the Integer from MemoryCell
        int val = wrapperVal.intValue(); // Converting Integer to int
        System.out.println("Output for Integer wrapper class: Contents are: " + val);

        // Part 3: Output for findMax with Shape objects
        Shape[] shapes = { new Circle(2.0), new Square(3.0), new Rectangle(3.0, 4.0) };
        System.out.println("Output for Max shape: " + findMax(shapes));

        // Part 4: Output for findMax with String objects
        String[] names = { "Joe", "Bob", "Bill", "Zeke" };
        System.out.println("Output for Max string: " + findMax(names));

        // Part 5: Demonstration of GenericMemoryCell with Integer and String
        // Using GenericMemoryCell with Integer
        GenericMemoryCell<Integer> intCell = new GenericMemoryCell<>();
        intCell.write(42);
        System.out.println("Output for GenericMemoryCell<Integer>: " + intCell.read());

        // Using GenericMemoryCell with String
        GenericMemoryCell<String> stringCell = new GenericMemoryCell<>();
        stringCell.write("Hello, Generics!");
        System.out.println("Output for GenericMemoryCell<String>: " + stringCell.read());
    }
}


