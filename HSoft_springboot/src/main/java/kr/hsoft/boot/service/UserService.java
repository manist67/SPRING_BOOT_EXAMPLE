package kr.hsoft.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.AuthDomain;
import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.exception.AuthNotFoundException;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public AuthDomain login(LoginDomain loginDomain) throws UserNotFoundException {
		// TODO: encrypted the password

		UserDTO user = userMapper.selectUserByIDandPassword(loginDomain);

		if (user == null) {
			throw new UserNotFoundException();
		}

		AuthDomain authDomain = new AuthDomain();

		authDomain.setUserSeq(user.getSeq());
		authDomain.setToken("asdf");
		authDomain.setReToken("asdfg");
		authDomain.setExpire(3600);

		userMapper.createAuthToken(authDomain);

		return authDomain;
	}

	public UserDomain getUser(String token) throws UserNotFoundException, AuthNotFoundException{
		AuthDomain auth = userMapper.selectToken(token);
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
}
