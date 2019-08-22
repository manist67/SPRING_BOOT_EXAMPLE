package kr.hsoft.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.domain.AuthDomain;

@Mapper
public interface AuthMapper {
	public void createAuthToken(AuthDomain authDomain);
	public AuthDomain selectToken(String token);
	public void deleteToken(String token);
	public int validateId(String id);
	public int validatePhone(String phone);
	public int validateNickname(String nickname);
}
