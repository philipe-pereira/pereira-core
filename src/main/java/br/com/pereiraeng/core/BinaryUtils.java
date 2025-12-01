package br.com.pereiraeng.core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Classe das funções de manipulações de binários
 * 
 * @author Philipe PEREIRA
 *
 */
public class BinaryUtils {

	// ----------------------- SHORT <-> BYTES -----------------------
	/**
	 * Função que converte um vetor de inteiros em um vetor de bytes. É a função
	 * inversa de {@link #bytes2shorts(byte[])}.
	 * 
	 * @param ss vetor de inteiros
	 * @return vetor de bytes
	 */
	public static byte[] shorts2bytes(short[] ss) {
		byte[] out = new byte[2 * ss.length];
		for (int i = 0; i < ss.length; i++)
			ByteBuffer.wrap(out, 2 * i, 2).putShort(ss[i]);
		return out;
	}

	/**
	 * Função que converte um vetor de bytes em um vetor de inteiros. É a função
	 * inversa de {@link #shorts2bytes(short[])}.
	 * 
	 * @param bs vetor de bytes
	 * @return vetor de inteiros
	 */
	public static short[] bytes2shorts(byte[] bs) {
		if (bs == null)
			return new short[0];
		short[] out = new short[bs.length / 2];
		for (int j = 0; j < out.length; j++)
			out[j] = ByteBuffer.wrap(bs, 2 * j, 2).getShort();
		return out;
	}

	// ----------------------- INTEGER <-> BYTES -----------------------

	/**
	 * Função que converte um inteiro em um vetor com 4 bytes. É a função inversa de
	 * {@link #bytes2int(byte[])}.
	 * 
	 * @param i número inteiro
	 * @return vetor com 4 bytes
	 */
	public static byte[] int2bytes(int i) {
		byte[] out = new byte[4];
		ByteBuffer.wrap(out).putInt(i);
		return out;
	}

	/**
	 * Função que converte um vetor com 4 bytes em um inteiro. É a função inversa de
	 * {@link #int2bytes(int)}.
	 * 
	 * @param bs vetor com 4 bytes
	 * @return número inteiro
	 */
	public static int bytes2int(byte[] bs) {
		return ByteBuffer.wrap(bs).getInt();
	}

	/**
	 * Função que converte um vetor de inteiros em um vetor de bytes. É a função
	 * inversa de {@link #bytes2ints(byte[])}.
	 * 
	 * @param is vetor de inteiros
	 * @return vetor de bytes
	 */
	public static byte[] ints2bytes(int[] is) {
		byte[] out = new byte[4 * is.length];
		for (int i = 0; i < is.length; i++)
			ByteBuffer.wrap(out, 4 * i, 4).putInt(is[i]);
		return out;
	}

	/**
	 * Função que converte um vetor de bytes em um vetor de inteiros. É a função
	 * inversa de {@link #ints2bytes(int[])}.
	 * 
	 * @param bs vetor de bytes
	 * @return vetor de inteiros
	 */
	public static int[] bytes2ints(byte[] bs) {
		if (bs == null)
			return new int[0];
		int[] out = new int[bs.length / 4];
		for (int j = 0; j < out.length; j++)
			out[j] = ByteBuffer.wrap(bs, 4 * j, 4).getInt();
		return out;
	}

	// ----------------------- FLOAT <-> BYTES -----------------------

	/**
	 * Função que converte um decimal em um vetor com 4 bytes. É a função inversa de
	 * {@link #bytes2float(byte[])}.
	 * 
	 * @param f número decimal
	 * @return vetor com 4 bytes
	 */
	public static byte[] float2bytes(float f) {
		byte[] out = new byte[4];
		ByteBuffer.wrap(out).putFloat(f);
		return out;
	}

	/**
	 * Função que converte um vetor com 4 bytes em um inteiro. É a função inversa de
	 * {@link #float2bytes(int)}.
	 * 
	 * @param bs vetor com 4 bytes
	 * @return número decimal
	 */
	public static float bytes2float(byte[] bs) {
		return ByteBuffer.wrap(bs).getFloat();
	}

