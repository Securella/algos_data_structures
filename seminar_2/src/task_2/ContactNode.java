package task_2;

/**
 * One node = single contact in address book.
 * Each node has name, address, and reference to next node.
 */
public class ContactNode {
    private String name;
    private String address;
    private ContactNode next;

    /**
     * Constructor to initialize contact node.
     * @param name Contact name.
     * @param address Contact address.
     */
    public ContactNode(String name, String address) {
        this.name = name;
        this.address = address;
        this.next = null;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ContactNode getNext() {
        return next;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNext(ContactNode next) {
        this.next = next;
    }
}

