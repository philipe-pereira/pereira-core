package br.com.pereiraeng.core;

import java.io.Serializable;

/**
 * Classe do objeto tag: uma sequência de caracteres que caracterizam de maneira
 * {@link #equals(Object) unívoca} algo
 * 
 * @author Philipe PEREIRA
 * @version July 08, 2020
 *
 */
public class Tag implements Serializable, Comparable<Tag> {
	private static final long serialVersionUID = 4517018290479019281L;

	/**
	 * Sequência de caracteres que caracterizam de maneira {@link #equals(Object)
	 * unívoca} algo
	 */
	protected String etq;

	/**
	 * id na base de dados externa, -1 se não houver identificação
	 */
	protected int externalId;

	/**
	 * Construtor do objeto que compreende uma sequência de caracteres
	 * 
	 * @param etq        sequência de caracteres que caracterizam de maneira
	 *                   {@link #equals(Object) unívoca} algo
	 * @param externalId id na base de dados externa, -1 se não houver identificação
	 */
	public Tag(String etq, int externalId) {
		this.etq = etq;
		this.externalId = externalId;
	}

	public String getEtq() {
		return etq;
	}

	public void setEtq(String etq) {
		this.etq = etq;
	}

	public int getExternalId() {
		return externalId;
	}

	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}

	@Override
	public boolean equals(Object anObject) {
		if (this == anObject)
			return true;
		if (anObject instanceof String) {
			String tag = (String) anObject;
			return tag.equals(this.getEtq());
		}
		if (anObject instanceof Tag) {
			Tag anotherTag = (Tag) anObject;
			return anotherTag.getEtq().equals(this.getEtq());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.etq.hashCode();
	}

	@Override
	public int compareTo(Tag o) {
		return this.etq.compareTo(o.getEtq());
	}

	@Override
	public String toString() {
		return this.etq;
	}
}
