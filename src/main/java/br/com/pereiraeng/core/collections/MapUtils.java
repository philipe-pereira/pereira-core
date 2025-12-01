package br.com.pereiraeng.core.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Maps utils
 * 
 * @author Philipe PEREIRA
 *
 */
public class MapUtils {

	public static <K, V> TreeMap<K, V> toTM(Map<K, V> hm) {
		TreeMap<K, V> out = new TreeMap<>();
		for (Entry<K, V> e : hm.entrySet())
			out.put(e.getKey(), e.getValue());
		return out;
	}

	public static <K, V> HashMap<K, V> toHM(Map<K, V> hm) {
		HashMap<K, V> out = new HashMap<>();
		for (Entry<K, V> e : hm.entrySet())
			out.put(e.getKey(), e.getValue());
		return out;
	}

	public static <K, V> int indexOfKey(Map<K, V> map, K k) {
		int i = 0;
		for (Entry<K, V> entry : map.entrySet())
			if (k.equals(entry.getKey()))
				return i;
			else
				i++;
		return -1;
	}

	public static <K, V> int indexOfKey(Dictionary<K, V> d, K k) {
		int i = 0;
		Enumeration<K> e = d.keys();
		while (e.hasMoreElements()) {
			if (k.equals(e.nextElement()))
				return i;
			else
				i++;
		}
		return -1;
	}

	public static <K, V> K getKeyAt(Map<K, V> map, int index) {
		for (Entry<K, V> entry : map.entrySet())
			if (index-- == 0)
				return entry.getKey();
		return null;
	}

	public static <K, V> int indexOfValue(Map<K, V> map, V v) {
		int i = 0;
		for (Entry<K, V> entry : map.entrySet())
			if (v.equals(entry.getValue()))
				return i;
			else
				i++;
		return -1;
	}

	/**
	 * Função que retorna o objeto de uma entrada de uma tabela de dispersão numa
	 * dada posição
	 * 
	 * @param <K>   classe da chave da tabela de dispersão
	 * @param <V>   classe do objeto da tabela de dispersão
	 * @param map   tabela de dispersão
	 * @param index inteiro indicando a posição
	 * @return objeto na dada posição
	 */
	public static <K, V> V getValueAt(Map<K, V> map, int index) {
		for (Entry<K, V> entry : map.entrySet())
			if (index-- == 0)
				return entry.getValue();
		return null;
	}

	public static <K, V> Entry<K, V> getEntryAt(Map<K, V> map, int index) {
		for (Entry<K, V> entry : map.entrySet())
			if (index-- == 0)
				return entry;
		return null;
	}

