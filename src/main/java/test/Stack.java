package test;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
	
	private Node First;
	private int N;
	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty() { return N == 0;}
	
	public int size() { return N;}
	
	public void push(Item item) {
		Node oldfirst = First;
		First = new Node();
		First.item = item;
		First.next = oldfirst;
		N++;		
	}
	
	public Item pop() {
		Item item = First.item;
		First = First.next;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() { return new StackIterator();}
	
	private class StackIterator implements Iterator<Item> {
		private Node current = First;
		public boolean hasNext() { return current != null;}
		public void remove() {}
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}
