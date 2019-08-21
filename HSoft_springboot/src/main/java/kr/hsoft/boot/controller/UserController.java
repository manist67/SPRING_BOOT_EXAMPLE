package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.service.UserService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getUsers(@RequestHeader HashMap<String, String> header, PaginationDomain pagination) {
		
		
		// 권한 분배 이런 기타 잡다한일을 하는게 나을 것 같음
		
		List<UserDomain> users = userService.getUsers(pagination);
		
		return new ResponseEntity<List<UserDomain>>(users, HttpStatus.OK);
	}
}

/**
 * 1. 컨트롤러를 만든다. => api 정의서에 있는 url따라서 오는
 * 2. 컨트롤러에서 필요한 데이터가 무엇인지 생각한다. // 데이터를 어떻게 제어할 건지
 * 3. 그 제어에 따른 데이터모음을 생각한다.
 * 4. 데이터를 제어할 로직을 생각. 
 * 5. 데이터베이스를 어떻게 활용할지 
 */