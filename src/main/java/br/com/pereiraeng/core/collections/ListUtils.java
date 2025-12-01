package br.com.pereiraeng.core.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Collections utils (list and set)
 * 
 * @author Philipe PEREIRA
 *
 */
public class ListUtils {

	// LISTS

	public static List<Object> getColumn(List<Object[]> matrix, int column) {
		List<Object> out = new ArrayList<>(matrix.size());
		for (Object[] objs : matrix)
			out.add(objs[column]);
		return out;
	}

	public static List<List<Object>> matriz2listOfList(Object[][] objects) {
		ArrayList<List<Object>> lists = new ArrayList<>();

		for (int i = 0; i < objects.length; i++)
			lists.add(Arrays.asList(objects[i]));

		return lists;
	}

	public static Object[][] list2ObjectMatrix(List<List<Object>> list) {
		Object[][] out = new Object[list.size()][];
		for (int i = 0; i < out.length; i++)
			out[i] = list.get(i).toArray(new Object[list.get(i).size()]);
		return out;
	}

	public static int[] toIntArray(Collection<? extends Number> collection) {
		int[] out = new int[collection.size()];
		int j = 0;
		for (Number n : collection)
			out[j++] = n.intValue();
		return out;
	}

	public static double[] toDoubleArray(Collection<? extends Number> collection) {
		double[] out = new double[collection.size()];
		int j = 0;
		for (Number n : collection)
			out[j++] = n.doubleValue();
		return out;
	}

	public static <K> List<K> intervaleList(List<K> l1, List<K> l2) {
		List<K> out = new LinkedList<>();
		intervaleList(out, new LinkedList<>(l1), new LinkedList<>(l2));
		return out;
	}

	public static <K> void intervaleList(List<K> out, List<K> l1, List<K> l2) {
		K k1 = null, k2 = null;
		// pegar o primeiro elemento, caso houver (se não houver, adiciona o
		// resto da outra lista na saída e encerra a função
		if (l1.size() > 0)
			k1 = l1.get(0);
		else {
			out.addAll(l2);
			return;
		}
		if (l2.size() > 0)
			k2 = l2.get(0);
		else {
			out.addAll(l1);
			return;
		}
		if (k1.equals(k2)) {
			// se achou-se um elemento presente em ambas listas, remove-se este
			// elemento
			l1.remove(0);
			l2.remove(0);
			out.add(k1);
			intervaleList(out, l1, l2);
		} else {
			// se o elemento é diferente, ver qual deles arruma um parceiro mais
			// rápido...
			int p1 = pair(l1, k2);
			int p2 = pair(l2, k1);
			if (p1 > p2) // se o elemento de 1 está mais longe de um parceiro...
				out.add(l2.remove(0)); // ... escolhe o de 2
			else
				out.add(l1.remove(0)); // e vice-versa
			intervaleList(out, l1, l2);
		}
	}

