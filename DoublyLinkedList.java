import java.util.NoSuchElementException;
/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author RAGHAV RAAHUL MANOHARAN JAYANTHI
 * @version 1.0
 * @userid YOUR USER ID HERE rjayanthi30
 * @GTID YOUR GT ID HERE 903536510
 *
 * Collaborators: NONE
 *
 * Resources: NONE
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into linked list.");
        }
        if (index == 0) {
            if (isEmpty()) {
                DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
                head  = newNode;
                tail  = newNode;
            } else {
                DoublyLinkedListNode<T> newest = new DoublyLinkedListNode<>(data, null, head);
                head.setPrevious(newest);
                head = newest;
            }
        } else if (index == size) {
            if (isEmpty()) {
                DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
                head  = newNode;
                tail  = newNode;
            } else {
                DoublyLinkedListNode<T> newest = new DoublyLinkedListNode<>(data, tail, null);
                tail.setNext(newest);
                tail = newest;
            }
        } else {
            DoublyLinkedListNode<T> current;
            if (index > (size - 1) / 2) {
                current = tail;
                for (int i = 1; i <= (size - 1 - index); i++) {
                    current = current.getPrevious();
                }
            } else {
                current = head;
                for (int i = 1; i <= index; i++) {
                    current = current.getNext();
                }
            }
            DoublyLinkedListNode<T> newest = new DoublyLinkedListNode<>(data, current.getPrevious(), current);
            (current.getPrevious()).setNext(newest);
            current.setPrevious(newest);
        }
        size++;
    }
    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }
    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }
    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        DoublyLinkedListNode<T> current = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        if (index == 0) {
            current = head;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrevious(null);
            }
        } else if (index == size - 1) {
            current = tail;
            tail = tail.getPrevious();
            tail.setNext(null);
        } else {
            if (index > (size - 1) / 2) {
                current = tail;
                for (int i = 1; i <= (size - 1 - index); i++) {
                    current = current.getPrevious();
                }
            } else {
                current = head;
                for (int i = 1; i <= index; i++) {
                    current = current.getNext();
                }
            }
            DoublyLinkedListNode<T> pre = current.getPrevious();
            DoublyLinkedListNode<T> post = current.getNext();
            pre.setNext(post);
            post.setPrevious(pre);
        }
        size--;
        return current.getData();
    }
    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove data from empty linked list.");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove data from empty linked list.");
        }
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        T data = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        if (index > (size - 1) / 2) {
            DoublyLinkedListNode<T> current = tail;
            for (int i = 1; i <= (size - 1 - index); i++) {
                current = current.getPrevious();
            }
            data = current.getData();
        } else {
            DoublyLinkedListNode<T> current = head;
            for (int i = 1; i <= index; i++) {
                current = current.getNext();
            }
            data = current.getData();
        }
        return data;
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        DoublyLinkedListNode<T> current = null;
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove data from empty linked list.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from linked list.");
        }
        boolean found = false;
        if (data.equals(tail.getData())) {
            found = true;
            current = tail;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = tail.getPrevious();
                tail.setNext(null);
            }
        } else {
            current = tail;
            for (int i = 0; i <= (size - 1); i++) {
                if ((current.getData()).equals(data)) {
                    found = true;
                    break;
                }
                current = current.getPrevious();
            }
            if (!found) {
                throw new NoSuchElementException("Data is not found in the linked list.");
            }
            if (current.getPrevious() != null) {
                DoublyLinkedListNode<T> pre = current.getPrevious();
                DoublyLinkedListNode<T> post = current.getNext();
                pre.setNext(post);
                post.setPrevious(pre);
            } else {
                head = head.getNext();
                head.setPrevious(null);
            }
        }
        size--;
        return current.getData();
    }
    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        DoublyLinkedListNode<T> current = head;
        for (int i = 0; i <= size - 1; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }
    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}