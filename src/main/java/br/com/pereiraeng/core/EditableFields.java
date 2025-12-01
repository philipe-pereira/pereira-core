package br.com.pereiraeng.core;

/**
 * Interface dos objetos cujos campos compõe uma lista que pode ser editada
 * 
 * @author Philipe PEREIRA
 *
 */
public interface EditableFields extends DisplayableFields {

	/**
	 * Função que o valor de um dado campo é estabelecido
	 * 
	 * @param index numeração do campo
	 * @param obj   valor do campo
	 */
	public void setField(int index, Object obj);
}
