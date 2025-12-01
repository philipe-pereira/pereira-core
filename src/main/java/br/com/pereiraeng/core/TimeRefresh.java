package br.com.pereiraeng.core;

import java.util.Calendar;

/**
 * Interface das classes que, <strong>além</strong> de serem repintadas
 * periodicamente, também tem a função {@link #fireTimeRefresh()} chamada por
 * {@link DefaultTimeMotor} com uma mesma periodicidade, porém antes da
 * repintura.
 * 
 * @author Philipe PEREIRA
 *
 */
public interface TimeRefresh {

	/**
	 * Constante a ser retornada por {@link #getUnit()} e {@link #getWindow()} para
	 * indicar que o {@link DefaultTimeMotor#getT() contador} não será utilizado.
	 */
	public static final int NOT = -1;

	/**
	 * Função que é chamada por {@link DefaultTimeMotor} ao final de cada
	 * {@link DefaultTimeMotor#setStep(long) ciclo}
	 */
	public void fireTimeRefresh();

	/**
	 * Função que retorna a unidade de tempo (ver {@link Calendar}) do contador
	 * 
	 * @return unidade de tempo (ver {@link Calendar})
	 */
	public int getUnit();

	/**
	 * Função que retorna o tamanho da janela de tempo (ver {@link Calendar})
	 * 
	 * @return tamanho da janela de tempo (ver {@link Calendar})
	 */
	public int getWindow();
}
