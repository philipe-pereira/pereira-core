package br.com.pereiraeng.core;

/**
 * Classe de funções matemáticas
 * 
 * @author Philipe PEREIRA
 *
 */
public class ExtendedMath {

	// =========================== CONSTANTES ===========================

	/**
	 * 2 pi
	 */
	public static final double TWO_PI = 6.283185307179586476925286766559;

	/**
	 * 2 pi sobre 3
	 */
	public static final double PI_23 = 2.0943951023931954923084289221863;

	/**
	 * 4 pi sobre 3
	 */
	public static final double PI_43 = 4.1887902047863909846168578443727;

	/**
	 * pi sobre 2
	 */
	public static final double PI_2 = 1.5707963267948966192313216916398;

	/**
	 * pi sobre 3
	 */
	public static final double PI_3 = 1.0471975511965977461542144610932;

	/**
	 * pi sobre 6
	 */
	public static final double PI_6 = 0.52359877559829887307710723054658;

	/**
	 * 3 pi sobre 2
	 */
	public static final double PI3_2 = 4.7123889803846898576939650749193;

	/**
	 * raiz quadrada de 2
	 */
	public static final double SQRT2 = 1.4142135623730950488016887242097;

	/**
	 * raiz quadrada de 3
	 */
	public static final double SQRT3 = 1.7320508075688772935274463415059;

	/**
	 * raiz quadrada de 3 sobre 2
	 */
	public static final double SQRT3_2 = 0.8660254037844386;

	/**
	 * logaritmo natural de 2
	 */
	public static final double LN_2 = 0.69314718055994530941723212145818;

	/**
	 * 2 dividido por pi
	 */
	public static final double TWO_BY_PI = 2. / Math.PI;

	/**
	 * 2 dividido por raiz de 3
	 */
	public static final double TWO_SQRT3 = 2 / Math.sqrt(3);

	/**
	 * {@link Math#floorMod(int, int)}
	 * 
	 * @param dividend dividend
	 * @param divisor  divisor
	 * @return
	 */
	public static int mod(int dividend, int divisor) {
		int out = dividend % divisor;
		return dividend < 0 ? out + divisor : out;
	}

	/**
	 * {@link Math#floorMod(int, int)}
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static long mod(long dividend, long divisor) {
		long out = dividend % divisor;
		return dividend < 0 ? out + divisor : out;
	}

	/**
	 * {@link Math#floorMod(int, int)}
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public static float mod(float dividend, float divisor) {
		float out = dividend % divisor;
		return dividend < 0 ? out + divisor : out;
	}

	/**
	 * {@link Math#floorMod(int, int)}
	 * 
	 * @param dividend dividend
	 * @param divisor  divisor
	 * @return
	 */
	public static double mod(double dividend, double divisor) {
		double out = dividend % divisor;
		return dividend < 0 ? out + divisor : out;
	}

	/**
	 * Função que retorna o fatorial de um número inteiro
	 * 
	 * @param i número inteiro
	 * @return fatorial
	 */
	public static long fatorial(long i) {
		if (i > 1)
			return i * fatorial(i - 1);
		else
			return 1;
	}

	/**
	 * Função que calcula o mímino múltiplo comum de um conjunto de número inteiros
	 * 
	 * @param values inteiros
	 * @return MMC
	 */
	public static int mmc(int... values) {
		if (values.length == 0)
			return 1;
		else if (values.length == 1)
			return values[0];
		int out = 1;
		int divisor = 2;
		boolean flag = true;
		while (flag) {
			boolean isDivisible = false;
			for (int i = 0; i < values.length; i++) {
				if (values[i] % divisor == 0) {
					values[i] /= divisor;
					isDivisible = true;
				}
			}
			if (!isDivisible) // se não dividiu ninguém...
				divisor++;
			else
				out *= divisor;

			flag = false;
			for (int j = 0; j < values.length; j++)
				flag |= values[j] != 1;
		}
		return out;
	}

	/**
	 * Função que calcula o máximo divisor comum de um conjunto de número inteiros
	 * 
	 * @param values inteiros
	 * @return MDC
	 */
	public static int mdc(int... values) {
		if (values.length == 0)
			return 1;
		else if (values.length == 1)
			return values[0];
		int out = values[0];
		for (int i = 1; i < values.length; i++)
			out = mdc(out, values[i]);
		return out;
	}

