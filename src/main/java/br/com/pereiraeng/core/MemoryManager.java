package br.com.pereiraeng.core;

public interface MemoryManager {

	/**
	 * Função que analisa o estado atual da memória e decide se é necessário ou não
	 * tomar uma ação para reduzir a quantidade de memória usada (ainda que com
	 * perda de tempo)
	 * 
	 * @return <code>true</code> para ação necessária, <code>false</code> para
	 *         desnecessária
	 */
	public boolean isMoreMemoryNeeded(Runtime runtime);
}
