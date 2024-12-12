package task_2;

import java.util.Scanner;

/**
 * AddressBook with automatic menu and manual steps for user interaction.
 */
public class AddressBook {
    private ContactNode head;

    public AddressBook() {
        this.head = null;
    }

    // Requirement 1: Adding node
    public void addContact(String name, String address) {
        ContactNode newNode = new ContactNode(name, address);
        if (head == null) {
            head = newNode;
        } else {
            ContactNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // Requirement 2: Removing node
    public void removeContact(int index) throws IndexOutOfBoundsException {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            head = head.getNext();
            return;
        }

        ContactNode current = head;
        ContactNode previous = null;
        int count = 0;

        while (current != null && count < index) {
            previous = current;
            current = current.getNext();
            count++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        previous.setNext(current.getNext());
    }

    // Requirement 3: Getting node using index
    public ContactNode getContact(int index) throws IndexOutOfBoundsException {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        ContactNode current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                return current;
            }
            current = current.getNext();
            count++;
        }

        throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    // Requirement 4: Printing the complete list of contacts to screen via loop
    public void printAllContacts() {
        if (head == null) {
            System.out.println("Address book is empty.");
            return;
        }

        ContactNode current = head;
        int index = 0;
        while (current != null) {
            System.out.printf("[%d] Name: %s, Address: %s%n", index, current.getName(), current.getAddress());
            current = current.getNext();
            index++;
        }
    }

    // Extra implementation: Searching contacts by name
    public ContactNode searchContactByName(String name) {
        ContactNode current = head;
        while (current != null) {
            if (current.getName().equalsIgnoreCase(name)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        // === Automatic Execution ===
        System.out.println("=== Automatic Execution ===");

        // Adding contacts
        System.out.println("Adding contacts to Address Book...");
        addressBook.addContact("Pekka", "Puistokatu 1");
        addressBook.addContact("Mikko", "Eiranranta 2");
        addressBook.addContact("Jarkko", "Hernesaarenkatu 3");

        // Printing all contacts
        System.out.println("\nAll Contacts:");
        addressBook.printAllContacts();

        // Retrieving contact
        System.out.println("\nRetrieving contact at index 1:");
        try {
            ContactNode contact = addressBook.getContact(1);
            System.out.printf("Name: %s, Address: %s%n", contact.getName(), contact.getAddress());
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

        // Removing contact
        System.out.println("\nRemoving contact at index 0:");
        try {
            addressBook.removeContact(0);
            addressBook.printAllContacts();
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

        // Searching for contact
        System.out.println("\nSearching for contact 'Ahti':");
        ContactNode searchResult = addressBook.searchContactByName("Ahti");
        if (searchResult != null) {
            System.out.printf("Found: Name: %s, Address: %s%n", searchResult.getName(), searchResult.getAddress());
        } else {
            System.out.println("Contact not found.");
        }

        // === User Interaction ===
        System.out.println("\n=== User Interaction ===");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Retrieve Contact");
            System.out.println("4. Search Contact by Name");
            System.out.println("5. Print All Contacts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1: // Add Contact
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();
                        addressBook.addContact(name, address);
                        System.out.println("Contact added successfully!");
                        break;

                    case 2: // Remove Contact
                        System.out.print("Enter the index of the contact to remove: ");
                        int removeIndex = scanner.nextInt();
                        addressBook.removeContact(removeIndex);
                        System.out.println("Contact removed successfully!");
                        break;

                    case 3: // Retrieve Contact
                        System.out.print("Enter the index of the contact to retrieve: ");
                        int retrieveIndex = scanner.nextInt();
                        ContactNode contact = addressBook.getContact(retrieveIndex);
                        System.out.printf("Name: %s, Address: %s%n", contact.getName(), contact.getAddress());
                        break;

                    case 4: // Search Contact by Name
                        System.out.print("Enter the name to search: ");
                        String searchName = scanner.nextLine();
                        ContactNode searchResultByName = addressBook.searchContactByName(searchName);
                        if (searchResultByName != null) {
                            System.out.printf("Found: Name: %s, Address: %s%n", searchResultByName.getName(), searchResultByName.getAddress());
                        } else {
                            System.out.println("Contact not found.");
                        }
                        break;

                    case 5: // Print All Contacts
                        addressBook.printAllContacts();
                        break;

                    case 6: // Exit
                        System.out.println("Exiting Address Book. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

