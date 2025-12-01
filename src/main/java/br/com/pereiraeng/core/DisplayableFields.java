package br.com.pereiraeng.core;

/**
 * Interface dos objetos cujos campos compõe uma lista que pode ser consultados
 * 
 * @author Philipe PEREIRA
 *
 */
public interface DisplayableFields {
	/**
	 * Função que retorna o número de campos
	 * 
	 * @return inteiro com o número de campos
	 */
	public int getFieldCount();

	/**
	 * Função que retorna um vetor com os nomes dos campos
	 * 
	 * @return vetor com as referências dos campos
	 */
	public String getFieldName(int index);

	/**
	 * Função que retorna o valor de um dado campo
	 * 
	 * @param index numeração do campo
	 * @return valor do campo
	 */
	public Object getField(int index);
}
