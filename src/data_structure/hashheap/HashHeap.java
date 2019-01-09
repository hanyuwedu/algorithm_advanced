package data_structure.hashheap;

import java.util.*;

/**
 * @param <E> the type of elements held in this collection
 * @author @hanyuw
 * @since 2.0
 * @date 1/4/2019
 */
public class HashHeap<E> extends PriorityQueue<E> {
    /**
     * The comparator, or null if priority queue uses elements'
     * natural ordering.
     */
    private final Comparator<? super E> comparator;

    /**
     * The index of each elements in the heap array.
     */
    private Map<E, Integer> position;

    /**
     * The count of occurrence of elements in the heap
     */
    private Map<E, Integer> count;

    /**
     * Heap as an arraylist.
     */
    private List<E> heap;

    /**
     * The number of elements in the heap.
     */
    private int size;

    /**
     * Creates a {@code HashHeap} with the specified comparator.
     *
     * @param comparator the comparator that will be used to order this
     *         priority queue.  If {@code null}, the {@linkplain Comparable
     *         natural ordering} of the elements will be used.
     */
    public HashHeap(Comparator<? super E> comparator) {
        this.comparator = comparator;

        this.position = new HashMap<>();
        this.count = new HashMap<>();
        this.heap = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Creates a {@code HashHeap}
     * that orders its elements according to their
     *
     * {@linkplain Comparable natural ordering}.
     */
    public HashHeap() {
        this(null);
    }

    /**
     * Inserts the specified element into this hash heap
     *
     * @param e element to be added to this hash heap
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws ClassCastException if the specified element cannot be
     *         compared with elements currently in this priority queue
     *         according to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }

        if (this.count.containsKey(e)) {
            this.count.put(e, this.count.get(e) + 1);
        } else {
            heap.add(e);
            this.count.put(e, 1);
            int lastIndex = heap.size() - 1;
            position.put(e, lastIndex);

            this.siftUp(lastIndex);
        }

        this.size++;
        return true;
    }

    @Override
    public E peek() {
        return (size == 0) ? null : heap.get(0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E remove() {
        E peek = this.peek();
        if (peek == null) {
            return null;
        } else {
            this.remove(peek);
            return peek;
        }
    }

    /**
     * Removes a single instance of the specified element from this hash heap,
     * if it is present.  More formally, removes an element {@code e} such
     * that {@code o.equals(e)}, if this queue contains one or more such
     * elements.  Returns {@code true} if and only if this queue contained
     * the specified element (or equivalently, if this queue changed as a
     * result of the call).
     *
     * Elements who have more than one instance will be removed by one quantity
     *
     * @param o element to be removed from this queue, if present
     * @return {@code true} if this queue changed as a result of the call
     */
    @Override
    public boolean remove(Object o) {
        if (!this.count.containsKey(o)) {
            return false;
        }

        if (this.count.get(o) > 1) {
            this.count.put((E) o, this.count.get(o) - 1);
        } else {
            this.count.remove(o);
            int index = this.position.get(o);

            if (index == this.heap.size() - 1) {
                /// Edege case: removed target is the last element
                this.heap.remove(heap.size() - 1);
            } else {
                this.swap(index, this.heap.size() - 1);
                this.heap.remove(heap.size() - 1);

                this.siftUp(index);
                this.siftDown(index);
            }

            this.count.remove(o);
            this.position.remove(o);
        }

        this.size--;
        return true;
    }


    /**
     * Remove all elements from the hashheap
     * Ad hoc solution. removeAll is alreay defined in AbstractQueue by remove on the iterator
     *
     * @param c collections where elements to be removed from the hashheap
     * @return whether all elements are successfully removed.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.remove(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns an iterator over the elements in this hash heap. The iterator
     * does not return the elements in any particular order.
     *
     * @return an iterator over the elements in this queue
     */
    @Override
    public Iterator<E> iterator() {
        return this.heap.iterator();
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    /**
     * Inserts item x at position index, maintaining heap invariant by
     * promoting x up the tree until it is greater than or equal to
     * its parent, or is the root.
     *
     * To simplify and speed up coercions and comparisons. the
     * Comparable and Comparator versions are separated into different
     * methods that are otherwise identical. (Similarly for siftDown.)
     *
     * @param index the position of current element
     */
    private void siftUp(Integer index) {
        if (comparator != null) {
            this.siftUpUsingComparator(index);
        } else {
            this.siftUpComparable(index);
        }
    }

    private void siftUpComparable(int index) {
        int parent = getParentIndex(index);

        while (parent >= 0
                && ((Comparable<? super E>) this.heap.get(index)).compareTo(this.heap.get(parent)) < 0) {
            this.swap(index, parent);

            index = parent;
            parent = getParentIndex(parent);
        }
    }

    private void siftUpUsingComparator(int index) {
        int parent = getParentIndex(index);

        while (parent >= 0
                && this.comparator.compare(this.heap.get(index), this.heap.get(parent)) < 0) {
            this.swap(index, parent);

            index = parent;
            parent = getParentIndex(parent);
        }
    }

    /**
     * Inserts item x at position index, maintaining heap invariant by
     * demoting x down the tree repeatedly until it is less than or
     * equal to its children or is a leaf.
     *
     * @param index the position of current element
     */
    private void siftDown(int index) {
        if (comparator != null) {
            this.siftDownUsingComparator(index);
        } else {
            this.siftDownComparable(index);
        }
    }

    private void siftDownComparable(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);

        while (left < this.heap.size()) {
            int min = left;
            if (right < this.heap.size()
                    && ((Comparable<? super E>) this.heap.get(right)).compareTo(this.heap.get(left)) < 0) {
                min = right;
            }

            if (((Comparable<? super E>) this.heap.get(index)).compareTo(this.heap.get(min)) > 0) {
                this.swap(min, index);

                index = min;
                left = getLeftChildIndex(index);
                right = getRightChildIndex(index);
            } else {
                return;
            }
        }
    }

    private void siftDownUsingComparator(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);

        while (left < this.heap.size()) {
            int min = left;
            if (right < this.heap.size()
                    && this.comparator.compare(this.heap.get(right), this.heap.get(left)) < 0) {
                min = right;
            }

            if (this.comparator.compare(this.heap.get(index), this.heap.get(min)) > 0) {
                this.swap(min, index);

                index = min;
                left = getLeftChildIndex(index);
                right = getRightChildIndex(index);
            } else {
                return;
            }
        }
    }


    private void swap(int left, int right) {
        E temp = this.heap.get(left);
        this.heap.set(left, this.heap.get(right));
        this.heap.set(right, temp);

        this.position.put(this.heap.get(left), left);
        this.position.put(this.heap.get(right), right);
    }

    private int getParentIndex(int current) {
        return (current - 1) / 2;
    }

    private int getLeftChildIndex(int current) {
        return current * 2 + 1;
    }

    private int getRightChildIndex(int current) {
        return current * 2 + 2;
    }


    public void print() {
        System.out.println("count: " + this.count.toString());
        System.out.println("position: " + this.position.toString());
        System.out.println("heap: " + this.heap.toString());
    }
}
