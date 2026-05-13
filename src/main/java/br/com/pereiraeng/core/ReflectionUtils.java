package br.com.pereiraeng.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe com funções utilitárias do Reflection
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
	 * @param clazz classe do objeto
	 * @return objeto padrão
	 */
	public static Object getNull(Class<?> clazz) {
		if (Object.class.equals(clazz))
			return null;

		Object out = null;
		try { // construtor padrão é bom...
//			class1.getConstructor();
			out = clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
		}

		if (out == null) { // não tem construtor padrão...
			if (Integer.class.equals(clazz))
				out = 0;
			else if (Long.class.equals(clazz))
				out = 0L;
			else if (Double.class.equals(clazz))
				out = 0.;
			else if (Boolean.class.equals(clazz))
				out = false;
			else if (Float.class.equals(clazz))
				out = 0f;
			else if (Calendar.class.equals(clazz))
				out = Calendar.getInstance();
			else if (Date.class.equals(clazz))
				out = Calendar.getInstance().getTime();
			else if (clazz.isEnum())
				out = clazz.getEnumConstants()[0];
			else
				System.err.println(clazz + " não tem construtor default");
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
			return (T) Double.valueOf(z);
		else if (t instanceof Integer)
			return (T) Integer.valueOf((int) z);
		else if (t instanceof Float)
			return (T) Float.valueOf((float) z);
		else if (t instanceof Long)
			return (T) Long.valueOf((long) z);
		else
			return null;
	}

	public static <T> T createInstance(String fullyQualifiedClassName, Class<T> expectedType) {
		try {
			Class<?> rawClass = Class.forName(fullyQualifiedClassName);

			if (!expectedType.isAssignableFrom(rawClass)) {
				throw new IllegalArgumentException(
						"A classe " + fullyQualifiedClassName + " não é do tipo esperado: " + expectedType.getName());
			}

			Class<? extends T> typedClass = rawClass.asSubclass(expectedType);

			return typedClass.getDeclaredConstructor().newInstance();

		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Classe não encontrada: " + fullyQualifiedClassName, e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(
					"A classe precisa ter um construtor sem argumentos: " + fullyQualifiedClassName, e);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Erro ao instanciar classe: " + fullyQualifiedClassName, e);
		}
	}
}
