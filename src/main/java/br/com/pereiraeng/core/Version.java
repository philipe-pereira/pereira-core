package br.com.pereiraeng.core;

/**
 * Classe do objeto que representa a versão de um aplicativo
 */
public class Version implements Comparable<Version> {

	private final int major;

	private final int minor;

	private final int patch;

	/**
	 * Construtor do objeto que representa a versão de um aplicativo
	 * 
	 * @param major versão maior
	 * @param minor versão menor
	 * @param patch patch
	 */
	public Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	/**
	 * Construtor do objeto que representa a versão de um aplicativo
	 * 
	 * @param string sequência de caracteres na forma [major].[minor].[patch]
	 */
	public Version(String string) {
		String[] majorMinorPatch = string.split("\\.");
		this.major = Integer.parseInt(majorMinorPatch[0]);
		this.minor = Integer.parseInt(majorMinorPatch[1]);
		this.patch = Integer.parseInt(majorMinorPatch[2]);
	}

	@Override
	public String toString() {
		return String.format("%d.%d.%d", major, minor, patch);
	}

	@Override
	public int compareTo(Version version) {
		if (this.major == version.major) {
			if (this.minor == version.minor)
				return this.patch - version.patch;
			else
				return this.minor - version.minor;
		} else
			return this.major - version.major;
	}
}
