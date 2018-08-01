import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int n;
	
	private class Node {
		Item item;
		Node next;
		Node befo;
	}
	
	public Deque() {
		first = null;  
        last  = null;  
        n = 0;
	}                           // construct an empty deque
	
	public boolean isEmpty() {
		return n == 0;
	}                           // is the deque empty?
	
	public int size() {
		return n;
	}                           // return the number of items on the deque
	
	public void addFirst(Item item) {
		if (item == null) throw new java.lang.NullPointerException();  
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.befo = null;
		first.next = null;
		if (isEmpty()) last = first;
		else {
			oldfirst.befo = first;
			first.next = oldfirst;
		}
		n++;
	}                           // add the item to the front
	
	public void addLast(Item item) {
		if (item == null) throw new java.lang.NullPointerException();  
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.befo = null;
		if (isEmpty()) first = last;
		else {
			oldlast.next = last;
			last.befo = oldlast;
		}
		n++;
	}                           // add the item to the end
	
	public Item removeFirst() {
	    if (isEmpty()) {  
            throw new java.util.NoSuchElementException("Queue underflow");  
        } 
		Item item = first.item;
		n--;
		if (isEmpty()) {
			first = null;
			last = null;
		}
		else {
			first = first.next;
			first.befo = null;
		}
		return item;
	}                           // remove and return the item from the front
	
	public Item removeLast() {
	    if (isEmpty()) {  
            throw new java.util.NoSuchElementException("Queue underflow");  
        } 
		Item item = last.item;
		n--;
		if (isEmpty()) {
			first = null;
			last = null;
		}
		else {
			last = last.befo;
			last.next = null;
		}
		return item;
	}                           // remove and return the item from the end
	
	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}                           // return an iterator over items in order from front to end
	
	private class LinkedListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() { return current != null; }
		public Item next() { 
			if (!hasNext()) throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
        }
		public void remove() {
			throw new java.lang.UnsupportedOperationException(); 
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<String> deque = new Deque<String>();  
	    while (!StdIn.isEmpty()) {  
	        String s = StdIn.readString();  
	        if (!s.equals("-")) {  
	            StdOut.println("1->deque.size()=" +deque.size());  
	            deque.addFirst(s);  
	            StdOut.println("2->deque.size()=" +deque.size());  
	        }  
	        else if (!deque.isEmpty()) {  
	            StdOut.println(deque.removeFirst() + " ");  
	            StdOut.println("3->deque.size()=" +deque.size());  
	        }  
	    }  
	    StdOut.println("(" + deque.size() +" left on the deque)"); 
	}

}
