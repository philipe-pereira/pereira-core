package br.com.pereiraeng.core;

import java.io.Serializable;

/**
 * Classe que representa um dado usuário do programa, com seu nome, login e
 * senha
 * 
 * @author Philipe Pereira
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String login;
	private Password password;

	/**
	 * Construtor da classe
	 * 
	 * @param name
	 *            nome do usuário
	 * @param login
	 *            login do usuário
	 * @param password
	 *            senha de acesso
	 */
	public User(String name, String login, Password password) {
		super();
		this.nome = name;
		this.login = login;
		this.password = password;
	}

	public String toString() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Password getPassword() {
		return password;
	}

	public void setSenha(Password senha) {
		this.password = senha;
	}
}