package br.com.pereiraeng.core.collections.list;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Classe dos objetos que representam uma lista em que todas as formas de se
 * inserir um elemento são grampeadas pela função {@link #add(Object)}.
 * 
 * @author Philipe PEREIRA
 *
 * @param <E> the type of elements held in this collection
 */
public abstract class FixPosList<E> extends LinkedList<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public void addFirst(E element) {
		this.add(element);
	}

	@Override
	public void addLast(E element) {
		this.add(element);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (direct)
			return super.addAll(index, c);
		else
			return addAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean out = false;
		for (E e : c)
			out |= this.add(e);
		return out;
	}

	@Override
	public E set(int index, E element) {
		return null;
	}

	private transient boolean direct = false;

	protected boolean directAddAll(Collection<? extends E> c) {
		this.direct = true;
		boolean out = super.addAll(c);
		this.direct = false;
		return out;
	}
}
