package task_1;

import java.util.Scanner;

/**
 * Unified test class for all parts of Task 1.
 * Menu interface to test:
 * 1. Queue using 2 stacks.
 * 2. Queue using 1 stack.
 * 3. Stack using 2 queues.
 * 4. Stack using 1 queue.
 * 5. Demo Mode
 * 6. Exit
 */
public class TestAllTask1 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Task 1 Unified Tester ===");
        System.out.println("1. Queue Using Two Stacks");
        System.out.println("2. Queue Using One Stack");
        System.out.println("3. Stack Using Two Queues");
        System.out.println("4. Stack Using One Queue");
        System.out.println("5. Demo Mode");
        System.out.println("6. Exit");

        while (true) {
            System.out.print("\nChoose an option to test (1-6): ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        testQueueUsingTwoStacks();
                        break;
                    case 2:
                        testQueueUsingOneStack();
                        break;
                    case 3:
                        testStackUsingTwoQueues();
                        break;
                    case 4:
                        testStackUsingOneQueue();
                        break;
                    case 5:
                        runDemo();
                        break;
                    case 6:
                        System.out.println("Exiting... Thank you!");
                        return;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void testQueueUsingTwoStacks() throws Exception {
        System.out.println("\n--- Queue Using Two Stacks ---");
        QueueUsingTwoStacks<Integer> queue = createQueueUsingTwoStacks();

        while (true) {
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter a value to enqueue: ");
                    int value = scanner.nextInt();
                    queue.enqueue(value);
                    System.out.println("Enqueued: " + value);
                    break;
                case 2:
                    System.out.println("Dequeued: " + queue.dequeue());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void testQueueUsingOneStack() throws Exception {
        System.out.println("\n--- Queue Using One Stack ---");
        QueueUsingOneStack<Integer> queue = createQueueUsingOneStack();

        while (true) {
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter a value to enqueue: ");
                    int value = scanner.nextInt();
                    queue.enqueue(value);
                    System.out.println("Enqueued: " + value);
                    break;
                case 2:
                    System.out.println("Dequeued: " + queue.dequeue());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void testStackUsingTwoQueues() throws Exception {
        System.out.println("\n--- Stack Using Two Queues ---");
        StackUsingTwoQueues<Integer> stack = createStackUsingTwoQueues();

        while (true) {
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter a value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    System.out.println("Pushed: " + value);
                    break;
                case 2:
                    System.out.println("Popped: " + stack.pop());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void testStackUsingOneQueue() throws Exception {
        System.out.println("\n--- Stack Using One Queue ---");
        StackUsingOneQueue<Integer> stack = createStackUsingOneQueue();

        while (true) {
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter a value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    System.out.println("Pushed: " + value);
                    break;
                case 2:
                    System.out.println("Popped: " + stack.pop());
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void runDemo() throws Exception {
        // ANSI color codes for terminal output
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";
    
        System.out.println(YELLOW + "\n=== Demo Mode ===" + RESET);
    
        // Demo: Queue Using Two Stacks
        System.out.println(YELLOW + "\n--- Queue Using Two Stacks ---" + RESET);
        QueueUsingTwoStacks<Integer> queueTwoStacks = new QueueUsingTwoStacks<>(3);
        System.out.println(GREEN + "Initial Queue Capacity: 3" + RESET);
        try {
            System.out.println("➡️ Enqueuing 1");
            queueTwoStacks.enqueue(1);
            System.out.println("Current Queue State: " + queueTwoStacks);
    
            System.out.println("➡️ Enqueuing 2");
            queueTwoStacks.enqueue(2);
            System.out.println("Current Queue State: " + queueTwoStacks);
    
            System.out.println("➡️ Enqueuing 3");
            queueTwoStacks.enqueue(3);
            System.out.println("Current Queue State: " + queueTwoStacks);
    
            System.out.println("⬅️ Dequeuing: " + queueTwoStacks.dequeue() + " (Front of the queue)");
            System.out.println("Current Queue State: " + queueTwoStacks);
    
            System.out.println("⬅️ Dequeuing: " + queueTwoStacks.dequeue() + " (Front of the queue)");
            System.out.println("Current Queue State: " + queueTwoStacks);
    
            // Overflow example
            System.out.println("➡️ Enqueuing 4 (Expected: Queue Overflow)");
            queueTwoStacks.enqueue(4);
        } catch (Exception e) {
            System.out.println(RED + "🚨 Error: " + e.getMessage() + RESET);
        }
    
        // Demo: Queue Using One Stack
        System.out.println(YELLOW + "\n--- Queue Using One Stack ---" + RESET);
        QueueUsingOneStack<Integer> queueOneStack = new QueueUsingOneStack<>(3);
        System.out.println(GREEN + "Initial Queue Capacity: 3" + RESET);
        try {
            System.out.println("➡️ Enqueuing 10");
            queueOneStack.enqueue(10);
            System.out.println("Current Queue State: " + queueOneStack);
    
            System.out.println("➡️ Enqueuing 20");
            queueOneStack.enqueue(20);
            System.out.println("Current Queue State: " + queueOneStack);
    
            System.out.println("➡️ Enqueuing 30");
            queueOneStack.enqueue(30);
            System.out.println("Current Queue State: " + queueOneStack);
    
            System.out.println("⬅️ Dequeuing: " + queueOneStack.dequeue() + " (Front of the queue)");
            System.out.println("Current Queue State: " + queueOneStack);
    
            System.out.println("⬅️ Dequeuing: " + queueOneStack.dequeue() + " (Front of the queue)");
            System.out.println("Current Queue State: " + queueOneStack);
    
            // Underflow example
            System.out.println("⬅️ Dequeuing from empty queue (Expected: Queue Underflow)");
            queueOneStack.dequeue();
        } catch (Exception e) {
            System.out.println(RED + "🚨 Error: " + e.getMessage() + RESET);
        }
    
        // Demo: Stack Using Two Queues
        System.out.println(YELLOW + "\n--- Stack Using Two Queues ---" + RESET);
        StackUsingTwoQueues<Integer> stackTwoQueues = new StackUsingTwoQueues<>(3);
        System.out.println(GREEN + "Initial Stack Capacity: 3" + RESET);
        try {
            System.out.println("🆙 Pushing 100");
            stackTwoQueues.push(100);
            System.out.println("Current Stack State: " + stackTwoQueues);
    
            System.out.println("🆙 Pushing 200");
            stackTwoQueues.push(200);
            System.out.println("Current Stack State: " + stackTwoQueues);
    
            System.out.println("🆙 Pushing 300");
            stackTwoQueues.push(300);
            System.out.println("Current Stack State: " + stackTwoQueues);
    
            System.out.println("📤 Popping: " + stackTwoQueues.pop() + " (Top of the stack)");
            System.out.println("Current Stack State: " + stackTwoQueues);
    
            System.out.println("📤 Popping: " + stackTwoQueues.pop() + " (Top of the stack)");
            System.out.println("Current Stack State: " + stackTwoQueues);
    
            // Overflow example
            System.out.println("🆙 Pushing 400 (Expected: Stack Overflow)");
            stackTwoQueues.push(400);
        } catch (Exception e) {
            System.out.println(RED + "🚨 Error: " + e.getMessage() + RESET);
        }
    
        // Demo: Stack Using One Queue
        System.out.println(YELLOW + "\n--- Stack Using One Queue ---" + RESET);
        StackUsingOneQueue<Integer> stackOneQueue = new StackUsingOneQueue<>(3);
        System.out.println(GREEN + "Initial Stack Capacity: 3" + RESET);
        try {
            System.out.println("🆙 Pushing 1000");
            stackOneQueue.push(1000);
            System.out.println("Current Stack State: " + stackOneQueue);
    
            System.out.println("🆙 Pushing 2000");
            stackOneQueue.push(2000);
            System.out.println("Current Stack State: " + stackOneQueue);
    
            System.out.println("🆙 Pushing 3000");
            stackOneQueue.push(3000);
            System.out.println("Current Stack State: " + stackOneQueue);
    
            System.out.println("📤 Popping: " + stackOneQueue.pop() + " (Top of the stack)");
            System.out.println("Current Stack State: " + stackOneQueue);
    
            System.out.println("📤 Popping: " + stackOneQueue.pop() + " (Top of the stack)");
            System.out.println("Current Stack State: " + stackOneQueue);
    
            // Underflow example
            System.out.println("📤 Popping from empty stack (Expected: Stack Underflow)");
            stackOneQueue.pop();
        } catch (Exception e) {
            System.out.println(RED + "🚨 Error: " + e.getMessage() + RESET);
        }
    }                

    private static QueueUsingTwoStacks<Integer> createQueueUsingTwoStacks() {
        System.out.print("Enter the capacity for the queue: ");
        int capacity = scanner.nextInt();
        return new QueueUsingTwoStacks<>(capacity);
    }

    private static QueueUsingOneStack<Integer> createQueueUsingOneStack() {
        System.out.print("Enter the capacity for the queue: ");
        int capacity = scanner.nextInt();
        return new QueueUsingOneStack<>(capacity);
    }

    private static StackUsingTwoQueues<Integer> createStackUsingTwoQueues() {
        System.out.print("Enter the capacity for the stack: ");
        int capacity = scanner.nextInt();
        return new StackUsingTwoQueues<>(capacity);
    }

    private static StackUsingOneQueue<Integer> createStackUsingOneQueue() {
        System.out.print("Enter the capacity for the stack: ");
        int capacity = scanner.nextInt();
        return new StackUsingOneQueue<>(capacity);
    }
}
