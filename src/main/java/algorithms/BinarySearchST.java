package algorithms;
//Algorithm3.2 Binary Search (in an ordered array)

import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	
	private static final int INIT_CAPACITY = 2;
	private Key[] keys;
	private Value[] vals;
	private int N = 0;
	
	public BinarySearchST() {
		this(INIT_CAPACITY);
	}
	
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
	private void resize(int capacity) {
		assert capacity >= N;
		Key[]   tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for(int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
	}
	
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		if (isEmpty()) return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	}
	
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {delete(key); return;}
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val; return;
		}
		if (N == keys.length) resize(2*keys.length);
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public int rank(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to rank() is null");
		int lo = 0, hi = N-1;
		while(lo<=hi) {
			int mid = lo + (hi-lo)/2;
			int cmp = key.compareTo(keys[mid]);
			if     (cmp < 0) hi = mid - 1;
			else if(cmp > 0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}
	
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (isEmpty()) return;
		int i = rank(key);
		if (i == N || keys[i].compareTo(key) != 0) return;
		for (int j = i; j < N-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null;
        vals[N] = null;
        if (N > 0 && N == keys.length/4) resize(keys.length/2);
	}
	
	public boolean contains(Key key) {return get(key) != null;}
	public int size() {return N;}
	public boolean isEmpty() {return N==0;}
	public Key min() {return keys[0];}
	public Key max() {return keys[N-1];}
	public Key select(int k) {return keys[k];}
	public Key ceiling(Key key) {if (rank(key)!=N) return keys[rank(key)];else return null;}
	public Key floor(Key key) {
		int i = rank(key);
		if (i < N && key.compareTo(keys[i])==0) return keys[i];
		if (i == 0) return null;
		else return keys[i-1];
		}
	
	public Iterable<Key> keys(){return keys(min(),max());}
	
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> q = new Queue<Key>();
		for (int i = rank(lo); i < rank(hi); i++) q.enqueue(keys[i]);
		if (contains(hi)) q.enqueue(keys[rank(hi)]);
		return q;
	}
	
	public static void main(String[] args) { 
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        for (int i = 0; i<11; i++) {
            String key = a[i];
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
