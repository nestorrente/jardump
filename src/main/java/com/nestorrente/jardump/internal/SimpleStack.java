package com.nestorrente.jardump.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

public class SimpleStack<E> {

	private final List<E> elements;

	public SimpleStack() {
		this.elements = new ArrayList<>();
	}

	public SimpleStack(Collection<? extends E> collection) {
		this.elements = new ArrayList<>(collection);
	}

	public SimpleStack(SimpleStack<? extends E> stack) {
		this.elements = new ArrayList<>(stack.elements);
	}

	public SimpleStack(int initialCapacity) {
		this.elements = new ArrayList<>(initialCapacity);
	}

	public void push(E element) {
		this.elements.add(element);
	}

	public int size() {
		return this.elements.size();
	}

	public E pop() {

		if(this.elements.isEmpty()) {
			throw new EmptyStackException();
		}

		return this.elements.remove(this.elements.size() - 1);

	}

	public E peek() {

		if(this.elements.isEmpty()) {
			throw new EmptyStackException();
		}

		return this.elements.get(this.elements.size() - 1);

	}

	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

}
