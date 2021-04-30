package com.mgg;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SaleList<T> {
	private SaleListNode<T> head;
	private int size;
	private Comparator<T> cmp;

//	public SaleList() {
//		this.head = null;
//		this.size = 0;
//	}

	public SaleListNode<T> getHead() {
		return this.head;
	}

	public SaleList(Comparator<T> cmp) {
		this.head = null;
		this.size = 0;
		this.cmp = cmp;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public int getSize() {
		return this.size;
	}

	public void clear() {
		this.size = 0;
	}

//	public static SaleList<Sale> uploadToNewList(List<Sale> saleList) {
//		SaleList<Sale> salesLinked = new SaleList<>();
//		for (Sale sale : saleList) {
//
//			salesLinked.add(sale);
//		}
//		return salesLinked;
//	}

	public void add(T s) {
		SaleListNode<T> current = this.head;
		if (current==null) {
			addToStart(s);
		} else {
			for (int i = 0; i < size; i++) {
				if (cmp.compare(s, current.getItem()) <= 0) {
					addSaleAtIndex(s, i);
				} else if (cmp.compare(s, current.getItem()) > 0) {
					current = current.getNext();
				} 
			}
			size++;
		}

	}

	// comapareTo
//	public void addByCoustomer(Sale s) {
//		SaleListNode current = this.head;
//		int compare = 0;
//		if (s.equals(null)) {
//			System.out.println("no sales");
//		} else {
//			current.
////			compare = current.getSale().getCustomer().getFirstName().compareTo(current.)
//			
//		}
//		this.size++;
//	}

	private void addSaleAtIndex(T s, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Error: Out of Bounds");
		}
		if (index == 0) {
			this.addToStart(s);
		} else {
			SaleListNode<T> previous = this.getSaleListNode(index - 1);
			SaleListNode<T> current = previous.getNext();
			SaleListNode<T> newNode = new SaleListNode<>(s);
			newNode.setNext(current);
			previous.setNext(newNode);
			this.size++;
			return;
		}
	}

	public void addToStart(T s) {
		SaleListNode<T> newHead = new SaleListNode<>(s);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
	}

//	public void addToEnd(T s) {
//		this.addSaleAtIndex(s, this.size);
//	}

	public void remove(int position) {
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException("Error: Out of Bounds");
		}
		SaleListNode<T> temp = head;
		if (position == 0) {
			head = temp.getNext();
			this.size--;
			return;
		}
		for (int i = 0; temp != null && i < position - 1; i++) {
			temp = temp.getNext();
		}
		SaleListNode<T> next = temp.getNext().getNext();
		temp.setNext(next);
		this.size--;
	}

	private SaleListNode<T> getSaleListNode(int position) {
		SaleListNode<T> current = head;
		for (int i = 0; i < position; i++) {
			current = current.getNext();
		}
		return current;
	}

	public T getSale(int position) {
		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException("Error: Out of Bounds");
		}
		SaleListNode<T> current = this.getSaleListNode(position);
		return current.getItem();
	}

}
