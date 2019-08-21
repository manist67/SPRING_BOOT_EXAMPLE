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
	
	@Autowired
	AuthMapper authMapper;

	public UserDomain getUser(String token) throws UserNotFoundException, AuthNotFoundException{
		AuthDomain auth = authMapper.selectToken(token);
		if(auth == null) {
			throw new AuthNotFoundException();
		}
		
		UserDTO user = userMapper.selectUserBySeq(auth.getUserSeq());

		if (user == null) {
			throw new UserNotFoundException();
		}
		
		UserDomain userDomain = new UserDomain();
		userDomain.setSeq(user.getSeq());
		userDomain.setUserID(user.getUserID());
		userDomain.setAddress1(user.getAddress1());
		userDomain.setAddress2(user.getAddress2());
		userDomain.setPhone(user.getEmail());
		userDomain.setEmail(user.getEmail());
		userDomain.setGender(user.getGender());

		return userDomain;
	}
	
	
	
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
