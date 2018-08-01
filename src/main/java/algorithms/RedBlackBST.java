package algorithms;
// Algotithm3.4 Red Black balanced tree search

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private class Node{
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;
		Node(Key key, Value val, int N, boolean color){
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	public RedBlackBST() {}
	
	//put and get
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) { delete(key); return;}
		root = put(root, key, val);
		root.color = RED;
	}
	private Node put(Node h, Key key, Value val) {
		if (h == null) return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0) h.left = put(h.left, key, val);
		else if (cmp > 0) h.right = put(h.right , key, val);
		else h.val = val;
		
		if (isRed(h.right) && !isRed(h.left))  h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))  h = rotateRight(h);
		if (isRed(h.right) && isRed(h.left))  flipColors(h);
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	} 
	
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else return x.val;
		}
		return null;
	}
	
	// delete part
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");
		if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
	}
	private Node deleteMin(Node h) {
		if (h.left == null) return null;
		if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
		h.left = deleteMin(h.left);
		return balance(h);
	}
	
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");
		if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
		root = deleteMax(root);
		if (!isEmpty()) root.color = BLACK;
	}
	private Node deleteMax(Node h) {
		if (isRed(h.left)) h = rotateRight(h);
		if (h.right == null) return null;
		if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
		h.right = deleteMax(h.right);
		return balance(h);
	}
	
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;
		if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
	}
	private Node delete(Node h, Key key) {
		if (key.compareTo(h.key) < 0) {
			if (isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else {
			if (isRed(h.left)) h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && h.right == null) return null;
			if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right, key);
		}
		return balance(h);
	}
	
	/*************************************************************
	 * Red-Black BST helper functions
	 *************************************************************/
	// rotate and flip	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1+size(h.left)+size(h.right);
		return x;
	}
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1+size(h.left)+size(h.right);
		return x;
	}
	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
		//h.color = RED;
		//h.left.color = BLACK;
		//h.right.color = BLACK;
	}	
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}
	
	// move red and balance
	private Node moveRedLeft(Node h) {
		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}
	private Node moveRedRight(Node h) {
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}
	private Node balance(Node h) {
		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	// API
	public int size() {return size(root);}
	private int size(Node x) {if(x == null) return 0; else return x.N;}
	
	public boolean isEmpty() {return root == null;}
	
	public boolean contains(Key key) { return get(key) != null;}
	
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return min(root).key;
	}
	private Node min(Node x) {
		if (x.left == null) return x;
		else return min(x.left);
	}
	
	public Key max() {
		if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
		return max(root).key;
	}
	private Node max(Node x) {
		if (x.right == null) return x;
		else return max(x.right);
	}

	public Key floor(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to floor() is null");
		if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
		Node x = floor(root, key);
		if (x == null) return null;
		else return x.key;
	}
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t;
		else return x;
	}
	
	public Key ceiling(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
		if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
		Node x = ceiling(root, key);
		if (x == null) return null;
		else return x.key;
	}
	private Node ceiling(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0) return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null) return t;
		else return x;
	}
	
	public Iterable<Key> keys(){
		if (isEmpty()) return new Queue<Key>();
		return keys(min(), max());
	}
	public Iterable<Key> keys(Key lo, Key hi){
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0) keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if (cmphi > 0) keys(x.right, queue, lo, hi);
	}
	
	public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
        for (int i = 0; i<11; i++) {
            String key = a[i];
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        st.delete("S");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
