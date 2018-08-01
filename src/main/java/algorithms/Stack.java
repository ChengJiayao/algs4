package algorithms;
//Algorithm1.2    Pushdown stack (linked-list implementation)

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
	
	private Node first;            // top of stack (most recently added node)
	private int N;                 // number of items
	
	private class Node {           // nested class to define nodes
		Item item;
		Node next;
	}
	
	public boolean isEmpty() { return first == null; }
	public int size() { return N; }
	
	public void push(Item item) {  // add item to top of stack
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop() {
		Item item = first.item;   // remove item from top of stack
		first = first.next;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator(){return new StackIterator();}
	private class StackIterator implements Iterator<Item>{
		private Node current = first;
        public boolean hasNext() {  return current != null;  }
        public void remove() { }
        public Item next() {
          	Item item = current.item;
         	current = current.next;
         	return item;
        }
	}

}
