package com.iffi;

/**
 * Utility class that instantiates list nodes for our list data structure
 * 
 * @author tre-vawnrainey, ethanwood
 *
 * @param <T>
 */
public class ListNode<T> {
	private ListNode<T> next;
	private final T item;

	public ListNode(T item) {
		this.item = item;
		this.next = null;
	}

	public T getItem() {
		return item;
	}

	public ListNode<T> getNext() {
		return next;
	}
	
	public boolean hasNext() {
		return next != null;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
		return;
	}
}
