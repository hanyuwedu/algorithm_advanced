package data_structure.hashheap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerHashMaxHeap {
    private List<Integer> heap;
    private Map<Integer, Integer> count;
    private Map<Integer, Integer> position;
    int size;

    public IntegerHashMaxHeap() {
        this.heap = new ArrayList<>();
        this.count = new HashMap<>();
        this.position = new HashMap<>();
        this.size = 0;
    }

    public void add(int next) {
        if (this.count.containsKey(next)) {
            this.count.put(next, this.count.get(next) + 1);
        } else {
            heap.add(next);
            this.count.put(next, 1);
            int lastIndex = heap.size() - 1;
            this.position.put(next, lastIndex);
            this.size++;

            this.siftUp(lastIndex);
        }

    }

    public Integer remove() {
        if (this.heap.isEmpty()) {
            return null;
        }

        int next = this.peek();
        this.remove();
        return next;
    }

    public boolean remove(int next) {
        if (!this.count.containsKey(next)) {
            return false;
        }

        if (this.count.get(next) > 1) {
            this.count.put(next, this.count.get(next) - 1);
        } else {
            this.count.remove(next);
            int index = this.position.get(next);

            if (index == this.heap.size() - 1) {
                this.heap.remove(this.heap.size() - 1);
                this.count.remove(next);
                this.position.remove(next);
            } else {
                this.swap(index, this.heap.size() - 1);
                this.heap.remove(this.heap.size() - 1);
                this.count.remove(next);
                this.position.remove(next);

                this.siftUp(index);
                this.siftDown(index);
            }
        }

        this.size--;
        return true;
    }

    public Integer peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public int size() {
        return this.size;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = this.getParentIndex(index);
            if (this.heap.get(parent) > this.heap.get(index)) {
                break;
            } else {
                this.swap(index, parent);
                index = parent;
            }
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = getLeftChildIndex(index);
            int right = getRightChildIndex(index);

            if (left > this.heap.size() - 1) {
                break;
            }

            int max = left;

            if (right <= this.heap.size() - 1 && this.heap.get(right) > this.heap.get(left)) {
                max = right;
            }

            if (this.heap.get(max) < this.heap.get(index)) {
                break;
            } else {
                this.swap(index, max);
                index = max;
            }
        }
    }

    private void swap(int left, int right) {
        int temp = this.heap.get(left);
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

}