	public static <K, V> Entry<K, V> removeEntryAt(Map<K, V> map, int index) {
		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<K, V> k = it.next();
			if (index-- == 0) {
				it.remove();
				return k;
			}
		}
		return null;
	}

	/**
	 * 
	 * Função que retorna a primeira chave do mapa que retorna um dado objeto
	 * 
	 * @param map   mapa a ser varrido
	 * @param value objeto cuja chave se procura
	 * @return primeira chave que retorna o valor dado, ou <code>null</code> se não
	 *         há objeto associados a essa chave
	 */
	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Entry<K, V> o : map.entrySet())
			if (o.getValue().equals(value))
				return o.getKey();
		return null;
	}

	/**
	 * 
	 * Função que retorna a primeira chave do mapa que retorna um vetor de objetos
	 * que, numa dada posição, contenha o valor indicadoFSF
	 * 
	 * @param map   mapa a ser varrido
	 * @param value objeto cuja chave se procura
	 * @param pos   posição no vetor
	 * @return primeira chave que retorna o valor dado, ou <code>null</code> se não
	 *         há objeto associados a essa chave
	 */
	public static <K, V> K getKey(Map<K, V[]> map, V value, int pos) {
		for (Entry<K, V[]> o : map.entrySet())
			if (o.getValue()[pos].equals(value))
				return o.getKey();
		return null;
	}

	public static <K, V> K getKey(V value, Dictionary<K, V> d) {
		Enumeration<K> e1 = d.keys();
		Enumeration<V> e2 = d.elements();
		while (e1.hasMoreElements() && e2.hasMoreElements()) {
			K k0 = e1.nextElement();
			V v0 = e2.nextElement();
			if (v0.equals(value))
				return k0;
		}
		return null;
	}

	/**
	 * Função que retorna as chaves do mapa que retornam um dado objeto
	 * 
	 * @param map   mapa a ser varrido
	 * @param value objeto cuja chave se procura
	 * @return conjunto de chaves que retornam o valor dado
	 */
	public static <K, V> Set<K> getKeys(Map<K, V> map, V value) {
		LinkedHashSet<K> keys = new LinkedHashSet<>();
		for (Entry<K, V> o : map.entrySet())
			if (value.equals(o.getValue()))
				keys.add(o.getKey());
		return keys;
	}

	public static <K, V> Set<K> getKeys(Map<K, V> map, Collection<V> values) {
		LinkedHashSet<K> keys = new LinkedHashSet<>();
		for (Entry<K, V> o : map.entrySet())
			if (values.contains(o.getValue()))
				keys.add(o.getKey());
		return keys;
	}

	/**
	 * Função que remove um dado valor do mapa (o primeiro encontrado dentro de um
	 * mapa)
	 * 
	 * @param map   mapa que contém o valor a ser apagado
	 * @param value objeto a ser apagado
	 * @return chave do objeto apagado
	 */
	public static <K, V> K removeValue(Map<K, V> map, V value) {
		K k = getKey(map, value);
		map.remove(k);
		return k;
	}

	/**
	 * Função que retorna um mapa que contém somente as entrada de um outro mapa
	 * associada a um dado conjunto de chaves
	 * 
	 * @param keys chaves que constarão no novo mapa
	 * @param map  mapa original, com chaves que constarão ou não no novo mapa
	 * @return novo mapa, só das ocorrências do conjuntos de chaves passado como
	 *         argumento. O mapa da saída é do mesmo tipo que aquele que foi enviado
	 *         passado como argumento
	 */
	public static <K, V> Map<K, V> subMap(Set<K> keys, Map<K, V> map) {
		Map<K, V> out = null;

		// mapa da saída é o mesmo da entrada
		if (map instanceof LinkedHashMap)
			out = new LinkedHashMap<K, V>();
		else if (map instanceof HashMap)
			out = new HashMap<K, V>();
		else if (map instanceof TreeMap)
			out = new TreeMap<K, V>();

		// se o mapa contém a chave do conjunto, a entrada dessa mapa vai para o
		// mapa da saída
		for (K k : keys) {
			V v = map.get(k);
			if (v != null)
				out.put(k, v);
		}

		return out;
	}

	/**
	 * Função que ordena uma dada tabela de dispersão pelos seus valores segundo a
	 * ordem natural de seus <strong>valores</strong>. A ordenação é feita com a
	 * criação de uma nova tabela {@link LinkedHashMap que permite a fixação de uma
	 * ordem} de acordo com a inserção
	 * 
	 * @param map mapa a ser ordenado
	 * @return novo mapa, com a ordem definida pelos seus valores
	 */
	public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortMap(Map<K, V> map) {
		// ordena os valores
		List<V> list = new ArrayList<V>(map.values());
		Collections.sort(list);

		// insere no novo mapa conforme ordem da lista
		LinkedHashMap<K, V> out = new LinkedHashMap<K, V>(map.size());
		for (V v : list) {
			Set<K> ks = getKeys(map, v);
			for (K k : ks)
				out.put(k, v);
		}
		return out;
	}

	/**
	 * Função que ordena uma dada tabela de dispersão pelos seus valores segundo a
	 * ordem natural de seus <strong>valores</strong>. A ordenação é feita sobre
	 * esta mesma tabela, {@link LinkedHashMap de modo que ela deve aceitar que a
	 * ordem seja feita de acordo com a ordem de inserção}
	 * 
	 * @param map mapa a ser ordenado
	 */
	public static <K, V extends Comparable<? super V>> void sortThisMap(LinkedHashMap<K, V> map) {
		LinkedHashMap<K, V> out = sortMap(map);
		map.clear();
		map.putAll(out);
	}

	/**
	 * Função que ordena uma dada tabela de dispersão pelos seus valores segundo uma
	 * regra passada pelo usuário. A ordenação é feita com a criação de uma nova
	 * tabela {@link LinkedHashMap que permite a fixação de uma ordem} de acordo com
	 * a inserção
	 * 
	 * @param map        mapa a ser ordenado
	 * @param comparator regra de ordenação dos elementos do mapa
	 * @return novo mapa, com a ordem definida pelos seus valores
	 */
	public static <K, V> LinkedHashMap<K, V> sortMap(Map<K, V> map, Comparator<V> comparator) {
		// ordena os valores
		List<V> list = new ArrayList<V>(map.values());
		Collections.sort(list, comparator);

		// insere no novo mapa conforme ordem da lista
		LinkedHashMap<K, V> out = new LinkedHashMap<K, V>(map.size());
		for (V v : list) {
			Set<K> ks = getKeys(map, v);
			for (K k : ks)
				out.put(k, v);
		}
		return out;
	}

	/**
	 * Função que ordena uma dada tabela de dispersão pelos seus valores segundo uma
	 * regra passada pelo usuário. A ordenação é feita sobre esta mesma tabela,
	 * {@link LinkedHashMap de modo que ela deve aceitar que a ordem seja feita de
	 * acordo com a ordem de inserção}
	 * 
	 * @param map        mapa a ser ordenado
	 * @param comparator regra de ordenação dos elementos do mapa
	 */
	public static <K, V> void sortThisMap(LinkedHashMap<K, V> map, Comparator<V> comparator) {
		LinkedHashMap<K, V> out = sortMap(map, comparator);
		map.clear();
		map.putAll(out);
	}

	/**
	 * Função que ordena uma tabela de dispersão {@link LinkedHashMap ordenável}
	 * segundo a ordem das chaves num vetor
	 * 
	 * @param map tabela de dispersão a ser ordenada
	 * @param fs  vetor que ditará a ordem das entradas na tabela de dispersão
	 */
	public static <K, V> void order(LinkedHashMap<K, V> map, K[] fs) {
		if (map.size() == fs.length) {
			LinkedHashMap<K, V> temp = new LinkedHashMap<>();

			for (int i = 0; i < fs.length; i++) {
				V v = map.get(fs[i]);
				if (v != null)
					temp.put(fs[i], v);
			}

			map.clear();
			map.putAll(temp);
			temp.clear();
			temp = null;
		} else
			throw new IllegalArgumentException(
					"O vetor deve ter um número de elementos igual ao número de chaves da tabela de dispersão.");
	}

	/**
	 * Função que ordena uma tabela de dispersão {@link LinkedHashMap ordenável}
	 * segundo a ordem das chaves numa dada coleção
	 * 
	 * @param map tabela de dispersão a ser ordenada
	 * @param ks  coleção que ditará a ordem das entradas na tabela de dispersão
	 */
	public static <K, V> void order(LinkedHashMap<K, V> map, Collection<K> ks) {
		LinkedHashMap<K, V> temp = new LinkedHashMap<>();

		for (K k : ks) {
			V v = map.get(k);
			if (v != null) {
				temp.put(k, v);
				map.remove(k);
			}
		}

		if (map.size() > 0) {
			temp.putAll(map);
			map.clear();
		}

		map.putAll(temp);
	}

	/**
	 * Função que remove todas as ocorrências de um dado valor no mapa
	 * 
	 * @param map   mapa que contém o valor a ser apagado
	 * @param value objeto a ser apagado
	 * @return conjunto de chaves que apontavam para o objeto apagado
	 */
	public static <K, V> Set<K> removeValues(Map<K, V> map, V value) {
		Set<K> ks = getKeys(map, value);
		for (K k : ks)
			map.remove(k);
		return ks;
	}

	public static <V, K> Map<K, V> reverse(Map<V, K> in) {
		Map<K, V> out = null;
		if (in instanceof HashMap)
			out = new HashMap<>();
		else if (in instanceof LinkedHashMap)
			out = new LinkedHashMap<>();
		else if (in instanceof TreeMap)
			out = new TreeMap<>();

		for (V i : in.keySet())
			out.put(in.get(i), i);
		return out;
	}

	public static <V, K> Map<K, Set<V>> reverseFull(Map<V, K> in) {
		Map<K, Set<V>> out = null;
		if (in instanceof HashMap)
			out = new HashMap<>();
		else if (in instanceof LinkedHashMap)
			out = new LinkedHashMap<>();
		else if (in instanceof TreeMap)
			out = new TreeMap<>();

		for (V i : in.keySet()) {
			Set<V> vs = out.get(in.get(i));
			if (vs == null)
				out.put(in.get(i), vs = new HashSet<>());
			vs.add(i);
		}
		return out;
	}

	public static <K, V> void put(LinkedHashMap<K, V> map, int index, K key, V value) {
		if (index >= 0) {
			LinkedHashMap<K, V> temp = new LinkedHashMap<>();

			if (index == 0) {
				temp.put(key, value);
				temp.putAll(map);
			} else {
				int i = 0;
				for (Entry<K, V> e : map.entrySet()) {
					if (i == index)
						temp.put(key, value);
					temp.put(e.getKey(), e.getValue());
					i++;
				}
			}
			if (index >= map.size())
				temp.put(key, value);

			map.clear();
			map.putAll(temp);
			temp.clear();
			temp = null;
		} else
			throw new IndexOutOfBoundsException("index " + index + " must be greater than zero");
	}

	// COMPOSIÇÃO DE TABELAS DE DISPERSÃO

	/**
	 * Função que troca as chaves de uma dada tabela, gerando uma nova
	 * 
	 * @param oldKey tabela com as chaves antigas
	 * @param newKey tabela que associa as chaves antigas às novas
	 * @param order  <code>true</code> para ordem natural, <code>false</code> para
	 *               sem ordem
	 * @return tabela com as chaves novas
	 */
	public static <K, I, V> Map<K, V> changeKey(Map<I, V> oldKey, Map<I, K> newKey, boolean order) {
		Map<K, V> out = null;

		if (order)
			out = new TreeMap<>();
		else
			out = new HashMap<>();

		for (Entry<I, V> e : oldKey.entrySet())
			out.put(newKey.get(e.getKey()), e.getValue());

		return out;
	}

	/**
	 * Função que trata as tabela como função e que gera a 'função composta' de duas
	 * tabelas
	 * 
	 * @param a2b   tabela que associa 'a' a 'b'
	 * @param b2c   tabela que associa 'b' a 'c'
	 * @param order <code>true</code> para ordem natural, <code>false</code> para
	 *              sem ordem
	 * @return tabela que associa 'a' a 'c'
	 */
	public static <K, I, V> Map<K, V> compose(Map<K, I> a2b, Map<I, V> b2c, boolean order) {
		Map<K, V> a2c = null;

		if (order)
			a2c = new TreeMap<>();
		else
			a2c = new HashMap<>();

		MapUtils.compose(a2b, b2c, a2c);

		return a2c;
	}

	public static <K, I, V> void compose(Map<K, I> a2b, Map<I, V> b2c, Map<K, V> a2c) {
		for (Entry<K, I> e : a2b.entrySet())
			a2c.put(e.getKey(), b2c.get(e.getValue()));
	}

	public static <K, V> void compose(Map<K, V> a2b, Collection<K> as, Collection<V> bs) {
		for (K a : as)
			bs.add(a2b.get(a));
	}

	public static <K, V> Collection<V> compose(Map<K, V> a2b, Collection<K> as, boolean set) {
		Collection<V> bs = set ? new HashSet<>() : new LinkedList<>();
		for (K a : as)
			bs.add(a2b.get(a));
		return bs;
	}

	/**
	 * Função inversa de {@link MapUtils#toString(Map)}
	 * 
	 * @param s
	 * @return
	 */
	public static Map<String, Object> toMap(String s) {
		s = s.substring(2, s.length() - 2);
		String[] ss = s.split("\", \"");
		Map<String, Object> out = new LinkedHashMap<>();
		for (String n : ss) {
			String[] ns = n.split("\" : \"");
			out.put(ns[0], ns[1]);
		}
		return out;
	}

	/**
	 * Função inversa de {@link #toMap(String)}
	 * 
	 * @param map
	 * @return
	 */
	public static String toString(Map<?, ?> map) {
		String out = "{";
		for (Entry<?, ?> e : map.entrySet())
			out += String.format("\"%s\" : \"%s\", ", e.getKey(), e.getValue());
		return out.substring(0, out.length() - 2) + "}";
	}

}
