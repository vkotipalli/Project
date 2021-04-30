package com.mgg;

public class SaleListNode<T> {
	private SaleListNode<T> next;
	private T item;

	public SaleListNode(T item) {
	        this.item = item;
	        this.next = null;
	    }

	public T getItem() {
		return item;
	}

	public SaleListNode<T> getNext() {
		return next;
	}

	public void setNext(SaleListNode<T> next) {
		this.next = next;
	}
}
