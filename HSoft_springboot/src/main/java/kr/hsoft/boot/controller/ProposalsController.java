package kr.hsoft.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import kr.hsoft.boot.domain.ProposalDomain;
import kr.hsoft.boot.service.ProposalService;


@RestController
@RequestMapping("/proposals")
public class ProposalsController {
	@Autowired
	ProposalService proposalService;
	
	
	@RequestMapping(value= "/{seq}", method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ProposalDomain getProposal(@PathVariable("seq") int seq) {
		
		ProposalDomain proposal = proposalService.getProposal(seq);
		return proposal;
		
	}

}
