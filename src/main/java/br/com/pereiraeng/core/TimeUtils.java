package br.com.pereiraeng.core;

import java.awt.Component;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class TimeUtils {

	public static final String ISO8601_STR = "yyyy-MM-dd'T'HH:mm:ss";

	public static final SimpleDateFormat ISO8601 = new SimpleDateFormat(ISO8601_STR);

	public static final String ISO8601_SSS_STR = ISO8601_STR + ".SSS";

	public static final String ISO_8601_FORMATTER = "%tFT%<tR";

	public static final String ISO_8601_FORMATTER_SS = "%tFT%<tT";

	// ==================== Funções de expressões temporais ====================

	/**
	 * Função que retorna a expressão que diz a diferença de tempo entre dois
	 * momentos distintos. A expressão contem no máximo duas unidades de tempo,
	 * sendo aquelas que melhor descrevem a diferença.
	 * 
	 * @param reference momento que serve de referência para a comparação
	 * @param time      momento a ser comparado com a referência
	 * @return expressão que indica a diferença
	 */
	public static String getDifference(Calendar reference, Calendar time) {
		if (reference == null || time == null)
			return null;

		StringBuilder out = new StringBuilder();

		// i en minutes
		int i = (int) ((time.getTimeInMillis() - reference.getTimeInMillis()) / 60000);

		if (i < 0)
			out.append(LocaleConfig.getString("from") + " ");
		else
			out.append(LocaleConfig.getString("to") + " ");

		i = Math.abs(i);

		if (i < 60) {
			out.append(String.format("%d %s%s", i, LocaleConfig.getString("minute"), i > 1 ? "s" : ""));
			return out.toString();
		}

		// i en heures
		int r = i % 60;
		i /= 60;

		if (i < 24) {
			out.append(String.format("%d %s%s %s %d %s%s", i, LocaleConfig.getString("hour"), i > 1 ? "s" : "",
					LocaleConfig.getString("and"), r, LocaleConfig.getString("minute"), r > 1 ? "s" : ""));
			return out.toString();
		}

		// i en jours
		r = i % 24;
		i /= 24;

		if (i < 30) {
			out.append(String.format("%d %s%s %s %d %s%s", i, LocaleConfig.getString("day"), i > 1 ? "s" : "",
					LocaleConfig.getString("and"), r, LocaleConfig.getString("hour"), r > 1 ? "s" : ""));
			return out.toString();
		}

		// i en mois
		r = i % 30;
		i /= 30;

		if (i < 12) {
			out.append(String.format("%d %s%s %s %d %s%s", i, LocaleConfig.getString("month"), i > 1 ? "s" : "",
					LocaleConfig.getString("and"), r, LocaleConfig.getString("day"), r > 1 ? "s" : ""));
			return out.toString();
		}

		// i en années
		r = i % 12;
		i /= 12;

		out.append(String.format("%d %s%s %s %d %s%s", i, LocaleConfig.getString("month"), i > 1 ? "s" : "",
				LocaleConfig.getString("and"), r, LocaleConfig.getString("year"), r > 1 ? "s" : ""));
		return out.toString();
	}

	public static boolean isSameHour(Calendar c1, Calendar c2) {
		if (c1 == null ^ c2 == null)
			return false;
		return (c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
				&& (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
				&& (c1.get(Calendar.HOUR_OF_DAY) == c2.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * Função que avalia se dois objetos {@link Calendar} apontam para o mesmo dia
	 * (ignorando-se a hora, minuto e segundos)
	 * 
	 * @param c1 uma data
	 * @param c2 outra data
	 * @return <code>true</code> se for o mesmo dia, <code>false</code> senão
	 */
	public static boolean isSameDay(Calendar c1, Calendar c2) {
		if (c1 == null ^ c2 == null)
			return false;
		return (c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
				&& (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR));
	}

	/**
	 * 
	 * @param c1 uma data
	 * @param c2 outra data
	 * @return <code>true</code> se for na mesma semana, <code>false</code> senão
	 */
	public static boolean isSameWeek(Calendar c1, Calendar c2) {
		if (c1 == null ^ c2 == null)
			return false;
		return c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR);
	}

	public static boolean isSameDay(Date d1, Date d2) {
		if (d1 == null ^ d2 == null)
			return false;
		return isSameDay(date2Calendar(d1), date2Calendar(d2));
	}

	public static boolean isToday(Calendar c) {
		return isSameDay(c, Calendar.getInstance());
	}

	/**
	 * Função que avalia se dois objetos {@link Calendar} apontam para o mesmo dia
	 * (ignorando-se a mês, dia, hora, minuto e segundos)
	 * 
	 * @param c1 uma data
	 * @param c2 outra data
	 * @return <code>true</code> se for o mesmo ano, <code>false</code> senão
	 */
	public static boolean isSameYear(Calendar c1, Calendar c2) {
		if (c1 == null ^ c2 == null)
			return false;
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}

	/**
	 * Função que retorna o número total de dias de um mês
	 * 
	 * @param year  número do ano
	 * @param month numéro do mês, e.g. Janeiro é 0 (assim como a numeração da
	 *              classe {@link Calendar}).
	 * @return número de dias do mês considerado
	 */
	public static int daysInMonth(int year, int month) {
		Calendar c = new GregorianCalendar(year, month, 1);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Função que retorna a numeração de um dado dia de um dado mês contado a partir
	 * do primeiro de janeiro
	 * 
	 * @param month numéro do mês, e.g. Janeiro é 0 (assim como a numeração da
	 *              classe {@link Calendar}).
	 * @param day   dia do mês
	 * @return dia do ano, contados a partir do primeiro de janeiro (considerado o
	 *         dia 1)
	 */
	public static int getDayOfTheYear(int month, int day) {
		Calendar c = new GregorianCalendar(2014, month, day);
		return c.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Função que retorna o objeto <code>Calendar</code> correspondente a um
	 * deternimado horário
	 * 
	 * @param hour           valor das horas
	 * @param minute         valor dos minutos
	 * @param second         valor dos segundos
	 * @param timeZone       hora local
	 * @param dayLightSaving <code>true</code> para o horário de verão,
	 *                       <code>false</code> senão
	 * @return objeto do tipo <code>Calendar</code> com as informações do horário
	 */
	public static Calendar getCalendar(int hour, int minute, int second, TimeZone timeZone, boolean dayLightSaving) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		int dl = dayLightSaving ? 3600000 : 0;
		c.setTimeZone(timeZone);
		c.set(Calendar.ZONE_OFFSET, timeZone.getRawOffset() + dl);
		c.set(Calendar.DST_OFFSET, dl);

		return c;
	}

	/**
	 * Função que determina o fuso horário de uma objeto {@link Calendar}, porém
	 * alterando o instante de tempo que ele representa (o método
	 * {@link Calendar#setTimeZone(TimeZone)} altera o fuso horário sem alterar o
	 * instante de tempo correspondente, fazendo para isso os ajustes necessários).
	 * <p>
	 * 
	 * Exemplo: se a hora fora 20h00 GMT+3 e o fuso horário a ser estabelecido é o
	 * GMT+0, esta função retorna 20h00 GMT+0. Se a função <code>setTimeZone</code>
	 * fosse usada, o resultado seria 23h00 GMT+0 (ou seja, o mesmo instante de
	 * tempo 20h00 GMT+3, porém em um fuso diferente).
	 * 
	 * 
	 * @param c  objeto {@link Calendar}
	 * @param tz objeto que representa o {@link TimeZone fuso horário}
	 * @return novo instante de tempo (diferente do anterior)
	 */
	public static Calendar setTimeZone(Calendar c, TimeZone tz) {
		Calendar newTime = Calendar.getInstance(tz);
		newTime.set(Calendar.YEAR, c.get(Calendar.YEAR));
		newTime.set(Calendar.MONTH, c.get(Calendar.MONTH));
		newTime.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		newTime.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
		newTime.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
		newTime.set(Calendar.SECOND, c.get(Calendar.SECOND));
		newTime.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
		return newTime;
	}

	/**
	 * Função que compara duas datas <strong>levando-se em conta somente o horário
	 * do dia</strong>
	 * 
	 * @param c1 objeto {@link Calendar} representando o primeiro horário
	 * @param c2 objeto {@link Calendar} representando o segundo horário
	 * @return <code>true</code> se a hora do primeiro horário vier antes da do
	 *         segundo
	 */
	public static boolean timeIsBefore(Calendar c1, Calendar c2) {
		return getMillisecondsFromMidnight(c1) < getMillisecondsFromMidnight(c2);
	}

	/**
	 * Função que compara duas datas <strong>levando-se em conta somente o horário
	 * do dia</strong>
	 * 
	 * @param c1 objeto {@link Date} representando o primeiro horário
	 * @param c2 objeto {@link Date} representando o segundo horário
	 * @return <code>true</code> se a hora do primeiro horário vier antes da do
	 *         segundo
	 */
	public static boolean timeIsBefore(Date c1, Date c2) {
		return timeIsBefore(date2Calendar(c1), date2Calendar(c2));
	}

	/**
	 * Função que retorna o total de milissegundos decorridos desde a meia-noite do
	 * dia considerado
	 * 
	 * @param c objeto {@link Calendar} representando o horário do dia
	 * @return <code>long</code> com o total de milissegundos do dia
	 */
	public static long getMillisecondsFromMidnight(Calendar c) {
		return ExtendedMath.mod(c.getTimeInMillis() + c.get(Calendar.DST_OFFSET) + c.get(Calendar.ZONE_OFFSET),
				86400000L);
	}

	public static String[] getDaysOfTheWeek() {
		String[] time = new String[7];

		for (int i = 0; i < 7; i++) {
			Calendar c = Calendar.getInstance();
			c.clear();
			c.set(Calendar.DAY_OF_WEEK, i + 1);
			time[i] = String.format("%tA", c);
		}

		return time;
	}

	public static String[] getMonths() {
		String[] time = new String[12];

		for (int i = 0; i < 12; i++) {
			Calendar c = Calendar.getInstance();
			c.clear();
			c.set(Calendar.MONTH, i);
			time[i] = String.format("%tB", c);
		}

		return time;
	}

	/**
	 * Função que converte um objeto de tempo do tipo {@link Date} em um tipo
	 * {@link Calendar}
	 * 
	 * @param date objeto {@link Date Date}
	 * @return objeto {@link Calendar Calendar}
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		return c;
	}

	/**
	 * Função que retorna um novo objeto {@link Calendar}, porém com o dia truncado
	 * para o primeiro (data e hora 00:00)
	 * 
	 * @param c objeto com as informações do mês e ano
	 * @return objeto que representa a data do dia primeiro do mês e ano da entrada
	 */
	public static Calendar truncateMonth(Calendar c) {
		return new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
	}

	/**
	 * Função que retorna os limites superior e inferior com uma dada distância para
	 * uma data
	 * 
	 * @param c         data a ser extendida
	 * @param threshold raio do intervalo
	 * @return limites do intervalo
	 */
	public static Calendar[] getInfSup(Calendar c, long threshold) {
		Calendar[] out = new Calendar[2];
		int seg = (int) (threshold / 1000L);

		// limite inferior
		out[0] = (Calendar) c.clone();
		out[0].add(Calendar.SECOND, -seg);

		// superior
		out[1] = (Calendar) c.clone();
		out[1].add(Calendar.SECOND, seg);
		return out;
	}

	/**
	 * Função que retorna os limites de um período de tempo que compreende um dado
	 * dia
	 * 
	 * @param c instante de tempo de um dado dia
	 * @return um vetor com duas posições com o começo e final do dia
	 */
	public static Calendar[] getDayRange(Calendar c) {
		return getRange(c, Calendar.DAY_OF_MONTH);
	}

	/**
	 * Função que retorna os limites de um período de tempo que compreende um dado
	 * mês
	 * 
	 * @param c um instante de tempo qualquer do mês
	 * @return um vetor com duas posições com o começo e final do mês
	 */
	public static Calendar[] getMonthRange(Calendar c) {
		return getRange(c, Calendar.MONTH);
	}

	/**
	 * Função que retorna os limites de um período de tempo que compreende um dado
	 * ano
	 * 
	 * @param c um instante de tempo qualquer do ano
	 * @return um vetor com duas posições com o começo e final do ano
	 */
	public static Calendar[] getYearRange(Calendar c) {
		return getRange(c, Calendar.YEAR);
	}

	/**
	 * Função que retorna o período de tempo que envolve uma dada data
	 * 
	 * @param c   data considerada
	 * @param per
	 *            <ul>
	 *            <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *            data e termina no final do mesmo ano;</i>
	 *            <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *            data e termina no final do mesmo mês;</i>
	 *            <li>{@link Calendar#WEEK_OF_MONTH} o intervalo começa no começa da
	 *            semana (madrugada de domingo) e termina no final da mesma semana
	 *            (sábado a noite)</i>
	 *            <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *            dia da data e termina no final do mesmo dia.</i>
	 *            </ul>
	 * @return intervalo que envolve a data
	 */
	public static Calendar[] getRange(Calendar c, int per) {
		Calendar[] out = new Calendar[2];
		out[0] = getLowerBoundary(c, per);
		out[1] = (Calendar) out[0].clone();
		out[1].add(per, 1);
		out[1].add(Calendar.MINUTE, -1);
		return out;
	}

	/**
	 * Função que retorna o limite inferior do período de tempo que envolve uma dada
	 * data
	 * 
	 * @param c   data considerada
	 * @param per
	 *            <ul>
	 *            <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *            data e termina no final do mesmo ano;</i>
	 *            <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *            data e termina no final do mesmo mês;</i>
	 *            <li>{@link Calendar#WEEK_OF_MONTH} o intervalo começa no começa da
	 *            semana (madrugada de domingo) e termina no final da mesma semana
	 *            (sábado a noite)</i>
	 *            <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *            dia da data e termina no final do mesmo dia.</i>
	 *            </ul>
	 * @return limite inferior do intervalo que envolve a data
	 */
	public static Calendar getLowerBoundary(Calendar c, int per) {
		if (per == Calendar.WEEK_OF_MONTH) {
			Calendar out = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			out.add(Calendar.DAY_OF_MONTH, -out.get(Calendar.DAY_OF_WEEK) + 1);
			return out;
		} else
			return new GregorianCalendar(c.get(Calendar.YEAR), per == Calendar.YEAR ? 0 : c.get(Calendar.MONTH),
					per == Calendar.YEAR || per == Calendar.MONTH ? 1 : c.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * Função que retorna o limite superior do período de tempo que envolve uma dada
	 * data
	 * 
	 * @param c   data considerada
	 * @param per
	 *            <ul>
	 *            <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *            data e termina no final do mesmo ano;</i>
	 *            <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *            data e termina no final do mesmo mês;</i>
	 *            <li>{@link Calendar#WEEK_OF_MONTH} o intervalo começa no começa da
	 *            semana (madrugada de domingo) e termina no final da mesma semana
	 *            (sábado a noite)</i>
	 *            <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *            dia da data e termina no final do mesmo dia.</i>
	 *            </ul>
	 * @return limite superior do intervalo que envolve a data
	 */
	public static Calendar getUpperBoundary(Calendar c, int per) {
		if (per == Calendar.WEEK_OF_MONTH) {
			Calendar out = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH), 23, 59);
			out.add(Calendar.DAY_OF_MONTH, 7 - out.get(Calendar.DAY_OF_WEEK));
			return out;
		} else {
			Calendar out = new GregorianCalendar(c.get(Calendar.YEAR),
					per == Calendar.YEAR ? 11 : c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59);
			if (per == Calendar.YEAR || per == Calendar.MONTH)
				out.set(Calendar.DAY_OF_MONTH, out.getActualMaximum(Calendar.DAY_OF_MONTH));
			return out;
		}
	}

	/**
	 * Função que determina os intervalos de tempo representando por certos
	 * instantes de tempo
	 * 
	 * @param per
	 *            <ul>
	 *            <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *            data e termina no final do mesmo ano;</i>
	 *            <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *            data e termina no final do mesmo mês;</i>
	 *            <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *            dia da data e termina no final do mesmo dia;</i>
	 *            </ul>
	 * @param cs  instantes de tempo que determinam os intervalos
	 * @return períodos de tempo, definidos por uma matriz de duas colunas, onde os
	 *         elementos da primeira coluna indicam o começo de um subintervalo e os
	 *         da segunda indicam o final do mesmo
	 */
	public static Calendar[][] convertCalendarIntoRange(int per, Calendar... cs) {
		List<Calendar[]> out = new LinkedList<>();

		Calendar[] current = null;
		for (int i = 0; i < cs.length; i++) {
			Calendar[] range = getRange(cs[i], per);
			if (current == null ? true : range[0].getTimeInMillis() - current[1].getTimeInMillis() > 60_000) {
				// se a distância entre o começo do próximo e o final do anterior for maior que
				// um minuto, então não é contínuo, inicia uma nova faixa
				current = range;
				out.add(current);
			} else {
				// senão, é contínuo, logo só desloca a faixa
				current[1] = range[1];
			}
		}
		return out.toArray(new Calendar[out.size()][]);
	}

	private static final Calendar SAS0 = new GregorianCalendar(1960, 0, 1);

	/**
	 * Função que une dois objetos, um representando a data e outro a hora, em um
	 * objeto único
	 * 
	 * @param date data
	 * @param time hora
	 * @param dst  <code>true</code> quando ignora-se o horário de verão (e na
	 *             verdade todas as horas estão atrasadas durante o horário de
	 *             verão, devendo ser adicionada uma hora); <code>false</code> a
	 *             hora está no horário de verão
	 * @return {@link Calendar} que representa a data e a hora indicadas
	 */
	public static Calendar mergeTimeDate(Object date, Object time, boolean dst) {
		if (date instanceof Date && time instanceof Time) {
			Date d = (Date) date;
			Time t = (Time) time;

			@SuppressWarnings("deprecation")
			long lt = t.getHours() * 3600000L + t.getMinutes() * 60000L + t.getSeconds() * 1000L;
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(d.getTime() - 1);
			c.setTimeInMillis(c.getTimeInMillis() + lt + 1 + (dst ? c.get(Calendar.DST_OFFSET) : 0));
			return c;
		} else {
			Calendar c = (Calendar) SAS0.clone();
			c.add(Calendar.DAY_OF_MONTH, ((Double) date).intValue());
			c.add(Calendar.SECOND, ((Double) time).intValue());
			return c;
		}
	}

	/**
	 * Função que analisa um diretório com arquivos de medições e informa os
	 * instantes inicial e final do período em que há medições disponíveis
	 * 
	 * @param folder diretório com os arquivos na forma
	 *               '[prefixo][yy][mm][dd][sufixo]'
	 * @param prefix padrão GLOB do prefixo do nome do arquivo
	 * @param suffix padrão GLOB do sufixo do nome do arquivo
	 * @return vetor com duas posições, indicando o instante inicial e final
	 */
	public static Calendar[] getPeriod(String folder, String prefix, String suffix) {
		Path dir = FileSystems.getDefault().getPath(folder);
		if (Files.exists(dir)) {
			String first = "999999", last = "000000";
			String glob = prefix + "[0123][0-9][01][0-9][0123][0-9]" + suffix;
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(dir, glob);
				for (Path path : stream) {
					String n = path.getFileName().toString();
					n = n.substring(prefix.length(), prefix.length() + first.length());
					if (first.compareTo(n) > 0)
						first = n;
					if (last.compareTo(n) < 0)
						last = n;
				}
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Calendar[] out = new Calendar[] {
					new GregorianCalendar(2000 + Integer.parseInt(first.substring(0, 2)),
							Integer.parseInt(first.substring(2, 4)) - 1, Integer.parseInt(first.substring(4, 6))),
					new GregorianCalendar(2000 + Integer.parseInt(last.substring(0, 2)),
							Integer.parseInt(last.substring(2, 4)) - 1, Integer.parseInt(last.substring(4, 6)), 23, 59,
							59) };
			if (out[0].after(out[1]))
				return new Calendar[] { Calendar.getInstance(), Calendar.getInstance() };
			else
				return out;
		} else
			return null;
	}

	/**
	 * Função que retorna as datas limites de uma estação
	 * 
	 * @param season     0 para verão, 1 para outono, 2 para inverno e 3 para
	 *                   primavera
	 * @param year       número do ano
	 * @param realSeason se <code>true</code>, a estação verão começa no dia do
	 *                   solstício de verão (21 de dezembro), senão no dia 1 de
	 *                   janeiro (divisão por trimestre)
	 * @return vetor com os limites inferior e superior
	 */
	public static Calendar[] getRangeSeason(int season, int year, boolean realSeason) {
		Calendar[] c = new Calendar[] { new GregorianCalendar(year, 3 * season, 1, 0, 0, 0),
				new GregorianCalendar(year, 3 * season + 3, 1, 0, 0, 0) };

		if (realSeason) {
			c[0].add(Calendar.DAY_OF_MONTH, -10);
			c[1].add(Calendar.DAY_OF_MONTH, -10);
		}

		c[1].add(Calendar.MINUTE, -1);
		return c;
	}

	/**
	 * REN ANEEL 418, de 23/11/2010
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isFeriadoNacional(Calendar c) {
		int d = c.get(Calendar.DAY_OF_MONTH), m = c.get(Calendar.MONTH);
		if (d == 1 && m == Calendar.JANUARY) // Confraternização universal
			return true;
		else if (d == 21 && m == Calendar.APRIL) // Tiradentes
			return true;
		else if (d == 1 && m == Calendar.MAY) // Dia do trabalho
			return true;
		else if (d == 7 && m == Calendar.SEPTEMBER) // independência
			return true;
		else if (d == 12 && m == Calendar.OCTOBER) // Aparecida
			return true;
		else if (m == Calendar.NOVEMBER) // Finados ou Proclamação da república
			return d == 2 || d == 15;
		else if (d == 25 && m == Calendar.DECEMBER) // Natal
			return true;
		else {
			// páscoa
			Calendar e = getPascoa(c.get(Calendar.YEAR));

			// Terça-feira de carnaval
			e.add(Calendar.DAY_OF_MONTH, -47);
			if (e.get(Calendar.DAY_OF_MONTH) == d && e.get(Calendar.MONTH) == m)
				return true;

			// Sexta-feira da Paixão
			e.add(Calendar.DAY_OF_MONTH, 45);
			if (e.get(Calendar.DAY_OF_MONTH) == d && e.get(Calendar.MONTH) == m)
				return true;

			// Quinta de Corpus Christi
			e.add(Calendar.DAY_OF_MONTH, 62);
			if (e.get(Calendar.DAY_OF_MONTH) == d && e.get(Calendar.MONTH) == m)
				return true;

			return false;
		}
	}

	public static Calendar getPascoa(int y) {
		int a = y % 19;
		int b = y / 100;
		int c = y % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int mes = (h + l - 7 * m + 114) / 31;
		int dia = ((h + l - 7 * m + 114) % 31) + 1;

		return new GregorianCalendar(y, mes - 1, dia);
	}

	public static Calendar getSabadoCarnaval(int y) {
		Calendar out = getPascoa(y);
		out.add(Calendar.DAY_OF_MONTH, -50);
		return out;
	}

	/**
	 * Função que dá o instante em que se inicia o horário de verão (devido ao fato
	 * de o relógio ter adiantado uma hora, será 01:00 do domingo)
	 * 
	 * @param y ano a ser considerado
	 * @return data e hora do início do horário de verão
	 */
	public static Calendar getDSTbeginning(int y) {
		if (y > 2018)
			return null;
		Calendar c = new GregorianCalendar(y, Calendar.OCTOBER, 1);
		for (int i = 2; i < 6; i++) {
			c.set(Calendar.WEEK_OF_MONTH, i);
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			if (c.get(Calendar.DST_OFFSET) != 0)
				break;
		}
		return c;
	}

	/**
	 * Função que dá o instante em que se encerra o horário de verão (devido ao fato
	 * de o relógio ter atrasado uma hora, 23:00 do sábado de novo)
	 * 
	 * @param y ano a ser considerado
	 * @return data e hora do fim do horário de verão
	 */
	public static Calendar getDSTend(int y) {
		if (y > 2018)
			return null;
		Calendar c = new GregorianCalendar(y, Calendar.FEBRUARY, 1);
		for (int i = 2; i < 6; i++) {
			c.set(Calendar.WEEK_OF_MONTH, i);
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			if (c.get(Calendar.DST_OFFSET) == 0)
				break;
		}
		c.add(Calendar.HOUR_OF_DAY, -1);
		return c;
	}

	// range, mas trabalhando com os inteiros equivalentes (ver seção "CONVERSÃO
	// TEMPO-INTEIRO")

	/**
	 * 
	 * @param per
	 *            <ul>
	 *            <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *            data e termina no final do mesmo ano;</i>
	 *            <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *            data e termina no final do mesmo mês;</i>
	 *            <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *            dia da data e termina no final do mesmo dia;</i>
	 *            </ul>
	 * @param i
	 * @return
	 */
	public static Calendar[] convertIntIntoRange(int per, int i) {
		return getRange(int2time(per, i), per);
	}

	/**
	 * 
	 * @param per
	 *            <ul>
	 *            <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *            data e termina no final do mesmo ano;</i>
	 *            <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *            data e termina no final do mesmo mês;</i>
	 *            <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *            dia da data e termina no final do mesmo dia;</i>
	 *            </ul>
	 * @param is
	 * @return
	 */
	public static Calendar[][] convertIntsIntoRange(int per, int... is) {
		return convertCalendarIntoRange(per, ints2times(per, is));
	}

	// ------------------------ CONVERSÃO TEMPO-INTEIRO ------------------------

	/**
	 * 
	 * @param t
	 *          <ul>
	 *          <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *          data e termina no final do mesmo ano;</i>
	 *          <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *          data e termina no final do mesmo mês;</i>
	 *          <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *          dia da data e termina no final do mesmo dia;</i>
	 *          </ul>
	 * @param c
	 * @return
	 */
	public static int time2int(int t, Calendar c) {
		switch (t) {
		case Calendar.DAY_OF_MONTH:
			return date2int(c);
		case Calendar.MONTH:
			return month2int(c);
		default:
			return c.get(t);
		}
	}

	/**
	 * 
	 * @param t
	 *          <ul>
	 *          <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *          data e termina no final do mesmo ano;</i>
	 *          <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *          data e termina no final do mesmo mês;</i>
	 *          <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *          dia da data e termina no final do mesmo dia;</i>
	 *          </ul>
	 * @param i
	 * @return
	 */
	public static Calendar int2time(int t, int i) {
		switch (t) {
		case Calendar.DAY_OF_MONTH:
			return int2date(i);
		case Calendar.MONTH:
			return int2month(i);
		case Calendar.YEAR:
			return new GregorianCalendar(i, 0, 1);
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param t
	 *           <ul>
	 *           <li>{@link Calendar#YEAR} o intervalo começa no começo do ano da
	 *           data e termina no final do mesmo ano;</i>
	 *           <li>{@link Calendar#MONTH} o intervalo começa no começo do mês da
	 *           data e termina no final do mesmo mês;</i>
	 *           <li>{@link Calendar#DAY_OF_MONTH} o intervalo começa no começo do
	 *           dia da data e termina no final do mesmo dia;</i>
	 *           </ul>
	 * @param is
	 * @return
	 */
	public static Calendar[] ints2times(int t, int... is) {
		Calendar[] out = new Calendar[is.length];
		for (int i = 0; i < is.length; i++)
			out[i] = int2time(t, is[i]);
		return out;
	}

	// SEGUNDOS

	/**
	 * (int)(46*365,25)*(24*3600)=1451606400 segundos de 0h00 1/1/1970 até 0h00
	 * 1/1/2016 + 2*3600=7200 (defasagem do fuso horário)
	 */
	private static final int ISTART = 1_451_613_600;

	private static final long SEC_MILLI = 1_000L;

	// tempo -> inteiro

	/**
	 * Função que converte uma data em um número inteiro. Tal número é o quantidade
	 * de segundos existentes entre a dada data e o instante Dom Jan 01 00:00:00
	 * BRST 2012. Essa função é a inversa da {@link #toCalendar(int) toCalendar},
	 * sendo que ambas funções são bijetoras (i.e. cada entrada corresponde a uma e
	 * somente uma saída).
	 * 
	 * @param c data a ser convertida
	 * @return inteiro equivalente
	 */
	public static int toInt(Calendar c) {
		return toInt(c.getTimeInMillis());
	}

	public static int toInt(Date d) {
		return toInt(d.getTime());
	}

	private static int toInt(long mili) {
		return (int) (mili / SEC_MILLI) - ISTART;
	}

	// inteiro -> tempo

	/**
	 * Função que converte um número inteiro em uma data, seguindo a lógica inversa
	 * daquela utilizada na função {@link TimeUtils#toInt(Calendar) toInt}, de modo que
	 * ambas funções são bijetoras (i.e. cada entrada corresponde a uma e somente
	 * uma saída).
	 * 
	 * @param ic inteiro a ser convertido.
	 * @return data equivalente
	 */
	public static Calendar toCalendar(int ic) {
		Calendar out = Calendar.getInstance();
		out.setTimeInMillis(toLong(ic));
		return out;
	}

	private static long toLong(int ic) {
		return SEC_MILLI * (ISTART + ((long) ic));
	}

	// unix epoch

	public static Calendar fromUnixepoch(int ue) {
		Calendar out = Calendar.getInstance();
		out.setTimeInMillis(SEC_MILLI * ue - out.getTimeZone().getRawOffset());
		return out;
	}

	public static int toUnixepoch(Calendar c) {
		return (int) ((c.getTimeInMillis() + c.getTimeZone().getRawOffset()) / SEC_MILLI);
	}

	// DIAS

	/**
	 * Função que converte uma data em um número inteiro. Tal número é o quantidade
	 * de dias existentes entre a dada data e o instante 0 do {@link Calendar}. Essa
	 * função é a inversa da {@link TimeUtils#int2date(int)}, sendo que ambas funções
	 * são bijetoras (i.e. cada entrada corresponde a uma e somente uma saída).
	 * 
	 * @param c data a ser convertida
	 * @return inteiro equivalente
	 */
	public static int date2int(Calendar c) {
		return (int) ((c.getTimeInMillis() + c.get(Calendar.DST_OFFSET) + c.get(Calendar.ZONE_OFFSET)) / 86400000);
	}

	/**
	 * Função que converte uma data em um número inteiro. Tal número é o quantidade
	 * de dias existentes entre a dada data e o instante 0 do {@link Calendar}. Essa
	 * função é a inversa da {@link TimeUtils#int2date(int)}, sendo que ambas funções
	 * são bijetoras (i.e. cada entrada corresponde a uma e somente uma saída).
	 * 
	 * @param d data a ser convertida
	 * @return inteiro equivalente
	 */
	public static int date2int(Date d) {
		return date2int(date2Calendar(d));
	}

	/**
	 * Função que converte um número inteiro em uma data, seguindo a lógica inversa
	 * daquela utilizada na função {@link TimeUtils#date2int(Calendar)}, de modo que
	 * ambas funções são bijetoras (i.e. cada entrada corresponde a uma e somente
	 * uma saída).
	 * 
	 * @param i inteiro a ser convertido.
	 * @return data equivalente
	 */
	public static Calendar int2date(int i) {
		Calendar out = Calendar.getInstance();
		// 3 horas de fuso
		out.setTimeInMillis(10_800_000);
		out.add(Calendar.DAY_OF_MONTH, i);
		return out;
	}

	// MESES

	/**
	 * Função que converte uma data que representa um momento de um dado mês em um
	 * inteiro representativo do mês. O inteiro é o número de meses que há entre
	 * janeiro de 2013 e o argumento da função.
	 * 
	 * @param year  inteiro que representa o ano
	 * @param month inteiro que represente o mês (janeiro 0, dezembro 11)
	 * @return inteiro que representa o mês
	 */
	public static int month2int(int year, int month) {
		return 12 * (year - 2013) + month;
	}

	/**
	 * Função que converte uma data que representa um momento de um dado mês em um
	 * inteiro representativo do mês. O inteiro é o número de meses que há entre
	 * janeiro de 2013 e o argumento da função. É a função inversa do
	 * {@link TimeUtils#int2month(int)}.
	 * 
	 * @param c objeto {@link Calendar} que representa a data
	 * @return inteiro que representa o mês
	 */
	public static int month2int(Calendar c) {
		return month2int(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
	}

	/**
	 * Função que converte um vetor de inteiros que representam meses em um vetor de
	 * objetos {@link Calendar}. Aplica-se a função {@link TimeUtils#int2month(int)}
	 * sobre cada um dos inteiros.
	 * 
	 * @param is vetor de número inteiros
	 * @return vetor de objetos {@link Calendar}
	 */
	public static Calendar[] ints2months(int... is) {
		Calendar[] out = new Calendar[is.length];
		for (int i = 0; i < is.length; i++)
			out[i] = int2month(is[i]);
		return out;
	}

	/**
	 * Função que converte uma data que representa um momento de um dado mês em um
	 * inteiro representativo do mês. O inteiro é o número de meses que há entre
	 * janeiro de 2013 e o argumento da função. É a função inversa do
	 * {@link TimeUtils#month2int(Calendar)}.
	 * 
	 * @param i inteiro que representa o mês
	 * @return objeto {@link Calendar} que representa o primeiro dia do mês
	 */
	public static Calendar int2month(int i) {
		return new GregorianCalendar(i / 12 + 2013, i % 12, 1);
	}

	public static final Calendar ZERO_W = new GregorianCalendar(2019, Calendar.DECEMBER, 29);

	/**
	 * Função que retorna o número da semana. A semana 0 é aquela que começa no
	 * domingo dia {@link #ZERO_W 29/12/2019} (domingo). É a função inversa de
	 * {@link #int2week(int) }.
	 * 
	 * @param c dia
	 * @return número da semana
	 */
	public static int week2int(Calendar c) {
		int out = c.get(Calendar.WEEK_OF_YEAR) - 1;
		if (c.get(Calendar.MONTH) == Calendar.DECEMBER && out == 0)
			out += 52 * (c.get(Calendar.YEAR) - 2019); // semana do ano seguinte
		else
			out += 52 * (c.get(Calendar.YEAR) - 2020);
		return out;
	}

	/**
	 * Função que retorna a semana, designada pelo seu domingo. A semana 0 é aquela
	 * que começa no domingo dia {@link #ZERO_W 29/12/2019}. É a função inversa de
	 * {@link #week2int(Calendar)}.
	 * 
	 * @param ci número da semana
	 * @return domingo do começo da semana
	 */
	public static Calendar int2week(int ci) {
		int w = ci % 52;
		int a = ci / 52;
		Calendar out = new GregorianCalendar(2020 + a, 0, 1);
		out.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		out.set(Calendar.WEEK_OF_YEAR, w + 1);
		return out;
	}

	// ==================== Funções de fusos horários ====================

	private static int otan2tzShift(char l) {
		int li = l - 65;
		if (li == 74)
			throw new IllegalArgumentException("J não é um fuso OTAN");
		int gmt = 0;
		if (li == 25) // Z
			return 0;
		else if (li >= 13) // N em diante
			return 12 - li;
		else if (li < 9) // antes de J
			gmt = li + 1;
		else // depois do J
			gmt = li;
		return gmt;
	}

	private static char tzShift2otan(int gmt) {
		if (gmt < -12 || gmt > 12)
			throw new IllegalArgumentException("J não é um fuso OTAN");
		if (gmt == 0) // Z
			return 'Z';
		else if (gmt < 0) // N em diante
			return (char) ('A' + 12 - gmt);
		else if (gmt < 10) // antes de J
			return (char) ('A' - 1 + gmt);
		else // depois do J
			return (char) ('A' + gmt);
	}

	public static TimeZone getTimeZone(int gmt) {
		if (gmt == 0)
			return TimeZone.getTimeZone("GMT");
		else
			return TimeZone.getTimeZone(String.format("GMT%+d", gmt));
	}

	public static char getTime(Calendar calendar) {
		int gmt = calendar.getTimeZone().getRawOffset() / 3600000;
		return tzShift2otan(gmt);
	}

	public static TimeZone getTimeZone(char letter) {
		return getTimeZone(otan2tzShift(letter));
	}

	public static TimeZone[] tzValues() {
		TimeZone[] out = new TimeZone[25];
		for (int i = -12; i <= 12; i++)
			out[i + 12] = getTimeZone(i);
		return out;
	}

	public static class TimeZoneRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			TimeZone tz = (TimeZone) value;
			return super.getListCellRendererComponent(list, tz.getID(), index, isSelected, cellHasFocus);
		}
	}

	/**
	 * Função que força o Java a reconhecer o fim do horário de verão brasileiro,
	 * caso a atualização dos fusos horários não tenha sido feita. Ver mais em
	 * <a href=
	 * "http://www.oracle.com/technetwork/java/javase/tzupdater-readme-136440.html">Timezone
	 * Updater Tool</a>.
	 */
	public static void endOfBRST() {
		Calendar c0 = Calendar.getInstance();
		if (c0.get(Calendar.DST_OFFSET) != 0) {
			System.setProperty("user.timezone", "America/Recife");
			TimeZone.setDefault(TimeZone.getTimeZone("America/Recife"));
		}
	}

	// ======== Funções de conversão de Strings em objetos e vice-versa ========

	/**
	 * Função que retorna o objeto {@link java.util.Calendar} associado a uma
	 * sequência de caracteres escritos na forma yyyy-MM-dd HH:mm:ss
	 * 
	 * @param string <code>String</code> num dado formato
	 * @return objeto <code>Calendar</code>
	 */
	public static Calendar string2Calendar(String string) {
		return date2Calendar(string2Date(string, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * Função que retorna o objeto {@link java.util.Calendar} associado a uma
	 * sequência de caracteres que obdece a um dado formato
	 * 
	 * @param string <code>String</code> num dado formato
	 * @param format formato a ser seguido (ver {@link SimpleDateFormat})
	 * @return objeto <code>Calendar</code>
	 */
	public static Calendar string2Calendar(String string, String format) {
		return string2Calendar(string, format, null);
	}

	/**
	 * Função que retorna o objeto {@link java.util.Calendar} associado a uma
	 * sequência de caracteres que obdece a um dado formato
	 * 
	 * @param string <code>String</code> num dado formato
	 * @param format formato a ser seguido (ver {@link SimpleDateFormat})
	 * @param locale parâmetros locais
	 * @return objeto <code>Calendar</code>
	 */
	public static Calendar string2Calendar(String string, String format, Locale locale) {
		return date2Calendar(TimeUtils.string2Date(string, format, locale));
	}

	/**
	 * Função que retorna o objeto {@link Date} associado a uma sequência de
	 * caracteres escritos na forma yyyy-MM-dd
	 * 
	 * @param date {@link String} num dado formato
	 * @return objeto {@link Date}
	 */
	public static Date string2Date(String date) {
		return TimeUtils.string2Date(date, "yyyy-MM-dd");
	}

	/**
	 * Função que retorna o objeto {@link Date} associado a uma sequência de
	 * caracteres que obdece a um dado formato
	 * 
	 * @param date   data num dado formato
	 * @param format formato a ser seguido (ver {@link SimpleDateFormat})
	 * @return objeto {@link Date}
	 */
	public static Date string2Date(String date, String format) {
		return string2Date(date, format, null);
	}

	/**
	 * Função que retorna o objeto {@link Date} associado a uma sequência de
	 * caracteres que obdece a um dado formato
	 * 
	 * @param str   data num dado formato
	 * @param format formato a ser seguido (ver {@link SimpleDateFormat})
	 * @param locale parâmetros locais
	 * @return objeto {@link Date}
	 */
	public static Date string2Date(String str, String format, Locale locale) {
		SimpleDateFormat dateFormat = null;
		if (locale == null)
			dateFormat = new SimpleDateFormat(format);
		else
			dateFormat = new SimpleDateFormat(format, locale);
		Date out = null;
		try {
			out = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return out;
	}

	// ============= Padrões =============

	/**
	 * Função que retorna o padrão que aceita somente meses do ano (numa dada
	 * língua)
	 * 
	 * @param l parâmetros regionais do idioma (<code>null</code> para parâmetros
	 *          locais estabelecidos na máquina)
	 * @return padrão dos meses do ano
	 */
	public static String monthPattern(Locale l) {
		String[] months = new String[12];
		Calendar c = new GregorianCalendar(2020, Calendar.JANUARY, 1);
		for (int i = 0; i < months.length; i++) {
			if (l != null)
				months[i] = String.format(l, "%tB", c).toLowerCase();
			else
				months[i] = String.format("%tB", c).toLowerCase();
			c.add(Calendar.MONTH, 1);
		}
		return StringUtils.getPatternNFL(months);
	}
}
