package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kr.hsoft.boot.domain.ApplicationDomain;
import kr.hsoft.boot.domain.UserDomain;
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
	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
	public ResponseEntity<?> getApplications(@RequestHeader HashMap<String, String> header) {
		UserDomain userDomain;
		try {
			userDomain = authService.getUser(header.get("token"));
		} catch(AuthNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

		List<ApplicationDomain> resultList = applicationService.getApplications(userDomain);

		return new ResponseEntity<List<ApplicationDomain>>(resultList, HttpStatus.OK);
	}

	@RequestMapping(value="/{seq}", method=RequestMethod.GET)
	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
	public ResponseEntity<?> getApplicationDetail(@RequestHeader HashMap<String, String> header, @PathVariable("seq") int seq) {
		UserDomain userDomain;
		try {
			userDomain = authService.getUser(header.get("token"));
		} catch(AuthNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		ApplicationDomain detailResult;
		try {
			detailResult = applicationService.getApplication(seq, userDomain);
		} catch (ApplicationNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (AuthenticationException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<ApplicationDomain>(detailResult, HttpStatus.OK);
	}

	/* @RequestMapping(value="/{seq}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateApplication(@PathVariable("seq") int seq,@RequestBody @NotNull ApplicationDomain applicationDomain) throws ApplicationNotFoundException {
		
		applicationService.updateApplication(applicationDomain);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	} */
}