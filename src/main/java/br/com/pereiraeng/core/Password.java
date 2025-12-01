package br.com.pereiraeng.core;

public class Password {
	
	private String password;

	public Password() {
		this("");
	}

	public Password(String password) {
		setPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
