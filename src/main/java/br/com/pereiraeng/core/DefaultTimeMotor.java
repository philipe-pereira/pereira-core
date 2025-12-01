package br.com.pereiraeng.core;

import java.util.Calendar;

/**
 * Classe do objeto que cadencia a execução de uma função.
 * 
 * @author Philipe PEREIRA
 *
 */
public class DefaultTimeMotor implements Runnable {

	private TimeRefresh tr;

	private boolean on = false;
	private boolean pause = false;

	private int t = 0;
	private int tMax = Integer.MAX_VALUE;
	private Calendar now;

	/**
	 * tempo entre duas atualizações, em milisegundos
	 */
	private long stepL = 1000L;

	/**
	 * offset sobre o instante de tempo considerado com o 'agora'
	 */
	private int offset = 0;

	/**
	 * Função que estabelece qual a classe que terá a função
	 * {@link TimeRefresh#fireTimeRefresh()} chamada para cada repintura promovida
	 * pelo motor temporal
	 * 
	 * @param tr classe com a função a ser invocada a cada chamada de
	 *           {@link TimeRefresh#fireTimeRefresh() função} associada ao motor
	 *           temporal
	 */
	public void setTimeRefresh(TimeRefresh tr) {
		this.tr = tr;
	}

	/**
	 * Função que estabelece o offset sobre o instante de tempo considerado com o
	 * 'agora'
	 * 
	 * @param offset offset, em milissegundos
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * 
	 * @return
	 *         <ul>
	 *         <li>no modo síncrono, ver {@link TimeRefresh#getUnit()} e
	 *         {@link TimeRefresh#getWindow()};</i>
	 *         <li>no modo assíncrono, número entre 0 e {@link #getTmax() o máximo},
	 *         acrescido a cada {@link #setStep(float) passo} de segundos.</i>
	 *         </ul>
	 */
	public int getT() {
		return t;
	}

	/**
	 * 
	 * @return no modo assíncrono, o valor máximo que o {@link #getT() contador}
	 *         pode assumir
	 */
	public int getTmax() {
		return this.tMax;
	}

	/**
	 * 
	 * @param tMax no modo assíncrono, o valor máximo que o {@link #getT() contador}
	 *             pode assumir
	 */
	public void setTmax(int tMax) {
		this.tMax = tMax;
	}

	public Calendar getNow() {
		return now;
	}

	/**
	 * Função que estipula o valor, em segundos, do passo de tempo entre duas
	 * chamadas da {@link TimeRefresh#fireTimeRefresh() função} do motor temporal
	 * 
	 * @param step número decimal que indica o tempo entre duas atualizações, em
	 *             segundos
	 */
	public void setStep(float step) {
		setStep((long) (step * 1000L));
	}

	/**
	 * Função que estipula o valor, em milissegundos, do passo de tempo entre duas
	 * chamadas da {@link TimeRefresh#fireTimeRefresh() função} do motor temporal
	 * 
	 * @param step tempo entre duas atualizações, em milisegundos
	 */
	public void setStep(long step) {
		if (step > 0f)
			this.stepL = step;
	}

	public boolean isOn() {
		return on;
	}

	// -------------------------- RUNNABLE --------------------------

	/**
	 * Função que dá início a execução da animação
	 */
	public void play() {
		if (!on)
			new Thread(this).start();
	}

	/**
	 * Função que pausa a execução da animação
	 */
	public void pause() {
		if (on) {
			on = false;
			pause = true;
		} else if (pause) {
			pause = false;
			play();
		}
	}

	/**
	 * Função que suspende a execução da animação
	 */
	public void stop() {
		boolean wasOn = on;
		on = false;
		t = 0;
		if (wasOn) {
			try {
				// esperar o Thread anterior acabar
				Thread.sleep(stepL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		on = true;
		try {
			while (on) {
				// contador
				this.now = Calendar.getInstance();
				if (offset != 0L)
					this.now.add(Calendar.MILLISECOND, offset);

				if (tr != null) {
					// possíveis valores de t:
					switch (tr.getUnit()) {
					case TimeRefresh.NOT: // contador
						break;
					case Calendar.SECOND:
						switch (tr.getWindow()) {
						case Calendar.DAY_OF_MONTH:
							t = this.now.get(Calendar.HOUR_OF_DAY) * 3600;
						case Calendar.HOUR_OF_DAY:
							t = this.now.get(Calendar.MINUTE) * 60;
						case Calendar.MINUTE:
							t = this.now.get(Calendar.SECOND);
							break;
						default:
							t = 0;
							System.out.println("Offset desconhecido");
							break;
						}
						break;
					case Calendar.MINUTE:
						switch (tr.getWindow()) {
						case Calendar.DAY_OF_MONTH:
							t = this.now.get(Calendar.HOUR_OF_DAY) * 60;
						case Calendar.HOUR_OF_DAY:
							t = this.now.get(Calendar.MINUTE);
						default:
							t = 0;
							System.out.println("Offset desconhecido");
							break;
						}
						break;
					case Calendar.HOUR_OF_DAY:
						switch (tr.getWindow()) {
						case Calendar.DAY_OF_MONTH:
							t = this.now.get(Calendar.HOUR_OF_DAY);
						default:
							t = 0;
							System.out.println("Offset desconhecido");
							break;
						}
						break;
					}
					tr.fireTimeRefresh();
				} else { // contador
					t++;
					if (t == tMax)
						t = 0;
				}
				beforeSleep();
				// dormir
				Thread.sleep(stepL);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void beforeSleep() {
	}
}