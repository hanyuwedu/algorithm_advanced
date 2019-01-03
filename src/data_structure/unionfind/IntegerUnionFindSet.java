package data_structure.unionfind;

/**
 * 1/2
 *
 * Simple Union find set open to integer
 */
public class IntegerUnionFindSet {
    public int[] root;
    public int[] component;
    public int size;

    public IntegerUnionFindSet(int size) {
        this.root = new int[size];
        this.component = new int[size];
        this.size = size;

        for (int i = 0; i <= size - 1; i++) {
            this.root[i] = i;
            this.component[i] = 1;
        }
    }

    public void union(int x, int y) {
        if (x >= root.length || x < 0 || y >= root.length || y < 0) {
            return;
        }

        int x_root = find(x);
        int y_root = find(y);

        if (x_root != y_root) {
            this.component[x_root] += this.component[y_root];
            this.root[y_root] = x_root;
            this.size--;
        }
    }

    public boolean isUnioned(int x, int y) {
        return find(x) == find(y);
    }

    private int find(int x) {
        if (root[x] == x) {
            return x;
        }

        int root = find(this.root[x]);

        this.root[x] = root;
        return root;
    }

    public int getSize() {
        return this.size;
    }

    public int getComponents(int x) {
        return this.component[find(x)];
    }
}
