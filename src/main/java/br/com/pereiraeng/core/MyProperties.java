package br.com.pereiraeng.core;

import java.util.Hashtable;

/**
 * Tabela que associa para cada tipo de propriedade, designada por uma sequência
 * de caracteres, uma {@link BinaryUtils#double2bytes(double) sequência de caracteres
 * que designa seu valor}
 * 
 * @author Philipe PEREIRA
 * @version September 30th, 2020
 *
 */
public class MyProperties extends Hashtable<String, byte[]> {
	private static final long serialVersionUID = -2408964296400400650L;

	public byte[] setProperty(String property, byte[] data) {
		return put(property, data);
	}

	public byte[] getProperty(String name) {
		return get(name);
	}

	public byte[] getProperty(Tag tag) {
		return getProperty(tag.getEtq());
	}
}
