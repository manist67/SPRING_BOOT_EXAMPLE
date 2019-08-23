package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import kr.hsoft.boot.domain.ApplicationDomain;
import kr.hsoft.boot.domain.ApplicationWriteDomain;
import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.domain.ProposalReadDomain;
import kr.hsoft.boot.domain.ProposalWriteDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.service.ApplicationService;
import kr.hsoft.boot.service.AuthService;
import kr.hsoft.boot.service.ProposalService;


@RestController
@RequestMapping("/proposals")
public class ProposalsController {
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	AuthService authService;
		
	@Autowired
	ApplicationService applicationService;
	
	@RequestMapping(value= "/{seq}", method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposal(@RequestHeader @Valid HashMap<String, String> header, @PathVariable("seq") int seq) {
		ProposalReadDomain proposal = proposalService.getProposal(seq);
		return new ResponseEntity<ProposalReadDomain>(proposal, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{seq}/applications", method=RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposalApplications(@PathVariable int seq){
		List<ApplicationDomain> proposalApplication = applicationService.getApplicationsByProposal(seq);
		
		if (proposalApplication==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(proposalApplication, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{seq}/applications", method=RequestMethod.POST)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposalApplications(
			@RequestHeader HashMap<String, String> header, 
			@PathVariable int seq, @RequestBody @Valid ApplicationWriteDomain application,
			Errors errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		UserDomain user;
		try {
			user = authService.getUser(token);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch (AuthNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		applicationService.postApplication(seq, user, application);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{seq}/applications/count", method=RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposalApplicationCount(@PathVariable int seq){
		int applicationCount = applicationService.getApplicationsByProposal(seq).size();

		return new ResponseEntity<>(applicationCount, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposals(
			@RequestHeader @Valid HashMap<String, String> header, 
			PaginationDomain pagination) {
		// 토큰 확인
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		UserDomain user;
		try {
			user = authService.getUser(token);
		} catch (UserNotFoundException e1) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch (AuthNotFoundException e1) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		List<ProposalReadDomain> proposals;
		try {
			proposals = proposalService.getProposals(user, pagination);			
		} catch( AuthException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		HttpHeaders ReponseHeader = new HttpHeaders();
		ReponseHeader.set("X-PAGE", Integer.toString(pagination.getPage()));
		ReponseHeader.set("X-TOTAL", Integer.toString(pagination.getTotal()));
		ReponseHeader.set("X-UNIT", Integer.toString(pagination.getUnit()));

		return new ResponseEntity<List<ProposalReadDomain>>(proposals, ReponseHeader, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> postProposal(@RequestHeader HashMap<String, String> header, 
			@RequestBody @Valid ProposalWriteDomain proposalDomain, Errors errors) throws UserNotFoundException, AuthNotFoundException{
		if(errors.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		UserDomain userDomain = authService.getUser(token);
		
		proposalService.postProposal(userDomain, proposalDomain);
		return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	@RequestMapping(value= "/{seq}", method = RequestMethod.PUT)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> putProposal(@RequestHeader @Valid HashMap<String, String> header, 
			@RequestBody ProposalWriteDomain proposalDomain, @PathVariable("seq") int seq) throws UserNotFoundException, AuthNotFoundException{
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	
		UserDomain userDomain = authService.getUser(token);
		String authLevel =  userDomain.getAuth();
		
		if(authLevel == "USER"
				&& authService.getUser(token) == proposalService.getProposal(seq).getUser()) {
			proposalService.putProposalForUser(seq, proposalDomain);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}else if(authLevel == "ADMIN") {
			proposalService.putProposalForAdmin(seq, proposalDomain);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}else if(authLevel == "MASTER"
				&& authService.getUser(token).getLocation() == proposalService.getProposal(seq).getUser().getLocation()) {
			proposalService.putProposalState(seq);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	

	@RequestMapping(value= "/{seq}", method = RequestMethod.PATCH)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> patchProposalStatus(@RequestHeader HashMap<String, String> header,
			@RequestBody HashMap<String, Object> body, @PathVariable int seq) {
		proposalService.patchStatus(seq, (Boolean)body.get("status"));

		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{seq}", method = RequestMethod.DELETE)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> deleteProposal(@RequestHeader @Valid HashMap<String, String> header, 
			@PathVariable("seq") int seq) throws UserNotFoundException, AuthNotFoundException{
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	
		UserDomain userDomain = authService.getUser(token);
		String authLevel =  userDomain.getAuth();
		
		if(authLevel == "USER"
				&& authService.getUser(token) == proposalService.getProposal(seq).getUser()) {
			proposalService.deleteProposal(seq);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}else if(authLevel == "ADMIN") {
			proposalService.deleteProposal(seq);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
}
