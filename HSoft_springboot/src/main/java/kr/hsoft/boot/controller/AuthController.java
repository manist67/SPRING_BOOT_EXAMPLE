package kr.hsoft.boot.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hsoft.boot.domain.AuthDomain;
import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.service.AuthService;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
	@Autowired
	AuthService authService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getUserInfo(@RequestHeader HashMap<String, String> header) {
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		UserDomain user;
		try {
			user = authService.getUser(token);
		} catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		} catch(AuthNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<UserDomain>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST) 
	public ResponseEntity<?> login(@RequestBody @Valid LoginDomain loginInfo, Errors error){
		if(error.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		AuthDomain auth;
		try {
			auth = authService.login(loginInfo);
		} catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<AuthDomain>(auth , HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST) 
	public ResponseEntity<?> logout(@RequestHeader @Valid HashMap<String, String> header) {
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		authService.logout(header.get("token"));
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
}
