import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private double[] thresholds;
    private int trials;
    private int n;

    // Perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }

        this.n = n;
        this.trials = trials;
        this.thresholds = new double[trials];

        // Monte Carlo Simulation
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openSites = 0;

            // Open random sites until the system percolates
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1); 
                int col = StdRandom.uniformInt(1, n + 1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSites++;
                }
            }

            // Store percolation threshold
            thresholds[i] = (double) openSites / (n * n);
        }
    }

    // Sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // Sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // Low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    // High endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    // Test client
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java PercolationStats <n> <trials>");
        }
        
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
