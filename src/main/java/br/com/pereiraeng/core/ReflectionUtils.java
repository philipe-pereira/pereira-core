package br.com.pereiraeng.core;

import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Philipe PEREIRA
 *
 */
public class ReflectionUtils {

	public static Constructor<?> getNoArgsConst(Class<?> classInput) {
		Constructor<?>[] constructors = classInput.getConstructors();
		Constructor<?> c = null;
		for (int i = 0; i < constructors.length; i++) {
			if (constructors[i].getParameterTypes().length == 0)
				c = constructors[i];
		}
		return c;
	}

	/**
	 * Função que retorna o objeto 'default' de uma dada classe. Esse método é
	 * chamado quando a classe do objeto não possui construtor sem argumentos, de
	 * modo que não é possível instanciar um objeto sem haver tais argumentos.
	 * 
	 * @param class1 classe do objeto
	 * @return objeto padrão
	 */
	public static Object getNull(Class<?> class1) {
		if (Object.class.equals(class1))
			return null;

		Object out = null;
		try { // construtor padrão é bom...
			out = class1.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		}

		if (out == null) {
			// não tem construtor padrão...
			if (Integer.class.equals(class1))
				out = 0;
			else if (Long.class.equals(class1))
				out = 0L;
			else if (Double.class.equals(class1))
				out = 0.;
			else if (Boolean.class.equals(class1))
				out = false;
			else if (Float.class.equals(class1))
				out = 0f;
			else if (Calendar.class.equals(class1))
				out = Calendar.getInstance();
			else if (Date.class.equals(class1))
				out = Calendar.getInstance().getTime();
			else if (class1.isEnum())
				out = class1.getEnumConstants()[0];
			else
				System.err.println(class1 + " não tem construtor default");
		}

		return out;
	}

	public static Object[] getNulls(Class<?>... clazz) {
		Object[] out = new Object[clazz.length];
		for (int i = 0; i < clazz.length; i++)
			out[i] = getNull(clazz[i]);
		return out;
	}

	/**
	 * Função que converte um número decimal num objeto parametrizado
	 * 
	 * @param t amostra do objeto número
	 * @param z valor decimal
	 * @return objeto parametrizado
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T double2number(T t, double z) {
		if (t instanceof Double)
			return (T) new Double(z);
		else if (t instanceof Integer)
			return (T) Integer.valueOf((int) z);
		else if (t instanceof Float)
			return (T) new Float(z);
		else if (t instanceof Long)
			return (T) new Long((long) z);
		else
			return null;
	}
}
