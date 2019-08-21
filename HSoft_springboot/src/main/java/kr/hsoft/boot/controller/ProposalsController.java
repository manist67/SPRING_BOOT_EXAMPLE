package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.hsoft.boot.domain.ProposalsDomain;

@RestController
@RequestMapping("/proposals")
public class ProposalsController {
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public String getProposals(
			@RequestHeader HashMap<String, String> header, 
			@RequestParam(required=false) HashMap<String, String> params) {
		
		return "hello";
	}

}
