import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private int[][] site;

    // creates n-by-n site, with all sites initially blocked
    public Percolation(int n) {
        site = new int[n][n];
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        } else {
            int value = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    site[i][j] = value;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(site[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        } else {
            if (isOpen(row, col)) {
                System.out.println("Site is already open");
            } else {
                site[row][col] = 1;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        } else {
            return site[row][col] == 1;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(20);

        System.out.println(p);
    }
}
