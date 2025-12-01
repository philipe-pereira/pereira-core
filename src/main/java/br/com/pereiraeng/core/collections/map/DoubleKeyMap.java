package br.com.pereiraeng.core.collections.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.pereiraeng.core.collections.MapUtils;

import java.util.Set;
import java.util.TreeMap;

/**
 * Classe do objeto que representa uma tabela de dispersão onde cada objeto pode
 * ser acessado a univocamente a partir de duas chaves.
 * 
 * @author Philipe PEREIRA
 *
 * @param <K> classe da chave principal
 * @param <I> classe da chave intermediária
 * @param <V> classe do objeto
 */
public class DoubleKeyMap<K, I, V> {

	private Map<K, I> main2int;

	private HashMap<I, V> int2value;

	public enum Order {
		/**
		 * Sem ordenamento
		 */
		NO,
		/**
		 * Ordenamento natural
		 */
		NAT,
		/**
		 * Ordenamento pela inserção
		 */
		INS;
	}

	public DoubleKeyMap() {
		this(Order.NO);
	}

	public DoubleKeyMap(Order order) {
		// intermediário
		switch (order) {
		case NO:
			this.main2int = new HashMap<>();
			break;
		case NAT:
			this.main2int = new TreeMap<>();
			break;
		case INS:
			this.main2int = new LinkedHashMap<>();
			break;
		}
		// principal
		this.int2value = new HashMap<>();
	}

	public int size() {
		return this.int2value.size();
	}

	public V get(Object key) {
		return this.int2value.get(main2int.get(key));
	}

	public V getI(Object key) {
		return this.int2value.get(key);
	}

	public V remove(Object key) {
		I i = main2int.remove(key);
		return this.int2value.remove(i);
	}

	public V removeI(I key) {
		MapUtils.removeValue(main2int, key);
		return this.int2value.remove(key);
	}

	public V put(K key, I key2, V value) {
		this.main2int.put(key, key2);
		return this.int2value.put(key2, value);
	}

	public void clear() {
		this.main2int.clear();
		this.int2value.clear();
	}

	public boolean containsKey(Object key) {
		return this.main2int.containsKey(key);
	}

	public boolean containsKeyI(Object key) {
		return this.int2value.containsKey(key);
	}

	public Set<K> keySet() {
		return this.main2int.keySet();
	}

	public Set<I> keySetI() {
		return int2value.keySet();
	}

	/**
	 * Returns a Collection view of the values contained in this map.
	 * 
	 * @return a collection view of the values contained in this map
	 */
	public Collection<V> values() {
		return this.int2value.values();
	}

	public Set<Entry<I, V>> entrySet() {
		return this.int2value.entrySet();
	}

	// ============================== AUXILIARES ==============================

	/**
	 * Função que gera a tabela com a chave dupla
	 * 
	 * @param kv tabela com a chave principal
	 * @param ik tabela que associa para cada chave intermediária a chave principal
	 * @return tabela com a chave dupla
	 */
	public static <K, I, V> DoubleKeyMap<K, I, V> getDoubleKey(Map<K, V> kv, Map<I, K> ik) {
		DoubleKeyMap<K, I, V> dkm = new DoubleKeyMap<>();
		for (Entry<I, K> e : ik.entrySet()) {
			K k = e.getValue();
			V v = kv.get(k);
			if (v != null)
				dkm.put(k, e.getKey(), v);
		}
		return dkm;
	}
}
