package br.com.pereiraeng.core;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe das funções de manipulação de sequências de caracteres
 * 
 * @author Philipe PEREIRA
 *
 */
public class StringUtils {

	/**
	 * Função que adiciona um prefixo nas sequências de caracteres de um vetor
	 * 
	 * @param prefix prefixo a ser adicionado
	 * @param s      vetor de sequências de caracteres
	 * @return novo vetor
	 */
	public static String[] addPrefix(String prefix, String[] s) {
		String[] out = new String[s.length];
		for (int i = 0; i < out.length; i++)
			out[i] = prefix + s[i];
		return out;
	}

	/**
	 * Função que acha o prefixo comum de uma série de sequência de caracteres (o
	 * que vale dizer que todos estas sequências retorna <code>true</code> para
	 * {@link String#startsWith(String)} usando como argumento a resposta desta
	 * função
	 * 
	 * @param ss vetor de sequência de caracteres
	 * @return prefixo comum
	 */
	public static String getPrefix(String... ss) {
		if (ss.length != 0) {
			String p = ss[0];
			for (int i = 1; i < ss.length; i++)
				p = getPrefix2(p, ss[i]);
			return p;
		} else
			return null;
	}

	private static String getPrefix2(String s1, String s2) {
		int c = 0, stop = Math.min(s1.length(), s2.length());
		while (c < stop ? s1.charAt(c) == s2.charAt(c) : false)
			c++;
		return s1.substring(0, c);
	}

	/**
	 * Função que retorna uma sequência de caracteres que representa um número
	 * hexadecional aleatório com um determinado número de dígitos.
	 * 
	 * @param digits número de dígitos
	 * @return sequência de caracteres do número
	 */
	public static String generateID(int digits) {
		return String.format("%0" + digits + "x", (long) (Math.random() * Math.pow(16, digits)));
	}

	/**
	 * Função que retorna o tamanho da maior sequência de caracteres de um vetor
	 * 
	 * @param ss vetor de sequência de caracteres
	 * @return tamanho da maior sequência de caracteres
	 */
	public static int maxStringLength(String[] ss) {
		int out = Integer.MIN_VALUE;
		for (int i = 0; i < ss.length; i++)
			out = Math.max(ss[i].length(), out);
		return out;
	}

