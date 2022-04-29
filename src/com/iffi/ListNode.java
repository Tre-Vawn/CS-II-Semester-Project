package com.iffi;

/**
 * Utility class that instantiates list nodes for our list data structure.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 * @param <T>
 */
public final class ListNode<T> {
	private ListNode<T> next;
	private final T item;

	protected ListNode(T item) {
		this.item = item;
		this.next = null;
	}

	protected final T getItem() {
		return this.item;
	}

	protected final ListNode<T> getNext() {
		return this.next;
	}

	protected final boolean hasNext() {
		return this.next != null;
	}

	/**
	 * This function sets the next node in the list.
	 * 
	 * @param next
	 */
	protected final void setNext(ListNode<T> next) {
		this.next = next;
		return;
	}
}
