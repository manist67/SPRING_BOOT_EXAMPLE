package kr.hsoft.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.hsoft.boot.component.CalculatorComponent;
import kr.hsoft.boot.domain.MathInsertDomain;
import kr.hsoft.boot.domain.MathResultDomain;
import kr.hsoft.boot.service.MathResultService;

@RestController
@RequestMapping("/math")
public class MathController {
	@Autowired
	private CalculatorComponent calculator;
	
	@Autowired
	private MathResultService mathResultService;

	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
	@RequestMapping(method=RequestMethod.GET)
	public List<MathResultDomain> main() {
		List<MathResultDomain> resultDomain = mathResultService.getResults();
		
		return resultDomain;
	}

	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public MathResultDomain add(@RequestBody @Valid MathInsertDomain insert) {
		MathResultDomain resultDomain = new MathResultDomain();
		
		float op1 = insert.getOp1();
		float op2 = insert.getOp2();
		
		resultDomain.setExpression(op1 + " + " + op2);
		resultDomain.setResult(calculator.add(op1, op2));
		
		mathResultService.insertResult(resultDomain);
		
		return resultDomain;
	}
}
