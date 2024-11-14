package algos_data_structures.algos_playground;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

// Class for my playground in algorithms and data structures
public class Main {

    // Original findMax method
    public static <T extends Comparable<T>> T findMax(T[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    // Enhanced findMax method with bounded generic type
    public static <AnyType extends Comparable<? super AnyType>> AnyType findMaxEnhanced(AnyType[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    // New findMax method with Comparator for custom comparisons
    public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    // Comparator class for case-insensitive comparison of strings
    static class CaseInsensitiveCompare implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareToIgnoreCase(rhs);
        }
    }

    // Method to check if an array contains a specific element
    public static <AnyType> boolean contains(AnyType[] arr, AnyType x) {
        for (AnyType val : arr) {
            if (x.equals(val)) {
                return true; // Element found
            }
        }
        return false; // Element not found
    }

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

    // Method to calculate the total area of an array of Shape objects
    public static double totalArea(Shape[] arr) {
        double total = 0;
        for (Shape s : arr) {
            if (s != null) {
                total += s.area();
            }
        }
        return total;
    }

    // New totalArea method using a Collection with a wildcard
    public static double totalArea(Collection<? extends Shape> arr) {
        double total = 0;
        for (Shape s : arr) {
            if (s != null) {
                total += s.area();
            }
        }
        return total;
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Circle)) return false;
            Circle other = (Circle) obj;
            return Double.compare(this.radius, other.radius) == 0;
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Square)) return false;
            Square other = (Square) obj;
            return Double.compare(this.side, other.side) == 0;
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Rectangle)) return false;
            Rectangle other = (Rectangle) obj;
            return Double.compare(this.width, other.width) == 0 &&
                   Double.compare(this.height, other.height) == 0;
        }
    }

    // Generic version of the MemoryCell class
    public static class GenericMemoryCell<AnyType> {
        private AnyType storedValue;

        public AnyType read() {
            return storedValue;
        }

        public void write(AnyType x) {
            storedValue = x;
        }
    }

    public static void main(String[] args) {
        // Output for Recursive routine to print an integer
        int number = 12345;
        System.out.print("Output for Recursive routine to print an integer: ");
        printOut(number);
        System.out.println();

        // Output for Integer wrapper class
        MemoryCell m = new MemoryCell();
        m.write(Integer.valueOf(37));
        Integer wrapperVal = (Integer) m.read();
        int val = wrapperVal.intValue();
        System.out.println("Output for Integer wrapper class: Contents are: " + val);

        // Output for original findMax with Shape array
        Shape[] shapes = { new Circle(2.0), new Square(3.0), new Rectangle(3.0, 4.0) };
        System.out.println("Output for original findMax with Shape array: " + findMax(shapes));

        // Output for enhanced findMax with Shape array
        System.out.println("Output for enhanced findMax with Shape array: " + findMaxEnhanced(shapes));

        // Output for findMax with String array
        String[] names = { "Joe", "Bob", "Bill", "Zeke" };
        System.out.println("Output for original findMax with String array: " + findMax(names));
        System.out.println("Output for enhanced findMax with String array: " + findMaxEnhanced(names));

        // Comparator-based findMax for case-insensitive comparison
        String[] animals = { "ZEBRA", "alligator", "crocodile" };
        System.out.println("Output for comparator-based findMax with String array: " + findMax(animals, new CaseInsensitiveCompare()));

        // Output for GenericMemoryCell with Integer and String
        GenericMemoryCell<Integer> intCell = new GenericMemoryCell<>();
        intCell.write(42);
        System.out.println("Output for GenericMemoryCell<Integer>: " + intCell.read());

        GenericMemoryCell<String> stringCell = new GenericMemoryCell<>();
        stringCell.write("Hello, Generics!");
        System.out.println("Output for GenericMemoryCell<String>: " + stringCell.read());

        // Output for autoboxing and unboxing with diamond operator
        GenericMemoryCell<Integer> autoBoxCell = new GenericMemoryCell<>();
        autoBoxCell.write(5);
        int unboxedVal = autoBoxCell.read();
        System.out.println("Output for autoboxing and unboxing with diamond operator: Contents are: " + unboxedVal);

        // Calculate total area of Shape array
        System.out.println("Output for total area of shapes array: " + totalArea(shapes));

        // Calculate total area of Shape collection (ArrayList with wildcard support)
        Collection<Shape> shapeCollection = new ArrayList<>();
        shapeCollection.add(new Circle(2.0));
        shapeCollection.add(new Square(3.0));
        shapeCollection.add(new Rectangle(3.0, 4.0));
        System.out.println("Output for total area of shape collection: " + totalArea(shapeCollection));

        // Testing the contains method
        System.out.println("Output for contains method with String array: " + contains(names, "Zeke"));
        System.out.println("Output for contains method with Shape array: " + contains(shapes, new Square(3.0)));
    }
}