	/**
	 * Função que retorna o número de ocorrência de um caracter em uma sequência
	 * 
	 * @param s sequência de caracteres
	 * @param c caracter
	 * @return número de ocorrências
	 */
	public static int countOccurrences(String s, char c) {
		int out = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == c)
				out++;
		return out;
	}

	/**
	 * Função que retorna o conjunto de caracteres de uma ou mais sequência de
	 * caracteres
	 * 
	 * @param s uma ou mais sequência de caracteres
	 * @return conjunto de caracteres
	 */
	public static Set<Character> getCharSets(String... s) {
		Set<Character> out = new HashSet<>();
		for (int i = 0; i < s.length; i++)
			if (s[i] != null)
				for (int j = 0; j < s[i].length(); j++)
					out.add(s[i].charAt(j));
		return out;
	}

	public static String getStringFromCS(Set<Character> cs) {
		StringBuilder out = new StringBuilder();
		for (Character c : cs)
			out.append(c);
		return out.toString();
	}

	public static String mergeCharSets(String... ss) {
		return getStringFromCS(getCharSets(ss));
	}

	/**
	 * Função que determina se duas sequências de caracteres são constituídas dos
	 * mesmos caracteres
	 * 
	 * @param s1 uma sequência de caracteres
	 * @param s2 outra sequência de caracteres
	 * @return <code>true</code> se as sequências possuem os mesmos caracteres,
	 *         <code>false</code> senão
	 */
	public static boolean hasSameLetters(String s1, String s2) {
		return getCharSets(s1).equals(getCharSets(s2));
	}

	/**
	 * Função que retorna uma sequência de caracteres que é a combinação dos
	 * {@link Object#toString() string característicos} separados por um dado
	 * separador
	 * 
	 * @param objs      relação de objetos cujas sequências características serão
	 *                  incluídas no string a ser formado
	 * @param separator separador a ser incluído entre cada string característico
	 * @return sequência contendo todos os objetos separados
	 */
	public static String addSeparator(Collection<? extends Object> objs, String separator) {
		return addSeparator(objs, null, separator);
	}

	/**
	 * Função que retorna uma sequência de caracteres que é a combinação dos
	 * {@link Object#toString() string característicos} separados por um dado
	 * separador
	 * 
	 * @param objs      relação de objetos cujas sequências características serão
	 *                  incluídas no string a ser formado
	 * @param formatter conversor objeto -> string (ver
	 *                  {@link String#format(String, Object...)})
	 * @param separator separador a ser incluído entre cada string característico
	 * @return sequência contendo todos os objetos separados
	 */
	public static String addSeparator(Collection<? extends Object> objs, String formatter, String separator) {
		if (objs.size() == 0)
			return "";
		String ft = formatter != null ? formatter : "%s"; // se não indicar formatter, toString
		Iterator<? extends Object> it = objs.iterator();
		StringBuilder out = new StringBuilder(String.format(ft, it.next()));
		while (it.hasNext())
			out.append(String.format(separator + ft, it.next()));
		return out.toString();
	}

	/**
	 * Função que retorna uma sequência de caracteres que é a combinação dos
	 * {@link Object#toString() string característicos} separados por um dado
	 * separador
	 * 
	 * @param objs      vetor de objetos cujas sequências características serão
	 *                  incluídas no string a ser formado
	 * @param separator separador a ser incluído entre cada string característico
	 * @param pos       vetor de tamanho variável, contendo inteiros que indicam
	 *                  quais dos elementos do vetor (primeiro argumento) serão
	 *                  utilizados (se o tamanho do vetor for nulo, serão usados
	 *                  todos os elementos)
	 * @return sequência contendo todos os objetos separados
	 */
	public static String addSeparator(Object[] objs, String separator, int... pos) {
		return addSeparator(objs, null, separator, pos);
	}

	/**
	 * Função que retorna uma sequência de caracteres que é a combinação dos
	 * {@link Object#toString() string característicos} separados por um dado
	 * separador
	 * 
	 * @param objs      vetor de objetos cujas sequências características serão
	 *                  incluídas no string a ser formado
	 * @param formatter conversor objeto -> string (ver
	 *                  {@link String#format(String, Object...)})
	 * @param separator separador a ser incluído entre cada string característico
	 * @param pos       vetor de tamanho variável, contendo inteiros que indicam
	 *                  quais dos elementos do vetor (primeiro argumento) serão
	 *                  utilizados (se o tamanho do vetor for nulo, serão usados
	 *                  todos os elementos)
	 * @return sequência contendo todos os objetos separados
	 */
	public static String addSeparator(Object[] objs, String formatter, String separator, int... pos) {
		if (objs.length == 0)
			return "";
		StringBuilder out = null;
		String ft = formatter != null ? formatter : "%s"; // se não indicar formatter, toString
		if (pos.length == 0) { // todos
			out = new StringBuilder(String.format(ft, objs[0]));
			for (int i = 1; i < objs.length; i++)
				out.append(String.format(separator + ft, objs[i]));
		} else { // somente algumas posições
			out = new StringBuilder(String.format(ft, objs[pos[0]].toString()));
			for (int i = 1; i < pos.length; i++)
				out.append(String.format(separator + ft, objs[pos[i]]));
		}
		return out.toString();
	}

	/**
	 * Função que retorna uma sequência de caracteres que é a repetição de uma dada
	 * sequência de caracteres separados por um dado separador
	 * 
	 * @param content   sequência de caracteres a ser repetida
	 * @param separator separador a ser incluído entre cada string
	 * @param n         número de repetições
	 * @return sequência contendo todos as string repetidas separadas
	 */
	public static String addSeparator(String content, String separator, int n) {
		if (n == 0)
			return "";
		StringBuilder out = new StringBuilder(content);
		for (int i = 1; i < n; i++)
			out.append(separator + content);
		return out.toString();
	}

	/**
	 * Função que retorna uma sequência de caracteres composta de um caracter
	 * repetido várias vezes
	 * 
	 * @param c caracter a ser repetido
	 * @param n número de repetições
	 * @return sequência contendo o caracter repetido
	 */
	public static String repeatChar(char c, int n) {
		if (n < 0)
			throw new IllegalArgumentException("Número negativo de caracteres");
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < n; i++)
			out.append(c);
		return out.toString();
	}

	/**
	 * Função que retorna uma sequência de caracteres composta da repetição de um
	 * dada sequência
	 * 
	 * @param c sequência a ser repetida
	 * @param n número de repetições
	 * @return sequência resultante da repetição
	 */
	public static String repeatString(String c, int n) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < n; i++)
			out.append(c);
		return out.toString();
	}

	/**
	 * Returns the index within this string of the n-th occurrence of the specified
	 * character.
	 * 
	 * @param str a string.
	 * @param c   a character (Unicode code point).
	 * @param n   occurrence
	 * @return the index of the n-th occurrence of the character, or -1 if the
	 *         character does not occur.
	 */
	public static int ordinalIndexOf(String str, char c, int n) {
		return ordinalIndexOf(str, c, n, 0);
	}

	/**
	 * Returns the index within this string of the n-th occurrence of the specified
	 * substring.
	 * 
	 * 
	 * @param str    a string.
	 * @param substr the substring to search for.
	 * @param n      occurrence
	 * @return the index of the n-th occurrence of the specified substring, or -1 if
	 *         there is no such occurrence.
	 */
	public static int ordinalIndexOf(String str, String substr, int n) {
		return ordinalIndexOf(str, substr, n, 0);
	}

	/**
	 * Returns the index within this string of the n-th occurrence of the specified
	 * character, starting the search at the specified index.
	 * 
	 * @param str       a string.
	 * @param c         a character (Unicode code point).
	 * @param n         occurrence
	 * @param fromIndex the index to start the search from
	 * @return the index of the n-th occurrence of the character, starting at the
	 *         specified index, or -1 if the character does not occur.
	 */
	public static int ordinalIndexOf(String str, char c, int n, int fromIndex) {
		int pos = fromIndex - 1;
		do
			pos = str.indexOf(c, pos + 1);
		while (n-- > 0 && pos != -1);
		return pos;
	}

	/**
	 * Returns the index within this string of the n-th occurrence of the specified
	 * substring, starting the search at the specified index.
	 * 
	 * @param str       a string.
	 * @param substr    the substring to search for.
	 * @param n         occurrence
	 * @param fromIndex the index to start the search from
	 * @return the index of the n-th occurrence of the specified substring, starting
	 *         at the specified index, or -1 if there is no such occurrence.
	 */
	public static int ordinalIndexOf(String str, String substr, int n, int fromIndex) {
		int pos = fromIndex - 1;
		do
			pos = str.indexOf(substr, pos + 1);
		while (n-- > 0 && pos != -1);
		return pos;
	}

	/**
	 * <p>
	 * Returns a new string that is a substring of a string.
	 * </p>
	 * <p>
	 * A subsequência de caracteres é delimitada por um dado caracter, localizada na
	 * n-ésima delimitação. É equivalente à função: <code>str.split(c)[n]</code>.
	 * </p>
	 * 
	 * @param n   inteiro não negativo que indica a posição delimitada
	 * @param str a string.
	 * @param c   caracter delimitador
	 * @return the specified substring.
	 */
	public static String substring(int n, String str, char c) {
		if (n == 0)
			return str.substring(0, str.indexOf(c, 0));
		else {
			int p1 = StringUtils.ordinalIndexOf(str, c, n - 1) + 1;
			if (p1 > 0) {
				int p2 = str.indexOf(c, p1);
				if (p2 < 0)
					return str.substring(p1);
				else
					return str.substring(p1, p2);
			} else
				return null;
		}
	}

	/**
	 * <p>
	 * Returns a new string that is a substring of a string.
	 * </p>
	 * <p>
	 * A subsequência de caracteres é delimitada por uma outra dada sequência de
	 * caracteres, localizada na n-ésima delimitação. É equivalente à função:
	 * <code>str.split(substr)[n]</code>.
	 * </p>
	 * 
	 * @param n      inteiro não negativo que indica a posição delimitada
	 * @param str    a string.
	 * @param substr sequência de caracteres delimitadora
	 * @return the specified substring.
	 */
	public static String substring(int n, String str, String substr) {
		if (n == 0)
			return str.substring(0, str.indexOf(substr, 0));
		else {
			int p1 = StringUtils.ordinalIndexOf(str, substr, n - 1) + 1;
			if (p1 > 0) {
				int p2 = str.indexOf(substr, p1);
				if (p2 < 0)
					return str.substring(p1);
				else
					return str.substring(p1, p2);
			} else
				return null;
		}
	}

	/**
	 * <p>
	 * Returns new strings that are substrings of a string.
	 * </p>
	 * <p>
	 * As subsequências de caracteres são delimitadas por um dado caracter,
	 * localizadas nas n-ésimas delimitações indicadas. Tais localizações devem ser
	 * indicadas por um vetor de número inteiros <strong>não repetidos e em ordem
	 * crescente</strong>.
	 * </p>
	 * 
	 * @param str a string.
	 * @param c   caracter delimitador
	 * @param ns  vetor de inteiros não negativos, não repetidos e em ordem
	 *            crescente que indicam as posições delimitadas
	 * @return array of the specified substrings
	 */
	public static String[] substrings(String str, char c, int... ns) {
		String[] out = new String[ns.length];

		int p2 = 0, n = 0;
		for (int i = 0; i < ns.length; i++) {
			if (ns[i] == 0)
				out[i] = str.substring(0, p2 = str.indexOf(c));
			else {
				int p1 = StringUtils.ordinalIndexOf(str, c, ns[i] - n - 1, p2) + 1;
				if (p1 > 0) {
					p2 = str.indexOf(c, p1);
					if (p2 < 0)
						out[i] = str.substring(p1);
					else
						out[i] = str.substring(p1, p2);
				} else {
					out[i] = null;
					p2 = str.length();
				}
			}
			n = ns[i];
		}
		return out;
	}

	/**
	 * <p>
	 * Returns new strings that are substrings of a string.
	 * </p>
	 * <p>
	 * As subsequências de caracteres são delimitadas por uma outra dada sequência
	 * de caracteres, localizadas nas n-ésimas delimitações indicadas. Tais
	 * localizações devem ser indicadas por um vetor de número inteiros <strong>não
	 * repetidos e em ordem crescente</strong>.
	 * </p>
	 * 
	 * @param str    a string.
	 * @param substr sequência de caracteres delimitadora
	 * @param ns     vetor de inteiros não negativos, não repetidos e em ordem
	 *               crescente que indicam as posições delimitadas
	 * @return array of the specified substrings
	 */
	public static String[] substrings(String str, String substr, int... ns) {
		String[] out = new String[ns.length];

		int p2 = 0, n = 0;
		for (int i = 0; i < ns.length; i++) {
			if (ns[i] == 0)
				out[i] = str.substring(0, p2 = str.indexOf(substr));
			else {
				int p1 = StringUtils.ordinalIndexOf(str, substr, ns[i] - n - 1, p2) + 1;
				if (p1 > 0) {
					p2 = str.indexOf(substr, p1);
					if (p2 < 0)
						out[i] = str.substring(p1);
					else
						out[i] = str.substring(p1, p2);
				} else {
					out[i] = null;
					p2 = str.length();
				}
			}
			n = ns[i];
		}
		return out;
	}

	/**
	 * Função equivalente à função {@link String#split(String)}, com a diferença de
	 * que o argumento <code>regex</code> é somente um caracter e que se esse
	 * caracter for rodeado por outro (argumento <code>between</code>), ele será
	 * ignorado
	 * 
	 * @param s         sequência de caracteres a ser quebrada
	 * @param separator separador entre as quebras da sequência de caracteres
	 * @param between   caracter que gera exceções à quebra
	 * @return vetor de sequência de caracteres obtido da quebra da sequência
	 *         original
	 */
	public static String[] splitBetween(String s, char separator, char between) {
		LinkedList<String> out = new LinkedList<>();
		boolean inside = false;
		int lastPos = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!inside) {
				if (c == separator) {
					out.add(s.substring(lastPos, i));
					lastPos = i + 1;
				} else if (c == between)
					inside = true;
			} else {
				if (c == between)
					inside = false;
			}
		}
		out.add(s.substring(lastPos, s.length()));
		return out.toArray(new String[out.size()]);
	}

	/**
	 * Função que remove os últimos caracteres de uma sequência de caracteres
	 * 
	 * @param s sequência de caracteres
	 * @param i número de caracteres a serem removidos
	 * @return sequência de caracteres com os últimos caracteres removidos
	 */
	public static String chop(String s, int i) {
		return (s == null || s.length() < i) ? null : (s.substring(0, s.length() - i));
	}

	/**
	 * Função equivalente à função {@link String#trim()}, porém aplicada a outro
	 * caracter que não seja um caracter branco
	 * 
	 * @param s sequência de caracteres
	 * @param c caracter a ser removido das extremidades
	 * @return sequência de caracteres sem o caracter designado em suas extremidades
	 */
	public static String trim(String s, char c) {
		if (s == null)
			return null;
		int b = -1;
		int e = -1;

		int ie = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
			if (b == -1 && s.charAt(i) != c)
				b = i;
			if (e == -1 && s.charAt(ie) != c)
				e = ie;
			else
				ie--;
			if (b != -1 && e != -1)
				break;
		}
		return s.substring(b, e + 1);
	}

	// =========== Funções de manipulação de maiúsculas e minúsculas ===========

	/**
	 * Função que, a partir de uma sequência de caracteres, retorna mesma sequência
	 * porém com a primeira letra de cada palavra em maiúsculo
	 * 
	 * @param s sequência de caracteres
	 * @return sequência com as primeiras letras das palavras em maiúsculo
	 */
	public static String firstUpper(String s) {
		if (s.equals(""))
			return "";
		else {
			String[] ss = s.split("[ \\p{Punct}]");
			String out = "";
			for (String o : ss) {
				if (o.length() > 2) {
					out += Character.toUpperCase(o.charAt(0)) + o.substring(1).toLowerCase() + " ";
				} else if (o.length() > 0) {
					out += (o.toLowerCase() + " ");
				}
			}
			return out.substring(0, out.length() - 1);
		}
	}

	/**
	 * Função que retorna que converte um <code>String</code> no formato
	 * <i>CamelCase</i> em um String com espaços. É a função inversa de
	 * {@link StringUtils#removeSpace(String)}
	 * 
	 * @param camel sequência de caracteres no formato CamelCase.
	 * @return sequência de caracteres com espaços.
	 */
	public static String addSpace(String camel) {
		char c = camel.charAt(0);

		String out = String.format("%c", c);

		boolean uc0 = Character.isUpperCase(c);
		for (int i = 1; i < camel.length(); i++) {
			c = camel.charAt(i);
			boolean uc1 = Character.isUpperCase(c);
			out += (uc1 && !uc0 ? " " : "") + c;
			uc0 = uc1;
		}
		return out;
	}

	/**
	 * Função que retorna que converte um <code>String</code> com espaços para outro
	 * no formato <i>CamelCase</i> sem espaços. É a função inversa de
	 * {@link StringUtils#addSpace(String)}
	 * 
	 * @param spaced sequência de caracteres com espaços
	 * @return sequência de caracteres no formato CamelCase, sem espaços
	 */
	public static String removeSpace(String spaced) {
		String out = "";
		for (int i = 0; i < spaced.length(); i++) {
			if (spaced.charAt(i) == ' ') {
				out += Character.toUpperCase(spaced.charAt(++i));
			} else {
				out += spaced.charAt(i);
			}
		}
		return out;
	}

	// ==================== Função de manipulação de nomes ====================

	/**
	 * Função que, a partir de uma sequência de caracteres de um nome, separa o nome
	 * do sobrenome
	 * 
	 * @param name nome completo a ser separado
	 * @return vetor com nome da pessoa na posição 0 e o sobrenome na posição 1
	 */
	public static String[] splitName(String name) {

		String[] out = new String[2];
		out[0] = "";
		out[1] = "";

		String[] names = name.split(" ");
		if (names.length == 1) {
			out[0] += names[0];
		} else {
			for (int i = 0; i < names.length; i++) {
				if (i == names.length - 1) {
					out[1] += names[i];
				} else {
					if (i == names.length - 2 && (names[i].equalsIgnoreCase("de") || names[i].equalsIgnoreCase("do")
							|| names[i].equalsIgnoreCase("da") || names[i].equalsIgnoreCase("dos")
							|| names[i].equalsIgnoreCase("das"))) {
						out[1] += names[i] + " ";
					} else
						out[0] += names[i] + " ";
				}
			}
			out[0] = out[0].substring(0, out[0].length() - 1);
		}
		return out;
	}

	// ================== Função de manipulação de acentuação ==================

	/**
	 * Função que remove os acentos das palavras de um texto
	 * 
	 * @param text texto cujas acentos serão removidos
	 * @return texto texto sem acentos
	 */
	public static String removeAccent(String text) {
		StringBuilder out = new StringBuilder();
		String[] ss = text.split("");
		for (String s : ss) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			if (s.length() == 2)
				out.append(s.charAt(0));
			else
				out.append(s);
		}
		return out.toString();
	}

	// ==================== Função de manipulação de unicode ===================

	private static final Pattern UNICODE = Pattern.compile("\\\\u\\p{XDigit}{4}");

	public static String replaceUnicode(String in) {
		Matcher m = UNICODE.matcher(in);

		StringBuffer sb = new StringBuffer(in.length());
		while (m.find()) {
			String carac = m.group();
			carac = String.format("%c", Integer.parseInt(m.group().substring(2), 16));
			m.appendReplacement(sb, Matcher.quoteReplacement(carac));
		}
		m.appendTail(sb);

		return sb.toString();
	}

	private static final int SUB = 0x2080, SUPER = 0x2070;

	public static String getUnicodeSubscript(int n) {
		String s = String.valueOf(n), out = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '-')
				out += (char) (SUB + 0xB);
			else
				out += (char) (c - 0x30 + SUB);
		}
		return out;
	}

	public static String getUnicodeSuperscript(int n) {
		String s = String.valueOf(n), out = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '-')
				out += (char) (SUPER + 0xB);
			else if (c == '2')
				out += (char) 0x00B2;
			else if (c == '3')
				out += (char) 0x00B3;
			else
				out += (char) (c - 0x30 + SUPER);
		}
		return out;
	}

	public static int parseSubSuperScript(String s) {
		String parse = "";
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			if (c == SUPER + 0xB || c == SUB + 0xB)
				parse += "-";
			else if (c == 0x00B2)
				parse += "2";
			else if (c == 0x00B3)
				parse += "3";
			else if (c >= SUB)
				parse += (char) (c + 0x30 - SUB);
			else
				parse += (char) (c + 0x30 - SUPER);
		}
		return Integer.parseInt(parse);
	}

	/**
	 * Função que retorna o par de números do unicode de 16 bits (isto é, até
	 * 0xFFFF) que são obtidos de um unicode de 32 bits. Ver <a
	 * href=https://en.wikipedia.org/wiki/UTF-16#Examples>UTF-16</a>
	 * 
	 * @param l número unicode (e.g., 1D11E, <a
	 *          href=http://www.unicode.org/charts/PDF/U1D100.pdf>clave de Sol</a>)
	 * @return par de número, entre 0x0000 e 0xFFFF (para o exemplo dado: 0xD834 e
	 *         0xDD1E)
	 */
	public int[] getUnicodeUTF32(int l) {
		l -= 0x10000;
		return new int[] { l / 0x400 + 0xD800, l % 0x400 + 0xDC00 };
	}

	// ================ Função de busca de palavras semelhantes ================

	public static float relevancia(String s1, String s2, boolean caseIgnore, boolean diacRmv) {
		if (caseIgnore) {
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();
		}
		if (diacRmv) {
			s1 = removeAccent(s1);
			s2 = removeAccent(s2);
		}
		return relevancia(s1, s2);
	}

	/**
	 * Função que retorna o nível de semelhança entre duas palavras a partir da
	 * distância de Levenshtein
	 * 
	 * @param s1 uma palavra
	 * @param s2 outra palavra
	 * @return proximidade relativa entre as palavras
	 */
	public static float relevancia(String s1, String s2) {
		int maxLength = Math.max(s1.length(), s2.length());
		return (maxLength - levenshteinDistance(s1, s2) + 0f) / maxLength;
	}

	/**
	 * 
	 * @param s1
	 * @param s2
	 * @param caseIgnore
	 * @param diacRmv
	 * @return
	 */
	public static int levenshteinDistance(String s1, String s2, boolean caseIgnore, boolean diacRmv) {
		if (caseIgnore) {
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();
		}
		if (diacRmv) {
			s1 = removeAccent(s1);
			s2 = removeAccent(s2);
		}
		return levenshteinDistance(s1, s2);
	}

	/**
	 * Função que retorna a distância de Levenshtein entre duas sequências de
	 * caracteres
	 * 
	 * @see <a href="en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein
	 *      distance</a>
	 * 
	 * @param s1 uma palavra
	 * @param s2 outra palavra
	 * @return a distância entre as duas sequências
	 */
	public static int levenshteinDistance(String s1, String s2) {

		// for all i and j, d[i,j] will hold the Levenshtein distance between
		// the first i characters of s and the first j characters of t;
		// note that d has (m+1)*(n+1) values

		int[][] d = new int[s1.length() + 1][s2.length() + 1];

		// source prefixes can be transformed into empty string by
		// dropping all characters
		for (int i = 1; i <= s1.length(); i++)
			d[i][0] = i;

		// target prefixes can be reached from empty source prefix
		// by inserting every characters
		for (int j = 1; j <= s2.length(); j++)
			d[0][j] = j;

		for (int j = 1; j <= s2.length(); j++) {
			for (int i = 1; i <= s1.length(); i++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					d[i][j] = d[i - 1][j - 1]; // no operation required
				else
					d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + 1);
			}
		}

		return d[s1.length()][s2.length()];
	}

	/**
	 * constante de proximidade
	 */
	public static float threshold = 0.4f;

	/**
	 * Função que busca em um dicionário (i.e. uma tabela de dispersão) o objeto
	 * mais semelhante a um dado e retorna a chave do mesmo.
	 * 
	 * As palavras são comparadas pelo cálculo da distância de Levenshtein entre
	 * elas. O nível de comparação nível aceitável pode ser estipulado ajustando o
	 * valor da {@link StringUtils#threshold constante de proximidade} .
	 * 
	 * @param string     objeto procurado
	 * @param dictionary tabela com as chaves e objetos
	 * @param caseIgnore se <code>true</code>, ignorar-se-á as diferenças entre
	 *                   maiúsculas e minúsculas
	 * @param diacRmv    se <code>true</code>, remover-se-á os acentos das palavras
	 *                   comparadas
	 * @return a chave do objeto mais semelhante
	 */
	public static <K> K searchDictionary(String string, Map<K, String> dictionary, boolean caseIgnore,
			boolean diacRmv) {

		// preparar string
		if (caseIgnore)
			string = string.toLowerCase();
		if (diacRmv)
			string = removeAccent(string);

		float maxValue = threshold;
		K maxKey = null;

		for (Entry<K, String> e : dictionary.entrySet()) {
			K key = e.getKey();
			String object = e.getValue();

			// preparar string
			if (caseIgnore)
				object = object.toLowerCase();
			if (diacRmv)
				object = removeAccent(object);

			float value = relevancia(string, object);
			if (value > maxValue) {
				maxValue = value;
				maxKey = key;
			}
		}
		return maxKey;
	}

	/**
	 * Função que atribui a cada uma das palavras de uma lista o correpondente do
	 * dicionário
	 * 
	 * @param strings    lista de palavras
	 * @param dic        tabela de associação entre a chave e o valor da palavra
	 * @param caseIgnore se <code>true</code>, ignorar-se-á as diferenças entre
	 *                   maiúsculas e minúsculas
	 * @param diacRmv    se <code>true</code>, remover-se-á os acentos das palavras
	 *                   comparadas
	 * @return lista ordenada das chaves associadas a cada uma das palavras da lista
	 */
	public static <K> List<K> associateDictionary(List<String> strings, Map<K, String> dic, boolean caseIgnore,
			boolean diacRmv) {
		List<K> out = new ArrayList<K>(strings.size());
		HashSet<Integer> already = new HashSet<>();

		// cada palavra do dicionário pode (nomes verdadeiros) podem pertencer a alguém
		// da lista. Quem na lista tiver mais peso leva a palavra
		for (Entry<K, String> e : dic.entrySet()) {
			K key = e.getKey();
			String value = e.getValue();

			float maxValue = -1f;
			int pos = -1;

			for (int i = 0; i < strings.size(); i++) {
				if (!already.contains(i)) {
					float relevancia = relevancia(strings.get(i), value);
					if (relevancia > maxValue) {
						maxValue = relevancia;
						pos = i;
					}
				}
			}

			out.add(key);
			already.add(pos);
		}
		return out;
	}

	public static Object getValue(String value, Class<?> classe) {
		if (classe.equals(Integer.class)) {
			return Integer.parseInt(value);
		} else if (classe.equals(Float.class)) {
			return Float.parseFloat(value);
		} else if (classe.equals(Double.class)) {
			return Double.parseDouble(value);
		} else if (classe.equals(Boolean.class)) {
			return Boolean.parseBoolean(value);
		} else if (classe.equals(String.class)) {
			return value;
		} else if (classe.equals(Color.class)) {
			return ColorUtils.stringJava2Color(value);
		} else if (classe.equals(Calendar.class)) {
			return TimeUtils.string2Calendar(value);
		} else {
			System.err.println("Não identifiquei que classe é");
			return null;
		}
	}

	public static String bytesArray2String(byte[] array) {
		String out = "";
		for (int i = array.length - 1; i >= 0; i--) {
			byte b = array[i];
			for (int j = 7; j >= 0; j--)
				out += ((b & (1 << j)) > 0) ? "1" : "0";
			out += " ";
		}
		return out;
	}

	public static int[] splitInteger(String vector, String regex) {
		String[] s = vector.split(regex);
		int[] out = new int[s.length];
		for (int i = 0; i < out.length; i++)
			out[i] = Integer.parseInt(s[i]);
		return out;
	}

	public static int string2int(String s) {
		int out = 0;
		for (int i = 0; i < Math.min(4, s.length()); i++)
			out += (s.charAt(i) & 0x00FF) << (8 * i);
		return out;
	}

	public static String int2string(int j) {
		String out = "";
		for (int i = 0; i < 4; i++)
			out += (char) ((j >> (8 * i)) & 0x000000FF);
		return out;
	}

	public static String float2string(float j) {
		byte[] bs = new byte[4];
		ByteBuffer.wrap(bs).order(ByteOrder.LITTLE_ENDIAN).putFloat(j);

		String out = "";
		for (int i = 0; i < 4; i++)
			out += (char) (bs[i]);
		return out;
	}

	public static float string2float(String s) {
		byte[] out = new byte[4];
		for (int i = 0; i < Math.min(4, s.length()); i++)
			out[i] = (byte) (s.charAt(i) & 0x00FF);
		return ByteBuffer.wrap(out).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}

	/**
	 * Função que converte um vetor de sequências de caracteres em um vetor de
	 * inteiros
	 * 
	 * @param in vetor de sequências de caracteres
	 * @return vetor de inteiros
	 */
	public static int[] parseInts(String[] in) {
		if (in.length == 1 ? "".equals(in[0]) : false)
			return new int[0];
		int[] out = new int[in.length];
		for (int i = 0; i < in.length; i++)
			out[i] = Integer.parseInt(in[i].trim());
		return out;
	}

	/**
	 * Função que converte um vetor de sequências de caracteres em um vetor de
	 * número decimais
	 * 
	 * @param in vetor de sequências de caracteres
	 * @return vetor de número decimais
	 */
	public static double[] parseDoubles(String[] in) {
		double[] out = new double[in.length];
		for (int i = 0; i < in.length; i++)
			out[i] = Double.parseDouble(in[i]);
		return out;
	}

	/**
	 * Função que converte um vetor de sequências de caracteres em um vetor de
	 * número decimais
	 * 
	 * @param in vetor de sequências de caracteres
	 * @return vetor de número decimais
	 */
	public static float[] parseFloats(String[] in) {
		float[] out = new float[in.length];
		for (int i = 0; i < in.length; i++)
			if (!"".equals(in[i]))
				out[i] = Float.parseFloat(in[i]);
		return out;
	}

	public static double getDouble(Object obj) {
		double value = Double.NaN;
		if (obj != null) {
			if (obj instanceof Double)
				value = ((Double) obj).doubleValue();
			else if (obj instanceof String) {
				try {
					value = Double.parseDouble(((String) obj).replace(',', '.'));
				} catch (NumberFormatException e) {
				}
			}
		}
		return value;
	}

	public static String toHex(byte[] bs) {
		String out = "";
		for (int i = 0; i < bs.length; i++)
			out += String.format("%02x", bs[i]);
		return out;
	}

	/**
	 * Função que converte um vetor de números inteiros em uma sequência de
	 * caracteres exprimindo um número decimal
	 * 
	 * @param ss vetor de inteiros
	 * @return sequência de caracteres na forma [h{4}]+
	 */
	public static String toHex(short[] ss) {
		String out = "";
		for (int i = 0; i < ss.length; i++)
			out += String.format("%04x", ss[i]);
		return out;
	}

	public static String toHex(int[] is) {
		String out = "";
		for (int i = 0; i < is.length; i++)
			out += String.format("%08x", is[i]);
		return out;
	}

	public static byte[] fromHex(String s) {
		byte[] out = new byte[s.length() / 2];
		for (int i = 0; i < out.length; i++)
			out[i] = (byte) Short.parseShort(String.format("%c%c", s.charAt(2 * i), s.charAt(2 * i + 1)), 16);
		return out;
	}

	public static String int2binary(int i) {
		String out = "";

		int displayMask = 1 << 31;

		for (int b = 1; b <= 32; b++) {
			out += (i & displayMask) == 0 ? "0" : "1";
			i <<= 1;
			if (b % 8 == 0)
				out += " ";
		}

		return out;
	}

	private static final Pattern NUM_PAT = Pattern.compile("(-|)\\d+([,.]\\d+|)");

	/**
	 * Função que varre uma sequência de caracteres em busca de padrões de um número
	 * inteiro ou decimal
	 * 
	 * @param str sequência de caracteres contendo um número
	 * @return primeiro número encontrado
	 */
	public static Number parseNumber(String str) {
		if (str == null)
			return null;
		Matcher mat = NUM_PAT.matcher(str);
		if (mat.find())
			return string2number(mat.group());
		else
			return null;
	}

	/**
	 * Função que varre uma sequência de caracteres em busca de padrões de números
	 * inteiros e decimais
	 * 
	 * @param s sequência de caracteres contendo os números
	 * @return vetor de números encontrados
	 */
	public static Number[] parseNumbers(String s) {
		if (s == null)
			return null;
		Matcher m = NUM_PAT.matcher(s);
		List<Number> out = new LinkedList<>();
		while (m.find())
			out.add(string2number(m.group()));
		return out.toArray(new Number[out.size()]);
	}

	private static Number string2number(String s) {
		try {
			if (s.contains("."))
				return Float.parseFloat(s);
			else if (s.contains(","))
				return Float.parseFloat(s.replace(',', '.'));
			else
				return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Função que reparte uma sequência de caracteres nas posições indicados
	 * 
	 * @param s   sequência de caracteres
	 * @param pos vetor de inteiros com as posições indicados
	 * @return vetor de sequência de caracteres obtidos pela divisão da sequência
	 *         dada nas posições indicadas
	 */
	public static String[] split(String s, int... pos) {
		String[] out = new String[pos.length - 1];
		boolean b1 = s.length() >= pos[0];
		for (int i = 0; i < out.length; i++) {
			boolean b2 = s.length() >= pos[i + 1];
			out[i] = (b2 ? s.substring(pos[i], pos[i + 1]) : (b1 ? s.substring(pos[i]) : "")).trim();
			b1 = b2;
		}
		return out;
	}

	private final static TreeMap<Integer, String> RN = new TreeMap<Integer, String>();

	static {
		RN.put(1000, "M");
		RN.put(900, "CM");
		RN.put(500, "D");
		RN.put(400, "CD");
		RN.put(100, "C");
		RN.put(90, "XC");
		RN.put(50, "L");
		RN.put(40, "XL");
		RN.put(10, "X");
		RN.put(9, "IX");
		RN.put(5, "V");
		RN.put(4, "IV");
		RN.put(1, "I");
	}

	/**
	 * Função que faz a conversão de um número inteiro em sua representação em
	 * algarismos romanos
	 * 
	 * @param n número inteiro
	 * @return representação do número inteiro em algarismos romanos
	 */
	public final static String toRoman(int n) {
		int l = RN.floorKey(n);
		if (n == l)
			return RN.get(n);
		return RN.get(l) + toRoman(n - l);
	}

	// ============= Funções de reconhecimento de padrões =============

	/**
	 * Função que retorna um determinado conteúdo, definido por um dado padrão.
	 * 
	 * @param str   string que contem o conteúdo procurado
	 * @param start delimitador do string (índice inicial)
	 * @param end   delimitador do string (índice final)
	 * @param index índice do conteúdo
	 * @param pat   padrão que identifica o conteúdo
	 * @return conteúdo procurado
	 */
	public static String getContent(String str, int start, int end, int index, Pattern pat) {
		Matcher m = pat.matcher(str);
		m.region(start, end);

		int i = 0;
		String out = null;
		while (m.find()) {
			if (i == index) {
				out = m.group();
				break;
			} else
				i++;
		}
		return out;
	}

	/**
	 * Função que retorna uma lista de conteúdos que obedecem a um dado padrão.
	 * 
	 * @param str   string que contem o conteúdo procurado
	 * @param start delimitador do string (índice inicial)
	 * @param end   delimitador do string (índice final)
	 * @param pat   padrão que identifica o conteúdo
	 * @return vetor com os conteúdos procurados
	 */
	public static String[] getContent(String str, int start, int end, Pattern pat) {
		Matcher m = pat.matcher(str);
		m.region(start, end);

		LinkedList<String> out = new LinkedList<String>();
		while (m.find())
			out.add(m.group());

		return out.toArray(new String[out.size()]);
	}

	/**
	 * Função que retorna os índices dos limites da região de um string delimitado
	 * por determinados padrões.
	 * 
	 * @param str    string que contem a região procurada
	 * @param start  delimitador do string (índice inicial)
	 * @param end    delimitador do string (índice final)
	 * @param index  índice da região
	 * @param patBeg padrão que identifica o início da região
	 * @param patEnd padrão que identifica o fim da região
	 * @return vetor com duas posições, a primeira para o início da região
	 *         encontrada, a segunda para o fim. Se não for encontrada a região,
	 *         retorna-se <code>null</code>
	 */
	public static int[] getLimits(String str, int start, int end, int index, Pattern patBeg, Pattern patEnd) {
		// início do setor
		Matcher m = patBeg.matcher(str);
		m.region(start, end);

		start = -1;
		int i = 0;
		while (m.find()) {
			if (i == index) {
				start = m.start();
				break;
			} else {
				i++;
			}
		}
		if (start == -1)
			return null;

		// fim do setor
		m = patEnd.matcher(str);
		if (m.find(start)) {
			end = m.start();
		}
		return new int[] { start, end };
	}

	/**
	 * Função que faz com que um padrão (acompanhado de suas exceções) seja
	 * modificado para aceitar e rejeitar as mesmas entradas, alterando-se a lista
	 * de exceções (que eventualmente, com a escolha do novo padrão, pode reduzir
	 * sensivelmente)
	 * 
	 * @param list           lista de referência
	 * @param currentPattern padrão atualmente usado
	 * @param exceptionsP    atual lista de exceções positivas
	 * @param exceptionsN    atual lista de exceções negativas
	 * @param newPattern     novo padrão sugerido
	 * @param exceptionsP0   nova lista de exceções positivas
	 * @param exceptionsN0   nova lista de exceções negativas
	 */
	public static void generateExceptionList(Collection<String> list, String currentPattern,
			Collection<String> exceptionsP, Collection<String> exceptionsN, String newPattern,
			Collection<String> exceptionsP0, Collection<String> exceptionsN0) {

		List<String> currentFilter = new LinkedList<>();
		Pattern p = Pattern.compile(currentPattern);
		for (String obj : list) {
			Matcher m = p.matcher(obj);
			if (m.find()) {
				if (!exceptionsP.contains(obj))
					currentFilter.add(obj);
			} else {
				if (exceptionsN.contains(obj))
					currentFilter.add(obj);
			}
		}

		p = Pattern.compile(newPattern);
		for (String obj : list) {
			Matcher m = p.matcher(obj);
			if (m.find()) {
				if (!currentFilter.contains(obj)) {
					exceptionsP0.add(obj);
				}
			} else {
				if (currentFilter.contains(obj)) {
					exceptionsN0.add(obj);
				}
			}
		}
	}

	/**
	 * Função que retorna o padrão de uma série de caracteres de tamanho fixo
	 * (fixed-length)
	 * 
	 * @param ss
	 * @param part partições
	 * @return
	 */
	public static String getPatternFL(String[] ss, int... part) {
		if (part.length > 0) {
			StringBuilder sb = new StringBuilder();
			int pos = 0;
			String[] ps = new String[ss.length];
			for (int i = 0; i < part.length; i++) {
				for (int j = 0; j < ss.length; j++)
					ps[j] = ss[j].substring(pos, part[i]);
				String p = getPatternNFL(ps); // TODO
				sb.append(p);
				if (i < part.length - 1)
					sb.append("\r\n");
				pos = part[i];
			}
			return sb.toString();
		} else
			return getPatternNFL(ss); // TODO
	}

	public static String getPatternNFL(String... ss) {
		return toPattern(getPatternNFLnode(ss));
	}

	public static DefaultMutableTreeNode getPatternNFLnode(String... list) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
		for (int i = 0; i < list.length; i++) {
			String s = list[i];
			DefaultMutableTreeNode parent = root;
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				DefaultMutableTreeNode d = findNodeLetter(parent, c);
				if (d == null) {
					d = new DefaultMutableTreeNode(c);
					parent.add(d);
				}
				parent = d;
			}
		}

		// elementos em série: agrupar
		while (true) {
			boolean flag = false;
			Stack<DefaultMutableTreeNode> stack = new Stack<>();
			stack.push(root);

			while (stack.size() > 0) {
				DefaultMutableTreeNode d = stack.pop();

				while (d.getChildCount() == 1 && d.getLevel() > 0) {
					DefaultMutableTreeNode dc = (DefaultMutableTreeNode) d.getChildAt(0);
					d.setUserObject(d.getUserObject() + dc.getUserObject().toString());
					d.remove(dc);
					while (dc.getChildCount() > 0)
						d.add((DefaultMutableTreeNode) dc.getChildAt(0));
					flag = true;
				}

				for (int i = 0; i < d.getChildCount(); i++)
					stack.push((DefaultMutableTreeNode) d.getChildAt(i));
			}

			if (!flag) // se não foi feita nenhum operação, árvore enxuta
				break;
		}

		// elementos em paralelo: agrupar, se possível...
		while (true) {
			boolean flag = false;
			Stack<DefaultMutableTreeNode> stack = new Stack<>();
			stack.push(root);

			while (stack.size() > 0) {
				DefaultMutableTreeNode d = stack.pop();

				if (d.getDepth() == 1) {
					if (d.getChildCount() > 1) {
						String[] pts = new String[d.getChildCount()];
						for (int i = 0; i < d.getChildCount(); i++)
							pts[i] = ((DefaultMutableTreeNode) d.getChildAt(i)).getUserObject().toString();
						String s = mergePatterns(pts);
						if (s != null) {
							d.setUserObject(s);
							d.removeAllChildren();
						}
					}
				}

				for (int i = 0; i < d.getChildCount(); i++)
					stack.push((DefaultMutableTreeNode) d.getChildAt(i));
			}

			if (!flag) // se não foi feita nenhum operação, árvore enxuta
				break;
		}

		return root;
	}

	private static DefaultMutableTreeNode findNodeLetter(DefaultMutableTreeNode node, char c) {
		for (int i = 0; i < node.getChildCount(); i++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
			char c0 = (char) child.getUserObject();
			if (c0 == c)
				return child;
		}
		return null;
	}

	/**
	 * Função que fusiona dois padrões ou mais padrões
	 * 
	 * @param patterns
	 * @return
	 */
	private static String mergePatterns(String... patterns) {
		String out = patterns[0];
		for (int i = 1; i < patterns.length; i++)
			out = mergePatterns(out, i > 1, patterns[i], false);
		return out;
	}

	/**
	 * Função que fusiona dois padrões
	 * 
	 * @param p1  segunda palavra (ou padrão)
	 * @param pt1 <code>false</code> se p1 for uma palavra, <code>true</code> se for
	 *            um padrão
	 * @param p2  segunda palavra (ou padrão)
	 * @param pt2 <code>false</code> se p2 for uma palavra, <code>true</code> se for
	 *            um padrão
	 * @return novo padrão
	 */
	private static String mergePatterns(String p1, boolean pt1, String p2, boolean pt2) {
		if (!pt1 && !pt2) {
			float max = Math.max(p1.length(), p2.length());
			float dist = StringUtils.levenshteinDistance(p1, p2) / max;
			if (dist < StringUtils.threshold) { // é possível fazer o merge

				// achar pontos onde os padrões tem a mesma letra
				LinkedList<int[]> samePos = new LinkedList<>();
				int i0 = 0;
				for (int i1 = 0; i1 < p1.length(); i1++) {
					char c1 = p1.charAt(i1);
					for (int i2 = i0; i2 < p2.length(); i2++) {
						char c2 = p2.charAt(i2);
						if (c1 == c2) {
							samePos.add(new int[] { i1, i2 });
							i0 = i2 + 1;
							break;
						}
					}
				}

				StringBuilder out = new StringBuilder();

				Iterator<int[]> it = samePos.iterator();
				int[] ds0 = it.next();

				// prefixos
				String pre1 = p1.substring(0, ds0[0]);
				String pre2 = p2.substring(0, ds0[1]);
				if (pre1.length() + pre2.length() > 0)
					out.append("(" + pre1 + "|" + pre2 + ")");
				out.append(p1.charAt(ds0[0]));
				ds0[0]++;
				ds0[1]++;

				// partes em comum
				while (it.hasNext()) {
					int[] ds = it.next();
					pre1 = p1.substring(ds0[0], ds[0]);
					pre2 = p2.substring(ds0[1], ds[1]);
					if (pre1.length() + pre2.length() > 0)
						out.append("(" + pre1 + "|" + pre2 + ")");
					out.append(p1.charAt(ds[0]));
					ds0 = ds;
					ds0[0]++;
					ds0[1]++;
				}

				// sufixos
				pre1 = p1.substring(ds0[0]);
				pre2 = p2.substring(ds0[1]);
				if (pre1.length() + pre2.length() > 0)
					out.append("(" + pre1 + "|" + pre2 + ")");

				return out.toString();
			} else
				return null;
		} else // TODO fundir três padrões (recursividade - o problema é que a primeira
				// iteração é sobre palavras, a segunda sobre uma palavra e um padrão
			return null;
	}

	public static String toPattern(DefaultMutableTreeNode node) {
		StringBuilder sb = new StringBuilder();

		Object obj = node.getUserObject();
		if (obj != null)
			sb.append(obj.toString());

		if (node.getChildCount() > 0) {
			sb.append("(");
			for (int i = 0; i < node.getChildCount(); i++) {
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
				sb.append("(" + toPattern(child) + ")|");
			}
			sb.setLength(sb.length() - 1);
			sb.append(")");
		}
		return sb.toString();
	}

}
