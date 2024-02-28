import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;
/**
 * Your implementation of an AVL.
 *
 * @author Raghav Raahul Manoharan Jayanthi
 * @version 1.0
 * @userid YOUR USER ID HERE (i.e. rjayanthi30)
 * @GTID YOUR GT ID HERE (i.e. 903536510)
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;
    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Null collection cannot be used to initialize the AVL.");
        }
        Iterator<T> iterator = data.iterator();
        while (iterator.hasNext()) {
            T element = (T) iterator.next();
            if (element == null) {
                throw new IllegalArgumentException("Null elements from the collection cannot be added to the AVL.");
            }
            add(element);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be added to the tree.");
        }
        if (root == null) {
            root = new AVLNode<T>(data);
            root.setHeight(0);
            root.setBalanceFactor(0);
            size++;
        } else {
            root = addHelp(root, data);
        }
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     *    replace the data, NOT successor. As a reminder, rotations can occur
     *    after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be removed from the tree.");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be retrieved from the tree.");
        }
        T result = search(root, data);

        if (result != null && result.equals(data)) {
            return result;
        } else {
            throw new NoSuchElementException("Data to be retrieved is not present in the tree.");
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be contained in the tree.");
        }
        if (root == null) {
            return false;
        }
        T result = search(root, data);
        return (result != null && result.equals(data)) ? true : false;
    }

    /**
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return computeHeight(root);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Find a path of letters in the tree that spell out a particular word,
     * if the path exists.
     *
     * Ex: Given the following AVL
     *
     *                   g
     *                 /   \
     *                e     i
     *               / \   / \
     *              b   f h   n
     *             / \         \
     *            a   c         u
     *
     * wordSearch([b, e, g, i, n]) returns the list [b, e, g, i, n],
     * where each letter in the returned list is from the tree and not from
     * the word array.
     *
     * wordSearch([h, i]) returns the list [h, i], where each letter in the
     * returned list is from the tree and not from the word array.
     *
     * wordSearch([a]) returns the list [a].
     *
     * wordSearch([]) returns an empty list [].
     *
     * wordSearch([h, u, g, e]) throws NoSuchElementException. Although all
     * 4 letters exist in the tree, there is no path that spells 'huge'.
     * The closest we can get is 'hige'.
     *
     * To do this, you must first find the deepest common ancestor of the
     * first and last letter in the word. Then traverse to the first letter
     * while adding letters on the path to the list while preserving the order
     * they appear in the word (consider adding to the front of the list).
     * Finally, traverse to the last letter while adding its ancestor letters to
     * the back of the list. Please note that there is no relationship between
     * the first and last letters, in that they may not belong to the same
     * branch. You will most likely have to split off to traverse the tree when
     * searching for the first and last letters.
     *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you may have to add to the front and
     * back of the list.
     *
     * You will only need to traverse to the deepest common ancestor once.
     * From that node, go to the first and last letter of the word in one
     * traversal each. Failure to do so will result in a efficiency penalty.
     * Validating the path against the word array efficiently after traversing
     * the tree will NOT result in an efficiency penalty.
     *
     * If there exists a path between the first and last letters of the word,
     * there will only be 1 valid path.
     *
     * You may assume that the word will not contain duplicate letters.
     *
     * WARNING: Do not return letters from the passed-in word array!
     * If a path exists, the letters should be retrieved from the tree.
     * Returning any letter from the word array will result in a penalty!
     *
     * @param word array of T, where each element represents a letter in the
     * word (in order).
     * @return list containing the path of letters in the tree that spell out
     * the word, if such a path exists. Order matters! The ordering of the
     * letters in the returned list should match that of the word array.
     * @throws java.lang.IllegalArgumentException if the word array is null
     * @throws java.util.NoSuchElementException if the path is not in the tree
     */
    public List<T> wordSearch(T[] word) {
        if (word == null) {
            throw new IllegalArgumentException("Cannot search for a null word in AVL");
        }
        LinkedList<T> wordList = new LinkedList<>();
        if (word.length == 0) {
            return wordList;
        }
        T beginning = word[0];
        T end = word[word.length - 1];
        T newEnd = end;
        T newBeginning = beginning;
        boolean reversed = false;
        if (beginning.compareTo(end) > 0) {
            reversed = true;
            newEnd = beginning;
            newBeginning = end;
        }
        LinkedList<T> returningList = getDCA(root, newBeginning, newEnd, wordList, reversed);
        for (int i = 0; i < word.length; i++) {
            if (!(word[i].equals(returningList.get(i)))) {
                throw new NoSuchElementException("The word list is not in the AVL tree.");
            }
        }
        return returningList;
    }

    /**
     * This method retrieves the deepest common ancestor of the first and last letter of the word.
     * @param current node to start finding DCA
     * @param beginning beginning of the word
     * @param end end of the word
     * @param wordList list to update
     * @param reversed checks if the word is alphabetically forward or backward
     * @return updated list
     */
    private LinkedList<T> getDCA(AVLNode<T> current, T beginning, T end, LinkedList<T> wordList, boolean reversed) {
        if (current == null) {
            throw new NoSuchElementException("The word list is not in the AVL tree.");
        }
        if (beginning.compareTo(current.getData()) > 0) {
            wordList = getDCA(current.getRight(), beginning, end, wordList, reversed);
            if (wordList.size() != 0) {
                return wordList;
            }
        } else if (end.compareTo(current.getData()) < 0) {
            wordList = getDCA(current.getLeft(), beginning, end, wordList, reversed);
            if (wordList.size() != 0) {
                return wordList;
            }
        }
        if (current.getData().compareTo(end) == 0) {
            traverseFront(current, beginning, wordList, reversed);
        } else if ((current.getData().compareTo(beginning) == 0)) {
            traverseBack(current, end, wordList, reversed);
        } else if (reversed) {
            traverseBack(current, end, wordList, reversed);
            wordList.removeLast();
            traverseFront(current, beginning, wordList, reversed);
        } else {
            traverseBack(current, end, wordList, reversed);
            wordList.removeFirst();
            traverseFront(current, beginning, wordList, reversed);
        }
        return wordList;
    }

    /**
     * This method traverses backwards
     * @param current node to start traversing backward
     * @param end end of the word
     * @param wordList list to update
     * @param reversed checks if the word is alphabetically forward or backward
     */

    private void traverseBack(AVLNode<T> current, T end, LinkedList<T> wordList, boolean reversed) {
        if (current == null) {
            throw new NoSuchElementException("The word list is not in the AVL tree.");
        }
        if (reversed) {
            if (end.compareTo(current.getData()) < 0) {
                traverseBack(current.getLeft(), end, wordList, reversed);
            } else if (end.compareTo(current.getData()) > 0) {
                traverseBack(current.getRight(), end, wordList, reversed);
            }
            wordList.addLast(current.getData());
        } else {
            if (end.compareTo(current.getData()) < 0) {
                traverseBack(current.getLeft(), end, wordList, reversed);
            } else if (end.compareTo(current.getData()) > 0) {
                traverseBack(current.getRight(), end, wordList, reversed);
            }
            wordList.addFirst(current.getData());
        }

    }

    /**
     * This method traverses forwards.
     * @param current the node to start traversing forward
     * @param beginning the beginning of the word
     * @param wordList list to be updated
     * @param reversed checks if the word is alphabetically forward or backward
     */
    private void traverseFront(AVLNode<T> current, T beginning, LinkedList<T> wordList, boolean reversed) {
        if (current == null) {
            throw new NoSuchElementException("The word list is not in the AVL tree.");
        }
        if (reversed) {
            wordList.addLast(current.getData());
            if (beginning.compareTo(current.getData()) < 0) {
                traverseFront(current.getLeft(), beginning, wordList, reversed);
            } else if (beginning.compareTo(current.getData()) > 0) {
                traverseFront(current.getRight(), beginning, wordList, reversed);
            }
        } else {
            wordList.addFirst(current.getData());
            if (beginning.compareTo(current.getData()) < 0) {
                traverseFront(current.getLeft(), beginning, wordList, reversed);
            } else if (beginning.compareTo(current.getData()) > 0) {
                traverseFront(current.getRight(), beginning, wordList, reversed);
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    /**
     * Helper method for get method.
     *
     * This must be done recursively.
     * @param current to start with recursion to find the data/element needed
     * @param data is the element looked for in the tree
     * @return the element found in the traversal (may or may not equal data)
     */
    private T search(AVLNode<T> current, T data) {
        if (current == null) {
            return null;
        }
        if (isExternal(current)) {
            return current.getData();
        }
        int comp = data.compareTo(current.getData());
        if (comp == 0) {
            return current.getData();
        } else if (comp < 0) {
            return search(current.getLeft(), data);
        } else {
            return search(current.getRight(), data);
        }
    }
    /**
     * Helper method to update the height and balance factor of a node
     *
     * @param node to update
     */
    private void updateHeightAndBF(AVLNode<T> node) {
        int leftHeight = computeHeight(node.getLeft());
        int rightHeight = computeHeight(node.getRight());
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        node.setBalanceFactor(leftHeight - rightHeight);
    }
    /**
     * Helper method to return the height of a node
     *
     * @param node the node to compute height
     * @return height of the node
     */
    private int computeHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }
    /**
     * Helper method to add the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Helper method to traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     *
     * @param current Node reference to traverse the tree
     * @param data the data to add
     * @return node the node returned after restructuring
     */
    private AVLNode<T> addHelp(AVLNode<T> current, T data) {
        if (current == null) {
            size++;
            AVLNode<T> node = new AVLNode<>(data);
            node.setHeight(0);
            return node;
        } else {
            if (data.compareTo(current.getData()) > 0) {
                current.setRight(addHelp(current.getRight(), data));
            } else if (data.compareTo(current.getData()) < 0) {
                current.setLeft(addHelp(current.getLeft(), data));
            }
        }
        updateHeightAndBF(current);
        return restructure(current);
    }
    /**
     * Helper method to find desired data to remove
     * @param node the node to start from and find the node to be removed
     * @param data the data to remove
     * @param dummy the node to remove
     * @return the data that was removed
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private AVLNode<T> removeHelper(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("Data to be retrieved is not present in the tree.");
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            size--;
            if (isExternal(node)) {
                return null;
            } else if (node.getLeft() != null && node.getRight() == null) {
                return node.getLeft();
            } else if (node.getRight() != null && node.getLeft() == null) {
                return node.getRight();
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(null);
                node.setLeft(removePredecessor(node.getLeft(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        updateHeightAndBF(node);
        return restructure(node);
    }
    /**
     * Helper method for removeHelper to find the predecessor node
     * @param current the data to remove
     * @param dummy the helper node to help remove
     * @return the data that was removed
     */
    private AVLNode<T> removePredecessor(AVLNode<T> current, AVLNode<T> dummy) {
        if (current.getRight() == null) {
            dummy.setData(current.getData());
            return current.getLeft();
        } else {
            current.setRight(removePredecessor(current.getRight(), dummy));
        }
        updateHeightAndBF(current);
        return restructure(current);
    }
    /**
     * Helper method for isExternal to determine if node is external
     * @param node to check if it has a left child and/or a right child
     * @return number of children the node has
     */
    private int numChildren(AVLNode<T> node) {
        int count = 0;
        if (node.getLeft() != null) {
            count++;
        }
        if (node.getRight() != null) {
            count++;
        }
        return count;
    }
    /**
     * Method to find if node is external
     * @param node to calculate number of children
     * @return if it has 0 children (external)
     */
    private boolean isExternal(AVLNode<T> node) {
        return (numChildren(node) == 0);
    }
    /**
     * Method to perform left rotation
     * @param a the node on which left rotation is performed
     * @return node the root of the subtree after rotation
     */
    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        updateHeightAndBF(a);
        updateHeightAndBF(b);
        return b;
    }
    /**
     * Method to perform right rotation
     * @param c the node on which right rotation is performed
     * @return node the root of the subtree after rotation
     */
    private AVLNode<T> rotateRight(AVLNode<T> c) {
        AVLNode<T> b = c.getLeft();
        c.setLeft(b.getRight());
        b.setRight(c);
        updateHeightAndBF(c);
        updateHeightAndBF(b);
        return b;
    }
    /**
     * Method to balance the tree if a node in the tree has a balance factor <=-2 or >=2
     * @param current the node to be rebalanced
     * @return node the root of subtree after rotation
     */
    private AVLNode<T> restructure(AVLNode<T> current) {
        if (current.getBalanceFactor() <= -2) {
            if (current.getRight().getBalanceFactor() == 0 || current.getRight().getBalanceFactor() == -1) {
                return rotateLeft(current);
            } else if (current.getRight().getBalanceFactor() == 1) {
                current.setRight(rotateRight(current.getRight()));
                return rotateLeft(current);
            }
        } else if (current.getBalanceFactor() >= 2) {
            if (current.getLeft().getBalanceFactor() == 0 || current.getLeft().getBalanceFactor() == 1) {
                return rotateRight(current);
            } else if (current.getLeft().getBalanceFactor() == -1) {
                current.setLeft(rotateLeft(current.getLeft()));
                return rotateRight(current);
            }
        }
        return current;
    }
}