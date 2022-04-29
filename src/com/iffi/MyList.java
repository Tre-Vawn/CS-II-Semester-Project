package com.iffi;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Class that instantiates and provides utility for our list data structure
 * 
 * @author tre-vawnrainey, ethanwood
 *
 * @param <T>
 */
public class MyList<T> implements Iterable<T>{

	private ListNode<T> head;
	private Comparator<T> cmp;
	private int size;

	public MyList(Comparator<T> cmp) {
		this.head = null;
		this.cmp = cmp;
		this.size = 0;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public void removeHead() {
		this.head = this.head.getNext();
		return;
	}

	public int getSize() {
		return this.size;
	}

	public void clearList() {
		this.head = null;
		this.size = 0;
		return;
	}

	/**
	 * This function retrieves a node and its associated information given its index
	 * 
	 * @param index
	 * @return
	 */
	public ListNode<T> getListNode(int index) {
		ListNode<T> current = this.head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}
	
	public void addToHead(T a) {
		ListNode<T> newHead = new ListNode<T>(a);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
		return;
	}

	/**
	 * This function adds an account to the list based upon the comparator chosen
	 * for the list
	 * 
	 * @param a
	 */
	public void addToList(T a) {
		if (this.isEmpty()) {
			this.addToHead(a);
			return;
		}
		ListNode<T> current = this.head;
		ListNode<T> insert = new ListNode<T>(a);
		if (current.getNext() == null) {
			if(cmp.compare(current.getItem(), a) <= 0) {
				current.setNext(insert);
			} else {
				insert.setNext(current);
				this.head = insert;
			}
			this.size++;
			return;
		}
		

		for (int i = 0; i < size - 1; i++) {
			if (cmp.compare(current.getItem(), a) <= 0 && cmp.compare(current.getNext().getItem(), a) >= 0) {
				insert.setNext(current.getNext());
				current.setNext(insert);
				this.size++;
				return;
			}
			current = current.getNext();
		}
		
		current.setNext(insert);
		return;
	}

	/**
	 * This function removes the node and its associated information from the list
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index > this.size - 1) {
			throw new RuntimeException("index out of bounds: " + index);
		}
		if (index == 0) {
			removeHead();
			this.size--;
			return;
		}

		ListNode<T> prev = getListNode(index - 1);
		ListNode<T> curr = prev.getNext();

		prev.setNext(curr.getNext());
		this.size--;
		return;
	}

	@Override
	public Iterator<T> iterator() {
	     Iterator<T> it = new Iterator<T>() {

	            private ListNode<T> current = head;

	            @Override
	            public boolean hasNext() {
	                return current.hasNext();
	            }

	            @Override
	            public T next() {
	            	T item = this.current.getItem();
	            	current = current.getNext();
	            	return item;
	            }
	        };
	        return it;
	}

}