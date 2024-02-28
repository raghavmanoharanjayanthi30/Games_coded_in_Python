import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 *
 * @author Raghav Raahul Manoharan Jayanthi
 * @version 1.0
 * @userid YOUR USER ID HERE rjayanthi30
 * @GTID YOUR GT ID HERE (i.e. 903536510)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot initialize the heap with null arraylist.");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int j = 1; j <= data.size(); j++) {
            T element = data.get(j - 1);
            if (element == null) {
                throw new IllegalArgumentException("Arraylist passed into the MinHeap constructor " 
                + "has an element that is null.");
            }
            backingArray[j] = element;
            size++; 
        }
        buildHeap();
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the heap");
        }
        if (size == backingArray.length - 1) {
            resize(backingArray.length * 2);
        }
        backingArray[size + 1] = data;
        size++;
        upheap(size);
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove and return the minimum element from an empty heap.");
        }
        T removeItem = backingArray[1];
        swap(1, size);
        backingArray[size] = null;
        size--;
        downheap(1);
        return removeItem;
    }

    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot retrieve and return the minimum element from an empty heap.");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
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
     * Returns the size of the heap.
     *
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
    * Compute the array index of the parent.
    * 
    * 
    * @param j the array index of the left or right child
    * @return the array index of the parent
    */
    private int parent(int j) {
        return (j / 2);
    }
    /**
    * Compute the array index of the left child.
    * 
    * 
    * @param j the array index of the parent
    * @return the array index of the left child
    */
    private int left(int j) {
        return (2 * j);
    }
    /**
    * Compute the array index of the right child.
    * 
    * 
    * @param j the array index of the parent
    * @return the array index of the right child
    */
    private int right(int j) {
        return (2 * j + 1);
    }
    /**
    * Checks if the parent has a left child
    * 
    * 
    * @param j the array index of the parent
    * @return true if the parent has a left child
    */
    private boolean hasLeft(int j) {
        return left(j) < (size + 1);
    }
    /**
    * Checks if the parent has a right child
    * 
    * 
    * @param j the array index of the parent
    * @return true if the parent has a right child
    */
    private boolean hasRight(int j) {
        return right(j) < (size + 1);
    }
    /**
    * Swap the elements of the array at indices i and j
    * 
    * 
    * @param i the index of the array element
    * @param j the index of the array element  
    */
    private void swap(int i, int j) {
        T tempData = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = tempData;
    }
    /**
    * Perform upHeap operation
    *
    * Moves the array element at index j higher, if required to restore the heap property
    * 
    * 
    * @param j the index to restore the heap property during addition
    */
    private void upheap(int j) {
        while (j > 1) {
            int p = parent(j);
            if (backingArray[j].compareTo(backingArray[p]) >= 0) {
                break; 
            }
            swap(j, p);
            j = p;
        }
    }
    /**
    * Perform downHeap operation
    *
    * Moves the array element at index j lower, if required to restore the heap property
    * 
    *
    * @param j the index to restore the heap property during removal/ buildHeap operation
    */    
    private void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (backingArray[leftIndex].compareTo(backingArray[rightIndex]) > 0) {
                    smallChildIndex = rightIndex;
                }
            }
            if (backingArray[smallChildIndex].compareTo(backingArray[j]) >= 0) {
                break;
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }
    /**
    * Resizes the array.
    * 
    * Resizes the backingArray to have twice its existing capacity
    *
    * 
    * @param capacity the new capacity of the backingArray after it is resized
    */
    private void resize(int capacity) {
        T[] tempArray = (T[]) new Comparable[capacity];
        for (int i = 1; i <= size; i++) {
            tempArray[i] = backingArray[i];
        }
        backingArray = tempArray;
    } 
    /**
    * BuildHeap algorithm
    *
    * Implement a bottom up construction of the heap.
    *
    * 
    */
    private void buildHeap() {
        for (int j = size / 2; j >= 1; j--) {
            downheap(j);
        }
    }   
}