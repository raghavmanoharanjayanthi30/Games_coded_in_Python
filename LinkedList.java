import java.util.NoSuchElementException;
/**
 * Represents the Generic LinkedList class.
 *
 * In order to help learn course concepts, I worked on the homework with nobody else,
 * discussed homework topics and issues with nobody else, and/or consulted related
 * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
 * @param <T> generic class
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    /**
    * Creates a LinkedList with default values for the firstNode and lastNode
    *
    */
    public LinkedList() {
        head = null;
        tail = null;
    }
    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Your index is out of the list bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("You cannot add null data to the list");
        }
        if (isEmpty()) {
            Node<T> node = new Node<T>(data);
            head = node;
            tail = node;
            size++;
        } else {
            if (size() == index) {
                Node<T> node = new Node<T>(data);
                tail.setNext(node);
                tail = node;
                size++;
            } else {
                Node<T> previous = null;
                Node<T> current = head;
                boolean found = false;
                int count = 0;
                while ((current != null) && (!found)) {
                    if (count == index) {
                        if (previous != null) {
                            previous.setNext(new Node<T>(data , current));
                        } else {
                            head = new Node<T>(data , current);
                        }
                        found = true;
                    } else {
                        previous = current;
                        current = current.getNext();
                    }
                    count++;
                }
                size++;
            }
        }
    }
    @Override
    public T getAtIndex(int index) {
        if (index < 0 || index > (this.size - 1)) {
            throw new IllegalArgumentException("Your index is out of the list bounds");
        }
        Node<T> current = head;
        boolean found = false;
        int count = 0;
        T returnData = null;
        while ((current != null) && (!found)) {
            if (count == index) {
                found =  true;
                returnData = current.getData();
            } else {
                current = current.getNext();
            }
            count++;
        }
        return returnData;
    }
    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > (this.size - 1)) {
            throw new IllegalArgumentException("Your index is out of bounds");
        }
        T returnData = null;
        boolean found = false;
        Node<T> previous = null;
        Node<T> current = head;
        int count = 0;
        while ((current != null) && (!found)) {
            if (count == index) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                    if (index == (this.size - 1)) {
                        tail = previous;
                    }
                } else {
                    if (head == tail) {
                        tail = null;
                    }
                    head = head.getNext();
                }
                found =  true;
                returnData = current.getData();
            } else {
                previous = current;
                current = current.getNext();
            }
            count++;
        }
        size--;
        return returnData;
    }
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot remove null data from the list");
        }
        boolean found = false;
        Node<T> previous = null;
        Node<T> current = head;
        int count = 0;
        T returnData = null;
        while ((current != null) && (!found)) {
            if (data.equals(current.getData())) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                    if (current.getNext() == null) {
                        tail = previous;
                    }
                } else {
                    if (head == tail) {
                        tail = null;
                    }
                    head = head.getNext();
                }
                found =  true;
                returnData = current.getData();
            } else {
                previous = current;
                current = current.getNext();
            }
            count++;
        }
        if (!found) {
            throw new NoSuchElementException("The data is not present in the list.");
        }
        size--;
        return returnData;
    }
    @Override
    public boolean isEmpty() {
        return (head == null);
    }
    /**
    * Returns the size of the List
    * @return integer size of the list
    */
    public int size() {
        return this.size;
    }
    /**
    * Creates a method to retrieve the first node
    *
    * @return Node - references the Node object - self referential class
    */
    public Node<T> getHead() {
        return head;
    }
    /**
    * Creates a method to retrieve the last node
    *
    * @return Node - references the Node object - self referential class
    */
    public Node<T> getTail() {
        return tail;
    }
    @Override
    public void clear() {
        while (head != null) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
            }
        }
        this.size = 0;
    }
}