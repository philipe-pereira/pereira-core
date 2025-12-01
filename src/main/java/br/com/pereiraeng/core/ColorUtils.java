package br.com.pereiraeng.core;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

	public static Color main = Color.BLACK;

	private static final Color[] COLORS = new Color[] { Color.RED, Color.GREEN, Color.BLUE, new Color(255, 165, 0),
			Color.YELLOW, new Color(235, 41, 119), new Color(165, 42, 42), new Color(128, 128, 0), Color.GRAY,
			new Color(0, 128, 128), new Color(189, 130, 255), new Color(169, 169, 169), new Color(190, 128, 192),
			new Color(0, 128, 0), new Color(238, 130, 238), new Color(211, 211, 211), Color.CYAN, Color.PINK };

	public static Color getColor(int index) {
		if (index >= COLORS.length)
			return main;
		return COLORS[index];
	}

	/**
	 * Função que retorna a cor inversa de uma dada cor
	 * 
	 * @param c cor
	 * @return cor inversa
	 */
	public static Color inverse(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	/**
	 * Função que retorna um inteiro que representa a cor. É a função inversa de
	 * {@link #rgb2color(int)}.
	 * 
	 * @param c cor
	 * @return inteiro correspondente cujo valor varia entre 0 (preto) e 16777215
	 *         (branco, 0xFFFFFF)
	 */
	public static int color2rgb(Color c) {
		return 65536 * c.getRed() + 256 * c.getGreen() + c.getBlue();
	}

	/**
	 * Função que retorna a cor representada um por número inteiro. É a função
	 * inversa de {@link #color2rgb(Color)}.
	 * 
	 * @param rgb inteiro entre 0 (preto) e 16777215 (branco, 0xFFFFFF)
	 * @return cor correspondente
	 */
	public static Color rgb2color(int rgb) {
		int r = rgb / 65536;
		int gb = rgb % 65536;
		return new Color(r, gb / 256, gb % 256);
	}

	/**
	 * Função que retorna uma cor que é uma superposição de duas outras
	 * 
	 * @param t      número decimal entre 0 e 1 (se for 1, a cor 2 prevalece, se for
	 *               0, a cor 1)
	 * @param color1 cor 1
	 * @param color2 cor 2
	 * @return superposição das cores
	 */
	public static Color getIntermedColor(double t, Color color1, Color color2) {
		return new Color((int) (t * (color2.getRed() - color1.getRed()) + color1.getRed()),
				(int) (t * (color2.getGreen() - color1.getGreen()) + color1.getGreen()),
				(int) (t * (color2.getBlue() - color1.getBlue()) + color1.getBlue()));
	}

	/**
	 * Função que retorna uma das cores do espectro de maneira cíclica (as cores
	 * estão ordenadas conforme a sequência: vermelho (0), amarelo (1/6), verde
	 * (1/3), ciano (1/2), azul (2/3), magenta (5/6), vermelho novamente (1))
	 * 
	 * @param f número decimal, sendo que será feita o resto da divisão por 1 para
	 *          se obter um número entre 0 e 1 (ambos números retornam a cor
	 *          vermelha, fechando o ciclo)
	 * @return cor do espectro
	 */
	public static Color getSpectre(float f) {
		f = (f % 1f) * 6f;
		float posc = f % 1f;
		switch ((int) f) {
		case 0: // do vermelho para o amarelo (passando pelo laranja)
			return new Color(1f, posc, 0f);
		case 1: // do amarelo para o verde
			return new Color(1f - posc, 1f, 0f);
		case 2: // do verde para o ciano
			return new Color(0f, 1f, posc);
		case 3: // do ciano para o azul
			return new Color(0f, 1f - posc, 1f);
		case 4: // do azul para o magenta (passando pelo violeta)
			return new Color(posc, 0f, 1f);
		case 5: // do magenta para o vermelho
			return new Color(1f, 0f, 1f - posc);
		}
		return null;
	}

	/**
	 * Função que retorna o objeto {@link Color} a partir da sequência de caracteres
	 * obtida pelo método {@link Color#toString()}.
	 * 
	 * @param toString sequência de caracteres contendo informações da cor
	 * @return objeto <code>Color</code> correspondente
	 */
	public static Color stringJava2Color(String toString) {
		if (!toString.equals("null")) {
			int r = -1, g = -1, b = -1;

			String s = toString.substring(14);
			Pattern p;
			Matcher m;

			p = Pattern.compile("r=\\d+");
			m = p.matcher(s);
			if (m.find())
				r = Integer.parseInt(m.group().substring(2));

			p = Pattern.compile("g=\\d+");
			m = p.matcher(s);
			if (m.find())
				g = Integer.parseInt(m.group().substring(2));

			p = Pattern.compile("b=\\d+");
			m = p.matcher(s);
			if (m.find())
				b = Integer.parseInt(m.group().substring(2));

			return new Color(r, g, b);
		} else
			return null;
	}

	/**
	 * Função que converte uma sequência de caracteres com os valores de R, G e B na
	 * cor correspondente. É a função inversa de {@link #color2html(Color)}.
	 * 
	 * @param s sequência de caracteres na forma 'rgb(XXX,YYY,ZZZ)' onde XXX, YYY e
	 *          ZZZ são números inteiros de 0 a 255
	 * @return cor correspondente
	 */
	public static Color html2color(String s) {
		if (s != null ? s.startsWith("rgb") : false) {
			// remove-se as letras 'rgb' e o parênteses inicial (4) e o parênteses final
			// (length() - 1)
			int[] rgb = StringUtils.parseInts(s.substring(4, s.length() - 1).split(","));
			return new Color(rgb[0], rgb[1], rgb[2]);
		} else
			return null;
	}

	/**
	 * Função que converte uma cor na sequência de caracteres com os valores de R, G
	 * e B correspondente. É a função inversa de {@link #html2color(String)}.
	 * 
	 * @param c cor
	 * @return sequência de caracteres correspondente na forma 'rgb(XXX,YYY,ZZZ)'
	 *         onde XXX, YYY e ZZZ são números inteiros de 0 a 255
	 */
	public static String color2html(Color c) {
		return String.format("rgb(%d,%d,%d)", c.getRed(), c.getGreen(), c.getBlue());
	}
}
