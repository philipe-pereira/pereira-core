package br.com.pereiraeng.core.collections.set;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Classe dos objetos que representam um conjunto em que todas as formas de se
 * inserir um elemento são grampeadas pela função {@link #add(Object)}.
 * 
 * @author Philipe PEREIRA
 *
 * @param <E> the type of elements held in this collection
 */
public abstract class FixPosSet<E> extends LinkedHashSet<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (direct)
			return super.addAll(c);
		else {
			boolean out = false;
			for (E e : c)
				out |= this.add(e);
			return out;
		}
	}

	private transient boolean direct = false;

	protected boolean directAddAll(Collection<? extends E> c) {
		this.direct = true;
		boolean out = super.addAll(c);
		this.direct = false;
		return out;
	}
}
