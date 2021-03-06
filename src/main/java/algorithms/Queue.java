package algorithms;
//Algorithm1.3    FIFO queue

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() { return first == null; }
	public int size() { return N; }
	
	public void enqueue(Item item) {            // add item at the end
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}
	
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		if (isEmpty()) first = last;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator(){return new QueueIterator();}
	
	private class QueueIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() { return current != null;}
		public void remove() {}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}
