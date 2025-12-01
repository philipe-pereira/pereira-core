package br.com.pereiraeng.core.collections.list;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Classe do objeto que representa uma lista circular, onde a posição de cada
 * elemento não importa, somente a sua distância relativa aos demais elementos
 * 
 * @author Philipe PEREIRA
 *
 * @param <T> classe dos objetos que ela contem
 */
public class CircularQueue<T> extends AbstractCollection<T> implements Queue<T>, Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The first element in the list.
	 */
	private transient Entry<T> first;

	/**
	 * The last element in the list.
	 */
	private transient Entry<T> last;

	/**
	 * The current length of the list.
	 */
	private transient int size = 0;

	/**
	 * Class to represent an entry in the list. Holds a single element.
	 */
	private static final class Entry<T> {
		/** The element in the list. */
		T data;

		/** The next list entry, null if this is last. */
		Entry<T> next;

		/** The previous list entry, null if this is first. */
		Entry<T> previous;

		/**
		 * Construct an entry.
		 * 
		 * @param data the list element
		 */
		private Entry(T data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return previous.data + ">" + data + "<" + next.data;
		}
	} // class Entry

	/**
	 * Remove an entry from the list. This will adjust size and deal with `first'
	 * and `last' appropriatly.
	 *
	 * @param e the entry to remove
	 */
	private void removeEntry(Entry<T> e) {
		size--;
		if (size == 0)
			first = last = null;
		else {
			if (e == first)
				first = e.next;
			else if (e == last)
				last = e.previous;
			e.next.previous = e.previous;
			e.previous.next = e.next;
		}
	}

	/**
	 * Inserts an element at the end of the list.
	 *
	 * @param e the entry to add
	 */
	private void addLastEntry(Entry<T> e) {
		if (size == 0)
			first = last = e;
		else {
			e.next = first;
			e.previous = last;
			last.next = e;
			first.previous = e;
			last = e;
		}
		size++;
	}

	@Override
	public T element() {
		if (size == 0)
			throw new NoSuchElementException();
		return last.data;
	}

	@Override
	public boolean add(T o) {
		return offer(o);
	}

	@Override
	public boolean offer(T o) {
		addLastEntry(new Entry<>(o));
		return true;
	}

	@Override
	public T peek() {
		if (size == 0)
			return null;
		return last.data;
	}

	@Override
	public T poll() {
		T t = last.data;
		removeEntry(last);
		return t;
	}

	@Override
	public T remove() {
		return poll();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Entry<T> next = first;

			@Override
			public boolean hasNext() {
				if (next == null)
					return false;
				else {
					if (next == last)
						next = null;
					return true;
				}
			}

			@Override
			public T next() {
				T t = null;
				if (next != null) {
					t = next.data;
					next = next.next;
				} else
					t = last.data;
				return t;
			}

			@Override
			public void remove() {
				if (next == null) {
					last = first.previous.previous;
					next = first;
				} else if (next.previous == first)
					first = next;

				next.previous.previous.next = next;
				next.previous = next.previous.previous;
			}
		};
	}

	/**
	 * Returns an iterator over the elements in this collection. There are no
	 * guarantees concerning the order in which the elements are returned (unless
	 * this collection is an instance of some class that provides a guarantee).
	 * 
	 * @param start posição inicial, com relação ao elemento tido como {@link #first
	 *              inicial}
	 * @return iterator
	 */
	public Iterator<T> infiniteIterator(int start) {
		return new InfiniteIterator(start);
	}

	private class InfiniteIterator implements Iterator<T> {
		private Entry<T> next;

		public InfiniteIterator(int start) {
			next = first;
			while (--start >= 0)
				next = next.next;
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public T next() {
			T t = next.data;
			next = next.next;
			return t;
		}

		@Override
		public void remove() {
			if (next == null) {
				last = first.previous.previous;
				next = first;
			} else if (next.previous == first)
				first = next;

			next.previous.previous.next = next;
			next.previous = next.previous.previous;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof CircularQueue))
			return false;
		int size = size();
		if (size != ((CircularQueue<?>) o).size())
			return false;

		CircularQueue<?> cq = (CircularQueue<?>) o;

		T root1 = this.first.data;
		Iterator<?> it = cq.iterator();
		int j = -1;
		loop: while (it.hasNext()) {
			j++;
			Object root2 = it.next();

			if (equals(root1, root2)) {
				Iterator<T> itt = iterator();
				Iterator<?> iit = cq.infiniteIterator(j);
				for (int i = 0; i < size; i++)
					if (!equals(itt.next(), iit.next()))
						continue loop;
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		Iterator<T> itr = iterator();
		int pos = size();
		while (--pos >= 0)
			hashCode = 31 * hashCode + hashCode(itr.next());
		return hashCode;
	}

	private static final boolean equals(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	private static final int hashCode(Object o) {
		return o == null ? 0 : o.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("[");
		Iterator<T> it = this.iterator();
		if (!it.hasNext())
			return out.append("]").toString();
		out.append(it.next());
		while (it.hasNext())
			out.append(", " + it.next());
		out.append("]");
		return out.toString();
	}
}