package com.iffi;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Class that instantiates and provides utility for our list data structure.
 * 
 * @author tre-vawnrainey, ethanwood
 *
 * @param <T>
 */
public final class MyList<T> implements Iterable<T> {

	private ListNode<T> head;
	private final Comparator<T> cmp;
	private int size;

	protected MyList(Comparator<T> cmp) {
		this.head = null;
		this.cmp = cmp;
		this.size = 0;
	}

	private final boolean isEmpty() {
		return (this.size == 0);
	}

	private final void removeHead() {
		this.head = this.head.getNext();
		return;
	}

	protected final int getSize() {
		return this.size;
	}

	protected final void clearList() {
		this.head = null;
		this.size = 0;
		return;
	}

	/**
	 * This function retrieves a node and its associated information given its
	 * index.
	 * 
	 * @param index
	 * @return
	 */
	private final ListNode<T> getListNode(int index) {
		ListNode<T> current = this.head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}

	/**
	 * This function adds an object a to the head of the list.
	 * 
	 * @param a
	 */
	private final void addToHead(T a) {
		ListNode<T> newHead = new ListNode<T>(a);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
		return;
	}

	/**
	 * This function adds an account to the list based upon the comparator chosen
	 * for the list.
	 * 
	 * @param a
	 */
	public final void addToList(T a) {
		if (this.isEmpty()) {
			this.addToHead(a);
			return;
		}
		ListNode<T> current = this.head;
		ListNode<T> insert = new ListNode<T>(a);

		if (cmp.compare(current.getItem(), a) >= 0) {
			this.addToHead(a);
			return;
		}

		for (int i = 0; i < size - 1; i++) {
			if (cmp.compare(current.getItem(), a) <= 0) {
				if (cmp.compare(current.getNext().getItem(), a) >= 0) {
					insert.setNext(current.getNext());
					current.setNext(insert);
					this.size++;
					return;
				}
			}
			current = current.getNext();
		}

		current.setNext(insert);
		this.size++;
		return;
	}

	/**
	 * This function removes the node and its associated information from the list.
	 * 
	 * @param index
	 */
	protected final void remove(int index) {
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

	/**
	 * This function allows the list to connect to the iterable interface.
	 * 
	 */
	public final Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {

			private ListNode<T> current = head;

			public boolean hasNext() {
				return this.current != null;
			}

			public T next() {
				T item = this.current.getItem();
				current = this.current.getNext();
				return item;
			}
		};
		return it;
	}
}