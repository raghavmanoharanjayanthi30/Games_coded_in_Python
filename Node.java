/**
 * Represents the Node class.
 *
 * In order to help learn course concepts, I worked on the homework with nobody else,
 * discussed homework topics and issues with nobody else, and/or consulted related
 * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
 * @param <T> generic class;
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
public class Node<T> {
    private T data;
    private Node<T> next;
    /**
    * Creates a Node with all required parameters
    *
    * @param data - of the generic type T
    */
    public Node(T data) {
        this(data, null);
    }
    /**
    * Creates a Node with all required parameters
    *
    * @param data - of the generic type T
    * @param next - references the Node object - self referential class
    */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    /**
    * Creates a method to retrieve the data
    *
    * @return T - of the generic type T
    */
    public T getData() {
        return data;
    }
   /**
    * Creates a method to retrieve the next node
    *
    * @return Node - references the Node object - self referential class
    */
    public Node<T> getNext() {
        return next;
    }
    /**
    * Creates a method to set the data
    *
    * @param data -  of the generic type T
    */
    public void setData(T data) {
        this.data = data;
    }
    /**
    * Creates a method to set the next node
    *
    * @param next -  references the Node object - self referential class
    */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}