	/**
	 * Função que converte um vetor de inteiros em um vetor de bytes. É a função
	 * inversa de {@link #bytes2floats(byte[])}.
	 * 
	 * @param fs vetor de decimais
	 * @return vetor de bytes
	 */
	public static byte[] floats2bytes(float... fs) {
		byte[] out = new byte[4 * fs.length];
		for (int i = 0; i < fs.length; i++)
			ByteBuffer.wrap(out, 4 * i, 4).putFloat(fs[i]);
		return out;
	}

	/**
	 * Função que converte um vetor de bytes em um vetor de decimais. É a função
	 * inversa de {@link #floats2bytes(byte[])}.
	 * 
	 * @param bs vetor de bytes
	 * @return vetor de decimais
	 */
	public static float[] bytes2floats(byte[] bs) {
		if (bs == null)
			return null;
		float[] out = new float[bs.length / 4];
		for (int i = 0; i < out.length; i++)
			out[i] = ByteBuffer.wrap(bs, 4 * i, 4).getFloat();
		return out;
	}

	// ----------------------- LONG <-> BYTES -----------------------

	/**
	 * Função que converte um inteiro em um vetor com 8 bytes. É a função inversa de
	 * {@link #bytes2long(byte[])}.
	 * 
	 * @param l número inteiro
	 * @return vetor com 8 bytes
	 */
	public static byte[] long2bytes(long l) {
		byte[] out = new byte[8];
		ByteBuffer.wrap(out).putLong(l);
		return out;
	}

	/**
	 * Função que converte um vetor com 4 bytes em um inteiro. É a função inversa de
	 * {@link #long2bytes(long)}.
	 * 
	 * @param bs vetor com 8 bytes
	 * @return número inteiro
	 */
	public static long bytes2long(byte[] bs) {
		return ByteBuffer.wrap(bs).getLong();
	}

	// ----------------------- DOUBLE <-> BYTES -----------------------

	/**
	 * Função que converte um inteiro em um vetor com 8 bytes. É a função inversa de
	 * {@link #bytes2double(byte[])}.
	 * 
	 * @param d número decimal
	 * @return vetor de 8 bytes
	 */
	public static byte[] double2bytes(double d) {
		byte[] out = new byte[8];
		ByteBuffer.wrap(out).putDouble(d);
		return out;
	}

	/**
	 * Função que converte um vetor com 4 bytes em um inteiro. É a função inversa de
	 * {@link #double2bytes(double)}.
	 * 
	 * @param bs vetor de 8 bytes
	 * @return número decimal
	 */
	public static double bytes2double(byte[] bs) {
		return ByteBuffer.wrap(bs).getDouble();
	}

	/**
	 * Função que converte um vetor de decimais em um vetor de bytes. É a função
	 * inversa de {@link #bytes2doubles(byte[])}.
	 * 
	 * @param vs vetor de decimais
	 * @return vetor de bytes
	 */
	public static byte[] doubles2bytes(double... vs) {
		byte[] out = new byte[8 * vs.length];
		for (int i = 0; i < vs.length; i++)
			ByteBuffer.wrap(out, 8 * i, 8).putDouble(vs[i]);
		return out;
	}

	/**
	 * Função que converte um vetor de bytes em um vetor de decimais. É a função
	 * inversa de {@link #doubles2bytes(double[])}.
	 * 
	 * @param bs vetor de bytes
	 * @return vetor de decimais
	 */
	public static double[] bytes2doubles(byte[] bs) {
		double[] out = new double[bs.length / 8];
		for (int i = 0; i < out.length; i++)
			out[i] = ByteBuffer.wrap(bs, 8 * i, 8).getDouble();
		return out;
	}

	// ----------------------- BYTE <-> BOOLEAN's -----------------------

	/**
	 * Função que converte um <code>byte</code> (i.e., conjunto de 8 bits) em um
	 * vetor de <code>boolean</code>, sendo que <strong>os bits menos significativos
	 * ficarão nas posições com índice maior</strong>.
	 * 
	 * @param b <code>byte</code> a ser convertido
	 * @param n número de bits a serem lido
	 * @return vetor de <code>boolean</code> com n posições
	 */
	public static boolean[] toBooleans(byte b, int n) {
		return toBooleans(b, n, true);
	}

