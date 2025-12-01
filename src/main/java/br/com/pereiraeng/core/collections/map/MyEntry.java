package br.com.pereiraeng.core.collections.map;

import java.util.Map;

/**
 * Implementação local da entrada das tabelas de dispersão
 * 
 * @author Philipe PEREIRA
 *
 * @param <K>
 *            classe da chave
 * @param <V>
 *            classe da valor
 */
public final class MyEntry<K, V> implements Map.Entry<K, V> {
	private final K key;
	private V value;

	public MyEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}

	@Override
	public String toString() {
		return key + "->" + value;
	}
}
