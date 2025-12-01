package br.com.pereiraeng.core;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Enumeração das possíveis direções de um dado desenho ou âncora. Há nove
 * opções: as direções cardeais, as direções colaterais e a central
 * 
 * @author Philipe PEREIRA
 *
 */
public enum Direction {
	UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT, CENTER;

	/**
	 * Função que retorna o próximo item da enumeração, seguindo a rotação do
	 * sentido horário
	 * 
	 * @return próxima direção no sentido horário (somente as direções cardinais)
	 */
	public Direction next() {
		return next(false);
	}

	public Direction oposite() {
		switch (this) {
		case DOWN:
			return UP;
		case DOWN_RIGHT:
			return UP_LEFT;
		case DOWN_LEFT:
			return UP_RIGHT;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case UP_RIGHT:
			return DOWN_LEFT;
		case UP_LEFT:
			return DOWN_RIGHT;
		case UP:
			return DOWN;
		default:
			return null;
		}
	}

	/**
	 * Função que retorna o próximo item da enumeração, seguindo a rotação do
	 * sentido horário
	 * 
	 * @param extended se <code>true</code>, inclui-se as diagonais na rotação
	 * @return próxima direção no sentido horário
	 */
	public Direction next(boolean extended) {
		return Direction.values()[(this.ordinal() + (extended ? 1 : 2)) % 8];
	}

	/**
	 * Função que retorna o próximo item da enumeração, seguindo a rotação do
	 * sentido anti-horário
	 * 
	 * @return próxima direção no sentido anti-horário
	 */
	public Direction previous() {
		return Direction.values()[ExtendedMath.mod(this.ordinal() - 2, 8)];
	}

	public String getCardinal() {
		switch (this) {
		case DOWN:
			return "south";
		case DOWN_RIGHT:
			return "south east";
		case DOWN_LEFT:
			return "south west";
		case LEFT:
			return "west";
		case RIGHT:
			return "east";
		case UP_RIGHT:
			return "north east";
		case UP_LEFT:
			return "north west";
		case UP:
			return "north";
		case CENTER:
			return "center";
		}
		return null;
	}

	

	public boolean isVertical() {
		return this == Direction.UP || this == Direction.DOWN;
	}

	public Orientation getOrientation() {
		return isVertical() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
	}

	public Point add(Point p, int length) {
		return add(p, length, true);
	}

	public Point add(Point p, int length, boolean graphics2D) {
		Point out = new Point(p);
		switch (this) {
		case DOWN:
			if (graphics2D)
				out.y += length;
			else
				out.y -= length;
			break;
		case DOWN_RIGHT:
			out.x += length;
			if (graphics2D)
				out.y += length;
			else
				out.y -= length;
			break;
		case DOWN_LEFT:
			out.x -= length;
			if (graphics2D)
				out.y += length;
			else
				out.y -= length;
			break;
		case LEFT:
			out.x -= length;
			break;
		case RIGHT:
			out.x += length;
			break;
		case UP_RIGHT:
			out.x += length;
			if (graphics2D)
				out.y -= length;
			else
				out.y += length;
			break;
		case UP_LEFT:
			out.x -= length;
			if (graphics2D)
				out.y -= length;
			else
				out.y += length;
			break;
		case UP:
			if (graphics2D)
				out.y -= length;
			else
				out.y += length;
			break;
		case CENTER:
			break;
		}
		return out;
	}

	public static Direction from(Point2D.Float center, Point2D.Float p) {
		return from(center, p, true);
	}

	/**
	 * 
	 * @param center     ponto de referência
	 * @param p          ponto
	 * @param graphics2D <code>true</code> quando a posição vertical cresce para
	 *                   valor de abscissas descendentes, <code>false</code> quando
	 *                   a posição vertical cresce para valor de abscissas
	 *                   crescentes
	 * @return direção
	 */
	public static Direction from(Point2D.Float center, Point2D.Float p, boolean graphics2D) {
		float xc = center.x;
		float yc = center.y;

		float xp = p.x;
		float yp = p.y;

		if (yp > yc) { // baixo, esquerda ou direita
			float dy = yp - yc; // >0

			if (xp > xc) { // baixo, direita
				float dx = xp - xc; // >0

				if (dx > dy)
					return Direction.RIGHT;
				else
					return graphics2D ? Direction.DOWN : Direction.UP;
			} else { // esqueda, baixo
				float dx = xc - xp; // >0
				if (dx > dy)
					return Direction.LEFT;
				else
					return graphics2D ? Direction.DOWN : Direction.UP;
			}
		} else { // cima, esquerda ou direita
			float dy = yc - yp; // >0

			if (xp > xc) { // cima, direita
				float dx = xp - xc; // >0

				if (dx > dy)
					return Direction.RIGHT;
				else
					return graphics2D ? Direction.UP : Direction.DOWN;
			} else { // esqueda, cima
				float dx = xc - xp; // >0
				if (dx > dy)
					return Direction.LEFT;
				else
					return graphics2D ? Direction.UP : Direction.DOWN;
			}
		}
	}

	/**
	 * Quadrante
	 * 
	 * @param center
	 * @param p
	 * @param graphics2D
	 * @return
	 */
	public static Direction quad(Point2D.Float center, Point2D.Float p, boolean graphics2D) {
		float xc = center.x;
		float yc = center.y;

		float xp = p.x;
		float yp = p.y;

		if (yp > yc) { // baixo, esquerda ou direita
			if (xp > xc) { // baixo, direita
				return graphics2D ? Direction.DOWN_RIGHT : Direction.UP_RIGHT;
			} else { // esqueda, baixo
				return graphics2D ? Direction.DOWN_LEFT : Direction.UP_LEFT;
			}
		} else { // cima, esquerda ou direita
			if (xp > xc) { // cima, direita
				return graphics2D ? Direction.UP_RIGHT : Direction.DOWN_RIGHT;
			} else { // esqueda, cima
				return graphics2D ? Direction.UP_LEFT : Direction.DOWN_LEFT;
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public Direction[] decomp() {
		switch (this) {
		case DOWN_LEFT:
			return new Direction[] { DOWN, LEFT };
		case DOWN_RIGHT:
			return new Direction[] { DOWN, RIGHT };
		case UP_LEFT:
			return new Direction[] { UP, LEFT };
		case UP_RIGHT:
			return new Direction[] { UP, RIGHT };
		default:
			return null;
		}
	}
}
