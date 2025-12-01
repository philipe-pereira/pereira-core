package br.com.pereiraeng.core;

import java.util.Calendar;

/**
 * Classe do objeto que representa uma mensagem, com remetente e data
 * 
 * @author Philipe PEREIRA
 *
 */
public class WrittenNote {
	private final int id;

	private String user;
	private Calendar date;
	private String text;

	/**
	 * Construtor do objeto que representa uma mensagem
	 * 
	 * @param user rementente
	 * @param date data
	 * @param text mensagem
	 */
	public WrittenNote(String user, Calendar date, String text) {
		this(user, date, text, -1);
	}

	/**
	 * Construtor do objeto que representa uma mensagem
	 * 
	 * @param user rementente
	 * @param date data
	 * @param text mensagem
	 * @param id   número identificador da mensagem
	 */
	public WrittenNote(String user, Calendar date, String text, int id) {
		this.user = user;
		this.date = date;
		this.text = text;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object anObject) {
		if (this == anObject)
			return true;
		if (anObject instanceof WrittenNote) {
			WrittenNote wn = (WrittenNote) anObject;
			if (this.getId() == -1 && wn.getId() == -1) // se não está se usando o ID
				return this.user.equals(wn.getUser()) && this.date.equals(wn.getDate());
			else
				return this.getId() == wn.getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("%td/%1$tm/%1$tY %2$s \"%3$s...\"", date, user,
				text.substring(0, Math.min(10, text.length())));
	}
}
