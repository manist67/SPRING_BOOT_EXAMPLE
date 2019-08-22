package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kr.hsoft.boot.domain.ApplicationDomain;
import kr.hsoft.boot.exception.ApplicationNotFoundException;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.service.ApplicationService;
import kr.hsoft.boot.service.AuthService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
	@Autowired
	ApplicationService applicationService;

	@Autowired
	AuthService authService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> ListUp(@RequestHeader HashMap<String, String> header, @RequestBody ApplicationDomain body) throws AuthNotFoundException, UserNotFoundException{
		String authLevel;
		List<ApplicationDomain> resultList = null;
		System.out.println("bye");

		try {
			authLevel = authService.getAuthLevel(header.get("token"));
		
		} catch(AuthNotFoundException e) {
		
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch(UserNotFoundException e) {
		
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

		resultList = applicationService.getApplications(authLevel, body.getUser().getSeq());

		return new ResponseEntity<List<ApplicationDomain>>(resultList, HttpStatus.OK);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	public ResponseEntity<?> getApplicationDetail(@PathVariable("seq") int seq) throws ApplicationNotFoundException {
		ApplicationDomain detailResult;
		System.out.println("HI");

		try {
			detailResult = applicationService.getSelectedApplication(seq);
		} catch (ApplicationNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ApplicationDomain>(detailResult, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> postApplication(@RequestBody ApplicationDomain applicationDomain) {
		applicationService.insertApplication(applicationDomain);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateApplication(@PathVariable("seq") int seq,@RequestBody @NotNull ApplicationDomain applicationDomain) throws ApplicationNotFoundException {
		
		applicationService.updateApplication(applicationDomain);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}