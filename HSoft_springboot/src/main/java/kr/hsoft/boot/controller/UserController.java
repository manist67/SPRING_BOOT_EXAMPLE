package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.domain.ProposalReadDomain;
import kr.hsoft.boot.domain.SignUpDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.SignUpErrorException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.service.AuthService;
import kr.hsoft.boot.service.UserService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getUsers(@RequestHeader HashMap<String, String> header, PaginationDomain pagination) {
		String authLevel;

		try {
			authLevel = authService.getAuthLevel(header.get("token"));
		} catch(AuthNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		if(authLevel == "USER") return new ResponseEntity<>(null, HttpStatus.FORBIDDEN); 
		/**
		 * 이게맞는지 고민좀 해보자 현수야
		 */
		List<UserDomain> users = userService.getUsers(pagination);

		return new ResponseEntity<List<UserDomain>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> postUser(@RequestBody @Valid SignUpDomain signUpDomain, Errors errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.postUsers(signUpDomain);
		} catch (SignUpErrorException e) {
			System.out.println(e.getErrorInfo());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value= "/{seq}/proposals", method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getUserProposals(@RequestHeader HashMap<String, String> header, @PathVariable("seq") int seq){
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		List<ProposalReadDomain> proposals = userService.getUserProposals(seq);
		return new ResponseEntity<>(proposals, HttpStatus.OK);
	}
}

/**
 * 1. 컨트롤러를 만든다. => api 정의서에 있는 url따라서 오는
 * 2. 컨트롤러에서 필요한 데이터가 무엇인지 생각한다. // 데이터를 어떻게 제어할 건지
 * 3. 그 제어에 따른 데이터모음을 생각한다.
 * 4. 데이터를 제어할 로직을 생각. 
 * 5. 데이터베이스를 어떻게 활용할지 
 */