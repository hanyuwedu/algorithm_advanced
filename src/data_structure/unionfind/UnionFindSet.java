package data_structure.unionfind;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @param <E>
 *
 * @author  Hanyuw
 * @since   2.1
 * @date    12/31/2018
 */
public class UnionFindSet<E>
        extends AbstractSet<E>
        implements Set<E> {

    private Map<E, E> root;
    private Map<E, Integer> neighborsSize;

    public UnionFindSet() {
        this.root = new HashMap<>();
        this.neighborsSize = new HashMap<>();
    }

    /*
     * Union Find Set exclusive behaviors
     */

    /**
     * Union two sets that contains two given elements,
     * by setting the root of one element with less neighbors size pointing to another
     *
     * @param e1 first element
     * @param e2 second element
     * @return whether two elements are already unioned,
     * false if yes.
     * true if not and union two sets and thereby union two sets
     */
    public boolean union(E e1, E e2) {
        if (this.isUnioned(e1, e2)) {
            return false;
        }

        if (!this.contains(e1) || !this.contains(e2)) {
            return false;
        }

        E root1 = find(e1), root2 = find(e2);
        if (this.neighborsSize.get(root1) > this.neighborsSize.get(root2)) {
            this.root.put(root2, root1);

            this.neighborsSize.put(root1, this.neighborsSize.get(root1) + this.neighborsSize.get(root2));
            this.neighborsSize.remove(root2);
        } else {
            this.root.put(root1, root2);

            this.neighborsSize.put(root2, this.neighborsSize.get(root1) + this.neighborsSize.get(root2));
            this.neighborsSize.remove(root1);
        }

        return true;
    }

    /**
     * Find the root of the elements,
     * path compressing all its parents on the way
     * by dividing and conquering
     *
     * @param e target element
     * @return root of this ancestor
     */
    private E find(E e) {
        if (this.root.get(e) == e) {
            return e;
        }

        E root = this.find(this.root.get(e));

        this.root.put(e, root);
        return root;
    }


    /**
     * Find whether two elements shares the same root
     *
     * @param e1 first element
     * @param e2 second element
     * @return whether two element is unioned
     */
    public boolean isUnioned(E e1, E e2) {
        if (!this.contains(e1) || !this.contains(e2)) {
            return false;
        }

        return this.find(e1) == this.find(e2);
    }


    /**
     * Count numbers of connected components by the size of the key set of neighborsSize
     *
     * @return count of connected components in the set
     */
    public int getComponents() {
        return this.neighborsSize.size();
    }


    /**
     * Count the numbers of elements that are connecting to the target element,
     * by measuring on the root
     *
     * @param e target element
     * @return numbers of elements that are connecting to e
     */
    public int getNeighborsSize(E e) {
        if (!this.root.containsKey(e)) {
            return 0;
        }

        return this.neighborsSize.get(this.find(e));
    }


    /**
     * Find all elements that are connected to given element
     * in O(n) time
     *
     * @param e target element
     * @return all elements that are connected to given element
     */
    public Set<E> getNeighbors(E e) {
        if (!this.root.containsKey(e)) {
            return new HashSet<>();
        }


//        return this.root.keySet().stream()
//                .filter(n -> this.isUnioned(n, e))
//                .collect(Collectors.toSet());
        Set<E> neighbors = new HashSet<>();
        for (E next : this.root.keySet()) {
            if (this.isUnioned(e, next)) {
                neighbors.add(next);
            }
        }

        return neighbors;
    }


    /**
     * Collect all elements in various set along their connectivity
     * in O(n) time
     *
     * @return all elements along components
     */
    public Set<Set<E>> getElementsAlongComponent() {
        Map<E, Set<E>> elementsAlongComponent = new HashMap<>();

        for (E k : this.neighborsSize.keySet()) {
            elementsAlongComponent.put(k, new HashSet<>());
        }

        for (E v : this.root.keySet()) {
            elementsAlongComponent.get(this.find(v)).add(v);
        }

//        return elementsAlongComponent.values().stream().collect(Collectors.toSet());
        Set<Set<E>> components = new HashSet<>();
        for (Set<E> sets : elementsAlongComponent.values()) {
            components.add(sets);
        }

        return components;
    }



    /*
     * Inherits from Set interface
     */

    @Override
    public int size() {
        return this.root.size();
    }

    @Override
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.root.containsKey(o);
    }

    @Override
    public Iterator iterator() {
        return this.root.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return this.root.keySet().toArray();
    }

    @Override
    public boolean add(Object o) {
        if (this.contains(o)) {
            return false;
        }

        E e = (E) o;
        this.root.put(e, e);
        this.neighborsSize.put(e, 1);

        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Remove operation is not supported by Union Find Set");
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            E e = (E) o;
            if (this.contains(e)) {
                return false;
            }
        }

        for (Object o : c) {
            this.add(o);
        }

        return true;
    }

    @Override
    public void clear() {
        this.root = new HashMap<>();
        this.neighborsSize = new HashMap<>();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Remove operation is not supported by Union Find Set");
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Remove operation is not supported by Union Find Set");
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return a;
    }

    @Override
    public String toString() {
        return this.root.keySet().toString();
    }
}
