package br.com.pereiraeng.core;

public interface EditFields {

	/**
	 * Função que recebe os valores atuais de certos parâmetros e retorna os valores
	 * após edição do usuário
	 * 
	 * @param description   descrição dos parâmetros
	 * @param fieldNames    nome dos campos
	 * @param initialValues valoes iniciais dos parâmetros
	 * @return vetor com os valores após edição
	 */
	public Object[] editFields(String description, String[] fieldNames, Object... initialValues);

}
