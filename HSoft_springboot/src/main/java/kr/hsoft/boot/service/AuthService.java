package kr.hsoft.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hsoft.boot.domain.AuthDomain;
import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.domain.UserDomain;
import kr.hsoft.boot.dto.UserDTO;
import kr.hsoft.boot.exception.AuthNotFoundException;
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
	
	/**
	 * 
	 * @param token
	 * @return "USER", "ADMIN", "MASTER"
	 * @throws AuthNotFoundException
	 * @throws UserNotFoundException
	 */
	public String getAuthLevel(String token) throws AuthNotFoundException, UserNotFoundException{
		AuthDomain auth = authMapper.selectToken(token);
		if(auth == null) {
			throw new AuthNotFoundException();
		}

		UserDTO user = userMapper.selectUserBySeq(auth.getUserSeq());

		if (user == null) {
			throw new UserNotFoundException();
		}
		
		return user.getAuth();
	}

	public void logout(String token) {
		authMapper.deleteToken(token);
		return;
	}
}
