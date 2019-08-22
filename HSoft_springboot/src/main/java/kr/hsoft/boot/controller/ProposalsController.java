package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
		ProposalDomain proposal = proposalService.getProposal(seq, user);
		return new ResponseEntity<ProposalDomain>(proposal, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposals(@RequestHeader @Valid HashMap<String, String> header) {
		// 토큰 확인
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		// 권한 확인
		String authLevel;
		try {
			authLevel = authService.getAuthLevel(header.get("token"));
		} catch(AuthNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		if(authLevel == "ADMIN") {
			// 지역구만 받아오기
			List<ProposalDomain> proposals = proposalService.getProposals();
			return new ResponseEntity<List<ProposalDomain>>(proposals, HttpStatus.OK);
		}else if(authLevel == "MASTER") {
			List<ProposalDomain> proposals = proposalService.getProposals();
			return new ResponseEntity<List<ProposalDomain>>(proposals, HttpStatus.OK);
			
		}else { // user
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@CrossOrigin("http://localhostL:3000")
	public ResponseEntity<?> postProposal(@RequestHeader @Valid HashMap<String, String> header, 
			@RequestBody ProposalDomain proposalDomain) throws UserNotFoundException, AuthNotFoundException{
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
		
		// 권한이랑 상관 없을 듯
		if(authService.getUser(token) == proposalService.getProposal(seq, userDomain).getUser()) {
			proposalService.putProposal(proposalDomain);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	

}
