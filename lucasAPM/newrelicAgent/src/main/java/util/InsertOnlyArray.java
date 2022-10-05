package util;

import java.util.Arrays;

/**
 * packageName    : util
 * fileName       : InsertOnlyArray
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class InsertOnlyArray<E> {

    private volatile Object[] elements;

    /**
     * The number of elements in the array. This is always accessed under a lock.
     */
    private int size = 0;

    public InsertOnlyArray(int capacity) {
        elements = new Object[capacity];
    }

    /**
     * Get an element from the array.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // we don't access the elements array under the lock because it's always swapped out as an atomic operation.
        // even if we have a stale copy of the elements, the array is insert only so the item at 'index' should still be
        // valid
        return (E) elements[index];
    }

    /**
     * Returns the index of an element. This is potentially very slow because we have to scan the array.
     *
     * @param element
     */
    public int getIndex(E element) {
        Object[] arr = elements;
        for (int i = 0; i < arr.length; i++) {
            if (element.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add an element to the array using a lock.
     *
     * @param newElement
     */
    public synchronized int add(E newElement) {
        int position = size;
        if (size + 1 > this.elements.length) {
            grow(size + 1);
        }
        elements[position] = newElement;
        size += 1;

        return position;
    }

    /**
     * This is always called under the lock.
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        // this is roughly the same logic as ArrayList.grow
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        newCapacity = Math.max(newCapacity, minCapacity);
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
