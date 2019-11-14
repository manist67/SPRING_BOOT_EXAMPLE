package kr.hsoft.boot.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
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
	
	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
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
	
	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
	@RequestMapping(value="/logout", method=RequestMethod.POST) 
	public ResponseEntity<?> logout(@RequestHeader @Valid HashMap<String, String> header) {
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		authService.logout(header.get("token"));
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/validator", method = RequestMethod.GET)
	@CrossOrigin(origins = { "http://localhost:3000", "http://52.78.51.146:8080/" })
	public ResponseEntity<?> validateId(String value, String flag) {
		if(value == null ) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		Boolean isDuplicate;
		switch(flag) {
		case "id":
			isDuplicate = authService.validateId(value);
			break;
		case "phone":
			isDuplicate = authService.validatePhone(value);
			break;
		case "nickname":
			isDuplicate = authService.validateNickname(value);
			break;
		default:
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
	}
	/*
	@RequestMapping(value= "/{phone}", method = RequestMethod.GET)
	public ResponseEntity<?> validatePhone(@PathVariable("phone") String phone){
		return new ResponseEntity<>(authService.validatePhone(phone), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{nickname}", method = RequestMethod.GET)
	public ResponseEntity<?> validateNickname(@PathVariable("nickname") String nickname){
		return new ResponseEntity<>(authService.validateNickname(nickname), HttpStatus.OK);
	}*/
}
