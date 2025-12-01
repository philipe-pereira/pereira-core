package br.com.pereiraeng.core.collections.comparison;

import java.util.Comparator;

import br.com.pereiraeng.core.StringUtils;

/**
 * Classe dos objetos objetos que ordenam {@link String} considerando eventuais
 * acentos ou artigos
 * 
 * @author Philipe PEREIRA
 * 
 */
public class AlphabeticalOrder implements Comparator<Object> {
	private final boolean prepare;

	public AlphabeticalOrder() {
		this(false);
	}

	public AlphabeticalOrder(boolean prepare) {
		this.prepare = prepare;
	}

	@Override
	public int compare(Object p1, Object p2) {
		return prepareWord(p1.toString()).compareTo(prepareWord(p2.toString()));
	}

	/**
	 * Função que prepara a palavra antes desta ser comparada com outra. Os
	 * possíveis artigos que a antecedem e seus acentos são removidos
	 * 
	 * @param word palavra a ser ordenada na lista
	 * @return palavra sem os acentos e artigos
	 */
	private String prepareWord(String words) {
		String out = words;

		if (prepare) {
			// retirar as partículas que precedem a palavra
			if (out.startsWith("l'"))
				out = out.substring(2);
			if (out.startsWith("la ") || out.startsWith("un ") || out.startsWith("le ") || out.startsWith("to "))
				out = out.substring(3);
			if (out.startsWith("les ") || out.startsWith("des ") || out.startsWith("une "))
				out = out.substring(4);

			// passar para letras minúsculas e retirar os acentos
			out = StringUtils.removeAccent(out.toLowerCase());
		}

		return out;
	}
}
