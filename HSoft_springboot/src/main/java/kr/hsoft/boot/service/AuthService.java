package kr.hsoft.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.AuthDomain;
import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.exception.UserNotFoundException;
import kr.hsoft.boot.mapper.AuthMapper;
import kr.hsoft.boot.mapper.UserMapper;

@Service
public class AuthService {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	AuthMapper authMapper;

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

		authMapper.createAuthToken(authDomain);

		return authDomain;
	}
	
	public void logout(String token) {
		authMapper.deleteToken(token);
		return;
	}
}
