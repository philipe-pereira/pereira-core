package br.com.pereiraeng.core;

import java.awt.geom.Point2D;

/**
 * Enumeração das possíveis orientações de um dado desenho
 * 
 * @author Philipe PEREIRA
 *
 */
public enum Orientation {
	HORIZONTAL, VERTICAL;

	/**
	 * Função que retorna a outra direção possível
	 * 
	 * @return horizontal se vertical, e vice-versa.
	 */
	public Orientation next() {
		return this == HORIZONTAL ? VERTICAL : HORIZONTAL;
	}

	/**
	 * 
	 * @param center ponto de referência
	 * @param p      ponto
	 * @return orientação
	 */
	public static Orientation from(Point2D.Float center, Point2D.Float p) {
		return Direction.from(center, p).getOrientation();
	}
}
