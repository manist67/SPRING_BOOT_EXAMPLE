package kr.hsoft.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.AuthDomain;
import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.mapper.AuthMapper;
import kr.hsoft.boot.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<UserDomain> getUsers(PaginationDomain pagination) {
		List<UserDTO> users = userMapper.selectUsers(pagination);
		List<UserDomain> userDomains = new ArrayList<UserDomain>();
		
		for(UserDTO user : users) {
			UserDomain userDomain = new UserDomain();
			userDomain.setUserID(user.getUserID());
			
			userDomains.add(userDomain);
		}
		
		return userDomains;
	}
}
