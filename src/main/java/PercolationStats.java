import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double staT[];
	private double mean;
	private double std;
	private int N;
	
	public PercolationStats(int N, int T)  {
		this.staT = new double[T];
		this.N = N;
		int t = 0;
		if(N<=0) throw new IllegalArgumentException();  
	    if(T<=0) throw new IllegalArgumentException(); 
	    while (t < T) {
	    	    Percolation pe = new Percolation(N);
	    	    int count = 0;
	    	    while (true) {
	    	    	    count++;
	    	    	    while (true) {
	    	    	    	    int x = StdRandom.uniform(N) + 1;
	    	    	    	    int y = StdRandom.uniform(N) + 1;
	    	    	    	    if (pe.isOpen(x, y)) continue;
	    	    	    	    else { pe.open(x, y); break;}
	    	    	    }
	    	    	    if (pe.percolates()) {
	    	    	    	    staT[t] = (double) count/N*N;
	    	    	    	    break;
	    	    	    }
	    	    }
	    	    t++;
	    }
		this.mean = StdStats.mean(staT);
		this.std = StdStats.stddev(staT);
	}   // perform T independent experiments on an N-by-N grid 
	
    public double mean() {
    	    return this.mean;
    }                      // sample mean of percolation threshold  
    
	public double stddev() {
		return this.std;
	}                    // sample standard deviation of percolation threshold  
	
	public double confidenceLo() {
		return this.mean-1.96*this.std/Math.sqrt(N); 
	}                    // low  endpoint of 95% confidence interval  
	
	public double confidenceHi() {
		return this.mean+1.96*this.std/Math.sqrt(N); 
	}                    // high endpoint of 95% confidence interval  
	
	public static void main(String[] args) {
		int N = StdIn.readInt();    
        int T = StdIn.readInt();    
        PercolationStats percolationStats = new PercolationStats(N, T);    
        StdOut.println("mean = " + percolationStats.mean());    
        StdOut.println("stddev = " + percolationStats.stddev());    
        StdOut.println("95% confidence interval " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi()); 
	}                    // test client (described below)
	
}