	/**
	 * Função que converte um <code>byte</code> (i.e., conjunto de 8 bits) em um
	 * vetor de <code>boolean</code>. É a função inversa de
	 * {@link BinaryUtils#toByte(boolean[], boolean)}.
	 * 
	 * @param b     <code>byte</code> a ser convertido
	 * @param n     número de bits a serem lido
	 * @param order se <code>false</code> os bits menos significativos ficarão nas
	 *              posições com índice menor. Se <code>true</code>, os bits menos
	 *              significativos ficarão nas posições com índice maior.
	 * @return vetor de <code>boolean</code> com n posições
	 */
	public static boolean[] toBooleans(byte b, int n, boolean order) {
		boolean[] out = new boolean[n];
		int c = 1;
		if (order) {
			for (int i = 0; i < n; i++) {
				out[n - 1 - i] = ((b & c) > 0);
				c <<= 1;
			}
		} else {
			for (int i = 0; i < n; i++) {
				out[i] = ((b & c) > 0);
				c <<= 1;
			}
		}
		return out;
	}

	/**
	 * Função que converte um <code>int</code> (i.e., conjunto de 32 bits) em um
	 * vetor de <code>boolean</code>. É a função inversa de
	 * {@link BinaryUtils#toInt(boolean[], boolean)}.
	 * 
	 * @param b     <code>int</code> a ser convertido
	 * @param n     número de bits a serem lido
	 * @param order se <code>false</code> os bits menos significativos ficarão nas
	 *              posições com índice menor. Se <code>true</code>, os bits menos
	 *              significativos ficarão nas posições com índice maior.
	 * @return
	 */
	public static boolean[] toBooleans(int b, int n, boolean order) {
		boolean[] out = new boolean[n];
		int c = 1;
		if (order) {
			for (int i = 0; i < n; i++) {
				out[n - 1 - i] = ((b & c) > 0);
				c <<= 1;
			}
		} else {
			for (int i = 0; i < n; i++) {
				out[i] = ((b & c) > 0);
				c <<= 1;
			}
		}
		return out;
	}

	/**
	 * Função que converte um vetor de <code>boolean</code> em um <code>byte</code>.
	 * É a função inversa de {@link BinaryUtils#toBooleans(byte, int, boolean)}.
	 * 
	 * @param bs    vetor de <code>boolean</code>
	 * @param order se <code>false</code> os bits menos significativos ficam nas
	 *              posições com índice menor. Se <code>true</code>, os bits menos
	 *              significativos ficarão nas posições com índice maior.
	 * @return <code>byte</code> correspondente
	 */
	public static byte toByte(boolean[] bs, boolean order) {
		byte b = 0;
		if (order) {
			int l = bs.length - 1;
			for (int i = l; i >= 0; i--)
				if (bs[i])
					b += (1 << (l - i));
		} else {
			for (int i = 0; i < bs.length; i++)
				if (bs[i])
					b += (1 << i);
		}
		return b;
	}

	// ----------------------- INTEGER <-> BOOLEAN's -----------------------

	/**
	 * Função que converte um vetor de <code>boolean</code> em um <code>int</code>.
	 * É a função inversa de {@link BinaryUtils#toBooleans(int, int, boolean)}.
	 * 
	 * @param bs    vetor de <code>boolean</code>
	 * @param order se <code>false</code> os bits menos significativos ficam nas
	 *              posições com índice menor. Se <code>true</code>, os bits menos
	 *              significativos ficarão nas posições com índice maior.
	 * @return <code>int</code> correspondente
	 */
	public static int toInt(boolean[] bs, boolean order) {
		int out = 0;
		if (order) {
			int l = bs.length - 1;
			for (int i = l; i >= 0; i--)
				if (bs[i])
					out += (1 << (l - i));
		} else {
			for (int i = 0; i < bs.length; i++)
				if (bs[i])
					out += (1 << i);
		}
		return out;
	}

	// ------------------- INTEGER <-> BYTES (OTHER ENCODINGS) -------------------

	// middle endian (as on the PDP-11)

	public static int getIntME(byte[] p) {
		return getIntME(p, 0);
	}

	public static int getIntME(byte[] p, int offset) {
		return ((p[2 + offset] & 0xFF) + (p[3 + offset] & 0xFF) * 256 + (p[offset] & 0xFF) * 65536
				+ (p[1 + offset] & 0xFF) * 16777216);
	}

	public static void getBytesME(int n, byte[] p) {
		getBytesME(n, p, 0);
	}

