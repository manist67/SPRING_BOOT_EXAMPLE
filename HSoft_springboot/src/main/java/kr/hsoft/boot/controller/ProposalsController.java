package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getProposal(@PathVariable("seq") int seq) {
		
		ProposalDomain proposal = proposalService.getProposal(seq);
		return new ResponseEntity<ProposalDomain>(proposal, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposals() {
		
		List<ProposalDomain> proposals = proposalService.getProposals();
		return new ResponseEntity<List<ProposalDomain>>(proposals, HttpStatus.OK);
		
	}

}
