package br.com.pereiraeng.core.split;

import java.lang.reflect.Field;

public interface Splitable {

	public boolean isFieldEditable(Field field);

	public boolean isFieldVisible(Field field);
}