package br.com.pereiraeng.core.collections.sortedlist;

import java.util.Collections;

import br.com.pereiraeng.core.collections.list.FixPosList;


/**
 * Classe dos objetos que representam uma lista auto-ordenável, onde os
 * elementos, ao serem inseridos, vão para uma dada posição determinada
 * 
 * @author Philipe Pereira
 *
 * @param <E> the type of elements held in this collection
 */
public class NaturalSortedList<E extends Comparable<? super E>> extends FixPosList<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public void add(int index, E element) {
		this.add(element);
	}

	@Override
	public boolean add(E element) {
		int pos = Collections.binarySearch(this, element);
		super.add(pos >= 0 ? pos : (-pos - 1), element);
		return true;
	}
}
