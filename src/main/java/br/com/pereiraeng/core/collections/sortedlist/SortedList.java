package br.com.pereiraeng.core.collections.sortedlist;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import br.com.pereiraeng.core.collections.list.FixPosList;

/**
 * Classe dos objetos que representam uma lista auto-ordenável, onde os
 * elementos, ao serem inseridos, vão para uma dada posição determinada
 * 
 * @author Philipe Pereira
 *
 * @param <E> the type of elements held in this collection
 */
public class SortedList<E> extends FixPosList<E> {
	private static final long serialVersionUID = 1L;

	private final Comparator<? super E> comparator;

	public SortedList(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void add(int index, E element) {
		this.add(element);
	}

	@Override
	public boolean add(E element) {
		int pos = Collections.binarySearch(this, element, comparator);
		super.add(pos >= 0 ? pos : (-pos - 1), element);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean out = super.directAddAll(c);
		Collections.sort(this, this.comparator);
		return out;
	}
}
