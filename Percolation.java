import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private  int n;
    private boolean[] openSites;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;
    private int openSitesCount;

    // creates n-by-n site, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        int size = n * n + 2;
        openSites = new boolean[size];
        virtualTop = 0;
        openSites[virtualTop] = true;
        virtualBottom = size - 1;
        openSites[virtualBottom] = true;
        uf = new WeightedQuickUnionUF(size);
        openSitesCount = 0;
        for (int i = 1; i <= n; i++) {
            uf.union(virtualTop, i);
        }
        for (int j = (n * (n -1)); j <= (size - 1); j++) {
            uf.union(j, virtualBottom);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = n * (row - 1) + (col - 1) + 1;
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        } else {
            if (!isOpen(row, col)) {
                openSites[index] = true;
                openSitesCount++;
                if (row > 1 && isOpen(row - 1, col)) {
                    uf.union(index, index - n);
                }
                if (row < n && isOpen(row + 1, col)) {
                    uf.union(index, index + n);
                }
                if (col > 1 && isOpen(row, col - 1)) {
                    uf.union(index, index - 1);
                }
                if (col < n && isOpen(row, col + 1)) {
                    uf.union(index, index + 1);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        } else {
            return openSites[n * (row - 1) + (col - 1) + 1];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;
        return uf.find(n * (row - 1) + (col - 1) + 1) == uf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(20); // 5x5 grid
        percolation.open(1, 3);
        System.out.println("Is Open: " +percolation.isOpen(1, 1));
        System.out.println("Is Full: " +percolation.isFull(1, 1));
        percolation.open(2, 3);
        percolation.open(3, 3);
        System.out.println("Does the System Percolate: " + percolation.percolates());
         // Should print 3
    }
}