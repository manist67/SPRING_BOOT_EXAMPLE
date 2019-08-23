package kr.hsoft.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.hsoft.boot.domain.CategoryDomain;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.service.CategoryService;

public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping (method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<?> getProposal(@RequestHeader @Valid HashMap<String, String> header) throws UserNotFoundException, AuthNotFoundException {
		String token = header.get("token");
		if(token == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		List<CategoryDomain> categories = categoryService.getCategories();
		return new ResponseEntity<List<CategoryDomain>>(categories, HttpStatus.OK);
	}
}
