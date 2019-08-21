package kr.hsoft.boot.domain;

import javax.validation.constraints.NotEmpty;

public class LoginDomain {
	@NotEmpty(message = "need the id")
	private String id;
	@NotEmpty(message = "need the password")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
