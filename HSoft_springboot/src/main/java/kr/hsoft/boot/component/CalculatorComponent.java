package kr.hsoft.boot.component;

import org.springframework.stereotype.Component;

@Component
public class CalculatorComponent{
	public float add(float op1, float op2) {
		return op1 + op2;
	}
}
