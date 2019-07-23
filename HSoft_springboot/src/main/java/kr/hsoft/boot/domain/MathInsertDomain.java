package kr.hsoft.boot.domain;

import javax.validation.constraints.NotNull;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class MathInsertDomain {
	@NotNull
	private float op1;
	@NotNull
	private float op2;
	
	public float getOp1() {
		return op1;
	}
	public void setOp1(float op1) {
		this.op1 = op1;
	}
	public float getOp2() {
		return op2;
	}
	public void setOp2(float op2) {
		this.op2 = op2;
	}
	
	public String toString() {
		return "op 1 : " + op1 + " op 2 : " + op2;
	}
}