	public static void getBytesME(int n, byte[] p, int offset) {
		p[offset] = (byte) ((n & 0x00ff0000) >> 16);
		p[1 + offset] = (byte) ((n & 0xff000000) >> 24);
		p[2 + offset] = (byte) ((n & 0x000000ff) >> 0);
		p[3 + offset] = (byte) ((n & 0x0000ff00) >> 8);
	}

	// ------------------- DOUBLE <-> BYTES (OTHER ENCODINGS) -------------------

	// VAX

	public static double vax2IEEE(byte[] p) {
		return vax2IEEE(p, 0);
	}

	public static double vax2IEEE(byte[] dbl, int offset) {
		long[] dt = new long[2];

		// Arrange the VAX double so that it may be accessed by a double64_t
		// structure, (two GUInt32s).

		byte[] ib = new byte[8];

		ib[2] = dbl[4 + offset];
		ib[3] = dbl[5 + offset];
		ib[0] = dbl[6 + offset];
		ib[1] = dbl[7 + offset];
		dt[0] = ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).getLong();

		ib[2] = dbl[offset];
		ib[3] = dbl[1 + offset];
		ib[0] = dbl[2 + offset];
		ib[1] = dbl[3 + offset];
		dt[1] = ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).getLong();

		// Save the sign of the double
		int sign = (int) (dt[1] & 0x80000000);

		// Adjust the exponent so that we may work with it
		int exponent = (int) (dt[1] >> 23);
		exponent &= 0x000000ff;

		if (exponent != 0)
			exponent = exponent - 129 + 1023;

		// Save the bits that we are discarding so we can round properly
		int rndbits = (int) (dt[0] & 0x00000007);

		dt[0] >>= 3;
		dt[0] = (dt[0] & 0x1fffffff) | (dt[1] << 29);

		if (rndbits != 0) // que resto é esse? Um dia descubro...
			dt[0] |= 0x00000001;

		// Shift the hi-order int over 3 and insert the exponent and sign
		dt[1] >>= 3;
		dt[1] &= 0x000fffff;
		dt[1] |= (exponent << 20) | sign;

		byte[] db = new byte[8];

		ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).putLong(dt[0]);
		System.arraycopy(ib, 0, db, 0, 4);

		ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).putLong(dt[1]);
		System.arraycopy(ib, 0, db, 4, 4);

		return ByteBuffer.wrap(db).order(ByteOrder.LITTLE_ENDIAN).getDouble();
	}

	public static byte[] IEEE2vax(double d) {
		long[] dt = new long[2];

		byte[] db = new byte[8];
		ByteBuffer.wrap(db).order(ByteOrder.LITTLE_ENDIAN).putDouble(d);

		byte[] ib = new byte[8];
		System.arraycopy(db, 0, ib, 0, 4);
		dt[0] = ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).getLong();

		System.arraycopy(db, 4, ib, 0, 4);
		dt[1] = ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).getLong();

		int sign = (int) (dt[1] & 0x80000000);
		int exponent = (int) (dt[1] >> 20);
		exponent &= 0x000007ff;

		// An exponent of zero means a zero value.
		if (exponent != 0)
			exponent = exponent - 1023 + 129;

		// In the case of overflow, return the largest number we can
		if (exponent > 255) {
			byte[] out = new byte[8];
			Arrays.fill(out, (byte) 0xff);

			if (sign == 0)
				out[1] = (byte) 0x7f;

			return out;
		}

		// In the case of of underflow return zero
		else if ((exponent < 0) || (exponent == 0 && sign == 0)) {
			return new byte[8];
		} else {
			// Shift the fraction 3 bits left and set the exponent and sign
			dt[1] <<= 3;
			dt[1] |= (dt[0] >> 29);
			dt[1] &= 0x007fffff;
			dt[1] = dt[1] | (exponent << 23) | sign;

			dt[0] <<= 3;
		}

		// Convert the double back to VAX format

		ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).putInt((int) dt[1]);
		db[0] = ib[2];
		db[1] = ib[3];
		db[2] = ib[0];
		db[3] = ib[1];

		ByteBuffer.wrap(ib).order(ByteOrder.LITTLE_ENDIAN).putInt((int) dt[0]);
		db[4] = ib[2];
		db[5] = ib[3];
		db[6] = ib[0];
		db[7] = ib[1];

		return db;
	}

	private static final short[] saQuots = { 1600, 40, 1 };

	/**
	 * Convert one 16-bits Radix-50 to ASCII (3 chars).
	 * 
	 * @param sRad50
	 * @param str
	 * @param offset
	 */
	public static void rad50toAscii(short sRad50, char[] str, int offset) {
		char ch = '\0';

		for (int i = 0; i < 3; i++) {
			short sValue = sRad50;
			sValue /= saQuots[i];
			/* Map 0..39 to ASCII */
			if (sValue == 0)
				ch = ' '; /* space */
			else if (sValue >= 1 && sValue <= 26)
				ch = (char) (sValue - 1 + 'A');// printable alpha A..Z
			else if (sValue == 27)
				ch = '$'; /* dollar */
			else if (sValue == 28)
				ch = '.'; /* period */
			else if (sValue == 29)
				ch = ' '; /* unused char, emit a space instead */
			else if (sValue >= 30 && sValue <= 39)
				ch = (char) (sValue - 30 + '0'); // digit 0..9

			str[offset] = ch;
			offset++;

			sRad50 -= (sValue * saQuots[i]);
		}

		/* Do zero-terminate */
		str[offset] = '\0';
	}

	public static short DGNAsciiToRad50(String str) {
		short rad50 = 0;
		int i;

		for (i = 0; i < 3; i++) {
			short value;

			if (i >= str.length()) {
				rad50 = (short) (rad50 * 40);
				continue;
			}

			if (str.charAt(i) == '$')
				value = 27;
			else if (str.charAt(i) == '.')
				value = 28;
			else if (str.charAt(i) == ' ')
				value = 29;
			else if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
				value = (short) (str.charAt(i) - '0' + 30);
			else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
				value = (short) (str.charAt(i) - 'a' + 1);
			else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				value = (short) (str.charAt(i) - 'A' + 1);
			else
				value = 0;

			rad50 = (short) (rad50 * 40 + value);
		}

		return rad50;
	}

	public static byte[] DGNAsciiToRad50b(String str) {
		byte[] out = new byte[2];
		ByteBuffer.wrap(out).order(ByteOrder.LITTLE_ENDIAN).putShort(DGNAsciiToRad50(str));
		return out;
	}

	public static int unsignedShort2int(byte[] array, int offset) {
		return (0xFF & array[offset]) + (0xFF & array[offset + 1]) * 256;
	}

	public static long unsignedInt2long(int ui) {
		return ui & 4294967295L;
	}

	// ---------------------- STRING <-> BOOLEAN's ----------------------

	/**
	 * Função que transforma uma sequência de caracteres '0' e '1' em um vetor de
	 * <code>boolean</code>. É a função inversa de
	 * {@link BinaryUtils#arrayToBits(boolean[])}.
	 * 
	 * @param string <code>String</code> composto por '0' e '1'
	 * @return vetor de bits
	 */
	public static boolean[] toBitArray(String string) {
		boolean[] out = new boolean[string.length()];
		for (int i = 0; i < string.length(); i++)
			out[i] = string.charAt(i) == '1';
		return out;
	}

	/**
	 * Função que transforma um vetor de booleanos em uma sequência de caracteres
	 * '0' e '1'. É a função inversa de {@link BinaryUtils#toBitArray(String)}.
	 * 
	 * @param b vetor de booleanos
	 * @return <code>String</code> representativo
	 */
	public static String arrayToBits(boolean[] b) {
		String out = "";
		for (int i = 0; i < b.length; i++)
			out += b[i] ? "1" : "0";
		return out;
	}

	// ---------------------- STRING <-> BYTES ----------------------

	/**
	 * Função que transforma uma sequência de caracteres '0' e '1' em um vetor de
	 * <code>byte</code>.
	 * 
	 * @param string <code>String</code> composto por '0' e '1'
	 * @return vetor de bytes
	 */
	public static byte[] toBytesArray(String string) {
		string = string.replace(" ", "");
		byte[] out = new byte[(int) Math.ceil(string.length() / 8.)];
		for (int i = 0; i < string.length(); i++) // string é escrito do mais sign. para o menos
			if (string.charAt(string.length() - i - 1) == '1')
				out[i / 8] |= 1 << (i % 8);
		return out;
	}
}
