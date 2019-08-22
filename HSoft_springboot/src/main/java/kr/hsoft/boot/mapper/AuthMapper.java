package kr.hsoft.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.hsoft.boot.domain.AuthDomain;

@Mapper
public interface AuthMapper {
	public void createAuthToken(AuthDomain authDomain);
	public AuthDomain selectToken(String token);
	public void deleteToken(String token);
	
	@Select("SELECT COUNT(*) FROM USER WHERE USER_ID = #{id}")
	public int validateId(String id);

	@Select("SELECT COUNT(*) FROM USER WHERE PHONE = #{phone}")
	public int validatePhone(String phone);

	@Select("SELECT COUNT(*) FROM USER WHERE NICKNAME = #{nickname}")
	public int validateNickname(String nickname);
}
