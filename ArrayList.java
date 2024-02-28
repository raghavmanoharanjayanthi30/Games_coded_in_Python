import java.util.NoSuchElementException;
/**
 * Your implementation of an ArrayList.
 *
 * @author Raghav Raahul Manoharan Jayanthi
 * @version 1.0
 * @userid YOUR USER ID HERE rjayanthi30
 * @GTID YOUR GT ID HERE (i.e. 903536510)
 * <p>
 * Collaborators: NONE
 * <p>
 * Resources: DATA STRUCTURES AND ALGORITHMS IN JAVA, 6TH EDITION
 */
public class ArrayList<T> {
    /**
     * The initial capacity of the ArrayList.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;
    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;
    /**
     * Constructs a new ArrayList.
     * <p>
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }
    /**
     * Adds the element to the specified index.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size + 1
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        try {
            validateIndex(index, size + 1);
            if (data == null) {
                throw new IllegalArgumentException("Cannot insert null data into data structure.");
            }
            if (size == backingArray.length) {
                resize(backingArray.length * 2);
            }
            for (int i = size - 1; i >= index; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[index] = data;
            size++;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("addAtIndex:" + e);
        } catch (IllegalArgumentException e) {
            System.err.println("addAtIndex:" + e);
        }
    }
    /**
     * Adds the element to the front of the list.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }
    /**
     * Adds the element to the back of the list.
     * <p>
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }
    /**
     * Removes and returns the element at the specified index.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T data = null;
        try {
            if (isEmpty()) {
                throw new NoSuchElementException("Cannot remove data from empty data structure.");
            }
            validateIndex(index, size);
            data = backingArray[index];
            for (int i = index; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[size - 1] = null;
            size--;
        } catch (NoSuchElementException e) {
            System.err.println("removeAtIndex:" + e);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("removeAtIndex:" + e);
        }
        return data;
    }
    /**
     * Removes and returns the first element of the list.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        return removeAtIndex(0);
    }
    /**
     * Removes and returns the last element of the list.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }
    /**
     * Returns the element at the specified index.
     * <p>
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        T data = null;
        try {
            validateIndex(index, size);
            data = backingArray[index];
        } catch (IndexOutOfBoundsException e) {
            System.err.println("get:" + e);
        }
        return data;
    }
    /**
     * Returns whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
     * Clears the list.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        int count = size - 1;
        while (count >= 0) {
            removeAtIndex(count);
            count--;
        }
        resize(INITIAL_CAPACITY);
        size = 0;
    }
    /**
     * Returns the backing array of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    /**
     * Validates the index.
     * <p>
     * Validates whether the index is in the range from 0 to (s-1)
     * <p>
     * @param i the index of the element to add or remove
     * @param s the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= s
     */
    private void validateIndex(int i, int s) {
        if (i < 0 || i >= s) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }
    /**
     * Resizes the array.
     * <p>
     * Resizes the backingArray to have twice its existing capacity
     *
     * <p>
     * @param capacity the new capacity of the backingArray after it is resized
     */
    private void resize(int capacity) {
        T[] tempArray = (T[]) new Object[capacity];
        for (int i = 0; i <= size - 1; i++) {
            tempArray[i] = backingArray[i];
        }
        backingArray = tempArray;
    }
}