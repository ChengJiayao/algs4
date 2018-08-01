package algorithms;
//Algorithm2.7 Heapsort

import edu.princeton.cs.algs4.StdOut;

public class Heap {
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int k = N/2; k >= 1; k--) {
			sink(a, k, N);
		}
		while (N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}
	
	private static void sink(Comparable[] a, int k, int N) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(a[j-1], a[j])) j++;
			if (!less(a[k-1], a[j-1])) break;
			exch(a, k, j);
			k = j;
		}
	}
	
	private static void exch(Comparable[] a, int i, int j) {
		Comparable v = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = v;
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	
	private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
		sort(a);
        assert isSorted(a);
        show(a);
	}

}
