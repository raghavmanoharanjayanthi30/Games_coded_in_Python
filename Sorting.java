import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Raghav Raahul Manoharan Jayanthi
 * @version 1.0
 * @userid YOUR USER ID HERE (i.e. rjayanthi30)
 * @GTID YOUR GT ID HERE (i.e. 903536510)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */

public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Cannot have a null comparator object");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }
    /**
     * Helper method for insertionSort
     * @param arr     the array that must be sorted after the method runs
     * @param index1  one of the two positions where elements of the array are swapped
     * @param index2  one of the two positions where elements of the array are swapped
     * @param <T>     data type to sort
     */
    private static <T> void swap(T[] arr, int index1, int index2) {
        T tempData = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tempData;
    }
    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Cannot have a null comparator object");
        }
        int startIndex = 0;
        int endIndex = arr.length - 1;
        int swapIndex = 0;
        while (startIndex < endIndex) {
            swapIndex = startIndex;
            for (int i = startIndex; i < endIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapIndex = i;
                }
            }
            endIndex = swapIndex;
            for (int i = endIndex; i > startIndex; i--) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    swapIndex = i;
                }
            }
            startIndex = swapIndex;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Cannot have a null comparator object");
        }
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        int length = arr.length;
        int middleIndex = length / 2;
        T[] left = (T[]) new Object[middleIndex];
        for (int i = 0; i <= (middleIndex - 1); i++) {
            left[i] = arr[i];
        }
        T[] right = (T[]) new Object[length - middleIndex];
        for (int i = middleIndex; i <= (length - 1); i++) {
            right[i - middleIndex] = arr[i];
        }
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        int maxNum;
        if (arr.length > 0) {
            if (arr[0] == Integer.MIN_VALUE) {
                maxNum = Integer.MIN_VALUE;
            } else {
                maxNum = Math.abs(arr[0]);
            }
            for (int i = 1; i < arr.length; i++) {
                if (arr[0] == Integer.MIN_VALUE || arr[i] == Integer.MIN_VALUE) {
                    maxNum = Integer.MIN_VALUE;
                    break;
                } else if (Math.abs(arr[i]) > maxNum) {
                    maxNum = Math.abs(arr[i]);
                }
            }
            int k = 1;
            if (maxNum == Integer.MIN_VALUE) {
                k = 10;
            } else {
                while (!(maxNum <= 9 && maxNum >= -9)) {
                    maxNum = maxNum / 10;
                    k++;
                }
            }
            int y = 1;
            LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[19];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < arr.length; j++) {
                    int digit = (arr[j] / y) % 10;
                    if (buckets[digit + 9] == null) {
                        buckets[digit + 9] = new LinkedList<Integer>();
                    }
                    buckets[digit + 9].addLast(arr[j]);
                }
                int index = 0;
                for (LinkedList<Integer> bucket : buckets) {
                    while (bucket != null && bucket.size() > 0) {
                        arr[index++] = bucket.removeFirst();
                    }
                }
                y = 10 * y;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Cannot have a null comparator object");
        }
        if (rand == null) {
            throw new IllegalArgumentException();
        }
        quickSortHelper(arr, comparator, rand, 0, arr.length - 1);
    }
    /**
     * Helper method for quickSort algorithm.
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @param start      start index of sub-array that is sorted before or after pivot.
     * @param end        end index of sub-array that is sorted before or after pivot.
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator, Random rand, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int pivotIndex = rand.nextInt(end - start + 1) + start;
        T pivotValue = arr[pivotIndex];
        swap(arr, start, pivotIndex);
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotValue) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotValue) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, start, j);
        quickSortHelper(arr, comparator, rand, start, j - 1);
        quickSortHelper(arr, comparator, rand, j + 1, end);
    }
}
