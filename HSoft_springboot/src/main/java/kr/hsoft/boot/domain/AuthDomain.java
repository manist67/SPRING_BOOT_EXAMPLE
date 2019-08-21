package kr.hsoft.boot.domain;

import java.sql.Timestamp;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class AuthDomain {
	private int seq;
	private int userSeq;
	private String token;
	private String reToken;
	private int expire;
	private Timestamp create;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getReToken() {
		return reToken;
	}
	public void setReToken(String reToken) {
		this.reToken = reToken;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public Timestamp getCreate() {
		return create;
	}
	public void setCreate(Timestamp create) {
		this.create = create;
	}
}
