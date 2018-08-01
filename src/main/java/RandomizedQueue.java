import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] a; // generic array creation is disallowed in Java
	private int n = 0;
	
	public RandomizedQueue() {
		a = (Item[]) new Object[1];
	}                 // construct an empty randomized queue
	
	public boolean isEmpty() {
		return n == 0;
	}                 // is the randomized queue empty?
	
	public int size() {
		return n;
	}                 // return the number of items on the randomized queue
	
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < n; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void enqueue(Item item) {
		if (item == null) throw new java.lang.NullPointerException();
		if (n == a.length) resize(2*a.length);
		a[n++] = item;
	}                 // add the item at the first
	
	public Item dequeue() {
		int r = StdRandom.uniform(n);
		Item item = a[r];
		a[r] = a[--n];                    // N = N-1, then, set item = a[N];
		a[n] = null;                           // avoid loitering
		if (n > 0 && n == a.length/4) resize(a.length/2);
		return item;
	}                 // remove and return a random item
	
	public Item sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int r = StdRandom.uniform(n);
		Item item = a[r];
		return item;
	}                 // return a random item (but do not remove it)
	
	public Iterator<Item> iterator() {
		return new ResizingArrayIterator();
	}                 // return an independent iterator over items in random order
	
	private class ResizingArrayIterator implements Iterator<Item> {
		private int i = 0;
		private final Item[] iter = (Item[]) new Object[n];
		public ResizingArrayIterator() {
			for (int i = 0; i < n; i++)  
		        iter[i] = a[i];  
		        StdRandom.shuffle(iter); 
		}
		public boolean hasNext() {
			return i < n;
		}
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();  
	        Item item = iter[i++];  
	        return item;
		}
		public void remove() { throw new java.lang.UnsupportedOperationException(); }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
