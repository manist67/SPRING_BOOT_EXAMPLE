package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import kr.hsoft.boot.domain.ProposalDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.service.AuthService;
import kr.hsoft.boot.service.ProposalService;


@RestController
@RequestMapping("/proposals")
public class ProposalsController {
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	AuthService authService;
		
	@RequestMapping(value= "/{seq}", method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposal(@RequestHeader @Valid HashMap<String, String> header, @PathVariable("seq") int seq) throws UserNotFoundException, AuthNotFoundException {
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		UserDomain user = authService.getUser(token);
		
		ProposalDomain proposal = proposalService.getProposal(seq);
		return new ResponseEntity<ProposalDomain>(proposal, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposals(@RequestHeader @Valid HashMap<String, String> header) throws UserNotFoundException, AuthNotFoundException {
		// 토큰 확인
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		UserDomain user = authService.getUser(token);
		
		List<ProposalDomain> proposals = proposalService.getProposals(user);
		return new ResponseEntity<List<ProposalDomain>>(proposals, HttpStatus.OK);
			
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@CrossOrigin("http://localhostL:3000")
	public ResponseEntity<?> postProposal(@RequestHeader HashMap<String, String> header, 
			@RequestBody @Valid ProposalDomain proposalDomain, Errors errors) throws UserNotFoundException, AuthNotFoundException{
		if(errors.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		UserDomain userDomain = authService.getUser(token);
		
		proposalService.postProposal(userDomain, proposalDomain);
		return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/{seq}", method = RequestMethod.PUT)
	@CrossOrigin("http://localhostL:3000")
	public ResponseEntity<?> putProposal(@RequestHeader @Valid HashMap<String, String> header, 
			@RequestBody ProposalDomain proposalDomain, @PathVariable("seq") int seq) throws UserNotFoundException, AuthNotFoundException{
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	
		UserDomain userDomain = authService.getUser(token);
		String authLevel =  userDomain.getAuth();
		
		if(authLevel == "USER"
				&& authService.getUser(token) == proposalService.getProposal(seq).getUser()) {
			proposalService.putProposal(proposalDomain);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}else if(authLevel == "ADMIN") {
			proposalService.putProposal(proposalDomain);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}else if(authLevel == "MASTER"
				&& authService.getUser(token).getLocation() == proposalService.getProposal(seq).getUser().getLocation()) {
			proposalService.putProposalState();
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
}
