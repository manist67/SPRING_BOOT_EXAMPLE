package kr.hsoft.boot.service;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.PaginationDomain;
import kr.hsoft.boot.domain.SignUpDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.exception.SignUpErrorException;
import kr.hsoft.boot.mapper.UserMapper;
import kr.hsoft.boot.utils.SHA256;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public List<UserDomain> getUsers(PaginationDomain pagination) {
		List<UserDTO> users = userMapper.selectUsers(pagination);
		List<UserDomain> userDomains = new ArrayList<UserDomain>();

		for (UserDTO user : users) {
			UserDomain userDomain = new UserDomain();
			userDomain.setUserID(user.getUserID());

			userDomains.add(userDomain);
		}

		return userDomains;
	}

	public void postUsers(SignUpDomain userInfo) throws SignUpErrorException {
		if(userMapper.countUsersByID(userInfo.getUserID()) > 0) {
			throw new SignUpErrorException("userID", "아이디 중복");
		}
		
		if(userMapper.countUsersByEmail(userInfo.getEmail()) > 0) {
			throw new SignUpErrorException("email", "이메일 중복");
		}

		String gender = userInfo.getGender();
		if(!gender.equals("M") && !gender.equals("F")) {
			throw new SignUpErrorException("gender", "성별은 M / F");
		}
		
		if(userMapper.countUsersByPhone(userInfo.getPhone()) > 0) {
			throw new SignUpErrorException("phone", "휴대폰 중복");
		}

		// TODO: PHONE AUTHENTICATION
		String password = SHA256.sha256(userInfo.getPassword());
		userInfo.setPassword(password);
		
		UserDTO userDTO = new UserDTO();

		userDTO.setUserID(userInfo.getUserID());
		userDTO.setPassword(password);
		userDTO.setAddress1(userInfo.getAddress1());
		userDTO.setAddress2(userInfo.getAddress2());
		userDTO.setPhone(userInfo.getPhone());
		userDTO.setEmail(userInfo.getEmail());
		userDTO.setName(userInfo.getName());
		userDTO.setGender(userInfo.getGender());
		userDTO.setNickname(userInfo.getNickname());
		userDTO.setNickname(userInfo.getName());
		userDTO.setLocation(userInfo.getLocation());
		
		userMapper.insertUser(userDTO);
	}
}
