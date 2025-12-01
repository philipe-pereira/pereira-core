package br.com.pereiraeng.core.collections;

import java.util.Arrays;

/**
 * Arrays utils
 * 
 * @author Philipe PEREIRA
 *
 */
public class ArrayUtils {

	/**
	 * Função que retorna a posição de um posição onde há um objeto igual a um outro
	 * objeto também presente no mesmo vetor
	 * 
	 * @param objs  vetor de objetos
	 * @param index posição do objeto cuja duplicidade está sendo procurada
	 * @return posição do objeto no vetor, ou -1 se não houver um objeto igual no
	 *         vetor
	 */
	public static int hasDuplicate(Object[] objs, int index) {
		for (int i = 0; i < objs.length; i++) {
			if (i == index)
				continue;
			else if (objs[index] == null ? objs[i] == null : objs[index].equals(objs[i]))
				return i;
		}
		return -1;
	}

	/**
	 * Função que indica se, num vetor de objetos, alguma de suas posições está
	 * apontando para algum objeto (ou seja, se todas as suas posições não estão com
	 * <code>null</code>)
	 * 
	 * @param objs vetor de objetos
	 * @return <code>true</code> se há algum objeto nesse vetor, <code>false</code>
	 *         senão
	 */
	public static boolean hasNonNull(Object[] objs) {
		boolean out = false;
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] != null) {
				out = true;
				break;
			}
		}
		return out;
	}

	/**
	 * Função que gera um novo vetor de objetos a partir dos objetos indicados em
	 * determinadas posições de um dado vetor
	 * 
	 * @param in  dado vetor
	 * @param pos posições
	 * @return novo vetor de objetos
	 */
	public static Object[] getElements(Object[] in, int... pos) {
		Object[] out = new Object[pos.length];
		for (int i = 0; i < pos.length; i++)
			out[i] = in[pos[i]];
		return out;
	}

	/**
	 * Função que gera um novo vetor de números decimais a partir dos objetos
	 * indicados em determinadas posições de um dado vetor
	 * 
	 * @param in  dado vetor
	 * @param pos posições
	 * @return novo vetor de números decimais
	 */
	public static float[] getElements(float[] in, int... pos) {
		float[] out = new float[pos.length];
		for (int i = 0; i < pos.length; i++)
			out[i] = in[pos[i]];
		return out;
	}

	/**
	 * Função que pega uma coluna de uma matriz
	 * 
	 * @param xy    matriz de double
	 * @param index índice da coluna
	 * @return vetor coluna
	 */
	public static double[] getColumn(double[][] matrix, int column) {
		double[] out = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++)
			out[i] = matrix[i][column];
		return out;
	}

	/**
	 * Função que pega mais de uma coluna de uma matriz
	 * 
	 * @param matrix matriz de double
	 * @param column índices das colunas
	 * @return vetores com cada um das colunas selecionadas
	 */
	public static double[][] getColumns(double[][] matrix, int... column) {
		double[][] out = new double[column.length][matrix.length];
		for (int i = 0; i < column.length; i++)
			for (int k = 0; k < matrix.length; k++)
				out[i][k] = matrix[k][column[i]];
		return out;
	}

	/**
	 * Função que pega uma coluna de uma matriz
	 * 
	 * @param matrix matriz de objetos
	 * @param column índice da coluna
	 * @return vetor coluna
	 */
	public static Object[] getColumn(Object[][] matrix, int column) {
		Object[] out = new Object[matrix.length];
		for (int i = 0; i < matrix.length; i++)
			out[i] = matrix[i][column];
		return out;
	}

	// ---------------------------------------------------

	/**
	 * Função que cria um vetor de booleans com todas as posições com o mesmo valor
	 * 
	 * @param value valor a ser repetido em todas as posições
	 * @param size  tamanho do vetor
	 * @return vetor criado
	 */
	public static boolean[] booleanVec(boolean value, int size) {
		boolean[] out = new boolean[size];
		Arrays.fill(out, value);
		return out;
	}

	/**
	 * Função que cria um vetor de inteiro com todas as posições com o mesmo valor
	 * 
	 * @param value valor a ser repetido em todas as posições
	 * @param size  tamanho do vetor
	 * @return vetor criado
	 */
	public static int[] intVec(int value, int size) {
		int[] out = new int[size];
		Arrays.fill(out, value);
		return out;
	}

	/**
	 * Função que cria um vetor preenchido com um mesmo dado valor
	 * 
	 * @param value número decimal que ocupará todas as posições
	 * @param size  número de posições
	 * @return vetor
	 */
	public static float[] floatVec(float value, int size) {
		float[] out = new float[size];
		Arrays.fill(out, value);
		return out;
	}

	/**
	 * Função que cria um vetor preenchido com um mesmo dado valor
	 * 
	 * @param value número decimal que ocupará todas as posições
	 * @param size  número de posições
	 * @return vetor
	 */
	public static double[] doubleVec(double value, int size) {
		double[] out = new double[size];
		Arrays.fill(out, value);
		return out;
	}

	// ---------------------------------------------------

	/**
	 * Função que cria um vetor de inteiro com todas as posições com o valor
	 * progressivo
	 * 
	 * @param size
	 * @param zero <code>true</code> para começar no zero, <code>false</code> para
	 *             começar no um
	 * @return
	 */
	public static Integer[] progVec(int size, boolean zero) {
		Integer[] out = new Integer[size];
		if (size > 0) {
			out[0] = zero ? 0 : 1;
			for (int i = 1; i < size; i++)
				out[i] = out[i - 1] + 1;
		}
		return out;
	}

	/**
	 * Função que retorna um vetor de inteiros em progressão aritmética de razão 1
	 * 
	 * @param zero <code>true</code> para começar no zero, <code>false</code> para
	 *             começar no um
	 * @param size número de posições do vetor
	 * @return vetor de inteiros em progressão aritmética de razão 1
	 */
	public static int[] progVec(boolean zero, int size) {
		return progVec(zero ? 0 : 1, size);
	}

	/**
	 * Função que retorna um vetor de inteiros em progressão aritmética de razão 1
	 * 
	 * @param start inteiro a partir do qual a progressão começa
	 * @param size  número de posições do vetor
	 * @return vetor de inteiros em progressão aritmética de razão 1
	 */
	public static int[] progVec(int start, int size) {
		return progVec(start, size, 1);
	}

	/**
	 * Função que retorna um vetor de inteiros em progressão aritmética
	 * 
	 * @param start inteiro a partir do qual a progressão começa
	 * @param size  número de posições do vetor
	 * @param r     razão da progressão aritmética
	 * @return vetor de inteiros em progressão aritmética
	 */
	public static int[] progVec(int start, int size, int r) {
		int[] out = new int[size];
		out[0] = start;
		for (int i = 1; i < size; i++)
			start = out[i] = start + r;
		return out;
	}

	// ---------------------------------------------------

	/**
	 * Função que desloca os <code>bytes</code> de um vetor um dado número de
	 * posições
	 * 
	 * @param array vetor com os <code>bytes</code>
	 * @param shift número de posições a serem deslocadas. A nova posição do
	 *              elemento será a antiga acrescida do valor deste argumento
	 * @return vetor com os bytes deslocados
	 */
	public static void shiftedArray(byte[] array, int shift) {
		for (int i = 0; i < array.length; i++) {
			int k = i - shift;
			if (k >= 0 && k < array.length)
				array[i] = array[k];
		}
	}

	public static void shiftedArray(float[] array, int shift) {
		for (int i = 0; i < array.length; i++) {
			int k = i - shift;
			if (k >= 0 && k < array.length)
				array[i] = array[k];
		}
	}

	public static void shiftedArray(double[] array, int shift) {
		for (int i = 0; i < array.length; i++) {
			int k = i - shift;
			if (k >= 0 && k < array.length)
				array[i] = array[k];
		}
	}

	public static void shiftedArray(Object[] array, int shift) {
		for (int i = 0; i < array.length; i++) {
			int k = i - shift;
			if (k >= 0 && k < array.length)
				array[i] = array[k];
		}
	}

	// ---------------------------------------------------

	/**
	 * Função que retorna a posição de um dado vetor que contém a entrada mais
	 * próxima de um dado número de referência
	 * 
	 * @param vector  vetor onde o valor será procurado
	 * @param value   valor de referência
	 * @param ordered se <code>true</code> a busca é feita presupondo que o vetor é
	 *                ordenado (isso faz com que a busca seja mais rápida, porém se
	 *                o vetor não estiver efetivamento ordenado, não é assegurado
	 *                que o valor mais próximo seja retornado)
	 * @return posição do vetor que com a entrada mais próxima do valor de
	 *         referência
	 */
	public static int getNearestIndice(double[] vector, double value, boolean ordered) {
		int indice = -1;
		double maxDelta = ordered ? vector[vector.length - 1] : Double.MAX_VALUE;

		for (int i = 0; i < vector.length; i++) {
			double distance = Math.abs(vector[i] - value);

			if (distance == 0.) {
				return i;
			} else {
				if (distance < maxDelta) {
					indice = i;
					maxDelta = distance;
				} else if (ordered)
					return indice;
			}
		}
		return indice;
	}

	/**
	 * Função que retorna o valor que está em um dado vetor que contém a entrada
	 * mais próxima de um dado número de referência
	 * 
	 * @param vector  vetor onde o valor será procurado
	 * @param value   valor de referência
	 * @param ordered se <code>true</code> a busca é feita presupondo que o vetor é
	 *                ordenado (isso faz com que a busca seja mais rápida, porém se
	 *                o vetor não estiver efetivamento ordenado, não é assegurado
	 *                que o valor mais próximo seja retornado)
	 * @return valor mais próximo do valor de referência
	 */
	public static double getNearestValue(double[] vector, double value, boolean ordered) {
		return vector[getNearestIndice(vector, value, ordered)];
	}

	/**
	 * Função que indica em qual intervalo se encontra um dado valor dentre de um
	 * seqüencia
	 * 
	 * @param x  valor procurado
	 * @param xs partição do intervalo
	 * @return índice do subintervalo procurado
	 */
	public static int contains(double x, double[] xs) {
		if (xs.length < 2)
			throw new IllegalArgumentException("O vetor que descreve a partição deve ter ao menos duas posições");
		if (x < xs[0])
			return 0;
		int i;
		for (i = 1; i < xs.length; i++)
			if (x <= xs[i]) // se estiver em [x(i-1); x(i)]...
				return i;
		return i;
	}

	@SafeVarargs
	public static <K extends Comparable<K>> boolean isWithin(K[] interval, boolean fromInclusive, boolean toInclusive,
			K... cs) {
		boolean out = true;
		for (K c : cs)
			out &= ((fromInclusive ? c.compareTo(interval[0]) >= 0 : c.compareTo(interval[0]) > 0)
					&& (toInclusive ? interval[1].compareTo(c) >= 0 : interval[1].compareTo(c) > 0));
		return out;
	}

	/**
	 * Função que indica se um ou mais objetos comparáveis estão compreendidos
	 * dentro de um dado intervalo (inclusive pela esquerda, exclusive pela direita)
	 * 
	 * @param interval intervalo de tempo, indicado na forma de um vetor com duas
	 *                 posições (a primeira ocupada pelo limite inferior do
	 *                 intervalo, a segunda pelo limite superior)
	 * @param cs       objetos
	 * @return <code>true</code> se todos os objetos estão dentro do intervalo,
	 *         <code>false</code> senão
	 */
	@SafeVarargs
	public static <K extends Comparable<K>> boolean isWithin(K[] interval, K... cs) {
		boolean out = true;
		for (K c : cs)
			out &= ((c.compareTo(interval[0]) >= 0) && (interval[1].compareTo(c) > 0));
		return out;
	}

	/**
	 * Função que determina os índices de um vetor {@link Comparable ordenado} de
	 * objetos de modo que os elementos entre tais indíces pertençam a um dado
	 * intervalo
	 * 
	 * @param start    índice a partir do qual começará a ser feita a busca
	 * @param interval vetor {@link Comparable ordenado} objetos
	 * @param inf      limite inferior do intervalo considerado
	 * @param sup      limite superior do intervalo considerado
	 * @return vetor de inteiros com duas posições, sendo que na primeira fica o
	 *         índice do limite inferior e na segunda o índice do limite superior
	 *         mais um (a adição da unidade é feita para que o
	 *         {@link Arrays#copyOfRange(Object[], int, int) método de recorte de
	 *         vetores} possa ser utilizado com o resultado desta função)
	 */
	public static <K extends Comparable<K>> int[] getRange(int start, Comparable<K>[] interval, K inf, K sup) {
		int[] out = new int[2];
		boolean flag = false;
		for (int i = start; i < interval.length; i++) {
			if (!flag && interval[i].compareTo(inf) >= 0) {
				out[0] = i;
				flag = true;
			}
			if (flag) {
				if (interval[i].compareTo(sup) > 0)
					break;
				out[1] = i + 1;
			}
		}
		return out;
	}

	// ---------------------------------------------------

	/**
	 * Função que retorna a primeira posição de um vetor onde há um número igual a
	 * um procurado
	 * 
	 * @param fs vetor de número decimais
	 * @param f  número decimal procurado
	 * @return posição do número decimal no vetor, ou -1 se não houver um número
	 *         decimal igual no vetor
	 */
	public static int indexOf(float[] fs, float f) {
		if (fs == null)
			return -1;
		for (int i = 0; i < fs.length; i++)
			if (f == fs[i])
				return i;
		return -1;
	}

	/**
	 * Função que retorna a primeira posição de um vetor onde há um número igual a
	 * um procurado
	 * 
	 * @param ds vetor de número decimais
	 * @param d  número decimal procurado
	 * @return posição do número decimal no vetor, ou -1 se não houver um número
	 *         decimal igual no vetor
	 */
	public static int indexOf(double[] ds, double d) {
		if (ds == null)
			return -1;
		for (int i = 0; i < ds.length; i++)
			if (d == ds[i])
				return i;
		return -1;
	}

	/**
	 * Função que retorna a primeira posição de um vetor onde há um número igual a
	 * um procurado
	 * 
	 * @param ds vetor de número inteiros
	 * @param d  número inteiro procurado
	 * @return posição do número inteiro no vetor, ou -1 se não houver um número
	 *         inteiro igual no vetor
	 */
	public static int indexOf(int[] ds, int d) {
		if (ds == null)
			return -1;
		for (int i = 0; i < ds.length; i++)
			if (d == ds[i])
				return i;
		return -1;
	}

	/**
	 * Função que retorna a posição de um vetor onde há um objeto igual a um
	 * procurado
	 * 
	 * @param objs vetor de objetos
	 * @param obj  objeto procurado
	 * @return posição do objeto no vetor, ou -1 se não houver um objeto igual no
	 *         vetor
	 */
	public static int indexOf(Object[] objs, Object obj) {
		for (int i = 0; i < objs.length; i++)
			if (obj == null ? objs[i] == null : obj.equals(objs[i]))
				return i;
		return -1;
	}

	/**
	 * Função que retorna as primeiras posições de um vetor onde há objetos iguais
	 * aos procurados
	 * 
	 * @param objs vetor de objetos
	 * @param obj  objetos procurados
	 * @return vetor com as posições dos objetos no vetor, ou -1 se não houver um
	 *         objeto igual no vetor
	 */
	public static int[] indexesOf(Object[] objs, Object... obj) {
		int[] out = intVec(-1, obj.length);
		for (int i = 0; i < obj.length; i++) {
			for (int j = 0; j < objs.length; j++) {
				if (obj[i] == null ? objs[j] == null : obj[i].equals(objs[j])) {
					out[i] = j;
					break;
				}
			}
		}
		return out;
	}

	// ---------------------------------------------------

	public static float[] castFloat(int[] is) {
		if (is == null)
			return null;
		float[] out = new float[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = is[i];
		return out;
	}

	public static float[] castFloat(double[] is) {
		if (is == null)
			return null;
		float[] out = new float[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = (float) is[i];
		return out;
	}

	public static double[] castDouble(int[] is) {
		if (is == null)
			return null;
		double[] out = new double[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = is[i];
		return out;
	}

	public static double[] castDouble(float[] fs) {
		if (fs == null)
			return null;
		double[] out = new double[fs.length];
		for (int i = 0; i < out.length; i++)
			out[i] = fs[i];
		return out;
	}

	public static int[] castInt(double[] is) {
		if (is == null)
			return null;
		int[] out = new int[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = (int) is[i];
		return out;
	}

	public static int[] castInt(float[] is) {
		if (is == null)
			return null;
		int[] out = new int[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = (int) is[i];
		return out;
	}

	public static String[] castString(Object[] is) {
		if (is == null)
			return null;
		String[] out = new String[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = (String) is[i];
		return out;
	}

	// ---------------------------------------------------

	public static Boolean[] box(boolean[] is) {
		if (is == null)
			return null;
		Boolean[] out = new Boolean[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = is[i];
		return out;
	}

	public static Integer[] box(int[] is) {
		if (is == null)
			return null;
		Integer[] out = new Integer[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = is[i];
		return out;
	}

	public static Float[] box(float[] is) {
		if (is == null)
			return null;
		Float[] out = new Float[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = is[i];
		return out;
	}

	public static Double[] box(double[] is) {
		if (is == null)
			return null;
		Double[] out = new Double[is.length];
		for (int i = 0; i < out.length; i++)
			out[i] = is[i];
		return out;
	}

	// ---------------------------------------------------

	public static float[] unboxFloat(Object[] os) {
		if (os == null)
			return null;
		float[] out = new float[os.length];
		for (int i = 0; i < out.length; i++)
			out[i] = ((Number) os[i]).floatValue();
		return out;
	}

	public static double[] unboxDouble(Object[] os) {
		if (os == null)
			return null;
		double[] out = new double[os.length];
		for (int i = 0; i < out.length; i++)
			out[i] = ((Number) os[i]).doubleValue();
		return out;
	}

	public static int[] unboxInteger(Object[] os) {
		if (os == null)
			return null;
		int[] out = new int[os.length];
		for (int i = 0; i < out.length; i++)
			out[i] = ((Number) os[i]).intValue();
		return out;
	}

	// ---------------------------------------------------

	public static <K> K[] concatArray(K[] a1, K[] a2) {
		int l = a1.length;
		a1 = Arrays.copyOf(a1, l + a2.length);
		System.arraycopy(a1, 0, a1, 0, l);
		System.arraycopy(a2, 0, a1, l, a2.length);
		return a1;
	}

	public static int[] concatArray(int[] a1, int[] a2) {
		int l = a1.length;
		a1 = Arrays.copyOf(a1, l + a2.length);
		System.arraycopy(a1, 0, a1, 0, l);
		System.arraycopy(a2, 0, a1, l, a2.length);
		return a1;
	}

	public static double[] concatArray(double[] a1, double[] a2) {
		int l = a1.length;
		a1 = Arrays.copyOf(a1, l + a2.length);
		System.arraycopy(a1, 0, a1, 0, l);
		System.arraycopy(a2, 0, a1, l, a2.length);
		return a1;
	}

	public static byte[] concatArray(byte[] a1, byte[] a2) {
		int l = a1.length;
		a1 = Arrays.copyOf(a1, l + a2.length);
		System.arraycopy(a1, 0, a1, 0, l);
		System.arraycopy(a2, 0, a1, l, a2.length);
		return a1;
	}

	public static boolean[] concatArray(boolean[] a1, boolean[] a2) {
		int l = a1.length;
		a1 = Arrays.copyOf(a1, l + a2.length);
		System.arraycopy(a1, 0, a1, 0, l);
		System.arraycopy(a2, 0, a1, l, a2.length);
		return a1;
	}

	// ---------------------------------------------------

	public static void show(double[][] a, double[][] y) {
		if (y == null)
			show(a);
		else {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++)
					System.out.print(String.format("%14g ", a[i][j]));
				System.out.println();
			}
			System.out.println();
			for (int i = 0; i < y.length; i++) {
				for (int j = 0; j < y[i].length; j++)
					System.out.print(String.format("%14g ", y[i][j]));
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void show(double[][] a, double[] y) {
		if (y == null)
			show(a);
		else {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++)
					System.out.print(String.format("%14g ", a[i][j]));
				System.out.print(String.format("\u2502 %14g\n", y[i]));
			}
			System.out.println();
		}
	}

	public static void show(double[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j]);
				if (j != mat[i].length - 1)
					System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static String toStringD(double[][] matrix) {
		String out = "[";
		for (int i = 0; i < matrix.length; i++)
			out += (Arrays.toString(matrix[i]) + ", ");
		out = out.substring(0, out.length() - 2);
		out += "]";
		return out;
	}

	/**
	 * Função que retorna um vetor de sequências de caracteres a partir de um vetor
	 * de objetos
	 * 
	 * @param objs
	 * @return
	 */
	public static String[] toStringA(Object[] objs) {
		String[] out = new String[objs.length];
		for (int i = 0; i < objs.length; i++)
			out[i] = objs[i].toString();
		return out;
	}

	public static String[] toString(int[] is) {
		String[] out = new String[is.length];
		for (int i = 0; i < is.length; i++)
			out[i] = String.valueOf(is[i]);
		return out;
	}

	public static String[] toString(String format, int[] is) {
		String[] out = new String[is.length];
		for (int i = 0; i < is.length; i++)
			out[i] = String.format(format, is[i]);
		return out;
	}

	/**
	 * Função que transforma uma sequência de caracteres, agrupados por colchetes,
	 * em um vetor de <code>String</code>. Essa função desfaz a que
	 * {@link Arrays#toString(Object[])} faz.
	 * 
	 * @param string sequência de caracteres representativa
	 * @return vetor de <code>String</code>
	 */
	public static String[] fromString(String string) {
		String[] out = string.substring(1, string.length() - 1).split(",");
		for (int i = 1; i < out.length; i++) {
			if (" ".equals(out[i]))
				out[i] = "";
			else
				out[i] = out[i].substring(1);
		}

		return out;
	}

	/**
	 * Função que transforma uma sequência de caracteres, agrupados por colchetes,
	 * em um vetor de duas dimensões (i.e. uma matriz) de <code>String</code>. Essa
	 * função desfaz a que {@link Arrays#deepToString(Object[])} faz.
	 * 
	 * @param string sequência de caracteres representativa
	 * @return matriz de <code>String</code>
	 */
	public static String[][] fromString2(String string) {
		String[] interm = string.substring(1, string.length() - 1).split(",(?![^\\[]*\\])");
		String[][] out = new String[interm.length][];

		for (int i = 0; i < out.length; i++)
			out[i] = fromString(interm[i].trim());

		return out;
	}

	// ---------------------------------------------------

	public static double[][] copyOf(double[][] in) {
		double[][] out = new double[in.length][];
		for (int i = 0; i < in.length; i++)
			out[i] = Arrays.copyOf(in[i], in[i].length);
		return out;
	}
}
