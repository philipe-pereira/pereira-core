package br.com.pereiraeng.core;

import java.util.regex.Pattern;

public class Uuid {

	/**
	 * Tamanho do código UUID, em número de caracteres
	 */
	private static final int UUID_LENGTH = 36;

	/**
	 * Padrão do UUID
	 */
	private static final Pattern UUID_PATTERN = Pattern
			.compile("\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}");

	public static boolean checkUuid(String str) {
		if (str.length() == UUID_LENGTH)
			return UUID_PATTERN.matcher(str).find();
		else
			return false;
	}
}
