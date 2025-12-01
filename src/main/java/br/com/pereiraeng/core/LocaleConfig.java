package br.com.pereiraeng.core;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleConfig {

	private static Locale lc;

	private LocaleConfig() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("arqconfig");
			String language = rb.getString("language");
			String country = rb.getString("country");

			lc = new Locale(language, country);
		} catch (MissingResourceException e) {
		}
	}

	/**
	 * Função que retorna um <code>String</code> a partir de um dado
	 * palavra-chave, em função das configurações locais
	 * 
	 * @param key
	 *            palavra-chave associada
	 * @return <code>String</code> correspondente, ou a chave caso não haja
	 *         correspondência
	 */
	public static String getString(String key) {
		String s = null;

		if (lc == null)
			new LocaleConfig();

		try {
			ResourceBundle rb = ResourceBundle.getBundle("txt", lc);
			s = rb.getString(key);
		} catch (MissingResourceException e) {
			s = null;
		}
		return s != null ? s : key;
	}

	/**
	 * Função que indica se há um conjunto de configurações locais
	 * 
	 * @return <code>true</code> se há o arquivo de configurações locais,
	 *         <code>false</code> senão
	 */
	public static boolean hasConfig() {
		if (lc == null) {
			new LocaleConfig();
			return lc != null;
		} else
			return true;
	}
}