	private static int mdc(int i, int j) {
		return (j == 0) ? i : mdc(j, i % j);
	}

	public static int min(int... is) {
		int min = Integer.MAX_VALUE;
		for (int i : is)
			min = Math.min(min, i);
		return min;
	}

	public static float min(float... ds) {
		float min = Float.POSITIVE_INFINITY;
		for (float d : ds)
			min = Math.min(min, d);
		return min;
	}

	public static double min(double... ds) {
		double min = Double.POSITIVE_INFINITY;
		for (double d : ds)
			min = Math.min(min, d);
		return min;
	}

	/**
	 * Função que retorna o mínimo absoluto de um vetor de números decimais
	 * 
	 * @param ds vetor de números decimais
	 * @return mínimo absoluto
	 */
	public static double minA(double... ds) {
		double min = Double.POSITIVE_INFINITY;
		for (double d : ds)
			min = Math.min(min, Math.abs(d));
		return min;
	}

	public static int max(int... is) {
		int max = Integer.MIN_VALUE;
		for (int i : is)
			max = Math.max(max, i);
		return max;
	}

	public static float max(float... ds) {
		float max = Float.NEGATIVE_INFINITY;
		for (int i = 0; i < ds.length; i++)
			max = Math.max(max, ds[i]);
		return max;
	}