	public static <K> int pair(List<K> a, K b) {
		int out = 0;
		for (K k : a) {
			if (k.equals(b))
				return out;
			else
				out++;
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * Função que adiciona um elemento numa lista depois de um dado elemento
	 * 
	 * @param list     lista
	 * @param obj      elemento a ser inserido
	 * @param previous elemento que precederá o elemento a ser inserido
	 */
	public static <K> void addAfter(List<K> list, K obj, K previous) {
		int p = list.indexOf(previous);
		if (p == -1)
			list.add(obj);
		else
			list.add(p + 1, obj);
	}

	// COLLECTION

	public static <E> E getElementAt(Collection<E> c, int index) {
		for (E entry : c)
			if (index-- == 0)
				return entry;
		return null;
	}

	public static <E> E getLast(Collection<E> c) {
		return getElementAt(c, c.size() - 1);
	}

	public static <E> int indexOf(Collection<E> c, E obj) {
		Iterator<E> it = c.iterator();
		int index = 0;
		while (it.hasNext()) {
			E k = it.next();
			if (obj.equals(k))
				return index;
			index++;
		}
		return -1;
	}

	public static <E> E removeElementAt(Collection<E> set, int index) {
		Iterator<E> it = set.iterator();
		while (it.hasNext()) {
			E k = it.next();
			if (index-- == 0) {
				it.remove();
				return k;
			}
		}
		return null;
	}

	/**
	 * Função que faz a filtragem dos objetos de uma coleção, removendo os elementos
	 * que não pertencem ao intervalo limítrofe.
	 * 
	 * @param i   coleção de objetos que será filtrado
	 * @param inf limite inferior do período
	 * @param sup limite superior do período
	 */
	public static <K extends Comparable<K>> void filter(Iterable<K> i, K inf, K sup) {
		Iterator<K> it = i.iterator();
		while (it.hasNext()) {
			K k = it.next();
			if (k.compareTo(inf) < 0 || k.compareTo(sup) > 0)
				it.remove();
		}
	}

	// SET

	public static <E> E setElementAt(Set<E> set, int index, E value) {
		E out = null;
		if (index >= 0) {
			if (index < set.size()) {
				LinkedHashSet<E> temp = new LinkedHashSet<>();

				int i = 0;
				for (E e : set) {
					if (i == index) {
						temp.add(value);
						out = e;
					} else
						temp.add(e);
					i++;
				}

				if (index >= set.size())
					temp.add(value);

				set.clear();
				set.addAll(temp);
				temp.clear();
				temp = null;
			} else
				throw new IndexOutOfBoundsException("index " + index + " must be less than size");
		} else
			throw new IndexOutOfBoundsException("index " + index + " must be greater than zero");
		return out;
	}

	public static <E> void put(LinkedHashSet<E> set, int index, E value) {
		if (index >= 0) {
			LinkedHashSet<E> temp = new LinkedHashSet<>();

			if (index == 0) {
				temp.add(value);
				temp.addAll(set);
			} else {
				int i = 0;
				for (E k : set) {
					if (i == index)
						temp.add(value);
					temp.add(k);
					i++;
				}
			}
			if (index >= set.size())
				temp.add(value);

			set.clear();
			set.addAll(temp);
			temp.clear();
			temp = null;
		} else
			throw new IndexOutOfBoundsException("index " + index + " must be greater than zero");
	}

	/**
	 * Função que retorna os elementos que estão presentes ao mesmo tempo em dois
	 * conjuntos
	 * 
	 * @param <E>  Classe dos objetos do conjunto
	 * @param set1 um conjunto
	 * @param set2 outro conjunto
	 * @return interseção dos conjuntos
	 */
	public static <E> Set<E> intersection(Set<E> set1, Set<E> set2) {
		Set<E> out = new HashSet<>(set1);
		out.retainAll(set2);
		return out;
	}

	/**
	 * Função que adiciona uma lista de elementos no início de um conjunto ordenado
	 * 
	 * @param orderedSet conjunto ordenado
	 * @param es         lista de elementos a serem adicionados
	 */
	@SafeVarargs
	public static <E> void addFirst(LinkedHashSet<E> orderedSet, E... es) {
		List<E> ls = new ArrayList<>(orderedSet);
		orderedSet.clear();
		for (E k : es)
			orderedSet.add(k);
		for (E k : ls)
			orderedSet.add(k);
	}

	// --------------------- FROM/TO STRING ---------------------

	/**
	 * Função inversa de {@link #toList(String)}
	 * 
	 * @param list
	 * @return
	 */
	public static String toString(List<?> list) {
		String out = "[";
		for (Object e : list)
			out += String.format("\"%s\", ", e);
		return out.substring(0, out.length() - 2) + "]";
	}

	/**
	 * Função inversa de {@link #toSet(String)}
	 * 
	 * @param set
	 * @return
	 */
	public static String toString(Set<?> set) {
		String out = "{";
		for (Object e : set)
			out += String.format("%s, ", e);
		return out.substring(0, out.length() - 2) + "}";
	}

	/**
	 * Função inversa de {@link #toString(List)}
	 * 
	 * @param s
	 * @return
	 */
	public static List<String> toList(String s) {
		s = s.substring(2, s.length() - 2);
		return Arrays.asList(s.split("\", \""));
	}

	/**
	 * Função inversa de {@link #toString(Set)}
	 * 
	 * @param s
	 * @return
	 */
	public static Collection<? extends Number> toSet(String s) {
		s = s.substring(1, s.length() - 1);
		String[] ss = s.split(", ");
		Set<Number> out = new LinkedHashSet<>();
		for (String n : ss)
			out.add(Integer.parseInt(n));
		return out;
	}

	public static <E> LinkedHashSet<E> sortMap(Set<E> set, Comparator<E> comparator) {
		// ordena os valores
		List<E> list = new ArrayList<E>(set);
		Collections.sort(list, comparator);

		// insere no novo mapa conforme ordem da lista
		LinkedHashSet<E> out = new LinkedHashSet<E>(set.size());
		for (E v : list)
			out.add(v);
		return out;
	}
}
