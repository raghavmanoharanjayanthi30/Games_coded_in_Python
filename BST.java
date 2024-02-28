import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.LinkedList;


/**
 * Your implementation of a BST.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Null collection cannot be used to initialize the tree.");
        }
        Iterator iterator = data.iterator();
        while (iterator.hasNext()) {
            T element = (T) iterator.next();
            if (element == null) {
                throw new IllegalArgumentException("Null elements from the collection cannot be added to the tree.");
            }
            add(element);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be added to the tree.");
        }
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            addHelp(root, data);
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be removed from the tree.");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(root, data, dummy);

        return dummy.getData();
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
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
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
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
        if (result == null) {
            return false;
        }
        return (result.equals(data));
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        preorderHelper(root, list);
        return list;
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        if (root != null) {
            inorderHelper(root, list);
        }
        return list;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        postorderHelper(root, list);
        return list;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> list = new ArrayList<>();
        if (root != null) {
            Queue<BSTNode<T>> queue = new ConcurrentLinkedQueue<>();
            queue.add(root);
            while (!(queue.isEmpty())) {
                BSTNode<T> p = queue.remove();
                list.add(p.getData());
                if (p.getLeft() != null) {
                    queue.add(p.getLeft());
                }
                if (p.getRight() != null) {
                    queue.add(p.getRight());
                }
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k).
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k > n, the number of data
     *                                            in the BST
     */
    public List<T> kLargest(int k) {
        LinkedList<T> list = new LinkedList<>();
        if (k > size) {
            throw new IllegalArgumentException("Cannot retrieve " 
            + k + " largest elements from data with " + size + "elements");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k has to be a whole number;" 
            + " can't retrieve negative number of elements");
        }
        if (root == null) {
            return list;
        }
        inOrderHelperReverse(root, list, k);
        return list;
    }
    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param current Node reference to traverse the tree
     * @param data the data to add
     */
    private void addHelp(BSTNode<T> current, T data) {
        if (data.compareTo(current.getData()) < 0) {
            if (isExternal(current)) {
                current.setLeft(new BSTNode<>(data));
                size++;
            } else {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                    addHelp(current, data);
                } else {
                    current.setLeft(new BSTNode<>(data));
                    size++;
                }
            }
        } else if (data.compareTo(current.getData()) > 0) {
            if (isExternal(current)) {
                current.setRight(new BSTNode<>(data));
                size++;
            } else {
                if (current.getRight() != null) {
                    current = current.getRight();
                    addHelp(current, data);
                } else {
                    current.setRight(new BSTNode<>(data));
                    size++;
                }
            }
        }
    }
    /**
       * Method to find if node is external  
       * @param node to calculate number of children
       * @return if it has 0 children (external) 
       */
    private boolean isExternal(BSTNode<T> node) {
        return (numChildren(node) == 0);
    }
    /**
      * Method to find if node is internal 
      * @param node to calculate number of children
      * @return if it has more than 0 children(internal) 
      */
    private boolean isInternal(BSTNode<T> node) {
        return (numChildren(node) > 0);
    }
    /**
      * Helper method for isExternal and isInternal to determine 
      * if the node is external or internal
      * @param node to check if it has a left child and/or a right child
      * @return number of children the node has 
      */
    private int numChildren(BSTNode<T> node) {
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
     * Helper method to get the height of the root of the tree.
     *
     * This is done recursively.
     *
     * Computes height as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     * @param current the root node of the tree
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    private int heightHelper(BSTNode<T> current) {
        if (size == 0) {
            return -1;
        }
        if (current == null) {
            return -1;
        }
        if (isExternal(current)) {
            return 0;
        } else {
            return Math.max(heightHelper(current.getLeft()), heightHelper(current.getRight())) + 1;
        }
    }
    /**
     * Helper method to generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     * Must be O(n).
     * @param node to start with recursing the left and right sides of the tree for postorder traversal
     * @param list to be updated and returned in the postorder method 
     */
    private void postorderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            postorderHelper(node.getLeft(), list);
            postorderHelper(node.getRight(), list);
            list.add(node.getData());
        }
    }
    /**
     * Helper method to generate a preorder traversal of the tree.
     *
     * This must be done recursively.
     * Must be O(n).
     * @param node to start with recursing the left and right sides of the tree for preorder traversal
     * @param list to be updated and returned in the preorder method 
     */
    private void preorderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preorderHelper(node.getLeft(), list);
            preorderHelper(node.getRight(), list);
        }
    }
    /**
     * Helper method to generate a inorder traversal of the tree.
     *
     * This must be done recursively.
     * Must be O(n).
     * @param node to start with recursing the left and right sides of the tree for inorder traversal
     * @param list to be updated and returned in the inorder method 
     */
    private void inorderHelper(BSTNode<T> node, List<T> list) {
        if (node.getLeft() != null) {
            inorderHelper(node.getLeft(), list);
        }
        list.add(node.getData());
        if (node.getRight() != null) {
            inorderHelper(node.getRight(), list);
        }
    }
    /**
     * Helper method for kLargest to do a inorder traversal of the tree in reverse order.
     * stops adding to the list when size of the list is k
     * This must be done recursively.
     * @param node to start with recursing the left and right sides of the tree for reverse inorder traversal
     * @param list to be updated and returned in the inorder method 
     * @param k number of elements in the list to be updated
     */
    private void inOrderHelperReverse(BSTNode<T> node, LinkedList<T> list, int k) {
        if (node.getRight() != null && list.size() < k) {
            inOrderHelperReverse(node.getRight(), list, k);
        }
        if (list.size() < k) {
            list.addFirst(node.getData());
        }
        if (node.getLeft() != null && list.size() < k) {
            inOrderHelperReverse(node.getLeft(), list, k);
        }
    }
    /**
     * Helper method for get method.
     * 
     * This must be done recursively.
     * @param current to start with recursion to find the data/element needed
     * @param data is the element looked for in the tree
     * @return the element found in the traversal (may or may not equal data)
     */
    private T search(BSTNode<T> current, T data) {
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
    * Helper method to find desired data to remove
    * @param node the node to start from and find the node to be removed 
    * @param data the data to remove
    * @param dummy the node to remove
    * @return the data that was removed
    * @throws java.util.NoSuchElementException   if the data is not in the tree
    */
    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> dummy) {
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
                BSTNode<T> dummy2 = new BSTNode<>(null);
                node.setRight(removeSuccessor(node.getRight(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        return node;
    }
    /**
    * Helper method for removeHelper to find the successor node
    * @param curr the data to remove
    * @param dummy the helper node to help remove
    * @return the data that was removed
    */
    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            return curr;
        }
    }
}