	public static double max(double... ds) {
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < ds.length; i++)
			max = Math.max(max, ds[i]);
		return max;
	}

	/**
	 * Função que retorna o máximo absoluto de um vetor de números decimais
	 * 
	 * @param ds vetor de números decimais
	 * @return máximo absoluto
	 */
	public static double maxA(double... ds) {
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < ds.length; i++)
			max = Math.max(max, Math.abs(ds[i]));
		return max;
	}

	public static double maxA(int length, double... ds) {
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < length; i++)
			max = Math.max(max, Math.abs(ds[i]));
		return max;
	}

	@SafeVarargs
	public static <K extends Comparable<K>> K minO(K... cs) {
		K c = cs[0];
		for (int i = 1; i < cs.length; i++)
			if (cs[i].compareTo(c) < 0)
				c = cs[i];
		return c;
	}

	@SafeVarargs
	public static <K extends Comparable<K>> K maxO(K... cs) {
		K c = cs[0];
		for (int i = 1; i < cs.length; i++)
			if (cs[i].compareTo(c) > 0)
				c = cs[i];
		return c;
	}

	/**
	 * Função que indica se um número está entre outro dois
	 * 
	 * @param n número
	 * @param a um dos números extremos
	 * @param b outro dos números extremos
	 * @return <code>true</code> se o número estiver entre os outros dois,
	 *         <code>false</code> senão
	 */
	public static boolean between(int n, int a, int b) {
		if (a < b)
			return n >= a && n <= b;
		else
			return n >= b && n <= a;
	}

	public static double round(double f, int casas) {
		double d = Math.pow(10., casas);
		return Math.round(f * d) / d;
	}

	public static final double TOL = 1E-6;

	/**
	 * Função que determina, com uma certa {@link #TOL tolerância} , se dois números
	 * decimais são iguais
	 * 
	 * @param a um número decimal
	 * @param b outro número decimal
	 * @return <code>true</code> se os dois números decimais são iguais,
	 *         <code>false</code> se são diferentes
	 */
	public static boolean equals(double a, double b) {
		return Math.abs(a - b) < TOL;
	}

	public static boolean isMinusZero(double det) {
		return !Double.valueOf(det).equals(Double.valueOf(0.));
	}

	// =========================== FUNÇÕES ===========================

	/**
	 * Função que retorna 0 para argumentos menores que 0, um dado valor para
	 * argumentos acima desse valor e o próprio argumento, senão
	 * 
	 * @param x   argumento
	 * @param max valor máximo da função
	 * @return valor entre 0 e o máximo
	 */
	public static double rampa(double x, double max) {
		if (x < 0.)
			return 0.;
		if (x > max)
			return max;
		return x;
	}

	// ========================= FUNÇÕES ESPECIAIS =========================

	// gama

	private static final int GAMMA_EULER_INFINITE_TERMS = 10_000_000;

	/**
	 * <ol start="0">
	 * <li>relação de Euler (lenta convergência);</i>
	 * <li>fórmula de Lanczos (rápida convergência).</i>
	 * </ol>
	 */
	private static final int GAMMA_METHOD = 1;

	private static final int LANCZOS_G = 7;
	private static final double[] LANCZOS_P = { 0.99999999999980993, 676.5203681218851, -1259.1392167224028,
			771.32342877765313, -176.61502916214059, 12.507343278686905, -0.13857109526572012, 9.9843695780195716e-6,
			1.5056327351493116e-7 };

	/**
	 * Função que retorna o valor da função gama para um dado número real
	 * 
	 * @param z número real
	 * @return valor da função gama
	 */
	public static double gama(double z) {
		if (z >= 2.0) {
			return (z - 1) * gama(z - 1);
		} else if (z < 1) {
			return gama(z + 1) / z;
		} else if (z == 1) {
			return 1;
		} else if (z == 1.5) {
			return 0.5 * Math.sqrt(Math.PI);
		} else { // z > 1 and z < 2 and z != 1.5
			double out = Double.NaN;
			switch (GAMMA_METHOD) {
			case 0: // Euler
				out = 1 / z;
				for (int n = 1; n < GAMMA_EULER_INFINITE_TERMS; n++)
					out *= Math.pow(1. + 1. / n, z) / (1. + z / n);
				break;
			case 1: // Lanczos
				z -= 1.;
				double x = LANCZOS_P[0];
				for (int i = 1; i < LANCZOS_P.length; i++)
					x += LANCZOS_P[i] / (z + i);
				double t = z + LANCZOS_G + 0.5;
				out = Math.sqrt(TWO_PI) * Math.pow(t, z + .5) * Math.exp(-t) * x;
				break;
			}
			return out;
		}
	}

	// Bessel de tipo I

	private static final int BESSEL_INFINITE_TERMS = 13;

	/**
	 * Função que calcula o valor da função de Bessel de tipo I de uma dada ordem
	 * para um dado valor (J<sub>&#x03B1;</sub>)
	 * 
	 * @param x     argumento da função
	 * @param alpha ordem da função
	 * @return valor da função
	 */
	public static double bessel(double x, double alpha) {
		return bessel(x, alpha, true);
	}

	private static double bessel(double x, double alpha, boolean alternate) {
		double out = 0.;
		for (int m = 0; m < BESSEL_INFINITE_TERMS; m++) {
			double p = Math.pow(x / 2, 2 * m + alpha) / (fatorial(m) * gama(m + 1 + alpha));
			if (alternate && m % 2 == 1)
				p *= -1.;
			out += p;
		}
		return out;
	}

	// Neumann (a.k.a. Bessel de tipo II)

	/**
	 * Função que retorna o valor da função de Neumann (também conhecida como função
	 * de Bessel de tipo II) de uma dada ordem para um dado valor
	 * (Y<sub>&#x03B1;</sub>)
	 * 
	 * @param x     argumento da função
	 * @param alpha ordem da função
	 * @return valor da função
	 */
	public static double neumann(double x, double alpha) {
		if (((int) alpha) == alpha)
			return neumannIntegerOrder(x, (int) alpha);
		else
			return (bessel(x, alpha) * Math.cos(alpha * Math.PI) - bessel(x, -alpha)) / Math.sin(alpha * Math.PI);
	}

	private static double neumannIntegerOrder(double x, int alpha) {
		if (alpha == 0 || alpha == 1) {
			return neumann01(x, alpha);
		} else {
			double tox = 2. / x;
			double yn = neumann01(x, 1), ynl1 = neumann01(x, 0), out = 0;
			for (int j = 1; j < alpha; j++) {
				out = j * tox * yn - ynl1;
				ynl1 = yn;
				yn = out;
			}
			return out;
		}
	}

	private static double neumann01(double x, int alpha) {
		final double[][] p = { { 1., -.1098628627e-2, .2734510407e-4, -.2073370639e-5, .2093887211e-6 },
				{ 1., .183105e-2, -.3516396496e-4, .2457520174e-5, -.240337019e-6 } },
				q = { { -.1562499995e-1, .1430488765e-3, -.6911147651e-5, .7621095161e-6, -.934945152e-7 },
						{ .04687499995, -.2002690873e-3, .8449199096e-5, -.88228987e-6, .105787412e-6 } },
				r = { { -2957821389., 7062834065., -512359803.6, 10879881.29, -86327.92757, 228.4622733 },
						{ -.4900604943e13, .1275274390e13, -.5153438139e11, .7349264551e9, -.4237922726e7,
								.8511937935e4 } },
				s = { { 40076544269., 745249964.8, 7189466.438, 47447.26470, 226.1030244, 1. }, { .2499580570e14,
						.4244419664e12, .3733650367e10, .2245904002e8, .1020426050e6, .3549632885e3, 1. } };

		if (x < 8) {
			double y = x * x, num = r[alpha][5], dem = (alpha == 0) ? s[alpha][5] : (s[alpha][5] + y * s[alpha][6]);
			for (int i = 4; i >= 0; i--) {
				num = (r[alpha][i] + y * num);
				dem = (s[alpha][i] + y * dem);
			}
			return (alpha == 0 ? 1 : x) * (num / dem)
					+ .636619772 * (bessel(x, alpha) * Math.log(x) - (alpha == 0 ? 0 : 1 / x));
		} else {
			double y = Math.pow(8. / x, 2), xx = x - (2 * alpha + 1) * Math.PI / 4, py = p[alpha][4], qy = q[alpha][4];
			for (int i = 3; i >= 0; i--) {
				py = (p[alpha][i] + y * py);
				qy = (q[alpha][i] + y * qy);
			}
			return Math.sqrt(.636619772 / x) * (Math.sin(xx) * py + (alpha == 0 ? 1 : 8. / x) * Math.cos(xx) * qy);
		}
	}

	// Bessel modificada de tipo I

	/**
	 * Função que calcula o valor da função de Bessel modificada de tipo I de uma
	 * dada ordem para um dado valor (I<sub>&#x03B1;</sub>)
	 * 
	 * @param x     argumento da função
	 * @param alpha ordem da função
	 * @return valor da função
	 */
	public static double besselI(double x, double alpha) {
		return bessel(x, alpha, false);
	}

	// Bessel modificada de tipo II

	/**
	 * Função que calcula o valor da função de Bessel modificada de tipo II de uma
	 * dada ordem para um dado valor (K<sub>&#x03B1;</sub>)
	 * 
	 * @param x     argumento da função
	 * @param alpha ordem da função
	 * @return valor da função
	 */
	public static double besselK(double x, double alpha) {
		if (((int) alpha) == alpha)
			return besselKintegerOrder(x, (int) alpha);
		else
			return ExtendedMath.PI_2 * (besselI(x, -alpha) - besselI(x, alpha)) / Math.sin(alpha * Math.PI);
	}

	private static double besselKintegerOrder(double x, int n) {
		if (n == 0)
			return besselK0(x);
		if (n == 1)
			return besselK1(x);

		double tox = 2 / x;
		double bkm = besselK0(x);
		double out = besselK1(x);
		for (int j = 1; j < n; j++) {
			double bkp = bkm + j * tox * out;
			bkm = out;
			out = bkp;
		}
		return out;
	}

	/**
	 * <p>
	 * Função que calcula a função de Bessel modificada do tipo II de ordem 0
	 * </p>
	 * 
	 * M.Abramowitz and I.A.Stegun, Handbook of Mathematical Functions, Mathematics
	 * Series vol. 55 (1964), Washington.
	 * 
	 * @param x variável da função
	 * @return valor da função
	 */
	private static double besselK0(double x) {
		final double p1 = -0.57721566, p2 = 0.42278420, p3 = 0.23069756, p4 = 3.488590e-2, p5 = 2.62698e-3,
				p6 = 1.0750e-4, p7 = 7.4e-6;
		final double q1 = 1.25331414, q2 = -7.832358e-2, q3 = 2.189568e-2, q4 = -1.062446e-2, q5 = 5.87872e-3,
				q6 = -2.51540e-3, q7 = 5.3208e-4;

		double out = 0;

		if (x <= 2) {
			double y = x * x / 4;
			out = (-Math.log(x / 2.) * besselI(x, 0))
					+ (p1 + y * (p2 + y * (p3 + y * (p4 + y * (p5 + y * (p6 + y * p7))))));
		} else {
			double y = 2 / x;
			out = (Math.exp(-x) / Math.sqrt(x)) * (q1 + y * (q2 + y * (q3 + y * (q4 + y * (q5 + y * (q6 + y * q7))))));
		}
		return out;
	}

	/**
	 * <p>
	 * Função que calcula a função de Bessel modificada do tipo II de ordem 1
	 * </p>
	 * 
	 * M.Abramowitz and I.A.Stegun, Handbook of Mathematical Functions, Applied
	 * Mathematics Series vol. 55 (1964), Washington.
	 * 
	 * @param x variável da função
	 * @return valor da função
	 */
	private static double besselK1(double x) {
		final double p1 = 1., p2 = 0.15443144, p3 = -0.67278579, p4 = -0.18156897, p5 = -1.919402e-2, p6 = -1.10404e-3,
				p7 = -4.686e-5;
		final double q1 = 1.25331414, q2 = 0.23498619, q3 = -3.655620e-2, q4 = 1.504268e-2, q5 = -7.80353e-3,
				q6 = 3.25614e-3, q7 = -6.8245e-4;

		double y = 0, result = 0;

		if (x <= 2) {
			y = x * x / 4;
			result = (Math.log(x / 2.) * besselI(x, 1))
					+ (1. / x) * (p1 + y * (p2 + y * (p3 + y * (p4 + y * (p5 + y * (p6 + y * p7))))));
		} else {
			y = 2 / x;
			result = (Math.exp(-x) / Math.sqrt(x))
					* (q1 + y * (q2 + y * (q3 + y * (q4 + y * (q5 + y * (q6 + y * q7))))));
		}
		return result;
	}

	// Kelvin-Bessel

	/**
	 * Parte real da função de Kelvin-Bessel
	 * 
	 * @param z  argumento
	 * @param nu ordem da função
	 * @return valor da função
	 */
	public static double ber(double z, double nu) {
		double out = 0.;
		for (int n = 0; n < BESSEL_INFINITE_TERMS; n++) {
			out += Math.cos((3. * nu / 4. + n / 2.) * Math.PI) * Math.pow(z / 2., 2. * n + nu)
					/ (fatorial(n) * gama(n + nu + 1.));
		}
		return out;
	}

	/**
	 * Parte imaginária da função de Kelvin-Bessel
	 * 
	 * @param z  argumento da função
	 * @param nu ordem da função
	 * @return valor da função
	 */
	public static double bei(double z, double nu) {
		double out = 0.;
		for (int n = 0; n < BESSEL_INFINITE_TERMS; n++) {
			out += Math.sin((3. * nu / 4. + n / 2.) * Math.PI) * Math.pow(z / 2., 2. * n + nu)
					/ (fatorial(n) * gama(n + nu + 1.));
		}
		return out;
	}

	// hiperbólica inversa

	public static double asinh(double x) {
		return Math.log(x + Math.sqrt(x * x + 1.));
	}

	public static double acosh(double x) {
		return Math.log(x + Math.sqrt(x * x - 1.));
	}

	public static double atanh(double x) {
		return .5 * Math.log((1. + x) / (1. - x));
	}

	/**
	 * Função que retorna o valor da função de Haversine para um dado valor
	 * 
	 * @param x argumento da função
	 * @return valor da função
	 */
	public static double haversin(double x) {
		return (1 - Math.cos(x)) / 2;
	}

	/**
	 * Função que retorna o valor da função inversa de Haversine para um dado valor
	 * 
	 * @param theta argumento da função
	 * @return valor da função
	 */
	public static double ahaversin(double a) {
		return Math.acos(1 - 2 * a);
	}

	/**
	 * Função que calcula o resta da divisão por 360 para um ângulo, de modo a
	 * eliminar ângulos com mais de uma volta.
	 * 
	 * Uma fórmula alternativa analítica para o cálculo é:
	 * 
	 * (float) (angle - 360f * Math.floor(0.5f + angle / 360f))
	 * 
	 * @param angle ângulo em graus
	 * @return ângulo em graus cujo valor se encontra entre -180 e 180 graus
	 */
	public static float circularDegree(float angle) {
		float e = angle % 360f;
		if (e < -180f)
			return 360f + e;
		else if (e > 180f)
			return e - 360f;
		else
			return e;
	}

	/**
	 * Função que calcula o resta da divisão por 360 para um ângulo, de modo a
	 * eliminar ângulos com mais de uma volta.
	 * 
	 * Uma fórmula alternativa analítica para o cálculo é:
	 * 
	 * (float) (angle - 360f * Math.floor(0.5f + angle / 360f))
	 * 
	 * @param angle ângulo em graus
	 * @return ângulo em graus cujo valor se encontra entre -180 e 180
	 */
	public static double circularDegree(double angle) {
		double e = angle % 360.;
		if (e < -180.)
			return 360. + e;
		else if (e > 180.)
			return e - 360.;
		else
			return e;
	}

	/**
	 * Função que calcula o resta da divisão por 2 pi para um ângulo, de modo a
	 * eliminar ângulos com mais de uma volta.
	 * 
	 * Uma fórmula alternativa analítica para o cálculo é:
	 * 
	 * radians - TWO_PI * Math.floor(0.5 + radians / TWO_PI)
	 * 
	 * @param radians ângulo em radianos
	 * @return ângulo em radianos cujo valor se encontra entre -pi e pi
	 */
	public static double circularRadians(double radians) {
		double e = radians % TWO_PI;
		if (e < -Math.PI)
			return TWO_PI + e;
		else if (e > Math.PI)
			return e - TWO_PI;
		else
			return e;
	}

	// ========================= GEOMETRIA ANALÍTICA ===========================

	/**
	 * Função que acha o valor da abscissa para o qual a reta passa pela eixo
	 * horizontal (ordenada nula)
	 * 
	 * @param x0 abscissa de um ponto
	 * @param x1 abscissa de outro ponto
	 * @param y0 ordenada de um ponto
	 * @param y1 ordenada de outro ponto
	 * @return abscissa de ordenada nula da reta
	 */
	public static double getZero(double x0, double x1, double y0, double y1) {
		return (y0 * x1 - x0 * y1) / (y0 - y1);
	}

	// =========================== SÉRIES ===========================

	/**
	 * Função que retorna a soma de uma PA
	 * 
	 * @param a0 termo inicial
	 * @param n  número de termos
	 * @param r  razão
	 * @return soma da PA
	 */
	public static double sumPA(double a0, int n, double r) {
		return n * (2 * a0 + (n - 1) * r) / 2;
	}

	/**
	 * Função que retorna as duas soluções de uma equação do segundo grau, escrita
	 * na forma ax^2+bx+c=0
	 * 
	 * @param a termo do segundo grau
	 * @param b termo do primeiro grau
	 * @param c termo independente
	 * @return vetor de números decimais com duas posições, com as soluções em ordem
	 *         crescente
	 */
	public static double[] bhaskara(double a, double b, double c) {
		double a2 = 2 * a;
		double d = Math.sqrt(b * b - 4 * a * c);
		double x1 = (-b - d) / a2;
		double x2 = (-b + d) / a2;
		return x1 < x2 ? new double[] { x1, x2 } : new double[] { x2, x1 };
	}

	public static double log2(int l) {
		return Math.log(l) / LN_2;
	}

	public static int countDig10(int number) {
		if (number < 100000) {
			if (number < 100) {
				if (number < 10)
					return 1;
				else
					return 2;
			} else {
				if (number < 1000) {
					return 3;
				} else {
					if (number < 10000)
						return 4;
					else
						return 5;
				}
			}
		} else {
			if (number < 10000000) {
				if (number < 1000000)
					return 6;
				else
					return 7;

			} else {
				if (number < 100000000)
					return 8;
				else {
					if (number < 1000000000)
						return 9;
					else
						return 10;
				}
			}
		}
	}
}