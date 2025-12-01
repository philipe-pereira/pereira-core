package br.com.pereiraeng.core.split;

import java.awt.Color;
import java.io.File;
import java.util.Calendar;

import br.com.pereiraeng.core.ColorUtils;
import br.com.pereiraeng.core.StringUtils;
import br.com.pereiraeng.core.TimeUtils;

/**
 * Classe que representa uma variável de um objeto, indicando qual o nome dessa
 * variável, seu valor, sua classe e se ele pode ser alterado.
 * 
 * @author Philipe Pereira
 * 
 */
public class Property {
	protected String name;

	protected Object value;

	protected Class<?> classValue;

	protected boolean editable;

	/**
	 * Construtor da propriedade do objeto, onde a variável é adicionada por seu
	 * objeto equivalente e sua classe por um objeto do tipo
	 * <code>Class<?></code>
	 * 
	 * @param name
	 *            nome da variável, sem espaços
	 * @param value
	 *            valor da variável (que se confunde com a variável em si)
	 * @param classValue
	 *            classe da variável (deve ser igual a
	 *            <code>value.getClass()</code>)
	 * @param editable
	 *            se o parâmetro é editável ou não
	 */
	public Property(String name, Object value, Class<?> classValue,
			boolean editable) {
		this.name = name;
		this.value = value;
		this.classValue = classValue;
		this.editable = editable;
	}

	/**
	 * Construtor da propriedade do objeto, onde a variável e sua classe são
	 * adicionadas na forma de sequências de caracteres
	 * 
	 * @param name
	 *            nome da variável, sem espaços
	 * @param className
	 *            nome da classe na forma de um <code>String</code>, a ser
	 *            confirmado com o comando <code>Class.forName(className)</code>
	 * @param value
	 *            <code>String</code> que representa a variável
	 * @param editable
	 *            se o parâmetro é editável ou não
	 */
	public Property(String name, String className, String value,
			boolean editable) {
		this.name = name;
		this.editable = editable;
		parse(className, value);
	}

	/**
	 * Função que retorna o nome da variável
	 * 
	 * @return nome da variável sem espaços
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Função que retorna o nome da variável com espaços sendo adicionados no
	 * esquema <i>CamelCase</i>.
	 * 
	 * @return nome da variável com espaços
	 */
	public String getEditionName() {
		return StringUtils.addSpace(this.name);
	}

	/**
	 * Função que retorna a variável
	 * 
	 * @return a variável
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Função que estabelece o valor da variável
	 * 
	 * @param value
	 *            novo valor da variável
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getClassValue() {
		return this.classValue;
	}

	public boolean isEditable() {
		return this.editable;
	}

	public String toString() {
		return "(" + name + ";" + format() + ";"
				+ this.classValue.getSimpleName() + ";" + this.editable + ")";
	}

	public String getXML() {
		String out = "<" + this.name + ">" + this.format() + "</" + this.name
				+ ">\n";
		return out;
	}

	private String format() {
		if (this.value != null) {
			if (value instanceof Calendar)
				return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS",
						this.value);
			else
				// TODO
				return this.value.toString();
		} else {
			return "";
		}
	}

	/**
	 * Função que a partir do nome da classe da variável e de sua representação
	 * na forma de uma sequência de caracteres padronizada retorna a variável na
	 * forma de um <code>Object</code>. Essa função é importante na serialização
	 * de um objeto.
	 * 
	 * @param className
	 *            da classe na forma de um <code>String</code>, a ser confirmado
	 *            com o comando <code>Class.forName(className)</code>
	 * @param value
	 *            <code>String</code> que representa a variável
	 * @throws ClassNotFoundException
	 *             caso a classe indicada em <code>className</code> não exista,
	 *             uma exceção é lançada
	 */
	private void parse(String className, String value) {
		this.classValue = getClass(className);

		if (this.classValue != null) {
			if (this.classValue.equals(String.class)) {
				this.value = value;
			} else if (this.classValue.equals(Integer.class)) {
				this.value = Integer.parseInt(value);
			} else if (this.classValue.equals(Float.class)) {
				this.value = Float.parseFloat(value);
			} else if (this.classValue.equals(Boolean.class)) {
				this.value = Boolean.parseBoolean(value);
			} else if (this.classValue.equals(Double.class)) {
				this.value = Double.parseDouble(value);
			} else if (this.classValue.equals(File.class)) {
				if (value.startsWith("$")) {
					this.value = new File(ClassLoader.getSystemResource(value)
							.getFile());
				} else {
					this.value = new File(value);
				}
			} else if (this.classValue.isEnum()) { // ENUM
				for (Object o : this.classValue.getEnumConstants()) {
					if (o.toString().equals(value)) {
						this.value = o;
						break;
					}
				}
			} else if (this.classValue.isArray()) { // ARRAY
				// TODO
			} else if (this.classValue.equals(Color.class)) {
				this.value = ColorUtils.stringJava2Color(value);
			} else if (this.classValue.equals(Calendar.class)) {
				this.value = TimeUtils.string2Calendar(value);
			} else {
				this.value = Property.createObjt(value);
			}
		}
	}

	private Class<?> getClass(String classe) {
		try {
			return Class.forName(classe);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Object createObjt(String value) {
		System.err.println("ainda nao!!");
		// TODO Auto-generated method stub
		return null;
	}
}