package br.com.pereiraeng.core.collections;

import java.util.Collection;

public class SetUtils {

	/**
	 * Função que carrega no vetor de duas posições os objetos limítrofes de uma
	 * relação de períodos
	 * 
	 * @param out vetor de duas posições a ser carregado com os objetos mínimos e
	 *            máximos
	 * @param ps  relação de períodos
	 */
	public static <K extends Comparable<K>> void union(K[] out, Collection<K[]> ps) {
		if (ps != null)
			for (K[] p : ps)
				union(out, p);
	}

	/**
	 * Função que atualiza os limites de um dado período
	 * 
	 * @param out período a ser atualizado
	 * @param p   período indicando os possíveis novos limites
	 */
	public static <K extends Comparable<K>> void union(K[] out, K[] p) {
		if (p == null ? true : p.length < 2)
			return;
		if (p[0] == null ? false : (out[0] == null ? true : p[0].compareTo(out[0]) < 0))
			out[0] = p[0];
		if (p[1] == null ? false : (out[1] == null ? true : p[1].compareTo(out[1]) > 0))
			out[1] = p[1];
	}

}
