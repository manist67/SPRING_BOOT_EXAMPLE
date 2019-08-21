package kr.hsoft.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.hsoft.boot.domain.AuthDomain;
import kr.hsoft.boot.domain.LoginDomain;
import kr.hsoft.boot.dto.UserDTO;

@Mapper
public interface UserMapper {
	public UserDTO selectUserByIDandPassword(LoginDomain loginDomain);
	public UserDTO selectUserBySeq(Integer seq);
}
