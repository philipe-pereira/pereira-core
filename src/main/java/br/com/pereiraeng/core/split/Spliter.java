package br.com.pereiraeng.core.split;

import java.lang.reflect.Field;
import java.util.LinkedList;

import br.com.pereiraeng.core.StringUtils;

public class Spliter {

	/**
	 * Função que retorna a lista de {@link Property propriedades} de uma dado
	 * objeto (i.e., seus campos já com seus respectivos valores e demais)
	 * 
	 * @param splitable
	 *            objeto cujos campos serão extraídos
	 * @return vetor com as propriedades do objeto
	 */
	public static Property[] getFields(Splitable splitable) {
		LinkedList<Property> fields = new LinkedList<Property>();
		try {
			for (Field field : splitable.getClass().getFields()) {
				if (splitable.isFieldVisible(field)) {
					Object o = field.get(splitable);
					fields.add(new Property(field.getName(), o, field.getType(),
							splitable.isFieldEditable(field)));
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return fields.toArray(new Property[fields.size()]);
	}

	/**
	 * Função que carrega um dado campo de um objeto com um dado valor
	 * 
	 * @param splitable
	 *            {@link Splitable objeto} cujo campo será carregado
	 * @param property
	 *            {@link Property propriedades} de uma dado objeto, contendo
	 *            informações do campo alvo e do seu conteúdo
	 */
	public static void setFields(Splitable splitable, Property property) {
		try {
			Field f = splitable.getClass().getField(property.getName());
			Object value = property.getValue();
			if (value != null)
				f.set(splitable, value);
		} catch (SecurityException | NoSuchFieldException
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Função que retorna a lista dos nomes dos campos de um dado objeto
	 * {@link Splitable}.
	 * 
	 * @param splitable
	 *            {@link Splitable objeto} cujos nomes dos campos serão
	 *            extraídos
	 * @return vetor com os nomes dos campos visíveis do objeto
	 */
	public static String[] getFieldsName(Splitable splitable) {
		LinkedList<String> fieldsName = new LinkedList<String>();
		try {
			for (Field f : splitable.getClass().getFields()) {
				if (splitable.isFieldVisible(f)) {
					fieldsName.add(StringUtils.addSpace(f.getName()));
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return fieldsName.toArray(new String[fieldsName.size()]);
	}
}